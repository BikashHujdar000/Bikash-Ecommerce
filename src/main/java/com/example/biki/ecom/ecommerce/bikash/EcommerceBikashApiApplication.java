package com.example.biki.ecom.ecommerce.bikash;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceBikashApiApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBikashApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Database is Configured");

	}
}
