package com.quimbaya.clientes_api.cliente.domain.port.in;

import java.util.List;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface FetchAllClienteUseCase {

	List<Cliente> fetchAll();
}
