package com.quimbaya.clientes_api.user.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.port.in.command.DeleteUserCommand;
import com.quimbaya.clientes_api.user.domain.port.out.UserCommandRepository;

@Component
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, Void> {

    private final UserCommandRepository repository;

    public DeleteUserCommandHandler(UserCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Void handle(DeleteUserCommand command) {
        boolean deleted = repository.deleteById(command.id());
        if (!deleted) {
            throw new UserNotFoundException(command.id());
        }
        return null;
    }
}
