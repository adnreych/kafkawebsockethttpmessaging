package com.example.mc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.example.mc1.controller", "com.example.mc1.service"})
@EnableJpaRepositories(
		basePackages = "com.example.mc1.repository"
)
@EntityScan(basePackages = "com.example.mc1.model")
public class Mc1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc1Application.class, args);
	}

}
