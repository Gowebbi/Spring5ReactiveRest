package com.zohaib.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.zohaib.config.FileStorageProperties;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.zohaib.config",
        "com.zohaib.service",
        "com.zohaib.controllers"
    }
)
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class ScanDocumentApplication {

	
	public static void main(final String[] args) {
		
		SpringApplication.run(ScanDocumentApplication.class, args);
	}
	
}
