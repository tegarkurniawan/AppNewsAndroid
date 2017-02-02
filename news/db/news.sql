-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2015 at 03:37 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `news`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_news`
--

CREATE TABLE IF NOT EXISTS `tbl_news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `news_heading` varchar(500) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `news_status` int(11) NOT NULL DEFAULT '1',
  `news_date` varchar(255) NOT NULL,
  `news_image` text NOT NULL,
  `news_description` text NOT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `tbl_news`
--

INSERT INTO `tbl_news` (`nid`, `news_heading`, `cat_id`, `news_status`, `news_date`, `news_image`, `news_description`) VALUES
(17, 'Sample News', 6, 1, '03-21-2015', '4386-2015-03-21.jpg', '<p><span style="font-size: 10px; line-height: 17px; text-align: justify;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</span></p>'),
(18, 'Sample News', 5, 1, '03-21-2015', '6553-2015-03-21.jpg', '<p><span style="font-size: 10px; line-height: 17px; text-align: justify;">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</span></p>');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_news_category`
--

CREATE TABLE IF NOT EXISTS `tbl_news_category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL,
  `category_image` text NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `tbl_news_category`
--

INSERT INTO `tbl_news_category` (`cid`, `category_name`, `category_image`, `status`) VALUES
(5, 'Business', '0056-2015-03-21.png', 1),
(6, 'Technology', '6897-2015-03-21.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_settings`
--

CREATE TABLE IF NOT EXISTS `tbl_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) NOT NULL,
  `app_logo` varchar(255) NOT NULL,
  `app_email` varchar(255) NOT NULL,
  `app_website` varchar(255) NOT NULL,
  `app_description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(15) NOT NULL,
  `Password` text NOT NULL,
  `Email` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`ID`, `Username`, `Password`, `Email`) VALUES
(1, 'admin', 'd82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892', 'email@website.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
