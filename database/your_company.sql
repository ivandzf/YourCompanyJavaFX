-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16 Sep 2017 pada 12.16
-- Versi Server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `your_company`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE IF NOT EXISTS `login` (
`id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `level` enum('Admin','Operator','User') NOT NULL,
  `status` enum('Login','Tidak Login') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `nama`, `email`, `level`, `status`) VALUES
(1, 'admin', 'admin', 'Divananda Zikry Fadilla', 'admin@gmail.com', 'Admin', 'Tidak Login'),
(2, 'ivan', 'ivan', 'icut', 'divanandazf@gmail.com', 'Operator', 'Tidak Login'),
(3, 'mila', 'milamonika', 'Mila Monika Berlyana', 'mila@mila.com', 'User', 'Tidak Login');

-- --------------------------------------------------------

--
-- Struktur dari tabel `uang_keluar`
--

CREATE TABLE IF NOT EXISTS `uang_keluar` (
`id` int(11) NOT NULL,
  `detail` varchar(50) NOT NULL,
  `uang_keluar` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu_input` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `uang_keluar`
--

INSERT INTO `uang_keluar` (`id`, `detail`, `uang_keluar`, `tanggal`, `waktu_input`) VALUES
(1, 'Listrik', 150000, '2017-01-01', '2017-08-27 15:16:08'),
(2, 'Surya', 50000, '2017-01-01', '2017-08-27 15:16:13'),
(3, 'Ato', 20000, '2017-01-01', '2017-08-27 15:16:18'),
(4, 'Sarapan', 25000, '2017-01-01', '2017-08-27 15:16:22'),
(5, 'Pulsa Bu Alit', 19000, '2017-01-01', '2017-08-27 15:16:29'),
(6, 'Sarapan', 25000, '2017-02-01', '2017-08-27 15:25:47'),
(7, 'Gas 2', 40000, '2017-02-01', '2017-08-27 15:25:51'),
(8, 'Usman', 50000, '2017-02-01', '2017-08-27 15:25:55'),
(9, 'Pa jupri servis mio', 82000, '2017-02-01', '2017-08-27 15:26:01'),
(10, 'ato', 50000, '2017-02-01', '2017-08-27 15:26:05'),
(11, 'atk', 50000, '2017-02-01', '2017-08-27 15:26:10'),
(12, 'listrik ato', 140000, '2017-02-01', '2017-08-27 15:26:14'),
(13, 'Beli gorengan', 10000, '2017-08-28', '2017-08-28 11:21:26'),
(14, 'Beli es', 5000, '2017-08-28', '2017-08-28 11:21:35'),
(15, 'Beli laptop', 4000000, '2017-08-01', '2017-08-28 11:22:03'),
(16, 'Beli galon', 15300, '2017-08-27', '2017-08-28 11:35:18'),
(17, 'Beli nasi', 5000, '2017-08-27', '2017-08-28 11:35:26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `uang_masuk`
--

CREATE TABLE IF NOT EXISTS `uang_masuk` (
`id_uang_masuk` int(11) NOT NULL,
  `detail` varchar(50) NOT NULL,
  `debit` int(11) NOT NULL,
  `kredit` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu_input` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `uang_masuk`
--

INSERT INTO `uang_masuk` (`id_uang_masuk`, `detail`, `debit`, `kredit`, `jenis`, `tanggal`, `waktu_input`) VALUES
(1, 'Tiara', 2022000, 2022000, 'Bakul', '2017-01-01', '2017-08-27 14:31:53'),
(2, 'NN', 605000, 605000, 'Bakul', '2017-01-01', '2017-08-27 14:32:00'),
(3, 'Yuyun', 120000, 120000, 'Bakul', '2017-01-01', '2017-08-27 14:32:09'),
(4, 'Samini', 58000, 58000, 'Bakul', '2017-01-01', '2017-08-27 15:10:20'),
(5, 'Takana', 58000, 58000, 'Bakul', '2017-01-01', '2017-08-27 15:10:29'),
(6, 'Echo', 1050000, 1050000, 'Bakul', '2017-01-01', '2017-08-27 15:10:35'),
(7, 'Kholis', 11000, 11000, 'Bakul', '2017-01-01', '2017-08-27 15:10:42'),
(8, 'Aris', 930000, 930000, 'Bakul', '2017-01-01', '2017-08-27 15:10:47'),
(9, 'Jupri', 81000, 81000, 'Bakul', '2017-01-01', '2017-08-27 15:10:53'),
(10, 'Raso', 175000, 175000, 'Bakul', '2017-01-01', '2017-08-27 15:10:58'),
(11, 'Mila', 188000, 188000, 'Bakul', '2017-01-01', '2017-08-27 15:11:03'),
(12, 'Juju', 780000, 780000, 'Bakul', '2017-01-01', '2017-08-27 15:11:11'),
(13, 'Heri', 484000, 484000, 'Bakul', '2017-01-01', '2017-08-27 15:11:18'),
(14, 'Sukron', 884000, 884000, 'Bakul', '2017-01-01', '2017-08-27 15:11:24'),
(15, 'Maman', 163000, 163000, 'Bakul', '2017-01-01', '2017-08-27 15:11:31'),
(16, 'Mumu', 220000, 220000, 'Bakul', '2017-01-01', '2017-08-27 15:11:35'),
(17, 'Mus', 99000, 99000, 'Bakul', '2017-01-01', '2017-08-27 15:11:41'),
(18, 'Amaris', 290000, 290000, 'Hotel', '2017-01-01', '2017-08-27 15:11:49'),
(19, 'Royal', 180000, 180000, 'Hotel', '2017-01-01', '2017-08-27 15:12:01'),
(20, 'AFC', 318000, 318000, 'Hotel', '2017-01-01', '2017-08-27 15:12:12'),
(21, 'Suniah', 280000, 280000, 'Bakul', '2017-02-01', '2017-08-27 15:22:48'),
(22, 'Mie Ayam', 64000, 64000, 'Bakul', '2017-02-01', '2017-08-27 15:22:54'),
(23, 'Parti', 26000, 26000, 'Bakul', '2017-02-01', '2017-08-27 15:22:58'),
(24, 'NN', 40000, 40000, 'Bakul', '2017-02-01', '2017-08-27 15:23:03'),
(25, 'Merbabu', 260000, 260000, 'Bakul', '2017-02-01', '2017-08-27 15:23:08'),
(26, 'Nasi Kucing', 22000, 22000, 'Bakul', '2017-02-01', '2017-08-27 15:23:15'),
(27, 'Kantin Prima', 54000, 54000, 'Bakul', '2017-02-01', '2017-08-27 15:23:20'),
(28, 'Topik', 100000, 100000, 'Bakul', '2017-02-01', '2017-08-27 15:23:24'),
(29, 'Raso', 160000, 160000, 'Bakul', '2017-02-01', '2017-08-27 15:23:29'),
(30, 'Rambo', 189000, 189000, 'Bakul', '2017-02-01', '2017-08-27 15:23:33'),
(31, 'Mus', 93000, 93000, 'Bakul', '2017-02-01', '2017-08-27 15:23:38'),
(32, 'Inah', 55000, 55000, 'Bakul', '2017-02-01', '2017-08-27 15:23:41'),
(33, 'Rumin', 80000, 80000, 'Bakul', '2017-02-01', '2017-08-27 15:23:45'),
(34, 'Mamat', 414000, 414000, 'Bakul', '2017-02-01', '2017-08-27 15:23:49'),
(35, 'Maman', 144000, 144000, 'Bakul', '2017-02-01', '2017-08-27 15:23:54'),
(36, 'Mumu', 184000, 184000, 'Bakul', '2017-02-01', '2017-08-27 15:23:58'),
(37, 'Heri', 300000, 0, 'Bakul', '2017-02-01', '2017-08-27 15:24:03'),
(38, 'Tiara', 1190000, 1190000, 'Bakul', '2017-02-01', '2017-08-27 15:24:10'),
(39, 'Aris', 949000, 949000, 'Bakul', '2017-02-01', '2017-08-27 15:24:17'),
(40, 'Sukron', 291000, 291000, 'Bakul', '2017-02-01', '2017-08-27 15:24:23'),
(41, 'Cindy  SMAK', 228000, 228000, 'Bakul', '2017-02-01', '2017-08-27 15:24:33'),
(42, 'Yanto SMAK', 252000, 252000, 'Bakul', '2017-02-01', '2017-08-27 15:24:41'),
(43, 'Juju', 509000, 509000, 'Bakul', '2017-02-01', '2017-08-27 15:24:45'),
(44, 'AFC', 552000, 552000, 'Hotel', '2017-02-01', '2017-08-27 15:25:00'),
(45, 'Amaris', 130000, 130000, 'Hotel', '2017-02-01', '2017-08-27 15:25:05'),
(46, 'Neo', 573000, 573000, 'Hotel', '2017-02-01', '2017-08-27 15:25:09'),
(47, 'Pendekar', 126000, 126000, 'Hotel', '2017-02-01', '2017-08-27 15:25:13'),
(54, 'Juju', 280000, 280000, 'Bakul', '2017-08-28', '2017-08-28 01:02:36'),
(55, 'Agus', 280000, 280000, 'Bakul', '2017-08-28', '2017-08-28 01:02:48'),
(56, 'Angga', 500000, 100000, 'Bakul', '2017-08-28', '2017-08-28 01:02:56'),
(57, 'Jojon', 500000, 500000, 'Hotel', '2017-08-28', '2017-08-28 01:24:55'),
(59, 'Angga', 500000, 500000, 'Bakul', '2017-08-27', '2017-08-28 11:34:18'),
(60, 'Udin', 120000, 100000, 'Hotel', '2017-08-27', '2017-08-28 11:34:30'),
(61, 'udin', 500000, 500000, 'Bakul', '2017-08-29', '2017-08-29 13:46:53');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uang_keluar`
--
ALTER TABLE `uang_keluar`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uang_masuk`
--
ALTER TABLE `uang_masuk`
 ADD PRIMARY KEY (`id_uang_masuk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `uang_keluar`
--
ALTER TABLE `uang_keluar`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `uang_masuk`
--
ALTER TABLE `uang_masuk`
MODIFY `id_uang_masuk` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=62;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
