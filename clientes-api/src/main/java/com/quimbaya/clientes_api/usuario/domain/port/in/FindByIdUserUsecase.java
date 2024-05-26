package com.quimbaya.clientes_api.usuario.domain.port.in;

import java.util.Optional;

import com.quimbaya.clientes_api.usuario.domain.model.Users;

public interface FindByIdUserUsecase {

	Optional<Users> findById(Long id);
}
