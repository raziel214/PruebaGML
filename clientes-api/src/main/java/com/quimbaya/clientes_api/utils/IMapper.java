package com.quimbaya.clientes_api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.infraestructure.persistence.entity.ClienteEntity;
import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.infraestructure.persistence.entity.UserEntity;


@Mapper
public interface IMapper {

	@Mapping(target = "id")
	public abstract ClienteEntity clienteConvertToEntity(Cliente cliente);
	
	@Mapping(target = "id")
	public abstract Cliente clienteConvertToDomain(ClienteEntity clienteEntity);
	
	@Mapping(target = "user_id")
	public abstract UserEntity userConvertToEntity(Users user); 
	
	@Mapping(target = "user_id")
	public abstract Users userConvertToDomain(UserEntity userEntity); 
	
}
