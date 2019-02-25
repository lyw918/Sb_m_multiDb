package com.lee.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = "com.lee.demo.mapper.master", sqlSessionTemplateRef  = "readSqlSessionTemplate")
public class DataSourceMasterConfig {
@Bean(name = "readDataSource")
@ConfigurationProperties(prefix = "spring.datasource.druid.read")
@Qualifier("readDataSource")
public DataSource readDataSource() {
    return DruidDataSourceBuilder.create().build();
}

@Bean(name = "readSqlSessionFactory")

public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource((javax.sql.DataSource) dataSource);
    return bean.getObject();
}

@Bean(name = "readTransactionManager")

public DataSourceTransactionManager readTransactionManager(@Qualifier("readDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
}

@Bean(name = "readSqlSessionTemplate")

public SqlSessionTemplate readSqlSessionTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
}
}