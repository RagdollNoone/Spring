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
@MapperScan(basePackages = "com.mapper.write", sqlSessionFactoryRef = "writeSqlSessionFactory")
public class WriteMyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write-datasource")
    public DataSource writeDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory writeSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(writeDataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager writeTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(writeDataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate writeTransactionTemplate(){
        return new TransactionTemplate(writeTransactionManager());
    }
}
