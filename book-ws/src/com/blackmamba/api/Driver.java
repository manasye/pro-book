package com.blackmamba.api;

import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] argc) {
        GoogleBook googleBook = new GoogleBook();
        List<Map> bookList = googleBook.searchBook("Informatics");
        int i = 1;
        for (Object book : bookList) {
            System.out.println(i);
            System.out.println(book);
            i++;
        }
    }
}
