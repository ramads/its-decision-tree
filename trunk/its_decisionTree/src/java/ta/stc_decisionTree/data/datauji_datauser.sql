-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2014 at 11:09 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tugasakhir`
--

-- --------------------------------------------------------

--
-- Table structure for table `datauser`
--

CREATE TABLE IF NOT EXISTS `datauser` (
  `iduser` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `datauser`
--

INSERT INTO `datauser` (`iduser`, `name`, `password`) VALUES
('ranin', 'ranin', 'ranin'),
('F1B009037', 'Nurmutmainnah', 'opened'),
('F1B008083', 'arifzorro', 'tidaktau'),
('f1b009069', 'adzin7', 'andi7'),
('F1D012052', 'RONIHIDAYAT', 'F1D012052'),
('F1B008085', 'devacyber08', 'devacyber'),
('F1B006028', 'Haerul', 'arifgagah'),
('F1B009027', 'septa', 'septa'),
('sariIsmi', 'sariIsmi', 'sariIsmi'),
('F1D012066', 'MARLIN', 'F1D012066'),
('dbudy', 'Budi Pratama Putra', 'sithajegeg'),
('f1b009021', 'sikoko', 'sikoko'),
('daniel', 'daniel', 'daniel'),
('F1B008011', 'Ramaditia DS', 'ramads'),
('F1D013023', 'Diaz Guntur Febrian', 'cobacoba'),
('F1D013009', 'annisa', '123456789'),
('F1D013095', 'Tini Maharani', 'ranias27'),
('F1D013057', 'Liza Yuliana Khairani', 'l1z4yul1'),
('f1d013043', 'ikhwan arif', 'elina123'),
('F1D013029', 'FIENA EFLIANA', 'Black123'),
('F1D013097', 'WAHDA FARAH BATARI', 'F1D013097'),
('F1D013079', 'NITA ISRA'' S', 'JAVAJAVA'),
('f1d013047', 'indah', '34567'),
('F1D013073', 'NAZIBULLAH', 'keparat'),
('F1D013077', 'Ni Wayan Eka Widiyani', 'ayanaeka'),
('F1D013093', 'ROSITA ANDREANI', 'ita95'),
('F1D013033', 'hankam', 'HANKAM'),
('F1D013001', 'Abdul Chalel Rahman', 'madrid'),
('f1d013085', 'Panji', 'papoy'),
('F1D013075', 'ni komang karliani', 'komang'),
('F1D013061', 'MUAMAR IKHSAN', '19realmadrid'),
('F1D013015', 'Nurul', '19c32'),
('F1D013049', 'isni fachri rizal', '12345'),
('F1D013003', 'Affandy Akbar', 'bismillah'),
('f1d013099', 'yudha', 'takamina'),
('F1D013059', 'Rizwan', 'h2love'),
('jeki378', 'muhammad soadkin', '140796'),
('F1D013067', 'MUSTIANTI', 'informatika'),
('F1D013087', 'PUSPITA ASRI NURMALASARI', 'informatika'),
('f1d013091', 'riska', 'kafaris'),
('f1d013069', 'nadia noviyanti bakary', 'koalisitotal'),
('F1D013031', 'FITRAH AMALIA', '12345'),
('f1d013045', 'ilham', 'hamnya'),
('F1D013041', 'sarwe', '54rw35'),
('F1D013005', 'Ahmad Patoni', 'jklasd'),
('f1d013025', 'dinda bahari putri', '12345'),
('F1D013089', 'Ramlah Nurlaeli', 'F1D013089'),
('F1D013027', 'Evandry Syaputra', 'malang'),
('F1D0130300', 'FITRAH AMALIA', '12345'),
('F1D013017', 'Bq.suci septiati', 'suci123'),
('F1D013055', 'LIA RAMDHANI', 'b3acd'),
('f1d013021', 'daning', '12345'),
('F1D013071', 'naningsih', '08081995'),
('f1d013007', 'anang', 'benar'),
('F1D013042', 'azmi yunda chairani', '01031993'),
('F1D013012', 'AZMI YUNDA CHAIRANI', '12345');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
