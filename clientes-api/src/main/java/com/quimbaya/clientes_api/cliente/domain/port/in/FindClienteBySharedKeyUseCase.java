package com.quimbaya.clientes_api.cliente.domain.port.in;

import java.util.Optional;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface FindClienteBySharedKeyUseCase {

	Optional<Cliente> findBySharedKey(String sharedKey);
}
