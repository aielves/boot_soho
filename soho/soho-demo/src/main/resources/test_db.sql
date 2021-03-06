/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-28 17:54:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for color
-- ----------------------------
DROP TABLE IF EXISTS `color`;
CREATE TABLE `color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '第三方平台公钥',
  `age` int(4) DEFAULT NULL COMMENT '第三方平台域名',
  `sex` int(4) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `utime` bigint(20) DEFAULT '0',
  `state` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='第三方平台';

-- ----------------------------
-- Records of color
-- ----------------------------
INSERT INTO `color` VALUES ('2', 'test', '1', '1', '0', '0', '1');

-- ----------------------------
-- Table structure for dog
-- ----------------------------
DROP TABLE IF EXISTS `dog`;
CREATE TABLE `dog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '第三方平台公钥',
  `age` int(4) DEFAULT NULL COMMENT '第三方平台域名',
  `sex` int(4) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  `utime` bigint(20) DEFAULT '0',
  `state` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='第三方平台';

-- ----------------------------
-- Records of dog
-- ----------------------------
INSERT INTO `dog` VALUES ('2', '小狗', '5', '1', '1521983559434', '0', '1');
INSERT INTO `dog` VALUES ('3', '??', '5', '1', '1522137045894', '0', '1');
INSERT INTO `dog` VALUES ('5', '??', '5', '1', '1522140691214', '0', '1');
INSERT INTO `dog` VALUES ('6', '??', '5', '1', '1522141027839', '0', '1');
INSERT INTO `dog` VALUES ('7', '??', '5', '1', '1522141124201', '0', '1');
INSERT INTO `dog` VALUES ('8', '??', '5', '1', '1522142760166', '0', '1');

-- ----------------------------
-- Table structure for oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) DEFAULT NULL COMMENT '第三方平台公钥',
  `client_secret` varchar(100) DEFAULT NULL COMMENT '第三方授权密钥',
  `company` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `mobile` varchar(100) DEFAULT NULL COMMENT '联系号码',
  `address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `domain_uri` varchar(255) DEFAULT NULL COMMENT '第三方平台域名',
  `bindIp` varchar(100) DEFAULT NULL COMMENT '第三方服务器IP',
  `usestate` int(4) DEFAULT '1' COMMENT '1.正常 2.冻结 3.禁用',
  `ctime` bigint(20) DEFAULT NULL,
  `utime` bigint(20) DEFAULT '0',
  `state` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_appid` (`client_id`),
  KEY `idx_appkey` (`client_secret`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='第三方平台';

-- ----------------------------
-- Records of oauth_client
-- ----------------------------
INSERT INTO `oauth_client` VALUES ('2', 'c522f0c158d4c9d5be2f1032c38a8148', 'dedadc9d4d3525033047d12150ab3b065405ab7eb0ac4e9f5a2dcedb1653f3eeb6cf1a742a8dcca814f668cb1ada781f', '皮皮公司', '13823912345', '中山', 'http://localhost', '192.168.1.176', '1', '0', '0', '1');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) DEFAULT NULL COMMENT '第三方平台公钥',
  `uid` varchar(100) DEFAULT NULL,
  `access_token` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `code_state` int(4) DEFAULT '1' COMMENT '1.未使用 2.已使用',
  `code_expire` bigint(20) DEFAULT NULL,
  `token_state` int(4) DEFAULT '1' COMMENT '1.有效 2.无效',
  `token_expire` bigint(20) DEFAULT NULL,
  `access_time` bigint(20) DEFAULT NULL,
  `refresh_token` varchar(100) DEFAULT NULL,
  `refresh_time` bigint(20) DEFAULT NULL,
  `logout_time` bigint(20) DEFAULT NULL,
  `redirect_uri` varchar(100) DEFAULT NULL COMMENT '授权接口重定向地址',
  `ctime` bigint(20) DEFAULT NULL,
  `utime` bigint(20) DEFAULT '0',
  `state` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`),
  UNIQUE KEY `idx_token` (`access_token`),
  UNIQUE KEY `idx_refreshtoken` (`refresh_token`),
  KEY `idx_appid` (`client_id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_token_state` (`token_state`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='平台授权TOKEN';

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------
INSERT INTO `oauth_client_token` VALUES ('41', 'c522f0c158d4c9d5be2f1032c38a8148', '7930040530a4744d77504bbbff7e7cb3', '9caf41bafc84782a3d842e3517b264e0', 'dc535ebf8cd08253fa6dc239c93aa927', '2', '1524909220481', '1', '1526118220481', '1524908620481', '05bd820da99946dbd1a34a3e48ad6bff', '1524908620481', '0', 'http://localhost:8080/oauth2.0/callback', '1524908620481', '1524909205088', '1');

-- ----------------------------
-- Table structure for oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `company` varchar(100) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uid` (`uid`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_user
-- ----------------------------
INSERT INTO `oauth_user` VALUES ('6', '7930040530a4744d77504bbbff7e7cb3', 'zhangsan', '7930040530a4744d77504bbbff7e7cb3', '张三', '皮皮公司', '0');
