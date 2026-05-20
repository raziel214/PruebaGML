package com.quimbaya.clientes_api.user.domain.port.out;

public interface PasswordHasher {

    String hash(String plainPassword);

    boolean matches(String plainPassword, String passwordHash);
}
