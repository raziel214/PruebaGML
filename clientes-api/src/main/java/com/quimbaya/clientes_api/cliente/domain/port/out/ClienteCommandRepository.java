package com.quimbaya.clientes_api.cliente.domain.port.out;

import java.util.Optional;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface ClienteCommandRepository {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    boolean deleteById(Long id);
}
