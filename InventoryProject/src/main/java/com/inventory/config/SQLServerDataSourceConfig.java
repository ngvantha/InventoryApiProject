package com.inventory.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {Constants.PACKAGE_DB2_REPOSITORIES}, entityManagerFactoryRef = Constants.ENTITY_MANAGER_FACTORY_2, transactionManagerRef = Constants.TRANSACTION_MANAGER_2)
public class SQLServerDataSourceConfig {
    @Bean(name = "sqlServerDataSource")
    @ConfigurationProperties(prefix = "spring.db2.datasource")
    public DataSource sqlServerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = Constants.ENTITY_MANAGER_FACTORY_2)
    public LocalContainerEntityManagerFactoryBean sqlServerEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("sqlServerDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Constants.PACKAGE_DB2_REPOSITORIES)
                .persistenceUnit(Constants.JPA_CONNET_DB2_UNIT_NAME)
                .build();
    }

    @Bean(name = Constants.TRANSACTION_MANAGER_2)
    public PlatformTransactionManager sqlServerTransactionManager(@Qualifier(Constants.ENTITY_MANAGER_FACTORY_2) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
