package com.quimbaya.clientes_api.cliente.infraestructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClienteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "sharedKey", length = 50)
    private String sharedKey;
	
	@Column(name = "nombre", length = 50)
    private String nombre;
	
	@Column(name = "apellido", length = 50)
    private String apellido;
	
	@Column(name = "correo", length = 50)
    private String correo;
	
	@Column(name = "telefono", length = 50)
    private String telefono;
}
