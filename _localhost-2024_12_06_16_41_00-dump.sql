-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: malldatabase
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nickName` varchar(100) NOT NULL,
  `loginName` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cartItemId` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `goodsId` int NOT NULL,
  `goodsCount` int NOT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cartItemId`),
  UNIQUE KEY `userID` (`userID`,`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,1,1,1,'2024-12-01 17:58:18','2024-12-02 07:34:33'),(2,1,2,1,'2024-12-01 18:18:00','2024-12-02 07:34:41'),(3,1,3,1,'2024-12-01 18:34:40','2024-12-02 07:34:59'),(4,1,6,1,'2024-12-05 15:03:49','2024-12-05 16:23:21'),(5,1,5,1,'2024-12-05 16:22:37','2024-12-05 16:23:21'),(18,7,7,1,'2024-12-06 06:23:05','2024-12-06 06:23:05'),(19,7,2,1,'2024-12-06 06:23:18','2024-12-06 06:23:18'),(20,7,3,1,'2024-12-06 06:23:21','2024-12-06 06:23:21'),(21,7,5,1,'2024-12-06 06:33:55','2024-12-06 06:33:55'),(22,1,7,1,'2024-12-06 07:04:10','2024-12-06 07:04:10');
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryId` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  `categoryRank` int NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `categoryName` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'电脑',10,'2024-11-30 18:09:39'),(2,'家电',9,'2024-11-30 18:11:36'),(3,'手机',8,'2024-11-30 18:11:36'),(4,'衣服',7,'2024-11-30 18:11:36'),(5,'食品',6,'2024-11-30 18:11:36'),(6,'美妆',5,'2024-11-30 18:11:36'),(7,'图书',4,'2024-11-30 18:11:36'),(8,'家具',3,'2024-11-30 18:11:36'),(9,'办公',2,'2024-11-30 18:11:36'),(10,'通信',1,'2024-11-30 18:11:36'),(12,'相机',13,'2024-12-04 15:13:44'),(14,'手表',16,'2024-12-06 04:40:11');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `goodsId` int NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) NOT NULL,
  `goodsKind` varchar(255) NOT NULL,
  `sellingPrice` decimal(10,2) NOT NULL,
  `goodsCoverImg` varchar(255) NOT NULL,
  `goodsDetailContent` text,
  `originalPrice` decimal(10,2) NOT NULL,
  `goodsSellStatus` int NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `stockNum` int NOT NULL,
  `goodsIntro` varchar(255) DEFAULT NULL,
  `goodsCategoryId` int NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`goodsId`),
  KEY `goodsCategoryId` (`goodsCategoryId`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`goodsCategoryId`) REFERENCES `category` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'MacBook Pro 16英寸 M1 Pro芯片','电脑',10328.00,'https://mall-image-store.oss-cn-guangzhou.aliyuncs.com/phone1.jpeg','https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/goods-detail/fa24954da9025b01.jpg',8088.00,0,'平板电脑',200,'超高速M1 Pro或M1 Max芯片，带来颠覆性表项和惊人续航',1,'2024-12-01 09:10:09','2024-12-06 04:36:24'),(2,'Apple MacBook Pro','电脑',8886.00,'https://mall-image-store.oss-cn-guangzhou.aliyuncs.com/mbp-m2-202206.jpg','https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/goods-detail/macbook-pro-m2-1.jpg',6888.00,0,'平板电脑',200,'全新M2芯片现身，13英寸MacBook Pro从此实力大涨，电池续航最长达20小时。',1,'2024-12-01 09:18:06','2024-12-04 17:51:13'),(3,'HUAWEI P50 雪域白','手机',4388.00,'https://mall-image-store.oss-cn-guangzhou.aliyuncs.com/p50-white.png','https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/goods-detail/b94beab1debab233.jpg',3388.00,0,'移动手机',100,'万象双环设计，美学新生，原色双影像单元，搭载HarmonyOS2',3,'2024-12-01 09:26:20','2024-12-04 17:51:13'),(4,'iPhone 14 Pro 金色','手机',6888.00,'https://mall-image-store.oss-cn-guangzhou.aliyuncs.com/iphone-14-pro-gold.jpg','https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/goods-detail/iphone-14-pro-1.jpg',5388.00,0,'移动手机',120,'新增灵动岛功能，iphone的新玩法',3,'2024-12-01 09:34:40','2024-12-04 17:51:13'),(5,'美食大礼包','食品',50.00,'https://mall-image-store.cn-guangzhou.oss.aliyuncs.com/3.png?Expires=1734593409&OSSAccessKeyId=LTAI5tByNfR1QXW2fTvjvC2U&Signature=PSPNGhdMd7GgIR48ADJsW7MFZFM%3D','<p>米面油粮大礼包，大家快来买</p>',40.00,0,'花生',400,'好吃的美食大礼包，实惠又美味。',5,'2024-12-05 13:43:49','2024-12-05 13:43:49'),(6,'美食大礼包','食品',50.00,'https://mall-image-store.cn-guangzhou.oss.aliyuncs.com/3.png?Expires=1734593409&OSSAccessKeyId=LTAI5tByNfR1QXW2fTvjvC2U&Signature=PSPNGhdMd7GgIR48ADJsW7MFZFM%3D','<p>米面油粮大礼包</p>',40.00,0,'花生',400,'好吃的美食大礼包，实惠又美味。',5,'2024-12-05 13:45:51','2024-12-05 16:28:34'),(7,'华为智能手表','',3899.00,'https://mall-image-store.cn-guangzhou.oss.aliyuncs.com/watch.png?Expires=1734647353&OSSAccessKeyId=LTAI5tByNfR1QXW2fTvjvC2U&Signature=70IrU7noAC5zo5tT1wbAxqOozxc%3D','<p>华为一表万象，钛金属材质，独立通话，强劲续航，心脏与呼吸健康，卓然天成，刚毅沉稳。还搭载了小艺哦。</p>',2389.00,0,'智能手表',49,'华为一表万象，钛金属材质，独立通话，强劲续航，心脏与呼吸健康，卓然天成，刚毅沉稳。',14,'2024-12-06 04:43:15','2024-12-06 04:43:27');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderNo` int NOT NULL AUTO_INCREMENT,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `orderStatus` int NOT NULL,
  `userID` int NOT NULL,
  `cartItemIds` varchar(1000) NOT NULL,
  `payType` tinyint DEFAULT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderNo`),
  KEY `userID` (`userID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2024-12-02 11:15:06',0,1,'1,2',NULL,19214.00,'2024-12-02 11:15:06'),(2,'2024-12-02 11:18:09',4,1,'1,2',NULL,19214.00,'2024-12-05 08:21:39'),(3,'2024-12-02 11:23:40',4,1,'1,2',NULL,19214.00,'2024-12-05 08:21:39'),(4,'2024-12-02 11:23:56',3,1,'1,2',NULL,19214.00,'2024-12-05 08:21:43'),(5,'2024-12-02 11:25:50',3,1,'1,2',NULL,19214.00,'2024-12-05 08:21:43'),(6,'2024-12-02 11:26:55',0,1,'1,2',NULL,19214.00,'2024-12-02 11:26:55'),(7,'2024-12-02 11:28:15',0,1,'1,2',NULL,19214.00,'2024-12-02 11:28:15'),(8,'2024-12-02 11:43:02',1,1,'1,2,3',2,23602.00,'2024-12-02 11:43:03'),(9,'2024-12-02 11:45:23',1,1,'1,2',1,19214.00,'2024-12-02 11:45:25'),(10,'2024-12-02 11:46:59',1,1,'1,2,3',1,23602.00,'2024-12-02 11:47:00'),(11,'2024-12-03 06:45:15',1,1,'1',2,10328.00,'2024-12-03 06:45:16'),(12,'2024-12-05 10:01:41',0,1,'3',NULL,4388.00,'2024-12-05 10:01:41'),(13,'2024-12-05 10:05:19',1,1,'3',1,4388.00,'2024-12-05 10:08:32'),(14,'2024-12-05 15:05:01',3,1,'3,5',1,4438.00,'2024-12-05 16:29:31'),(15,'2024-12-05 16:30:03',2,1,'2,3,5,4,1',2,23702.00,'2024-12-05 16:30:38'),(16,'2024-12-06 06:31:00',2,7,'19,20,18',1,17173.00,'2024-12-06 07:02:50'),(17,'2024-12-06 06:34:04',4,7,'21',1,50.00,'2024-12-06 07:03:01'),(18,'2024-12-06 07:03:33',1,7,'19,21',1,8936.00,'2024-12-06 07:03:37'),(19,'2024-12-06 07:04:20',1,1,'22',2,3899.00,'2024-12-06 07:04:23');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history`
--

DROP TABLE IF EXISTS `user_browse_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_browse_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `goodsId` int NOT NULL,
  `browseTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userID` (`userID`,`goodsId`,`browseTime`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history`
--

LOCK TABLES `user_browse_history` WRITE;
/*!40000 ALTER TABLE `user_browse_history` DISABLE KEYS */;
INSERT INTO `user_browse_history` VALUES (12,1,1,'2024-12-02 01:33:48'),(13,1,1,'2024-12-02 01:33:54'),(14,1,1,'2024-12-02 01:36:21'),(15,1,1,'2024-12-02 01:36:59'),(16,1,1,'2024-12-02 01:42:40'),(17,1,1,'2024-12-02 01:44:50'),(18,1,1,'2024-12-02 01:46:27'),(19,1,1,'2024-12-02 01:47:03'),(20,1,1,'2024-12-02 01:47:48'),(23,1,1,'2024-12-02 01:51:32'),(26,1,1,'2024-12-02 01:58:15'),(27,1,1,'2024-12-02 01:58:18'),(28,1,1,'2024-12-02 02:09:58'),(30,1,1,'2024-12-02 02:18:06'),(31,1,1,'2024-12-02 02:23:28'),(33,1,1,'2024-12-02 02:24:46'),(34,1,1,'2024-12-02 02:26:29'),(35,1,1,'2024-12-02 02:29:04'),(37,1,1,'2024-12-02 02:29:08'),(38,1,1,'2024-12-02 02:29:44'),(39,1,1,'2024-12-02 02:34:04'),(41,1,1,'2024-12-02 02:34:29'),(43,1,1,'2024-12-02 02:34:32'),(45,1,1,'2024-12-02 02:35:00'),(46,1,1,'2024-12-02 02:35:14'),(47,1,1,'2024-12-02 02:39:22'),(49,1,1,'2024-12-02 02:39:26'),(51,1,1,'2024-12-02 02:41:20'),(52,1,1,'2024-12-02 02:41:46'),(53,1,1,'2024-12-02 02:41:47'),(54,1,1,'2024-12-02 02:41:51'),(55,1,1,'2024-12-02 02:47:48'),(57,1,1,'2024-12-02 02:47:52'),(58,1,1,'2024-12-02 02:48:33'),(60,1,1,'2024-12-02 02:52:39'),(61,1,1,'2024-12-02 02:52:42'),(62,1,1,'2024-12-02 02:52:49'),(63,1,1,'2024-12-02 02:55:03'),(65,1,1,'2024-12-02 02:55:06'),(66,1,1,'2024-12-02 02:57:37'),(67,1,1,'2024-12-02 03:01:34'),(69,1,1,'2024-12-02 03:01:37'),(70,1,1,'2024-12-02 03:03:47'),(72,1,1,'2024-12-02 03:03:52'),(73,1,1,'2024-12-02 03:05:44'),(75,1,1,'2024-12-02 03:05:46'),(76,1,1,'2024-12-02 03:06:35'),(78,1,1,'2024-12-02 03:06:39'),(79,1,1,'2024-12-02 03:08:47'),(80,1,1,'2024-12-02 03:08:48'),(81,1,1,'2024-12-02 03:08:50'),(82,1,1,'2024-12-02 03:10:32'),(83,1,1,'2024-12-02 03:11:36'),(85,1,1,'2024-12-02 03:13:02'),(87,1,1,'2024-12-02 03:13:36'),(88,1,1,'2024-12-02 03:17:00'),(90,1,1,'2024-12-02 03:17:25'),(91,1,1,'2024-12-02 03:18:13'),(92,1,1,'2024-12-02 03:18:14'),(103,1,1,'2024-12-06 00:24:12'),(24,1,2,'2024-12-02 01:52:01'),(25,1,2,'2024-12-02 01:58:15'),(29,1,2,'2024-12-02 02:18:00'),(50,1,2,'2024-12-02 02:39:34'),(93,1,2,'2024-12-02 15:42:55'),(96,1,2,'2024-12-05 00:56:44'),(100,1,2,'2024-12-06 00:24:01'),(101,1,2,'2024-12-06 00:24:05'),(112,1,2,'2024-12-06 15:27:16'),(44,1,3,'2024-12-02 02:34:40'),(94,1,3,'2024-12-02 15:43:02'),(102,1,3,'2024-12-06 00:24:07'),(21,1,4,'2024-12-02 01:47:58'),(22,1,4,'2024-12-02 01:51:32'),(95,1,4,'2024-12-02 15:43:06'),(98,1,5,'2024-12-06 00:22:37'),(97,1,6,'2024-12-05 23:03:49'),(99,1,6,'2024-12-06 00:23:57'),(111,1,7,'2024-12-06 15:04:10'),(106,7,2,'2024-12-06 14:23:18'),(107,7,3,'2024-12-06 14:23:21'),(108,7,3,'2024-12-06 14:23:31'),(109,7,3,'2024-12-06 14:23:37'),(110,7,5,'2024-12-06 14:33:55'),(104,7,7,'2024-12-06 14:22:11'),(105,7,7,'2024-12-06 14:23:05');
/*!40000 ALTER TABLE `user_browse_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) NOT NULL,
  `nickName` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `address` text,
  `password` varchar(255) NOT NULL,
  `lockedFlag` tinyint(1) NOT NULL DEFAULT '0',
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName` (`userName`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'cssc','cssc','1234567890','3165682184@qq.com','广州市华南理工大学','12345678',0,0,'2024-11-28 08:54:47','2024-12-02 07:47:49'),(2,'yanliwei','ylw',NULL,'fmni48.g1v@qq.com',NULL,'IiHwNCxyrIfCrwG',0,0,'2024-11-30 13:21:20','2024-12-03 16:13:00'),(7,'kkwo',NULL,'afuhiafsfw','aici2022@qq.com','广东省佛山市','123456789',0,0,'2024-12-06 04:45:54','2024-12-06 06:48:28');
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

-- Dump completed on 2024-12-06 16:41:01
