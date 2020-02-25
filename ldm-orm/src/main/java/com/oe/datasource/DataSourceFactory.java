package com.oe.datasource;

import javax.sql.DataSource;
import java.util.Properties;

public interface DataSourceFactory {

   DataSource buildDataSource();

    void setProperties(Properties properties);


}
