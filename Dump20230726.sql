CREATE DATABASE  IF NOT EXISTS `platformdatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `platformdatabase`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: platformdatabase
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
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `item_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (36,8,4),(37,9,17),(38,8,17);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `img_path` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `discount_per` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (2,'Jeans','/img/jeans-g62464c5bd_640.jpg',22,21),(3,'Potatoes (1kg)','/img/agriculture-g66e24b038_640.jpg',32,3),(4,'Shoes','/img/shoe-g0be8defea_640.jpg\n',183,2),(5,'Old car','/img/us-car-g259cc3a36_640.png',2310,34),(17,'Test2','/img/elephant-g7baf1f085_640.png',200,2);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (8,'UserTest1@student.ncirl.ie','1234'),(9,'UserTest2@student.ncirl.ie','1234');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `items` varchar(600) DEFAULT NULL,
  `negotiation_price` varchar(10) DEFAULT NULL,
  `sender` varchar(50) NOT NULL,
  `text` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (8,'[{\"id\":4,\"name\":\"Shoes\",\"imgPath\":\"/img/shoe-g0be8defea_640.jpg\\n\",\"price\":183,\"discountPer\":2},{\"id\":17,\"name\":\"Test2\",\"imgPath\":\"/img/elephant-g7baf1f085_640.png\",\"price\":200,\"discountPer\":2}]','280','UserTest1@student.ncirl.ie','Please reduce the price');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `payment` varchar(10) NOT NULL,
  `card_number` varchar(16) DEFAULT NULL,
  `items` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,1,'Miso','Rathmines','card','3332','[{\"id\":3,\"name\":\"Potatoes (1kg)\",\"imgPath\":\"/img/agriculture-g66e24b038_640.jpg\",\"price\":32,\"discountPer\":3},{\"id\":14,\"name\":\"Test\",\"imgPath\":\"/img/elephant-g7baf1f085_640.png\",\"price\":330,\"discountPer\":3}]'),(5,1,'Test1','Rathmines','card','11111','[{\"id\":4,\"name\":\"Shoes\",\"imgPath\":\"/img/shoe-g0be8defea_640.jpg\\n\",\"price\":183,\"discountPer\":2}]'),(6,1,'Miso','Rathmines','card','111222','[{\"id\":2,\"name\":\"Jeans\",\"imgPath\":\"/img/jeans-g62464c5bd_640.jpg\",\"price\":22,\"discountPer\":21},{\"id\":3,\"name\":\"Potatoes (1kg)\",\"imgPath\":\"/img/agriculture-g66e24b038_640.jpg\",\"price\":32,\"discountPer\":3},{\"id\":4,\"name\":\"Shoes\",\"imgPath\":\"/img/shoe-g0be8defea_640.jpg\\n\",\"price\":183,\"discountPer\":2}]'),(7,1,'Order','Maxwell Road','card','111','[{\"id\":3,\"name\":\"Potatoes (1kg)\",\"imgPath\":\"/img/agriculture-g66e24b038_640.jpg\",\"price\":32,\"discountPer\":3},{\"id\":17,\"name\":\"Test2\",\"imgPath\":\"/img/elephant-g7baf1f085_640.png\",\"price\":200,\"discountPer\":2}]'),(8,8,'OrderTest','2 Frankfort Avenue Rathgar Dublin','card','2212','[{\"id\":4,\"name\":\"Shoes\",\"imgPath\":\"/img/shoe-g0be8defea_640.jpg\\n\",\"price\":183,\"discountPer\":2}]'),(9,8,'OrderTest2','Acacia compound','bank','2222121','[{\"id\":2,\"name\":\"Jeans\",\"imgPath\":\"/img/jeans-g62464c5bd_640.jpg\",\"price\":22,\"discountPer\":21},{\"id\":3,\"name\":\"Potatoes (1kg)\",\"imgPath\":\"/img/agriculture-g66e24b038_640.jpg\",\"price\":32,\"discountPer\":3}]');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26 22:45:25
