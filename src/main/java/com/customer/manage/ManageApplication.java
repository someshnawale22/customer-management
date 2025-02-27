package com.customer.manage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * ManageApplication class serves as the entry point for the Spring Boot application,
 * which provides APIs for managing customers.
 * Author: Somesh Nawale
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer API", version = "1.0", description = "API for managing customers"))
public class ManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
		System.out.println("Welcome to Customer Management System application....!");
	}

}
