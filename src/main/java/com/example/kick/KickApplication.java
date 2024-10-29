package com.example.kick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KickApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickApplication.class, args);
	}

}
