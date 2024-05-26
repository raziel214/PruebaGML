package com.quimbaya.clientes_api.cliente.domain.port.out;

import java.util.Optional;
import java.util.List;
import com.quimbaya.clientes_api.cliente.domain.model.Cliente;

public interface ClienteRepositoryPort {
	
	Cliente save(Cliente cliente);
	Optional<Cliente> findById(Long id);
	Optional<Cliente> findByShareKey(String shareKey);
	List<Cliente> findAll();
	Optional<Cliente> update(Cliente cliente);
	boolean deleteById(Long id);

}
