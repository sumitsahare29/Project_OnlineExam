package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.example.demo.entity")
public class SpringOnlineExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOnlineExamApplication.class, args);
	}

}
