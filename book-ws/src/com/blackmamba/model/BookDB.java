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

    public Book getBookById(String id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM book where id = ?;");
            preparedStatement.setString(1, id);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            resultSet.next();
            String bookId = resultSet.getString("id");
            int bookPrice = Integer.parseInt(resultSet.getString("price"));

            return new Book(bookId, bookPrice);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;

        }
    }

    public boolean insertBook(Book book) {
        try {
            // Parse book
            String id = book.getId();
            int price = book.getPrice();

            // Check whether the book already existed
            if (getBookById(id) != null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO book VALUES (?, ?);");
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, price);

            // Execute query
            System.out.println(preparedStatement.executeUpdate());

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateBook(Book book) {
        try {
            // Parse book
            String id = book.getId();
            int price = book.getPrice();

            // Check whether the book already existed
            if (getBookById(id) == null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE book SET price =  ? WHERE id = ?;");
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, id);

            // Execute query
            System.out.println(preparedStatement.executeUpdate());

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deleteBookById(String id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM book WHERE id = ?;");
            preparedStatement.setString(1, id);

            // Execute query
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void printAllBooks() {
        String query = "SELECT * FROM book;";
        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("id");
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
