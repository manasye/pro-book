package com.blackmamba.api;

import com.blackmamba.model.Book;
import com.blackmamba.model.BookDB;
import com.blackmamba.model.SoldDB;
import com.blackmamba.model.TransactionResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BankAPI extends BaseAPI {
    private String BASE_API_URL = "http://localhost:3000/production";

    private static int MISSING_BOOK_PRICE = -1;

    private static String MERCHANT_SECRET = "JokowiMaruf2019";

    private BookDB bookDB;
    private SoldDB soldDB;

    public BankAPI() {
        this.bookDB = new BookDB();
        this.soldDB = new SoldDB();
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
        requestBody.put("merchantSecret", BankAPI.MERCHANT_SECRET);

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

    public TransactionResponse makeTransaction(String cardNumber, String token, String bookId, int bookAmount) {
        if (bookAmount <= 0) {
            System.out.println("[ERROR makeTransaction] - bookAmount must be more than 0");
            return new TransactionResponse(false, "book amount must be more than 0");
        }

        int transactionAmount = this.getTransactionAmount(bookId, bookAmount);
        if (transactionAmount == BankAPI.MISSING_BOOK_PRICE) {
            System.out.println("[ERROR makeTransaction] - book not found");
            return new TransactionResponse(false, "book " + bookId + " not found");
        }

        JSONObject paymentJsonObject = this.makePayment(cardNumber, token, transactionAmount);
        if (paymentJsonObject == null || !this.isPaymentSucceed(paymentJsonObject)) {
            System.out.println("[ERROR makeTransaction] - payment failed");
            return new TransactionResponse(false, "payment failed");
        }

        if (!this.bookDB.incrementBookSold(bookId)) {
            System.out.println("[ERROR makeTransaction] - fail increment sold for book " + bookId);
            return new TransactionResponse(false, "transaction failed");
        }

        return new TransactionResponse(true, "transaction succeed");
    }
}
