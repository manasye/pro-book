package com.blackmamba.model;

import java.sql.*;

public class SoldDB extends BaseModel {
    private Connection connection;

    public SoldDB() {
        super();
        try {
            this.connection = this.connect();
        } catch (SQLException ex) {
            System.out.println("BookDB Failed to Connect to MySQL Server");
            System.out.println(ex.getMessage());
            this.connection = null;
        }
    }

    public Sold getSoldById(int id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM sold where id = ?;");
            preparedStatement.setInt(1, id);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            resultSet.next();
            int soldId = Integer.parseInt(resultSet.getString("id"));
            String soldCategory = resultSet.getString("price");
            int soldCount = resultSet.getShort("count");

            return new Sold(soldId, soldCategory, soldCount);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return null;

        }
    }

    public boolean insertSold(Sold sold) {
        try {
            // Parse book
            int id = sold.getId();
            String category = sold.getCategory();
            int count = sold.getCount();

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO sold VALUES (?, ?, ?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, count);

            // Execute query
            System.out.println(preparedStatement.executeUpdate());

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deleteSoldById(int id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM sold WHERE id = ?;");
            preparedStatement.setInt(1, id);

            // Execute query
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void printAllSold() {
        String query = "SELECT * FROM sold;";
        try (Statement st = this.connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String category = rs.getString("category");
                int count = Integer.parseInt(rs.getString("count"));
                Sold sold = new Sold(id, category, count);
                System.out.println(sold);
            }

        } catch (SQLException ex) {
            System.out.println("gagal brader");
            System.out.println(ex.getMessage());
        }
    }
}
