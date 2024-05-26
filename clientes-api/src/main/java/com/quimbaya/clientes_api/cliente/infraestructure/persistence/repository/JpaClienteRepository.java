package com.quimbaya.clientes_api.cliente.infraestructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quimbaya.clientes_api.cliente.infraestructure.persistence.entity.ClienteEntity;

public interface JpaClienteRepository extends JpaRepository <ClienteEntity,Long> {

	Optional<ClienteEntity> findBySharedKey(String sharedKey);
}
