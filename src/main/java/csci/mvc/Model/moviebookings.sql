-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 19, 2022 at 11:35 PM
-- Server version: 8.0.28
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moviebookings`
--

-- --------------------------------------------------------

--
-- Table structure for table `auditorium`
--

DROP TABLE IF EXISTS `auditorium`;
CREATE TABLE IF NOT EXISTS `auditorium` (
  `audID` int NOT NULL AUTO_INCREMENT,
  `audName` varchar(255) NOT NULL,
  `noOfSeats` int NOT NULL,
  PRIMARY KEY (`audID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
CREATE TABLE IF NOT EXISTS `bookings` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `showTimeID` int NOT NULL,
  `cardID` int NOT NULL,
  `noOfTickets` int NOT NULL,
  `totalPrice` decimal(10,0) NOT NULL,
  `promoID` int NOT NULL,
  `dateOfBooking` date NOT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `cardID_idx` (`cardID`),
  KEY `userID_idx` (`userID`),
  KEY `promoID_idx` (`promoID`),
  KEY `showtimeID_idx` (`showTimeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `movieID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `cast` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `producer` varchar(255) NOT NULL,
  `duration` time NOT NULL,
  `trailerPicture` varchar(255) DEFAULT NULL,
  `trailerVideo` varchar(255) DEFAULT NULL,
  `review` int NOT NULL,
  `ratingID` int NOT NULL,
  PRIMARY KEY (`movieID`),
  KEY `ratingID_idx` (`ratingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `paymentcard`
--

DROP TABLE IF EXISTS `paymentcard`;
CREATE TABLE IF NOT EXISTS `paymentcard` (
  `cardNo` int NOT NULL,
  `userID` int NOT NULL,
  `type` varchar(255) NOT NULL,
  `expirationDate` varchar(255) NOT NULL,
  `billingAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cardNo`),
  UNIQUE KEY `cardNo_UNIQUE` (`cardNo`),
  KEY `userID_idx` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
CREATE TABLE IF NOT EXISTS `promotions` (
  `promoID` int NOT NULL AUTO_INCREMENT,
  `promoCode` varchar(45) NOT NULL,
  `promoStart` date NOT NULL,
  `promoEnd` date NOT NULL,
  `promoDiscount` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`promoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
CREATE TABLE IF NOT EXISTS `seat` (
  `seatID` int NOT NULL AUTO_INCREMENT,
  `audID` int NOT NULL,
  PRIMARY KEY (`seatID`),
  KEY `audID_idx` (`audID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `showschedule`
--

DROP TABLE IF EXISTS `showschedule`;
CREATE TABLE IF NOT EXISTS `showschedule` (
  `showTimeID` int NOT NULL AUTO_INCREMENT,
  `showID` int NOT NULL,
  `showroomID` int NOT NULL,
  PRIMARY KEY (`showTimeID`),
  KEY `showID_idx` (`showID`),
  KEY `showroomID_idx` (`showroomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `ticketID` int NOT NULL AUTO_INCREMENT,
  `bookingID` int NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `date` date NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`ticketID`),
  KEY `bookingID_idx` (`bookingID`),
  KEY `type_idx` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickettype`
--

DROP TABLE IF EXISTS `tickettype`;
CREATE TABLE IF NOT EXISTS `tickettype` (
  `idtickettype` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`idtickettype`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tickettype`
--

INSERT INTO `tickettype` (`idtickettype`, `type`) VALUES
(1, 'Child'),
(2, 'Adult'),
(3, 'Senior');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `enrolledforPromotions` tinyint NOT NULL,
  `userType` int NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UserID_UNIQUE` (`userID`),
  KEY `UserType_idx` (`userType`),
  KEY `userstatus_idx` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `firstName`, `lastName`, `email`, `password`, `phone`, `status`, `enrolledforPromotions`, `userType`) VALUES
(1, 'AdminFName', 'AdminLName', 'ewm92737@uga.edu', 'TeamB6', '0000000000', 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `userstatus`
--

DROP TABLE IF EXISTS `userstatus`;
CREATE TABLE IF NOT EXISTS `userstatus` (
  `statusID` int NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `userstatus`
--

INSERT INTO `userstatus` (`statusID`, `status`) VALUES
(1, 'Active'),
(2, 'Inactive'),
(3, 'Suspended');

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
CREATE TABLE IF NOT EXISTS `usertype` (
  `UserTypeID` int NOT NULL AUTO_INCREMENT,
  `UserTypeName` varchar(45) NOT NULL,
  PRIMARY KEY (`UserTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`UserTypeID`, `UserTypeName`) VALUES
(1, 'Admin'),
(2, 'Customer'),
(3, 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `usrating`
--

DROP TABLE IF EXISTS `usrating`;
CREATE TABLE IF NOT EXISTS `usrating` (
  `ratingID` int NOT NULL AUTO_INCREMENT,
  `ratingCode` varchar(255) NOT NULL,
  PRIMARY KEY (`ratingID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `usrating`
--

INSERT INTO `usrating` (`ratingID`, `ratingCode`) VALUES
(1, 'G'),
(2, 'PG'),
(3, 'PG-13'),
(4, 'R');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `cardID` FOREIGN KEY (`cardID`) REFERENCES `paymentcard` (`cardNo`),
  ADD CONSTRAINT `promoID` FOREIGN KEY (`promoID`) REFERENCES `promotions` (`promoID`),
  ADD CONSTRAINT `showtimeID` FOREIGN KEY (`showTimeID`) REFERENCES `showschedule` (`showTimeID`),
  ADD CONSTRAINT `uID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `ratingID` FOREIGN KEY (`ratingID`) REFERENCES `usrating` (`ratingID`);

--
-- Constraints for table `paymentcard`
--
ALTER TABLE `paymentcard`
  ADD CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `audID` FOREIGN KEY (`audID`) REFERENCES `auditorium` (`audID`);

--
-- Constraints for table `showschedule`
--
ALTER TABLE `showschedule`
  ADD CONSTRAINT `showID` FOREIGN KEY (`showID`) REFERENCES `movie` (`movieID`),
  ADD CONSTRAINT `showroomID` FOREIGN KEY (`showroomID`) REFERENCES `auditorium` (`audID`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `bookingID` FOREIGN KEY (`bookingID`) REFERENCES `bookings` (`bookingID`),
  ADD CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `tickettype` (`idtickettype`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `userstatus` FOREIGN KEY (`status`) REFERENCES `userstatus` (`statusID`),
  ADD CONSTRAINT `UserType` FOREIGN KEY (`userType`) REFERENCES `usertype` (`UserTypeID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
