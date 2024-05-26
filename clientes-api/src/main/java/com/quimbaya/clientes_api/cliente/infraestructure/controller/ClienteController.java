package com.quimbaya.clientes_api.cliente.infraestructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quimbaya.clientes_api.cliente.aplication.service.ClienteService;
import com.quimbaya.clientes_api.cliente.domain.exception.ClienteNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.exception.ClienteSharedKeyNotFoundException;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> fetchAllCliente(){
		
		List<Cliente> cliente= clienteService.fetchAll();
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
		
		Cliente createCliente=clienteService.createCliente(cliente);
		return new ResponseEntity<>(createCliente,HttpStatus.OK);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateCliente(@PathVariable Long id,@RequestBody Cliente cliente)
	{		
		Cliente updateCliente= clienteService.updateCliente(id, cliente);
		return new ResponseEntity<>(updateCliente, HttpStatus.OK);		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>deleteCliente(@PathVariable Long id)
	{
		boolean isDelete=clienteService.deleteById(id);
		if(isDelete) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{id}")
    public ResponseEntity<Cliente>finByIdRole(@PathVariable Long id) {
    	Cliente findIdCliente = clienteService.findById(id)
    			.orElseThrow(()->new ClienteNotFoundException(id));
    	return new ResponseEntity<>(findIdCliente, HttpStatus.OK);
    }
	@PostMapping("/sharedKey/{id}")
    public ResponseEntity<Cliente>findBySharedKey(@PathVariable Long id,@RequestBody String sharedKey ) {
    	Cliente findIdCliente = clienteService.findBySharedKey(sharedKey)
    			.orElseThrow(()->new ClienteSharedKeyNotFoundException(sharedKey));
    	return new ResponseEntity<>(findIdCliente, HttpStatus.OK);
    }
}
