/*
 Navicat Premium Data Transfer

 Source Server         : 39
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 10.252.21.39:3306
 Source Schema         : grg-cloud-counter-bank

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 09/11/2021 16:02:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grg_account
-- ----------------------------
DROP TABLE IF EXISTS `grg_account`;
CREATE TABLE `grg_account`  (
                                `id` int(0) NOT NULL AUTO_INCREMENT,
                                `customer_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
                                `card_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卡号',
                                `card_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡密',
                                `card_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡类型( 1：借记卡  2：信用卡)',
                                `card_certificate_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户证件类型',
                                `card_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
                                `account_sign_type` tinyint(1) NULL DEFAULT NULL COMMENT '账户签发类型（0：面签； 1：网签； 2：其它）',
                                `account_status` tinyint(1) NULL DEFAULT NULL COMMENT '账户状态（0：已激活； 1：未激活； 2：已挂失）',
                                `lock_status` tinyint(1) NULL DEFAULT NULL COMMENT '锁定状态（0：未锁定； 1：已锁定）',
                                `create_date` datetime(0) NOT NULL,
                                `card_level` tinyint(1) NULL DEFAULT NULL COMMENT '卡片等级（1： 一类卡； 2：二类卡）',
                                `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `update_date` datetime(0) NULL DEFAULT NULL,
                                `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `bind` int(0) NOT NULL COMMENT '银行卡绑定状态（0：未绑定；1：已绑定）',
                                `cvv_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信用卡验证码',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行卡详细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_account
-- ----------------------------
INSERT INTO `grg_account` VALUES (1, 8, '6228480136461875776', '123123', '1', '身份证', '中国农业银行', NULL, 2, NULL, '2021-09-01 15:03:12', NULL, NULL, '2021-11-08 16:01:28', NULL, 1, '3333');
INSERT INTO `grg_account` VALUES (2, 8, '6228480136461875778', '123123', '2', '身份证', '中国工商银行', NULL, 0, NULL, '2021-09-06 13:50:59', NULL, NULL, '2021-11-04 15:39:50', NULL, 1, '3333');
INSERT INTO `grg_account` VALUES (3, 8, '6228480136461875779', '123123', '1', '身份证', '中国农业银行', NULL, 0, NULL, '2021-09-06 14:43:14', NULL, NULL, '2021-11-05 14:47:23', NULL, 0, '3333');
INSERT INTO `grg_account` VALUES (4, 9, '6228480136461875780', '123123', '1', '身份证', '光大银行', NULL, 2, NULL, '2021-09-07 10:54:24', NULL, NULL, '2021-11-05 15:31:40', NULL, 0, '3333');
INSERT INTO `grg_account` VALUES (5, 9, '6228480136461875781', '123123', '2', '身份证', '招商银行', NULL, 2, NULL, '2021-09-08 13:47:43', NULL, NULL, '2021-11-05 15:34:21', NULL, 0, '3333');
INSERT INTO `grg_account` VALUES (6, 9, '6228480136461875782', '123123', '2', '身份证', '中国建设银行', NULL, 1, NULL, '2021-09-10 15:38:37', 1, NULL, '2021-11-05 16:34:10', NULL, 1, '3333');

-- ----------------------------
-- Table structure for grg_account_record
-- ----------------------------
DROP TABLE IF EXISTS `grg_account_record`;
CREATE TABLE `grg_account_record`  (
                                       `id` int(0) NOT NULL,
                                       `account_id` int(0) NOT NULL,
                                       `serial_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
                                       `card_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号',
                                       `merchant` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称',
                                       `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '金额',
                                       `tran_detail` tinyint(1) NULL DEFAULT NULL COMMENT '交易明细 1：收入  2：支出',
                                       `txn_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易状态',
                                       `create_date` datetime(0) NULL DEFAULT NULL,
                                       `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `update_date` datetime(0) NULL DEFAULT NULL,
                                       `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行卡业务操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_account_record
-- ----------------------------
INSERT INTO `grg_account_record` VALUES (1, 1, '20210903000001', '6228480136461875777', '太二', 500, 1, '1', '2021-09-03 15:59:32', NULL, '2021-09-03 15:59:36', NULL);
INSERT INTO `grg_account_record` VALUES (2, 1, '20210903000002', '6228480136461875777', '阿强', 256, 1, '1', '2021-09-03 16:03:21', NULL, '2021-09-03 16:03:24', NULL);
INSERT INTO `grg_account_record` VALUES (3, 2, '20210903000003', '6228480136461875777', '海底捞', 300, 1, '1', '2021-09-03 16:08:49', NULL, '2021-09-03 16:08:51', NULL);
INSERT INTO `grg_account_record` VALUES (4, 3, '20210903000004', '6228480136461875777', '太二', 300, 1, '1', '2021-10-28 14:01:30', NULL, '2021-10-28 14:01:34', NULL);

-- ----------------------------
-- Table structure for grg_customer
-- ----------------------------
DROP TABLE IF EXISTS `grg_customer`;
CREATE TABLE `grg_customer`  (
                                 `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                 `gender` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
                                 `cusbirthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
                                 `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
                                 `nickname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
                                 `csource` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人来源',
                                 `language` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
                                 `marriage` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
                                 `education` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
                                 `identifytype` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
                                 `identifynumber` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
                                 `idcardexpiredate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件到期日',
                                 `occupation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
                                 `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
                                 `emailalt` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用电子邮件',
                                 `mobileno` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
                                 `mobilealt` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用手机号码',
                                 `phone` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公电话',
                                 `extension` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公分机',
                                 `familyphone` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
                                 `fax` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真号码',
                                 `country` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
                                 `province` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
                                 `city` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市(区)县',
                                 `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地址',
                                 `postcode` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
                                 `weixin` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
                                 `weixinname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信昵称',
                                 `weixinid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信ID',
                                 `touchtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后联系时间',
                                 `datastatus` tinyint(0) NULL DEFAULT NULL COMMENT '数据状态',
                                 `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
                                 `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
                                 `update_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                 `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
                                 `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
                                 `portrait` text CHARACTER SET sjis COLLATE sjis_japanese_ci NULL COMMENT '头像',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_customer
-- ----------------------------
INSERT INTO `grg_customer` VALUES ('1', '1', '1997', '张小明', 'zhangXiaoMing', 'APP', '汉语', '已婚', '本科', '1', '440582199001156666', NULL, '技术人员12', '18319981182@163.com', NULL, NULL, NULL, '18319981182', NULL, '020-83434', NULL, NULL, '广东省', '广州市', '广州市天河区', '510001', NULL, NULL, NULL, '2021-10-27 14:07:04', NULL, NULL, NULL, NULL, '2021-10-27 14:07:04', NULL, '0', 'http://10.252.37.58:9000/counter/MJrWzOuAEREIALYh9Wic04tJ6IU1BSfSq?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210923T070820Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=minioadmin%2F20210923%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=31e31a9ad980561efd1a43ab59a2ca0357e0d69944653bfe7686d09df75c2e38');
INSERT INTO `grg_customer` VALUES ('10', '1', '1997', '小A', 'aa', 'APP', '汉语', '单身', '博士后', '1', '440582199001156669', NULL, '开发工程师', '18825092562@163.com', NULL, NULL, NULL, '18825092562', NULL, '020-343324', NULL, NULL, '广东省', '广州市', '广东省广州市天河区广电运通电子股份有限公司', '510003', NULL, NULL, NULL, '2021-10-27 14:07:13', NULL, NULL, NULL, NULL, '2021-10-27 14:07:13', NULL, '0', NULL);
INSERT INTO `grg_customer` VALUES ('8', '2', '1993', '郑燕', 'zhengYan', 'APP', '汉语', '已婚', '本科', '1', '440582199001156667', NULL, '开发工程师', '13711726710@163.com', NULL, NULL, NULL, '13711726710', NULL, '020-343323', NULL, NULL, '广东省', '广州市', '广东省广州市沙虎鲨', '510000', NULL, NULL, NULL, '2021-11-09 11:56:37', NULL, NULL, '2021-09-22 15:07:03', NULL, '2021-11-09 11:56:37', NULL, '0', 'http://10.252.37.58:9000/counter/MJrWzOuAEREIALYh9Wic04tJ6IU1BSfSq?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210923T070820Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=minioadmin%2F20210923%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=31e31a9ad980561efd1a43ab59a2ca0357e0d69944653bfe7686d09df75c2e38');
INSERT INTO `grg_customer` VALUES ('9', '2', '1998', '张嫒珊', 'zhangaishan', 'APP', '汉语', '已婚', '本科', '1', '441302199801184026', NULL, '技术人员12', '18319981182@163.com', NULL, NULL, NULL, '18319981184', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '510001', NULL, NULL, NULL, '2021-11-05 15:33:37', NULL, NULL, NULL, NULL, '2021-11-05 15:33:37', NULL, '0', NULL);

SET FOREIGN_KEY_CHECKS = 1;
