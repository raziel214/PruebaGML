package com.quimbaya.clientes_api.cliente.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FindClienteByIdQuery;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteQueryRepository;
import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;

@Component
public class FindClienteByIdQueryHandler implements QueryHandler<FindClienteByIdQuery, Cliente> {

    private final ClienteQueryRepository repository;

    public FindClienteByIdQueryHandler(ClienteQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente handle(FindClienteByIdQuery query) {
        return repository.findById(query.id())
                .orElseThrow(() -> new ClienteNotFoundException(query.id()));
    }
}
