package com.quimbaya.clientes_api.user.domain.port.out;

import java.util.Optional;

import com.quimbaya.clientes_api.user.domain.model.User;

public interface UserCommandRepository {

    User save(User user);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    boolean deleteById(Long id);
}
