package com.oe.parsers;

import com.oe.exception.ExceptionBuilder;
import com.oe.mappers.MappedStatement;
import com.oe.mappers.MapperConfig;
import com.oe.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;

public class MapperConfigParser implements Parser<MapperConfig> {

    private MapperConfig mapperConfig;

    private final String[] sqlTagArr=new String[]{"select","delete","update","insert"};

    public MapperConfigParser() {
        this.mapperConfig = new MapperConfig();
    }

    public MapperConfig parse(Element mapperConfigElement) {
        Attribute namespaceTag = mapperConfigElement.attribute("namespace");
        String namespace = namespaceTag.getValue();
        if(!StringUtil.hasText(namespace)){
            ExceptionBuilder.buildOrmException("mapper文件的namespace不能为空！");
        }
        mapperConfig.setNamespace(namespace);

        for (int i = 0; i < sqlTagArr.length; i++) {
            String sqlTag = sqlTagArr[i];
            List<Element> sqlEleList = mapperConfigElement.elements(sqlTag);
            for (Element sqlEle : sqlEleList) {
                MappedStatementParser mappedStatementParser = new MappedStatementParser();
                MappedStatement mappedStatement = mappedStatementParser.parse(sqlEle);
                mapperConfig.getMappedStatementList().add(mappedStatement);
            }
        }

        return mapperConfig;
    }
}
