-- Flyway migration V1: initial schema for clientes-api.
-- Mirrors the JPA entities under com.quimbaya.clientes_api.{cliente,user}.infrastructure.persistence.entity

CREATE TABLE IF NOT EXISTS `users` (
    `user_id`        BIGINT       NOT NULL AUTO_INCREMENT,
    `user_name`      VARCHAR(100) NOT NULL,
    `user_last_name` VARCHAR(100) NOT NULL,
    `user_email`     VARCHAR(255) NOT NULL,
    `user_pass`      VARCHAR(255) NOT NULL,
    `date_creation`  DATETIME(6)  NOT NULL,
    `date_update`    DATETIME(6)  NULL,
    `date_delete`    DATETIME(6)  NULL,
    `state`          SMALLINT     NOT NULL,
    `first`          SMALLINT     NOT NULL,
    `recovery_pass`  INT          NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_users_user_email` (`user_email`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `cliente_entity` (
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `shared_key` VARCHAR(50)  NOT NULL,
    `nombre`     VARCHAR(50)  NOT NULL,
    `apellido`   VARCHAR(50)  NOT NULL,
    `correo`     VARCHAR(100) NOT NULL,
    `telefono`   VARCHAR(50)  NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_cliente_shared_key` (`shared_key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
