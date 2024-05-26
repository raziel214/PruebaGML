package com.quimbaya.clientes_api.usuario.domain.port.in;

import com.quimbaya.clientes_api.usuario.domain.model.LoginUser;
import com.quimbaya.clientes_api.usuario.domain.model.Users;

public interface LoginUserUseCase {
	LoginUser authenticate(LoginUser loginUser);
	Users findByEmail(String Email);
}
