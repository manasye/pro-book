package com.blackmamba.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Books {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/probook";
        String user = "root";
        String password = "";

        String query = "SELECT * FROM book;";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("id = " + rs.getString(1) + ", price = " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println("gagal brader");
            System.out.println(ex.getMessage());
        }
    }
}
