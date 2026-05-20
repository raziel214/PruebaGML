package com.quimbaya.clientes_api.cliente.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteSharedKeyNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FindClienteBySharedKeyQuery;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteQueryRepository;
import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;

@Component
public class FindClienteBySharedKeyQueryHandler implements QueryHandler<FindClienteBySharedKeyQuery, Cliente> {

    private final ClienteQueryRepository repository;

    public FindClienteBySharedKeyQueryHandler(ClienteQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente handle(FindClienteBySharedKeyQuery query) {
        return repository.findBySharedKey(query.sharedKey())
                .orElseThrow(() -> new ClienteSharedKeyNotFoundException(query.sharedKey()));
    }
}
