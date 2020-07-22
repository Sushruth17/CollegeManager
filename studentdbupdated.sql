-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2020 at 12:30 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentdb`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_createUser` (IN `p_name` VARCHAR(20), IN `p_username` VARCHAR(20), IN `p_password` VARCHAR(20))  BEGIN
    if ( select exists (select 1 from tbl_user where user_username = p_username) ) THEN
     
        select 'Username Exists !!';
     
    ELSE
     
        insert into tbl_user
        (
            user_name,
            user_username,
            user_password
        )
        values
        (
            p_name,
            p_username,
            p_password
        );
     
    END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `branchinfo`
--

CREATE TABLE `branchinfo` (
  `id` int(11) NOT NULL,
  `Name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `branchinfo`
--

INSERT INTO `branchinfo` (`id`, `Name`) VALUES
(1, 'CSE'),
(2, 'ECE'),
(3, 'ISE');

-- --------------------------------------------------------

--
-- Table structure for table `feesinfo`
--

CREATE TABLE `feesinfo` (
  `id` int(10) NOT NULL,
  `fees_status` varchar(10) NOT NULL,
  `actual_fee` int(11) NOT NULL,
  `fee_concession` int(11) NOT NULL,
  `amount_to_be_paid` int(10) NOT NULL,
  `amount_paid` int(10) NOT NULL,
  `amount_due` int(10) NOT NULL,
  `Sid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feesinfo`
--

INSERT INTO `feesinfo` (`id`, `fees_status`, `actual_fee`, `fee_concession`, `amount_to_be_paid`, `amount_paid`, `amount_due`, `Sid`) VALUES
(1, 'Paid', 10000, 0, 10000, 10000, 0, 6),
(2, 'Not Paid', 10000, 0, 10000, 5000, 5000, 7),
(3, 'Not Paid', 10000, 0, 10000, 1000, 9000, 9),
(6, 'Not Paid', 10000, 1000, 9000, 4000, 5000, 11),
(7, 'Not Paid', 10000, 500, 9500, 4500, 5000, 12),
(8, 'Paid', 10000, 0, 10000, 10000, 0, 13),
(9, 'Paid', 10000, 0, 10000, 10000, 0, 14);

-- --------------------------------------------------------

--
-- Table structure for table `marksinfo`
--

CREATE TABLE `marksinfo` (
  `id` int(11) NOT NULL,
  `Marks` int(11) DEFAULT NULL,
  `Sid` int(11) DEFAULT NULL,
  `Subid` int(11) DEFAULT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `marksinfo`
--

INSERT INTO `marksinfo` (`id`, `Marks`, `Sid`, `Subid`, `year`) VALUES
(21, 56, 6, 1, 20192020),
(22, 86, 6, 2, 20192020),
(23, 89, 6, 3, 20192020),
(24, 59, 6, 4, 20192020),
(26, 76, 7, 1, 20192020),
(27, 46, 7, 2, 20192020),
(28, 78, 7, 3, 20192020),
(29, 87, 7, 4, 20192020),
(34, 42, 9, 1, 20192020),
(35, 62, 9, 2, 20192020),
(36, 57, 9, 3, 20192020),
(37, 86, 9, 4, 20192020),
(42, 45, 11, 1, 20192020),
(43, 98, 11, 2, 20192020),
(44, 78, 11, 3, 20192020),
(45, 56, 11, 4, 20192020),
(46, 43, 12, 1, 20192020),
(47, 98, 12, 2, 20192020),
(48, 45, 12, 3, 20192020),
(49, 78, 12, 4, 20192020),
(50, 91, 13, 1, 20182019),
(51, 89, 13, 2, 20182019),
(52, 90, 13, 3, 20182019),
(53, 87, 13, 4, 20182019),
(54, 67, 14, 1, 20182019),
(55, 95, 14, 2, 20182019),
(56, 75, 14, 3, 20182019),
(57, 65, 14, 4, 20182019),
(58, 98, 15, 1, 20182019),
(59, 68, 15, 2, 20182019),
(60, 78, 15, 3, 20182019),
(61, 75, 15, 4, 20182019),
(62, 66, 16, 1, 20172018),
(63, 98, 16, 2, 20172018),
(64, 87, 16, 3, 20172018),
(65, 68, 16, 4, 20172018),
(66, 56, 17, 1, 20172018),
(67, 35, 17, 2, 20172018),
(68, 78, 17, 3, 20172018),
(69, 85, 17, 4, 20172018),
(70, 46, 18, 1, 20172018),
(71, 96, 18, 2, 20172018),
(72, 58, 18, 3, 20172018),
(73, 87, 18, 4, 20172018),
(74, 65, 19, 1, 20202021),
(75, 56, 19, 2, 20202021),
(76, 86, 19, 3, 20202021),
(78, 86, 19, 4, 20202021),
(79, 92, 20, 1, 20202021),
(80, 87, 20, 2, 20202021),
(81, 97, 20, 3, 20202021),
(82, 68, 20, 4, 20202021),
(83, 35, 27, 1, 20192020),
(84, 45, 27, 2, 20192020),
(85, 57, 27, 3, 20192020),
(86, 59, 27, 4, 20192020),
(87, 89, 35, 1, 20192020),
(88, 79, 35, 2, 20192020),
(89, 95, 35, 3, 20192020),
(90, 76, 35, 4, 20192020),
(91, 22, 36, 1, 20192020),
(92, 14, 36, 2, 20192020),
(93, 25, 36, 3, 20192020),
(94, 18, 36, 4, 20192020),
(97, 25, 37, 1, 20192020),
(100, 16, 37, 2, 20192020),
(101, 25, 37, 3, 20192020),
(102, 28, 37, 4, 20192020),
(103, 88, 38, 1, 20192020),
(104, 76, 38, 2, 20192020),
(105, 55, 38, 3, 20192020),
(106, 48, 38, 4, 20192020),
(107, 45, 39, 1, 20192020),
(108, 66, 39, 2, 20192020),
(109, 42, 39, 3, 20192020),
(110, 76, 39, 4, 20192020),
(111, 54, 40, 1, 20192020),
(112, 88, 40, 2, 20192020),
(113, 14, 40, 3, 20192020),
(114, 62, 40, 4, 20192020),
(115, 98, 41, 1, 20192020),
(116, 88, 41, 2, 20192020),
(117, 45, 41, 3, 20192020),
(118, 69, 41, 4, 20192020),
(119, 75, 42, 1, 20192020),
(120, 12, 42, 2, 20192020),
(121, 56, 42, 3, 20192020),
(122, 98, 42, 4, 20192020),
(123, 75, 43, 1, 20192020),
(124, 92, 43, 2, 20192020),
(125, 57, 43, 3, 20192020),
(126, 75, 43, 4, 20192020),
(127, 72, 44, 1, 20192020),
(128, 88, 44, 2, 20192020),
(129, 26, 44, 3, 20192020),
(130, 45, 44, 4, 20192020),
(131, 88, 45, 1, 20192020),
(132, 95, 45, 2, 20192020),
(133, 42, 45, 3, 20192020),
(134, 76, 45, 4, 20192020),
(135, 52, 46, 1, 20192020),
(136, 15, 46, 2, 20192020),
(137, 27, 46, 3, 20192020),
(138, 34, 46, 4, 20192020),
(139, 54, 47, 1, 20192020),
(140, 85, 47, 2, 20192020),
(141, 77, 47, 3, 20192020),
(142, 64, 47, 4, 20192020),
(143, 94, 48, 1, 20192020),
(144, 85, 48, 2, 20192020),
(145, 57, 48, 3, 20192020),
(146, 84, 48, 4, 20192020),
(147, 54, 49, 1, 20192020),
(148, 79, 49, 2, 20192020),
(149, 67, 49, 3, 20192020),
(150, 48, 49, 4, 20192020),
(151, 84, 50, 1, 20192020),
(152, 95, 50, 2, 20192020),
(153, 56, 50, 3, 20192020),
(154, 75, 50, 4, 20192020),
(155, 24, 51, 1, 20192020),
(156, 25, 51, 2, 20192020),
(157, 26, 51, 3, 20192020),
(158, 25, 51, 4, 20192020),
(159, 35, 52, 1, 20192020),
(160, 34, 52, 2, 20192020),
(161, 28, 52, 3, 20192020),
(162, 23, 52, 4, 20192020),
(163, 55, 53, 1, 20192020),
(164, 56, 53, 2, 20192020),
(165, 88, 53, 3, 20192020),
(166, 24, 53, 4, 20192020),
(167, 58, 54, 1, 20192020),
(168, 95, 54, 2, 20192020),
(169, 75, 54, 3, 20192020),
(170, 69, 54, 4, 20192020),
(171, 75, 55, 1, 20192020),
(172, 86, 55, 2, 20192020),
(173, 73, 55, 3, 20192020),
(174, 39, 55, 4, 20192020),
(175, 79, 56, 1, 20192020),
(176, 84, 56, 2, 20192020),
(177, 67, 56, 3, 20192020),
(178, 38, 56, 4, 20192020),
(179, 83, 57, 1, 20182019),
(180, 89, 57, 2, 20182019),
(181, 76, 57, 3, 20182019),
(182, 52, 57, 4, 20182019),
(183, 32, 58, 1, 20182019),
(184, 31, 58, 2, 20182019),
(185, 18, 58, 3, 20182019),
(186, 26, 58, 4, 20182019),
(187, 28, 59, 1, 20182019),
(188, 34, 59, 2, 20182019),
(189, 12, 59, 3, 20182019),
(190, 22, 59, 4, 20182019),
(191, 84, 60, 1, 20182019),
(192, 76, 60, 2, 20182019),
(193, 91, 60, 3, 20182019),
(194, 72, 60, 4, 20182019),
(195, 12, 62, 1, 20182019),
(196, 34, 62, 2, 20182019),
(197, 27, 62, 3, 20182019),
(198, 19, 62, 4, 20182019),
(199, 82, 63, 1, 20182019),
(200, 54, 63, 2, 20182019),
(201, 47, 63, 3, 20182019),
(202, 79, 63, 4, 20182019),
(203, 88, 64, 1, 20182019),
(204, 73, 64, 2, 20182019),
(205, 95, 64, 3, 20182019),
(206, 72, 64, 4, 20182019),
(207, 78, 65, 1, 20182019),
(208, 93, 65, 2, 20182019),
(209, 55, 65, 3, 20182019),
(210, 82, 65, 4, 20182019),
(211, 77, 66, 1, 20182019),
(212, 42, 66, 2, 20182019),
(213, 95, 66, 3, 20182019),
(214, 88, 66, 4, 20182019),
(215, 67, 67, 1, 20182019),
(216, 92, 67, 2, 20182019),
(217, 76, 67, 3, 20182019),
(218, 45, 67, 4, 20182019),
(219, 57, 68, 1, 20182019),
(220, 82, 68, 2, 20182019),
(221, 75, 68, 3, 20182019),
(222, 42, 68, 4, 20182019),
(223, 57, 69, 1, 20182019),
(224, 82, 69, 2, 20182019),
(225, 75, 69, 3, 20182019),
(226, 42, 69, 4, 20182019),
(227, 57, 70, 1, 20182019),
(228, 82, 70, 2, 20182019),
(229, 75, 70, 3, 20182019),
(230, 42, 70, 4, 20182019),
(231, 67, 71, 1, 20182019),
(232, 92, 71, 2, 20182019),
(233, 72, 71, 3, 20182019),
(234, 69, 71, 4, 20182019);

-- --------------------------------------------------------

--
-- Table structure for table `seminfo`
--

CREATE TABLE `seminfo` (
  `id` int(11) NOT NULL,
  `Sem` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seminfo`
--

INSERT INTO `seminfo` (`id`, `Sem`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `studentinfo`
--

CREATE TABLE `studentinfo` (
  `id` int(11) NOT NULL,
  `Name` varchar(40) DEFAULT NULL,
  `Address` varchar(40) DEFAULT NULL,
  `ParentName` varchar(40) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Bid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentinfo`
--

INSERT INTO `studentinfo` (`id`, `Name`, `Address`, `ParentName`, `Age`, `Bid`) VALUES
(6, 'Arun G', 'bnagar', 'Mcdonald', 21, 1),
(7, 'Thomas', 'Majestic', 'Chris gayle ', 30, 1),
(9, 'Daniel', 'chordroad', 'Burns', 20, 3),
(11, 'Oliver', 'krpuram', 'Craig', 20, 3),
(12, 'Bell', 'gnagar', 'Robertson', 21, 3),
(13, 'Benjamin', 'mysoreroad', 'Max', 20, 3),
(14, 'Nichols', 'dnagar', 'Christopher', 21, 3),
(15, 'Adam', 'devanahalli', 'Mills', 20, 2),
(16, 'Luke', 'shivajinagar', 'Nathan', 21, 2),
(17, 'Lewis', 'chikjala', 'Jake', 20, 2),
(18, 'Aaron', 'srirampura', 'Patel', 21, 2),
(19, 'Harry', 'ppnagar', 'Edward', 20, 2),
(20, 'Peter', 'kuvempuroad', 'Collins', 21, 2),
(27, 'Varun', 'MG Road', 'Kumar', 23, 2),
(35, 'Eoin', 'Dublin', 'Morgan', 22, 1),
(36, 'Moeen', 'Birmingham', 'Ali', 22, 1),
(37, 'Joe', 'Sheffield', 'Root', 22, 1),
(38, 'James', 'Burnley', 'Anderson', 22, 1),
(39, 'Jofra', 'Bridgetown', 'Archer', 22, 1),
(40, 'Jonny', 'Bradford', 'Bairstow', 22, 1),
(41, 'Tom', 'Chiltern', 'Banton', 22, 1),
(42, 'Dominic', 'Exeter', 'Bess', 22, 1),
(43, 'Sam', 'Pembury', 'Billings', 22, 1),
(44, 'Stuart', 'Nottingham', 'Broad ', 22, 1),
(45, 'Rory', 'Epsom', 'Burns', 22, 1),
(46, 'Jos', 'Taunton', 'Buttler', 22, 1),
(47, 'Zak', 'Bromley', 'Crawley', 22, 1),
(48, 'Sam', 'Northampton', 'Curran', 22, 1),
(49, 'Tom', 'Cape Town', 'Curran', 22, 1),
(50, 'Joe', 'Canterbury', 'Denly', 22, 1),
(51, 'Ben', 'Colchester', 'Foakes', 22, 1),
(52, 'Lewis', 'Plymouth', 'Gregory', 22, 1),
(53, 'Keaton', 'Johannesburg', 'Jennings', 22, 2),
(54, 'Chris', 'Lowlands', 'Jordan', 22, 2),
(55, 'Jack', 'Taunton', 'Leach', 22, 2),
(56, 'Saqib', 'Birmingham', 'Mahmood', 22, 2),
(57, 'Dawid', 'Roehampton', 'Malan', 22, 2),
(58, 'Craig', 'Barnstaple', 'Overton', 22, 2),
(59, 'Matthew', 'Bolton', 'Parkinson', 22, 2),
(60, 'Ollie', 'Chelsea', 'Pope', 22, 2),
(62, 'Adil', 'Bradford', 'Rashid', 22, 3),
(63, 'Ollie', 'Margate', 'Robinson', 22, 3),
(64, 'Jason', 'Durban', 'Roy', 22, 3),
(65, 'Dominic', 'Epsom', 'Sibley', 22, 3),
(66, 'Ben', 'Christchurch', 'Stokes', 22, 3),
(67, 'Olly', 'Norwich', 'Stone', 22, 3),
(68, 'James', 'Cuckfield', 'Vince', 22, 3),
(69, 'Chris', 'Birmingham', 'Woakes', 22, 3),
(70, 'Mark', 'Ashington', 'Wood ', 22, 3),
(71, 'Matthew', 'Lowlands', 'Wood ', 22, 3);

-- --------------------------------------------------------

--
-- Table structure for table `subjectinfo`
--

CREATE TABLE `subjectinfo` (
  `id` int(11) NOT NULL,
  `Name` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subjectinfo`
--

INSERT INTO `subjectinfo` (`id`, `Name`) VALUES
(1, 'English'),
(2, 'C'),
(3, 'Kannada'),
(4, 'java');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `user_username` varchar(45) NOT NULL,
  `user_email_id` varchar(45) NOT NULL,
  `user_phone_number` int(10) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  `user_type_id` int(11) NOT NULL,
  `user_status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `user_name`, `user_username`, `user_email_id`, `user_phone_number`, `user_password`, `user_type_id`, `user_status`) VALUES
(2, 'Sushruth S', 'sushruth.sushe@gmail', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(3, '123', '123', '123@gmail.com', NULL, 'pbkdf2:sha256:150000', 0, ' inactive'),
(5, '3456', '34545', '', NULL, 'pbkdf2:sha256:150000', 0, 'inactive'),
(6, '1234567', '1234567', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(7, 'asd', 'asd', 'asd@gmai.com', NULL, 'asd', 0, 'active'),
(8, 'ggg', 'ggg', '', NULL, 'ggg', 2, 'inactive'),
(9, 'Prajwal', 'prajwal@gmail.com', '', NULL, '123', 0, ''),
(11, 'efgh', 'efgh18', '', NULL, 'efgh', 0, ''),
(12, 'ef', 'ef18', '', NULL, 'ef', 0, ''),
(13, 'test4', 't4', 'test@gmail.com', NULL, '5566', 0, 'active'),
(20, NULL, '', 'test5', NULL, NULL, 0, 'not created'),
(43, NULL, '', 'ccc@gmail.com', NULL, NULL, 0, NULL),
(45, 'bbb', 'bbb', 'bbb@gmail.com', 1231231231, 'bbb', 2, 'active'),
(47, 'vvhh', 'vvh12', 'vvhh@gmail.com', 123456789, '123', 0, 'active'),
(48, NULL, '', 'asd@gmail.com', NULL, NULL, 0, 'not created'),
(49, NULL, '', '123456@gmail.com', NULL, NULL, 2, 'not created'),
(50, NULL, '', 'admin@new.com', NULL, NULL, 2, 'not created'),
(51, NULL, '', 'add123@admin.com', NULL, NULL, 2, 'not created');

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

CREATE TABLE `usertype` (
  `id` int(20) NOT NULL,
  `user_type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`id`, `user_type`) VALUES
(0, 'teacher'),
(1, 'HOD'),
(2, 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branchinfo`
--
ALTER TABLE `branchinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feesinfo`
--
ALTER TABLE `feesinfo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Sid` (`Sid`);

--
-- Indexes for table `marksinfo`
--
ALTER TABLE `marksinfo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Sid` (`Sid`),
  ADD KEY `Subid` (`Subid`);

--
-- Indexes for table `seminfo`
--
ALTER TABLE `seminfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentinfo`
--
ALTER TABLE `studentinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subjectinfo`
--
ALTER TABLE `subjectinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_email_id` (`user_email_id`,`user_username`),
  ADD KEY `user_type_id` (`user_type_id`);

--
-- Indexes for table `usertype`
--
ALTER TABLE `usertype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branchinfo`
--
ALTER TABLE `branchinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `feesinfo`
--
ALTER TABLE `feesinfo`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `marksinfo`
--
ALTER TABLE `marksinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=236;

--
-- AUTO_INCREMENT for table `seminfo`
--
ALTER TABLE `seminfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `studentinfo`
--
ALTER TABLE `studentinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `subjectinfo`
--
ALTER TABLE `subjectinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `usertype`
--
ALTER TABLE `usertype`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feesinfo`
--
ALTER TABLE `feesinfo`
  ADD CONSTRAINT `feesinfo_ibfk_1` FOREIGN KEY (`Sid`) REFERENCES `studentinfo` (`id`);

--
-- Constraints for table `marksinfo`
--
ALTER TABLE `marksinfo`
  ADD CONSTRAINT `marksinfo_ibfk_1` FOREIGN KEY (`Sid`) REFERENCES `studentinfo` (`id`),
  ADD CONSTRAINT `marksinfo_ibfk_2` FOREIGN KEY (`Subid`) REFERENCES `subjectinfo` (`id`);

--
-- Constraints for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `usertype` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
