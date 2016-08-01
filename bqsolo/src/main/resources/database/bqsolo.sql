/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : bqsolo

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-08-01 09:15:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL COMMENT '用户名',
  `userpass` varchar(32) NOT NULL COMMENT '用户密码',
  `useremail` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `logintime` datetime DEFAULT NULL COMMENT '登陆时间',
  `loginip` varchar(15) DEFAULT NULL COMMENT '登陆ip',
  `cuid` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `cdate` datetime DEFAULT NULL COMMENT '创建日期',
  `changeuid` int(11) unsigned DEFAULT NULL COMMENT '修改者',
  `changedate` datetime DEFAULT NULL COMMENT '修改日期',
  `isvoid` int(1) DEFAULT '1' COMMENT '删除者(1有效，0无效)',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gruopname` varchar(32) DEFAULT '' COMMENT '组名',
  `shorthand` varchar(8) DEFAULT NULL COMMENT '简写',
  `cuid` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `cdate` datetime DEFAULT NULL COMMENT '创建日期',
  `changeuid` int(11) unsigned DEFAULT NULL COMMENT '修改者',
  `changedate` datetime DEFAULT NULL COMMENT '修改日期',
  `isvoid` int(1) DEFAULT '1' COMMENT '删除者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pic
-- ----------------------------
DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logicname` varchar(64) NOT NULL COMMENT '逻辑名称(bqsolo.top.{userid}[5位随机数])',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `path` varchar(200) NOT NULL COMMENT '地址',
  `priority` double(3,2) NOT NULL DEFAULT '5.00' COMMENT '权重（风格）1-10 2位小数',
  `lable` varchar(64) DEFAULT NULL COMMENT '标签 多个用，号',
  `MD5` varchar(64) DEFAULT NULL COMMENT '文件md5',
  `click` int(11) NOT NULL DEFAULT '0' COMMENT '点击',
  `gruopid` int(11) NOT NULL DEFAULT '-1' COMMENT '组',
  `cuid` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `cdate` datetime DEFAULT NULL COMMENT '创建日期',
  `changeuid` int(11) unsigned DEFAULT NULL COMMENT '修改者',
  `changedate` datetime DEFAULT NULL COMMENT '修改日期',
  `isvoid` int(1) DEFAULT '1' COMMENT '删除者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户名',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `img` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `priority` double(3,2) NOT NULL DEFAULT '5.00' COMMENT '权重（风格）1-10 2位小数',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `logintime` int(11) DEFAULT NULL COMMENT '登陆时间',
  `cuid` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `cdate` datetime DEFAULT NULL COMMENT '创建日期',
  `changeuid` int(11) unsigned DEFAULT NULL COMMENT '修改者',
  `changedate` datetime DEFAULT NULL COMMENT '修改日期',
  `isvoid` int(1) NOT NULL DEFAULT '1' COMMENT '删除者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
