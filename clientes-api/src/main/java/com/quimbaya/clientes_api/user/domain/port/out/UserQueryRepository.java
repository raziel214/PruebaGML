package com.quimbaya.clientes_api.user.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.quimbaya.clientes_api.user.domain.model.User;

public interface UserQueryRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
