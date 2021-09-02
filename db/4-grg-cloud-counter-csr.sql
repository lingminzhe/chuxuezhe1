/*
 Navicat Premium Data Transfer

 Source Server         : 10.252.21.20
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 10.252.21.20:3306
 Source Schema         : grg-cloud-counter-csr

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 02/09/2021 14:34:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grg_agent
-- ----------------------------
DROP TABLE IF EXISTS `grg_agent`;
CREATE TABLE `grg_agent` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `agent_no` varchar(50) DEFAULT NULL COMMENT '坐席号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `bank_branch` varchar(50) DEFAULT NULL COMMENT '所在支行',
  `start_time` datetime DEFAULT NULL COMMENT '开始工作时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束工作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='坐席详情表';

-- ----------------------------
-- Records of grg_agent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for grg_agent_record
-- ----------------------------
DROP TABLE IF EXISTS `grg_agent_record`;
CREATE TABLE `grg_agent_record` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `agent_id` varchar(64) DEFAULT NULL COMMENT '坐席id',
  `business_no` varbinary(50) DEFAULT NULL COMMENT '业务编号',
  `business_type` int(11) DEFAULT NULL COMMENT '业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级）',
  `business_status` tinyint(1) DEFAULT NULL COMMENT '业务状态（0：失败； 1：已完成）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='坐席业务记录表';

-- ----------------------------
-- Records of grg_agent_record
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
