package com.quimbaya.clientes_api.user.domain.port.in;

import com.quimbaya.clientes_api.user.domain.model.Users;

public interface UpdateUserUseCase {
	
	Users updateUser(Long id,Users user );

}
