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
@MapperScan(basePackages = "com.lee.demo.mapper.cluster", sqlSessionTemplateRef  = "writeSqlSessionTemplate")
public class DataSourceClusterConfig {
	@Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    @Qualifier("writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "writeSqlSessionFactory")
    @Primary
    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "writeTransactionManager")
    @Primary
    public DataSourceTransactionManager writeTransactionManager(@Qualifier("writeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "writeSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("writeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}