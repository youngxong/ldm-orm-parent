package com.oe.session;


import com.oe.config.Configuration;
import com.oe.exception.ExceptionBuilder;
import com.oe.mappers.MappedStatement;
import com.oe.mappers.SqlCommandType;
import com.oe.transaction.JdbcTransaction;
import com.oe.transaction.Transaction;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private boolean isAutoCommit;

    private Transaction transaction;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.isAutoCommit=false;
    }

    public DefaultSqlSession(Configuration configuration,boolean isAutoCommit) {
        this.configuration = configuration;
        this.isAutoCommit=isAutoCommit;
    }

    public <E> List<E> selectList(String statementid, Object... params) throws Exception {

        //将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappersConfigCollection().getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if(objects!=null&&objects.size()>0){
            return (T) objects.get(0);
        }
        return null;
    }

    @Override
    public int insert(String statementid, Object... params) throws Exception {
        return this.excuteUpdate(statementid,params);
    }

    @Override
    public int update(String statementid, Object... params) throws Exception {
        return this.excuteUpdate(statementid,params);
    }

    @Override
    public int delete(String statementid, Object... params) throws Exception {
        return this.excuteUpdate(statementid,params);
    }

    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                SqlCommandType sqlCommandType = getSqlCommandType(getStatementId(method));
                if(SqlCommandType.SELECT==sqlCommandType){
                    return excuteQuery(method,args);
                }else {
                    return  excuteUpdate(getStatementId(method),args);
                }
            }
        });
        return (T) proxyInstance;
    }

    private int excuteUpdate(String statementId, Object[] args) throws Exception {
        SimpleExecutor simpleExecutor=null;
        if(!isAutoCommit){
            Connection connection = configuration.getDataSource().getConnection();
            connection.setAutoCommit(false);
            transaction=new JdbcTransaction(connection);
            simpleExecutor = new SimpleExecutor(isAutoCommit,transaction);
        }else {
            Connection connection = configuration.getDataSource().getConnection();
            transaction=new JdbcTransaction(connection);
            simpleExecutor=new SimpleExecutor(false,transaction);
        }
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        return  simpleExecutor.update(configuration,mappedStatement,args);
    }

    private Object excuteQuery(Method method, Object[] args) throws Exception {
        Type genericReturnType = method.getGenericReturnType();
        // 判断是否进行了 泛型类型参数化
        if(genericReturnType instanceof ParameterizedType){
            List<Object> objects = selectList(getStatementId(method), args);
            return objects;
        }
        return selectOne(getStatementId(method),args);
    }

    public String getStatementId(Method method) {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        return  className+"."+methodName;
    }

    @Override
    public void commit() {
        if(transaction!=null&&!isAutoCommit){
            try {
                transaction.commit();
            } catch (SQLException e) {
                ExceptionBuilder.buildOrmException("事务提交异常！",e);
            }
        }
    }

    private SqlCommandType getSqlCommandType(String statementId){
        return this.configuration.getMappedStatement(statementId).getSqlCommandType();
    };
}
