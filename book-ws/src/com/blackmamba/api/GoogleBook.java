package com.blackmamba.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleBook {
    private String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";

    private String parseSearchTerm(String searchTerm) throws UnsupportedEncodingException {
        return URLEncoder.encode(searchTerm, "UTF-8");
    }

    private URL getSearchBookUrl(String searchTerm) throws UnsupportedEncodingException, MalformedURLException {
        return new URL(BASE_API_URL + "?q=" + parseSearchTerm(searchTerm));
    }

    private URL getBookDetail(String bookId) throws MalformedURLException {
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

    public void searchBook(String searchTerm) {
        try {
            URL url = this.getSearchBookUrl(searchTerm);
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

            JSONObject books = new JSONObject(response);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

//        return books;
    }
}
