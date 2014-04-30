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
-- Table structure for table `uielement`
--

DROP TABLE IF EXISTS `uielement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uielement` (
  `UIElementID` int(11) NOT NULL AUTO_INCREMENT,
  `UIElementName` varchar(100) DEFAULT NULL,
  `Label` varchar(500) DEFAULT NULL,
  `ParentUIElementID` int(11) DEFAULT NULL,
  `IsContainer` tinyint(1) NOT NULL,
  `Enabled` tinyint(1) DEFAULT NULL,
  `Visible` tinyint(1) DEFAULT NULL,
  `Sequence` int(11) NOT NULL,
  `RequiresValidation` tinyint(1) NOT NULL DEFAULT '1',
  `HelpText` varchar(2000) DEFAULT NULL,
  `AddedBy` varchar(20) DEFAULT NULL,
  `AddedDate` datetime NOT NULL,
  `IsActive` tinyint(1) NOT NULL,
  `FormID` int(11) NOT NULL,
  `UIElementDataTypeID` int(11) NOT NULL,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  `UpdatedDate` datetime DEFAULT NULL,
  `HasCustomRule` tinyint(1) DEFAULT '0',
  `GeneratedName` varchar(200) DEFAULT NULL,
  `DataSourceElementDisplayModeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UIElementID`),
  KEY `FK_UIElement_DataSourceElementDisplayMode` (`DataSourceElementDisplayModeID`),
  KEY `FK_UIElement_FormID` (`FormID`),
  KEY `FK_UIElement_UIElement` (`ParentUIElementID`),
  KEY `FK_UIElement_UIElementDataType` (`UIElementDataTypeID`),
  CONSTRAINT `FK_UIElement_DataSourceElementDisplayMode` FOREIGN KEY (`DataSourceElementDisplayModeID`) REFERENCES `datasourceelementdisplaymode` (`DataSourceElementDisplayModeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_UIElement_FormID` FOREIGN KEY (`FormID`) REFERENCES `formdesign` (`FormID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_UIElement_UIElement` FOREIGN KEY (`ParentUIElementID`) REFERENCES `uielement` (`UIElementID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_UIElement_UIElementDataType` FOREIGN KEY (`UIElementDataTypeID`) REFERENCES `applicationdatatype` (`ApplicationDataTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uielement`
--

LOCK TABLES `uielement` WRITE;
/*!40000 ALTER TABLE `uielement` DISABLE KEYS */;
/*!40000 ALTER TABLE `uielement` ENABLE KEYS */;
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
