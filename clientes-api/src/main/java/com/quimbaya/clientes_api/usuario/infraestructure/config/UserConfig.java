package com.quimbaya.clientes_api.usuario.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quimbaya.clientes_api.usuario.aplication.service.UserService;
import com.quimbaya.clientes_api.usuario.aplication.usescases.CreatedUserCaseImpl;
import com.quimbaya.clientes_api.usuario.aplication.usescases.DeleteUserCaseImpl;
import com.quimbaya.clientes_api.usuario.aplication.usescases.FetchAllUsersCaseImpl;
import com.quimbaya.clientes_api.usuario.aplication.usescases.FindUserCaseByIdImpl;
import com.quimbaya.clientes_api.usuario.aplication.usescases.LoginRequestUseCaseImpl;
import com.quimbaya.clientes_api.usuario.aplication.usescases.UpdateUserCaseImpl;
import com.quimbaya.clientes_api.usuario.domain.port.out.UserRepositoryPort;
import com.quimbaya.clientes_api.usuario.infraestructure.persistence.repository.JpaUserRepositoryAdapter;


@Configuration
public class UserConfig {
	
	
	@Bean
	public UserService userService(UserRepositoryPort userRepositoryPort) {
		return new UserService(
				new CreatedUserCaseImpl(userRepositoryPort),
				new DeleteUserCaseImpl(userRepositoryPort),
				new FetchAllUsersCaseImpl(userRepositoryPort),
				new FindUserCaseByIdImpl(userRepositoryPort), 
				new UpdateUserCaseImpl(userRepositoryPort),
				new LoginRequestUseCaseImpl(userRepositoryPort));		
	}
	@Bean
	public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
		
		return jpaUserRepositoryAdapter;
	
   }

}
