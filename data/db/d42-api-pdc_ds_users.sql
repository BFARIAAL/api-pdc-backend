-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: d42-api-pdc
-- ------------------------------------------------------
-- Server version	5.7.32-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ds_users`
--

DROP TABLE IF EXISTS `ds_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ds_users` (
  `id` varchar(8) NOT NULL COMMENT 'User''s CCSID',
  `description` varchar(80) DEFAULT NULL COMMENT 'User''s name/description',
  `last_updated` date NOT NULL COMMENT 'Date in which this entry was last updated',
  `end_date` date DEFAULT NULL COMMENT 'A flag that if not null means the user has been archived',
  `user_1_id` varchar(45) NOT NULL COMMENT 'ID of the last user that updated this user',
  `scan_admin` varchar(1) DEFAULT 'N' COMMENT 'A flag to indicate if user is scan admin',
  `scan_user` char(1) DEFAULT 'N' COMMENT 'A flag to indicate if user is scan user',
  `privilege` int(11) DEFAULT NULL COMMENT 'A number that represents the user role',
  `default_location` varchar(45) DEFAULT NULL COMMENT 'The code of the user''s default location',
  `default_brand` varchar(5) DEFAULT NULL COMMENT 'The code of the user''s default brand',
  `default_carrier` int(11) DEFAULT NULL COMMENT 'The ID of the user''s default carrier',
  `user_group` int(11) NOT NULL DEFAULT '40' COMMENT 'The group the user is a part of',
  `last_accessed` date DEFAULT NULL COMMENT 'NOT IN USE',
  `default_language` varchar(5) NOT NULL DEFAULT 'en-GB' COMMENT 'The code for the user''s default language',
  `password` varchar(45) NOT NULL COMMENT 'Stores the user password (FOR DEVELOPMENT PURPOSE ONLY)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_1_id_idx` (`user_1_id`),
  KEY `Fk_Loc_idx` (`default_location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table responsible for storing users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_users`
--

LOCK TABLES `ds_users` WRITE;
/*!40000 ALTER TABLE `ds_users` DISABLE KEYS */;
INSERT INTO `ds_users` VALUES ('bfariaal','Bruno Faria Almeida','2023-07-05',NULL,'1','N','N',1,'SLC',NULL,NULL,40,'2023-08-09','en','test');
/*!40000 ALTER TABLE `ds_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-09 16:43:42
