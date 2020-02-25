package com.oe.datasource;

import java.util.Properties;

public class DataSourceConfig {

    private String  dataSourceFactoryClassName;

    Properties properties;

    public String getDataSourceFactoryClassName() {
        return dataSourceFactoryClassName;
    }

    public void setDataSourceFactoryClassName(String dataSourceFactoryClassName) {
        this.dataSourceFactoryClassName = dataSourceFactoryClassName;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
