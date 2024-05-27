-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2024 a las 06:16:56
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clientes_db`
--
CREATE DATABASE IF NOT EXISTS `clientes_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `clientes_db`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_entity`
--

CREATE TABLE `cliente_entity` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `shared_key` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente_entity`
--

INSERT INTO `cliente_entity` (`id`, `apellido`, `correo`, `nombre`, `shared_key`, `telefono`) VALUES
(1, 'Gomez', 'maria.gomez@example.com', 'Maria', 'key2', '555-5678'),
(52, 'Quintero', 'soulreavers214@gamil.com', 'Alex', 'key4', '3004756201');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_entity_seq`
--

CREATE TABLE `cliente_entity_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente_entity_seq`
--

INSERT INTO `cliente_entity_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `date_delete` datetime(6) DEFAULT NULL,
  `date_update` datetime(6) DEFAULT NULL,
  `first` smallint(6) NOT NULL,
  `recovery_pass` int(11) NOT NULL,
  `state` smallint(6) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_last_name` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_pass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `date_creation`, `date_delete`, `date_update`, `first`, `recovery_pass`, `state`, `user_email`, `user_last_name`, `user_name`, `user_pass`) VALUES
(1, '2023-10-31 20:25:51.000000', NULL, '2023-10-31 20:25:51.000000', 0, 0, 1, 'Soulreavers1214@gmail.com', 'Quimbaya Orozco', 'John Fredy', '$2a$10$xKYJdWso6ElZQdX18fvM7.tBCczIr8bRnMKuJ3iVHDA.0nlmcW.gW');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente_entity`
--
ALTER TABLE `cliente_entity`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_33uo7vet9c79ydfuwg1w848f` (`user_email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
