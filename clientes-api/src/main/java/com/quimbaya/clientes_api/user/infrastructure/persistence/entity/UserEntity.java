package com.quimbaya.clientes_api.user.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "user_last_name", nullable = false, length = 100)
    private String userLastName;

    @Column(name = "user_email", nullable = false, unique = true, length = 255)
    private String userEmail;

    @Column(name = "user_pass", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;

    @Column(name = "state", nullable = false)
    private Short state;

    @Column(name = "`first`", nullable = false)
    private Short first;

    @Column(name = "recovery_pass", nullable = false)
    private Integer recoveryPass;
}
