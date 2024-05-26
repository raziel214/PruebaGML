package com.quimbaya.clientes_api.cliente.aplication.usescase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.UpdateClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class UpdateClienteUseCaseImpl implements UpdateClienteUseCase {
	
	private final ClienteRepositoryPort clienteRepositoryPort;

	@Override
	public Cliente updateCliente(Long id, Cliente cliente) {
		
		return clienteRepositoryPort.findById(id)
				.map(existingCliente->{
					existingCliente.setNombre(cliente.getNombre());
					existingCliente.setApellido(cliente.getApellido());
					existingCliente.setCorreo(cliente.getCorreo());
					existingCliente.setTelefono(cliente.getTelefono());
					existingCliente.setSharedKey(cliente.getSharedKey());
					
					return clienteRepositoryPort.save(existingCliente);
				}).orElseThrow(()-> new ClienteNotFoundException(id));
	}
}
