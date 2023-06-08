package com.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.mapper.read", sqlSessionFactoryRef = "readSqlSessionFactory")
public class ReadMyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read-datasource")
    public DataSource readDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory readSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(readDataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager readTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(readDataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate readTransactionTemplate(){
        return new TransactionTemplate(readTransactionManager());
    }
}
