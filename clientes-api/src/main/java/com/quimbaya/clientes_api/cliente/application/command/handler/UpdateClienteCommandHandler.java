package com.quimbaya.clientes_api.cliente.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.UpdateClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteCommandRepository;
import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;

@Component
public class UpdateClienteCommandHandler implements CommandHandler<UpdateClienteCommand, Cliente> {

    private final ClienteCommandRepository repository;

    public UpdateClienteCommandHandler(ClienteCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cliente handle(UpdateClienteCommand command) {
        Cliente existing = repository.findById(command.id())
                .orElseThrow(() -> new ClienteNotFoundException(command.id()));
        Cliente updated = existing.withUpdatedContactInfo(
                command.sharedKey(),
                command.nombre(),
                command.apellido(),
                command.correo(),
                command.telefono());
        return repository.save(updated);
    }
}
