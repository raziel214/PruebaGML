package com.quimbaya.clientes_api.cliente.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface ClienteQueryRepository {

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findBySharedKey(String sharedKey);

    List<Cliente> findAll();
}
