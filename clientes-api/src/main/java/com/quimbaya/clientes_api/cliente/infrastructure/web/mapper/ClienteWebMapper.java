package com.quimbaya.clientes_api.cliente.infrastructure.web.mapper;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.CreateClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.UpdateClienteCommand;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.ClienteResponse;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.CreateClienteRequest;
import com.quimbaya.clientes_api.cliente.infrastructure.web.dto.UpdateClienteRequest;

@Component
public class ClienteWebMapper {

    public CreateClienteCommand toCommand(CreateClienteRequest request) {
        return new CreateClienteCommand(
                request.sharedKey(),
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.telefono());
    }

    public UpdateClienteCommand toCommand(Long id, UpdateClienteRequest request) {
        return new UpdateClienteCommand(
                id,
                request.sharedKey(),
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.telefono());
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.id(),
                cliente.sharedKey(),
                cliente.nombre(),
                cliente.apellido(),
                cliente.correo(),
                cliente.telefono());
    }
}
