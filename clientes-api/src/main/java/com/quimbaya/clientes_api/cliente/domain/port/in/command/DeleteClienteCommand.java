package com.quimbaya.clientes_api.cliente.domain.port.in.command;

import com.quimbaya.clientes_api.shared.cqrs.Command;

public record DeleteClienteCommand(Long id) implements Command<Void> {
}
