package com.example.CRUDABS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
public class CrudAbsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CrudAbsApplication.class);
		app.setWebApplicationType(SERVLET);
		app.run(args);

	}

}
