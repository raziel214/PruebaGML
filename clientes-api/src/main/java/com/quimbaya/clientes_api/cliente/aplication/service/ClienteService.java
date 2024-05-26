package com.quimbaya.clientes_api.cliente.aplication.service;

import java.util.List;
import java.util.Optional;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.CreateClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.DeleteClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.FetchAllClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.FindClienteByIdUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.FindClienteBySharedKeyUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.in.UpdateClienteUseCase;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteService implements CreateClienteUseCase,DeleteClienteUseCase,FetchAllClienteUseCase,FindClienteByIdUseCase,FindClienteBySharedKeyUseCase,UpdateClienteUseCase {

	private final CreateClienteUseCase createClienteUseCase;
	private final DeleteClienteUseCase deleteClienteUseCase;
	private final FetchAllClienteUseCase fetchAllClienteUseCase;
	private final FindClienteByIdUseCase findClienteByIdUseCase;
	private final FindClienteBySharedKeyUseCase findClienteBySharedKeyUseCase;
	private final UpdateClienteUseCase updateClienteUseCase;
	
	@Override
	public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		return updateClienteUseCase.updateCliente(id, cliente);
	}

	@Override
	public Optional<Cliente> findBySharedKey(String sharedKey) {
		// TODO Auto-generated method stub
		return findClienteBySharedKeyUseCase.findBySharedKey(sharedKey);
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return findClienteByIdUseCase.findById(id);
	}

	@Override
	public List<Cliente> fetchAll() {
		// TODO Auto-generated method stub
		return fetchAllClienteUseCase.fetchAll();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return deleteClienteUseCase.deleteById(id);
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return createClienteUseCase.createCliente(cliente);
	}	
}
