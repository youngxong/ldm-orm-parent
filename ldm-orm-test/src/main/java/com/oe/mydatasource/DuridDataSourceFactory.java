package com.oe.mydatasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.oe.datasource.DataSourceFactory;
import com.oe.exception.ExceptionBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

public class DuridDataSourceFactory implements DataSourceFactory {

    Properties properties;

    public DataSource buildDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getProperty("driverClass"));
        druidDataSource.setUrl(properties.getProperty("jdbcUrl"));
        druidDataSource.setUsername(properties.getProperty("username"));
        druidDataSource.setPassword(properties.getProperty("password"));
        return druidDataSource;
    }

    public void setProperties(Properties properties) {
         this.properties=properties;
    }
}
