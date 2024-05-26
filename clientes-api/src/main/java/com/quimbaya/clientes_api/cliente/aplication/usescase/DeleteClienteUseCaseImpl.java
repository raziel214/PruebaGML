package com.quimbaya.clientes_api.cliente.aplication.usescase;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.cliente.domain.port.in.DeleteClienteUseCase;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class DeleteClienteUseCaseImpl implements DeleteClienteUseCase {

	private final ClienteRepositoryPort clienteRepositoryPort;

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepositoryPort.deleteById(id);
	}
}
