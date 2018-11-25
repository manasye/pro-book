package com.blackmamba;

import java.util.Properties;

public class AppProperties extends Properties {
    public AppProperties() {
        super();

        this.setProperty("MYSQL_URL", "jdbc:mysql://localhost:3306/probook");
        this.setProperty("MYSQL_USER", "root");
        this.setProperty("MYSQL_PASSWORD", "");
    }
}
