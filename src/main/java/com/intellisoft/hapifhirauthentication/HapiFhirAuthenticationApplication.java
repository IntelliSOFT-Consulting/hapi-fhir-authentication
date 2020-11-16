package com.intellisoft.hapifhirauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HapiFhirAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HapiFhirAuthenticationApplication.class, args);
	}

}
