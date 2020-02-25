package com.oe.config;

import com.oe.mappers.MappedStatement;
import com.oe.mappers.MappersConfigCollection;

import javax.sql.DataSource;

public class Configuration {

    DataSource dataSource;

    MappersConfigCollection mappersConfigCollection;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappersConfigCollection getMappersConfigCollection() {
        return mappersConfigCollection;
    }

    public void setMappersConfigCollection(MappersConfigCollection mappersConfigCollection) {
        this.mappersConfigCollection = mappersConfigCollection;
    }

    public MappedStatement getMappedStatement(String statementId){
        return  this.mappersConfigCollection.getMappedStatementMap().get(statementId);
    }
}
