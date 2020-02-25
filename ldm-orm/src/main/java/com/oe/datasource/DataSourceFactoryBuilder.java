package com.oe.datasource;

import com.oe.utils.ReflectUtil;

public class DataSourceFactoryBuilder {

    public  DataSourceFactory build(DataSourceConfig dataSourceConfig){
        DataSourceFactory dataSourceFactory = (DataSourceFactory)ReflectUtil.getInstanceByClassName(dataSourceConfig.getDataSourceFactoryClassName());
        dataSourceFactory.setProperties(dataSourceConfig.getProperties());
        return dataSourceFactory;
    }

}
