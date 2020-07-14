-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2020 at 04:16 PM
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
(82, 68, 20, 4, 20202021);

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
(27, 'Varun', 'MG Road', 'Kumar', 23, NULL);

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
(1, '', '', '', 0, 'pbkdf2:sha256:150000', 0, ''),
(2, 'Sushruth S', 'sushruth.sushe@gmail', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(3, '123', '123', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(4, 'abcd', 'abcd', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(5, '3456', '34545', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(6, '1234567', '1234567', '', NULL, 'pbkdf2:sha256:150000', 0, ''),
(7, 'asd', 'asd', '', NULL, 'asd', 0, 'active'),
(8, 'ggg', 'ggg', '', NULL, 'ggg', 2, ''),
(9, 'Prajwal', 'prajwal@gmail.com', '', NULL, '123', 0, ''),
(10, 'abcd', 'abcd17', '', NULL, 'abcd', 0, ''),
(11, 'efgh', 'efgh18', '', NULL, 'efgh', 0, ''),
(12, 'ef', 'ef18', '', NULL, 'ef', 0, ''),
(13, 'test4', 't4', 'test@gmail.com', NULL, '5566', 0, 'active'),
(20, NULL, '', 'test5', NULL, NULL, 0, 'not created'),
(43, NULL, '', 'ccc@gmail.com', NULL, NULL, 0, NULL),
(45, 'bbb', 'bbb', 'bbb@gmail.com', 1231231231, 'bbb', 2, 'active'),
(47, 'vvhh', 'vvh12', 'vvhh@gmail.com', 123456789, '123', 0, 'active'),
(48, NULL, '', 'asd@gmail.com', NULL, NULL, 0, 'not created');

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
-- AUTO_INCREMENT for table `marksinfo`
--
ALTER TABLE `marksinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `seminfo`
--
ALTER TABLE `seminfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `studentinfo`
--
ALTER TABLE `studentinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `subjectinfo`
--
ALTER TABLE `subjectinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `usertype`
--
ALTER TABLE `usertype`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

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
