CREATE DATABASE  IF NOT EXISTS `hospitaldb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `hospitaldb`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitaldb
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `patient_data`
--

DROP TABLE IF EXISTS `patient_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `patient_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `diagnosis_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `nurse_id` int(11) DEFAULT NULL,
  `patient_status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_15mtt1x1k81vrlbt86ydh13uc` (`patient_id`),
  KEY `FKcymbeo00bp14xs0oqsbx5hdnh` (`diagnosis_id`),
  KEY `FK6xbi0s4nm21f7a5vedq3jrde7` (`doctor_id`),
  KEY `FKo0tqbp3eshokuo5nr56iqsqyv` (`nurse_id`),
  CONSTRAINT `FK6xbi0s4nm21f7a5vedq3jrde7` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKcymbeo00bp14xs0oqsbx5hdnh` FOREIGN KEY (`diagnosis_id`) REFERENCES `diagnosis_table` (`id_diagnosis`),
  CONSTRAINT `FKo0tqbp3eshokuo5nr56iqsqyv` FOREIGN KEY (`nurse_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKtd7ogffhoyw3o5wmcpd60q4ng` FOREIGN KEY (`patient_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_data`
--

LOCK TABLES `patient_data` WRITE;
/*!40000 ALTER TABLE `patient_data` DISABLE KEYS */;
INSERT INTO `patient_data` VALUES (1,3,4,2,2,1),(2,5,5,NULL,NULL,0),(3,9,3,4,2,1),(4,10,6,4,2,1),(5,11,7,4,2,1),(6,12,1,1,2,1),(7,13,7,1,NULL,1),(8,14,6,NULL,NULL,0),(9,15,5,4,NULL,1),(10,17,4,NULL,NULL,0),(11,18,NULL,4,NULL,1),(12,19,NULL,NULL,NULL,1),(13,20,NULL,NULL,NULL,1),(14,21,NULL,NULL,NULL,1),(15,22,NULL,NULL,NULL,1),(16,23,NULL,NULL,NULL,1),(17,24,NULL,NULL,NULL,1),(18,25,NULL,NULL,NULL,1),(19,26,NULL,NULL,NULL,1),(20,27,NULL,NULL,NULL,1),(21,28,NULL,NULL,NULL,1),(22,30,NULL,NULL,NULL,1),(23,32,NULL,NULL,NULL,1),(24,33,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `patient_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 20:47:14
