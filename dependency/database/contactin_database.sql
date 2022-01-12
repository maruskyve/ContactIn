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
  `contact_id` varchar(20) NOT NULL,
  `contact_phone_number` varchar(20) DEFAULT NULL,
  `contact_email` varchar(35) DEFAULT NULL,
  `contact_fname` varchar(50) DEFAULT NULL,
  `contact_lname` varchar(35) DEFAULT NULL,
  `contact_ppicture` varchar(100) DEFAULT NULL,
  `contact_stars` enum('1','0') DEFAULT NULL,
  `fk_contact_type_id` int(11) DEFAULT NULL,
  `fk_user_id` varchar(20) DEFAULT NULL,
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
INSERT INTO `contact` VALUES ('20220110102359584316','02349083424','contact1@email.com','My','Contact1','MC','1',2,'20220110092943840602'),('20220110104253835157','08923487','contact2@email.com','My','Contact2','MC','0',1,'20220110092943840602'),('20220110111553126703','08123617846','hedef@email.com','Hedef','Fedeh','HF','0',1,'20220110103636164938'),('20220110111656644873','05934278432','wrefdi23@email.com','Wrefdi','Newww','WN','1',3,'20220110103636164938'),('20220110120539462655','082734627','contact1@email.com','contact1','contact1LN','CC','0',2,'20220110120347643869'),('20220110134922857393','09293847927','kontakbaru1@email.com','Kontak','Baru','KB','0',4,'20220110103636164938'),('20220110140305298177','0932894274','newcontact@email.com','New','Contact','NC','0',1,'20220110103636164938'),('20220110140428295363','094385912','newcontact1@email.com','New','Contact 1','NC','1',1,'20220110103636164938'),('20220110200059459562','0812736744','mycontact3@email.com','My','Contact Neww 3','MC','0',3,'20220110092943840602'),('20220110203341392892','08937249123','kontakbaruu2313@email.com','Kontak','Baruuu 1','KB','1',2,'20220110092943840602'),('20220110203714289825','876hn68','6','apus','89237h','A8','0',1,'20220110203458543342'),('20220110203724764747','n8h','8n','78hwner8q8bt8','bt86t','7B','0',1,'20220110203458543342'),('20220110203740085874','8n67t','n86','78','78n','77','0',1,'20220110203458543342'),('20220110203752784129','9m7y','97ny','8n6t','8n6t','88','0',3,'20220110203458543342'),('20220110203804352547','9myn9','9','78nyn8','8n7tt5v7br9n','78','0',4,'20220110203458543342'),('20220110205058270484','89ku9','9m9','pasfk9s-f','9lk80','P9','0',1,'20220110205040375692'),('20220110205104839730','6nt7','7n56','9m7y789','97my8','99','0',1,'20220110205040375692'),('20220110205112783507','6nbt7','76nt','234','86nt87nt8','28','0',1,'20220110205040375692'),('20220110210531516120','4242','42424','2342','4242','24','0',1,'20220110205040375692'),('20220110210618244502','sf','','sdf','sdf','SS','0',1,'20220110205040375692'),('20220110210704260748','534535','2342','345353','534','35','0',1,'20220110205040375692'),('20220110210716540312','ryryry','ryry','rtyry','ryryry','RR','0',1,'20220110205040375692'),('20220111195736691724','0812934421','kontakkunewww1@email.com','Kontakku','Neww','KN','1',2,'20220110092943840602'),('20220111195900606824','072647814184','ndknbk@email.com','Nama Depan Kontak','Nama Belakang Kontak','NN','0',1,'20220110092943840602'),('20220111214721904355','08923942738','kontakbarulagi1213@email.com','Kontak','Baru lagi','KB','1',2,'20220110103636164938');
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
  `user_id` varchar(20) NOT NULL,
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
INSERT INTO `user` VALUES ('20220110092943840602','hedef','237914689','hedef@email.com','hedef','hedef','fedeh','M','pic.jpg'),('20220110093216816702','noobmaster69','92386471','nmaster234@emai','noobpassword','Henry','Volker','M','picture.jpg'),('20220110093506680222','fathyhadmin','fathyh@admin.contactin.com','08923476122','5190411201','Fathyh','Qorie Prasetyo','M','picture.jpg'),('20220110093609280817','wisnuadmin','wisnu@admin.contactin.com','08297468146','5190411339','Wisnu','Arya Pratama','M','picture.jpg'),('20220110094018295705','novaadmin','nova@admin.contactin.com','08987236473','5190411340','Nova','Dwi Lestari','F','picture.jpg'),('20220110094330793332','marufadmin','maruf@admin.contactin.com','08762354272','5190411370','Maruf','Nurrochman','M','picture.jpg'),('20220110103636164938','superadmin','superadmin@admin.contactin.com','08884912342','superadminpw','Super','Admin','M','pic.jpg'),('20220110120347643869','user','user@email.com','0757567463','passworduser','user','userlastname','M','picture.jpg'),('20220110200609778416','imnewuser','imnewuser@email.com','08579426553','newPassword','New','User','M','picture.jpg'),('20220110203458543342','hapus','hapus@email.com','08294762374','hapus','Hapus','Supah','M','picture.jpg'),('20220110205040375692','mm','wrh78','0298349274','mm','YU','YUIYIY','M','pic.jpg');
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

-- Dump completed on 2022-01-11 23:08:38
