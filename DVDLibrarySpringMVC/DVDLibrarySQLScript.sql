DROP DATABASE IF EXISTS `DVD_Library` ;
CREATE DATABASE IF NOT EXISTS `DVD_Library` DEFAULT CHARACTER SET utf8 ;


USE `DVD_Library` ;
CREATE TABLE IF NOT EXISTS `dvd_list` (
 `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
 `dvd_title` varchar(50) NOT NULL,
 `release_year` varchar(50) NOT NULL,
 `director` varchar(50) NOT NULL,
 `rating` varchar(10) DEFAULT NULL,
 `notes` varchar(50) NOT NULL,
 PRIMARY KEY (`dvd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP DATABASE IF EXISTS `DVD_Library_Test` ;
CREATE DATABASE IF NOT EXISTS `DVD_Library_Test` DEFAULT CHARACTER SET utf8 ;


USE `DVD_Library_Test` ;
CREATE TABLE IF NOT EXISTS `dvd_list` (
 `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
 `dvd_title` varchar(50) NOT NULL,
 `release_year` varchar(50) NOT NULL,
 `director` varchar(50) NOT NULL,
 `rating` varchar(10) DEFAULT NULL,
 `notes` varchar(50) NOT NULL,
 PRIMARY KEY (`dvd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;