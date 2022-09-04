package com.example.mc2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

import java.io.IOException;

@SpringBootApplication
@ComponentScan({"com.example.mc2.controller", "com.example.mc2.config", "com.example.mc2.service"})
@EnableJpaRepositories(
		basePackages = "com.example.mc2.repository"
)
@EntityScan(basePackages = "com.example.mc2.model")
public class Mc2Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc2Application.class, args);
	}


}
