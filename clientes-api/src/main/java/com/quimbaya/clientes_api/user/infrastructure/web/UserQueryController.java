package com.quimbaya.clientes_api.user.infrastructure.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.shared.cqrs.QueryBus;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FetchAllUsersQuery;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByEmailQuery;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByIdQuery;
import com.quimbaya.clientes_api.user.infrastructure.web.dto.UserResponse;
import com.quimbaya.clientes_api.user.infrastructure.web.mapper.UserWebMapper;

@RestController
@RequestMapping("/api/users")
public class UserQueryController {

    private final QueryBus queryBus;
    private final UserWebMapper mapper;

    public UserQueryController(QueryBus queryBus, UserWebMapper mapper) {
        this.queryBus = queryBus;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> fetchAll() {
        List<User> users = queryBus.dispatch(new FetchAllUsersQuery());
        return ResponseEntity.ok(users.stream().map(mapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = queryBus.dispatch(new FindUserByIdQuery(id));
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email) {
        User user = queryBus.dispatch(new FindUserByEmailQuery(email));
        return ResponseEntity.ok(mapper.toResponse(user));
    }
}
