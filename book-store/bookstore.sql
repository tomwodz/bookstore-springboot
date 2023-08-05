-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Lip 30, 2023 at 05:05 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tbook`
--

CREATE TABLE `tbook` (
  `id` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `author` varchar(256) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `quantity` int(6) NOT NULL,
  `isbn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbook`
--

INSERT INTO `tbook` (`id`, `title`, `author`, `price`, `quantity`, `isbn`) VALUES
(3, 'Java. Kompendium programisty. Wydanie XII', 'Java. Kompendium programisty. Wydanie XII', 119.00, 47, '978-83-832-2156-4'),
(4, 'Czysty kod. Podręcznik dobrego programisty', 'Czysty kod. Podręcznik dobrego programisty', 47.00, 37, '978-83-832-2344-5'),
(6, 'Java. Rusz głową! Wydanie III', 'Java. Rusz głową! Wydanie III', 95.00, 9, '978-83-283-9984-6'),
(8, 'Java. Efektywne programowanie. Wydanie III', 'Java. Efektywne programowanie. Wydanie III', 65.00, 59, '978-83-283-9896-2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torder`
--

CREATE TABLE `torder` (
  `id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total` decimal(6,2) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `torder`
--

INSERT INTO `torder` (`id`, `status`, `total`, `datetime`, `user_id`) VALUES
(2, 'NEW', 166.00, '2023-07-30 14:27:52', 1),
(3, 'NEW', 166.00, '2023-07-30 14:39:21', 1),
(4, 'NEW', 326.00, '2023-07-30 15:00:16', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torderposition`
--

CREATE TABLE `torderposition` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `torderposition`
--

INSERT INTO `torderposition` (`id`, `quantity`, `order_id`, `book_id`) VALUES
(1, 1, 2, 4),
(2, 1, 2, 3),
(3, 1, 3, 3),
(4, 1, 3, 4),
(5, 1, 4, 6),
(6, 1, 4, 3),
(7, 1, 4, 4),
(8, 1, 4, 8);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(40) NOT NULL,
  `password` varchar(33) NOT NULL,
  `name` varchar(40) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `email` varchar(80) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `password`, `name`, `surname`, `email`, `role`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Tomasz', 'Wodz', 'twodzinski@op.pl', 'ADMIN'),
(2, 'tomasz', '21232f297a57a5a743894a0e4a801fc3', 'Tomasz', 'Wodz', 'twodzisnki@op.pl', 'USER'),
(3, 'test', '098f6bcd4621d373cade4e832627b4f6', 'Tomasz', 'Wodz', 'twodzinski@op.pl', 'USER');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tbook`
--
ALTER TABLE `tbook`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `torder`
--
ALTER TABLE `torder`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `torderposition`
--
ALTER TABLE `torderposition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbook`
--
ALTER TABLE `tbook`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `torder`
--
ALTER TABLE `torder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `torderposition`
--
ALTER TABLE `torderposition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
