package com.blackmamba.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseAPI {
    protected String parseInputStream(InputStreamReader inputStream) {
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
}
