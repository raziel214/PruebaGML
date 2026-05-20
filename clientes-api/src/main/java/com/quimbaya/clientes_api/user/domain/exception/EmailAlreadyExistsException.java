package com.quimbaya.clientes_api.user.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("Email '" + email + "' is already registered");
    }
}
