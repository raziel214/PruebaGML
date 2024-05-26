package com.quimbaya.clientes_api.user.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
	
	 private String userEmail; // El email del usuario autenticado.
	 private String userPass; // El nombre del usuario.
	

}
