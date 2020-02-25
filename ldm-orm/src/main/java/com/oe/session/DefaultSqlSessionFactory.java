package com.oe.session;

import com.oe.config.Configuration;

public class DefaultSqlSessionFactory  implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        return  new DefaultSqlSession(configuration);
    }

    @Override
    public SqlSession openSession(boolean isAutoCommit) {
        return  new DefaultSqlSession(configuration,isAutoCommit);
    }
}
