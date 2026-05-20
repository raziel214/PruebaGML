package com.quimbaya.clientes_api.cliente.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.quimbaya.clientes_api.cliente.domain.model.Cliente;
import com.quimbaya.clientes_api.cliente.infrastructure.persistence.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClientePersistenceMapper {

    ClienteEntity toEntity(Cliente cliente);

    default Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return Cliente.reconstitute(
                entity.getId(),
                entity.getSharedKey(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getCorreo(),
                entity.getTelefono());
    }
}
