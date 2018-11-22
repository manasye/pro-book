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
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM book where id = ?;");
            preparedStatement.setInt(1, id);

            // Execute query
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

    public boolean insertBook(Book book) {
        try {
            // Parse book
            int id = book.getId();
            int price = book.getPrice();

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO book VALUES (?, ?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, price);

            // Execute query
            System.out.println(preparedStatement.executeUpdate());

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deleteBookById(int id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM book WHERE id = ?;");
            preparedStatement.setInt(1, id);

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
