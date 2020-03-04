package com.connectedcities.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.connectedcities")
public class ConnectedCitiesApp {

	public static void main(String[] args) {
		SpringApplication.run(ConnectedCitiesApp.class, args);
	}

}
