package com.oe.pojo;

import com.oe.MyDataSource;
import com.oe.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class MyDataSourceFactory implements DataSourceFactory {

    Properties properties;

    public DataSource buildDataSource() {
        System.out.println("获取连接池！");
        return new MyDataSource();
    }

    public void setProperties(Properties properties) {
        properties=properties;
        System.out.println(properties);
    }

}
