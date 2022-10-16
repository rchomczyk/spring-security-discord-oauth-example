package com.rchomczyk.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableJdbcHttpSession
@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] arguments) {
		SpringApplication.run(ExampleApplication.class, arguments);
	}
}
