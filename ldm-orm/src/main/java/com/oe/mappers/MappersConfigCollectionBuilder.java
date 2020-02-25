package com.oe.mappers;

import com.oe.parsers.MapperConfigParser;

import javax.xml.bind.Element;
import java.util.List;

public class MappersConfigCollectionBuilder {

    private  MappersConfigCollection mappersConfigCollection;

    public MappersConfigCollectionBuilder() {
        this.mappersConfigCollection = new MappersConfigCollection();
    }

    public MappersConfigCollection build(List<MapperPath> mapperPathList){
        for (MapperPath mapperPath : mapperPathList) {
            MapperConfigBuilder mapperConfigBuilder = new MapperConfigBuilder();
            MapperConfig mapperConfig = mapperConfigBuilder.build(mapperPath);
            mappersConfigCollection.getMapperConfigList().add(mapperConfig);
        }
        mappersConfigCollection.initStatementMap();
        return mappersConfigCollection;
    }
}
