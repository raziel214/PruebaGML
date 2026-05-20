package com.quimbaya.clientes_api.cliente.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteQueryRepository;
import com.quimbaya.clientes_api.cliente.infrastructure.persistence.mapper.ClientePersistenceMapper;

@Component
public class ClienteQueryRepositoryAdapter implements ClienteQueryRepository {

    private final JpaClienteRepository jpaRepository;
    private final ClientePersistenceMapper mapper;

    public ClienteQueryRepositoryAdapter(
            JpaClienteRepository jpaRepository,
            ClientePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findBySharedKey(String sharedKey) {
        return jpaRepository.findBySharedKey(sharedKey).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
