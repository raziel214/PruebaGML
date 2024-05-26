package com.quimbaya.clientes_api.cliente.aplication.usescase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.FetchAllClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Service
public class FetchAllClienteUseCaseImpl implements FetchAllClienteUseCase {

	private final ClienteRepositoryPort clienteRepositoryPort;
	@Override
	public List<Cliente> fetchAll() {
		// TODO Auto-generated method stub
		return clienteRepositoryPort.findAll();
	}
	
	
	
}
