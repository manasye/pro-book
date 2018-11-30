package com.blackmamba.model;

import java.util.List;
import java.util.Map;

public class ListBooks {

    private List<BookDetail> bookList;

    public ListBooks(List<BookDetail> bookList) {
        this.bookList = bookList;
    }

    public List<BookDetail> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDetail> bookList) {
        this.bookList = bookList;
    }
}
