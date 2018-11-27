-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: probook
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ActiveTokens`
--

DROP TABLE IF EXISTS `ActiveTokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActiveTokens` (
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(300) DEFAULT NULL,
  `login_timestamp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActiveTokens`
--

LOCK TABLES `ActiveTokens` WRITE;
/*!40000 ALTER TABLE `ActiveTokens` DISABLE KEYS */;
INSERT INTO `ActiveTokens` VALUES (1,'3141f6a32bc6b73627157967ada18a29',1540459624),(1,'b1131a1accfc54889a18c3d57c89674e',1540460042),(2,'5cdc5921ad8a3d95ba6c4b3002e9b27d',1540460636),(3,'f9d4b8585d086c9e7418066b93caec91',1542798627),(3,'e276a6153f92b7c2aa0ad1048d0aab5b',1542803786),(3,'e0bb2e030b698f9be8bc622fcc41ccb1',1543033536),(3,'f244399fb05b761541ff5978c88b06a0',1543042201),(3,'0c67b6fe213933e17e9eeacb9a4f67af',1543043874),(3,'7d722e1da466d58d05bcd93d6ffcd795',1543109502),(3,'ca2823386e318c60c456179eeb87476f',1543110407),(3,'d7d731785153104ee07f0ed7e163ac31',1543110688),(3,'4b5f884461cccaf97e1674fc5fc21485',1543188504),(3,'348ceabb8893977f267d9f2fdc8762a5',1543284072),(4,'ae55cce0a3c88f70dfc10bb104923569',1543308034),(5,'2f5a3031c3dc911bbc3b62bbb54448d0',1543308090),(6,'7ce101bc51f0f6f67742edc00e6dfc7d',1543309047),(7,'297ecfc52073588073f5d2033a51cdff',1543309562);
/*!40000 ALTER TABLE `ActiveTokens` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `Ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ratings` (
  `id` varchar(255) NOT NULL,
  -- `title` varchar(300) DEFAULT NULL,
  -- `author` varchar(300) DEFAULT NULL,
  -- `synopsis` varchar(300) DEFAULT NULL,
  `rating` float DEFAULT '0',
  `vote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `is_review` tinyint(1) DEFAULT NULL,
  `book_id` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `order_timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
-- INSERT INTO `Orders` VALUES (1,1,0,1,4,1540460068),(2,1,1,11,3,1540460087),(3,3,1,1,1,1543049288),(4,3,0,NULL,1,1543156031),(5,3,0,NULL,1,1543245029);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reviews`
--

DROP TABLE IF EXISTS `Reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` float DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `username` varchar(300) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reviews`
--

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;
INSERT INTO `Reviews` VALUES (1,5,'This book has open my mind!! World Governments need to change their country\'s ideology to Nazi Imperialism!! HEIL HITLER!! Aufa Fuhrer!! Leben von Aufa, Make Aufa ist ein Vorbild, Aufa ist Konig',11,'misterjoko',1),(2,4,'aaa',1,'mans',3);
/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `cardnumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Koko Widodo','misterjoko','2019tetapjokowi@indonesia.com','adbf245ec953b6ba5a29d600a12e4e3c','Rumah Kaesang, Istana Presiden, Jakarta','08169696969',NULL),(2,'Rachel Park','kimmiso','kimmiso@seoulentertainment.com','e7750d4a7fd4c70f46c6da28900df35e','DG Enterprise 105, Seoul, South Korea','081395954095',NULL),(3,'Manasye','mans','manasyebukit@gmail.com','36685a5564f9d47bae8e07f7ccdc7b63','mans','090901920912',NULL),(4,'Manasye Bukit','wadidaw','wadidaw@wad.com','e7af287f7c896a07485ff47fed078512','Tubagus Ismail','81264822748',NULL),(5,'Manasye Bukit','adadad','manasyebukit2@gmail.com','93279e3308bdbbeed946fc965017f67a','Tubagus Ismail','81264822748',NULL),(6,'Pearl Martin','cipeh','xuvybaqulu@mailinator.com','f3ed11bbdb94fd9ebdefbaf646ab94d3','Ad ex quia omnis quis exercitation qui doloribus','0912891821',NULL),(7,'Alec Carrillo','qyxiv','gobatu@mailinator.net','f3ed11bbdb94fd9ebdefbaf646ab94d3','Consectetur qui voluptas laborum Molestiae minima qui aliqua Similique eveniet repellendus Laudantium','833134167308','4111111111111111');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-27 16:11:31
