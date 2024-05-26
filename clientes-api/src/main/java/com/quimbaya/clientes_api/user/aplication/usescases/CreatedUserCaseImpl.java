package com.quimbaya.clientes_api.user.aplication.usescases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.port.in.CreatedUserUseCase;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;

@Service
public class CreatedUserCaseImpl implements CreatedUserUseCase{
	
	private final UserRepositoryPort userRepositoryPort; // Inyección de la dependencia del puerto del repositorio
	
	
	public CreatedUserCaseImpl(UserRepositoryPort userRepositoryPort) {
		this.userRepositoryPort = userRepositoryPort;
		
	}

	@Override
    public Users createdUser(Users user) {
        String hashedPassword =new BCryptPasswordEncoder().encode(user.getUserPass()); 
        user.setUserPass(hashedPassword); // Establece la contraseña codificada en el usuario
        return userRepositoryPort.save(user); // Guarda el usuario con la contraseña codificada en el repositorio
    }

}
