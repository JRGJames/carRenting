-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 19-11-2023 a las 14:14:48
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE IF NOT EXISTS car_renting;

-- Selección de la base de datos
USE car_renting;

--
-- Estructura de tabla para la tabla `car`
--

CREATE TABLE `car` (
  `id` bigint NOT NULL,
  `brand` varchar(255) COLLATE utf16_unicode_ci NOT NULL,
  `model` varchar(255) COLLATE utf16_unicode_ci NOT NULL,
  `year` int NOT NULL,
  `available` bigint DEFAULT '0',
  `id_user` int NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `car`
--

INSERT INTO `car` (`id`, `brand`, `model`, `year`, `available`, `id_user`) VALUES
(1, 'BMW', 'X5', 2016, 1, 1),
(2, 'Audi', 'A4', 2013, 0, 1),
(3, 'Opel', 'Corsa', 2010, 0, 1);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rental`
--

CREATE TABLE `rental` (
  `id` bigint NOT NULL,
  `id_car` bigint NOT NULL,
  `id_user` bigint NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `price` DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `rental`
--

INSERT INTO `rental` (`id`, `id_car`, `id_user`, `start_date`, `end_date`, `price`) VALUES
(1, 1, 1, '2004-12-05', '2021-02-10', 30.3),
(2, 3, 2, '2004-11-22', '2005-07-17', 33.3),
(3, 2, 1, '2013-04-04', '2014-09-27', 60.0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `firstname` varchar(100) COLLATE utf16_unicode_ci NOT NULL,
  `lastname` varchar(100) COLLATE utf16_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf16_unicode_ci NOT NULL,
  `username` varchar(100) COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `email`, `username`, `password`, `role`) VALUES
(1, 'Winston', 'Scott', 'winscott@continental.com', 'winscott', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 1),
(2, 'John', 'Wick', 'babayaga@continental.com', 'babayaga', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(3, 'José Antonio', 'Navarro', 'JosNav0@ausiasmarch.net', 'Josav0', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(4, 'Rafael', 'Aguilar', 'RafAgu99@ausiasmarch.net', 'Rafgu99', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `loans`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rental_car` (`id_car`),
  ADD KEY `fk_rental_user` (`id_user`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
