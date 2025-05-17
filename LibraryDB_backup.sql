-- MySQL dump 10.13  Distrib 9.2.0, for macos14.7 (x86_64)
--
-- Host: localhost    Database: LibraryDB
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
  `TitleISBN` varchar(20) NOT NULL,
  `TitleDescription` varchar(255) DEFAULT NULL,
  `TitleBinding` varchar(50) DEFAULT NULL,
  `TitleLanguage` varchar(30) DEFAULT NULL,
  `TitleEdition` varchar(30) DEFAULT NULL,
  `SubjectAreas` varchar(100) DEFAULT NULL,
  `TitleAuthors` varchar(100) DEFAULT NULL,
  `BookStatus` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`TitleISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES ('ISBN12345','Database Systems Concepts','Hardcover','English','2nd','Computer Science','Thomas Connolly','Available'),('ISBN67890','Introduction to Algorithms','Paperback','English','3rd','Computer Science','Cormen, Leiserson, Rivest','Available');
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Borrow`
--

DROP TABLE IF EXISTS `Borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Borrow` (
  `BookID` int NOT NULL,
  `MemberID` int DEFAULT NULL,
  `TitleISBN` varchar(20) DEFAULT NULL,
  `BorrowDate` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  `DateReturned` date DEFAULT NULL,
  `OverdueStatus` varchar(20) DEFAULT NULL,
  `FineAmount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`BookID`),
  KEY `MemberID` (`MemberID`),
  KEY `TitleISBN` (`TitleISBN`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `Member` (`MemberID`),
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`TitleISBN`) REFERENCES `Book` (`TitleISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Borrow`
--

LOCK TABLES `Borrow` WRITE;
/*!40000 ALTER TABLE `Borrow` DISABLE KEYS */;
INSERT INTO `Borrow` VALUES (101,1,'ISBN12345','2025-04-01','2025-04-15',NULL,'Not Returned',NULL),(102,2,'ISBN67890','2025-04-05','2025-04-20','2025-04-18','Returned',NULL);
/*!40000 ALTER TABLE `Borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Member` (
  `MemberID` int NOT NULL,
  `MemberFName` varchar(50) DEFAULT NULL,
  `MemberLName` varchar(50) DEFAULT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `ExpirationDate` date DEFAULT NULL,
  `SSNNum` varchar(20) DEFAULT NULL,
  `CampusStreet` varchar(100) DEFAULT NULL,
  `CampusCity` varchar(50) DEFAULT NULL,
  `CampusState` varchar(20) DEFAULT NULL,
  `CampusZip` varchar(10) DEFAULT NULL,
  `HomeStreet` varchar(100) DEFAULT NULL,
  `HomeCity` varchar(50) DEFAULT NULL,
  `HomeState` varchar(20) DEFAULT NULL,
  `HomeZip` varchar(10) DEFAULT NULL,
  `FacultyID` int DEFAULT NULL,
  `BorrowCount` int DEFAULT NULL,
  `MemberType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'John','Doe','1234567890','2025-12-31','987-65-4321','123 College Ave','Queens','NY','11367','789 Main St','Brooklyn','NY','11230',1001,3,'Student '),(2,'Sarah','Lee','9876543210','2026-05-20','123-45-6789','456 Campus Rd','Bronx','NY','10453','321 Park Ave','Manhattan','NY','10001',1002,5,'Student '),(3,'Aniqa','Haque','2982028092','2026-12-31','673-55-6789','56 oak Street','Queens','NY','11300','41 oak Street','Queens','NY','11300',1003,2,'Faculty'),(4,'Dhara','Patel','1023349343','2026-07-31','573-99-5090','76 welcome Street','Long island city ','NY ','11003','59 welcome street ','Long Island City ','NY ','11003',1004,4,NULL),(6,'Alex','David','777-888-9999','2026-07-20','111874444','56 England Street','Bronx','NY','11245','59 Ack street','Bronx','NY','11234',NULL,NULL,'Student'),(7,'John','Noah','888-999-0000','2026-09-30','222875555','56 England Street','Bronx','NY','11245','27 Ack street','Bronx','NY','11234',NULL,NULL,'Student'),(10,'Afwan','Shaikh','1234567890','2026-12-31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Faculty');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `StaffID` int NOT NULL,
  `StaffFName` varchar(50) DEFAULT NULL,
  `StaffLName` varchar(50) DEFAULT NULL,
  `StaffRole` varchar(30) DEFAULT NULL,
  `StaffCampusStreet` varchar(100) DEFAULT NULL,
  `StaffCampusCity` varchar(50) DEFAULT NULL,
  `StaffCampusState` varchar(20) DEFAULT NULL,
  `StaffCampusZip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (5001,'Alice','Smith','Librarian','789 Library St','Queens','NY','11367'),(5002,'Robert','Brown','Assistant','654 Library Ave','Bronx','NY','10453');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'afwan','1234','Admin');
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

-- Dump completed on 2025-05-17  0:10:00
