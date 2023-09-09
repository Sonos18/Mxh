-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mxh
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `auction_id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `starting_price` decimal(10,2) DEFAULT NULL,
  `buyout_price` decimal(10,2) DEFAULT NULL,
  `winning_bid` decimal(10,2) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `winner_user_id` int DEFAULT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `product_id` (`product_id`),
  KEY `winner_user_id` (`winner_user_id`),
  KEY `auction_ibfk_2` (`post_id`),
  CONSTRAINT `auction_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `auction_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `auction_ibfk_3` FOREIGN KEY (`winner_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (18,'2023-08-30 00:00:00','2023-09-12 00:00:00',34.00,12.00,23.00,18,67,NULL),(19,'2023-09-14 00:00:00','2023-09-19 00:00:00',24.00,12.00,34.00,19,69,NULL),(20,'2023-09-01 00:00:00','2023-09-12 00:00:00',56.00,12.00,23.00,20,70,NULL),(21,'2023-09-02 00:00:00','2023-09-08 00:00:00',12.00,12.00,NULL,21,72,NULL),(22,'2023-09-02 00:00:00','2023-09-08 00:00:00',12.00,12.00,NULL,22,71,NULL),(23,'2023-09-09 00:00:00','2023-09-11 00:00:00',24.00,12.00,NULL,23,73,NULL);
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `parent_comment_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comments_ibfk_1` (`user_id`),
  KEY `comments_ibfk_2` (`post_id`),
  KEY `comments_ibfk_3` (`parent_comment_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'2023-09-08 07:32:39','hello',8,66,NULL),(11,'2023-09-09 04:27:14','say hi',5,66,NULL),(12,'2023-09-09 04:32:55','hello new2',5,68,NULL),(13,'2023-09-09 04:33:20','dfdf',5,68,NULL),(14,'2023-09-09 04:40:40','hdjfghuisdj ',5,66,NULL),(15,'2023-09-09 04:43:22','fisdugfyu',5,68,NULL),(16,'2023-09-09 04:46:48','sfsdhfdugf',5,68,NULL),(17,'2023-09-09 04:50:38','fbhdasgf',5,68,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtags`
--

DROP TABLE IF EXISTS `hashtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtags` (
  `hashtag_id` int NOT NULL AUTO_INCREMENT,
  `hashtag_text` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hashtag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtags`
--

LOCK TABLES `hashtags` WRITE;
/*!40000 ALTER TABLE `hashtags` DISABLE KEYS */;
INSERT INTO `hashtags` VALUES (1,'hay','2023-08-24 11:38:17'),(2,'hai','2023-08-24 11:38:17'),(3,'ba',NULL),(4,'bon',NULL),(5,'hot',NULL),(6,'#hi',NULL),(7,'#zero',NULL),(8,'#hello',NULL),(9,'#dvsdghigf',NULL),(10,'#hihi',NULL),(11,'#xinchao',NULL),(12,'#xinchao',NULL),(13,'#hieuthuhai',NULL),(14,'#test',NULL),(15,'#hehe',NULL),(16,'#sad',NULL),(17,'#djbfjd',NULL),(18,'#hai',NULL),(19,'#YV',NULL),(20,'#avbfgads',NULL),(21,'#n',NULL),(22,'#haha',NULL),(23,'#hdhas',NULL),(24,'#asbdugsduf',NULL),(25,'#yen',NULL),(26,'#dbfg',NULL),(27,'#dasdf',NULL),(28,'#sdfh',NULL),(29,'#fsdf',NULL),(30,'#fgdfug',NULL),(31,'#fsdfgsdg',NULL),(32,'#jj',NULL),(33,'#dfdfg',NULL),(34,NULL,NULL),(35,'#fdhjfg',NULL),(36,'#hay',NULL),(37,'#sdfnsdhfh',NULL),(38,'#sdfgbsgfyus',NULL),(39,'#dfhidhsuifh',NULL),(40,'#fgdgdfg',NULL),(41,'#dg',NULL),(42,'#au',NULL),(43,'#11',NULL),(44,NULL,NULL),(45,NULL,NULL),(46,NULL,NULL),(47,NULL,NULL),(48,NULL,NULL),(49,'#fgnidnug',NULL),(50,NULL,NULL),(51,'#new',NULL),(52,'#dfgdgkfdk',NULL),(53,'#fufu',NULL),(54,NULL,NULL),(55,NULL,NULL),(56,NULL,NULL),(57,NULL,NULL),(58,NULL,NULL);
/*!40000 ALTER TABLE `hashtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `likes_ibfk_2` (`post_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (28,NULL,8,66),(29,NULL,8,66),(30,NULL,8,68);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `action_type` enum('like','comment') DEFAULT NULL,
  `target_id` int DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  KEY `target_id` (`target_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`target_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (18,8,'like',66,1,'2023-09-08 05:46:56'),(19,8,'like',66,1,'2023-09-08 05:46:56'),(20,8,'like',68,1,'2023-09-08 05:55:42');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_hashtags`
--

DROP TABLE IF EXISTS `post_hashtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_hashtags` (
  `post_id` int NOT NULL,
  `hashtag_id` int NOT NULL,
  PRIMARY KEY (`post_id`,`hashtag_id`),
  KEY `hashtag_id` (`hashtag_id`),
  CONSTRAINT `post_hashtags_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_hashtags_ibfk_2` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtags` (`hashtag_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_hashtags`
--

LOCK TABLES `post_hashtags` WRITE;
/*!40000 ALTER TABLE `post_hashtags` DISABLE KEYS */;
INSERT INTO `post_hashtags` VALUES (66,51),(67,52),(68,53);
/*!40000 ALTER TABLE `post_hashtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  `isLocked` tinyint(1) DEFAULT '0',
  `isAuction` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (66,8,'bai moi\r\n','2023-09-08 05:46:50','2023-09-08 05:51:14','https://res.cloudinary.com/dakmujrnc/image/upload/v1694152009/arrwqywg96d9gja61ks6.jpg',0,0),(67,8,'sdfgdfgdf\r\n','2023-09-08 05:48:21','2023-09-08 05:51:14',NULL,0,1),(68,8,'new2','2023-09-08 05:55:33',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1694152532/qecedzqbjdvhys1fph8i.jpg',0,0),(69,5,'hihihaha','2023-09-09 06:13:24',NULL,NULL,0,1),(70,5,'sasdasefsdfsd','2023-09-09 14:22:10',NULL,NULL,0,1),(71,5,'fsdfsd','2023-09-09 15:32:04',NULL,NULL,0,1),(72,5,'fsdfsd','2023-09-09 15:32:04',NULL,NULL,0,1),(73,5,'xghfdhsfhj','2023-09-09 15:45:40',NULL,NULL,0,1);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'scren','fullhd',NULL),(2,'Iphone7','nice',NULL),(3,NULL,NULL,NULL),(4,NULL,NULL,NULL),(5,NULL,NULL,NULL),(6,NULL,NULL,NULL),(7,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062351/b2dcyny6l7topu2nekco.jpg'),(8,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062352/n2dbjdzjoyiwx0f5wl5y.jpg'),(9,'watch','hjhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063083/upiskgmq4dstqf3lxrgw.jpg'),(10,'fgdfg','dfgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063315/dd1x10qcfdt8jxpx1uhv.jpg'),(11,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064254/amt4sdtpss4fqzl6zd8y.jpg'),(12,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064295/fkkwqmakelbpqfvtwhfb.jpg'),(13,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064635/whjoyq9tvydd50453z1v.jpg'),(14,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064728/n2qfjykdcj2kpxep6jje.jpg'),(15,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066321/bqkiub9aymlxfw1xxgjv.jpg'),(16,'askd,foif','djmgfojg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066489/hxodo9h9ufxltzi4i6cx.jpg'),(17,'sdfgfdg','asfwerg#fgfdhhndgj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066649/pevljyfmqi9ch3dizg7l.jpg'),(18,'sp moi','dfsfadfgfdhgfhjgfhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694152102/nsqrbf7p7zjipzt4yjjf.jpg'),(19,'headphone','dfsdfsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694240006/av1asiiqdzpygmcx2dv5.jpg'),(20,'dfdf','dfsdfvcsxvsdf','https://res.cloudinary.com/dakmujrnc/image/upload/v1694269330/rhyfsodbug5983bdochr.jpg'),(21,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/xmoutcaekrgqimfrmb8u.jpg'),(22,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/nnu4zchymd6akywtmh1e.jpg'),(23,'graphic','asdfsedg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694274340/trsye1plrzqvyic8lkxw.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1','user1@example.com','password1','user','avatar1.jpg','2023-08-21 08:16:07'),(2,'user2','user2@example.com','password2','user','avatar2.jpg','2023-08-21 08:16:07'),(3,'admin','admin@example.com','adminpass','ADMIN','admin-avatar.jpg','2023-08-21 08:16:07'),(4,'tan','tan@ou.vn','$2a$10$lBAgtJw1LiOEEcvsok41V.AxZ8S5VMAkCWySBVvRg.xPOGOwwWOLW','USER','https://res.cloudinary.com/drhqwhn08/image/upload/v1692856879/wsigr2hxi4b9lgic6sdm.png',NULL),(5,'son','son@ou.vn','$2a$10$5LnwtOQ24QyFquEMqiinHe39Vkz93JXe1zwLESug1hx1Dib30QPnO','USER','https://res.cloudinary.com/drhqwhn08/image/upload/v1693025776/pcnwihfvmnpucdqqq5ak.png',NULL),(6,'user','u@user','$2a$10$Sk2fjUzmBseycQWrLujYK.QyjrQpcQhSw.MjYKs/ZKlXS8UPCA9OS','USER','https://res.cloudinary.com/drhqwhn08/image/upload/v1693579487/uytvioehzkvno3jwvbd5.png',NULL),(7,'usernew','u@new','$2a$10$MR4C6.yCHYNGLdzdhbFVXu29i.A3hLvUhCLXS5YmudECXZe1b9UyO','USER','https://res.cloudinary.com/drhqwhn08/image/upload/v1693581306/feij2vbe7ufwgsubg2we.png','2023-09-01 15:15:05'),(8,'user12','user@12','$2a$10$TIy0bLgLosQQcFxPLMtwSehHt46xCujNKoMxkRyQHNc7mraCbeZai','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693730572/evam0z8vepwvb7cfwkjz.jpg','2023-09-03 08:42:51'),(9,'yen','yen@ou','$2a$10$vNb5dneQ1YzmIO91UkYlqOcDrLw7xTUzUy21u4Auj6OMDa8H2RPWi','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693840669/r6d6lsr6aqpyakz1ih0l.png','2023-09-04 15:17:50'),(10,'ngocyen','ngoc@yen','$2a$10$YNl7czCWL.H7FaO2k/ysUOTrGqK4uvHFAHH3p0iss/CLbk0GFgv4i','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1693840733/vmineiotiw35jy1v5nms.png','2023-09-04 15:18:54');
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

-- Dump completed on 2023-09-09 23:22:11
