-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 13-11-2023 a las 10:54:07
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.11

CREATE DATABASE IF NOT EXISTS car_renting;

-- Volver a seleccionar la base de datos
USE car_renting;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `car_renting`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `car`
--

CREATE TABLE `car` (
  `id` bigint NOT NULL,
  `brand` varchar(50) COLLATE utf16_unicode_ci NOT NULL,
  `model` varchar(50) COLLATE utf16_unicode_ci NOT NULL,
  `year` int NOT NULL,
  `transmission` varchar(20) COLLATE utf16_unicode_ci NOT NULL,
  `fuel` varchar(20) COLLATE utf16_unicode_ci NOT NULL,
  `doors` int NOT NULL,
  `seats` int NOT NULL,
  `color` varchar(20) COLLATE utf16_unicode_ci NOT NULL,
  `hp` int NOT NULL,
  `image` varchar(255) COLLATE utf16_unicode_ci NOT NULL,
  `id_user` bigint NOT NULL DEFAULT '1',
  `id_rental` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `phone_number` varchar(20) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `province` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `postal_code` varchar(10) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `country` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `phone_number`, `email`, `address`, `city`, `province`, `postal_code`, `country`, `username`, `password`, `role`) VALUES
(1, 'Fernando', 'Alonso', '333333333', 'fa@gmail.com', 'c/el nano', 'Oviedo', 'Asturias', '46033', 'Spain', 'fernandoalo_oficial', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 0),
(2, 'Fernando', 'Alonso', '601447829', 'pepegarcia@gmail.com', 'C/ El Nano', 'Oviedo', 'Asturias', '46033', 'Spain', 'elNano', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rental`
--

CREATE TABLE `rental` (
  `id` bigint NOT NULL,
  `pickup_date` datetime NOT NULL,
  `dropoff_date` datetime NOT NULL,
  `pickup_location` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `dropoff_location` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `cost` float NOT NULL,
  `id_user` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrve1w0o6l1o26x5ysd5s7xken` (`id_user`),
  ADD KEY `FK4qmdr0717tpjgxegh9j6n1xnl` (`id_rental`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKryptlngl3muielublte2tryx0` (`id_user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `FK4qmdr0717tpjgxegh9j6n1xnl` FOREIGN KEY (`id_rental`) REFERENCES `rental` (`id`),
  ADD CONSTRAINT `FKrve1w0o6l1o26x5ysd5s7xken` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FKryptlngl3muielublte2tryx0` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;