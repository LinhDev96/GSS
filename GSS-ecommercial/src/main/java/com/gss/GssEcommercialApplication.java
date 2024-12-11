package com.gss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gss")
public class GssEcommercialApplication {

	public static void main(String[] args) {
		SpringApplication.run(GssEcommercialApplication.class, args);
	}

}
