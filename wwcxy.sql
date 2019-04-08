/*
Navicat MySQL Data Transfer

Source Server         : wwcxy
Source Server Version : 50723
Source Host           : jimxu.top:3306
Source Database       : wwcxy

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-08 10:16:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(50) NOT NULL,
  `tid` int(11) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `years` varchar(50) NOT NULL,
  `ibsn` varchar(50) NOT NULL,
  `ibsnimg` varchar(100) NOT NULL,
  `allnum` int(11) NOT NULL,
  `nownum` int(11) NOT NULL,
  `bimage` varchar(100) NOT NULL DEFAULT 'nopic.jpg',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `email` varchar(25) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '正常',
  `vip` varchar(10) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lendrecord
-- ----------------------------
DROP TABLE IF EXISTS `lendrecord`;
CREATE TABLE `lendrecord` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `borrowtime` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `returntime` date DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tcode` int(11) NOT NULL,
  `tname` varchar(50) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `userpwd` varchar(50) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `upower` varchar(11) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
