--
-- Database: `ReBook`
--

CREATE DATABASE ReBook;
USE ReBook;

--
-- Table structure for table `UTILIZADOR`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `UTILIZADOR` (
	`idUser` int(10) NOT NULL AUTO_INCREMENT,
	`username` varchar(100) NOT NULL,
	`password` varchar(100) NOT NULL,
	PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Table structure for table `LIVRO`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `LIVRO` (
	`idLivro` int(10) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`nomeFicheiro` varchar(100) NOT NULL,
	`derivado` int(10),
	PRIMARY KEY (`idLivro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Table structure for table `FAVORITO`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `FAVORITO` (
	`idLivro` int(10) NOT NULL,
	`idUser` int(10) NOT NULL,
	PRIMARY KEY (`idLivro`, `idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `AUTOR`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `AUTOR` (
	`idAutor` int(10) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`info` varchar(255),
	PRIMARY KEY (`idAutor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Table structure for table `LIVRO_AUTOR`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `LIVRO_AUTOR` (
	`idLivro` int(10) NOT NULL,
	`idAutor` int(10) NOT NULL,
	PRIMARY KEY(`idLivro`, `idAutor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `RASCUNHO`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `RASCUNHO` (
	`idRascunho` int(10) NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`nomeFicheiro` varchar(100) NOT NULL,
	`derivado` int(10),
	PRIMARY KEY(`idRascunho`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Table structure for table `UTILIZADOR_RASCUNHO`
--
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `UTILIZADOR_RASCUNHO` (
	`idUser` int(10) NOT NULL,
	`idRascunho` int(10) NOT NULL,
	PRIMARY KEY (`idUser`, `idRascunho`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
