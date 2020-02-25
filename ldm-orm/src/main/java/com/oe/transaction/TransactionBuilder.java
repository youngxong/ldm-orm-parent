package com.oe.transaction;

import java.sql.Connection;

public class TransactionBuilder {
    Transaction transaction;

    public TransactionBuilder(Connection connection) {
        this.transaction = new JdbcTransaction(connection);
    }

    public  Transaction  build(){
        return null;
    }
}
