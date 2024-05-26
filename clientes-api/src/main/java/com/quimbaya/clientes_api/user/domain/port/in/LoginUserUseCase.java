package com.quimbaya.clientes_api.user.domain.port.in;

import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.model.LoginUser;

public interface LoginUserUseCase {
	LoginUser authenticate(LoginUser loginUser);
	Users findByEmail(String Email);
}
