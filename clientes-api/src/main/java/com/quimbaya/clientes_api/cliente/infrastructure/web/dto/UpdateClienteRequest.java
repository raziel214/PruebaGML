package com.quimbaya.clientes_api.cliente.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateClienteRequest(
        @NotBlank @Size(max = 50) String sharedKey,
        @NotBlank @Size(max = 50) String nombre,
        @NotBlank @Size(max = 50) String apellido,
        @NotBlank @Email @Size(max = 100) String correo,
        @NotBlank @Size(max = 50) String telefono) {
}
