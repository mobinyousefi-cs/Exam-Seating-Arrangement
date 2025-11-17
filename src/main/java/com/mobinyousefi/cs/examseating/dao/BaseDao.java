package com.mobinyousefi.cs.examseating.dao;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDao {

    private final DBConnectionManager connectionManager;

    protected BaseDao(DBConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
}
