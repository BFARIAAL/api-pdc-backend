-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
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
-- Table structure for table `carrier`
--

DROP TABLE IF EXISTS `carrier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrier` (
  `id` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrier`
--

LOCK TABLES `carrier` WRITE;
/*!40000 ALTER TABLE `carrier` DISABLE KEYS */;
INSERT INTO `carrier` VALUES ('ACUMEN','Acumen Distribution');
/*!40000 ALTER TABLE `carrier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `load_status`
--

DROP TABLE IF EXISTS `load_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `load_status` (
  `id` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `load_status`
--

LOCK TABLES `load_status` WRITE;
/*!40000 ALTER TABLE `load_status` DISABLE KEYS */;
INSERT INTO `load_status` VALUES ('BUILT','Load Built',NULL),('CANC','Load Canceled',NULL),('DELVRD','Load Delivered',NULL),('TRANS','Load In Transit',NULL);
/*!40000 ALTER TABLE `load_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loads`
--

DROP TABLE IF EXISTS `loads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loads` (
  `id` varchar(45) NOT NULL,
  `dep_loc` varchar(45) NOT NULL,
  `dest_loc` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `carrier` varchar(45) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `load_ref_UNIQUE` (`id`),
  KEY `Fk_dep_idx` (`dep_loc`,`dest_loc`),
  KEY `Fk_stat_idx` (`status`),
  KEY `Fk_dest_idx` (`dest_loc`),
  KEY `Fk_car_idx` (`carrier`),
  CONSTRAINT `Fk_car` FOREIGN KEY (`carrier`) REFERENCES `carrier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_dep` FOREIGN KEY (`dep_loc`) REFERENCES `locations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_dest` FOREIGN KEY (`dest_loc`) REFERENCES `locations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_stat` FOREIGN KEY (`status`) REFERENCES `load_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loads`
--

LOCK TABLES `loads` WRITE;
/*!40000 ALTER TABLE `loads` DISABLE KEYS */;
INSERT INTO `loads` VALUES ('BDC001','BDC','ZUKA','BUILT','ACUMEN',NULL),('SLC001','SLC','BDC','DELVRD','ACUMEN',NULL),('SLC002','SLC','ZUKA','CANC',NULL,NULL);
/*!40000 ALTER TABLE `loads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES ('BDC','Badesley Compound',NULL),('SLC','Solihul Compound',NULL),('ZUKA','Zone UK A',NULL);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_loads`
--

DROP TABLE IF EXISTS `vehicle_loads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_loads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `veh` int(11) NOT NULL,
  `load_ref` varchar(45) NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Fk_veh_idx` (`veh`),
  KEY `Fk_load_idx` (`load_ref`),
  CONSTRAINT `Fk_load` FOREIGN KEY (`load_ref`) REFERENCES `loads` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_veh` FOREIGN KEY (`veh`) REFERENCES `vehicles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_loads`
--

LOCK TABLES `vehicle_loads` WRITE;
/*!40000 ALTER TABLE `vehicle_loads` DISABLE KEYS */;
INSERT INTO `vehicle_loads` VALUES (1,1,'SLC001',NULL),(2,2,'SLC002',NULL),(3,2,'BDC001',NULL);
/*!40000 ALTER TABLE `vehicle_loads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `short_vin` varchar(8) DEFAULT NULL,
  `loc_code` varchar(45) NOT NULL,
  `end_date` date DEFAULT NULL,
  `status` enum('INTRANS','DLVRD','INLOAD','BUILT') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Fk_loc_idx` (`loc_code`),
  CONSTRAINT `Fk_loc` FOREIGN KEY (`loc_code`) REFERENCES `locations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (1,'LK003500','BDC',NULL,NULL),(2,'LK003501','BDC',NULL,NULL),(3,'LK003502','ZUKA',NULL,NULL);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-29  9:44:35
