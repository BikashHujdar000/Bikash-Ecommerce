package com.example.biki.ecom.ecommerce.bikash;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class EcommerceBikashApiApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBikashApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Database is Configured");

	}

	@Bean
	public ModelMapper modelMapper()
	{
		return  new ModelMapper();
	}


	@Bean
	public RestClient restClient() {
		return RestClient.builder()
				.baseUrl("https://a.khalti.com/api/v2")  // Base URL Khalti
				.build();
	}


}
