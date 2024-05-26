package com.quimbaya.clientes_api.usuario.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private Long user_id;
	private String userName;
	private String userLastName;
	private String userEmail;
	private String userPass;
    private Long    userRolId;
    private Date    dateCreation;
    private Date    dateUpdate;
    private Date    dateDelete;
    private Short   state;
    private Short   first;
    private Integer recoveryPass;
    private Long 	 ciudadId;
}
