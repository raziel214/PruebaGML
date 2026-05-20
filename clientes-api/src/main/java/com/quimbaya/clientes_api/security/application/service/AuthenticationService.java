package com.quimbaya.clientes_api.security.application.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.shared.cqrs.QueryBus;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByEmailQuery;

@Service
public class AuthenticationService implements UserDetailsService {

    private final QueryBus queryBus;

    public AuthenticationService(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = queryBus.dispatch(new FindUserByEmailQuery(email));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.userEmail())
                    .password(user.passwordHash())
                    .authorities(List.of(
                            new SimpleGrantedAuthority("ROLE_ADMIN"),
                            new SimpleGrantedAuthority("ROLE_USER")))
                    .accountLocked(false)
                    .disabled(false)
                    .build();
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException("Invalid credentials", ex);
        }
    }
}
