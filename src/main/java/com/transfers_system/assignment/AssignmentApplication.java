package com.transfers_system.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.transfers_system.assignment.repository")
//@EntityScan(basePackages = "com.transfers_system.assignment.entities.database.tables")
public class AssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
