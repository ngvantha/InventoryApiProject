package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
//Disable some Spring Boot auto config
@EnableAutoConfiguration(exclude = { 						//
     DataSourceAutoConfiguration.class, 					//
     DataSourceTransactionManagerAutoConfiguration.class, 	//
     HibernateJpaAutoConfiguration.class })					//
public class InventoryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryProjectApplication.class, args);
	}

}