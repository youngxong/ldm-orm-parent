package com.oe.session;

import com.oe.config.Configuration;
import com.oe.config.ConfigurationBuilder;
import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in){
        // 第一：使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        ConfigurationBuilder xmlConfigBuilder = new ConfigurationBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);


        // 第二：创建sqlSessionFactory对象：工厂类：生产sqlSession:会话对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}
