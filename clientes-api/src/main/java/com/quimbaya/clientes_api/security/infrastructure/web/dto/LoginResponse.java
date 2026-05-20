package com.quimbaya.clientes_api.security.infrastructure.web.dto;

public record LoginResponse(String accessToken, String tokenType) {

    public static LoginResponse bearer(String token) {
        return new LoginResponse(token, "Bearer");
    }
}
