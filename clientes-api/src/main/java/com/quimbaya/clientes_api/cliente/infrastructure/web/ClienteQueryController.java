package com.quimbaya.clientes_api.cliente.infrastructure.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FetchAllClientesQuery;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FindClienteByIdQuery;
import com.quimbaya.clientes_api.cliente.domain.port.in.query.FindClienteBySharedKeyQuery;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.ClienteResponse;
import com.quimbaya.clientes_api.cliente.infrastructure.web.mapper.ClienteWebMapper;
import com.quimbaya.clientes_api.shared.cqrs.QueryBus;

@RestController
@RequestMapping("/api/cliente")
public class ClienteQueryController {

    private final QueryBus queryBus;
    private final ClienteWebMapper mapper;

    public ClienteQueryController(QueryBus queryBus, ClienteWebMapper mapper) {
        this.queryBus = queryBus;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> fetchAll() {
        List<Cliente> clientes = queryBus.dispatch(new FetchAllClientesQuery());
        return ResponseEntity.ok(clientes.stream().map(mapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id) {
        Cliente cliente = queryBus.dispatch(new FindClienteByIdQuery(id));
        return ResponseEntity.ok(mapper.toResponse(cliente));
    }

    @GetMapping("/by-shared-key/{sharedKey}")
    public ResponseEntity<ClienteResponse> findBySharedKey(@PathVariable String sharedKey) {
        Cliente cliente = queryBus.dispatch(new FindClienteBySharedKeyQuery(sharedKey));
        return ResponseEntity.ok(mapper.toResponse(cliente));
    }
}
