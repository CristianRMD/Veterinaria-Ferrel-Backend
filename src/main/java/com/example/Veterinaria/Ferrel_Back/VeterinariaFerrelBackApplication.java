package com.example.Veterinaria.Ferrel_Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.example.Veterinaria.Ferrel_Back")
@EnableScheduling
public class VeterinariaFerrelBackApplication {

	public static void main(String[] args) {

		SpringApplication.run(VeterinariaFerrelBackApplication.class, args);
	}

}
