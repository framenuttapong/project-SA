-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2022 at 06:20 AM
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
  `L_ID` int(1) NOT NULL,
  `L_Date` date NOT NULL,
  `P_ID` int(1) NOT NULL,
  `L_Exp` date NOT NULL,
  `L_Quantity` int(5) NOT NULL,
  `L_Status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

CREATE TABLE `order_product` (
  `OP_ID` int(1) NOT NULL,
  `OP_Quantity` int(5) NOT NULL,
  `OP_Price` int(7) NOT NULL,
  `P_ID` int(1) NOT NULL,
  `OP_Date` datetime NOT NULL,
  `OP_Type` int(1) NOT NULL DEFAULT 0,
  `OP_Status` int(1) NOT NULL DEFAULT 0,
  `Username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `P_ID` int(1) NOT NULL,
  `P_Name` varchar(15) NOT NULL,
  `P_Quantity` int(5) NOT NULL DEFAULT 0,
  `P_Type` varchar(10) NOT NULL,
  `P_Price` int(4) NOT NULL,
  `P_Image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`P_ID`, `P_Name`, `P_Quantity`, `P_Type`, `P_Price`, `P_Image`) VALUES
(1, 'Black pepper', 0, 'spices', 250, 'picture-2022-11-01-22-15-16.png'),
(2, 'Cinnamon', 0, 'spices', 280, 'picture-2022-11-01-22-21-12.png'),
(3, 'Ginger', 0, 'spices', 25, 'picture-2022-11-01-22-24-05.png'),
(4, 'Galangal', 0, 'spices', 30, 'picture-2022-11-01-22-24-41.png'),
(5, 'Cymbopogon', 0, 'spices', 20, 'picture-2022-11-01-22-26-54.png'),
(6, 'Kaffir lime', 0, 'vegetables', 50, 'picture-2022-11-01-22-27-22.png'),
(7, 'Chili pepper', 0, 'spices', 60, 'picture-2022-11-01-22-27-56.png'),
(8, 'Shallot', 0, 'spices', 120, 'picture-2022-11-01-22-28-16.png');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `PM_ID` int(1) NOT NULL,
  `PM_Name` varchar(20) NOT NULL,
  `PM_Description` varchar(150) NOT NULL,
  `P_ID` int(1) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL
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
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Password`, `U_Name`, `Email`, `Phone`, `Address`, `Role`, `Postcode`, `U_Status`) VALUES
('0', '0', 'Administrator', '-', '-', '-', 'Admin', '-', 0),
('1', '1', 'Manager', '-', '-', '-', 'Manager', '-', 1),
('2', '2', 'Customer', '-', '-', '-', 'Customer', '-', 1),
('4', '4', 'Employee', '-', '-', '-', 'Employee', '-', 0),
('Nobita000', '123456789', 'Doraemon', 'asdasdasdaasd', '0987654321', '1231213564', 'Manager', '10222', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lot`
--
ALTER TABLE `lot`
  ADD PRIMARY KEY (`L_ID`),
  ADD KEY `P_ID` (`P_ID`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`OP_ID`),
  ADD KEY `P_ID` (`P_ID`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`P_ID`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`PM_ID`),
  ADD KEY `P_ID` (`P_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lot`
--
ALTER TABLE `lot`
  MODIFY `L_ID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order_product`
--
ALTER TABLE `order_product`
  MODIFY `OP_ID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `P_ID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `PM_ID` int(1) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lot`
--
ALTER TABLE `lot`
  ADD CONSTRAINT `lot_ibfk_1` FOREIGN KEY (`P_ID`) REFERENCES `product` (`P_ID`);

--
-- Constraints for table `order_product`
--
ALTER TABLE `order_product`
  ADD CONSTRAINT `order_product_ibfk_1` FOREIGN KEY (`P_ID`) REFERENCES `product` (`P_ID`),
  ADD CONSTRAINT `order_product_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`P_ID`) REFERENCES `product` (`P_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
