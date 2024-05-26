package com.quimbaya.clientes_api.user.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users{   
	private Long user_id;
	private String userName;
	private String userLastName;
	private String userEmail;
	private String userPass;
    private Long    userRolId; // Considerando que quieres almacenar solo el ID del rol.
    private Date    dateCreation;
    private Date    dateUpdate;
    private Date    dateDelete;
    private Short   state;  // Usamos Short dado que mencionaste "smallint".
    private Short   first;   // De igual manera, usamos Short para "smallint".
    private Integer recoveryPass;
    private Long 	 ciudadId;
   
}
