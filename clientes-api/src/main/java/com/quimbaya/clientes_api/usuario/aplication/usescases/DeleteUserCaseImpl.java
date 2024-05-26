package com.quimbaya.clientes_api.usuario.aplication.usescases;



import com.quimbaya.clientes_api.usuario.domain.port.in.DeleteUserUsecase;
import com.quimbaya.clientes_api.usuario.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserCaseImpl implements DeleteUserUsecase {
	
	private final UserRepositoryPort userRepositoryPort;

	

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return userRepositoryPort.deleteById(id);
	}
	
	

}
