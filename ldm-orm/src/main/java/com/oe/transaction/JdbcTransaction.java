package com.oe.transaction;

import com.oe.log.BaseLog;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransaction extends BaseLog implements Transaction {

    private Connection connection;


    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
