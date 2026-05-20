package com.quimbaya.clientes_api.cliente.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.DeleteClienteCommand;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.ClienteResponse;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.CreateClienteRequest;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.UpdateClienteRequest;
import com.quimbaya.clientes_api.cliente.infrastructure.web.mapper.ClienteWebMapper;
import com.quimbaya.clientes_api.shared.cqrs.CommandBus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteCommandController {

    private final CommandBus commandBus;
    private final ClienteWebMapper mapper;

    public ClienteCommandController(CommandBus commandBus, ClienteWebMapper mapper) {
        this.commandBus = commandBus;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody CreateClienteRequest request) {
        Cliente created = commandBus.dispatch(mapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateClienteRequest request) {
        Cliente updated = commandBus.dispatch(mapper.toCommand(id, request));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandBus.dispatch(new DeleteClienteCommand(id));
        return ResponseEntity.noContent().build();
    }
}
