package com.forezp.serviceweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWebApplication.class, args);
	}
}
