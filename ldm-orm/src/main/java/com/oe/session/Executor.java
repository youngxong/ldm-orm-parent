package com.oe.session;

import com.oe.config.Configuration;
import com.oe.mappers.MappedStatement;
import com.oe.transaction.Transaction;

import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    public int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
