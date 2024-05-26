package com.quimbaya.clientes_api.user.domain.port.in;

import com.quimbaya.clientes_api.user.domain.model.Users;

public interface CreatedUserUseCase {

	Users createdUser(Users  user );
}
