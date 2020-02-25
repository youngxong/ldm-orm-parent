package com.oe.parsers;

import com.oe.datasource.DataSourceConfig;
import com.oe.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public class DataSourceParser implements Parser<DataSourceConfig>{

    private final String TYPE_DRFAULT="DEFAULT";
    private final String DRFAULT_DATASOURCE_FACTORY="com.oe.datasource.DefaultDataSourceFactory";

    private DataSourceConfig config;

    public DataSourceParser() {
        this.config = new DataSourceConfig();
    }

    public DataSourceConfig parse(Element dataSourceEle){
        Attribute type = dataSourceEle.attribute("type");
        if(type==null|| StringUtil.compareIgnoreCase(type.getValue(),TYPE_DRFAULT)){
            config.setDataSourceFactoryClassName(DRFAULT_DATASOURCE_FACTORY);
        }else {
            config.setDataSourceFactoryClassName(type.getValue());
        }
        List<Element> elements = dataSourceEle.elements();
        ListIterator<Element> iter = elements.listIterator();
        Properties properties = new Properties();
        while (iter.hasNext()){
            Element propertyTag = iter.next();
            Attribute name = propertyTag.attribute("name");
            Attribute value = propertyTag.attribute("value");
            properties.setProperty(name.getValue(),value.getValue());
        }
        config.setProperties(properties);
        return config;


    }
}
