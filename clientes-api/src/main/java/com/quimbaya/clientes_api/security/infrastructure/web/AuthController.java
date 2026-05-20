package com.quimbaya.clientes_api.security.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.security.application.service.JwtService;
import com.quimbaya.clientes_api.security.infrastructure.web.dto.LoginRequest;
import com.quimbaya.clientes_api.security.infrastructure.web.dto.LoginResponse;
import com.quimbaya.clientes_api.shared.cqrs.QueryBus;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByEmailQuery;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final QueryBus queryBus;

    public AuthController(
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            QueryBus queryBus) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.queryBus = queryBus;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userEmail(), request.password()));
        User user = queryBus.dispatch(new FindUserByEmailQuery(request.userEmail()));
        return ResponseEntity.ok(LoginResponse.bearer(jwtService.generateToken(user)));
    }
}
