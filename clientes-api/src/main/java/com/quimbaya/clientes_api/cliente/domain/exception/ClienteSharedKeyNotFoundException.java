package com.quimbaya.clientes_api.cliente.domain.exception;

import com.quimbaya.clientes_api.utils.Constanst;

public class ClienteSharedKeyNotFoundException extends RuntimeException{

	public ClienteSharedKeyNotFoundException(String shareKey) {
		super( Constanst.ClienteShareKeyNotFound + shareKey +Constanst.ClienteNotFound);
	}
}
