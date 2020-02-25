package com.oe.mappers;

import com.oe.exception.ExceptionBuilder;
import com.oe.parsers.MapperConfigParser;
import com.oe.parsers.MappersParser;
import com.oe.resource.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MapperConfigBuilder {

    private final String ROOT_ELEMENT_NAME="mapper";

    public  MapperConfig build(MapperPath mapperPath){
        /**
         * 解析配置文件
         */
        Document document = null;
        try {
            document = new SAXReader().read(Resources.getResourceAsSteam(mapperPath.getMapperPath()));
        } catch (DocumentException e) {
            ExceptionBuilder.buildOrmException("mapper的配置解析异常！",e);
        }
        Element root = document.getRootElement();
        if(root==null||!root.getName().equals(ROOT_ELEMENT_NAME)){
            ExceptionBuilder.buildOrmException("ldm-orm的config.xml的根元素必须是："+ROOT_ELEMENT_NAME);
        }
        MapperConfigParser mappersParser = new MapperConfigParser();
        return mappersParser.parse(root);
    };
}
