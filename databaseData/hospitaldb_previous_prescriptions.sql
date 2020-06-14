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
-- Table structure for table `previous_prescriptions`
--

DROP TABLE IF EXISTS `previous_prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `previous_prescriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `prescription_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `previous_prescriptions_patient_info_patient_id_fk` (`patient_id`),
  KEY `previous_prescriptions_prescription_table_id_prescription_fk` (`prescription_id`),
  CONSTRAINT `previous_prescriptions_patient_info_patient_id_fk` FOREIGN KEY (`patient_id`) REFERENCES `patient_info` (`patient_id`),
  CONSTRAINT `previous_prescriptions_prescription_table_id_prescription_fk` FOREIGN KEY (`prescription_id`) REFERENCES `prescription_table` (`id_prescription`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `previous_prescriptions`
--

LOCK TABLES `previous_prescriptions` WRITE;
/*!40000 ALTER TABLE `previous_prescriptions` DISABLE KEYS */;
INSERT INTO `previous_prescriptions` VALUES (1,2,6),(2,6,6),(3,6,5),(4,10,1),(5,10,7),(6,11,5),(7,8,6),(8,12,1),(9,7,7),(10,13,1);
/*!40000 ALTER TABLE `previous_prescriptions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 20:47:12
