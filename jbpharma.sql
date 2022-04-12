-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2016 at 10:33 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jbpharma`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblautoinvoiceno`
--

CREATE TABLE IF NOT EXISTS `tblautoinvoiceno` (
  `fld_invoice_no` int(11) NOT NULL AUTO_INCREMENT,
  `fld_taken` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`fld_invoice_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10019 ;

--
-- Dumping data for table `tblautoinvoiceno`
--

INSERT INTO `tblautoinvoiceno` (`fld_invoice_no`, `fld_taken`) VALUES
(10000, 1),
(10001, 1),
(10002, 1),
(10003, 1),
(10004, 1),
(10005, 1),
(10006, 1),
(10007, 1),
(10008, 1),
(10009, 1),
(10010, 1),
(10011, 1),
(10012, 1),
(10013, 1),
(10014, 1),
(10015, 1),
(10016, 1),
(10017, 1),
(10018, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblcart`
--

CREATE TABLE IF NOT EXISTS `tblcart` (
  `fld_cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_invoice_no` varchar(15) NOT NULL,
  `fld_customer_code` varchar(255) NOT NULL,
  `fld_lot_no` text NOT NULL,
  `fld_tax` double NOT NULL DEFAULT '0',
  `fld_price` double NOT NULL DEFAULT '0',
  `fld_qty` int(11) NOT NULL,
  `fld_discount` decimal(10,0) NOT NULL DEFAULT '0',
  `fld_subtotal` double NOT NULL,
  `fld_expiry` date NOT NULL,
  PRIMARY KEY (`fld_cart_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `tblcart`
--

INSERT INTO `tblcart` (`fld_cart_id`, `fld_invoice_no`, `fld_customer_code`, `fld_lot_no`, `fld_tax`, `fld_price`, `fld_qty`, `fld_discount`, `fld_subtotal`, `fld_expiry`) VALUES
(1, '101', '3432', '202', 0, -100, 10, '0', -1000, '2016-12-02'),
(2, '10002', 'CUS100', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(4, '10001', '216', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(5, '10001', '216', '3', 0, -1, 1, '0', -1, '2017-12-02'),
(7, '10003', '123123', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(9, '10004', '2013', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(11, '10004', '2013', '3', 0, -7, 1, '0', -6.5, '2017-12-02'),
(12, '10005', '123123', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(15, '10006', 'CUS100', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(16, '10007', '123123', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(19, '10008', '216', '3', 0, -1, 1, '0', -1, '2017-12-02'),
(20, '10008', '216', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(21, '10009', 'CUS100', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(23, '10010', '123123', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(24, '10011', '343', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(26, '10012', '216', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(27, '10013', '343', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(28, '10013', '343', '3', 0, -1, 1, '0', -1, '2017-12-02'),
(30, '10014', '123123', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(31, '10014', '123123', '3', 0, -1, 1, '0', -1, '2017-12-02'),
(36, 'INV10016', 'CUS102', '204', 0, 1000, 2, '0', 2000, '2017-12-02'),
(41, 'INV10016', 'CUS102', '201', 0, 100, 18, '0', 1800, '2017-12-07'),
(42, 'INV10017', 'CUS103', '201', 0, 12, 12, '0', 144, '2017-12-02'),
(44, 'INV10017', 'CUS103', '204', 0, 1142.5, 2, '0', 2285, '2017-12-02');

-- --------------------------------------------------------

--
-- Table structure for table `tblcategory`
--

CREATE TABLE IF NOT EXISTS `tblcategory` (
  `fld_cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_catname` varchar(30) NOT NULL,
  PRIMARY KEY (`fld_cat_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `tblcategory`
--

INSERT INTO `tblcategory` (`fld_cat_id`, `fld_catname`) VALUES
(11, 'Medicine'),
(12, 'Medical Supply'),
(13, 'Dextrose'),
(14, 'Injectables'),
(15, 'IV Fluids');

-- --------------------------------------------------------

--
-- Table structure for table `tblcreatedpo`
--

CREATE TABLE IF NOT EXISTS `tblcreatedpo` (
  `fld_po_no` varchar(200) NOT NULL,
  `fld_subtotal` double DEFAULT NULL,
  `fld_tax` double DEFAULT NULL,
  `fld_gtotal` double DEFAULT NULL,
  `fld_po_status` varchar(10) NOT NULL DEFAULT 'Open',
  `fld_po_invoice` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblcreatedpo`
--

INSERT INTO `tblcreatedpo` (`fld_po_no`, `fld_subtotal`, `fld_tax`, `fld_gtotal`, `fld_po_status`, `fld_po_invoice`) VALUES
('', 0, 0, 0, 'Open', ''),
('12', 1149.28, 156.72, 1306, 'Closed', '101'),
('10012', 688.16, 93.84, 782, '', NULL),
('1231', 264, 36, 300, '', NULL),
('1231', 264, 36, 300, '', NULL),
('1212', NULL, NULL, NULL, 'Open', NULL),
('1004', NULL, NULL, NULL, 'Open', NULL),
('11', NULL, NULL, NULL, 'Closed', NULL),
('1008', NULL, NULL, NULL, 'Open', NULL),
('1005', NULL, NULL, NULL, 'Open', NULL),
('143', NULL, NULL, NULL, 'Open', NULL),
('10121', NULL, NULL, NULL, 'Open', NULL),
('10101', NULL, NULL, NULL, 'Open', NULL),
('101010', NULL, NULL, NULL, 'Open', NULL),
('110001', NULL, NULL, NULL, 'Closed', '1010'),
('110002', NULL, NULL, NULL, 'Closed', '1000'),
('110003', NULL, NULL, NULL, 'Closed', '100'),
('110004', NULL, NULL, NULL, 'Open', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tblcustomer`
--

CREATE TABLE IF NOT EXISTS `tblcustomer` (
  `fld_customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_customer_code` varchar(255) NOT NULL,
  `fld_lname` varchar(255) DEFAULT '',
  `fld_fname` varchar(255) DEFAULT '',
  `fld_mname` varchar(255) DEFAULT '',
  `fld_zone` varchar(255) NOT NULL DEFAULT '',
  `fld_st` varchar(255) NOT NULL,
  `fld_city` varchar(255) NOT NULL,
  `fld_province` varchar(255) NOT NULL,
  PRIMARY KEY (`fld_customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=104 ;

--
-- Dumping data for table `tblcustomer`
--

INSERT INTO `tblcustomer` (`fld_customer_id`, `fld_customer_code`, `fld_lname`, `fld_fname`, `fld_mname`, `fld_zone`, `fld_st`, `fld_city`, `fld_province`) VALUES
(1, '2013', 'Calimpong', 'Vanessa', 'Walakobalo', '6', 'Dsa', 'Dfaacsd', 'Dfasdfasdf'),
(7, '216', 'Udang', 'Gerz', 'Emata', '2', 'Rabanes', 'Cagayan De Oro Citty', 'Misamis Oriental'),
(9, '3123', '', '', 'Fasf Sdf Asdf As  ', '3', 'Velez', 'Cagayan De Oro ', 'Province'),
(10, '123123', '', '', 'Fasf Sdf Asdf As  ', '3', '3', '3', '3'),
(11, '343', '', '', 'Fdf', '3', 'fa', 'Sdf', 'Fs'),
(12, '3432', 'Eslabon', 'Maria', 'M', '3', 'Idonow', 'Cagayan De Oro City', 'Mis. Or'),
(100, 'CUS100', '', '', 'Gerz Hotel And Pharmacy', '', 'Rabanes', 'Cagayan De Oro', 'Misamis Oriental'),
(101, 'CUS101', 'Last', 'First', 'Middle', '', 'St', 'Ct', 'Pr'),
(102, 'CUS102', '', '', 'BRGY TEST', '', 'ST', 'CT', 'CPR'),
(103, 'CUS103', '', '', 'Df Asdf', '', 'St', 'T', 'St');

-- --------------------------------------------------------

--
-- Table structure for table `tblinvoice`
--

CREATE TABLE IF NOT EXISTS `tblinvoice` (
  `fld_total_invoice_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_invoice_no` varchar(15) NOT NULL,
  `fld_invoice_subtotal` double NOT NULL DEFAULT '0',
  `fld_invoice_tax` double NOT NULL DEFAULT '0',
  `fld_invoice_total` double NOT NULL,
  PRIMARY KEY (`fld_total_invoice_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `tblinvoice`
--

INSERT INTO `tblinvoice` (`fld_total_invoice_id`, `fld_invoice_no`, `fld_invoice_subtotal`, `fld_invoice_tax`, `fld_invoice_total`) VALUES
(16, '100101', 0, 0, 35.8),
(17, '10000', 0, 0, -180),
(18, '101', 0, 0, -1000),
(19, '10002', 0, 0, -41),
(20, '10001', 0, 0, -15),
(21, '10003', 0, 0, -145),
(22, '10004', 0, 0, -1512.1),
(23, '10005', 0, 0, -14),
(24, '10006', 0, 0, -4),
(25, '10007', 0, 0, -14),
(26, '10008', 0, 0, -15),
(27, '10009', 0, 0, -14),
(28, '10010', 0, 0, -2),
(29, '10011', 0, 0, -14),
(30, '10012', 0, 0, -2),
(31, '10013', 0, 0, -15),
(32, '0', 0, 0, 0),
(33, '10014', 0, 0, -15),
(34, 'INV10016', 0, 0, 13800),
(35, 'INV10015', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblinvoiceno`
--

CREATE TABLE IF NOT EXISTS `tblinvoiceno` (
  `fld_invoice_no` varchar(15) NOT NULL,
  `fld_invoice_date` date NOT NULL DEFAULT '0000-00-00',
  `fld_customer_code` varchar(255) DEFAULT NULL,
  `fld_invoice_type` varchar(10) NOT NULL,
  PRIMARY KEY (`fld_invoice_no`),
  UNIQUE KEY `fld_invoice_no` (`fld_invoice_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblinvoiceno`
--

INSERT INTO `tblinvoiceno` (`fld_invoice_no`, `fld_invoice_date`, `fld_customer_code`, `fld_invoice_type`) VALUES
('10000', '2016-11-08', '2013', 'Private'),
('10001', '2016-11-14', '216', 'Private'),
('10002', '2016-12-02', 'CUS100', 'Private'),
('10003', '2016-12-02', '123123', 'Private'),
('10004', '2016-12-02', '2013', 'Private'),
('10005', '2016-12-02', '123123', 'Private'),
('10006', '2016-12-02', 'CUS100', 'Private'),
('10007', '2016-12-02', '123123', 'Private'),
('10008', '2016-12-02', '216', 'Private'),
('10009', '2016-12-02', 'CUS100', 'Private'),
('10010', '2016-12-02', '123123', 'Private'),
('100101', '2016-11-08', '3123', 'Gov'),
('10011', '2016-12-02', '343', 'Private'),
('10012', '2016-12-02', '216', 'Private'),
('10013', '2016-12-02', '343', 'Private'),
('10014', '2016-12-02', '123123', 'Private'),
('101', '2016-12-21', '3432', 'Gov'),
('INV10015', '2016-12-07', 'CUS100', 'Private'),
('INV10016', '2016-12-07', 'CUS102', 'Private'),
('INV10017', '2016-12-08', 'CUS103', 'Private'),
('INV10018', '2016-12-09', '3432', 'Private');

-- --------------------------------------------------------

--
-- Table structure for table `tblitemdetails`
--

CREATE TABLE IF NOT EXISTS `tblitemdetails` (
  `fld_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_item_desc` varchar(100) NOT NULL,
  `fld_lot_no` text NOT NULL,
  `fld_catname` varchar(50) NOT NULL,
  `fld_manu_name` varchar(50) NOT NULL DEFAULT ' ',
  `fld_item_price` double DEFAULT '0',
  `fld_ven_price` double DEFAULT '0',
  `fld_reorder_point` int(11) NOT NULL,
  PRIMARY KEY (`fld_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=410 ;

--
-- Dumping data for table `tblitemdetails`
--

INSERT INTO `tblitemdetails` (`fld_item_id`, `fld_item_desc`, `fld_lot_no`, `fld_catname`, `fld_manu_name`, `fld_item_price`, `fld_ven_price`, `fld_reorder_point`) VALUES
(26, 'Alluminum Mag Susp 200mg 60ml', '1', 'Medicine', '-', 1, 0, 10),
(27, 'Alluminum Mag Tab 200mg 100s Shelogel', '2', 'Medicine', '-', 1, 0, 10),
(28, 'Alluminum Mag Susp 200mg 120ml Gel Malicid', '3', 'Medicine', '-', 1, 0, 10),
(29, 'Alluminum Mag Tab 200mg 100s Gel Malicid', '4', 'Medicine', '-', 1, 0, 10),
(30, 'Ambroxol Drops 6mg 15ml', '5', 'Medicine', '-', 1, 0, 10),
(31, 'Ambroxol Syr 15mg 60ml', '6', 'Medicine', '-', 1, 0, 10),
(32, 'Ambroxol Syr 15mg 60ml Amectuss', '7', 'Medicine', '-', 2, 3, 4),
(33, 'Ambroxol Tab 30mg 100s', '8', 'Medicine', '-', 1, 0, 10),
(34, 'Ambroxol Tab 30mg 100s Ambroflam', '9', 'Medicine', '-', 1, 0, 10),
(35, 'Amlodipine Besilate Tab 5mg 100s Blocopast', '10', 'Medicine', '-', 1, 0, 10),
(36, 'Amlodipine Besilate Tab 10mg 100s Blocopast', '11', 'Medicine', '-', 1, 0, 10),
(37, 'Amoxicillin Drops', '12', 'Medicine', '-', 1, 0, 10),
(38, 'Amoxicillin Susp 125mg 60ml Moxylor', '13', 'Medicine', '-', 1, 0, 10),
(39, 'Amoxicillin Susp 250 Mg 60ml Moxylor', '14', 'Medicine', '-', 1, 0, 10),
(40, 'Amoxicillin Cap 250mg 100s Vhellox', '15', 'Medicine', '-', 1, 0, 10),
(41, 'Amoxicillin Cap 500mg 100s Vhellox', '16', 'Medicine', '-', 1, 0, 10),
(42, 'Ascorbic Acid Drops', '17', 'Medicine', '-', 1, 0, 10),
(43, 'Ascorbic Acid Syr 100mg 60ml', '18', 'Medicine', '-', 1, 0, 10),
(44, 'Ascorbic Acid Syr 100mg 120ml Vitcee', '19', 'Medicine', '-', 1, 0, 10),
(45, 'Ascorbic Acid Tab 500mg 100s', '20', 'Medicine', '-', 1, 0, 10),
(46, 'Ascorbic Acid + Zinc Syr 60ml', '21', 'Medicine', '-', 1, 0, 10),
(47, 'Ascrobic Acid + Zinc Syr 120ml', '22', 'Medicine', '-', 1, 0, 10),
(48, 'Atenolol 50mg  Tab 20s Flamingo', '23', 'Medicine', '-', 1, 0, 10),
(49, 'Azythromycin Tab 500mg 4s', '24', 'Medicine', '-', 1, 0, 10),
(50, 'Bisacodyl Tab 5mg 100s', '25', 'Medicine', '-', 1, 0, 10),
(51, 'Captopril Tablet 25mg 100s', '26', 'Medicine', '-', 1, 0, 10),
(52, 'Carbocisteine Drops 50mg 15ml Marluxyn', '27', 'Medicine', '-', 1, 0, 10),
(53, 'Carbocisteine Syr 100mg 60ml Marluxyn', '28', 'Medicine', '-', 1, 0, 10),
(54, 'Carbocisteine Cap 500mg 100s Oflem', '29', 'Medicine', '-', 1, 0, 10),
(55, 'Carbocisteine Susp 250mg 60ml Oflem', '30', 'Medicine', '-', 1, 0, 10),
(56, 'Cefaclor Susp 125mg 60ml', '31', 'Medicine', '-', 1, 0, 10),
(57, 'Cefaclor Susp 250mg 60ml', '32', 'Medicine', '-', 1, 0, 10),
(58, 'Cefalexin Drops 100mg 15ml', '33', 'Medicine', '-', 1, 0, 10),
(59, 'Cefalexin Susp 125mg 60ml', '34', 'Medicine', '-', 1, 0, 10),
(60, 'Cefalexin Susp 250mg 60ml', '35', 'Medicine', '-', 1, 0, 10),
(61, 'Cefalexin Cap 250mg 100s', '36', 'Medicine', '-', 1, 0, 10),
(62, 'Cefalexin Cap 500mg 100s Exel', '37', 'Medicine', '-', 1, 0, 10),
(63, 'Cefixime 200mg Tab 10s', '38', 'Medicine', '-', 1, 0, 10),
(64, 'Cefuroxime Susp 125 50ml', '39', 'Medicine', '-', 1, 0, 10),
(65, 'Cefuroxime Susp 250mg 50ml', '40', 'Medicine', '-', 1, 0, 10),
(66, 'Cefuroxime Axetil Tab 500mg 10s', '41', 'Medicine', '-', 1, 0, 10),
(67, 'Cefalexin Cap 500mg 100s Falteria', '42', 'Medicine', '-', 1, 0, 10),
(68, 'Celecoxib Cap 200mg 30s Brecxib', '43', 'Medicine', '-', 1, 0, 10),
(69, 'Celecoxib Cap 400mg 100s Geocoxib', '44', 'Medicine', '-', 1, 0, 10),
(70, 'Cetirizine 10mg Tab 100s Irizine-10-Zetir', '45', 'Medicine', '-', 1, 0, 10),
(71, 'Cetirizine Oral Sol 1mg/5ml Dialix', '46', 'Medicine', '-', 1, 0, 10),
(72, 'Chloramphenicol 125mg 60ml Zinnet', '47', 'Medicine', '-', 1, 0, 10),
(73, 'Chloramphenicol Cap 250mg 100s', '48', 'Medicine', '-', 1, 0, 10),
(74, 'Chloramphenicol Cap 500mg 100s Medichlor', '49', 'Medicine', '-', 1, 0, 10),
(75, 'Chloramphenicol Ear Drops 0.5% 10ml', '50', 'Medicine', '-', 1, 0, 10),
(76, 'Chlorphenamine Tab 4mg 100s Riphen', '51', 'Medicine', '-', 1, 0, 10),
(77, 'Chlorphenamine Syr 2mg 60ml Riphen', '52', 'Medicine', '-', 1, 0, 10),
(79, 'Cimetidine Tab 200mg 100s', '54', 'Medicine', '-', 1, 0, 10),
(80, 'Cimetidine Tab 400mg 100s', '55', 'Medicine', '-', 1, 0, 10),
(81, 'Cinnarizing Tab 25mg 100s', '56', 'Medicine', '-', 1, 0, 10),
(82, 'Ciprofloxacin Tab 500mg 100s Ciprobach', '57', 'Medicine', '-', 1, 0, 10),
(83, 'Ciprofloxacin Tab 500mg 100s Flosicron', '58', 'Medicine', '-', 1, 0, 10),
(84, 'Clarithromycin 125mg 60ml', '59', 'Medicine', '-', 1, 0, 10),
(85, 'Clindamycin Cap 150mg 100s Kylezine', '60', 'Medicine', '-', 1, 0, 10),
(86, 'Clindamycin Cap 300 100s Schelle', '61', 'Medicine', '-', 1, 0, 10),
(87, 'Clopidogril 75mg Tab 100s', '62', 'Medicine', '-', 1, 0, 10),
(88, 'Cloxacillin Susp 125mg 60ml', '63', 'Medicine', '-', 1, 0, 10),
(89, 'Cloxacillin Susp 250mg 60ml', '64', 'Medicine', '-', 1, 0, 10),
(90, 'Cloxacillin Cap 500mg 100s', '65', 'Medicine', '-', 1, 0, 10),
(91, 'Cloxacillin Cap 500mg 100s Flactamasin', '66', 'Medicine', '-', 1, 0, 10),
(92, 'Co-amoxiclav Susp 125mg/31.25mg', '67', 'Medicine', '-', 1, 0, 10),
(93, 'Co-amoxiclav Susp 250mg/62.50mg', '68', 'Medicine', '-', 1, 0, 10),
(94, 'Co-amoxiclav Tab 500mg/125mg 10s Clavistan', '69', 'Medicine', '-', 1, 0, 10),
(95, 'Colchicine 500mg Tab 20s Goutnil', '70', 'Medicine', '-', 1, 0, 10),
(96, 'Colchicine 500mg Tab 10s Cocilone', '71', 'Medicine', '-', 1, 0, 10),
(97, 'Cotrimoxazole Susp 200mg 60ml Kathrex', '72', 'Medicine', '-', 1, 0, 10),
(98, 'Cotrimoxazole Susp 200mg 60ml Kylemed', '73', 'Medicine', '-', 1, 0, 10),
(99, 'Cotrimoxazole Susp 400mg 60ml', '74', 'Medicine', '-', 1, 0, 10),
(100, 'Cotrimoxazole Cap 400mg/80mg 100s', '75', 'Medicine', '-', 1, 0, 10),
(101, 'Cotrimoxazole Tab 400mg/80mg 100s Kathrex', '76', 'Medicine', '-', 1, 0, 10),
(102, 'Cotrimoxazole Tab 800mg/160mg 100s Kathrex', '77', 'Medicine', '-', 1, 0, 10),
(103, 'Cotrimoxazole Tab 400mg/80mg 100s Embatrim', '78', 'Medicine', '-', 1, 0, 10),
(104, 'Cotrimoxazole Tab 800mg/160mg 100s Diazole', '79', 'Medicine', '-', 1, 0, 10),
(105, 'Dexamethasone Tab 5mg 100s', '80', 'Medicine', '-', 1, 0, 10),
(106, 'Diclofenac Tab 50mg 100s Trifocid', '81', 'Medicine', '-', 1, 0, 10),
(107, 'Diclofenac Tab 50mg 100s Voren', '82', 'Medicine', '-', 1, 0, 10),
(108, 'Dicycloverin Syr 10mg 60ml Diaciel', '83', 'Medicine', '-', 1, 0, 10),
(109, 'Dicycloverine Tab 10mg 100s Diaceil', '84', 'Medicine', '-', 1, 0, 10),
(110, 'Diphenhydramine Syr 12.5mg 60ml', '85', 'Medicine', '-', 1, 0, 10),
(111, 'Diphenhydramine Cap 50mg 100s', '86', 'Medicine', '-', 1, 0, 10),
(112, 'Domperidone Tab 10mg 100s', '87', 'Medicine', '-', 1, 0, 10),
(113, 'Doxycycline Cap 100mg 100s', '88', 'Medicine', '-', 1, 0, 10),
(114, 'Enalapril Maleate 5mg Tab 100s', '89', 'Medicine', '-', 1, 0, 10),
(115, 'Enalapril Maleate 10mg Tab 100s', '90', 'Medicine', '-', 1, 0, 10),
(116, 'Erythromycin Eye Drops .5% 5mg/2ml', '91', 'Medicine', '-', 1, 0, 10),
(117, 'Erythromycin Tab 500mg 100s', '92', 'Medicine', '-', 1, 0, 10),
(118, 'Erythromycin Susp 200mg 60ml', '93', 'Medicine', '-', 1, 0, 10),
(119, 'Ferrous Sulfate Drops 15ml', '94', 'Medicine', '-', 1, 0, 10),
(120, 'Ferrous Sulfate Syr 150mg 60ml', '95', 'Medicine', '-', 1, 0, 10),
(121, 'Ferrous Sulfate+folic Acid Tab 325mg 100s', '96', 'Medicine', '-', 1, 0, 10),
(122, 'Ferrous Sulfate + Folic Acid + Vit. B-complex Tab  Foralivit 325mg 100s', '97', 'Medicine', '-', 1, 0, 10),
(123, 'Ferrous Sulfate + Folic Acid + Vit. B-complex Tab Vitafer-Ob 325mg 100s', '98', 'Medicine', '-', 1, 0, 10),
(125, 'Folic Acid Tab 5mg 100s Ameciron', '100', 'Medicine', '-', 1, 0, 10),
(126, 'Furazolidone 60ml Susp', '101', 'Medicine', '-', 1, 0, 10),
(127, 'Furosemide Tab 20mg 100s Dm', '102', 'Medicine', '-', 1, 0, 10),
(128, 'Furosemide Tab 40mg 100s', '103', 'Medicine', '-', 1, 0, 10),
(129, 'Gentamycin Ear/eye Drops 0.3% 5ml', '104', 'Medicine', '-', 1, 0, 10),
(130, 'Glibenclamide Tab 5mg 100s DM', '105', 'Medicine', '-', 1, 0, 10),
(131, 'Glibenclamide Tab 5mg 100s Debtan', '106', 'Medicine', '-', 1, 0, 10),
(132, 'Gliclazide 80mg Tab 100s', '107', 'Medicine', '-', 1, 0, 10),
(133, 'Hyoscine Butylbromide Tab 10g 100s', '108', 'Medicine', '-', 1, 0, 10),
(134, 'Ibuprofen Tab 400mg 100s', '109', 'Medicine', '-', 1, 0, 10),
(135, 'Ipatrium + Salbutamol Neb. 5s Lactolose 120ml', '110', 'Medicine', '-', 1, 0, 10),
(136, 'Lagundi Leaf 300mg Tab 100s', '111', 'Medicine', '-', 1, 0, 10),
(137, 'Lagundi Leaf Syr 60ml', '112', 'Medicine', '-', 1, 0, 10),
(138, 'Lagundi Syrup 300mg 60ml', '113', 'Medicine', '-', 1, 0, 10),
(139, 'Lagundi Syrup 300mg 120ml', '114', 'Medicine', '-', 1, 0, 10),
(140, 'Levofloxacin Tab 500mg 50s', '115', 'Medicine', '-', 1, 0, 10),
(141, 'Loperamide HCL Cap 2mg 100s Motirex', '116', 'Medicine', '-', 1, 0, 10),
(142, 'Loratadine 10mg Tab 10s', '117', 'Medicine', '-', 1, 0, 10),
(143, 'Losartan Potasium 50mg Tab 100s Cotenace', '118', 'Medicine', '-', 1, 0, 10),
(144, 'Losartan Potasium 100mg Tab 100s Natrazol', '119', 'Medicine', '-', 1, 0, 10),
(145, 'Mefenamic Susp 50mg 60ml Myrefen', '120', 'Medicine', '-', 1, 0, 10),
(146, 'Mefenamic Cap 500mg 100s Analmin', '121', 'Medicine', '-', 1, 0, 10),
(147, 'Mefenamic Cap 250 Mg 100s Mefenamark', '122', 'Medicine', '-', 1, 0, 10),
(148, 'Metformin Tab 500mg 100s', '123', 'Medicine', '-', 1, 0, 10),
(149, 'Metoclopramide Syr 5mg 60ml Emijen', '124', 'Medicine', '-', 1, 0, 10),
(150, 'Metoclopramide Tab 10mg 100s', '125', 'Medicine', '-', 1, 0, 10),
(151, 'Metoprolol Tab 50mg 100s Metocard', '126', 'Medicine', '-', 1, 0, 10),
(152, 'Metoprolol Tab 100mg 100s Metocard', '127', 'Medicine', '-', 1, 0, 10),
(153, 'Metoprolol Tab 50mg 100s Prolol', '128', 'Medicine', '-', 1, 0, 10),
(154, 'Metronidazole Susp 125mg 60ml', '129', 'Medicine', '-', 1, 0, 10),
(155, 'Metronidazole Tab 500mg 100s', '130', 'Medicine', '-', 1, 0, 10),
(156, 'Multivitamins Drops 15ml Diavit', '131', 'Medicine', '-', 1, 0, 10),
(157, 'Multivitamins Syr 60ml Diavit', '132', 'Medicine', '-', 1, 0, 10),
(158, 'Multivitamins Cap 100s Diavit', '133', 'Medicine', '-', 1, 0, 10),
(159, 'Multivitamins + Iron Cap 100s Hanizyn', '134', 'Medicine', '-', 1, 0, 10),
(160, 'Multivitamins Syr 120ml Eurivil', '135', 'Medicine', '-', 1, 0, 10),
(161, 'Multivitamins + Minerals Tab 100s Eurivit', '136', 'Medicine', '-', 1, 0, 10),
(162, 'Naproxen Tab 500mg', '137', 'Medicine', '-', 1, 0, 10),
(163, 'Nifedipine Cap 5mg Calcigard', '138', 'Medicine', '-', 1, 0, 10),
(164, 'Nifedipine Cap 10mg Calcigard', '139', 'Medicine', '-', 1, 0, 10),
(165, 'Nifedipine Cap 5mg Heplobin', '140', 'Medicine', '-', 1, 0, 10),
(166, 'Nifedipine Cap 10mg Heplobin', '141', 'Medicine', '-', 1, 0, 10),
(167, 'Norfloxacin Tab 400mg 100s', '142', 'Medicine', '-', 1, 0, 10),
(168, 'Ofloxacin Tab 400mg 100s', '143', 'Medicine', '-', 1, 0, 10),
(169, 'Omeprazole Cap 20mg 100s Flazomel', '144', 'Medicine', '-', 1, 0, 10),
(170, 'Omeprazole Cap 40mg 30s', '145', 'Medicine', '-', 1, 0, 10),
(171, 'Oral Rehydration Salt Sachet 5.575g 25s', '146', 'Medicine', '-', 1, 0, 10),
(172, 'Paracetamol Drops 100mg 15ml Myremol', '147', 'Medicine', '-', 1, 0, 10),
(173, 'Paracetamol Syr 125ml 60ml Myremol', '148', 'Medicine', '-', 1, 0, 10),
(174, 'Paracetamol Syr 250mg 60ml Myremol', '149', 'Medicine', '-', 1, 0, 10),
(175, 'Paracetamol Tab 500mg 100s Kylemed', '150', 'Medicine', '-', 1, 0, 10),
(176, 'Para + Phenyl HCL+Chlorphe 325mg/25mg/2mg Tab', '151', 'Medicine', '-', 1, 0, 10),
(177, 'Phenyl Drops 6.25mg 15ml', '152', 'Medicine', '-', 1, 0, 10),
(178, 'Phenyl Syr 12.5mg 60ml', '153', 'Medicine', '-', 1, 0, 10),
(179, 'Piroxicam 20mg Cap 100s', '154', 'Medicine', '-', 1, 0, 10),
(180, 'Prednisone Tab 5 Mg 100s Dm', '155', 'Medicine', '-', 1, 0, 10),
(181, 'Propranolol Tab 10mg 100s', '156', 'Medicine', '-', 1, 0, 10),
(182, 'Propranolol Tab 40mg 100s', '157', 'Medicine', '-', 1, 0, 10),
(183, 'Ranitidine Tab 150mg 100s', '158', 'Medicine', '-', 1, 0, 10),
(184, 'Ranitidine Tab 300mg 100s', '159', 'Medicine', '-', 1, 0, 10),
(185, 'Rifampicin Cap 450mg 100s', '160', 'Medicine', '-', 1, 0, 10),
(186, 'Roxithromycin Tab 150mg 50s', '161', 'Medicine', '-', 1, 0, 10),
(187, 'Salbutamol + Guaifenesin 150mg Syr 60ml', '162', 'Medicine', '-', 1, 0, 10),
(188, 'Salbutamol + Guaifenesin Cap 2mg/100mg', '163', 'Medicine', '-', 1, 0, 10),
(189, 'Salbutamol Tab 2mg 100s Beisol', '164', 'Medicine', '-', 1, 0, 10),
(190, 'Salbutamol Tab 2mg 100s Albulem', '165', 'Medicine', '-', 1, 0, 10),
(191, 'Salbutamol Syr 2mg 60ml Albulem', '166', 'Medicine', '-', 1, 0, 10),
(192, 'Salbutamol Nebule 1mg 2.5ml 1s Hivent', '167', 'Medicine', '-', 1, 0, 10),
(193, 'Salbutamol Nebule Mg 2.5ml 1s Aerovent', '168', 'Medicine', '-', 1, 0, 10),
(194, 'Sambong Forte Leaf 250mg Tab 100s', '169', 'Medicine', '-', 1, 0, 10),
(195, 'Simvastatin 10mg Tab 100s', '170', 'Medicine', '-', 1, 0, 10),
(196, 'Simvatatin 20mg Tab 100s', '171', 'Medicine', '-', 1, 0, 10),
(197, 'Simvastatin 40mg Tab 100s', '172', 'Medicine', '-', 1, 0, 10),
(198, 'Sodium Ascorbate 528mg Tab 100s Hexcee', '173', 'Medicine', '-', 1, 0, 10),
(199, 'Telmesartan 40mg Tab 100s Cardisar', '174', 'Medicine', '-', 1, 0, 10),
(200, 'Tetracycline Cap 500mg 100s', '175', 'Medicine', '-', 1, 0, 10),
(201, 'Tramadol Cap 50mg 100s', '176', 'Medicine', '-', 1, 0, 10),
(202, 'Tranexamic Acid Cap 500mg 100s', '178', 'Medicine', '-', 1, 0, 10),
(203, 'Vitamin B1 B6 B12 Tab 100s Revitaplex', '179', 'Medicine', '-', 1, 0, 10),
(204, 'Vitamin B1 B6 B12 Tab 100s Neuronerve', '180', 'Medicine', '-', 1, 0, 10),
(205, 'Vitamin B1 B6 B12 Cap 100s Tyricid', '181', 'Medicine', '-', 1, 0, 10),
(206, 'Vitamin B1 B6 B12 Tab 100s Myrevit-B', '182', 'Medicine', '-', 1, 0, 10),
(207, 'Vitamin K Tab 10mg 100s Menadione', '183', 'Medicine', '-', 1, 0, 10),
(208, 'Zinc Sulfate Drop Ammuno-Z', '184', 'Medicine', '-', 1, 0, 10),
(209, 'Zinc Sulfate Syr 60ml Ammuno-Z', '185', 'Medicine', '-', 1, 0, 10),
(210, 'Zinc Sulfate Syr 120ml Ammuno-Z', '186', 'Medicine', '-', 1, 0, 10),
(211, 'Amikacin 50mg/2ml Vial(100mg) Cinmik', '187', 'Injectables', '-', 1, 0, 10),
(212, 'Amikacin 125mg/2ml Vial(250mg) Cinmik', '188', 'Injectables', '-', 1, 0, 10),
(213, 'Amikacin 250mg/2ml Vial(500mg) Cinmik', '189', 'Injectables', '-', 1, 0, 10),
(214, 'Aminophyline 25mg/10ml Amp', '190', 'Injectables', '-', 1, 0, 10),
(215, 'Ampicillin 250mg Vial Kylemed', '193', 'Injectables', '-', 1, 0, 10),
(216, 'Ampicillin 500mg Vial Kylemed', '194', 'Injectables', '-', 1, 0, 10),
(217, 'Ampicillin 1g Vial Kylemed', '195', 'Injectables', '-', 1, 0, 10),
(218, 'Ampicillin + Sulbactam 500mg/250mg Miasyn', '196', 'Injectables', '-', 1, 0, 10),
(219, 'Ampicillin + Sulbactam 1.5g Miasyn', '197', 'Injectables', '-', 1, 0, 10),
(220, 'Ascorbic Acid Amp Activarol', '198', 'Injectables', '-', 1, 0, 10),
(221, 'A. T. S 1,500 I.U. Amp Sharjvax', '201', 'Injectables', '-', 1, 0, 10),
(222, 'A. T. S 3,000 I.U. Amp Sharjvax', '202', 'Injectables', '-', 1, 0, 10),
(223, 'A. T. S 5,000 I.U. Amp Sharjvax', '204', 'Injectables', '-', 1, 0, 10),
(224, 'Bupivacaine Heavy 4ml', '205', 'Injectables', '-', 1, 0, 10),
(225, 'Cefazolin 1g Vial Fazolin', '206', 'Injectables', '-', 1, 0, 10),
(226, 'Ceftazidime 1g Vial Flazidim', '207', 'Injectables', '-', 1, 0, 10),
(227, 'Cefuroxime 750mg Vial Ceforin', '208', 'Injectables', '-', 1, 0, 10),
(228, 'Cefuroxime 750 Vial Zerunix', '210', 'Injectables', '-', 1, 0, 10),
(229, 'Ceftriazone 1g Vial Fazactin', '211', 'Injectables', '-', 1, 0, 10),
(230, 'Ciprlfloxacin 200mg/100ml Vial Cipulox', '212', 'Injectables', '-', 1, 0, 10),
(231, 'Chloramphenicol 1g Vial Medichlor', '213', 'Injectables', '-', 1, 0, 10),
(232, 'Citicholine 500mg Amp Cikolin', '214', 'Injectables', '-', 1, 0, 10),
(233, 'Citicholine 1g Amp Cikolin', '215', 'Injectables', '-', 1, 0, 10),
(234, 'Clindamycin 150mg/4ml Amp(600mg)', '216', 'Injectables', '-', 1, 0, 10),
(235, 'Cloxacillin 500mg Vial Cloxal', '217', 'Injectables', '-', 1, 0, 10),
(236, 'Diphenhydramine 50mg/1ml Amp Dypen', '218', 'Injectables', '-', 1, 0, 10),
(237, 'Dexamenthasone 4mg/1ml Amp Dexticort', '219', 'Injectables', '-', 1, 0, 10),
(238, 'Dexamenthasone 4mg/2ml Vial Dabrin', '220', 'Injectables', '-', 1, 0, 10),
(239, 'Dexamenthasone 5mg/1ml Amp Dexticort', '221', 'Injectables', '-', 1, 0, 10),
(240, 'Dopamine HDL 40mg/5ml Amp Dopnax', '222', 'Injectables', '-', 1, 0, 10),
(241, 'Epineprine 1ml Amp', '223', 'Injectables', '-', 1, 0, 10),
(242, 'Enoxarine .4ml Syr. Inj. Hepaclex', '224', 'Injectables', '-', 1, 0, 10),
(243, 'Enoxarine .6ml Syr. Inj. Hepaclex', '225', 'Injectables', '-', 1, 0, 10),
(244, 'Erythropoietiene 4000 I.U Epogen', '226', 'Injectables', '-', 1, 0, 10),
(245, 'Famotidine 20mg Vial W/ Diluent Peptodin', '227', 'Injectables', '-', 1, 0, 10),
(246, 'Fleet Enema', '228', 'Injectables', '-', 1, 0, 10),
(247, 'Furosemide 20mg Amp Fusem', '229', 'Injectables', '-', 1, 0, 10),
(248, 'Gentamycin 40mg/2ml(80mg) Amp Agentam', '231', 'Injectables', '-', 1, 0, 10),
(249, 'Hydrocortisone 100mg Vial Flamisone', '232', 'Injectables', '-', 1, 0, 10),
(250, 'Hydrocortisone 250mg Vial Stericort', '233', 'Injectables', '-', 1, 0, 10),
(251, 'Hydrocortisone 500mg Vial Stericort', '234', 'Injectables', '-', 1, 0, 10),
(252, 'Hydralazine 20mg/100ml Amp Aprezin', '235', 'Injectables', '-', 1, 0, 10),
(253, 'Hyoscine 20mg Amp Spasbascine', '236', 'Injectables', '-', 1, 0, 10),
(254, 'Iron Sucrose 5ml/2ml Amp', '237', 'Injectables', '-', 1, 0, 10),
(255, 'Lidocaine HCI + Epine 2% 1.8 Carp(50s) Hizon', '238', 'Injectables', '-', 1, 0, 10),
(256, 'Ketorolac Tromethamine Amp 30mg/1ml Oradol', '239', 'Injectables', '-', 1, 0, 10),
(257, 'Magnesium Sulfate 250mg/10ml Amp', '240', 'Injectables', '-', 1, 0, 10),
(258, 'Methylergometrine 200mg Amp/1ml Myometril', '241', 'Injectables', '-', 1, 0, 10),
(259, 'Meropenem 500mg Vial Meropen', '242', 'Injectables', '-', 1, 0, 10),
(260, 'Meropenem 1g Vial Meropen', '243', 'Injectables', '-', 1, 0, 10),
(261, 'Metoclopramide 10mg/1ml Amp Meto', '244', 'Injectables', '-', 1, 0, 10),
(262, 'Metronidazole IV 5mg/100ml Kylemed', '245', 'Injectables', '-', 1, 0, 10),
(263, 'Oeprazole 40mg/10ml Vial Zolphen', '246', 'Injectables', '-', 1, 0, 10),
(264, 'Oxacillin 500mg Vial Oxapen', '247', 'Injectables', '-', 1, 0, 10),
(265, 'Oxytocin 10 I.U/1ml Amp Gynetocin', '248', 'Injectables', '-', 1, 0, 10),
(266, 'Paracetamol 150mg/ml Amp Flugard', '249', 'Injectables', '-', 1, 0, 10),
(267, 'Pen G 5 M I.U Vial Zyplicin', '250', 'Injectables', '-', 1, 0, 10),
(268, 'Phytomenadione 10mg/ml Hemogen', '251', 'Injectables', '-', 1, 0, 10),
(269, 'Piperacillin + Tazobactam Na 2.25g Vial', '252', 'Injectables', '-', 1, 0, 10),
(270, 'Piperacillin + Tazobactam Na 4.5g Vial', '253', 'Injectables', '-', 1, 0, 10),
(271, 'Promethazine 25mg/2ml Amp Zinmet', '254', 'Injectables', '-', 1, 0, 10),
(272, 'Ranitidine HCL 25mg/2ml Amp Raxidine', '255', 'Injectables', '-', 1, 0, 10),
(273, 'Sodium Bicabonate 8.4% 50ml Amp', '256', 'Injectables', '-', 1, 0, 10),
(274, 'Streptomycin 1g Vial Septin', '257', 'Injectables', '-', 1, 0, 10),
(275, 'Tetanus Toxoid Vaccine 40 I.U/0.5ml Amp Tetvac', '258', 'Injectables', '-', 1, 0, 10),
(276, 'Tramadol HCI 50mg/1ml Amp Plazadol', '259', 'Injectables', '-', 1, 0, 10),
(277, 'Tramadol HCI 50mg/2ml Amp(100mg)', '260', 'Injectables', '-', 1, 0, 10),
(278, 'Tranexamic Acid 100mg/5ml Amp Xanfib', '261', 'Injectables', '-', 1, 0, 10),
(279, 'Vitamin B1+B6+B12 3ml Amp Tribimin', '262', 'Injectables', '-', 1, 0, 10),
(280, 'Abdominal Binder (1s)', '263', 'Medical Supply', '-', 1, 0, 10),
(281, 'Arm Sling S/M/L (1s)', '264', 'Medical Supply', '-', 1, 0, 10),
(282, 'Asepto Syringe (1s)', '265', 'Medical Supply', '-', 1, 0, 10),
(283, 'Blood Set (1s) Terumo 50s', '266', 'Medical Supply', '-', 1, 0, 10),
(284, 'Bonnet - OR Cap(1s)', '267', 'Medical Supply', '-', 1, 0, 10),
(285, 'Cord Clamp (1s) Indoplas', '268', 'Medical Supply', '-', 1, 0, 10),
(286, 'Chromic 2/0 Cutting Double Arm(12s) Bbraun', '269', 'Medical Supply', '-', 1, 0, 10),
(287, 'Chromic 0 Round (12s) Ethicon', '270', 'Medical Supply', '-', 1, 0, 10),
(288, 'Chromic 1 Round (12s) Ethicon', '271', 'Medical Supply', '-', 1, 0, 10),
(289, 'Chromic 2/0 Round (12s) Ethicon', '273', 'Medical Supply', '-', 1, 0, 10),
(290, 'Chromic 3/0 Round (12s) Ethicon', '274', 'Medical Supply', '-', 1, 0, 10),
(291, 'Chromic 4/0 Round (12s) Ethicon', '275', 'Medical Supply', '-', 1, 0, 10),
(292, 'Clean Gloves/Latex Examination Gloves M/L (100s)', '276', 'Medical Supply', '-', 1, 0, 10),
(293, 'Colostomy Bag(1s)', '277', 'Medical Supply', '-', 1, 0, 10),
(294, 'Condom Catheter - Freesize (1s)', '278', 'Medical Supply', '-', 1, 0, 10),
(295, 'Dropper(1s)', '279', 'Medical Supply', '-', 1, 0, 10),
(296, 'Elastic Bandage 2x5(1s)', '280', 'Medical Supply', '-', 1, 0, 10),
(297, 'Elastic Bandage 3x5(1s)', '281', 'Medical Supply', '-', 1, 0, 10),
(298, 'Elastic Bandage 4x5(1s)', '282', 'Medical Supply', '-', 1, 0, 10),
(299, 'Elastic Bandage 5x5(1s)', '283', 'Medical Supply', '-', 1, 0, 10),
(300, 'Epidural Kit - Portex G18 (1s)/Perifix', '284', 'Medical Supply', '-', 1, 0, 10),
(301, 'Foley Ballon Catheter - F 5/8/10 (1s)', '285', 'Medical Supply', '-', 1, 0, 10),
(302, 'Foley Ballon Catheter - F 12/14/16/18 (1s)', '287', 'Medical Supply', '-', 1, 0, 10),
(303, 'Foley Ballon Catheter - F 24/26 (1s)', '288', 'Medical Supply', '-', 1, 0, 10),
(304, 'Foley Ballon Catheter - 3 Way - F 20/22/24 (1s)', '289', 'Medical Supply', '-', 1, 0, 10),
(305, 'Face Mask (box Of 50s)', '290', 'Medical Supply', '-', 1, 0, 10),
(306, 'Gigly Saw Wire (1s)', '291', 'Medical Supply', '-', 1, 0, 10),
(307, 'Gauze Bandage 2x10yards(12s)', '292', 'Medical Supply', '-', 1, 0, 10),
(308, 'Gauze Bandage 3x10yards(12s)', '293', 'Medical Supply', '-', 1, 0, 10),
(309, 'Gauze Bandage 4x10yards(12s)', '294', 'Medical Supply', '-', 1, 0, 10),
(310, 'Gauze Pads/Sponges 3x3(100s)', '295', 'Medical Supply', '-', 1, 0, 10),
(311, 'Gauze Pads/Sponges 4x4(100s)', '299', 'Medical Supply', '-', 1, 0, 10),
(312, 'Gauze Pads/Sponges 4x4(100s) Non Sterile', '300', 'Medical Supply', '-', 1, 0, 10),
(313, 'Gelfoam (1s)', '301', 'Medical Supply', '-', 1, 0, 10),
(314, 'Heplock IN-Stopper 1s', '302', 'Medical Supply', '-', 1, 0, 10),
(315, 'Insulin Syringe 1/2 CC G29(100s) Terumo', '303', 'Medical Supply', '-', 1, 0, 10),
(316, 'Insulin Syringe 1 Cc G29(100s) Terumo', '304', 'Medical Supply', '-', 1, 0, 10),
(317, 'IV Catheter W/ Wings G26', '305', 'Medical Supply', '-', 1, 0, 10),
(318, 'Insyte G 18/20/22 1s', '306', 'Medical Supply', '-', 1, 0, 10),
(319, 'Insyte G 24(1s)', '307', 'Medical Supply', '-', 1, 0, 10),
(320, 'IV Cannula G 18/20 (1s)', '308', 'Medical Supply', '-', 1, 0, 10),
(321, 'IV Canula G 18/20/22 (1s)', '309', 'Medical Supply', '-', 1, 0, 10),
(322, 'IV Cannula G 24 (1s)', '310', 'Medical Supply', '-', 1, 0, 10),
(323, 'IV Canula G 26 (1s)', '311', 'Medical Supply', '-', 1, 0, 10),
(324, 'Jackson Pratt(JP Drain)', '312', 'Medical Supply', '-', 1, 0, 10),
(325, 'JP Drain/Suction Reservoir Kit(panavac)', '313', 'Medical Supply', '-', 1, 0, 10),
(326, 'Lubricating Jelly 1s', '314', 'Medical Supply', '-', 1, 0, 10),
(327, 'Macro Set - Inset Adult (1s)', '315', 'Medical Supply', '-', 1, 0, 10),
(328, 'Macro Set - Inset Pedia (1s)', '316', 'Medical Supply', '-', 1, 0, 10),
(329, 'Nebulizer Kit (1s)', '318', 'Medical Supply', '-', 1, 0, 10),
(330, 'Nebulizer Kit With Mask Adult (1s)', '319', 'Medical Supply', '-', 1, 0, 10),
(331, 'Nebulizer Kit With Mask Pedia (1s)', '320', 'Medical Supply', '-', 1, 0, 10),
(332, 'Nebulizer Set - Machine (Endure)', '321', 'Medical Supply', '-', 1, 0, 10),
(333, 'Needle G18/19/20/21/22/23/24/25/26/27 (100s)', '323', 'Medical Supply', '-', 1, 0, 10),
(334, 'NGT - F5/8/10/12/14/16/18 (1s)', '324', 'Medical Supply', '-', 1, 0, 10),
(335, 'Oxygen Cannula Adult (1s)', '325', 'Medical Supply', '-', 1, 0, 10),
(336, 'Oxygen Cannula Pedia (1s)', '326', 'Medical Supply', '-', 1, 0, 10),
(337, 'Oxygen Mask Adult (1s)', '327', 'Medical Supply', '-', 1, 0, 10),
(338, 'Oxygen Mask Pedia (1s)', '328', 'Medical Supply', '-', 1, 0, 10),
(339, 'Plaster Of Paris 6x5 (1s)', '329', 'Medical Supply', '-', 1, 0, 10),
(340, 'Plain 0 (12s)', '331', 'Medical Supply', '-', 1, 0, 10),
(341, 'Plain 1 (12s)', '332', 'Medical Supply', '-', 1, 0, 10),
(342, 'Plain 2/0 (12s)', '333', 'Medical Supply', '-', 1, 0, 10),
(343, 'Poole Abdominal Drain FG-24(Suction Set)', '334', 'Medical Supply', '-', 1, 0, 10),
(344, 'Pregnacy Test 1s', '335', 'Medical Supply', '-', 1, 0, 10),
(345, 'Prolene 2/0 Ethicon', '336', 'Medical Supply', '-', 1, 0, 10),
(346, 'Prolene 3/0 Ethicon', '337', 'Medical Supply', '-', 1, 0, 10),
(347, 'Prolene 4/0 Ethicon', '338', 'Medical Supply', '-', 1, 0, 10),
(348, 'Prolene 5/0 Ethicon', '339', 'Medical Supply', '-', 1, 0, 10),
(349, 'Silk 0 Cutting/Round/Strand (12s) Mersilk', '340', 'Medical Supply', '-', 1, 0, 10),
(350, 'Silk 1 Cutting/Round/Strand (12s) Mersilk', '341', 'Medical Supply', '-', 1, 0, 10),
(351, 'Silk 2/0 Cutting/Round/Strand (12s) Mersilk', '342', 'Medical Supply', '-', 1, 0, 10),
(352, 'Silk 3/0 Cutting/Round/Strand (12s) Mersilk', '343', 'Medical Supply', '-', 1, 0, 10),
(353, 'Silk 4/0 Cutting/Round/Strand (12s) Mersilk', '344', 'Medical Supply', '-', 1, 0, 10),
(354, 'Skin Stapler (1s)', '345', 'Medical Supply', '-', 1, 0, 10),
(355, 'Solu Set Infusion Set (1s)', '346', 'Medical Supply', '-', 1, 0, 10),
(356, 'Spinal Needle SPINOCAN G25 (25s)', '347', 'Medical Supply', '-', 1, 0, 10),
(357, 'Stop Cock 3 Way', '348', 'Medical Supply', '-', 1, 0, 10),
(358, 'Suction Bulb ( Orange Small )', '349', 'Medical Supply', '-', 1, 0, 10),
(359, 'Suction Catheter F 6/8/10/12/14/16/18 (1s)', '350', 'Medical Supply', '-', 1, 0, 10),
(360, 'Surgical Gloves #6/6.5/7/7.5/8 (50s)', '352', 'Medical Supply', '-', 1, 0, 10),
(361, 'Surgical Tape 1 Inch (12s) HP', '353', 'Medical Supply', '-', 1, 0, 10),
(362, 'Surgical Tape 1/2inch (24s) HP', '354', 'Medical Supply', '-', 1, 0, 10),
(363, 'Surgical Tape 1inch (12s) Micropore', '355', 'Medical Supply', '-', 1, 0, 10),
(364, 'Surgical Tape 1/2inch (24s) Micropore', '356', 'Medical Supply', '-', 1, 0, 10),
(365, 'Syringe 1ml(100s)', '357', 'Medical Supply', '-', 1, 0, 10),
(366, 'Syringe 3ml (100s)', '358', 'Medical Supply', '-', 1, 0, 10),
(367, 'Syringe 5ml(100s)', '359', 'Medical Supply', '-', 1, 0, 10),
(368, 'Syringe 10ml(100s)', '360', 'Medical Supply', '-', 1, 0, 10),
(369, 'Syringe 20ml(50s)', '361', 'Medical Supply', '-', 1, 0, 10),
(370, 'Syringe 50ml(20s)', '362', 'Medical Supply', '-', 1, 0, 10),
(371, 'Syringe 20ml(box Of 50s)', '363', 'Medical Supply', '-', 1, 0, 10),
(372, 'Thermometer Digital (1s)', '364', 'Medical Supply', '-', 1, 0, 10),
(373, 'Uring Bag Adult (1s)', '365', 'Medical Supply', '-', 1, 0, 10),
(374, 'Uring Collector Pedia (1s)', '366', 'Medical Supply', '-', 1, 0, 10),
(375, 'Vicryl 0 Cutting/Round 90cm (12s)', '367', 'Medical Supply', '-', 1, 0, 10),
(376, 'Vicryl 1 CT Cutting/Round 90cm (12s)', '368', 'Medical Supply', '-', 1, 0, 10),
(377, 'Vicryl 2/0 Cutting/Round 90cm (12s)', '369', 'Medical Supply', '-', 1, 0, 10),
(378, 'Vicryl 3/0 Cutting/Round 90cm (12s)', '370', 'Medical Supply', '-', 1, 0, 10),
(379, 'Vicryl 4/0 Cutting/Round 90cm (12s)', '371', 'Medical Supply', '-', 1, 0, 10),
(380, 'Vicryl 2/0 Round MH 90cm (12s)', '372', 'Medical Supply', '-', 1, 0, 10),
(381, 'Vicryl 2/0 Rapide 120cm (12s)', '373', 'Medical Supply', '-', 1, 0, 10),
(382, 'Wadding Sheet(1s)', '374', 'Medical Supply', '-', 1, 0, 10),
(383, 'Bedesunide 250mg/ml Nebule 2ml', '375', 'IV Fluids', '-', 1, 0, 10),
(384, 'Calcium Gluconate 10% Solution For Inj. 20ml', '376', 'IV Fluids', '-', 1, 0, 10),
(385, 'D50% Solution For Injection 50ml', '377', 'IV Fluids', '-', 1, 0, 10),
(386, 'Lidocaine HCL 2% Solution For Inj. 50ml', '378', 'IV Fluids', '-', 1, 0, 10),
(387, 'Lidocaine HCL 2% Solution For Inj. 20ml', '379', 'IV Fluids', '-', 1, 0, 10),
(388, 'Magnesium Sulfate 25% (250mg/m) 20ml', '380', 'IV Fluids', '-', 1, 0, 10),
(389, 'Potassium Chloride 0.9% Solution For Inj. 20ml', '381', 'IV Fluids', '-', 1, 0, 10),
(390, 'Sodium Chloride 0.9% Solution For Inj. 50ml', '382', 'IV Fluids', '-', 1, 0, 10),
(391, 'Sodium Chloride 0.9% Solution For Inj. 20ml', '383', 'IV Fluids', '-', 1, 0, 10),
(392, 'Sterile Water For Injection 50ml', '384', 'IV Fluids', '-', 1, 0, 10),
(393, 'Plain LR 500ml', '385', 'Dextrose', '-', 1, 0, 10),
(394, 'Plain LR 1000ml', '386', 'Dextrose', '-', 1, 0, 10),
(395, 'Plain NSS 500ml', '387', 'Dextrose', '-', 1, 0, 10),
(396, 'Plain NSS 1000ml', '388', 'Dextrose', '-', 1, 0, 10),
(397, 'D5 LR 500ml', '389', 'Dextrose', '-', 1, 0, 10),
(398, 'D5 LR 1000ml', '390', 'Dextrose', '-', 1, 0, 10),
(399, 'D5 NR 1000ml', '391', 'Dextrose', '-', 1, 0, 10),
(400, 'D5 0.3 500ml', '392', 'Dextrose', '-', 1, 0, 10),
(401, 'D5 0.3 1000ml', '393', 'Dextrose', '-', 1, 0, 10),
(402, 'D5 IMB 500ml', '394', 'Dextrose', '-', 1, 0, 10),
(403, 'D5 Water 250ml (Glass)', '395', 'Dextrose', '-', 1, 0, 10),
(404, 'D5 Water 500ml', '396', 'Dextrose', '-', 1, 0, 10),
(405, 'D5 Water 1000ml', '397', 'Dextrose', '-', 1, 0, 10),
(406, 'D10 Water 500ml', '398', 'Dextrose', '-', 1, 0, 10),
(407, 'D5 NM 1000ml', '399', 'Dextrose', '-', 1, 0, 10),
(408, 'Irrigation 1000ml', '400', 'Dextrose', '-', 1, 0, 10),
(409, 'Mannitol 500ml', '401', 'Dextrose', '-', 1, 0, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tblmanufacturer`
--

CREATE TABLE IF NOT EXISTS `tblmanufacturer` (
  `fld_manu_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_manu_code` varchar(25) NOT NULL,
  `fld_manu_name` varchar(50) NOT NULL,
  PRIMARY KEY (`fld_manu_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tblmanufacturer`
--

INSERT INTO `tblmanufacturer` (`fld_manu_id`, `fld_manu_code`, `fld_manu_name`) VALUES
(4, 'Fa', '-');

-- --------------------------------------------------------

--
-- Table structure for table `tblpo`
--

CREATE TABLE IF NOT EXISTS `tblpo` (
  `fld_po_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_po_no` int(11) NOT NULL,
  `fld_lot_no` text NOT NULL,
  `fld_po_price` decimal(10,0) DEFAULT NULL,
  `fld_po_qty` int(11) NOT NULL,
  `fld_discount` double DEFAULT NULL,
  `fld_amount` double DEFAULT NULL,
  `fld_item_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`fld_po_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=93 ;

--
-- Dumping data for table `tblpo`
--

INSERT INTO `tblpo` (`fld_po_id`, `fld_po_no`, `fld_lot_no`, `fld_po_price`, `fld_po_qty`, `fld_discount`, `fld_amount`, `fld_item_status`) VALUES
(79, 10121, '100120', NULL, 20, NULL, NULL, 1),
(80, 10101, '202', NULL, 10, NULL, NULL, 1),
(81, 101010, '98', NULL, 12, NULL, NULL, 0),
(83, 110001, '204', '2', 2, NULL, 4, 1),
(84, 110001, '2', '101', 12, NULL, 1212, 1),
(85, 110001, '3', '1', 1, NULL, 1, 1),
(86, 110001, '1', '0', 0, NULL, 0, 1),
(87, 110002, '201', '0', 12, NULL, 0, 1),
(88, 110002, '263', '120', 12, NULL, 1440, 1),
(89, 110002, '2', '15', 12, NULL, 180, 1),
(90, 110003, '2', '12', 5, NULL, 60, 0),
(91, 110004, '98', '12', 2, NULL, 24, 0),
(92, 110004, '97', '1', 21, NULL, 21, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblpono`
--

CREATE TABLE IF NOT EXISTS `tblpono` (
  `fld_po_no` int(11) NOT NULL AUTO_INCREMENT,
  `fld_po_date` date NOT NULL,
  `fld_expected_date` date NOT NULL,
  `fld_sup_code` varchar(100) NOT NULL,
  PRIMARY KEY (`fld_po_no`),
  UNIQUE KEY `fld_po_no` (`fld_po_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=110005 ;

--
-- Dumping data for table `tblpono`
--

INSERT INTO `tblpono` (`fld_po_no`, `fld_po_date`, `fld_expected_date`, `fld_sup_code`) VALUES
(110001, '2016-12-01', '2016-12-01', 'SUP103'),
(110002, '2016-12-02', '2016-12-02', 'Name'),
(110003, '2016-12-02', '2016-12-02', 'Name'),
(110004, '2016-12-05', '2016-12-05', 'Name');

-- --------------------------------------------------------

--
-- Table structure for table `tblstocks`
--

CREATE TABLE IF NOT EXISTS `tblstocks` (
  `fld_stocks_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_lot_no` text NOT NULL,
  `fld_stocks_qty` int(11) NOT NULL,
  `fld_lacking` int(11) NOT NULL DEFAULT '0',
  `fld_batch1` date NOT NULL DEFAULT '2018-05-05',
  `fld_old_stocks` int(11) NOT NULL DEFAULT '0',
  `fld_batch2` date NOT NULL DEFAULT '2018-05-05',
  `fld_new_stocks` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`fld_stocks_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=406 ;

--
-- Dumping data for table `tblstocks`
--

INSERT INTO `tblstocks` (`fld_stocks_id`, `fld_lot_no`, `fld_stocks_qty`, `fld_lacking`, `fld_batch1`, `fld_old_stocks`, `fld_batch2`, `fld_new_stocks`) VALUES
(22, '1', 0, 0, '2018-05-05', 0, '2017-12-02', 0),
(23, '2', 109, 0, '2017-12-02', 104, '2017-12-02', 5),
(24, '3', 1, 0, '2018-05-05', 0, '2017-12-02', 1),
(25, '4', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(26, '5', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(27, '6', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(28, '7', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(29, '8', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(30, '9', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(31, '10', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(32, '11', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(33, '12', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(34, '13', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(35, '14', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(36, '15', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(37, '16', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(38, '17', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(39, '18', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(40, '19', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(41, '20', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(42, '21', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(43, '22', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(44, '23', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(45, '24', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(46, '25', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(47, '26', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(48, '27', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(49, '28', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(50, '29', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(51, '30', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(52, '31', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(53, '32', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(54, '33', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(55, '34', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(56, '35', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(57, '36', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(58, '37', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(59, '38', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(60, '39', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(61, '40', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(62, '41', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(63, '42', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(64, '43', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(65, '44', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(66, '45', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(67, '46', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(68, '47', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(69, '48', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(70, '49', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(71, '50', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(72, '51', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(73, '52', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(75, '54', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(76, '55', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(77, '56', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(78, '57', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(79, '58', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(80, '59', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(81, '60', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(82, '61', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(83, '62', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(84, '63', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(85, '64', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(86, '65', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(87, '66', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(88, '67', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(89, '68', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(90, '69', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(91, '70', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(92, '71', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(93, '72', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(94, '73', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(95, '74', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(96, '75', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(97, '76', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(98, '77', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(99, '78', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(100, '79', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(101, '80', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(102, '81', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(103, '82', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(104, '83', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(105, '84', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(106, '85', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(107, '86', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(108, '87', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(109, '88', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(110, '89', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(111, '90', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(112, '91', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(113, '92', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(114, '93', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(115, '94', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(116, '95', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(117, '96', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(118, '97', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(119, '98', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(121, '100', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(122, '101', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(123, '102', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(124, '103', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(125, '104', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(126, '105', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(127, '106', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(128, '107', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(129, '108', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(130, '109', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(131, '110', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(132, '111', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(133, '112', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(134, '113', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(135, '114', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(136, '115', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(137, '116', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(138, '117', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(139, '118', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(140, '119', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(141, '120', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(142, '121', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(143, '122', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(144, '123', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(145, '124', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(146, '125', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(147, '126', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(148, '127', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(149, '128', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(150, '129', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(151, '130', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(152, '131', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(153, '132', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(154, '133', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(155, '134', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(156, '135', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(157, '136', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(158, '137', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(159, '138', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(160, '139', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(161, '140', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(162, '141', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(163, '142', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(164, '143', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(165, '144', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(166, '145', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(167, '146', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(168, '147', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(169, '148', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(170, '149', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(171, '150', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(172, '151', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(173, '152', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(174, '153', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(175, '154', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(176, '155', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(177, '156', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(178, '157', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(179, '158', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(180, '159', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(181, '160', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(182, '161', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(183, '162', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(184, '163', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(185, '164', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(186, '165', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(187, '166', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(188, '167', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(189, '168', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(190, '169', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(191, '170', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(192, '171', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(193, '172', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(194, '173', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(195, '174', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(196, '175', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(197, '176', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(198, '178', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(199, '179', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(200, '180', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(201, '181', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(202, '182', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(203, '183', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(204, '184', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(205, '185', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(206, '186', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(207, '187', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(208, '188', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(209, '189', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(210, '190', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(211, '193', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(212, '194', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(213, '195', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(214, '196', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(215, '197', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(216, '198', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(217, '201', 102, 0, '2018-05-05', 114, '2017-12-02', 12),
(218, '202', 0, 0, '2018-05-05', 10, '2017-11-21', 10),
(219, '204', 0, 0, '2018-05-05', 2, '2017-12-02', 2),
(220, '205', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(221, '206', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(222, '207', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(223, '208', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(224, '210', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(225, '211', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(226, '212', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(227, '213', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(228, '214', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(229, '215', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(230, '216', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(231, '217', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(232, '218', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(233, '219', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(234, '220', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(235, '221', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(236, '222', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(237, '223', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(238, '224', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(239, '225', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(240, '226', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(241, '227', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(242, '228', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(243, '229', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(244, '231', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(245, '232', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(246, '233', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(247, '234', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(248, '235', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(249, '236', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(250, '237', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(251, '238', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(252, '239', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(253, '240', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(254, '241', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(255, '242', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(256, '243', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(257, '244', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(258, '245', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(259, '246', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(260, '247', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(261, '248', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(262, '249', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(263, '250', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(264, '251', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(265, '252', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(266, '253', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(267, '254', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(268, '255', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(269, '256', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(270, '257', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(271, '258', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(272, '259', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(273, '260', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(274, '261', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(275, '262', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(276, '263', 54, 0, '2018-05-05', 50, '2017-12-02', 4),
(277, '264', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(278, '265', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(279, '266', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(280, '267', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(281, '268', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(282, '269', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(283, '270', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(284, '271', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(285, '273', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(286, '274', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(287, '275', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(288, '276', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(289, '277', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(290, '278', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(291, '279', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(292, '280', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(293, '281', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(294, '282', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(295, '283', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(296, '284', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(297, '285', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(298, '287', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(299, '288', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(300, '289', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(301, '290', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(302, '291', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(303, '292', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(304, '293', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(305, '294', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(306, '295', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(307, '299', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(308, '300', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(309, '301', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(310, '302', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(311, '303', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(312, '304', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(313, '305', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(314, '306', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(315, '307', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(316, '308', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(317, '309', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(318, '310', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(319, '311', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(320, '312', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(321, '313', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(322, '314', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(323, '315', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(324, '316', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(325, '318', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(326, '319', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(327, '320', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(328, '321', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(329, '323', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(330, '324', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(331, '325', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(332, '326', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(333, '327', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(334, '328', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(335, '329', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(336, '331', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(337, '332', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(338, '333', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(339, '334', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(340, '335', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(341, '336', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(342, '337', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(343, '338', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(344, '339', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(345, '340', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(346, '341', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(347, '342', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(348, '343', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(349, '344', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(350, '345', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(351, '346', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(352, '347', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(353, '348', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(354, '349', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(355, '350', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(356, '352', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(357, '353', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(358, '354', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(359, '355', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(360, '356', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(361, '357', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(362, '358', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(363, '359', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(364, '360', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(365, '361', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(366, '362', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(367, '363', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(368, '364', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(369, '365', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(370, '366', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(371, '367', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(372, '368', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(373, '369', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(374, '370', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(375, '371', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(376, '372', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(377, '373', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(378, '374', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(379, '375', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(380, '376', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(381, '377', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(382, '378', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(383, '379', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(384, '380', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(385, '381', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(386, '382', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(387, '383', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(388, '384', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(389, '385', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(390, '386', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(391, '387', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(392, '388', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(393, '389', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(394, '390', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(395, '391', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(396, '392', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(397, '393', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(398, '394', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(399, '395', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(400, '396', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(401, '397', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(402, '398', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(403, '399', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(404, '400', 0, 0, '2018-05-05', 0, '2018-05-05', 0),
(405, '401', 0, 0, '2018-05-05', 0, '2018-05-05', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblstocksin`
--

CREATE TABLE IF NOT EXISTS `tblstocksin` (
  `fld_stocksin_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_po_no` int(11) NOT NULL,
  `fld_lot_no` text NOT NULL,
  `fld_date_received` date NOT NULL,
  `fld_date_expiry` date NOT NULL,
  `fld_qty_recieved` int(11) NOT NULL,
  PRIMARY KEY (`fld_stocksin_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `tblstocksin`
--

INSERT INTO `tblstocksin` (`fld_stocksin_id`, `fld_po_no`, `fld_lot_no`, `fld_date_received`, `fld_date_expiry`, `fld_qty_recieved`) VALUES
(60, 10121, '100120', '2016-11-08', '2016-11-08', 12),
(61, 10121, '100120', '2016-11-08', '2016-11-08', 20),
(62, 10121, '100120', '2016-11-08', '2016-11-08', 15),
(63, 10121, '100120', '2016-11-08', '2016-11-08', 15),
(64, 10121, '100120', '2016-11-08', '2016-11-08', 15),
(65, 10121, '100120', '2016-11-08', '2016-11-08', 15),
(66, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(67, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(68, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(69, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(70, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(71, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(72, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(73, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(74, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(75, 10121, '100120', '2016-11-16', '2017-04-16', 20),
(76, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(77, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(78, 10121, '100120', '2016-11-16', '2017-03-16', 20),
(79, 10121, '100120', '2016-11-16', '2017-11-16', 20),
(80, 10101, '202', '2016-11-21', '2017-11-21', 10),
(81, 110001, '204', '2016-12-02', '2017-12-02', 2),
(82, 110001, '3', '2016-12-02', '2017-12-02', 1),
(83, 110001, '1', '2016-12-02', '2017-12-02', 0),
(84, 110001, '2', '2016-12-02', '2017-12-02', 12),
(85, 110002, '201', '2016-12-02', '2017-12-02', 12),
(86, 110002, '263', '2016-12-02', '2017-12-02', 12),
(87, 110002, '2', '2016-12-02', '2017-12-02', 12);

-- --------------------------------------------------------

--
-- Table structure for table `tblsupplier`
--

CREATE TABLE IF NOT EXISTS `tblsupplier` (
  `fld_sup_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_sup_code` varchar(50) NOT NULL,
  `fld_sup_name` varchar(50) NOT NULL,
  `fld_sup_street` varchar(100) NOT NULL,
  `fld_sup_zone` varchar(100) NOT NULL DEFAULT '',
  `fld_sup_city` varchar(100) NOT NULL,
  `fld_sup_province` varchar(100) NOT NULL,
  `fld_sup_mobile` varchar(20) NOT NULL DEFAULT '',
  `fld_sup_telno` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`fld_sup_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=104 ;

--
-- Dumping data for table `tblsupplier`
--

INSERT INTO `tblsupplier` (`fld_sup_id`, `fld_sup_code`, `fld_sup_name`, `fld_sup_street`, `fld_sup_zone`, `fld_sup_city`, `fld_sup_province`, `fld_sup_mobile`, `fld_sup_telno`) VALUES
(102, 'Name', 'SUP102', 'Ct', '', 'Ct', 'Or', 'mo', 'tel'),
(103, 'SUP103', 'Fdgs', '', '', 'Sfgs', 'Gsdg', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbluseraccount`
--

CREATE TABLE IF NOT EXISTS `tbluseraccount` (
  `fld_username` varchar(30) NOT NULL,
  `fld_password` varchar(30) NOT NULL,
  `fld_privilege` varchar(30) NOT NULL,
  PRIMARY KEY (`fld_username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbluseraccount`
--

INSERT INTO `tbluseraccount` (`fld_username`, `fld_password`, `fld_privilege`) VALUES
('admin', 'secret', 'Admin'),
('gudang', 'ugerzon', 'User');

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE IF NOT EXISTS `tblusers` (
  `fld_username` varchar(30) NOT NULL,
  `fld_lname` varchar(30) NOT NULL,
  `fld_fname` varchar(30) NOT NULL,
  `fld_gender` varchar(10) NOT NULL,
  `fld_zone` varchar(10) NOT NULL DEFAULT '',
  `fld_lotno` varchar(10) NOT NULL DEFAULT '',
  `fld_blockno` varchar(10) NOT NULL DEFAULT '',
  `fld_street` varchar(30) NOT NULL DEFAULT '',
  `fld_city` varchar(50) NOT NULL,
  `fld_province` varchar(50) NOT NULL,
  `fld_contactno` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`fld_username`, `fld_lname`, `fld_fname`, `fld_gender`, `fld_zone`, `fld_lotno`, `fld_blockno`, `fld_street`, `fld_city`, `fld_province`, `fld_contactno`) VALUES
('gudang', 'Gerzon', 'Udang', 'Female', '0', '0', '0', '-', '-', '-', '-');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
