package com.example.mc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.mc2.controller", "com.example.mc2.config"})
@EnableJpaRepositories(
		basePackages = "com.example.mc2.repository"
)
@EntityScan(basePackages = "com.example.mc2.model")
public class Mc2Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc2Application.class, args);
	}

}
