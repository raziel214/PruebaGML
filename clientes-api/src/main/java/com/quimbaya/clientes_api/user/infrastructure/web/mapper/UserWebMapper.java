package com.quimbaya.clientes_api.user.infrastructure.web.mapper;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.command.CreateUserCommand;
import com.quimbaya.clientes_api.user.domain.port.in.command.UpdateUserCommand;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.CreateUserRequest;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.UpdateUserRequest;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.UserResponse;

@Component
public class UserWebMapper {

    public CreateUserCommand toCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.userName(),
                request.userLastName(),
                request.userEmail(),
                request.password());
    }

    public UpdateUserCommand toCommand(Long id, UpdateUserRequest request) {
        return new UpdateUserCommand(
                id,
                request.userName(),
                request.userLastName(),
                request.userEmail());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.id(),
                user.userName(),
                user.userLastName(),
                user.userEmail(),
                user.dateCreation(),
                user.dateUpdate(),
                user.state());
    }
}
