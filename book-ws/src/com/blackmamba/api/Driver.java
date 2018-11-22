package com.blackmamba.api;

import java.io.UnsupportedEncodingException;

public class Driver {
    public static void main(String[] argc) {
        try {
            GoogleBook gbook = new GoogleBook();
            String searchTerm = "Harry Potter";
            System.out.println("Before string : " + searchTerm);
            System.out.println("After string  : " + gbook.parseSearchTerm(searchTerm));
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
