package com.example.CalculadoraDePrimos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CalculadoraDeNumerosPrimosApplication {

	public static void main(String[] args) {

		SpringApplication.run(CalculadoraDeNumerosPrimosApplication.class, args);

	}

}
