package com.quimbaya.clientes_api.cliente.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quimbaya.clientes_api.cliente.aplication.service.ClienteService;
import com.quimbaya.clientes_api.cliente.aplication.usescase.CreateClienteUseCaseImpl;
import com.quimbaya.clientes_api.cliente.aplication.usescase.DeleteClienteUseCaseImpl;
import com.quimbaya.clientes_api.cliente.aplication.usescase.FetchAllClienteUseCaseImpl;
import com.quimbaya.clientes_api.cliente.aplication.usescase.FindClienteByIdUseCaseImpl;
import com.quimbaya.clientes_api.cliente.aplication.usescase.FindClienteBySharedKeyUseCaseImpl;
import com.quimbaya.clientes_api.cliente.aplication.usescase.UpdateClienteUseCaseImpl;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;
import com.quimbaya.clientes_api.cliente.infraestructure.persistence.repository.JpaClienteRepositoryAdapter;

@Configuration
public class ClienteConfig {

	@Bean
	public ClienteService clienteService(ClienteRepositoryPort clienteRepositoryPort) 
	{	
		return new ClienteService(
				new CreateClienteUseCaseImpl(clienteRepositoryPort)	,
				new DeleteClienteUseCaseImpl(clienteRepositoryPort),
				new FetchAllClienteUseCaseImpl(clienteRepositoryPort),
				new FindClienteByIdUseCaseImpl(clienteRepositoryPort),
				new FindClienteBySharedKeyUseCaseImpl(clienteRepositoryPort),			
				new UpdateClienteUseCaseImpl(clienteRepositoryPort)						
				);
	}
	
	@Bean
	public ClienteRepositoryPort clienteRepositoryPort(JpaClienteRepositoryAdapter jpaClienteRepositoryAdapter) {
		
		return jpaClienteRepositoryAdapter;
	}
}
