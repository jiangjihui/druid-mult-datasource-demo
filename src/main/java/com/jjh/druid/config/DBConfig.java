package com.jjh.druid.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 配置JPA的Hibernate实现
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary" )
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
//        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary" )
    public DataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();     //使用DruidDataSourceBuilder需关闭druid配置的sql防火墙，否则查询语句含有uid会抛出异常
//        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalSessionFactoryBean sessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jjh.druid.models");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.naming.implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");        //隐式命名策略，使用此属性当我们使用的表或列没有明确指定一个使用的名称 。
        properties.put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");     //物理命名策略，用于转换“逻辑名称”(隐式或显式)的表或列成一个物理名称。
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean(name = "secondarySessionFactory")
    public LocalSessionFactoryBean secondarySessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jjh.druid.othermodels");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.naming.implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");        //隐式命名策略，使用此属性当我们使用的表或列没有明确指定一个使用的名称 。
        properties.put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");     //物理命名策略，用于转换“逻辑名称”(隐式或显式)的表或列成一个物理名称。
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    /*spring-boot 2.0 配置 问题*/
    /*@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new          LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "com.jjh.druid.models" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }
*/
}
