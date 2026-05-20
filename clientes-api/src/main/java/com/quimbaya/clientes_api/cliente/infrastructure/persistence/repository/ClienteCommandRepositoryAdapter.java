package com.quimbaya.clientes_api.cliente.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteCommandRepository;
import com.quimbaya.clientes_api.cliente.infrastructure.persistence.entity.ClienteEntity;
import com.quimbaya.clientes_api.cliente.infrastructure.persistence.mapper.ClientePersistenceMapper;

@Component
public class ClienteCommandRepositoryAdapter implements ClienteCommandRepository {

    private final JpaClienteRepository jpaRepository;
    private final ClientePersistenceMapper mapper;

    public ClienteCommandRepositoryAdapter(
            JpaClienteRepository jpaRepository,
            ClientePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente);
        ClienteEntity persisted = jpaRepository.save(entity);
        return mapper.toDomain(persisted);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
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
