package com.oe.mappers;

import com.oe.mappers.MapperConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappersConfigCollection {

    List<MapperConfig> mapperConfigList=new ArrayList<MapperConfig>();

    Map<String,MappedStatement> mappedStatementMap=new HashMap<String, MappedStatement>();

    public List<MapperConfig> getMapperConfigList() {
        return mapperConfigList;
    }

    public void initStatementMap(){
        if(mapperConfigList.size()>0){
            for (MapperConfig mapperConfig : mapperConfigList) {
                String namespace = mapperConfig.getNamespace();
                List<MappedStatement> mappedStatementList = mapperConfig.getMappedStatementList();
                for (MappedStatement mappedStatement : mappedStatementList) {
                    String mappedStatementId=namespace+"."+mappedStatement.getId();
                    mappedStatementMap.put(mappedStatementId,mappedStatement);
                }
            }


        }
    };

    public void setMapperConfigList(List<MapperConfig> mapperConfigList) {

        this.mapperConfigList = mapperConfigList;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
