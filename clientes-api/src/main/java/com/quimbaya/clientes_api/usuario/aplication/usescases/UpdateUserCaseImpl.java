package com.quimbaya.clientes_api.usuario.aplication.usescases;


import com.quimbaya.clientes_api.usuario.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.usuario.domain.model.Users;
import com.quimbaya.clientes_api.usuario.domain.port.in.UpdateUserUseCase;
import com.quimbaya.clientes_api.usuario.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserCaseImpl implements UpdateUserUseCase{
	
	private final UserRepositoryPort userRepositoryPort;

	

	@Override
	public Users updateUser(Long id, Users user) {
		// TODO Auto-generated method stub
		return userRepositoryPort.findById(id)
				.map(existingUser->{
					existingUser.setUserName(user.getUserName());
					existingUser.setUserLastName(user.getUserLastName());
					existingUser.setUserEmail(user.getUserEmail());
					existingUser.setUserPass(user.getUserPass());
					existingUser.setUserRolId(user.getUserRolId());
					existingUser.setDateCreation(user.getDateCreation());
					existingUser.setDateUpdate(user.getDateUpdate());
					existingUser.setDateDelete(user.getDateDelete());
					existingUser.setState(user.getState());
					existingUser.setFirst(user.getFirst());
					existingUser.setRecoveryPass(user.getRecoveryPass());
					
					return userRepositoryPort.save(existingUser);
					
				}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	


}
