package com.quimbaya.clientes_api.cliente.domain.exception;

public class ClienteSharedKeyNotFoundException extends RuntimeException {

    public ClienteSharedKeyNotFoundException(String sharedKey) {
        super("Cliente with sharedKey '" + sharedKey + "' not found");
    }
}
