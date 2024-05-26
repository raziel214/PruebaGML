package com.quimbaya.clientes_api.user.aplication.usescases;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.port.in.FetchAllUserUseCase;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Service
public class FetchAllUsersCaseImpl implements FetchAllUserUseCase {
	
	private final UserRepositoryPort userRepositoryPort;

	
	@Override
	public List<Users> fetchAll() {
	    List<Users> usersFromRepo = userRepositoryPort.findAll();
	    
	    // Si es null, devuelve una lista vacía.
	    if (usersFromRepo == null) {
	        return Collections.emptyList();
	    }

	    return usersFromRepo.stream()
	            .map(this::convertUserToDomain)
	            .collect(Collectors.toList());
	}

	private Users convertUserToDomain(Users userRepo) {
		
		return userRepo;
		
	}
	
	
	
	
	
	

}
