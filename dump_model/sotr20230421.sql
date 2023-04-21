-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: sotr
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL DEFAULT '18',
  `name` varchar(50) NOT NULL,
  `pol` bit(1) NOT NULL DEFAULT b'1',
  `salary` double(10,2) NOT NULL DEFAULT '6500.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sd5rcqkto9rwnmx8g16o41af` (`name`),
  CONSTRAINT `int_salary` CHECK ((`salary` >= 6500)),
  CONSTRAINT `range_age` CHECK (((`age` >= 18) and (`age` <= 75)))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,35,'Zhuk',_binary '',120000.00),(2,55,'Kot',_binary '',55000.00),(3,34,'Gusin',_binary '',99000.00),(4,28,'Zhatova',_binary '\0',99000.00),(5,29,'Shatova',_binary '\0',99000.00),(6,32,'Svatok',_binary '',70000.00),(7,37,'Katz',_binary '',75000.00),(8,39,'Kotov',_binary '',55000.00),(9,33,'Lomov',_binary '',90000.00),(10,32,'Popova',_binary '\0',50000.00);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inns`
--

DROP TABLE IF EXISTS `inns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inns` (
  `id_inn` bigint NOT NULL AUTO_INCREMENT,
  `date_issued` date NOT NULL,
  `issued` varchar(255) NOT NULL,
  `number` bigint NOT NULL DEFAULT '1000000000',
  `id_employee` bigint NOT NULL,
  PRIMARY KEY (`id_inn`),
  UNIQUE KEY `UK_ltu4h7iy559xb9yxm9ibayfb4` (`number`),
  UNIQUE KEY `UK_53h4mubbhr7xn5v5r5xqgjgff` (`id_employee`),
  CONSTRAINT `FKdepptky1sb8kk5qsfb5a5q0na` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`),
  CONSTRAINT `min_number` CHECK ((`number` >= 1000000000))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inns`
--

LOCK TABLES `inns` WRITE;
/*!40000 ALTER TABLE `inns` DISABLE KEYS */;
INSERT INTO `inns` VALUES (1,'2006-02-01','Podatkova Shevchenkivskogo rajonu',2563474747,1),(2,'1986-12-02','Podatkova Kharkiv region',2563272727,2),(3,'2007-09-03','Podatkova Kharkiv region',3492767676,3),(4,'2013-07-06','Podatkova Shevchenkivskogo rajonu',2592292929,4),(5,'2012-04-28','Podatkova Kharkiv region',2533334433,5),(6,'2009-09-12','Podatkova Shevchenkivskogo rajonu',2593939393,6),(7,'2004-09-18','Podatkova Dergachivskogo rajonu',3562626262,7),(8,'2002-09-19','Podatkova Shevchenkivskogo rajonu',3583839092,8),(9,'2008-12-28','Podatkova Slobidskogo rajonu',2626552525,9),(10,'2009-04-02','Podatkova Dergachivskogo rajonu',3578787878,10);
/*!40000 ALTER TABLE `inns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `phone_number` varchar(10) NOT NULL,
  `number_type` varchar(10) NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3anxg73qq5wyh7mjihr2lfq54` (`phone_number`),
  KEY `FKa996in4mk9l1wgqpax8y1deij` (`employee_id`),
  CONSTRAINT `FKa996in4mk9l1wgqpax8y1deij` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (1,_binary '\0','0377218219','HOME',1),(2,_binary '','0416728127','HOME',1),(3,_binary '\0','0796817494','MOBILE',1),(4,_binary '','0105305855','OFFICE',2),(5,_binary '\0','0181504029','HOME',3),(6,_binary '\0','0230568094','MOBILE',4),(7,_binary '','0696773876','OFFICE',5),(8,_binary '','0983260773','MOBILE',5),(9,_binary '','0123196718','MOBILE',5),(10,_binary '\0','0549901655','OFFICE',6),(11,_binary '','0838941162','HOME',6),(12,_binary '','0812701456','OFFICE',7),(13,_binary '\0','0159426770','OFFICE',8),(14,_binary '\0','0526168973','OFFICE',8),(15,_binary '','0231087655','HOME',9),(16,_binary '','0609680920','OFFICE',10),(17,_binary '\0','0725896950','OFFICE',10),(18,_binary '','0587287202','OFFICE',10);
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pl` varchar(12) NOT NULL,
  `team_cod` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jv3kjxqst00johbpp36r15e94` (`team_cod`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'JAVA','Java001'),(2,'JavaScript','JavaScript'),(3,'JavaScript','JavaScript02'),(4,'JAVA','Java002'),(5,'CSHARP','CSharp2022');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_in_team`
--

DROP TABLE IF EXISTS `work_in_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_in_team` (
  `id_team` bigint NOT NULL,
  `work_end` date DEFAULT NULL,
  `work_start` date NOT NULL,
  `id_empl` bigint NOT NULL,
  PRIMARY KEY (`id_empl`,`id_team`),
  KEY `FKs5vmgejvk907g50y1cjo3fb98` (`id_team`),
  CONSTRAINT `FKnunp41rnx9njv2o9wytox947i` FOREIGN KEY (`id_empl`) REFERENCES `employees` (`id`),
  CONSTRAINT `FKs5vmgejvk907g50y1cjo3fb98` FOREIGN KEY (`id_team`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_in_team`
--

LOCK TABLES `work_in_team` WRITE;
/*!40000 ALTER TABLE `work_in_team` DISABLE KEYS */;
INSERT INTO `work_in_team` VALUES (1,'2022-11-22','2022-08-16',1),(4,NULL,'2023-04-21',1),(1,'2022-11-22','2022-08-16',2),(4,NULL,'2023-04-21',2),(1,'2022-11-22','2022-08-16',3),(4,NULL,'2023-04-21',3),(2,'2022-12-23','2022-09-15',4),(4,NULL,'2023-04-21',4),(2,'2022-12-23','2022-09-15',5),(4,NULL,'2023-04-21',5),(1,'2022-11-22','2022-08-16',6),(4,NULL,'2023-04-21',6),(1,'2022-11-22','2022-08-16',7),(4,NULL,'2023-04-21',7),(1,'2022-11-22','2022-08-16',8),(4,NULL,'2023-04-21',8),(1,'2022-11-22','2022-08-16',9),(4,NULL,'2023-04-21',9),(2,'2022-12-23','2022-09-15',10),(4,NULL,'2023-04-21',10);
/*!40000 ALTER TABLE `work_in_team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-21  7:46:02
