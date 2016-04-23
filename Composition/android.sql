-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Час створення: Квт 17 2016 р., 00:11
-- Версія сервера: 5.6.17
-- Версія PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База даних: `android`
--

-- --------------------------------------------------------

--
-- Структура таблиці `elimination`
--

CREATE TABLE IF NOT EXISTS `elimination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_answer` tinyint(1) NOT NULL,
  `taskId` int(11) NOT NULL,
  `variantId` int(11) NOT NULL,
  `reason` varchar(200) COLLATE utf8_bin NOT NULL,
  `sender` varchar(200) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблиці `recognition`
--

CREATE TABLE IF NOT EXISTS `recognition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `n1` int(11) NOT NULL,
  `n2` int(11) NOT NULL,
  `n3` int(11) NOT NULL,
  `n4` int(11) NOT NULL,
  `n5` int(11) NOT NULL,
  `sender` varchar(200) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Дамп даних таблиці `recognition`
--

INSERT INTO `recognition` (`id`, `n1`, `n2`, `n3`, `n4`, `n5`, `sender`, `time`) VALUES
(2, 11, 14, 11, 1, 0, '192.168.219.101', '2016-04-16 22:10:41');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
