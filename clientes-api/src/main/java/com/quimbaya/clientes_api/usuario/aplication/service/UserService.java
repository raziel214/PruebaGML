package com.quimbaya.clientes_api.usuario.aplication.service;

import java.util.List;
import java.util.Optional;

import com.quimbaya.clientes_api.usuario.domain.model.LoginUser;
import com.quimbaya.clientes_api.usuario.domain.model.Users;
import com.quimbaya.clientes_api.usuario.domain.port.in.CreatedUserUseCase;
import com.quimbaya.clientes_api.usuario.domain.port.in.DeleteUserUsecase;
import com.quimbaya.clientes_api.usuario.domain.port.in.FetchAllUserUseCase;
import com.quimbaya.clientes_api.usuario.domain.port.in.FindByIdUserUsecase;
import com.quimbaya.clientes_api.usuario.domain.port.in.LoginUserUseCase;
import com.quimbaya.clientes_api.usuario.domain.port.in.UpdateUserUseCase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserService implements CreatedUserUseCase,DeleteUserUsecase,FetchAllUserUseCase,FindByIdUserUsecase,UpdateUserUseCase,LoginUserUseCase {

	private final  CreatedUserUseCase createdUserUseCase;
	private final  DeleteUserUsecase deleteUserUsecase;
	private final  FetchAllUserUseCase fetchAllUserUseCase;
	private final  FindByIdUserUsecase findByIdUserUsecase;
	private final  UpdateUserUseCase   updateUserUseCase;
	private final  LoginUserUseCase   loginUserUseCase;
	
	@Override
	public Users createdUser(Users user) {
		// TODO Auto-generated method stub
		return createdUserUseCase.createdUser(user);
	}

	@Override
	public Users updateUser(Long id, Users user) {
		// TODO Auto-generated method stub
		return updateUserUseCase.updateUser(id, user);
	}

	@Override
	public Optional<Users> findById(Long id) {
		// TODO Auto-generated method stub
		return findByIdUserUsecase.findById(id);
	}

	@Override
	public List<Users> fetchAll() {
		// TODO Auto-generated method stub
		return fetchAllUserUseCase.fetchAll();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return deleteUserUsecase.deleteById(id);
	}
	@Override
	public LoginUser authenticate(LoginUser loginUser) {
		// TODO Auto-generated method stub
		return loginUserUseCase.authenticate(loginUser);
	}
	public Users findByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return loginUserUseCase.findByEmail(userEmail);
	}
	

	

}
