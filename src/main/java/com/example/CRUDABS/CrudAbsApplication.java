package com.example.CRUDABS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
@EnableSwagger2
public class CrudAbsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CrudAbsApplication.class);
		app.setWebApplicationType(SERVLET);
		app.run(args);

	}

}
