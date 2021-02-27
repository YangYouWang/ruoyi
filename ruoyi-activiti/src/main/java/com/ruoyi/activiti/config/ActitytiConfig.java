package com.ruoyi.activiti.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author yangyouwang
 * @title: ActitytiConfig
 * @projectName ruoyi
 * @description: ActitytiConfig
 * @date 2020/7/24上午11:10
 */
@Configuration
public class ActitytiConfig extends AbstractProcessEngineAutoConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier("dynamicDataSource") DataSource dataSource) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setJobExecutorActivate(true);
        configuration.setTransactionManager(transactionManager(dataSource));
        return configuration;
    }
}
