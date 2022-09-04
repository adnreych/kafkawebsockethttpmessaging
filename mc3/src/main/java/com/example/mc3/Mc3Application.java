 package com.example.mc3;

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.autoconfigure.domain.EntityScan;
 import org.springframework.context.annotation.ComponentScan;
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.mc3.config", "com.example.mc3.service"})
@EnableJpaRepositories(
		basePackages = "com.example.mc3.repository"
)
@EntityScan(basePackages = "com.example.mc3.model")
public class Mc3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc3Application.class, args);
	}



}
