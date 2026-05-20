package com.quimbaya.clientes_api.user.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;
import com.quimbaya.clientes_api.user.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FindUserByIdQuery;
import com.quimbaya.clientes_api.user.domain.port.out.UserQueryRepository;

@Component
public class FindUserByIdQueryHandler implements QueryHandler<FindUserByIdQuery, User> {

    private final UserQueryRepository repository;

    public FindUserByIdQueryHandler(UserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public User handle(FindUserByIdQuery query) {
        return repository.findById(query.id())
                .orElseThrow(() -> new UserNotFoundException(query.id()));
    }
}
