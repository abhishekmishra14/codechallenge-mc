package com.codechallenge.mc.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.codechallenge.mc")
public class ConnectMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectMainApplication.class, args);
	}

}
