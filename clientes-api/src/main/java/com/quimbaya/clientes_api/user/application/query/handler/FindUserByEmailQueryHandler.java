package com.quimbaya.clientes_api.user.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByEmailQuery;
import com.quimbaya.clientes_api.user.domain.port.out.UserQueryRepository;

@Component
public class FindUserByEmailQueryHandler implements QueryHandler<FindUserByEmailQuery, User> {

    private final UserQueryRepository repository;

    public FindUserByEmailQueryHandler(UserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public User handle(FindUserByEmailQuery query) {
        return repository.findByEmail(query.userEmail())
                .orElseThrow(() -> new UserNotFoundException(query.userEmail()));
    }
}
