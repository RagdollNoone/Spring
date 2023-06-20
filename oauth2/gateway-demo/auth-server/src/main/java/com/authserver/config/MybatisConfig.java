package com.authserver.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.authserver.mapper")
public class MybatisConfig {
//    @ConfigurationProperties("spring.datasource")
//    @Bean
//    public DataSource dataSource() {
//        return new HikariDataSource();
//    }
}
