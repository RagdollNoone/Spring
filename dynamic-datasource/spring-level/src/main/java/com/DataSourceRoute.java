package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Primary
@Component
public class DataSourceRoute extends AbstractRoutingDataSource {

    private static ThreadLocal<String> name = new ThreadLocal<>();

    @Autowired
    private DataSource readDataSource;

    @Autowired
    private DataSource writeDataSource;

    public static void setDataSource(String dataSourceName) {
        name.set(dataSourceName);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("R", readDataSource);
        targetDataSource.put("W", writeDataSource);

        super.setTargetDataSources(targetDataSource);
        super.setDefaultTargetDataSource(writeDataSource);

        super.afterPropertiesSet();
    }
}
