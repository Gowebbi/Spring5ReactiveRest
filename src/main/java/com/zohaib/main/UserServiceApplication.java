package com.zohaib.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {
		"com.zohaib.http.request.handlers",
		"com.zohaib.service",
		"com.zohaib.config"
})
public class UserServiceApplication {

	
	public static void main(final String[] args) {
		
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
}
