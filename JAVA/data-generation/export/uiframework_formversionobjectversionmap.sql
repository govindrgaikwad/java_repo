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
-- Table structure for table `formversionobjectversionmap`
--

DROP TABLE IF EXISTS `formversionobjectversionmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formversionobjectversionmap` (
  `FormVersionObjectVersionMap` int(11) NOT NULL AUTO_INCREMENT,
  `FormDesignVersionID` int(11) NOT NULL,
  `ObjectVersionID` int(11) NOT NULL,
  PRIMARY KEY (`FormVersionObjectVersionMap`),
  KEY `FK_FormDesignVersionID_FormDesignVersion_FormDesignVersionID` (`FormDesignVersionID`),
  KEY `FK_ObjectVersionID_ObjectVersion_ObjectVersionID` (`ObjectVersionID`),
  CONSTRAINT `FK_FormDesignVersionID_FormDesignVersion_FormDesignVersionID` FOREIGN KEY (`FormDesignVersionID`) REFERENCES `formdesignversion` (`FormDesignVersionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ObjectVersionID_ObjectVersion_ObjectVersionID` FOREIGN KEY (`ObjectVersionID`) REFERENCES `objectversion` (`VersionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formversionobjectversionmap`
--

LOCK TABLES `formversionobjectversionmap` WRITE;
/*!40000 ALTER TABLE `formversionobjectversionmap` DISABLE KEYS */;
/*!40000 ALTER TABLE `formversionobjectversionmap` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-30 18:33:18
