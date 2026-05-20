package com.quimbaya.clientes_api.cliente.domain.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(Long id) {
        super("Cliente with id " + id + " not found");
    }
}
