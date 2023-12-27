-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: posctzn
-- ------------------------------------------------------
-- Server version	11.3.0-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_pme00_permission`
--

DROP TABLE IF EXISTS `tb_pme00_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pme00_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pme00_permission`
--

LOCK TABLES `tb_pme00_permission` WRITE;
/*!40000 ALTER TABLE `tb_pme00_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pme00_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pme00_permission_role`
--

DROP TABLE IF EXISTS `tb_pme00_permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pme00_permission_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `PERMISSION_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tb_pme00_permission_role_tb_pme00_role` (`ROLE_ID`),
  KEY `FK_tb_pme00_permission_role_tb_pme00_permission` (`PERMISSION_ID`),
  CONSTRAINT `FK_tb_pme00_permission_role_tb_pme00_permission` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `tb_pme00_permission` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tb_pme00_permission_role_tb_pme00_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_pme00_role` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pme00_permission_role`
--

LOCK TABLES `tb_pme00_permission_role` WRITE;
/*!40000 ALTER TABLE `tb_pme00_permission_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pme00_permission_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pme00_role`
--

DROP TABLE IF EXISTS `tb_pme00_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pme00_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT '',
  `STATUS` int(5) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pme00_role`
--

LOCK TABLES `tb_pme00_role` WRITE;
/*!40000 ALTER TABLE `tb_pme00_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pme00_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pme00_role_user`
--

DROP TABLE IF EXISTS `tb_pme00_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pme00_role_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` varchar(15) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tb_pme00_role_user_tb_m00_employee` (`EMPLOYEE_ID`),
  KEY `FK_tb_pme00_role_user_tb_pme00_role` (`ROLE_ID`),
  CONSTRAINT `FK_tb_pme00_role_user_tb_m00_employee` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `tb_m00_employee` (`EMP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tb_pme00_role_user_tb_pme00_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_pme00_role` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pme00_role_user`
--

LOCK TABLES `tb_pme00_role_user` WRITE;
/*!40000 ALTER TABLE `tb_pme00_role_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pme00_role_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-27 15:04:16
