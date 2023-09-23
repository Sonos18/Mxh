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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (25,'2023-09-11 00:00:00','2023-09-09 00:00:00',34.00,12.00,56.00,24,77,11),(26,'2023-09-11 00:00:00','2023-09-09 00:00:00',23.00,12.00,23.00,25,83,12),(27,'2023-09-11 00:00:00','2023-08-11 00:00:00',0.00,12.00,57.00,26,84,12),(28,'2023-09-10 00:00:00','2023-09-13 00:00:00',0.00,1.00,90.00,27,85,NULL),(29,'2023-09-07 00:00:00','2023-09-10 00:00:00',0.00,12.00,12.00,28,86,11),(30,'2023-09-11 00:00:00','2023-09-10 00:00:00',0.00,34.00,0.00,29,88,NULL),(31,'2023-09-18 00:00:00','2023-09-26 00:00:00',0.00,13.00,23.00,30,89,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (28,'2023-09-10 08:13:40','45',11,77,NULL),(29,'2023-09-10 14:38:35','nhà đẹp quá z ta ước có 1 căn như vậy quá',11,81,NULL),(30,'2023-09-10 16:12:00','12',11,77,NULL),(31,'2023-09-10 16:44:51','23',11,77,NULL),(32,'2023-09-10 16:46:37','56',11,77,NULL),(33,'2023-09-10 21:56:28','23',12,83,NULL),(34,'2023-09-10 22:47:33','12',12,84,NULL),(35,'2023-09-10 22:50:06','11',12,84,NULL),(36,'2023-09-10 22:54:09','23',11,84,NULL),(37,'2023-09-10 23:07:48','24',12,84,NULL),(38,'2023-09-10 23:07:53','25',12,84,NULL),(39,'2023-09-10 23:09:36','12',12,84,NULL),(40,'2023-09-10 23:10:15','12',12,84,NULL),(41,'2023-09-10 23:10:28','16',12,84,NULL),(42,'2023-09-10 23:10:35','34',12,84,NULL),(43,'2023-09-10 23:11:51','45',12,84,NULL),(44,'2023-09-10 23:12:12','56',12,84,NULL),(45,'2023-09-10 23:14:57','12',11,86,NULL),(46,'2023-09-10 23:34:31','57',12,84,NULL),(47,'2023-10-10 01:20:25','80',11,85,NULL),(48,'2023-09-11 02:59:38','chao ban',11,82,NULL),(49,'2023-09-11 02:59:45','hih',11,82,NULL),(50,'2023-09-11 03:01:08','90',11,85,NULL),(51,'2023-09-17 08:12:28','23',11,89,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtags`
--

LOCK TABLES `hashtags` WRITE;
/*!40000 ALTER TABLE `hashtags` DISABLE KEYS */;
INSERT INTO `hashtags` VALUES (59,'#number1',NULL),(60,'#number2',NULL),(61,'#sad',NULL),(62,'#huhu',NULL),(63,'#teachnology',NULL),(64,'#cold',NULL),(65,'#vuive',NULL),(66,'#xinhdep',NULL),(67,'#hatgiongtamhon',NULL),(68,'#donghoxin',NULL),(69,'#tivi',NULL),(70,'#mattien',NULL),(71,'#hochocnuahocmai',NULL),(72,'#4years',NULL),(73,NULL,NULL),(74,NULL,NULL),(75,NULL,NULL),(76,'#hi',NULL),(77,NULL,NULL),(78,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (37,'2023-09-10 06:52:12',12,76),(38,'2023-09-10 08:12:01',11,76),(41,'2023-09-10 14:40:02',11,81),(42,'2023-09-10 14:40:06',11,82);
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (30,12,'like',76,1,'2023-09-10 06:52:12'),(31,11,'like',76,0,'2023-09-10 08:12:01'),(32,11,'comment',77,0,'2023-09-10 08:13:40'),(35,11,'comment',81,0,'2023-09-10 14:38:35'),(36,11,'like',81,0,'2023-09-10 14:40:02'),(37,11,'like',82,1,'2023-09-10 14:40:06'),(38,12,'comment',83,1,'2023-09-10 21:56:28'),(39,12,'comment',84,1,'2023-09-10 22:47:33'),(40,11,'comment',84,1,'2023-09-10 22:54:09'),(41,11,'comment',86,0,'2023-09-10 23:14:57'),(42,11,'comment',85,0,'2023-09-11 01:20:25'),(43,11,'comment',82,1,'2023-09-11 02:59:38'),(44,11,'comment',89,0,'2023-09-17 08:12:28');
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
INSERT INTO `post_hashtags` VALUES (77,63),(76,65),(76,66),(79,67),(81,70),(82,71),(83,72);
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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (76,12,'Hoa đồng tiền\r\n\r\n','2023-09-10 06:49:48','2023-09-10 12:45:05','https://res.cloudinary.com/dakmujrnc/image/upload/v1694328587/kcvsx1dlc075cgkbhmqg.jpg',0,0),(77,12,'hãy theo đuổi đam mê\r\n','2023-09-10 06:54:56',NULL,NULL,0,1),(79,12,'Sách mới ra\r\n','2023-09-10 12:46:23',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1694349982/z1tif4wpxnc9jfgfxmzj.jpg',0,0),(81,12,'Nhà cao cấp\r\n \r\n \r\n \r\n ','2023-09-10 12:50:36','2023-09-10 13:08:41','https://res.cloudinary.com/dakmujrnc/image/upload/v1694351320/sdnpbe1dmqxsoukm83oc.jpg',0,0),(82,11,'Sách bổ ích\r\n','2023-09-10 13:10:53',NULL,'https://res.cloudinary.com/dakmujrnc/image/upload/v1694351452/f2csz1db56c3ljzbrxks.jpg',0,0),(83,11,'4 năm chỉ 1 lần','2023-09-10 21:55:39',NULL,NULL,0,1),(84,11,'jhsdhas','2023-09-10 22:33:41',NULL,NULL,0,1),(85,12,'sdsa','2023-09-10 23:13:11',NULL,NULL,0,1),(86,12,'asfdsf','2023-08-10 23:14:30','2023-09-11 01:14:22',NULL,0,1),(88,11,'fgudg','2023-09-11 03:00:18',NULL,NULL,0,1),(89,20,'hfishdi','2023-09-17 08:12:04',NULL,NULL,0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'scren','fullhd',NULL),(2,'Iphone7','nice',NULL),(3,NULL,NULL,NULL),(4,NULL,NULL,NULL),(5,NULL,NULL,NULL),(6,NULL,NULL,NULL),(7,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062351/b2dcyny6l7topu2nekco.jpg'),(8,'tenten','overhop','https://res.cloudinary.com/dakmujrnc/image/upload/v1694062352/n2dbjdzjoyiwx0f5wl5y.jpg'),(9,'watch','hjhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063083/upiskgmq4dstqf3lxrgw.jpg'),(10,'fgdfg','dfgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694063315/dd1x10qcfdt8jxpx1uhv.jpg'),(11,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064254/amt4sdtpss4fqzl6zd8y.jpg'),(12,'defer ','dfdsgdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064295/fkkwqmakelbpqfvtwhfb.jpg'),(13,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064635/whjoyq9tvydd50453z1v.jpg'),(14,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694064728/n2qfjykdcj2kpxep6jje.jpg'),(15,'defer ','dfdsgdfg#vhnfcjvh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066321/bqkiub9aymlxfw1xxgjv.jpg'),(16,'askd,foif','djmgfojg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066489/hxodo9h9ufxltzi4i6cx.jpg'),(17,'sdfgfdg','asfwerg#fgfdhhndgj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694066649/pevljyfmqi9ch3dizg7l.jpg'),(18,'sp moi','dfsfadfgfdhgfhjgfhj','https://res.cloudinary.com/dakmujrnc/image/upload/v1694152102/nsqrbf7p7zjipzt4yjjf.jpg'),(19,'headphone','dfsdfsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694240006/av1asiiqdzpygmcx2dv5.jpg'),(20,'dfdf','dfsdfvcsxvsdf','https://res.cloudinary.com/dakmujrnc/image/upload/v1694269330/rhyfsodbug5983bdochr.jpg'),(21,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/xmoutcaekrgqimfrmb8u.jpg'),(22,'sdgdfg','sdfgsdfg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694273524/nnu4zchymd6akywtmh1e.jpg'),(23,'graphic','asdfsedg','https://res.cloudinary.com/dakmujrnc/image/upload/v1694274340/trsye1plrzqvyic8lkxw.jpg'),(24,'headphone','chống rung quang học','https://res.cloudinary.com/dakmujrnc/image/upload/v1694328897/epb0zqiq5viadt92bptd.jpg'),(25,'house','mặt tiền','https://res.cloudinary.com/dakmujrnc/image/upload/v1694382941/x9czqm1gbrnvsjrpvgfd.jpg'),(26,'ádhnfjhdjs','jxbhcjh','https://res.cloudinary.com/dakmujrnc/image/upload/v1694385224/dd3cpkdugkuaqvrayeyd.jpg'),(27,'sdasd','asdasdas','https://res.cloudinary.com/dakmujrnc/image/upload/v1694387594/kkujgjpoohp770ctzbsz.jpg'),(28,'asdfasd','dfasfds','https://res.cloudinary.com/dakmujrnc/image/upload/v1694387672/hcmm1jjx4roulewy1fo9.jpg'),(29,'hjafohjiosdh','jdhfjihid','https://res.cloudinary.com/dakmujrnc/image/upload/v1694401220/f3jm2bhszujiruhmldtp.jpg'),(30,'wndj','dfnkjdn','https://res.cloudinary.com/dakmujrnc/image/upload/v1694938327/wvrvln4gxqbd4yyoiccu.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `reason` varchar(50) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `post_id` (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (11,'U1','2051052117son@ou.edu.vn','$2a$10$Ps4fwkzcSL31ntmJAA9k6Og/DWl1vcifFyPd93WHDfzJrAR1iPjZq','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1694327742/tqfysnl44dadulwdsxdv.jpg','2023-09-10 06:35:41'),(12,'U2','2051052117son@ou.edu.vn','$2a$10$0eLMDyccCp6TqNNZqfBk9OySay2YPmKqdCG1otCo4w7DfTDXeHKCC','ADMIN','https://res.cloudinary.com/dakmujrnc/image/upload/v1694327839/uvrmxc64hjlvkxnjsbxe.jpg','2023-09-10 06:37:16'),(20,'u3','son@123','$2a$10$hw1UUXjvkwBQIeY0xVwhSe2/DVsck6IjN/7p.kCl59JpWbUixOkgG','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1694937926/sd4wnbg20pvgo0vskmvr.jpg','2023-09-17 08:05:24'),(21,'yen','yen@ou','$2a$10$THdLP9uZCHZYHt0xkgpzWekmhREMlqSAaYY5rJslxHJyNeiGY8m6y','USER','https://res.cloudinary.com/dakmujrnc/image/upload/v1695281532/mookub21bkyvtahmjjp5.jpg','2023-09-21 07:32:13');
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

-- Dump completed on 2023-09-23 10:40:11
