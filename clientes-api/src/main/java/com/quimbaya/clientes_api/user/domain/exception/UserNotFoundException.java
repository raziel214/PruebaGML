package com.quimbaya.clientes_api.user.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found");
    }

    public UserNotFoundException(String userEmail) {
        super("User with email '" + userEmail + "' not found");
    }
}
