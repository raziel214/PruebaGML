package com.quimbaya.clientes_api.cliente.domain.port.in.query;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.shared.cqrs.Query;

public record FindClienteByIdQuery(Long id) implements Query<Cliente> {
}
