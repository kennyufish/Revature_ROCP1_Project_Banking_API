-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: bankapi
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `accountid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `balance` decimal(15,2) NOT NULL,
  `statusid` int NOT NULL,
  `typeid` int NOT NULL,
  PRIMARY KEY (`accountid`),
  UNIQUE KEY `accountid_UNIQUE` (`accountid`),
  KEY `statusid_idx` (`statusid`),
  KEY `typeid_idx` (`typeid`),
  KEY `username_idx` (`username`),
  CONSTRAINT `statusid` FOREIGN KEY (`statusid`) REFERENCES `accountstatus` (`statusid`),
  CONSTRAINT `typeid` FOREIGN KEY (`typeid`) REFERENCES `accounttype` (`typeid`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=100026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (100000,'admin',0.00,3,1),(100007,'standard',5272.00,2,1),(100008,'employee',12345.00,1,1),(100009,'melissa21',1235722.99,2,1),(100010,'andrewg123',12871237.00,2,1),(100011,'andrewg123',1231241231.00,2,1),(100012,'kennyyu30',0.00,4,1),(100013,'kennyyu30',1.00,1,1),(100014,'melissa21',0.00,1,1),(100015,'melissa21',-12342.00,3,1),(100016,'melissa21',827232.00,2,2),(100017,'standard',500.00,1,2),(100020,'test',123123.00,1,1),(100021,'standard',6034.00,2,1),(100025,'testnew',1234.00,1,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-15 21:17:39
