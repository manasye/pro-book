package com.blackmamba.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseModel {
    private String url;
    private String user;
    private String password;

    public BaseModel() {
        this.url = "jdbc:mysql://localhost:3306/probook2";
        this.user = "root";
        this.password = "root";
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
