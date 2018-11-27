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

import com.blackmamba.model.BookDetail;
import org.json.simple.JSONObject;

import com.blackmamba.model.Book;
import com.blackmamba.api.GoogleBook;

import com.blackmamba.api.GoogleBook;

@WebService
@SOAPBinding(style = Style.RPC, use = SOAPBinding.Use.ENCODED)
public class BookWS {
    @WebMethod
    public ListBooks searchTitle(String title) {
        GoogleBook googleBook = new GoogleBook();
        List<BookDetail> books = googleBook.searchBookByTitle(title);
        ListBooks bookList = new ListBooks(books);
        return bookList;
    }

    @WebMethod
    public BookDetail searchDetail(String id) {
        GoogleBook googleBook = new GoogleBook();
        BookDetail bookDetail = googleBook.getBookDetail(id);
        return bookDetail;
    }

    @WebMethod
    public ListBooks getBookRecommendation(String[] categories) {
        GoogleBook googleBook = new GoogleBook();
        List<BookDetail> bookRecommendations = googleBook.getBookRecommendation(categories);
        System.out.println("BOOKREC LEN = " + bookRecommendations.size());
        if (bookRecommendations == null) {
            return null;
        } else {
            ListBooks bookRecommendationsList = new ListBooks(bookRecommendations);
            return bookRecommendationsList;
        }
    }

    // TODO: implement book transaction
//    @WebMethod
//    public ListBooks getBookRecommendation(String id) {
//    }

}
