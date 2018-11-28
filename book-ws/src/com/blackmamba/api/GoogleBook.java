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
import java.util.*;

//import com.blackmamba.model.SoldDB;
//import com.blackmamba.model.Sold;
import com.blackmamba.model.BookDB;
import com.blackmamba.model.Book;
import com.blackmamba.model.BookDetail;


//class SortbySold implements Comparator<Book> {
//    // Used for sorting in ascending order of
//    // roll number
//    public int compare(Book a, Book b) {
//        return b.getSold() - a.getSold();
//    }
//}

public class GoogleBook extends BaseAPI {
    private String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";
    private BookDB bookDB;
//    private SoldDB soldDB;

    private static String BOOK_TITLE = "Title Is Not Available!";
    private static String BOOK_AUTHOR = "-";
    private static String BOOK_CATEGORY = "Others";
    private static String BOOK_DESCRIPTION = "Description Is Not Available";
    private static String BOOK_IMAGE_URL = "https://bennetts.co.nz/wp-content/uploads/placeholder2.png";
    private static int BOOK_PRICE = -1;

    private static int MAX_RECOMMENDATION = 5;

    public GoogleBook() {
        this.bookDB = new BookDB();
//        this.soldDB = new SoldDB();
    }

    private String parseSearchTerm(String searchTerm) throws UnsupportedEncodingException {
        return URLEncoder.encode(searchTerm, "UTF-8");
    }

    private URL getSearchBookByTitleUrl(String title) throws UnsupportedEncodingException, MalformedURLException {
        return new URL(BASE_API_URL + "?q=" + parseSearchTerm(title));
    }

    private URL getSearchBookByCategoryUrl(String category) throws UnsupportedEncodingException, MalformedURLException {
        return new URL(BASE_API_URL + "?q=subject:" + parseSearchTerm(category));
    }

    private URL getBookDetailUrl(String bookId) throws MalformedURLException {
        return new URL(BASE_API_URL + "/" + bookId);
    }

//    private String parseInputStream(InputStreamReader inputStream) {
//        try {
//
//            BufferedReader in = new BufferedReader(inputStream);
//            StringBuffer response = new StringBuffer();
//            String inputLine;
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            in.close();
//
//            return response.toString();
//
//        } catch (IOException ex) {
//
//            System.out.println(ex.getMessage());
//
//            return null;
//
//        }
//    }

    public List<BookDetail> searchBookByTitle(String title) {
        try {
            URL url = this.getSearchBookByTitleUrl(title);
            return this.searchBook(url);
        } catch (Exception ex) {
            System.out.println("[ERROR searchBookByTitle]: " + ex.getMessage());
            return null;
        }
    }

    public List<BookDetail> searchBookByCategory(String category) {
        try {
            URL url = this.getSearchBookByCategoryUrl(category);
            return this.searchBook(url);
        } catch (Exception ex) {
            System.out.println("[ERROR searchBookByTitle] " + ex.getMessage());
            return null;
        }
    }

    public List<BookDetail> searchBook(URL url) {
        List<BookDetail> bookList = new ArrayList<>();
        try {
            JSONObject books = makeConnection(url);
            JSONArray bookItem = books.getJSONArray("items");
            for (int i = 0; i < bookItem.length(); i++) {
                JSONObject item = bookItem.getJSONObject(i);
                bookList.add(parseBookDetail(item));
            }
        } catch (Exception e) {
            System.out.println("[ERROR searchBook] " + e.getMessage());

        }
        return bookList;
    }

    private JSONObject _makeConnection(URL url, int attempLeft) throws IOException, JSONException {
        if (attempLeft <= 0) {
            return null;
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            return this._makeConnection(url, attempLeft - 1);
        }

        String response = this.parseInputStream(new InputStreamReader(connection.getInputStream()));
        if (response == null) {
            System.out.println("Failed parsing response");
        }

        JSONObject books = new JSONObject(response);
        return books;
    }

    private JSONObject makeConnection(URL url) throws IOException, JSONException {
        return this._makeConnection(url, 3);
    }

    private String parseBookTitle(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String title;
        if (volumeInfo.has("title")) {
            title = volumeInfo.getString("title");
        } else {
            title = GoogleBook.BOOK_TITLE;
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
                author = GoogleBook.BOOK_AUTHOR;
            }
        } else {
            author = GoogleBook.BOOK_AUTHOR;
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
                category = GoogleBook.BOOK_CATEGORY;
            }
        } else {
            category = GoogleBook.BOOK_CATEGORY;
        }
        return category;
    }

    private String parseBookDescription(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String description;
        if (volumeInfo.has("description")) {
            description = volumeInfo.getString("description");
        } else {
            description = GoogleBook.BOOK_DESCRIPTION;
        }
        return description;
    }

    private String parseBookImageUrl(JSONObject book) throws JSONException {
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        String imageUrl;
        if (volumeInfo.has("imageLinks")) {
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
            if (imageLinks.has("extraLarge")) {
                imageUrl = imageLinks.getString("extraLarge");
            } else if (imageLinks.has("large")) {
                imageUrl = imageLinks.getString("large");
            } else if (imageLinks.has("medium")) {
                imageUrl = imageLinks.getString("medium");
            } else if (imageLinks.has("small")) {
                imageUrl = imageLinks.getString("small");
            } else if (imageLinks.has("thumbnail")) {
                imageUrl = imageLinks.getString("thumbnail");
            } else {
                imageUrl = GoogleBook.BOOK_IMAGE_URL;
            }
        } else {
            imageUrl = GoogleBook.BOOK_IMAGE_URL;
        }
        return imageUrl;
    }

    private int getBookPrice(String id) {
        Book book = this.bookDB.getBookById(id);
        if (book != null) {
            return book.getPrice();
        } else {
            return GoogleBook.BOOK_PRICE;
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

            return bookDetail;

        } catch (JSONException ex) {

            return null;
        }
    }

    public BookDetail getBookDetail(String id) {
        try {
            URL url = this.getBookDetailUrl(id);
            JSONObject book = makeConnection(url);
            BookDetail bookDetail = this.parseBookDetail(book);
            return bookDetail;
        } catch (Exception ex) {
            System.out.println("[ERROR getBookDetail] " + ex.getMessage());
            return null;
        }
    }

    public BookDetail getRandomBookDetail() {
        try {
            Book book = this.bookDB.getBookRandom();
            return this.getBookDetail(book.getId());
        } catch (Exception ex) {
            System.out.println("[ERROR getOneBookNoMatterWhat] " + ex.getMessage());
            return null;
        }
    }

    public List<BookDetail> getBookRecommendation(String[] categories) {
        if (categories.length == 0) {
            return null;
        }

        List<Book> bookList = this.bookDB.getHighestSoldBookByCategories(categories);
        if (bookList == null) {
            return null;
        } else if (bookList.size() > 0) {
            List<BookDetail> bookRecommendations = new ArrayList<>();
            for (Book book : bookList) {
                BookDetail curHighestSoldBookDetail = this.getBookDetail(book.getId());
                if (curHighestSoldBookDetail != null) {
                    bookRecommendations.add(curHighestSoldBookDetail);
                }
            }
            return bookRecommendations;
        } else {
            List<BookDetail> bookDetails = this.searchBookByCategory(categories[0]);
            if (bookDetails.size() > 0) {
                bookDetails = new ArrayList<BookDetail>(bookDetails.subList(0, 1));
            } else {
                bookDetails.add(this.getRandomBookDetail());
            }
            return bookDetails;
        }
    }

}
