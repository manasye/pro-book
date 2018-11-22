package com.blackmamba.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.blackmamba.model.BookDB;
import com.blackmamba.model.Book;
import com.blackmamba.model.BookDetail;

private static final class DefaultValues {
    public static String BOOK_TITLE = "Title Not Available!";
    public static String BOOK_AUTHOR = "-";
    public static String BOOK_CATEGORY = "Others";
    public static String BOOK_DESCRIPTION = "Description Not Available";
    public static String BOOK_IMAGE_URL = "https://bennetts.co.nz/wp-content/uploads/placeholder2.png";
    public static int BOOK_PRICE = -1;

}

public class GoogleBook {
    private String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";
    private BookDB bookDB;

    public GoogleBook() {
        this.bookDB = new BookDB();
    }

    private String parseSearchTerm(String searchTerm) throws UnsupportedEncodingException {
        return URLEncoder.encode(searchTerm, "UTF-8");
    }

    private URL getSearchBookUrl(String searchTerm) throws UnsupportedEncodingException, MalformedURLException {
        return new URL(BASE_API_URL + "?q=" + parseSearchTerm(searchTerm));
    }

    private URL getBookDetailUrl(String bookId) throws MalformedURLException {
        return new URL(BASE_API_URL + "/" + bookId);
    }

    private String parseInputStream(InputStreamReader inputStream) {
        try {

            BufferedReader in = new BufferedReader(inputStream);
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            return response.toString();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

            return null;

        }
    }

    public List<BookDetail> searchBook(String searchTerm) {
        List<BookDetail> bookList = new ArrayList<>();
        try {
            URL url = this.getSearchBookUrl(searchTerm);
            JSONObject books = makeConnection(url);

            JSONArray bookItem = books.getJSONArray("items");
            for (int i = 0; i < bookItem.length(); i++) {
                JSONObject item = bookItem.getJSONObject(i);
                bookList.add(parseBookDetail(item));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return bookList;
    }

    private JSONObject makeConnection(URL url) throws IOException, JSONException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Search Error!");
        }

        String response = this.parseInputStream(new InputStreamReader(connection.getInputStream()));
        if (response == null) {
            System.out.println("Failed parsing response");
        }

        System.out.println(response);
        JSONObject books = new JSONObject(response);
        return books;
    }


    private String parseBookTitle(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String title;
        if (volumeInfo.has("title")) {
            title = volumeInfo.getString("title");
        } else {
            title = DefaultValues.BOOK_TITLE;
        }
        return title;
    }

    private String parseBookAuthor(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String author;
        if (volumeInfo.has("authors")) {
            JSONArray authors = volumeInfo.getJSONArray("authors");
            if (authors.length() > 0) {
                author = authors.get(0).toString();
            } else {
                author = DefaultValues.BOOK_AUTHOR;
            }
        } else {
            author = DefaultValues.BOOK_AUTHOR;
        }
        return author;
    }

    private String parseBookCategory(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String category;
        if (volumeInfo.has("categories")) {
            JSONArray categories = volumeInfo.getJSONArray("categories");
            if (categories.length() > 0) {
                category = categories.get(0).toString();
            } else {
                category = DefaultValues.BOOK_CATEGORY;
            }
        } else {
            category = DefaultValues.BOOK_CATEGORY;
        }
        return category;
    }

    private String parseBookDescription(JSONObject book) throws JSONException {
        String description;
        if (book.has("description")) {
            description = book.getString("description");
        } else {
            description = DefaultValues.BOOK_DESCRIPTION;
        }
        return description;
    }

    private String parseBookImageUrl(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String imageUrl;
        if (volumeInfo.has("imageLinks")) {
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
            if (imageLinks.has("thumbnail")) {
                imageUrl = imageLinks.getString("thumbnail");
            } else {
                imageUrl = DefaultValues.BOOK_IMAGE_URL;
            }
        } else {
            imageUrl = DefaultValues.BOOK_IMAGE_URL;
        }
        return imageUrl;
    }

    private int getBookPrice(String id) {
        Book book = this.bookDB.getBookById(id);
        if (book != null) {
            return book.getPrice();
        } else {
            return DefaultValues.BOOK_PRICE;
        }
    }

    private BookDetail parseBookDetail(JSONObject book) {
        try {
            String id = book.getString("id");
            String title = this.parseBookTitle(book);
            String author = this.parseBookAuthor(book);
            String category = this.parseBookCategory(book);
            String description = this.parseBookDescription(book);
            String imageUrl = this.parseBookImageUrl(book);
            int price = this.getBookPrice(id);

            BookDetail bookDetail = new BookDetail(id, title, author, category, description, imageUrl, price);

            // HashMap bookDetail = new HashMap();
            //
            // bookDetail.put("id", id);
            // bookDetail.put("title", title);
            // bookDetail.put("author", author);
            // bookDetail.put("category", category);
            // bookDetail.put("description", description);
            // bookDetail.put("imageUrl", imageUrl);
            // bookDetail.put("price", price);

            return bookDetail;

        } catch (JSONException ex) {

            return null;
        }
    }

    public BookDetail getBookDetail(String id) {
        try {
            URL url = this.getBookDetailUrl(id);
            System.out.println(url);
            JSONObject book = makeConnection(url);

            BookDetail bookDetail = this.parseBookDetail(book);

            System.out.println(bookDetail.getTitle());

            return bookDetail;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;

        }
    }

}
