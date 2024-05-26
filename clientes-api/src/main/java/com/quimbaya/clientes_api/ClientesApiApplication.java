package com.quimbaya.clientes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.quimbaya.clientes_api")
@ComponentScan(basePackages = "com.quimbaya.clientes_api")
@EnableJpaRepositories(basePackages = {"com.quimbaya.clientes_api"})
@EntityScan(basePackages = {"com.quimbaya.clientes_api"})
public class ClientesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApiApplication.class, args);
	}

}
