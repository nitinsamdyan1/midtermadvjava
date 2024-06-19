package com.example.exam1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://mysql:3306/MedicalRecords";
    private static final String USER = "root";
    private static final String PASSWORD = "nitin9255";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

