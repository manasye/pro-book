package com.blackmamba.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blackmamba.model.ListBooks;
import org.json.simple.JSONObject;

import com.blackmamba.model.Book;
import com.blackmamba.api.GoogleBook;

@WebService
@SOAPBinding(style= Style.RPC, use= SOAPBinding.Use.ENCODED)
public class BookWS {
    @WebMethod
    public ListBooks searchTitle(String title) {
        GoogleBook googleBook = new GoogleBook();
        List<Map> books = googleBook.searchBook(title);
        ListBooks bookList = new ListBooks(books);
        return bookList;
    }

    @WebMethod
    public String searchDetail(String id) throws IOException {
        return "huyu";
    }

//    @WebMethod

}

