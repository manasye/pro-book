package com.blackmamba.model;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class BaseModel {
    private String url;
    private String user;
    private String password;

    public BaseModel() {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("MYSQL_URL");
        this.user = dotenv.get("MYSQL_USER");
        this.password = dotenv.get("MYSQL_PASSWORD");
//        this.url = appProperties.getProperty("MYSQL_URL");
//        this.user = appProperties.getProperty("MYSQL_USER");
//        this.password = appProperties.getProperty("MYSQL_PASSWORD");
    }

    public BaseModel(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR connection] - " + e.getMessage());
            return null;
        }
        TimeZone timeZone = TimeZone.getTimeZone("yourTimeZone"); // e.g. "Europe/Rome"
        System.out.println("HUYUUU: " + timeZone);
        TimeZone.setDefault(timeZone);
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
