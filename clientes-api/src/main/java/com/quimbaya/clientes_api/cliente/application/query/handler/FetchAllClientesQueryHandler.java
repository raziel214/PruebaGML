package com.quimbaya.clientes_api.cliente.application.query.handler;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FetchAllClientesQuery;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteQueryRepository;
import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;

@Component
public class FetchAllClientesQueryHandler implements QueryHandler<FetchAllClientesQuery, List<Cliente>> {

    private final ClienteQueryRepository repository;

    public FetchAllClientesQueryHandler(ClienteQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> handle(FetchAllClientesQuery query) {
        return repository.findAll();
    }
}
