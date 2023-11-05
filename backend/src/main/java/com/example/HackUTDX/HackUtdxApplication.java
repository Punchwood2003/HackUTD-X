package com.example.HackUTDX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan
public class HackUtdxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackUtdxApplication.class, args);
	}

	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
}
