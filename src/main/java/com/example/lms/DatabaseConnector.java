package com.example.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-16-2024
 * Class: DatabaseConnector
 *
 * The DatabaseConnector class provides a method to establish a connection to the database.
 * It uses JDBC and retrieves configuration details from the DatabaseConfig class.
 */
public class DatabaseConnector {
    public static Connection connect() throws SQLException {

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DatabaseConfiguration.getDbUrl();
            var user = DatabaseConfiguration.getDbUsername();
            var password = DatabaseConfiguration.getDbPassword();

            // Open and return a connection to the database
            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
