package com.blackmamba.api;

import com.blackmamba.model.Book;
import com.blackmamba.model.BookDB;
import com.blackmamba.model.TransactionResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BankAPI extends BaseAPI {
    private String BASE_API_URL;

    private static int MISSING_BOOK_PRICE = -1;

    private String MERCHANT_SECRET;

    private BookDB bookDB;

    public BankAPI() {
        Dotenv dotenv = Dotenv.load();
        this.BASE_API_URL = dotenv.get("BANK_HOST");
        this.MERCHANT_SECRET = dotenv.get("BANK_MERCHANT_SECRET");
        this.bookDB = new BookDB();
    }

    private URL getPaymentUrl() {
        try {
            return new URL(BASE_API_URL + "/payment");
        } catch (MalformedURLException ex) {
            System.out.println("[ERROR getPaymentUrl] - " + ex.getMessage());
            return null;
        }
    }

    private JSONObject _makePayment(String cardNumber, String token, int transactionAmount, int attempLeft) throws IOException, JSONException {
        if (attempLeft <= 0) {
            return null;
        }

        URL url = this.getPaymentUrl();
        if (url == null) {
            System.out.println("[ERROR _makePayment] - payment url malformed");
            return null;
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        JSONObject requestBody   = new JSONObject();
        requestBody.put("cardNumber", cardNumber);
        requestBody.put("token", token);
        requestBody.put("amount", transactionAmount);
        requestBody.put("merchantSecret", this.MERCHANT_SECRET);

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(requestBody.toString());
        wr.flush();
        wr.close();

        connection.connect();

        int responseCode = connection.getResponseCode();
        String response = this.parseInputStream(new InputStreamReader(connection.getInputStream()));
        if (responseCode != 200) {
            return this._makePayment(cardNumber, token, transactionAmount, attempLeft - 1);
        }

        if (response == null) {
            System.out.println("[ERROR _makePayment] - Failed parsing response");
        }

        JSONObject jsonObject = new JSONObject(response);
        return jsonObject;
    }

    private JSONObject makePayment(String cardNumber, String token, int transactionAmount) {
        try {
            return this._makePayment(cardNumber, token, transactionAmount, 3);
        } catch (IOException ex) {
            System.out.println("[ERROR makePayment] - IOException: " + ex.getMessage());
            return null;
        } catch (JSONException ex) {
            System.out.println("[ERROR makePayment] - JSONException: " + ex.getMessage());
            return null;
        }
    }

    private int getTransactionAmount(String bookId, int bookAmount) {
        Book book = this.bookDB.getBookById(bookId);
        if (book == null) {
            System.out.println("[getTransactionAmount] - book not found");
            return BankAPI.MISSING_BOOK_PRICE;
        }
        return book.getPrice() * bookAmount;
    }

    private boolean isPaymentSucceed(JSONObject paymentJsonObject) {
        try {
            return paymentJsonObject.has("success") && paymentJsonObject.getBoolean("success");
        } catch (JSONException ex) {
            System.out.println("[ERROR isPaymentSucceed] - " + ex.getMessage());
            return false;
        }
    }

    private String getPaymentResponseMessage(JSONObject paymentJsonObject) {
        try {
            if (!paymentJsonObject.has("success")) {
                return "Error connecting to bank";
            }
            return paymentJsonObject.getString("message");
        } catch (JSONException ex) {
            System.out.println("[ERROR getPaymentResponseMessage] - " + ex.getMessage());
            return null;
        }
    }

    public TransactionResponse makeTransaction(String cardNumber, String token, String bookId, int bookAmount) {
        if (bookAmount <= 0) {
            System.out.println("[ERROR makeTransaction] - bookAmount must be more than 0");
            return new TransactionResponse(false, "Book amount must be more than 0");
        }

        int transactionAmount = this.getTransactionAmount(bookId, bookAmount);
        if (transactionAmount == BankAPI.MISSING_BOOK_PRICE) {
            System.out.println("[ERROR makeTransaction] - book not found");
            return new TransactionResponse(false, "Book " + bookId + " not found");
        }

        JSONObject paymentJsonObject = this.makePayment(cardNumber, token, transactionAmount);
        String paymentResponseMessage = this.getPaymentResponseMessage(paymentJsonObject);
        if (paymentJsonObject == null || !this.isPaymentSucceed(paymentJsonObject)) {
            System.out.println("[ERROR makeTransaction] - payment failed");
            return new TransactionResponse(false, "Payment failed - " + paymentResponseMessage);
        }

        if (!this.bookDB.addBookSold(bookId, bookAmount)) {
            System.out.println("[ERROR makeTransaction] - fail increment sold for book " + bookId);
            return new TransactionResponse(false, "Transaction failed");
        }

        return new TransactionResponse(true, "Transaction succeed");
    }
}
