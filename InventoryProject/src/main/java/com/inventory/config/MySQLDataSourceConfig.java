package com.inventory.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {
		Constants.PACKAGE_DB1_REPOSITORIES }, entityManagerFactoryRef = Constants.ENTITY_MANAGER_FACTORY_1, transactionManagerRef = Constants.TRANSACTION_MANAGER_1)
@EnableTransactionManagement
public class MySQLDataSourceConfig {
	@Primary
	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.db1.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = Constants.ENTITY_MANAGER_FACTORY_1)
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mysqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
					  //.properties(hibernateProperties())
					  .packages(Constants.PACKAGE_DB1_ENTITIES)
					  .persistenceUnit(Constants.JPA_CONNET_DB1_UNIT_NAME)
					  .build();
	}

	@Primary
	@Bean(name = Constants.TRANSACTION_MANAGER_1)
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier(Constants.ENTITY_MANAGER_FACTORY_1) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
//	private Map<String, Object> hibernateProperties() {
//		 
//		Resource resource = new ClassPathResource("hibernate.properties");
//		
//		try {
//			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
//			return properties.entrySet().stream()
//											.collect(Collectors.toMap(
//														e -> e.getKey().toString(),
//														e -> e.getValue())
//													);
//		} catch (IOException e) {
//			return new HashMap<String, Object>();
//		}
//	}
	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
}
