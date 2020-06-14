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
-- Table structure for table `app_users`
--

DROP TABLE IF EXISTS `app_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `app_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `password` longtext COLLATE utf8_bin,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `app_users_user_roles_role_id_fk` (`role`),
  CONSTRAINT `app_users_user_roles_role_id_fk` FOREIGN KEY (`role`) REFERENCES `user_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users`
--

LOCK TABLES `app_users` WRITE;
/*!40000 ALTER TABLE `app_users` DISABLE KEYS */;
INSERT INTO `app_users` VALUES (1,'Steve','Madden','stevemadden@gmail.com','b9f42a213cef73f57b5c22bc0f612c8386121e6409febc6e6070a1b8dd19669b',2),(2,'Sam','Evans','samevans@gmail.com','de8200a4e9086f43654c5131df2c64a8d46202aec31a96dff95607e044efbbef',1),(3,'Maria','Klein','mariaklein@gmail.com','13224f61b614c0c3ff44237dcc1f146d162d9440bad0fa383f109feddafaa51e',3),(4,'Yelyzaveta','Onyshchenko','lemonycap@gmail.com','16af147fef7eaa61cd113fe1e8902176ae89cfbe8d0808f0ca7b3627bc0fddf0',2),(5,'Evelina','Set','evelinaset@gmail.com','de970a3437e3b2886eb5e001ab87d9e9fc749812a6990bb30747987687047747',3),(6,'Sebastian','Fetsun','sebfetsun@gmail.com','fa6cc716ead7413255ddb2ca60f2eb97073b98ac6ed2ef501f14a45ba0aa92d5',1),(7,'Anna','Val','annaval@gmail.com','2271955fe7beb236ff19adc80df9bd3684e3bec6f7cae2accdd78ce964a1e8b0',1),(8,'Dan','Dans','dandans@gmail.com','97ae9ac255150011b207157c6cad90ed46eaa707b32437a79cd06b023cb5e5d6',1),(9,'Mary','Mer','marymer@gmail.com','6bde5af1fea70b076eea4b6c886d24f3368c1feb4db80e4e8a336695034602c0',2),(10,'Sasha','Nemo','sashanemo@gmail.com','dcfe717e2b16c8a42cb0c030ad85f1368fc0a3f0bc8226eb80e823d99ec34ad4',1),(11,'User1','User','user1@gmail.com','0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90',1),(12,'TestUser','TestUser','testuser@gmail.com','ae5deb822e0d71992900471a7199d0d95b8e7c9d05c40a8245a281fd2c1d6684',1),(13,'Santa','Fe','santafe@gmail.com','855a2b6a271b67d78bc98fa9ebf7789d9ad6a26f3dce41154c1e54508c979b0c',1),(14,'testPatient','Patient','testpatient@gmail.com','2515025074d87a8caacc72879ff1262c25004c15a2db9baa929fbd3a7de0728b',1);
/*!40000 ALTER TABLE `app_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 20:47:13
