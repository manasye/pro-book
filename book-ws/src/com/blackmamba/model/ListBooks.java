package com.blackmamba.model;

import java.util.List;
import java.util.Map;

public class ListBooks {

    private List<Map> bookList;

    public ListBooks(List<Map> bookList) {
        this.bookList = bookList;
    }

    public List<Map> getBookList() {
        return bookList;
    }

    public void setBookList(List<Map> bookList) {
        this.bookList = bookList;
    }
}
