package com.blackmamba.model;

public class Driver {
    public static void main(String[] args) {
        BookDB bookDB = new BookDB();
        Book book = bookDB.getBookById(1);
        System.out.println(book);
    }
}
