package com.quimbaya.clientes_api.user.domain.port.in.command;

import com.quimbaya.clientes_api.shared.cqrs.Command;

public record DeleteUserCommand(Long id) implements Command<Void> {
}
