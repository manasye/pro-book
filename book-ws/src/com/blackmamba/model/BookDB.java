package com.blackmamba.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        } else if (resultSet.next()) {
            String bookId = resultSet.getString("id");
            int bookPrice = Integer.parseInt(resultSet.getString("price"));
            String bookCategory = resultSet.getString("category");
            int bookSold = Integer.parseInt(resultSet.getString("sold"));
            return new Book(bookId, bookPrice, bookCategory, bookSold);
        } else {
            return null;
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
            return this.getBookFromResultSet(resultSet);

        } catch (SQLException ex) {
            System.out.println("[ERROR getBookById] - " + ex.getMessage());
            return null;

        }
    }

    public boolean addBookSold(String id, int bookQuantity) {
        Book book = this.getBookById(id);
        if (book == null) {
            return false;
        }
        book.setSold(book.getSold() + bookQuantity);
        this.updateBook(book);
        return true;
    }

    public boolean insertBook(Book book) {
        try {
            // Parse book
            String id = book.getId();
            int price = book.getPrice();
            String category = book.getCategory();
            int sold = book.getSold();

            // Check whether the book already existed
            if (getBookById(id) != null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO book VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, sold);

            // Execute query
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("[ERROR insertBook] - " + ex.getMessage());
            return false;
        }
    }

    public boolean updateBook(Book book) {
        try {
            // Parse book
            String id = book.getId();
            int price = book.getPrice();
            String category = book.getCategory();
            int sold = book.getSold();

            // Check whether the book already existed
            if (getBookById(id) == null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE book SET price =  ?, category = ?, sold = ? WHERE id = ?;");
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, sold);
            preparedStatement.setString(4, id);

            // Execute query
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("[ERROR updateBook] - " + ex.getMessage());
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
            System.out.println("[ERROR deleteBookById] - " + ex.getMessage());
            return false;
        }
    }

    public Book getBookRandom() {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM book ORDER BY RAND() LIMIT ?;");
            preparedStatement.setInt(1, 1);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            return this.getBookFromResultSet(resultSet);
        } catch (SQLException ex) {
            System.out.println("[ERROR getBookRandom] - " + ex.getMessage());
            return null;
        }
    }

    public List<Book> parseBookResultSet(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<Book>();
        while (resultSet != null && resultSet.next()) {
            String bookId = resultSet.getString("id");
            int bookPrice = Integer.parseInt(resultSet.getString("price"));
            String bookCategory = resultSet.getString("category");
            int bookSold = Integer.parseInt(resultSet.getString("sold"));
            Book book = new Book(bookId, bookPrice, bookCategory, bookSold);
            bookList.add(book);
        }
        return bookList;
    }

    public List<Book> getBookByCategory(String category) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM book where category = ?;");
            preparedStatement.setString(1, category);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            List<Book> bookList = this.parseBookResultSet(resultSet);

            return bookList;
        } catch (SQLException ex) {
            System.out.println("[ERROR getBookByCategory] - " + ex.getMessage());
            return null;
        }
    }

    public List<Book> getHighestSoldBookByCategories(String[] categories) {
        try {
            String sqlStatement = "";
            for (int i = 0; i < categories.length; i++) {
                if (i == 0) {
                    sqlStatement += "SELECT * FROM book WHERE category IN (?";
                } else {
                    sqlStatement += ", ?";
                }
            }
            sqlStatement +=  ") AND `sold` > 0 ORDER BY `sold` DESC LIMIT 3;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlStatement);
            for (int i = 1; i <= categories.length; i++) {
                preparedStatement.setString(i, categories[i - 1]);
            }

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            List<Book> bookList = this.parseBookResultSet(resultSet);

            return bookList;

        } catch (SQLException ex) {
            System.out.println("[ERROR getHighestSoldBookByCategories] - " + ex.getMessage());
            return null;
        }
    }

}
