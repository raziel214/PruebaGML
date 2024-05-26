package com.quimbaya.clientes_api.user.domain.port.in;

import java.util.Optional;

import com.quimbaya.clientes_api.user.domain.model.Users;



public interface FindByIdUserUsecase {

	Optional<Users> findById(Long id);
}
