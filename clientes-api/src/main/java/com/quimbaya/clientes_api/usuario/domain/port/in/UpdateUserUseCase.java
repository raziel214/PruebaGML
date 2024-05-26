package com.quimbaya.clientes_api.usuario.domain.port.in;

import com.quimbaya.clientes_api.usuario.domain.model.Users;

public interface UpdateUserUseCase {
	
	Users updateUser(Long id,Users user );

}
