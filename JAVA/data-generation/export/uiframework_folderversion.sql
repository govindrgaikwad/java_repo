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
-- Table structure for table `folderversion`
--

DROP TABLE IF EXISTS `folderversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folderversion` (
  `FolderVersionID` int(11) NOT NULL AUTO_INCREMENT,
  `FolderID` int(11) NOT NULL,
  `EffectiveDate` datetime NOT NULL,
  `WFStateID` int(11) NOT NULL,
  `FolderVersionNumber` varchar(50) NOT NULL,
  `VersionTypeID` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '0',
  `TenantID` int(11) NOT NULL,
  `AddedDate` datetime NOT NULL,
  `AddedBy` varchar(20) NOT NULL,
  `UpdatedDate` datetime DEFAULT NULL,
  `UpdatedBy` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FolderVersionID`),
  KEY `FK_FolderVersion_VersionType` (`VersionTypeID`),
  KEY `FK_FolderVersion_Tenant` (`TenantID`),
  KEY `FK_FolderVersion_Folder` (`FolderID`),
  KEY `FK_FolderVersion_WorkFlowState` (`WFStateID`),
  CONSTRAINT `FK_FolderVersion_Folder` FOREIGN KEY (`FolderID`) REFERENCES `folder` (`FolderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FolderVersion_Tenant` FOREIGN KEY (`TenantID`) REFERENCES `tenant` (`TenantID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FolderVersion_VersionType` FOREIGN KEY (`VersionTypeID`) REFERENCES `versiontype` (`VersionTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FolderVersion_WorkFlowState` FOREIGN KEY (`WFStateID`) REFERENCES `workflowstate` (`WFStateID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folderversion`
--

LOCK TABLES `folderversion` WRITE;
/*!40000 ALTER TABLE `folderversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `folderversion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-30 18:33:22
