package com.oe.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.oe.exception.ExceptionBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

public class DefaultDataSourceFactory implements DataSourceFactory{

    private Properties properties;

    @Override
    public DataSource buildDataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        } catch (PropertyVetoException e) {
            ExceptionBuilder.buildOrmException("数据库驱动加载异常！",e);
        }
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        return comboPooledDataSource;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties=properties;
    }
}
