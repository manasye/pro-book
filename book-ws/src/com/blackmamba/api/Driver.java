package com.blackmamba.api;

import java.util.List;

public class Driver {
    public static void main(String[] argc) {
        GoogleBook googleBook = new GoogleBook();
        List<Object> bookList = googleBook.searchBook("How To Win Friends and Influence People");
        int i = 1;
        for (Object book : bookList) {
            System.out.println(i);
            System.out.println(book);
            i++;
        }
    }
}
