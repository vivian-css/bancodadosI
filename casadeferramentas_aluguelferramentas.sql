-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: casadeferramentas
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `aluguelferramentas`
--

DROP TABLE IF EXISTS `aluguelferramentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluguelferramentas` (
  `Ferramentas` int NOT NULL AUTO_INCREMENT,
  `data_alug` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `situacao` varchar(20) NOT NULL,
  `modelof` varchar(150) NOT NULL,
  `numdeserie` varchar(30) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `idcli` int NOT NULL,
  `Ferramenta` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Ferramentas`),
  KEY `idcli` (`idcli`),
  CONSTRAINT `aluguelferramentas_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `cliente` (`idcli`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluguelferramentas`
--

LOCK TABLES `aluguelferramentas` WRITE;
/*!40000 ALTER TABLE `aluguelferramentas` DISABLE KEYS */;
INSERT INTO `aluguelferramentas` VALUES (1,'2023-07-25 22:40:51','Alugada','Martelo','NB54DFG',10.90,1,'Martelo de Bola'),(2,'2023-07-25 22:41:49','Disponivel','Martelo','PQB54DFG',8.90,1,'Martelo de unha');
/*!40000 ALTER TABLE `aluguelferramentas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-25 19:44:37
