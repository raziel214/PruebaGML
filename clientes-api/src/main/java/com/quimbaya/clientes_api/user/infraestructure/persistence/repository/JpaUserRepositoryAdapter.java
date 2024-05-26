package com.quimbaya.clientes_api.user.infraestructure.persistence.repository;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.user.domain.exception.InvalidCredentialsException;
import com.quimbaya.clientes_api.user.domain.model.LoginUser;
import com.quimbaya.clientes_api.user.domain.model.Users;
import com.quimbaya.clientes_api.user.domain.port.out.UserRepositoryPort;
import com.quimbaya.clientes_api.user.infraestructure.persistence.entity.UserEntity;
import com.quimbaya.clientes_api.utils.IMapper;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
	
	@Autowired
	private JpaUserRepository jpaUserRepository;
	
	
	private final IMapper mapper= Mappers.getMapper(IMapper.class);

	public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
		this.jpaUserRepository = jpaUserRepository;
		
	}


	@Override
	public Users save(Users user) {		
		
		 // Convertir de Users (dominio) a UserEntity (JPA/entidad) usando MapStruct
	    UserEntity entityToSave = mapper.userConvertToEntity(user);
	    // Guardar la entidad
	    UserEntity savedEntity = jpaUserRepository.save(entityToSave);
	    // Convertir de vuelta de UserEntity a Users usando MapStruct
	    return mapper.userConvertToDomain(savedEntity);
	}


	@Override
	public Optional<Users> findById(Long id) {
		// TODO Auto-generated method stub
		return jpaUserRepository.findById(id).map(mapper::userConvertToDomain);
	}

	@Override
	public List<Users> findAll() {
	    return jpaUserRepository.findAll().stream()
	                             .map(mapper::userConvertToDomain)
	                             .collect(Collectors.toList());
	}


	@Override
	public Optional<Users> update(Users user) {
	    if (jpaUserRepository.existsById(user.getCiudadId())) {
	        // Convertir de Users (dominio) a UserEntity (JPA/entidad) usando MapStruct
	        UserEntity entityToUpdate = mapper.userConvertToEntity(user);
	        // Guardar la entidad actualizada
	        UserEntity updatedEntity = jpaUserRepository.save(entityToUpdate);
	        // Convertir de vuelta de UserEntity a Users usando MapStruct y envolver en Optional
	        return Optional.of(mapper.userConvertToDomain(updatedEntity));
	    }
	    return Optional.empty();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		if(jpaUserRepository.existsById(id)){
			jpaUserRepository.deleteById(id);
			return true;
			}
		else {
			return false;
		}
	}

	@Override
	public LoginUser findByEmailAndPassword(String email, String password) {
		Optional<UserEntity> userEntity = jpaUserRepository.findByUserEmailAndUserPass(email, password);
	    if (userEntity.isPresent()) {
	        UserEntity entity = userEntity.get();
	        return new LoginUser(entity.getUserEmail(), entity.getUserPass());
	    } else {
	        throw new InvalidCredentialsException();
	    }
	}


	@Override
	public Optional<Users> findByEmail(String email) {
	    return jpaUserRepository.findByUserEmail(email).map(mapper::userConvertToDomain);
	}
	
	
}
