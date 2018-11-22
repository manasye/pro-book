package com.blackmamba.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleBookAPI {
    private String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";

    private String parseSearchTerm(String searchTerm) throws UnsupportedEncodingException {
        return URLEncoder.encode(searchTerm, "UTF-8");
    }

    public String getSearchBookUrl(String searchTerm) {
        return BASE_API_URL + "?q=" + parseSearchTerm(searchTerm);
    }

    public String getBookDetail(String bookId) {
        return BASE_API_URL + "/" + bookId;
    }

    // TODO: create method to communicate with google book api
}
