-- MySQL dump 10.13  Distrib 5.7.23, for osx10.14 (x86_64)
--
-- Host: localhost    Database: probook
-- ------------------------------------------------------
-- Server version	5.7.23

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
  `user_agent` varchar(255) NOT NULL,
  `ip_address` varchar(20) NOT NULL,
  `expiration_timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActiveTokens`
--

LOCK TABLES `ActiveTokens` WRITE;
/*!40000 ALTER TABLE `ActiveTokens` DISABLE KEYS */;
INSERT INTO `ActiveTokens` VALUES (8,'e085e1ac2ff8c24253307ccf98b7c32d','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.1 Safari/605.1.15','::1',1543534785);
/*!40000 ALTER TABLE `ActiveTokens` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ratings`
--

DROP TABLE IF EXISTS `Ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ratings` (
  `id` varchar(255) NOT NULL,
  `rating` float DEFAULT '0',
  `vote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ratings`
--

LOCK TABLES `Ratings` WRITE;
/*!40000 ALTER TABLE `Ratings` DISABLE KEYS */;
INSERT INTO `Ratings` VALUES ('-dzJDAAAQBAJ',0,0),('0ENFDwAAQBAJ',0,0),('0w2Q6swN5BsC',0,0),('3Ntz-UJzZN0C',0,0),('4Jo9DwAAQBAJ',0,0),('7_zn6VwOnJcC',0,0),('9aYEBwAAQBAJ',0,0),('Aaug_RnI-xQC',0,0),('aFmjOlog120C',0,0),('aPWsDAAAQBAJ',0,0),('bL7QZHtWvaUC',0,0),('CAlM6nNPcsgC',0,0),('cdCMDgAAQBAJ',0,0),('DKcWE3WXoj8C',0,0),('DoJQAAAAMAAJ',0,0),('FkBPDwAAQBAJ',0,0),('Ht3JDAAAQBAJ',0,0),('iO5pApw2JycC',0,0),('iQmPNDIAskUC',0,0),('li5QDAAAQBAJ',0,0),('Mr48DwAAQBAJ',0,0),('MZRRDwAAQBAJ',0,0),('NP1wDwAAQBAJ',0,0),('OyB4llvAoXQC',0,0),('QXojiwovK7oC',0,0),('SEFjcIpP20wC',0,0),('sIENKoFHwdgC',0,0),('ttaMIFv8bv8C',0,0),('uVVSDwAAQBAJ',0,0),('VE0LqD085eMC',0,0),('vSLlCwAAQBAJ',0,0),('vz-LEFRT8E0C',0,0),('wQEjrc7Qrj8C',0,0),('WV8pZj_oNBwC',0,0),('xVBbEzRjBZoC',0,0),('xVStCQAAQBAJ',0,0),('yc2YZFRjcAQC',0,0),('YJZGDwAAQBAJ',0,0),('YnGz2ghKF-gC',0,0),('ZoF06z4dhQ4C',0,0);
/*!40000 ALTER TABLE `Ratings` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Koko Widodo','misterjoko','2019tetapjokowi@indonesia.com','adbf245ec953b6ba5a29d600a12e4e3c','Rumah Kaesang, Istana Presiden, Jakarta','08169696969',NULL),(2,'Rachel Park','kimmiso','kimmiso@seoulentertainment.com','e7750d4a7fd4c70f46c6da28900df35e','DG Enterprise 105, Seoul, South Korea','081395954095',NULL),(3,'Manasye','mans','manasyebukit@gmail.com','36685a5564f9d47bae8e07f7ccdc7b63','mans','090901920912',NULL),(4,'Manasye Bukit','wadidaw','wadidaw@wad.com','e7af287f7c896a07485ff47fed078512','Tubagus Ismail','81264822748',NULL),(5,'Manasye Bukit','adadad','manasyebukit2@gmail.com','93279e3308bdbbeed946fc965017f67a','Tubagus Ismail','81264822748',NULL),(6,'Pearl Martin','cipeh','xuvybaqulu@mailinator.com','f3ed11bbdb94fd9ebdefbaf646ab94d3','Ad ex quia omnis quis exercitation qui doloribus','0912891821',NULL),(7,'Alec Carrillo','qyxiv','gobatu@mailinator.net','f3ed11bbdb94fd9ebdefbaf646ab94d3','Consectetur qui voluptas laborum Molestiae minima qui aliqua Similique eveniet repellendus Laudantium','833134167308','4111111111111111'),(8,'Abram Situmorang','abrampers','abram.perdanaputra@gmail.com','822da21a8c864760260f54f10c6a45bf','Jalan Cinere','081298811177','4111111111111111');
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

-- Dump completed on 2018-11-29 14:00:47
