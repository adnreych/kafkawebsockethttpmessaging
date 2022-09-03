 package com.example.mc3;

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
@ComponentScan({"com.example.mc3.controller", "com.example.mc3.config", "com.example.mc3.service"})
@EnableJpaRepositories(
		basePackages = "com.example.mc3.repository"
)
@EntityScan(basePackages = "com.example.mc3.model")
@EnableKafka
@PropertySource({"classpath:kafka.properties"})
public class Mc3Application {

	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config","classpath:jaas.conf");
		SpringApplication.run(Mc3Application.class, args);
	}

	@Bean
	public JsonDeserializer jsonDeserializer() {
		return new JsonDeserializer() {
			@Override
			public Object deserialize(JsonParser p, DeserializationContext context) throws IOException {
				return null;
			}
		};
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = JsonMapper.builder()
				.addModule(new JavaTimeModule())
				.build();
		return mapper;
	}

}
