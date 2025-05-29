package com.pablo.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Empacotamento de Pedidos", version = "1.0", description = "Microserviço para empacotar produtos em caixas"))
public class LojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}
