package com.quimbaya.clientes_api.usuario.aplication.usescases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.usuario.domain.model.Users;
import com.quimbaya.clientes_api.usuario.domain.port.in.FindByIdUserUsecase;
import com.quimbaya.clientes_api.usuario.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Service
public class FindUserCaseByIdImpl implements FindByIdUserUsecase {
	
	
	private final UserRepositoryPort userRepositoryPort;
	@Override
	public Optional<Users> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepositoryPort.findById(id);
	}
}
