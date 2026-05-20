package com.quimbaya.clientes_api.cliente.domain.port.in.command;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.shared.cqrs.Command;

public record UpdateClienteCommand(
        Long id,
        String sharedKey,
        String nombre,
        String apellido,
        String correo,
        String telefono) implements Command<Cliente> {
}
