/*
 Navicat Premium Data Transfer

 Source Server         : 10.252.21.20
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 10.252.21.20:3306
 Source Schema         : grg-cloud-counter-bank

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 03/09/2021 16:26:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grg_account
-- ----------------------------
DROP TABLE IF EXISTS `grg_account`;
CREATE TABLE `grg_account` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `card_no` varchar(100) NOT NULL COMMENT '卡号',
  `card_pwd` varchar(100) NOT NULL COMMENT '卡密',
  `card_certificate_type` varchar(50) DEFAULT NULL COMMENT '开户证件类型',
  `account_sign_type` tinyint(1) DEFAULT NULL COMMENT '账户签发类型（0：面签； 1：网签； 2：其它）',
  `account_status` tinyint(1) DEFAULT NULL COMMENT '账户状态（0：已激活； 1：未激活； 2：已挂失）',
  `lock_status` tinyint(1) DEFAULT NULL COMMENT '锁定状态（0：未锁定； 1：已锁定）',
  `create_date` datetime NOT NULL,
  `card_level` tinyint(1) DEFAULT NULL COMMENT '卡片等级（1： 一类卡； 2：二类卡）',
  `create_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡详细表';

-- ----------------------------
-- Records of grg_account
-- ----------------------------
BEGIN;
INSERT INTO `grg_account` VALUES (1, 1, '12345678', '123456', '身份证', NULL, NULL, NULL, '2021-09-01 15:03:12', NULL, NULL, '2021-09-01 15:03:12', NULL);
COMMIT;

-- ----------------------------
-- Table structure for grg_account_record
-- ----------------------------
DROP TABLE IF EXISTS `grg_account_record`;
CREATE TABLE `grg_account_record` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL COMMENT '账户id',
  `serial_number` varchar(100) DEFAULT NULL COMMENT '流水号',
  `business_type` int(11) DEFAULT NULL COMMENT '业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级）',
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡业务操作记录表';

-- ----------------------------
-- Records of grg_account_record
-- ----------------------------
BEGIN;
INSERT INTO `grg_account_record` VALUES (1, 1, '1236541', 2, '2021-09-03 15:59:32', NULL, '2021-09-03 15:59:36', NULL);
INSERT INTO `grg_account_record` VALUES (2, 2, '99999', 1, '2021-09-03 16:03:21', NULL, '2021-09-03 16:03:24', NULL);
INSERT INTO `grg_account_record` VALUES (3, 2, '9999', 1, '2021-09-03 16:08:49', NULL, '2021-09-03 16:08:51', NULL);
COMMIT;

-- ----------------------------
-- Table structure for grg_customer
-- ----------------------------
DROP TABLE IF EXISTS `grg_customer`;
CREATE TABLE `grg_customer` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `gender` varchar(60) DEFAULT NULL COMMENT '性别',
  `cusbirthday` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `name` varchar(128) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `csource` varchar(64) DEFAULT NULL COMMENT '联系人来源',
  `language` varchar(40) DEFAULT NULL COMMENT '语言',
  `marriage` varchar(60) DEFAULT NULL COMMENT '婚姻状况',
  `education` varchar(60) DEFAULT NULL COMMENT '学历',
  `identifytype` varchar(60) DEFAULT NULL COMMENT '证件类型',
  `identifynumber` varchar(40) DEFAULT NULL COMMENT '证件号码',
  `idcardexpiredate` varchar(50) DEFAULT NULL COMMENT '证件到期日',
  `occupation` varchar(50) DEFAULT NULL COMMENT '职业',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮件',
  `emailalt` varchar(128) DEFAULT NULL COMMENT '备用电子邮件',
  `mobileno` varchar(40) DEFAULT NULL COMMENT '手机号码',
  `mobilealt` varchar(40) DEFAULT NULL COMMENT '备用手机号码',
  `phone` varchar(40) DEFAULT NULL COMMENT '办公电话',
  `extension` varchar(40) DEFAULT NULL COMMENT '办公分机',
  `familyphone` varchar(40) DEFAULT NULL COMMENT '住宅电话',
  `fax` varchar(40) DEFAULT NULL COMMENT '传真号码',
  `country` varchar(60) DEFAULT NULL COMMENT '国家',
  `province` varchar(60) DEFAULT NULL COMMENT '省',
  `city` varchar(60) DEFAULT NULL COMMENT '市(区)县',
  `address` text COMMENT '地址',
  `postcode` varchar(40) DEFAULT NULL COMMENT '邮政编码',
  `weixin` varchar(60) DEFAULT NULL COMMENT '微信号',
  `weixinname` varchar(60) DEFAULT NULL COMMENT '微信昵称',
  `weixinid` varchar(255) DEFAULT NULL COMMENT '微信ID',
  `touchtime` datetime DEFAULT NULL COMMENT '最后联系时间',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户详情表';

-- ----------------------------
-- Records of grg_customer
-- ----------------------------
BEGIN;
INSERT INTO `grg_customer` VALUES ('1', '1', '1997', '旭旭宝宝', '大马猴', NULL, NULL, NULL, '本科', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
