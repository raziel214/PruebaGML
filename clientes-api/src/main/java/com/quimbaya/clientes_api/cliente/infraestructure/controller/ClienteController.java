package com.quimbaya.clientes_api.cliente.infraestructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.cliente.aplication.service.ClienteService;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

@RestController
@RequestMapping("/api/ciudad")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> fetchAllClientes(){
		List<Cliente> cliente=clienteService.fetchAll();
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}
}
