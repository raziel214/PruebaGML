package com.quimbaya.clientes_api.cliente.domain.exception;

import com.quimbaya.clientes_api.utils.Constanst;

public class ClienteNotFoundException extends RuntimeException {

	
	public ClienteNotFoundException(Long id) {
		super( Constanst.ClienteException + id +Constanst.ClienteNotFound);
	}
	
}
