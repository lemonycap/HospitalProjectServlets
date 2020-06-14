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
-- Table structure for table `prescription_table`
--

DROP TABLE IF EXISTS `prescription_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prescription_table` (
  `id_prescription` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_class` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prescription_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prescription_duration` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_prescription`),
  UNIQUE KEY `prescription_table_id_prescription_uindex` (`id_prescription`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_table`
--

LOCK TABLES `prescription_table` WRITE;
/*!40000 ALTER TABLE `prescription_table` DISABLE KEYS */;
INSERT INTO `prescription_table` VALUES (1,'medicine','medicine6',NULL),(2,'medicine','medicine7',NULL),(3,'medicine','medicine8',NULL),(4,'procedures','procedure 1',NULL),(5,'procedures','procedure 2',NULL),(6,'procedures','procedure 3',NULL),(7,'operations','operation 1',NULL),(8,'operations','operation 2',NULL),(9,'medicine','medicine1',NULL),(10,'medicine','medicine2',NULL),(11,'medicine','medicine3',NULL),(12,'medicine','medicine4',NULL),(13,'medicine','medicine5',NULL);
/*!40000 ALTER TABLE `prescription_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 20:47:15
