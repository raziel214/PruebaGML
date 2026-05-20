package com.quimbaya.clientes_api.user.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;
import com.quimbaya.clientes_api.user.domain.exception.EmailAlreadyExistsException;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.command.CreateUserCommand;
import com.quimbaya.clientes_api.user.domain.port.out.PasswordHasher;
import com.quimbaya.clientes_api.user.domain.port.out.UserCommandRepository;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {

    private final UserCommandRepository repository;
    private final PasswordHasher passwordHasher;

    public CreateUserCommandHandler(UserCommandRepository repository, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    @Transactional
    public User handle(CreateUserCommand command) {
        if (repository.existsByEmail(command.userEmail())) {
            throw new EmailAlreadyExistsException(command.userEmail());
        }
        String passwordHash = passwordHasher.hash(command.plainPassword());
        User user = User.createNew(
                command.userName(),
                command.userLastName(),
                command.userEmail(),
                passwordHash);
        return repository.save(user);
    }
}
