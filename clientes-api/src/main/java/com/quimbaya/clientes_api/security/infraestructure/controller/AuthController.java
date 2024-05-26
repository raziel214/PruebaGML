package com.quimbaya.clientes_api.security.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.security.aplication.service.AuthenticationService;
import com.quimbaya.clientes_api.security.aplication.service.JwtService;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.LoginUser;
import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/public/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    
    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepositoryPort;
    
       

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody  LoginUser loginUser) {    	    
    	//String token = authenticationService.loadUserByUsername(loginUser.getUserEmail(), loginUser.getUserPass());
    	
    	UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginUser.getUserEmail(),loginUser.getUserPass() );
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());
        Users user = userRepositoryPort.findByEmail(loginUser.getUserEmail())
                .orElseThrow(()->new UserNotFoundException((long) 1));

        String jwt = this.jwtService.generateToken(user);
        System.out.println(jwt);

        //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    	
         return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
