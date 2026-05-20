package com.quimbaya.clientes_api.security.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank @Email String userEmail,
        @NotBlank String password) {
}
