-- MySQL dump 10.13  Distrib 5.1.30, for Win32 (ia32)
--
-- Host: localhost    Database: vodafone_hr_system
-- ------------------------------------------------------
-- Server version	5.1.30-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `department` (
  `ID_DEPARTEMENT` int(3) unsigned NOT NULL,
  `STR_DEPARTEMENT_NAME` char(30) NOT NULL,
  PRIMARY KEY (`ID_DEPARTEMENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Accountant'),(2,'Business Develoment');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `employee` (
  `ID_EMPLOYEE` int(6) unsigned NOT NULL DEFAULT '0',
  `ID_MANAGER` int(6) unsigned NOT NULL,
  `ID_DEPARTEMENT` int(3) unsigned NOT NULL,
  `STR_EMPLOYEE_FULLNAME` char(50) NOT NULL,
  `STR_IMAGE_URL` varchar(70) DEFAULT NULL,
  `STR_MOBILE` char(15) NOT NULL,
  `ENUM_GENDER` enum('MALE','FEMALE') NOT NULL,
  PRIMARY KEY (`ID_EMPLOYEE`),
  KEY `ID_DEPARTEMENT` (`ID_DEPARTEMENT`),
  KEY `ID_MANAGER` (`ID_MANAGER`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`ID_DEPARTEMENT`) REFERENCES `department` (`ID_DEPARTEMENT`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`ID_MANAGER`) REFERENCES `employee` (`ID_EMPLOYEE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,0,2,'Ahmed Samir Amer','1341985430043_ProfileImage','67891523084','MALE'),(1,0,2,'dalia','logo1.jpg','0100873126','FEMALE');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-11 15:54:22
