-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2022 at 10:03 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `lot`
--

CREATE TABLE `lot` (
  `L_Date` int(11) NOT NULL,
  `P_ID` char(6) NOT NULL,
  `L_Exp` datetime NOT NULL,
  `L_Quantity` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order product`
--

CREATE TABLE `order product` (
  `OP_ID` char(6) NOT NULL,
  `SO_ID` char(6) NOT NULL,
  `OP_Quantity` int(5) NOT NULL,
  `OP_Price` int(7) NOT NULL,
  `P_ID` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `P_ID` char(6) NOT NULL,
  `P_Name` varchar(15) NOT NULL,
  `P_Quantity` int(5) NOT NULL,
  `P_Type` varchar(10) NOT NULL,
  `P_Price` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `PM_ID` int(11) NOT NULL,
  `PM_Name` varchar(20) NOT NULL,
  `PM_Description` varchar(150) NOT NULL,
  `P_ID` char(6) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sale order`
--

CREATE TABLE `sale order` (
  `SO_ID` char(6) NOT NULL,
  `SO_Date` datetime NOT NULL,
  `SO_Type` char(10) NOT NULL,
  `SO_Status` int(1) NOT NULL,
  `SO_Price` int(7) NOT NULL,
  `Username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `U_Name` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Phone` char(10) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Role` char(10) NOT NULL,
  `Postcode` char(10) NOT NULL,
  `U_Status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lot`
--
ALTER TABLE `lot`
  ADD PRIMARY KEY (`L_Date`,`P_ID`);

--
-- Indexes for table `order product`
--
ALTER TABLE `order product`
  ADD PRIMARY KEY (`OP_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`P_ID`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`PM_ID`);

--
-- Indexes for table `sale order`
--
ALTER TABLE `sale order`
  ADD PRIMARY KEY (`SO_ID`),
  ADD KEY `fk_sale order_user` (`Username`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sale order`
--
ALTER TABLE `sale order`
  ADD CONSTRAINT `fk_sale order_user` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
