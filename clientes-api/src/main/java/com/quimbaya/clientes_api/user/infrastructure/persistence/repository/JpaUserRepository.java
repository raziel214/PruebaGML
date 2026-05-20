package com.quimbaya.clientes_api.user.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quimbaya.clientes_api.user.infrastructure.persistence.entity.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);
}
