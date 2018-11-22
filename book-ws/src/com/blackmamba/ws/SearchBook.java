package com.blackmamba.ws;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchBook {
    public JSONObject searchBook() {
        JSONObject books = new JSONObject();
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=informatics");
            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            books = new JSONObject(response.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
