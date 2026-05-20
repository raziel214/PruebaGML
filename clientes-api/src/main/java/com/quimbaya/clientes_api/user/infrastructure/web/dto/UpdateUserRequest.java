package com.quimbaya.clientes_api.user.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank @Size(max = 100) String userName,
        @NotBlank @Size(max = 100) String userLastName,
        @NotBlank @Email @Size(max = 255) String userEmail) {
}
