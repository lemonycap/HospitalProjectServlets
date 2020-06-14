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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `password` longtext COLLATE utf8_bin,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `users_user_roles_role_id_fk` (`role`),
  CONSTRAINT `users_user_roles_role_id_fk` FOREIGN KEY (`role`) REFERENCES `user_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Steve','Madden','stevemadden@gmail.com','$2y$12$oAYqW4OYRqk2.E0/qWrb6.fuaWj60cavlhlKX2VGoUsZtPnDximt2',1,2),(2,'Maria','Klein','mariaklein@gmail.com','$2y$12$5PzBRiX8FPibFGOkxRUwI.RHGr8/VFPUlbT5s.o5xuX28.gEKdT6m',1,3),(3,'Sam','Evans','samevans@gmail.com','$2y$12$zudkUIQAKTXbUJkJDMGL3uSTYBTSm60y9yrHikwQjXwOq/6b.qEBy',1,1),(4,'Yelyzaveta ','Onyshchenko','lemonycap@gmail.com','$2y$12$SC4nEkrIvnFu5PopU.muoeG1Cp/5H2XMgOdRIxu3y3FJ36dPsJJr2',1,2),(5,'Sebastian','Fetsun','sebfetsun@gmail.com','$2a$10$26igy4iMghF.VNd95aRMNON9.DnR.7QuCslKpMGuNbjxAfab2H7fm',1,1),(6,'Evelina','Set','evelinaset@gmail.com','$2a$10$osdz7RLP9iIKWIPaUVt6tOWoI6wbTmGvHg5DYyOKxp9Pdr1P3qVJi',1,2),(7,'Rain','Cloud','raincloud@gmail.com','$2a$10$jtlGBAyacl/TJa73fnHOvOm.PmhrwsXj3dTX6U7Xl/R/8U0tG.t8O',1,3),(8,'Anna','Maria','annamaria@gmail.com','$2a$10$xxGtmp1QO.tMNm1bzW2z/.hQ0ItugusZHFnqzh7x/V87NokBmR8ou',1,2),(9,'Anna','Val','annaval@gmail.com','$2a$10$UvMMkIhLCH3d45B.aoAiO.6ZOsmUBhKdRVQX5xcDqUSfwyBgTNEW2',1,1),(10,'Mary','Ivanova','maryivanova@gmail.com','$2a$10$98ucRIaO0O55qK0uaNKULe1ln49eLD0QJPft8eQKJXkRIaN/rKeP2',1,1),(11,'Sasha','Nemo','sashanemo@gmail.com','$2a$10$tJcH7prcSVnXuJPfuifix..jyxD/QpAlIv6qNyA.PXM/DKkRdcqLS',1,1),(12,'Santa','Ann','santaann@gmail.com','$2a$10$zzEfiXvB9iDTyCG6ja02fux7v9RhSuxl3X0CTe3x0SEuaPvb2RMuO',1,1),(13,'user1','user1','user1','$2a$10$VJmHQVIOo6buZGJWXYW.Huzan9.iJ1DrCcX34mvxTWRUPKEXdFpFm',1,1),(14,'user2','user2','user2','$2a$10$6vAM/MstXRmXjkwD0VdM7eGJjnwFOdgEici80lSBXEvuYWehgY46u',1,1),(15,'user3','user3','user3','$2a$10$6zmqlxC/2jsv0CssQac9kufFpxMds8DVqB82cmIqof4poMV5WwRAC',1,1),(17,'user5','user5','user5','$2a$10$GM1WcPvQ61VR9AQYncKjHuZnmZDvyMli1w/TsDHvMzyKiw1WeM72K',1,1),(18,'u6','u6','u6','$2a$10$WJwSVopz1QbA.XWuFS6iaOHaQtM82qbcpCANx8eIQAhQek7KtxS22',1,1),(19,'u7','u7','u7','$2a$10$SCkkY0axfHnPKhzOquRP4eE6Foj2AfK0MGphEFdF/FaMWZP5NN106',1,1),(20,'u8','u8','u8','$2a$10$7cjyivDqaavPM2vJCOa.gutTgwBzEXRFznL27KLrgPOCTBYCw3VqC',1,1),(21,'u9','u9','u9','$2a$10$6mu9cCAUwpsN4TY3U4ixHunmuKNTRMou5RZLoLtVjUF.irJp3T/1W',1,1),(22,'w','c','a','$2a$10$niruZuoaSwR8qjo4VSo2UOaPt7IMjrQY4C.v9lSt1s7HpCqErHxfC',1,1),(23,'liza','liza','liza','$2a$10$XWlh3y7lNSeiZKL0/03G7u.qtNHZ3zWOuRD4SKpQRGrTIU0M1B2Ja',1,1),(24,'n','n','n','$2a$10$ecIJSNIGLXWF.7ijYe3D5.0nelamSktq6OUIKIFtjHHm8jc0mNqgO',1,1),(25,'i9','i9','i9','$2a$10$n9EP6mT5cJD5WYvT8Q6ZmeclfS7i4ByCo7XUp6QxemFZ12uzPWw6.',1,1),(26,'lo','lo','lo','$2a$10$dqjh9tEPyT/fb10lhofKD.5yNg/PYXXv2xOVQwEiC6KTZYQuAotiC',1,1),(27,'m','m','m','$2a$10$eUJQXPCCX.JkPfNLzOZUo.JRtNVwnxSsJAH4xj6MYEMC5VvXSxj0u',1,1),(28,'Sebastian','s','s','$2a$10$XVdSWjqorpD6Kga1XAF1Ge79nfBawoWLzkwCAiuZigcM1.au2.PO.',1,1),(29,'admin','first','admin@gmail.com','$2y$12$lNhyoPx2NSvni9di/ybzO.bXZfuj0Ih8deP4ki7PdSqDCtV7vOWNO',1,4),(30,'user10','user10','user10@gmail.com','$2a$10$iS19LPZEqG26KUFjPV7lLuLbQZo.OZCAjrLavDOS99M1KO40h2RMq',1,1),(31,'user11','user11','user11','$2a$10$k8xxwh6TKnytVlx8t2fOo.wgDECZrkdbYn/T809bw78HnyggbT7U.',1,2),(32,'b','b','b','$2a$10$A5s7M1sFdN.4iNURL3P5uue5hNjxFQmoUzqNOoxCT0YdWECkVnbWi',1,1),(33,'1','1','1','$2a$10$XUaa3aGyqsh/64RTwIDeAekuixY8l26bfANbRgIRc881t4uadfqRm',1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 20:47:16
