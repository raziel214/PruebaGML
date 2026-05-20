package com.quimbaya.clientes_api.cliente.domain.port.in.query;

import java.util.List;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.shared.cqrs.Query;

public record FetchAllClientesQuery() implements Query<List<Cliente>> {
}
