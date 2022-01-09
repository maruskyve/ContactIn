-- MariaDB dump 10.19  Distrib 10.4.18-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: contactin
-- ------------------------------------------------------
-- Server version	10.4.18-MariaDB

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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL,
  `contact_phone_number` varchar(20) DEFAULT NULL,
  `contact_email` varchar(35) DEFAULT NULL,
  `contact_fname` varchar(50) DEFAULT NULL,
  `contact_lname` varchar(35) DEFAULT NULL,
  `contact_ppicture` varchar(100) DEFAULT NULL,
  `contact_stars` enum('1','0') DEFAULT NULL,
  `fk_contact_type_id` int(11) DEFAULT NULL,
  `fk_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `fk_contact_type_id_idx` (`fk_contact_type_id`),
  KEY `fk_user_id_idx` (`fk_user_id`),
  CONSTRAINT `fk_contact_type_id` FOREIGN KEY (`fk_contact_type_id`) REFERENCES `contact_type` (`contact_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (24,'picture.jpg','hnj ','gfhfhfh','rtyry','b6yy','0',3,72),(32,'ertyrryryituyi','tyuiyrty34534e','ttttttrete','eryututu','TE','0',3,398),(33,'picture.jpg','fjurn','bgvedrg g','14fgerg','eg','0',3,72),(48,'ouim','uiipon','nuim','yuim','NY','0',4,398),(197,'RE','eeee','eeee','rrrr','wwww','0',3,37578),(288,'picture.jpg','bbb','bbbb','bbbb','bbbb','0',3,72),(375,'picture.jpg','aleh','kjqewk@jkeuhwer','hela','91293165841','0',3,37578),(453,'picture.jpg','nuru3','ub56','cw246','b544y','0',3,72),(459,'bbbbb','bbbbb','bbbbbbb','bbbb','BB','0',4,37578),(487,'hdef','jdef','cdef','hdef','CH','0',3,398),(521,'picture.jpg','esom','qkwhne@wojuw.com','mose','9345789391','0',3,37578),(531,'000000','uhdihad@odjadj.com','FNAME','LNAME','FL','0',2,37578),(601,'13','313131','3113','123131','313131','0',2,37578),(679,'2222','22','1','22','12','0',1,37578),(827,'11','123','123','123','123','0',2,37578),(835,'00000','00000','666666','666666','66','0',2,398),(837,'yrefdi','urefdi','wrefdi','crefdi','WC','0',4,398),(845,'mA','auf','','meiry','','0',3,37578),(868,'9123218340','kjfhuiwh@fjfhi','CONTACT','CONTACT1','CC','0',3,398),(953,'3453541','hdhdh@342e','JAJAJAJA','G@#$@#$','updatedPicture.jpg','1',2,398),(985,'2','2','2','22','22','0',2,37578),(991,'ghert','hjghjhfj','fddfg','dgdhfghf','FD','0',2,398),(1231,'23425252342','sfsfd@wefrwr.hrh','BBBBB','8u6b676n','updatedPicture.jpg','1',2,398),(123151,NULL,'5345','AAAAA','6456','66666','0',2,72),(414123,NULL,NULL,'CCCCCC',NULL,'wff',NULL,2,72);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_type`
--

DROP TABLE IF EXISTS `contact_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_type` (
  `contact_type_id` int(11) NOT NULL,
  `contact_type_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_type`
--

LOCK TABLES `contact_type` WRITE;
/*!40000 ALTER TABLE `contact_type` DISABLE KEYS */;
INSERT INTO `contact_type` VALUES (1,'Family'),(2,'Friend'),(3,'Company'),(4,'Community');
/*!40000 ALTER TABLE `contact_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(70) DEFAULT NULL,
  `user_email` varchar(70) DEFAULT NULL,
  `user_phone_number` varchar(15) DEFAULT NULL,
  `user_password` varchar(30) DEFAULT NULL,
  `user_fname` varchar(35) DEFAULT NULL,
  `user_lname` varchar(35) DEFAULT NULL,
  `user_gender` enum('M','F') DEFAULT NULL,
  `user_ppicture` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (62,'maruf','  jolajsd@dmladsf','nusdfsf908955','','jojadu9021','iasdu90j9uf0s VVRSGF','M',''),(72,'123','123','123','','ddfsfs','dfsf','M',''),(398,'hedef','hedef@email.com','77777777','hedef','hedef','fedeh','F','pic.jpg'),(2994,'1','3','4','2','5','6','F','picture.jpg'),(3543,'1','111','1111','11','11111','111111','F','picture.jpg'),(9231,'username','useremail','userphone','userpassword','userfname','userlname','M','pic.jpg'),(21573,'4f34242f','2224','2f4v2424','34242f4','2v42424','2v424','M','picture.jpg'),(25473,'dsfv','sdfsf','asdad','M','dfgd','cvbcb','','picture.jpg'),(37578,'tirpitz','tirpitz@meiry.com','019827478963','TIRPITZ','tp','tz','M','pic.jpg'),(48836,'[[[','[[[[','[[[','[[[[','[[[','[[[','M','picture.jpg'),(53983,'btjhtjbtyjtyj','tyjtjt','tjt','yjtjtj','jtjtj','tj','F','picture.jpg'),(77905,'fellow666','093827428','fllow@jdef.com','fllo','oiwjrwofjo','mjui','M','picture.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-09 13:29:49
