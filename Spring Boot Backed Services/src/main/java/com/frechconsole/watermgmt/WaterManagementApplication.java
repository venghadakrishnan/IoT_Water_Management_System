package com.frechconsole.watermgmt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.frechconsole.watermgmt.repository")
@EntityScan(basePackages = "com.frechconsole.watermgmt.entity")
public class WaterManagementApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WaterManagementApplication.class, args);
		
	}
	
}
	

