package com.oe.session;

public interface SqlSessionFactory {

    public SqlSession openSession();

    public SqlSession openSession(boolean isAutoCommit);


}
