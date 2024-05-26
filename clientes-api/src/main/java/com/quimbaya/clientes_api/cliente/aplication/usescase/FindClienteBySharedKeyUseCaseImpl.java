package com.quimbaya.clientes_api.cliente.aplication.usescase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.FindClienteBySharedKeyUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class FindClienteBySharedKeyUseCaseImpl implements FindClienteBySharedKeyUseCase{
	
	private final ClienteRepositoryPort clienteRepositoryPort;

	@Override
	public Optional<Cliente> findBySharedKey(String sharedKey) {
		// TODO Auto-generated method stub
		return clienteRepositoryPort.findByShareKey(sharedKey);
	}
	
	

}
