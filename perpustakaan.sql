-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 18, 2020 at 10:45 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `username`, `password`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'Abizar Deriana', 'abizar1', 'gotoxy'),
(3, 'Farhan Afson', 'farhanaf', 'kerinci123'),
(4, 'Permata Ariani', 'permariani12', 'akuncatik12'),
(5, 'Muhammad Arifin', 'arifinmuh14', 'bandungjuara'),
(6, 'Syamsu Rizal', 'naruto133', 'sasuke19'),
(7, 'Ardian Samsudin', 'summerblack', 'kotabaru11'),
(8, 'Fetiana Rahmisyam', 'rahmianafeti12', 'persib1933'),
(9, 'Rifqi Gates', 'gatesrifqi111', 'micorosoft10'),
(10, 'Farhan Muhammad', 'muhfarhan12', 'akuganteng12'),
(11, 'Yanuar Wijaya', 'wijayanuar77', 'indonesiapintar'),
(12, 'Fauzan Pratama', 'fauzan11', 'kujangbandung'),
(13, 'Annisa Tantro', 'tannisalisa', 'travelermania11'),
(14, 'Wanda Simalungun', 'simalungun22', 'simalungunjaya12'),
(15, 'Marshela Winata', 'mockingbird77', 'flyingbird51'),
(16, 'Amanda Trintania', 'taniamanda00', 'akusukakucing99'),
(17, 'Thania Syarifah', 'indonesiakeren', 'jayalahnegeriku'),
(18, 'Ibnu Nasrul', 'nasrullahibnu', 'duniaimaji44'),
(19, 'Wiranata Azmi', 'aezakmi22', 'rockismymusic'),
(20, 'Sarah Afifah', 'sarchu22', 'sarahlucu22'),
(21, 'Indah Permata', 'bandungindah', 'dancer123'),
(22, 'Malik Al Hasan', 'hasanalmalik89', 'iwanfalsoi11'),
(23, 'Tanasha Maria', 'marianasha', 'ilovemusic'),
(24, 'Syarif Farizi', 'alsyarifalfarizi', 'syarifganteng78'),
(25, 'Kirana Lisa', 'lisakirana789', 'backtojakarta00');

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id_anggota` int(11) NOT NULL,
  `nama_anggota` varchar(50) NOT NULL,
  `no_tlp` varchar(15) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id_anggota`, `nama_anggota`, `no_tlp`, `alamat`, `username`, `password`, `status`) VALUES
(1, 'Fuaza Dwifalah', '081182462727', 'Jln Raya Cileunyi No 945', 'aneng2001', 'anengarifah1', 'AKTIF'),
(2, 'Reihan Naufal Muntaza', '082127788165', 'Jalan Raya Cibiru Indah No. 17', 'reihan20', 'reihanbiru20', 'AKTIF'),
(3, 'Ananta Heriyana', '082124698211', 'Jalan Kircon No 21', 'jamaica88', 'ilovejamaica1', 'AKTIF'),
(4, 'Arfin Faisal', '081141567080', 'Jalan Kemayoran No 490', 'pergitenang', 'jauhmelangkah', 'AKTIF'),
(5, 'Miranda Yahyani', '081314303921', 'Kompleks Cibening Indah No D3', 'andalusia20', 'kpop2020', 'TIDAK AKTIF'),
(6, 'Ricky Namara', '081384886829', 'Jalan Kasblanka No 41', 'youtube333', 'iamayoutuber2020', 'TIDAK AKTIF'),
(7, 'Lazuardi Latief', '082112618111', 'Kompleks Cibening Indah No D3', 'latieflazuardi22', 'gaspar1999', 'AKTIF'),
(8, 'Zainal Khalidin', '081221480643', 'Kompleks Bumi Kirana No 3', 'juandaexport', 'itb2020', 'TIDAK AKTIF'),
(9, 'Dimas Anugrah', '082129908090', 'Kompleks Cibiru Indah No 33', 'batman099', 'batmanvsuperman', 'AKTIF'),
(10, 'Zabiyan Aqsal', '081228908177', 'Komplek Permata Biru No H3', 'laboratoriumkimia', 'kimiaisfun', 'TIDAK AKTIF'),
(11, 'Zaidan Anggara', '089789878754', 'Jalan Cibiru Raya No. 14', 'zaidanangga', '123456', 'TIDAK AKTIF'),
(12, 'Syakila Azzahra', '087133878756', 'Komp. Mekar Arum No. 22', 'azzahrasyakila', 'bandung123', 'AKTIF'),
(13, 'Sindi Aprilianti', '081221887423', 'Jl. Cilengkrang II No. 15', 'apriliasindi', 'mercedezbenz', 'AKTIF'),
(14, 'Rama Ishaq', '081655211809', 'Jl. Jatihandap A2 No. 29', 'persibjuara', 'persibjuara1933', 'AKTIF'),
(15, 'Indira Refalleta', '0821778212087', 'Gg. Jibja No. 15', 'irarefalleta', 'jibjaistimewa', 'AKTIF'),
(16, 'Syahnaz Annisa', '089213877431', 'Komp. Manglayang Regency 14', 'syahnazkila01', 'beautyandbeast', 'TIDAK AKTIF'),
(17, 'Rara Aprilia', '0821922188278', 'Komp. Antapani Indah C10', 'pagidansore', 'pecintasenja22', 'AKTIF'),
(18, 'Ibrahim Syukron', '087162461980', 'Jl. Jakarta No. 25', 'pecintagunung56', 'youngsummit2002', 'TIDAK AKTIF'),
(19, 'Akbar Maulana', '081312876731', 'Komp. Viku A6 No. 5', 'kobar67', 'evilcorps', 'AKTIF'),
(20, 'Mohammad Reza Faisal', '0851278812397', 'Komp. Bumi Asri No. 41', 'rezafaisal9', 'bumiasrisquad', 'TIDAK AKTIF'),
(21, 'Tiara Azzahra', '085211321785', 'Jl. Cigagak No.102', 'tiarazahra12', 'tigapagi31', 'AKTIF'),
(22, 'Muhammad Arif', '085126781912', 'Komp. Pasanggrahan Indah 188', 'naruto12', 'boruto099', 'AKTIF'),
(23, 'Mutaz Yazid', '082157669012', 'Jl. Cijambe D1 No. 8', 'superutek', 'mayorutek13', 'TIDAK AKTIF'),
(24, 'Ahmad Zamzam', '085123896576', 'Gg. Karamat No. 2', 'zamzamahmad', 'gitarisidola66', 'TIDAK AKTIF'),
(25, 'Ismi Hidayat', '082231752198', 'Gg. Sukanegla No. 115', 'suneg12', 'milkblendrafi11', 'AKTIF'),
(26, 'Adam Nur Wahid', '085146786756', 'Jl. Sukabumi Dalam No. 40', 'adam1922', 'wonderfulnight12', 'TIDAK AKTIF');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` int(11) NOT NULL,
  `judul_buku` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `penulis` varchar(50) NOT NULL,
  `deskripsi` varchar(100) NOT NULL,
  `tahun_terbit` varchar(5) NOT NULL,
  `date_created` date NOT NULL,
  `stok` int(3) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul_buku`, `penerbit`, `penulis`, `deskripsi`, `tahun_terbit`, `date_created`, `stok`, `id_admin`, `status`) VALUES
(1, 'Senja & Pagi', 'Loveable x Bhumi Anoma', 'Alffy Rev & Linka Angelia', 'Novel ini bercerita tentang perjalanan cinta penulisnya yang terbilang singkat', '2018', '2020-06-08', 11, 1, 'ADA'),
(2, 'Unity Tutorial Game Engine', 'Penerbit Informatika', 'Rickman Roedavan', 'Buku ini berisi mengenai tata cara penggunaan unity', '2018', '2020-06-09', 10, 1, 'ADA'),
(3, 'Resign', 'Gramedia Pustaka Utama', 'Almira Bastari', 'Buku ini mengisahkan kehidupan para pegawai kantor yang ingin keluar dari pekerjaan', '2018', '2020-08-12', 6, 2, 'ADA'),
(4, 'Antologi Rasa', 'Gramedia Pustaka Utama', 'Ika Natassa', 'Dalam novel Antologi Rasa mengisahkan tentang cinta yang terjalin di antara tiga sahabat', '2018', '2020-06-13', 8, 2, 'ADA'),
(5, 'Yang Fana Adalah Waktu', 'Gramedia Pustaka Utama', 'Sapardi Djoko Damono', 'Yang Fana Adalah Waktu merupakan sekuel dari trilogi Hujan Bulan Juni dan Pingkan Melipat Jarak', '2018', '2020-06-14', 8, 3, 'ADA'),
(6, 'Perangkat Keras Komputer', 'PT Elex Media Komputindo', 'Bobi Kurniawan, St.,M.Kom', 'Buku ini membahas seputar perangkat keras komputer', '2014', '2020-06-14', 10, 3, 'DIHAPUS'),
(7, '7 In 1 Pemograman Web Untuk Pemula', 'PT Elex Media Komputindo', 'Rohi Abdulloh', 'Buku ini berisi mengenai pemrograman web yang ditujukan bagi para pemula', '2018', '2020-07-14', 12, 3, 'ADA'),
(8, 'Aroma Karsa', 'Bentang Pustaka', 'Dee Lestari', 'Dalam buku ini,terdapat kisah romansa, petualangan, detektif, fantasi, bahkan mitologi', '2018', '2020-07-17', 8, 4, 'ADA'),
(9, 'Modul Pemrograman Web (HTML,PHP&MySQL/MariaDB)', 'Penerbit Modula', 'Budi Raharjo', 'Buku ini berisikan tentang pemrograman web dari HTML hingga MariaDb', '2016', '2020-08-01', 3, 5, 'ADA'),
(10, 'Jika Kamu Menjadi Tanda Tambah', 'BIP', 'Trista Speed Shaskan', 'Buku ini mengajarkan konsep penjumlahan dalam matematika kepada anak-anak', '2010', '2020-08-02', 3, 6, 'DIHAPUS'),
(11, 'Bumi Manusia', 'Lentera Dipantara', 'Pramoedya Ananta Toer', 'Sebuah roman yang dikenal dengan tetralogi buru. Ditulis oleh pram saat di dalam penjara', '2017', '2020-08-07', 6, 1, 'DIHAPUS'),
(12, 'Islam dan Akal Merdeka', 'Sega Arsy', 'Mohammad Natsir', 'Buku ini adalah kritik atas pemikiran Soekarno tentang Islam Sontoloyo', '2018', '2020-08-07', 4, 11, 'ADA'),
(13, 'Mengenal Microsoft Office 2013', 'PT Elex Media Komputindo', 'Taryana Suryana & Koesheryatin', 'Buku ini adalah buku yang berisi tentang-tentang pengenalan microsoft office 2013', '2018', '2020-08-08', 6, 12, 'ADA'),
(14, 'Guns, Germs, and Steel', 'KPG', 'Jared Diamond', 'Buku tentang sejarah rangkuman riwayat masyarakat manusia di bumi', '2018', '2020-08-09', 8, 13, 'ADA'),
(15, 'Pemrograman Berorientasi Objek Menggunakan Java', 'Penerbit Informatika', 'Adam Mukharil Bachtiar & Firman Nizammudin Fakhrul', 'Buku ini ditujukan untuk mahasiswa, dosen yang ingin mempelajari pemrograman oop dengan java', '2018', '2020-08-09', 1, 14, 'ADA'),
(16, 'Pemrograman C dan C++', 'Penerbit Informatika', 'Adam Mukharil Bachtiar', 'Buku ini ditujukan untuk mahasiswa yang ingin mempelajari konsep bahasa C dan C++', '2017', '2020-08-09', 0, 14, 'ADA'),
(17, 'Matematika Diskrit', 'Penerbit Informatika', 'Rinaldi Munir', 'Buku ini disusun sebagai buku teks mahasiswa yang mengambil mata kuliah Matematika Diskrit', '2016', '2020-08-09', 0, 15, 'ADA'),
(18, 'Max Havelaar', 'Penerbit Qanita', 'Multatuli', 'Novel ini menceritakan seorang Max Havelaar yang bekerja sebagai Asisten Lebak Banten ', '2018', '2020-08-10', 13, 17, 'ADA'),
(19, '1984', 'Penerbit Bentang', 'George Orwell', 'Novel ini bercerita tentang seseorang yang hidup dalam negara totalitarianisme', '2018', '2020-08-10', 6, 19, 'ADA'),
(20, 'Murder On The Orient Express', 'PT Gramedia Pustaka Utama', 'Agatha Christie', 'Novel yang menceritakan tentang penyelidikan pembunuhan di kereta Orient Express ', '2018', '2020-08-10', 0, 19, 'ADA'),
(21, 'Sjahrir: Peran Besar Bung Kecil', 'KPG', 'Tim Buku Tempo', 'Buku yang menceritakan tentang tokoh pahlawan bangsa bapak bangsa yaitu Sjahrir', '2010', '2020-08-10', 0, 20, 'DIHAPUS'),
(22, 'Soedirman: Seorang Panglima, Seorang Martir', 'KPG', 'Tim Buku Tempo', 'Buku yang menceritakan tokoh militer atau pejuang kemerdekaan yaitu Soedirman', '2010', '2020-08-10', 0, 21, 'DIHAPUS'),
(23, 'When Broken Glass Floats', 'PT Elex Media Komputindo', 'Chanrithy Him', 'Buku yang menceritakan perjalanan penulis melewati neraka ladang pembantaian oleh rezim Khmer Merah', '2011', '2020-08-10', 0, 22, 'DIHAPUS'),
(24, 'Gajah Mada: Sistem Politik dan Kepemimpinan', 'Narasi', 'Enung Nurhayati Ma., Ph.D', 'Buku yang menjelaskan tentang sistem politik dan kepemimpinan Gajah Mada', '2018', '2020-08-10', 1, 23, 'ADA'),
(25, 'Serat Cantigi', 'MOJOK', 'E. Rokajat Asura', 'Buku yang menceritakan pergulatan seorang anak yang mencari fakta tentang bapaknya', '2018', '2020-08-10', 0, 24, 'DIHAPUS'),
(26, 'Jemput Terbawa', 'MOJOK', 'Pinto Anugrah', 'Novel yang menceritakan tentang seorang perempuan yang kembali ke kampung', '2018', '2020-08-10', 0, 1, 'DIHAPUS'),
(27, 'test', 'test', 'test', 'test', '2020', '2020-08-13', 0, 1, 'DIHAPUS'),
(28, 'UUD 1945 dan Amandemennya', 'PT Grasindo', 'Tim Grasindo', 'Buku UUD 1945 dan amandemennya dilengkapi dengan susunan kabinet baru', '2020', '2020-08-13', 20, 1, 'ADA');

-- --------------------------------------------------------

--
-- Table structure for table `detail_peminjaman`
--

CREATE TABLE `detail_peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `tanggal_pinjam` date NOT NULL,
  `tanggal_kembali` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_peminjaman`
--

INSERT INTO `detail_peminjaman` (`id_peminjaman`, `id_buku`, `tanggal_pinjam`, `tanggal_kembali`, `status`) VALUES
(1, 10, '2020-08-01', '2020-08-06', 'DIKEMBALIKAN'),
(2, 7, '2020-08-03', '2020-08-08', 'DIKEMBALIKAN'),
(3, 1, '2020-08-03', '2020-08-08', 'DIKEMBALIKAN'),
(4, 9, '2020-08-03', '2020-08-08', 'DIKEMBALIKAN'),
(5, 2, '2020-08-03', '2020-08-08', 'DIKEMBALIKAN'),
(6, 5, '2020-08-04', '2020-08-09', 'DIKEMBALIKAN'),
(7, 6, '2020-08-04', '2020-08-09', 'DIKEMBALIKAN'),
(8, 7, '2020-08-05', '2020-08-10', 'DIKEMBALIKAN'),
(9, 7, '2020-08-05', '2020-08-10', 'DIKEMBALIKAN'),
(10, 8, '2020-08-06', '2020-08-11', 'DIKEMBALIKAN'),
(11, 2, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(12, 3, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(13, 4, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(14, 9, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(15, 8, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(16, 3, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(17, 18, '2020-08-05', '2020-08-10', 'DIKEMBALIKAN'),
(18, 14, '2020-08-06', '2020-08-11', 'DIKEMBALIKAN'),
(18, 19, '2020-08-06', '2020-08-11', 'DIKEMBALIKAN'),
(19, 24, '2020-08-06', '2020-08-11', 'DIKEMBALIKAN'),
(20, 15, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(21, 12, '2020-08-07', '2020-08-12', 'DIKEMBALIKAN'),
(22, 16, '2020-08-08', '2020-08-13', 'DIKEMBALIKAN'),
(23, 20, '2020-08-08', '2020-08-13', 'DIKEMBALIKAN'),
(24, 24, '2020-08-08', '2020-08-13', 'DIKEMBALIKAN'),
(25, 15, '2020-08-08', '2020-08-13', 'DIKEMBALIKAN'),
(25, 25, '2020-08-09', '2020-08-14', 'DIPINJAM'),
(26, 12, '2020-08-09', '2020-08-14', 'DIKEMBALIKAN'),
(27, 13, '2020-08-09', '2020-08-14', 'DIKEMBALIKAN'),
(28, 17, '2020-08-09', '2020-08-14', 'DIPINJAM'),
(29, 20, '2020-08-10', '2020-08-15', 'DIPINJAM'),
(30, 14, '2020-08-10', '2020-08-15', 'DIPINJAM'),
(31, 12, '2020-08-11', '2020-08-16', 'DIKEMBALIKAN'),
(32, 18, '2020-08-11', '2020-08-16', 'DIPINJAM'),
(33, 15, '2020-08-12', '2020-08-17', 'DIPINJAM'),
(34, 19, '2020-08-12', '2020-08-17', 'DIKEMBALIKAN'),
(35, 24, '2020-08-12', '2020-08-17', 'DIPINJAM'),
(36, 1, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(37, 1, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(38, 1, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(39, 9, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(40, 9, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(41, 9, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(42, 9, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(43, 1, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN'),
(44, 1, '2020-08-13', '2020-08-18', 'DIKEMBALIKAN');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `id_anggota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_anggota`) VALUES
(1, 1),
(2, 1),
(3, 2),
(14, 2),
(15, 2),
(16, 2),
(31, 2),
(32, 2),
(33, 2),
(34, 2),
(35, 2),
(36, 2),
(37, 2),
(39, 2),
(40, 2),
(41, 2),
(42, 2),
(43, 2),
(44, 2),
(4, 3),
(5, 4),
(38, 4),
(6, 5),
(7, 6),
(13, 6),
(8, 7),
(9, 8),
(10, 9),
(11, 10),
(12, 10),
(17, 12),
(18, 12),
(19, 13),
(20, 14),
(21, 15),
(23, 17),
(25, 17),
(30, 18),
(22, 19),
(26, 19),
(27, 21),
(28, 22),
(29, 24),
(24, 25);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indexes for table `detail_peminjaman`
--
ALTER TABLE `detail_peminjaman`
  ADD KEY `id_peminjaman` (`id_peminjaman`),
  ADD KEY `detail_peminjaman_ibfk_2` (`id_buku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `id_anggota` (`id_anggota`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `id_anggota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `buku_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_peminjaman`
--
ALTER TABLE `detail_peminjaman`
  ADD CONSTRAINT `detail_peminjaman_ibfk_1` FOREIGN KEY (`id_peminjaman`) REFERENCES `peminjaman` (`id_peminjaman`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_peminjaman_ibfk_2` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id_buku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`id_anggota`) REFERENCES `anggota` (`id_anggota`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
