package com.quimbaya.clientes_api.cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	private Long id;
    private String sharedKey;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
}
