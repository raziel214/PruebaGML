package com.quimbaya.clientes_api.security.domain.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String reason) {
        super("Invalid or expired token: " + reason);
    }
}
