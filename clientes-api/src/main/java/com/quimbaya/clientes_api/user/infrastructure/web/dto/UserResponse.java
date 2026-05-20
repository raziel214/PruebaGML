package com.quimbaya.clientes_api.user.infrastructure.web.dto;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String userName,
        String userLastName,
        String userEmail,
        LocalDateTime dateCreation,
        LocalDateTime dateUpdate,
        Short state) {
}
