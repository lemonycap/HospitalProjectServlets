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
-- Table structure for table `prescription_history`
--

DROP TABLE IF EXISTS `prescription_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prescription_history` (
  `patient_id` int(11) DEFAULT NULL,
  `prescription_id` int(11) DEFAULT NULL,
  KEY `FKb4b45dmap5gnx7kc44u1ov7ka` (`prescription_id`),
  KEY `FKarjtiurx4wn2v9ayhcg65fgvh` (`patient_id`),
  CONSTRAINT `FKarjtiurx4wn2v9ayhcg65fgvh` FOREIGN KEY (`patient_id`) REFERENCES `patient_data` (`id`),
  CONSTRAINT `FKb4b45dmap5gnx7kc44u1ov7ka` FOREIGN KEY (`prescription_id`) REFERENCES `prescription_table` (`id_prescription`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_history`
--

LOCK TABLES `prescription_history` WRITE;
/*!40000 ALTER TABLE `prescription_history` DISABLE KEYS */;
INSERT INTO `prescription_history` VALUES (15,3),(15,7),(1,1),(1,5),(2,1),(3,7),(4,3),(4,1),(4,5),(5,4),(5,5),(6,2),(7,3),(7,5),(8,6),(8,5),(8,2),(9,6),(9,4),(9,2),(10,6),(10,2),(11,3),(12,6),(13,4),(13,7),(14,3),(14,4),(14,7),(16,1),(17,6),(17,1),(18,7),(19,6),(19,5),(20,5),(21,4),(21,7),(22,6),(23,11),(23,12),(23,10),(24,2);
/*!40000 ALTER TABLE `prescription_history` ENABLE KEYS */;
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
