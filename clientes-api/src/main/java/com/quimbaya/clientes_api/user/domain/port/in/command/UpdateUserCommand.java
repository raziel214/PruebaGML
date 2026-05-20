package com.quimbaya.clientes_api.user.domain.port.in.command;

import com.quimbaya.clientes_api.shared.cqrs.Command;
import com.quimbaya.clientes_api.user.domain.model.User;

public record UpdateUserCommand(
        Long id,
        String userName,
        String userLastName,
        String userEmail) implements Command<User> {
}
