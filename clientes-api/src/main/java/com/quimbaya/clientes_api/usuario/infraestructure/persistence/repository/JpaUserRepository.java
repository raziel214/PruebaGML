package com.quimbaya.clientes_api.usuario.infraestructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quimbaya.clientes_api.usuario.infraestructure.persistence.entity.UserEntity;


@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
	Optional<UserEntity> findByUserEmailAndUserPass(String userEmail, String userPass);
	Optional<UserEntity> findByUserEmail(String userEmail);
	

}
