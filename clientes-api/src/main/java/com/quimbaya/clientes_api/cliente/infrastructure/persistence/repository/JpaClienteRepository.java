package com.quimbaya.clientes_api.cliente.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quimbaya.clientes_api.cliente.infrastructure.persistence.entity.ClienteEntity;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findBySharedKey(String sharedKey);
}
