package com.lrh.dict.config;

import com.lrh.mybatis.core.pagehelper.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
@Configuration
@MapperScan(basePackages = {"com.lrh.dict.mapper"})
public class MybatisConfig {
    private static final String MAPPER_LOCATION="classpath*:mapping/*.xml";
    //注册插件
    @Bean
    public PaginationInterceptor myPagePlugin() {
        PaginationInterceptor myPlugin = new PaginationInterceptor();
        //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
        Properties properties = new Properties();
        properties.setProperty("dialect", "MYSQL");
        myPlugin.setProperties(properties);
        return myPlugin;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        bean.setPlugins(new Interceptor[]{myPagePlugin()});
        return bean.getObject();
    }
}
