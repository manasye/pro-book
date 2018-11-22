package com.blackmamba.model;

public class Driver {
    public static void main(String[] args) {
//        BookDB bookDB = new BookDB();

        // Test READ book
//        Book book = bookDB.getBookById(1);
//        System.out.println(book);

        // Test DELETE book
//        int deleteBookId = 6;
//        boolean isDeleteSucceed = bookDB.deleteBookById(deleteBookId);
//        if (isDeleteSucceed) {
//            System.out.println("Deleting book with id = " + deleteBookId + " succeed!");
//        } else {
//            System.out.println("Deleting book with id = " + deleteBookId + " FAILEDDDDD!");
//        }

        // Test CREATE book
//        Book insertedBook = new Book(6, 34243432);
//        bookDB.insertBook(insertedBook);


        SoldDB soldDB = new SoldDB();

        soldDB.printAllSold();
    }
}
