package com.quimbaya.clientes_api.usuario.aplication.usescases;


import com.quimbaya.clientes_api.usuario.domain.model.LoginUser;
import com.quimbaya.clientes_api.usuario.domain.model.Users;
import com.quimbaya.clientes_api.usuario.domain.port.in.LoginUserUseCase;
import com.quimbaya.clientes_api.usuario.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class LoginRequestUseCaseImpl implements LoginUserUseCase {
	
	private final UserRepositoryPort userRepositoryPort;
	
	


	@Override
	public LoginUser authenticate(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return userRepositoryPort.findByEmailAndPassword(loginUser.getUserEmail(),loginUser.getUserPass());
	}



	@Override
	public Users findByEmail(String email) {
	    return userRepositoryPort.findByEmail(email)
	        .orElseThrow(() -> new IllegalArgumentException("No Users found with email: " + email));
	}

}
