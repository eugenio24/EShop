-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 07, 2019 alle 09:14
-- Versione del server: 10.1.37-MariaDB
-- Versione PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eshop`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `order_head`
--

CREATE TABLE `order_head` (
  `order_number` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `shipping_address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `order_row`
--

CREATE TABLE `order_row` (
  `id` int(11) NOT NULL,
  `idOrder` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `descrizione` varchar(500) DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `brand` int(11) DEFAULT NULL,
  `image_path` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `product_category`
--

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `is_main` tinyint(1) NOT NULL,
  `main_category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `psw_hash` varchar(100) DEFAULT NULL,
  `psw_salt` varchar(30) DEFAULT NULL,
  `registration_date` datetime NOT NULL,
  `registration_type` enum('NATIVE','GOOGLE') NOT NULL,
  `external_id` varchar(100) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `order_head`
--
ALTER TABLE `order_head`
  ADD PRIMARY KEY (`order_number`),
  ADD KEY `idUser_2` (`idUser`) USING BTREE;

--
-- Indici per le tabelle `order_row`
--
ALTER TABLE `order_row`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idOrder` (`idOrder`),
  ADD KEY `idProduct` (`idProduct`);

--
-- Indici per le tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category` (`category`) USING BTREE,
  ADD KEY `brand` (`brand`);

--
-- Indici per le tabelle `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `main_category` (`main_category`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `order_head`
--
ALTER TABLE `order_head`
  ADD CONSTRAINT `order_head_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `order_row`
--
ALTER TABLE `order_row`
  ADD CONSTRAINT `order_row_ibfk_1` FOREIGN KEY (`idOrder`) REFERENCES `order_head` (`order_number`),
  ADD CONSTRAINT `order_row_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`);

--
-- Limiti per la tabella `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category`) REFERENCES `product_category` (`id`);

--
-- Limiti per la tabella `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `main_category` FOREIGN KEY (`main_category`) REFERENCES `product_category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
