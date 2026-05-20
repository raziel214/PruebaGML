package com.quimbaya.clientes_api.user.domain.port.in.command;

import com.quimbaya.clientes_api.shared.cqrs.Command;
import com.quimbaya.clientes_api.user.domain.model.User;

public record CreateUserCommand(
        String userName,
        String userLastName,
        String userEmail,
        String plainPassword) implements Command<User> {
}
