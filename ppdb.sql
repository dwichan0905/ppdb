-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 18 Jul 2019 pada 23.24
-- Versi server: 10.3.16-MariaDB
-- Versi PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ppdb`
--
CREATE DATABASE IF NOT EXISTS `ppdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ppdb`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa_baru`
--

CREATE TABLE `siswa_baru` (
  `nisn` varchar(20) NOT NULL,
  `namalengkap` varchar(100) NOT NULL,
  `jeniskelamin` char(1) NOT NULL,
  `tempatlhr` varchar(100) NOT NULL,
  `tanggallhr` date NOT NULL,
  `alamat` text NOT NULL,
  `agama` varchar(20) NOT NULL,
  `namaayah` varchar(100) NOT NULL,
  `pekerjaanayah` varchar(100) NOT NULL,
  `teleponayah` varchar(15) NOT NULL,
  `alamatayah` text NOT NULL,
  `namaibu` varchar(100) NOT NULL,
  `pekerjaanibu` varchar(100) NOT NULL,
  `teleponibu` varchar(15) NOT NULL,
  `alamatibu` text NOT NULL,
  `status` varchar(100) NOT NULL,
  `handphone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa_baru`
--

INSERT INTO `siswa_baru` (`nisn`, `namalengkap`, `jeniskelamin`, `tempatlhr`, `tanggallhr`, `alamat`, `agama`, `namaayah`, `pekerjaanayah`, `teleponayah`, `alamatayah`, `namaibu`, `pekerjaanibu`, `teleponibu`, `alamatibu`, `status`, `handphone`) VALUES
('00092784773', 'Nuryah Afni', 'P', 'Banjar', '2000-07-17', 'Dsn. Cadasgantung RT 04 RW 05', 'Islam', 'Entah', '-', '', '-', 'Suminah', 'Buruh', '', 'Dsn. Cadasgantung RT 04 RW 05', 'Anak kandung', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `nama` varchar(225) NOT NULL,
  `username` varchar(12) NOT NULL,
  `password` varchar(225) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`nama`, `username`, `password`, `email`) VALUES
('Dwi Candra Permana', '0009126835', '123456', 'dwichan@outlook.com'),
('Putut Sahbroe A', 'sahbroe', '123456', 'sahbroe45@gmail.com'),
('Tia Novianti Widiastuti', 'tianovianti', '123456', 'tianovianti@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `siswa_baru`
--
ALTER TABLE `siswa_baru`
  ADD PRIMARY KEY (`nisn`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
