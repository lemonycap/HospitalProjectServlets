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
-- Table structure for table `patient_info`
--

DROP TABLE IF EXISTS `patient_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `patient_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `nurse_id` int(11) DEFAULT NULL,
  `diagnosis_id` int(11) DEFAULT NULL,
  `patient_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `patient_info_app_users_id_fk` (`patient_id`),
  KEY `patient_info_app_users_id_fk_2` (`doctor_id`),
  KEY `patient_info_app_users_id_fk_3` (`nurse_id`),
  KEY `patient_info_diagnosis_table_id_diagnosis_fk` (`diagnosis_id`),
  CONSTRAINT `patient_info_app_users_id_fk` FOREIGN KEY (`patient_id`) REFERENCES `app_users` (`id`),
  CONSTRAINT `patient_info_app_users_id_fk_2` FOREIGN KEY (`doctor_id`) REFERENCES `app_users` (`id`),
  CONSTRAINT `patient_info_app_users_id_fk_3` FOREIGN KEY (`nurse_id`) REFERENCES `app_users` (`id`),
  CONSTRAINT `patient_info_diagnosis_table_id_diagnosis_fk` FOREIGN KEY (`diagnosis_id`) REFERENCES `diagnosis_table` (`id_diagnosis`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_info`
--

LOCK TABLES `patient_info` WRITE;
/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;
INSERT INTO `patient_info` VALUES (1,2,NULL,NULL,3,0),(2,6,NULL,NULL,7,0),(3,7,1,3,5,1),(4,8,1,NULL,7,0),(5,10,1,3,3,1),(6,11,1,5,1,1),(7,12,NULL,NULL,7,0),(8,13,NULL,NULL,2,0),(9,14,4,3,4,1);
/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;
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
