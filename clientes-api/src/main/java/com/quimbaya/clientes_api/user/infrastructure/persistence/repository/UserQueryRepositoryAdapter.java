package com.quimbaya.clientes_api.user.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.out.UserQueryRepository;
import com.quimbaya.clientes_api.user.infrastructure.persistence.mapper.UserPersistenceMapper;

@Component
public class UserQueryRepositoryAdapter implements UserQueryRepository {

    private final JpaUserRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    public UserQueryRepositoryAdapter(JpaUserRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByUserEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
