package com.blackmamba.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoldDB extends BaseModel {
    private Connection connection;

    public SoldDB() {
        super();
        try {
            this.connection = this.connect();
        } catch (SQLException ex) {
            System.out.println("SoldDB Failed to Connect to MySQL Server");
            System.out.println(ex.getMessage());
            this.connection = null;
        }
    }

    public Sold getSoldById(String id) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM sold where id = ?;");
            preparedStatement.setString(1, id);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            resultSet.next();
            String soldId = resultSet.getString("id");
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
            String id = sold.getId();
            String category = sold.getCategory();
            int count = sold.getCount();

            // Check whether the sold already existed
            if (getSoldById(id) != null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO sold VALUES (?, ?, ?);");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, count);

            // Execute query
            System.out.println(preparedStatement.executeUpdate());

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<Sold> parseSoldBookResultSet(ResultSet resultSet) throws SQLException {
        List<Sold> soldList = new ArrayList<Sold>();
        while (resultSet != null && resultSet.next()) {
            String soldId = resultSet.getString("id");
            String soldCategory = resultSet.getString("category");
            int soldCount = resultSet.getShort("count");
            Sold soldBook = new Sold(soldId, soldCategory, soldCount);
            soldList.add(soldBook);
        }
        return soldList;
    }

    public List<Sold> getSoldByCategory(String category) {
        try {
            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM sold where category = ?;");
            preparedStatement.setString(1, category);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            List<Sold> soldList = this.parseSoldBookResultSet(resultSet);

            return soldList;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;

        }
    }

    public List<Sold> getHighestSoldByCategories(String[] categories) {
        try {
            String sqlStatement = "";
            for (int i = 0; i < categories.length; i++) {
                if (i == 0) {
                    sqlStatement += "SELECT * FROM sold WHERE category IN (?";
                } else {
                    sqlStatement += ", ?";
                }
            }
            sqlStatement +=  ") ORDER BY `count` DESC LIMIT 3;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlStatement);
            for (int i = 1; i <= categories.length; i++) {
                preparedStatement.setString(i, categories[i - 1]);
            }

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse query
            List<Sold> soldList = this.parseSoldBookResultSet(resultSet);

            return soldList;

        } catch (SQLException ex) {
            System.out.println("FUNCTION ERRORRRRRR . . .");
            System.out.println(ex.getMessage());
            return null;

        }
    }

    public boolean updateSold(Sold sold) {
        try {
            // Parse book
            String id = sold.getId();
            String category = sold.getCategory();
            int count = sold.getCount();

            // Check whether the sold already existed
            if (getSoldById(id) == null) {
                return false;
            }

            // Prepare query
            PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE sold SET category =  ?, count = ? WHERE id = ?;");
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, count);
            preparedStatement.setString(3, id);

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
                String id = rs.getString("id");
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
