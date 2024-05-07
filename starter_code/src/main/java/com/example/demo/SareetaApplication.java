package com.example.demo;

import org.apache.logging.log4j.LogManager;

import com.example.demo.Logger.Logger2;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories("com.example.demo.model.persistence.repositories")
@EntityScan("com.example.demo.model.persistence")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SareetaApplication {

	private static final Logger mylogger = LogManager.getLogger(SareetaApplication.class);

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		mylogger.info("*** E-commerce Application started...");
		SpringApplication.run(SareetaApplication.class, args);
		mylogger.trace("greeting user!");
		mylogger.debug("Welcome to the E-commerce Application!");
	}

	@Bean
	public Logger2 getLogs(){
		return new Logger2();
	}

}
