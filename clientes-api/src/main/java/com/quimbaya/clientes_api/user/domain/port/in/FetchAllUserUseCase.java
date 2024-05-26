package com.quimbaya.clientes_api.user.domain.port.in;

import java.util.List;

import com.quimbaya.clientes_api.user.domain.model.Users;

public interface FetchAllUserUseCase {
	List<Users> fetchAll();

}
