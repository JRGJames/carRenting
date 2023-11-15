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
  `brand` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `model` varchar(50) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `year` int NOT NULL,
  `transmission` varchar(20) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `fuel` varchar(20) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `doors` int NOT NULL,
  `seats` int NOT NULL,
  `color` varchar(20) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `hp` int NOT NULL,
  `image` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_user` bigint NOT NULL DEFAULT '1',
  `id_rental` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `car`
--

INSERT INTO `car` (`id`, `brand`, `model`, `year`, `transmission`, `fuel`, `doors`, `seats`, `color`, `hp`, `image`, `id_user`, `id_rental`) VALUES
(1, 'Audi', 'Fusion', 1950, 'Manual', 'Diesel', 2, 5, 'White', 56, 'car1.jpg', 2, 16),
(2, 'BMW', 'Malibu', 1993, 'Manual', 'Gasoline', 2, 5, 'Orange', 325, 'car4.jpg', 21, 16),
(3, 'Honda', 'Malibu', 1991, 'Automatic', 'Electric', 2, 5, 'Red', 183, 'car5.jpg', 21, 8),
(4, 'Toyota', 'Civic', 1963, 'Manual', 'Gasoline', 4, 2, 'Gray', 853, 'car1.jpg', 17, 7),
(5, 'Chevrolet', 'Elantra', 1934, 'Manual', 'Gasoline', 4, 5, 'Silver', 1027, 'car1.jpg', 5, 1),
(6, 'Ford', 'E-Class', 1933, 'Manual', 'Hybrid', 2, 4, 'Yellow', 498, 'car5.jpg', 21, 8),
(7, 'Chevrolet', 'Elantra', 1999, 'Automatic', 'Gasoline', 4, 2, 'White', 831, 'car1.jpg', 19, 15),
(8, 'Audi', 'Jetta', 1949, 'Automatic', 'Hybrid', 2, 2, 'Silver', 1076, 'car1.jpg', 14, 2),
(9, 'Ford', 'X5', 1971, 'Manual', 'Hybrid', 2, 5, 'Brown', 625, 'car1.jpg', 21, 8),
(10, 'BMW', 'Malibu', 1933, 'Manual', 'Electric', 4, 4, 'Yellow', 1003, 'car3.jpg', 21, 10),
(11, 'Honda', 'Fusion', 2005, 'Automatic', 'Gasoline', 2, 2, 'Orange', 722, 'car2.jpg', 18, 19),
(12, 'Toyota', 'E-Class', 1952, 'Automatic', 'Electric', 2, 2, 'Silver', 959, 'car3.jpg', 16, 13),
(13, 'Volkswagen', 'Civic', 1963, 'Manual', 'Electric', 2, 4, 'Red', 577, 'car2.jpg', 11, 15),
(14, 'Chevrolet', 'Camry', 1953, 'Manual', 'Electric', 4, 5, 'White', 950, 'car4.jpg', 20, 7),
(15, 'Hyundai', 'Jetta', 1954, 'Automatic', 'Diesel', 2, 2, 'Brown', 998, 'car2.jpg', 16, 16),
(16, 'BMW', 'X5', 1951, 'Manual', 'Electric', 4, 4, 'Black', 704, 'car5.jpg', 15, 13),
(17, 'Volkswagen', 'Camry', 2015, 'Manual', 'Gasoline', 2, 4, 'Gray', 387, 'car3.jpg', 6, 6),
(18, 'Audi', 'Elantra', 2001, 'Manual', 'Gasoline', 4, 4, 'Yellow', 545, 'car4.jpg', 7, 13),
(19, 'Mercedes-Benz', 'Camry', 2014, 'Automatic', 'Electric', 4, 5, 'White', 151, 'car1.jpg', 4, 18),
(20, 'Toyota', 'A4', 1939, 'Manual', 'Diesel', 4, 2, 'Blue', 783, 'car5.jpg', 18, 14);

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
-- Volcado de datos para la tabla `rental`
--

INSERT INTO `rental` (`id`, `pickup_date`, `dropoff_date`, `pickup_location`, `dropoff_location`, `cost`, `id_user`) VALUES
(1, '2023-11-27 17:40:38', '2023-12-17 17:40:38', 'Airport', 'Train Station', 257.805, 1),
(2, '2023-11-15 17:40:38', '2023-11-20 17:40:38', 'Airport', 'Train Station', 355.489, 1),
(3, '2023-11-16 17:40:38', '2023-12-16 17:40:38', 'Downtown', 'Hotel', 98.1471, 1),
(4, '2023-11-17 17:40:38', '2023-12-08 17:40:38', 'Airport', 'Airport', 410.082, 1),
(5, '2023-11-17 17:40:38', '2023-12-01 17:40:38', 'Train Station', 'Airport', 478.666, 1),
(6, '2023-11-22 17:40:38', '2023-12-14 17:40:38', 'Airport', 'Train Station', 119.092, 1),
(7, '2023-11-24 17:40:38', '2023-12-18 17:40:38', 'Train Station', 'Train Station', 403.072, 1),
(8, '2023-11-27 17:40:38', '2023-12-03 17:40:38', 'Airport', 'Suburb', 438.411, 1),
(9, '2023-11-28 17:40:38', '2023-12-08 17:40:38', 'Suburb', 'Hotel', 348.024, 1),
(10, '2023-11-21 17:40:38', '2023-11-24 17:40:38', 'Downtown', 'Suburb', 459.086, 1),
(11, '2023-11-27 17:40:38', '2023-12-11 17:40:38', 'Hotel', 'Airport', 235.794, 1),
(12, '2023-11-28 17:40:38', '2023-12-21 17:40:38', 'Airport', 'Airport', 122.775, 1),
(13, '2023-11-17 17:40:38', '2023-12-17 17:40:38', 'Airport', 'Hotel', 262.769, 1),
(14, '2023-11-25 17:40:38', '2023-12-11 17:40:38', 'Suburb', 'Suburb', 429.271, 1),
(15, '2023-11-23 17:40:38', '2023-12-10 17:40:38', 'Airport', 'Train Station', 66.7095, 1),
(16, '2023-11-25 17:40:38', '2023-12-22 17:40:38', 'Train Station', 'Hotel', 242.062, 1),
(17, '2023-11-17 17:40:38', '2023-11-22 17:40:38', 'Train Station', 'Airport', 69.216, 1),
(18, '2023-11-22 17:40:38', '2023-11-25 17:40:38', 'Train Station', 'Downtown', 322.561, 1),
(19, '2023-11-21 17:40:38', '2023-12-21 17:40:38', 'Airport', 'Hotel', 238.132, 1),
(20, '2023-11-20 17:40:38', '2023-12-16 17:40:38', 'Train Station', 'Suburb', 227.995, 1);

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
(2, 'Fernando', 'Alonso', '601447829', 'pepegarcia@gmail.com', 'C/ El Nano', 'Oviedo', 'Asturias', '46033', 'Spain', 'elNano', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(3, 'Eloy', 'Alcañiz', '6099096240', 'javaMaster@hotmail.com', '515 Willow Rd', 'San Antonio', 'North Carolina', '14177', 'Germany', 'codeNinja', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(4, 'Paula', 'López', '6001402319', 'dataGeek@example.com', '601 Main Ave', 'Philadelphia', 'Florida', '45065', 'Japan', 'codeSlinger', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(5, 'Sergio', 'Muñoz', '6079778769', 'gamerGirl@outlook.com', '290 Pine Ave', 'Los Angeles', 'Ohio', '71089', 'United States', 'user123', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(6, 'Raquel', 'Cano', '6084368987', 'bugHunter@example.com', '429 Oak Ct', 'New York', 'Pennsylvania', '07986', 'Brazil', 'scriptHero', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(7, 'Elena', 'Serrano', '6023586227', 'bugHunter@example.com', '144 Elm Ln', 'Los Angeles', 'Florida', '49947', 'France', 'codeCruncher', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(8, 'Paula', 'Ferrer', '6035322122', 'byteBender@example.com', '696 Maple Ave', 'Chicago', 'Texas', '13325', 'Australia', 'codeCruncher', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(9, 'Sergio', 'Serrano', '6079532877', 'cryptoCoder@outlook.com', '206 Holly Rd', 'San Jose', 'New York', '97158', 'Brazil', 'codeNinja', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(10, 'Jaime', 'Suárez', '6016865344', 'coolCoder@gmail.com', '603 Elm Rd', 'Chicago', 'Georgia', '82679', 'Australia', 'coolCoder', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(11, 'Sergio', 'Ayala', '6066989165', 'techExplorer@gmail.com', '946 Pine Dr', 'San Diego', 'Georgia', '91699', 'China', 'cryptoCoder', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(12, 'Pablo', 'Maldonado', '6058717918', 'byteBender@gmail.com', '946 Birch Blvd', 'Chicago', 'New York', '43440', 'China', 'cryptoCoder', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(13, 'Mónica', 'Aguilar', '6035940014', 'pixelPirate@hotmail.com', '646 Willow Pl', 'Phoenix', 'Texas', '71531', 'United States', 'webDevPro', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(14, 'Alan', 'Farell', '6089162953', 'codeSlinger@example.com', '723 Birch Rd', 'San Diego', 'Illinois', '58635', 'United Kingdom', 'devGuru', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(15, 'Eva', 'Quilez', '6031498823', 'codingWizard@outlook.com', '136 Holly Ln', 'New York', 'Michigan', '80202', 'Japan', 'dataGeek', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(16, 'Jaime', 'Muñoz', '6052120149', 'javaJunkie@example.com', '594 Poplar Ct', 'Phoenix', 'Illinois', '20386', 'United States', 'debuggerKing', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(17, 'Fernando', 'Muñoz', '6081358017', 'codeWarrior@outlook.com', '186 Cedar Ln', 'San Antonio', 'North Carolina', '66549', 'France', 'dataGeek', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(18, 'José Antonio', 'Aparici', '6038678136', 'user123@example.com', '418 Poplar Ave', 'Chicago', 'Florida', '59591', 'France', 'user123', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(19, 'Eva', 'Fabra', '6012169807', 'dataGeek@hotmail.com', '677 Elm Pl', 'San Diego', 'New York', '05594', 'Canada', 'gamerGirl', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(20, 'Eva', 'Lara', '6077279320', 'devGuru@yahoo.com', '576 Willow St', 'Houston', 'Georgia', '03093', 'Australia', 'codeWarrior', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(21, 'Fernando', 'Aznar', '6083184007', 'byteBender@hotmail.com', '634 Main Pl', 'Los Angeles', 'Ohio', '50156', 'Japan', 'cryptoCoder', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1),
(22, 'José Antonio', 'Aguilar', '6072293665', 'codeWarrior@yahoo.com', '663 Birch St', 'New York', 'Ohio', '36304', 'China', 'codeNinja', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 1);

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
-- Indices de la tabla `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKryptlngl3muielublte2tryx0` (`id_user`);

--
-- Indices de la tabla `user`
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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

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
