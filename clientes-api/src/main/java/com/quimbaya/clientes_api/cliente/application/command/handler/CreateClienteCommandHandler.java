package com.quimbaya.clientes_api.cliente.application.command.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.in.command.CreateClienteCommand;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteCommandRepository;
import com.quimbaya.clientes_api.shared.cqrs.CommandHandler;

@Component
public class CreateClienteCommandHandler implements CommandHandler<CreateClienteCommand, Cliente> {

    private final ClienteCommandRepository repository;

    public CreateClienteCommandHandler(ClienteCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cliente handle(CreateClienteCommand command) {
        Cliente cliente = Cliente.createNew(
                command.sharedKey(),
                command.nombre(),
                command.apellido(),
                command.correo(),
                command.telefono());
        return repository.save(cliente);
    }
}
