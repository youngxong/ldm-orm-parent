package com.oe.config;

import com.oe.datasource.DataSourceFactory;
import com.oe.datasource.DataSourceFactoryBuilder;
import com.oe.exception.ExceptionBuilder;
import com.oe.mappers.MappersConfigCollection;
import com.oe.mappers.MappersConfigCollectionBuilder;
import com.oe.parsers.DataSourceParser;
import com.oe.parsers.MappersParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class ConfigurationBuilder {

    public static final Logger logger = LoggerFactory.getLogger(ConfigurationBuilder.class);

    private final String ROOT_ELEMENT_NAME="configuration";

    Configuration configuration;

    public ConfigurationBuilder() {
        this.configuration = new Configuration();
    }

    public  Configuration parseConfig(InputStream inputStream) {
        logger.info("开始解析配置....");
        /**
         * 解析配置文件
         */
        Document document = null;
        try {
            document = new SAXReader().read(inputStream);
        } catch (DocumentException e) {
            ExceptionBuilder.buildOrmException("configuration的配置解析异常！",e);
        }
        Element root = document.getRootElement();
        if(root==null||!root.getName().equals(ROOT_ELEMENT_NAME)){
            ExceptionBuilder.buildOrmException("ldm-orm的config.xml的根元素必须是："+ROOT_ELEMENT_NAME);
        }
        /**
         *解析连接池
         */
        Element dataSourceTag = root.element("dataSource");
        DataSourceParser dataSourceParser = new DataSourceParser();
        DataSourceFactoryBuilder dataSourceFactoryBuilder = new DataSourceFactoryBuilder();
        DataSourceFactory dataSourceFactory = dataSourceFactoryBuilder.build(dataSourceParser.parse(dataSourceTag));
        configuration.setDataSource(dataSourceFactory.buildDataSource());


        /**
         * 解析mapper文件
         */
        Element mappersTag = root.element("mappers");
        MappersParser mappersParser = new MappersParser();
        MappersConfigCollectionBuilder mappersConfigCollectionBuilder = new MappersConfigCollectionBuilder();
        MappersConfigCollection mappersConfigCollection = mappersConfigCollectionBuilder.build(mappersParser.parse(mappersTag));
        configuration.setMappersConfigCollection(mappersConfigCollection);
        logger.info("结束解析配置....");
        return configuration;
    }
}
