package com.blackmamba.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.blackmamba.AppProperties;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseModel {
    private String url;
    private String user;
    private String password;

    public BaseModel() {
        Properties appProperties = new AppProperties();

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
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
