package com.blackmamba.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Books {

    public static void main(String[] args) {
        BaseModel model = new BaseModel();
        String query = "SELECT * FROM book;";

        try (Connection con = model.connect();
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
