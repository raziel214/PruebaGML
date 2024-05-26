package com.quimbaya.clientes_api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.infraestructure.persistence.entity.ClienteEntity;

@Mapper
public interface IMapper {

	@Mapping(target = "id")
	public abstract ClienteEntity clienteConvertToEntity(Cliente cliente);
	
	@Mapping(target = "id")
	public abstract Cliente clienteConvertToDomain(ClienteEntity clienteEntity);
}
