package com.quimbaya.clientes_api.cliente.infrastructure.web.dto;

public record ClienteResponse(
        Long id,
        String sharedKey,
        String nombre,
        String apellido,
        String correo,
        String telefono) {
}
