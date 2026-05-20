package com.quimbaya.clientes_api.user.application.query.handler;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quimbaya.clientes_api.shared.cqrs.QueryHandler;
import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.in.query.FetchAllUsersQuery;
import com.quimbaya.clientes_api.user.domain.port.out.UserQueryRepository;

@Component
public class FetchAllUsersQueryHandler implements QueryHandler<FetchAllUsersQuery, List<User>> {

    private final UserQueryRepository repository;

    public FetchAllUsersQueryHandler(UserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> handle(FetchAllUsersQuery query) {
        return repository.findAll();
    }
}
