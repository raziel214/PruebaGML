package com.quimbaya.clientes_api.user.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.quimbaya.clientes_api.user.domain.model.LoginUser;
import com.quimbaya.clientes_api.user.domain.model.Users;



public interface UserRepositoryPort {
	
	Users save(Users user);
    Optional<Users> findById(Long id);
    List<Users> findAll();
    Optional<Users> update(Users user);
    boolean deleteById(Long id);
    LoginUser findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);

}
