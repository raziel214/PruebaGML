package com.quimbaya.clientes_api.cliente.infraestructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.domain.port.out.ClienteRepositoryPort;
import com.quimbaya.clientes_api.cliente.infraestructure.persistence.entity.ClienteEntity;
import com.quimbaya.clientes_api.utils.IMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaClienteRepositoryAdapter implements ClienteRepositoryPort {

	private final JpaClienteRepository jpaClienteRepository;
	private final IMapper mapper= Mappers.getMapper(IMapper.class);
	
	@Override
	public Cliente save(Cliente cliente) {		
		ClienteEntity entity= mapper.clienteConvertToEntity(cliente);
		entity=jpaClienteRepository.save(entity);
		return mapper.clienteConvertToDomain(entity);
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return jpaClienteRepository.findById(id).map(mapper::clienteConvertToDomain);
	}

	@Override
	public Optional<Cliente> findByShareKey(String shareKey) {
		// TODO Auto-generated method stub
		return jpaClienteRepository.findBySharedKey(shareKey).map(mapper::clienteConvertToDomain);
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return jpaClienteRepository.findAll()
				.stream()
				.map(mapper::clienteConvertToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Cliente> update(Cliente cliente) {
		// TODO Auto-generated method stub
		if(jpaClienteRepository.existsById(cliente.getId())) {
			ClienteEntity entityToUpdate=mapper.clienteConvertToEntity(cliente);
			ClienteEntity updatedEntity=jpaClienteRepository.save(entityToUpdate);
			return Optional.of(mapper.clienteConvertToDomain(updatedEntity));
		}
		return Optional.empty();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		if(jpaClienteRepository.existsById(id)) {
			jpaClienteRepository.deleteById(id);
			return true;
		}		
		return false;
	}		
}
