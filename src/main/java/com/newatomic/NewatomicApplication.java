package com.newatomic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewatomicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewatomicApplication.class, args);
	}

}
