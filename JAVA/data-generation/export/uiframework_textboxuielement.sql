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
-- Table structure for table `textboxuielement`
--

DROP TABLE IF EXISTS `textboxuielement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `textboxuielement` (
  `UIElementID` int(11) NOT NULL,
  `UIElementTypeID` int(11) NOT NULL DEFAULT '3',
  `IsMultiline` tinyint(1) DEFAULT NULL,
  `DefaultValue` varchar(4000) DEFAULT NULL,
  `MaxLength` int(11) NOT NULL DEFAULT '50',
  `IsLabel` tinyint(1) NOT NULL DEFAULT '0',
  `SpellCheck` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`UIElementID`),
  KEY `FK_TextBoxUIElement_UIElementType` (`UIElementTypeID`),
  CONSTRAINT `FK_TextBoxUIElement_UIElement` FOREIGN KEY (`UIElementID`) REFERENCES `uielement` (`UIElementID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TextBoxUIElement_UIElementType` FOREIGN KEY (`UIElementTypeID`) REFERENCES `uielementtype` (`UIElementTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textboxuielement`
--

LOCK TABLES `textboxuielement` WRITE;
/*!40000 ALTER TABLE `textboxuielement` DISABLE KEYS */;
/*!40000 ALTER TABLE `textboxuielement` ENABLE KEYS */;
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
