package com.aviation.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AviationComponentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AviationComponentApiApplication.class, args);
	}
}
