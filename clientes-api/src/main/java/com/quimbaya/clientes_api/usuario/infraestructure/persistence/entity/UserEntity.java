package com.quimbaya.clientes_api.usuario.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")  
public class UserEntity {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long user_id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userLastName;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @JsonIgnore
    @Column(nullable = false)
    private String userPass;


    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @Column
    private LocalDateTime dateUpdate;

    @Column
    private LocalDateTime dateDelete;

    @Column(nullable = false)
    private Short state;

    @Column(nullable = false)
    private Short first;

    @Column(nullable = false)
    private Integer recoveryPass;
    
}
