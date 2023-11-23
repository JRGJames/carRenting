-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 23-11-2023 a las 06:22:35
-- Versión del servidor: 10.9.8-MariaDB-1:10.9.8+maria~ubu2204
-- Versión de PHP: 8.2.12

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
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `id_user` int(11) NOT NULL DEFAULT 1,
  `price_day` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `car`
--

INSERT INTO `car` (`id`, `brand`, `model`, `year`, `id_user`, `price_day`) VALUES
(1, 'Chevrolet', 'Altima', 2006, 10, 59),
(2, 'Volkswagen', 'Camry', 1976, 8, 101),
(3, 'Volkswagen', 'Jetta', 2018, 6, 67),
(4, 'Ford', 'Altima', 1955, 12, 45),
(5, 'Volkswagen', 'Fusion', 1980, 9, 141),
(6, 'Volkswagen', 'E-Class', 1990, 9, 177),
(7, 'Nissan', 'Elantra', 2010, 8, 55),
(8, 'Mercedes-Benz', 'Malibu', 1960, 1, 190),
(9, 'BMW', 'Jetta', 1982, 5, 40),
(10, 'Ford', 'E-Class', 1930, 5, 30),
(11, 'BMW', 'Altima', 1942, 5, 70),
(12, 'BMW', 'Jetta', 2018, 7, 37),
(13, 'Ford', 'A4', 1974, 12, 68),
(14, 'Honda', 'A4', 2004, 4, 120),
(15, 'BMW', 'X5', 1945, 10, 78),
(16, 'Ford', 'Altima', 1979, 8, 133),
(17, 'Ford', 'A4', 1977, 3, 57),
(18, 'Honda', 'Altima', 2002, 10, 61),
(19, 'Audi', 'X5', 1982, 8, 100),
(20, 'Nissan', 'A4', 2011, 2, 127),
(21, 'Chevrolet', 'Jetta', 1974, 6, 127),
(22, 'Chevrolet', 'A4', 1997, 2, 42),
(23, 'Ford', 'Civic', 1940, 5, 39),
(24, 'Honda', 'X5', 2005, 11, 110),
(25, 'Honda', 'Camry', 2012, 11, 60),
(26, 'Toyota', 'Camry', 1941, 1, 126),
(27, 'Audi', 'Malibu', 1960, 4, 32),
(28, 'BMW', 'X5', 1956, 7, 168),
(29, 'Honda', 'Elantra', 1951, 11, 69),
(30, 'Ford', 'A4', 1933, 8, 117),
(31, 'Toyota', 'Altima', 1968, 5, 77),
(32, 'Chevrolet', 'X5', 2011, 1, 107),
(33, 'Honda', 'Fusion', 1952, 2, 113),
(34, 'Toyota', 'A4', 1969, 3, 121),
(35, 'Nissan', 'E-Class', 1983, 9, 133),
(36, 'Nissan', 'X5', 2015, 9, 77),
(37, 'Chevrolet', 'Elantra', 1956, 12, 111),
(38, 'Chevrolet', 'Camry', 1980, 8, 106),
(39, 'Ford', 'Altima', 1965, 5, 188),
(40, 'Chevrolet', 'Civic', 1992, 1, 193),
(41, 'Nissan', 'Fusion', 1955, 12, 136),
(42, 'Ford', 'Elantra', 1944, 5, 133),
(43, 'Hyundai', 'A4', 1947, 8, 172),
(44, 'BMW', 'Civic', 1988, 1, 54),
(45, 'BMW', 'Malibu', 1980, 5, 126),
(46, 'Mercedes-Benz', 'Altima', 1980, 11, 176),
(47, 'Mercedes-Benz', 'Altima', 1975, 2, 155),
(48, 'Audi', 'Civic', 1983, 6, 49),
(49, 'Toyota', 'A4', 1968, 10, 159),
(50, 'Chevrolet', 'Elantra', 2009, 5, 176);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rental`
--

CREATE TABLE `rental` (
  `id` bigint(20) NOT NULL,
  `id_car` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `rental`
--

INSERT INTO `rental` (`id`, `id_car`, `id_user`, `start_date`, `end_date`) VALUES
(1, 33, 10, '2023-12-02', '2023-12-18'),
(2, 24, 11, '2023-11-29', '2023-12-23'),
(3, 15, 4, '2023-12-04', '2024-01-02'),
(4, 21, 12, '2023-11-24', '2023-12-10'),
(5, 38, 3, '2023-12-02', '2023-12-09'),
(6, 48, 7, '2023-11-23', '2023-12-13'),
(7, 8, 1, '2023-12-04', '2023-12-23'),
(8, 18, 10, '2023-12-06', '2023-12-22'),
(9, 15, 2, '2023-11-28', '2023-12-04'),
(10, 26, 5, '2023-11-27', '2023-12-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `email`, `username`, `password`, `role`) VALUES
(1, 'Carlos', 'Sainz', 'cs@gmail.com', 'carlossainz55', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(2, 'Fernando', 'Alonso', 'fa@gmail.com', 'fernandoalo', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 1),
(3, 'José Antonio', 'McLure', 'javaMaster@example.com', 'byteBender', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(4, 'Raquel', 'Alcañiz', 'devGuru@outlook.com', 'user123', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(5, 'Mónica', 'Roselló', 'codeSlinger@yahoo.com', 'byteBender', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(6, 'Sergio', 'Ayala', 'javaJunkie@example.com', 'codeSlinger', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(7, 'Alan', 'Roselló', 'javaJunkie@gmail.com', 'devGuru', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(8, 'Mónica', 'Suárez', 'codingWizard@yahoo.com', 'cryptoCoder', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(9, 'Rafael', 'Aznar', 'dataGeek@gmail.com', 'byteBender', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(10, 'Alan', 'Gómez', 'bugHunter@example.com', 'codeSlinger', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(11, 'Sergio', 'García', 'devGuru@gmail.com', 'techExplorer', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0),
(12, 'Lucas', 'Aznar', 'byteBender@hotmail.com', 'pixelPirate', '05c34c3e0cb0ad7a7a8912f17b270d6f30dd22b568c3920d5a68066e4e96a26e', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rental_car` (`id_car`),
  ADD KEY `fk_rental_user` (`id_user`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `rental`
--
ALTER TABLE `rental`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
