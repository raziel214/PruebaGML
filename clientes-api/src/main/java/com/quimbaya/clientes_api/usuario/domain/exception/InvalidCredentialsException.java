package com.quimbaya.clientes_api.usuario.domain.exception;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super("Usuario o contraseña incorrectos");
	}
}
