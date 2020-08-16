package com.lrh.dict.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@Configuration
@RefreshScope
public class DataSourceConfig {

//    spring.datasource.hikari.jdbc-url=jdbc:mysql://192.168.3.9:3306/lrh?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true&useSSL=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
//    spring.datasource.hikari.username=root
//    spring.datasource.hikari.password=root
//    spring.datasource.hikari.minimum-idle=5
//    spring.datasource.hikari.maximum-pool-size=100
//    spring.datasource.hikari.connection-timeout=30000
//    spring.datasource.hikari.idle-timeout=600000
//    spring.datasource.hikari.max-lifetime=1800000
    @Value("${spring.datasource.hikari.jdbc-url}")
    private String jdbcUrl;
    @Value("${spring.datasource.hikari.username}")
    private String username;
    @Value("${spring.datasource.hikari.password}")
    private String password;
    @Value("${spring.datasource.hikari..minimum-idle}")
    private int minimumIdle;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxlifetime;
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @RefreshScope
    public DataSource dataSource() {
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxlifetime);
        return dataSource;
    }
}
