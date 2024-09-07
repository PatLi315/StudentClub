package com.unimelb.swen90007.studentclub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:postgresql://dpg-cqvbnfdds78s739ghsrg-a.oregon-postgres.render.com:5432/sda_agw5";
    private static final String JDBC_USERNAME = "admin";
    private static final String JDBC_PASSWORD = "Cw2dSOz91dJ8TymtnKDgxcVHuj8dP2NJ";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
