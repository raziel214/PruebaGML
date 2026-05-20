package com.quimbaya.clientes_api.user.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.shared.cqrs.CommandBus;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.command.DeleteUserCommand;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.CreateUserRequest;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.UpdateUserRequest;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.UserResponse;
import com.quimbaya.clientes_api.user.infrastructure.web.mapper.UserWebMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserCommandController {

    private final CommandBus commandBus;
    private final UserWebMapper mapper;

    public UserCommandController(CommandBus commandBus, UserWebMapper mapper) {
        this.commandBus = commandBus;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        User created = commandBus.dispatch(mapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        User updated = commandBus.dispatch(mapper.toCommand(id, request));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandBus.dispatch(new DeleteUserCommand(id));
        return ResponseEntity.noContent().build();
    }
}
