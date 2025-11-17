package com.mobinyousefi.cs.examseating.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple JDBC connection manager. In a production system you would prefer a
 * proper connection pool such as HikariCP, but this is sufficient for a
 * teaching/demo project.
 */
public class DBConnectionManager {

    private final String url;
    private final String username;
    private final String password;

    public DBConnectionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC driver not found", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void close() {
        // no-op, kept for symmetry and future extension
    }
}
