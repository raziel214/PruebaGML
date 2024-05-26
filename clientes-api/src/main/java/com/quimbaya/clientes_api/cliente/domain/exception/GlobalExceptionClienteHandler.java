package com.quimbaya.clientes_api.cliente.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionClienteHandler {

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(ClienteSharedKeyNotFoundException.class)
	public ResponseEntity<String> handleClienteShareKeyNotFoundException(ClienteSharedKeyNotFoundException e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
