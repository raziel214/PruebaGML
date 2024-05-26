package com.quimbaya.clientes_api.usuario.domain.port.in;

import java.util.List;

import com.quimbaya.clientes_api.usuario.domain.model.Users;


public interface FetchAllUserUseCase {
	List<Users> fetchAll();

}
