CREATE DATABASE  IF NOT EXISTS `uiframework` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `uiframework`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: uiframework
-- ------------------------------------------------------
-- Server version	5.5.36-log

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
-- Table structure for table `objectversionattribxref`
--

DROP TABLE IF EXISTS `objectversionattribxref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objectversionattribxref` (
  `ObjVerID` int(11) NOT NULL AUTO_INCREMENT,
  `VersionID` int(11) NOT NULL,
  `OID` int(11) NOT NULL,
  `AttrID` int(11) NOT NULL,
  PRIMARY KEY (`ObjVerID`),
  KEY `Fk_ObjectVersion_Xref1` (`VersionID`),
  KEY `Fk_ObjectVersionXref3` (`AttrID`),
  KEY `Fk_ObjectVersion_Xref2` (`OID`),
  CONSTRAINT `Fk_ObjectVersionXref3` FOREIGN KEY (`AttrID`) REFERENCES `attribute` (`AttrID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_ObjectVersion_Xref1` FOREIGN KEY (`VersionID`) REFERENCES `objectversion` (`VersionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_ObjectVersion_Xref2` FOREIGN KEY (`OID`) REFERENCES `objectdefinition` (`OID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objectversionattribxref`
--

LOCK TABLES `objectversionattribxref` WRITE;
/*!40000 ALTER TABLE `objectversionattribxref` DISABLE KEYS */;
/*!40000 ALTER TABLE `objectversionattribxref` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-30 18:33:20
