package com.quimbaya.clientes_api.cliente.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.DeleteClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteCommandRepository;
import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;

@Component
public class DeleteClienteCommandHandler implements CommandHandler<DeleteClienteCommand, Void> {

    private final ClienteCommandRepository repository;

    public DeleteClienteCommandHandler(ClienteCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Void handle(DeleteClienteCommand command) {
        boolean deleted = repository.deleteById(command.id());
        if (!deleted) {
            throw new ClienteNotFoundException(command.id());
        }
        return null;
    }
}
