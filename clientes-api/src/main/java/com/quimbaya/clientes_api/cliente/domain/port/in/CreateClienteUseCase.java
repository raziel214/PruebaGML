package com.quimbaya.clientes_api.cliente.domain.port.in;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface CreateClienteUseCase {

	Cliente createCliente(Cliente cliente);
}
