package com.blackmamba.model;

import java.sql.*;

public class BookDB extends BaseModel {
    private Connection connection;

    public BookDB() {
        super();
        try {
            this.connection = this.connect();
        } catch (SQLException ex) {
            System.out.println("BookDB Failed to Connect to MySQL Server");
            System.out.println(ex.getMessage());
            this.connection = null;
        }
    }

    public Book getBookById(int id) {
        try {
            // Prepare and execute query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM book where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            resultSet.next();
            int bookId = Integer.parseInt(resultSet.getString("id"));
            int bookPrice = Integer.parseInt(resultSet.getString("price"));

            return new Book(bookId, bookPrice);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;

        }
    }

    // TODO: method to insert book

    // TODO: method to update book

    public void printAllBooks() {
        String query = "SELECT * FROM book;";
        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                int price = Integer.parseInt(rs.getString("price"));
                Book book = new Book(id, price);
                System.out.println(book);
            }

        } catch (SQLException ex) {
            System.out.println("gagal brader");
            System.out.println(ex.getMessage());
        }
    }
}
