package com.quimbaya.clientes_api.usuario.infraestructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.security.domain.exception.InvalidCredentialsException;
import com.quimbaya.clientes_api.usuario.aplication.service.UserService;
import com.quimbaya.clientes_api.usuario.domain.exception.UserNotFoundException;
import com.quimbaya.clientes_api.usuario.domain.model.LoginUser;
import com.quimbaya.clientes_api.usuario.domain.model.Users;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity <List<Users>> fetchAllUsers(){
		List<Users> user= userService.fetchAll();
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity <Users>createUser(@RequestBody Users user)
	{
		Users createdUser=userService.createdUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody Users user){
		Users updateUser= userService.updateUser(id, user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
		
	}
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void>deleteUser(@PathVariable Long id)
	{
		boolean isDeleted = userService.deleteById(id);
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // HTTP 204 (No Content)
		}else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // HTTP 404 (Not Found)
        }
	}
	 
	 @PostMapping("/login")
	 public ResponseEntity<?> authenticateUser(@RequestBody LoginUser loginRequest) {
	       
	     // Obtener el usuario de la base de datos basado en el email proporcionado
	     Users userFromDb = userService.findByEmail(loginRequest.getUserEmail()); 
	     // Si no hay un usuario en la base de datos con ese email, o la contraseña no coincide, lanza la excepción
	     if (userFromDb == null || !passwordEncoder.matches(loginRequest.getUserPass(), userFromDb.getUserPass())) {
	         throw new InvalidCredentialsException();
	     }
	     // Credenciales correctas
	     return new ResponseEntity<>(userFromDb, HttpStatus.OK);
	 }
	 
	 @PostMapping("/{id}")
	 public ResponseEntity<Users> findByIdUsers(@PathVariable Long id) {
		 Users userFindById =userService.findById(id)
				 .orElseThrow(()->new UserNotFoundException(id));
		 return new ResponseEntity<>(userFindById, HttpStatus.OK);
		 
	 }


	
	
}
