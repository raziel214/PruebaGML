package com.quimbaya.clientes_api.user.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.domain.port.out.UserCommandRepository;
import com.quimbaya.clientes_api.user.infrastructure.persistence.entity.UserEntity;
import com.quimbaya.clientes_api.user.infrastructure.persistence.mapper.UserPersistenceMapper;

@Component
public class UserCommandRepositoryAdapter implements UserCommandRepository {

    private final JpaUserRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    public UserCommandRepositoryAdapter(JpaUserRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity persisted = jpaRepository.save(entity);
        return mapper.toDomain(persisted);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByUserEmail(email);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!jpaRepository.existsById(id)) {
            return false;
        }
        jpaRepository.deleteById(id);
        return true;
    }
}
