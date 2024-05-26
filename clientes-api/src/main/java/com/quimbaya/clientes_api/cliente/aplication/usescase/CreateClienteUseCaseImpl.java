package com.quimbaya.clientes_api.cliente.aplication.usescase;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.CreateClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Service
public class CreateClienteUseCaseImpl implements CreateClienteUseCase{

	
	private final ClienteRepositoryPort clienteRepositoryPort;
	@Override
	public Cliente createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepositoryPort.save(cliente);
	}
	
}
