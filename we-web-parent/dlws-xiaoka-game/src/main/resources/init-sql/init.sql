/*
SQLyog v10.2 
MySQL - 5.1.73 : Database - web-template
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`web-template` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `web-template`;

/*Table structure for table `s_function` */

DROP TABLE IF EXISTS `s_function`;

CREATE TABLE `s_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` int(11) DEFAULT NULL COMMENT '上级功能id',
  `name` varchar(32) DEFAULT '' COMMENT '功能名称',
  `url` varchar(128) DEFAULT '' COMMENT '访问地址',
  `level` int(11) DEFAULT '0' COMMENT '功能等级，是指在功能树中所处的层级',
  `remark` varchar(128) DEFAULT '' COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort` int(4) DEFAULT '0' COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='功能菜单表';

/*Table structure for table `s_log` */

DROP TABLE IF EXISTS `s_log`;

CREATE TABLE `s_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operatorId` int(11) DEFAULT '0' COMMENT '操作员id',
  `functionId` int(11) DEFAULT '0' COMMENT '功能id',
  `operatorName` varchar(64) DEFAULT '' COMMENT '操作员名称',
  `path` varchar(255) DEFAULT '' COMMENT '访问路径',
  `functionName` varchar(64) DEFAULT '' COMMENT '功能名称',
  `operatorTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

/*Table structure for table `s_role` */

DROP TABLE IF EXISTS `s_role`;

CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) DEFAULT '' COMMENT '角色名称',
  `remark` varchar(64) DEFAULT '' COMMENT '备注',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `s_role_function_map` */

DROP TABLE IF EXISTS `s_role_function_map`;

CREATE TABLE `s_role_function_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleId` int(11) DEFAULT NULL COMMENT '角色id',
  `functionId` int(11) DEFAULT NULL COMMENT '功能id',
  PRIMARY KEY (`id`),
  KEY `FK_roleid` (`roleId`),
  KEY `FK_role_funcId` (`functionId`),
  CONSTRAINT `FK_roleid` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_role_funcId` FOREIGN KEY (`functionId`) REFERENCES `s_function` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='角色功能关系表';

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(32) DEFAULT '' COMMENT '用户名',
  `password` varchar(36) DEFAULT '' COMMENT '密码',
  `email` varchar(64) DEFAULT '' COMMENT '用户邮箱',
  `tel` varchar(16) DEFAULT '' COMMENT '手机号',
  `userType` varchar(32) DEFAULT '' COMMENT '参见字典-用户类型',
  `status` int(11) DEFAULT '0' COMMENT '用户状态，0-初始化，1-启用，2-停用',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `s_user_role_map` */

DROP TABLE IF EXISTS `s_user_role_map`;

CREATE TABLE `s_user_role_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) DEFAULT '0' COMMENT '用户id',
  `roleId` int(11) DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`roleId`),
  KEY `FK_Reference_6` (`userId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`userId`) REFERENCES `s_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;