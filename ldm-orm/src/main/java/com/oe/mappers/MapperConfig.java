package com.oe.mappers;

import com.oe.mappers.MappedStatement;

import java.util.ArrayList;
import java.util.List;

public class MapperConfig {

    private String namespace;

    private List<MappedStatement> mappedStatementList=new ArrayList<MappedStatement>();

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<MappedStatement> getMappedStatementList() {
        return mappedStatementList;
    }

    public void setMappedStatementList(List<MappedStatement> mappedStatementList) {
        this.mappedStatementList = mappedStatementList;
    }
}
