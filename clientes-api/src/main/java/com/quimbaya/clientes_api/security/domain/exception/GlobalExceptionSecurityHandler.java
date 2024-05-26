package com.quimbaya.clientes_api.security.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionSecurityHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	 public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
}
