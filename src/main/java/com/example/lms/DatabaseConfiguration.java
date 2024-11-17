package com.example.lms;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-16-2024
 * Class: DatabaseConfiguration
 *
 * The DatabaseConfiguration class provides configuration details for connecting to the database.
 * It contains methods to retrieve the database URL, username, and password.
 */
public class DatabaseConfiguration {

    // Database connection details
    static String url = "jdbc:mysql://localhost:3306/lms";
    static String username = "lms";
    static String password = "TB7f[18lomEibEvI";

    /**
     * Retrieves the database URL.
     *
     * @return The URL of the database as a String.
     */
    public static String getDbUrl() {
        return url;
    }

    /**
     * Retrieves the database username.
     *
     * @return The username for database access as a String.
     */
    public static String getDbUsername() {
        return username;
    }

    /**
     * Retrieves the database password.
     *
     * @return The password for database access as a String.
     */
    public static String getDbPassword() {
        return password;
    }
}