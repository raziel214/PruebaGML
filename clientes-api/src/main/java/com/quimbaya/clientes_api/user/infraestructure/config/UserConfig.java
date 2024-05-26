package com.quimbaya.clientes_api.user.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quimbaya.clientes_api.user.aplication.service.UserService;
import com.quimbaya.clientes_api.user.aplication.usescases.CreatedUserCaseImpl;
import com.quimbaya.clientes_api.user.aplication.usescases.DeleteUserCaseImpl;
import com.quimbaya.clientes_api.user.aplication.usescases.FetchAllUsersCaseImpl;
import com.quimbaya.clientes_api.user.aplication.usescases.FindUserCaseByIdImpl;
import com.quimbaya.clientes_api.user.aplication.usescases.UpdateUserCaseImpl;
import com.quimbaya.clientes_api.user.aplication.usescases.LoginRequestUseCaseImpl;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;
import com.quimbaya.clientes_api.user.infraestructure.persistence.repository.JpaUserRepositoryAdapter;

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
