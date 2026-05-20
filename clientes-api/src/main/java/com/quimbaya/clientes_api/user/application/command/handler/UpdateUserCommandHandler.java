package com.quimbaya.clientes_api.user.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;
import com.quimbaya.clientes_api.user.domain.exception.EmailAlreadyExistsException;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.command.UpdateUserCommand;
import com.quimbaya.clientes_api.user.domain.port.out.UserCommandRepository;

@Component
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand, User> {

    private final UserCommandRepository repository;

    public UpdateUserCommandHandler(UserCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User handle(UpdateUserCommand command) {
        User existing = repository.findById(command.id())
                .orElseThrow(() -> new UserNotFoundException(command.id()));
        if (!existing.userEmail().equalsIgnoreCase(command.userEmail())
                && repository.existsByEmail(command.userEmail())) {
            throw new EmailAlreadyExistsException(command.userEmail());
        }
        User updated = existing.withUpdatedProfile(
                command.userName(),
                command.userLastName(),
                command.userEmail());
        return repository.save(updated);
    }
}
