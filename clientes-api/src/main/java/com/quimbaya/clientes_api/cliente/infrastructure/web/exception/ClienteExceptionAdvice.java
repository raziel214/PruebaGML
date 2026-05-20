package com.quimbaya.clientes_api.cliente.infrastructure.web.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.exception.ClienteSharedKeyNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.quimbaya.clientes_api.cliente.infrastructure.web")
public class ClienteExceptionAdvice {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClienteNotFound(ClienteNotFoundException ex) {
        return notFound(ex.getMessage());
    }

    @ExceptionHandler(ClienteSharedKeyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClienteSharedKeyNotFound(ClienteSharedKeyNotFoundException ex) {
        return notFound(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Bad Request",
                "message", ex.getMessage()));
    }

    private ResponseEntity<Map<String, Object>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", message));
    }
}
