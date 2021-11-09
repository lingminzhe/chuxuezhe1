/*
 Navicat Premium Data Transfer

 Source Server         : 39
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 10.252.21.39:3306
 Source Schema         : grg-cloud-counter-csr

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 09/11/2021 16:02:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grg_api_info
-- ----------------------------
DROP TABLE IF EXISTS `grg_api_info`;
CREATE TABLE `grg_api_info`  (
                                 `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                 `api_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口编码',
                                 `api_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
                                 `api_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口状态',
                                 `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                 `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_api_info
-- ----------------------------

-- ----------------------------
-- Table structure for grg_busi_info
-- ----------------------------
DROP TABLE IF EXISTS `grg_busi_info`;
CREATE TABLE `grg_busi_info`  (
                                  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                  `busi_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类别',
                                  `busi_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编码',
                                  `busi_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称',
                                  `busi_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务状态',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_busi_info
-- ----------------------------
INSERT INTO `grg_busi_info` VALUES ('1001', '2', '100001', '个人信息修改', '1', '2021-09-08 13:04:40', '2021-09-08 13:14:36');
INSERT INTO `grg_busi_info` VALUES ('1002', '2', '100002', '个人信息修改（敏感）', '1', '2021-09-08 13:04:57', '2021-09-08 13:14:37');
INSERT INTO `grg_busi_info` VALUES ('1003', '2', '110001', '挂失', '1', '2021-09-08 13:05:58', '2021-09-08 13:14:39');
INSERT INTO `grg_busi_info` VALUES ('1004', '2', '120001', '信用卡激活', '1', '2021-10-12 16:24:19', '2021-10-27 10:05:51');

-- ----------------------------
-- Table structure for grg_busi_opt
-- ----------------------------
DROP TABLE IF EXISTS `grg_busi_opt`;
CREATE TABLE `grg_busi_opt`  (
                                 `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                 `session_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会话ID',
                                 `busi_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编码',
                                 `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '坐席ID',
                                 `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户ID',
                                 `busi_opt_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务办理状态 (1、已完成  2、进行中 3、未完成)',
                                 `busi_opt_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务操作流水号',
                                 `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                 `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务操作流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_busi_opt
-- ----------------------------
INSERT INTO `grg_busi_opt` VALUES ('1', '', '100001', '101', '1', '1', '100000001', '2021-09-08 13:35:08', '2021-09-16 15:32:00');
INSERT INTO `grg_busi_opt` VALUES ('1438697371507773442', '', '100002', '103', '1', '2', '100000008', '2021-08-17 10:49:15', '2021-09-27 10:33:21');
INSERT INTO `grg_busi_opt` VALUES ('1438761450071019522', '', '100002', '101', '1', '2', '100000009', '2021-09-17 15:03:53', '2021-09-27 10:33:22');
INSERT INTO `grg_busi_opt` VALUES ('2', '', '100002', '102', '1', '3', '100000002', '2021-09-16 16:28:35', '2021-09-27 10:33:23');
INSERT INTO `grg_busi_opt` VALUES ('3', '', '100002', '103', '1', '3', '100000003', '2021-09-16 16:31:20', '2021-09-27 10:33:23');
INSERT INTO `grg_busi_opt` VALUES ('4', '', '100002', '103', '1', '3', '100000004', '2021-09-17 10:12:53', '2021-09-27 10:33:24');
INSERT INTO `grg_busi_opt` VALUES ('5', '', '100002', '103', '1', '1', '100000005', '2021-09-17 10:35:29', '2021-09-27 10:33:25');
INSERT INTO `grg_busi_opt` VALUES ('58a5b80ccac276f2ac2be35015137358', '', '100002', '103', '1', '1', '100000006', '2021-09-17 10:47:37', '2021-09-27 10:33:26');
INSERT INTO `grg_busi_opt` VALUES ('b68d75dc540bbdb38ef47cc7e2ca8f7a', '', '100002', '103', '1', '1', '100000007', '2021-09-17 10:48:09', '2021-09-27 10:33:31');
INSERT INTO `grg_busi_opt` VALUES ('b7e8474921ed3fb00d7956286d5487e8', '1448187593106812929', '100001', '102', 'sdfsd4645464', NULL, NULL, '2021-10-13 15:23:26', '2021-10-13 15:23:26');

-- ----------------------------
-- Table structure for grg_employee_service
-- ----------------------------
DROP TABLE IF EXISTS `grg_employee_service`;
CREATE TABLE `grg_employee_service`  (
                                         `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                         `employee_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '坐席ID',
                                         `employee_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '坐席状态(1、签入：用户登录；\n2、就绪：工作人员进入待办业务状态；\n3、办理：工作人员业务办理中状态；\n4、小憩：人员暂时离开；\n5、签出：用户坐席离线；)',
                                         `busi_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编码',
                                         `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                         `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '坐席客服表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_employee_service
-- ----------------------------
INSERT INTO `grg_employee_service` VALUES ('1', '101', '5', '100001', '2021-09-13 13:55:48', '2021-11-09 15:37:38');
INSERT INTO `grg_employee_service` VALUES ('2', '102', '5', '100001', '2021-09-13 14:34:17', '2021-11-09 10:39:54');
INSERT INTO `grg_employee_service` VALUES ('3', '103', '5', '110001', '2021-09-13 14:57:47', '2021-11-09 15:03:29');
INSERT INTO `grg_employee_service` VALUES ('4', '104', '1', '100001', '2021-10-21 18:00:14', '2021-10-27 13:15:38');
INSERT INTO `grg_employee_service` VALUES ('5', '105', '5', '100001', '2021-10-21 18:00:24', '2021-10-28 08:44:51');

-- ----------------------------
-- Table structure for grg_employee_work_record
-- ----------------------------
DROP TABLE IF EXISTS `grg_employee_work_record`;
CREATE TABLE `grg_employee_work_record`  (
                                             `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
                                             `employee_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '坐席ID',
                                             `work_date` date NULL DEFAULT NULL COMMENT '工作日期',
                                             `work_time` datetime(0) NULL DEFAULT NULL COMMENT '工作时间',
                                             `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                             `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '坐席工作时间记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_employee_work_record
-- ----------------------------

-- ----------------------------
-- Table structure for grg_file_manager
-- ----------------------------
DROP TABLE IF EXISTS `grg_file_manager`;
CREATE TABLE `grg_file_manager`  (
                                     `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户ID',
                                     `file_busi_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
                                     `file_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件ID',
                                     `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                     `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                     `session_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会话ID',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 657 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grg_file_manager
-- ----------------------------
INSERT INTO `grg_file_manager` VALUES (95, '10001', '101', '173', '2021-10-14 10:51:31', '2021-10-14 10:51:31', '10000');
INSERT INTO `grg_file_manager` VALUES (96, '10001', '101', '174', '2021-10-14 10:51:32', '2021-10-14 10:51:32', '10000');
INSERT INTO `grg_file_manager` VALUES (97, '10001', '101', '178', '2021-10-14 14:19:39', '2021-10-14 14:19:39', '100001');
INSERT INTO `grg_file_manager` VALUES (98, '10001', '102', '179', '2021-10-14 14:19:39', '2021-10-14 14:19:39', '100001');
INSERT INTO `grg_file_manager` VALUES (99, '10001', '101', '180', '2021-10-14 14:26:14', '2021-10-14 14:26:14', '100001');
INSERT INTO `grg_file_manager` VALUES (100, '10001', '102', '181', '2021-10-14 14:26:14', '2021-10-14 14:26:14', '100001');
INSERT INTO `grg_file_manager` VALUES (101, '10001', '101', '182', '2021-10-14 14:29:14', '2021-10-14 14:29:14', '100001');
INSERT INTO `grg_file_manager` VALUES (102, '10001', '102', '183', '2021-10-14 14:29:14', '2021-10-14 14:29:14', '100001');
INSERT INTO `grg_file_manager` VALUES (103, '10001', '101', '186', '2021-10-15 10:17:21', '2021-10-15 10:17:21', '123456789');
INSERT INTO `grg_file_manager` VALUES (104, '10001', '102', '187', '2021-10-15 10:17:22', '2021-10-15 10:17:22', '123456789');
INSERT INTO `grg_file_manager` VALUES (105, '10001', '101', '188', '2021-10-15 10:26:47', '2021-10-15 10:26:47', '123456789');
INSERT INTO `grg_file_manager` VALUES (106, '10001', '102', '189', '2021-10-15 10:26:47', '2021-10-15 10:26:47', '123456789');
INSERT INTO `grg_file_manager` VALUES (107, '10001', '101', '190', '2021-10-15 10:35:08', '2021-10-15 10:35:08', '123456789');
INSERT INTO `grg_file_manager` VALUES (108, '10001', '102', '191', '2021-10-15 10:35:08', '2021-10-15 10:35:08', '123456789');
INSERT INTO `grg_file_manager` VALUES (109, '10001', '101', '192', '2021-10-15 10:46:36', '2021-10-15 10:46:36', '123456789');
INSERT INTO `grg_file_manager` VALUES (110, '10001', '102', '193', '2021-10-15 10:46:36', '2021-10-15 10:46:36', '123456789');
INSERT INTO `grg_file_manager` VALUES (111, '10001', '101', '194', '2021-10-15 10:54:14', '2021-10-15 10:54:14', '123456789');
INSERT INTO `grg_file_manager` VALUES (112, '10001', '102', '195', '2021-10-15 10:54:14', '2021-10-15 10:54:14', '123456789');
INSERT INTO `grg_file_manager` VALUES (113, '10001', '101', '196', '2021-10-15 11:03:16', '2021-10-15 11:03:16', '123456789');
INSERT INTO `grg_file_manager` VALUES (114, '10001', '102', '197', '2021-10-15 11:03:16', '2021-10-15 11:03:16', '123456789');
INSERT INTO `grg_file_manager` VALUES (115, '10002', '101', '198', '2021-10-15 11:25:10', '2021-10-15 11:25:10', '123');
INSERT INTO `grg_file_manager` VALUES (116, '10002', '101', '201', '2021-10-15 14:16:01', '2021-10-15 14:16:01', '123456789');
INSERT INTO `grg_file_manager` VALUES (117, NULL, '101', '202', '2021-10-15 14:50:32', '2021-10-15 14:50:32', '123456789');
INSERT INTO `grg_file_manager` VALUES (118, NULL, '102', '203', '2021-10-15 14:50:33', '2021-10-15 14:50:33', '123456789');
INSERT INTO `grg_file_manager` VALUES (119, NULL, '101', '204', '2021-10-15 14:51:23', '2021-10-15 14:51:23', '123456789');
INSERT INTO `grg_file_manager` VALUES (120, NULL, '102', '205', '2021-10-15 14:51:24', '2021-10-15 14:51:24', '123456789');
INSERT INTO `grg_file_manager` VALUES (121, NULL, '101', '206', '2021-10-15 14:52:17', '2021-10-15 14:52:17', '123456789');
INSERT INTO `grg_file_manager` VALUES (122, NULL, '102', '207', '2021-10-15 14:52:17', '2021-10-15 14:52:17', '123456789');
INSERT INTO `grg_file_manager` VALUES (123, NULL, '101', '208', '2021-10-15 15:48:22', '2021-10-15 15:48:22', '123456789');
INSERT INTO `grg_file_manager` VALUES (124, NULL, '102', '209', '2021-10-15 15:48:26', '2021-10-15 15:48:26', '123456789');
INSERT INTO `grg_file_manager` VALUES (125, NULL, '101', '210', '2021-10-15 15:50:32', '2021-10-15 15:50:32', '123456789');
INSERT INTO `grg_file_manager` VALUES (126, NULL, '102', '211', '2021-10-15 15:50:32', '2021-10-15 15:50:32', '123456789');
INSERT INTO `grg_file_manager` VALUES (127, NULL, '101', '212', '2021-10-15 15:52:54', '2021-10-15 15:52:54', '123456789');
INSERT INTO `grg_file_manager` VALUES (128, NULL, '102', '213', '2021-10-15 15:52:55', '2021-10-15 15:52:55', '123456789');
INSERT INTO `grg_file_manager` VALUES (158, NULL, '101', '249', '2021-10-16 10:36:42', '2021-10-16 10:36:42', '123456789');
INSERT INTO `grg_file_manager` VALUES (159, NULL, '102', '250', '2021-10-16 10:36:42', '2021-10-16 10:36:42', '123456789');
INSERT INTO `grg_file_manager` VALUES (160, NULL, '101', '251', '2021-10-16 10:41:13', '2021-10-16 10:41:13', '123456789');
INSERT INTO `grg_file_manager` VALUES (161, NULL, '101', '253', '2021-10-16 10:42:47', '2021-10-16 10:42:47', '123456789');
INSERT INTO `grg_file_manager` VALUES (162, NULL, '101', '252', '2021-10-16 10:42:47', '2021-10-16 10:42:47', '123456789');
INSERT INTO `grg_file_manager` VALUES (163, NULL, '101', '254', '2021-10-16 10:44:20', '2021-10-16 10:44:20', '123456789');
INSERT INTO `grg_file_manager` VALUES (164, NULL, '101', '254', '2021-10-16 10:45:21', '2021-10-16 10:45:21', '123456789');
INSERT INTO `grg_file_manager` VALUES (165, NULL, '101', '255', '2021-10-16 10:45:21', '2021-10-16 10:45:21', '123456789');
INSERT INTO `grg_file_manager` VALUES (166, NULL, '101', '256', '2021-10-16 10:45:21', '2021-10-16 10:45:21', '123456789');
INSERT INTO `grg_file_manager` VALUES (167, '10002', '101', '257', '2021-10-16 10:45:28', '2021-10-16 11:03:05', '1234567890');
INSERT INTO `grg_file_manager` VALUES (168, '10002', '102', '258', '2021-10-16 10:45:28', '2021-10-16 11:02:44', '1234567890');
INSERT INTO `grg_file_manager` VALUES (169, '10002', '101', '259', '2021-10-16 11:13:56', '2021-10-16 11:13:56', '123456789');
INSERT INTO `grg_file_manager` VALUES (170, '10002', '101', '265', '2021-10-16 13:35:48', '2021-10-16 13:35:48', '123456789');
INSERT INTO `grg_file_manager` VALUES (171, '10002', '102', '266', '2021-10-16 13:35:48', '2021-10-16 13:35:48', '123456789');
INSERT INTO `grg_file_manager` VALUES (172, '10002', '101', '267', '2021-10-16 13:35:58', '2021-10-16 13:35:58', '123456789');
INSERT INTO `grg_file_manager` VALUES (173, '10002', '102', '268', '2021-10-16 13:35:58', '2021-10-16 13:35:58', '123456789');
INSERT INTO `grg_file_manager` VALUES (174, '10002', '101', '269', '2021-10-16 13:36:57', '2021-10-16 13:36:57', '123456789');
INSERT INTO `grg_file_manager` VALUES (175, '10002', '102', '270', '2021-10-16 13:36:57', '2021-10-16 13:36:57', '123456789');
INSERT INTO `grg_file_manager` VALUES (176, '10002', '101', '272', '2021-10-16 15:19:34', '2021-10-16 15:19:34', '123456789');
INSERT INTO `grg_file_manager` VALUES (177, '10002', '102', '273', '2021-10-16 15:19:34', '2021-10-16 15:19:34', '123456789');
INSERT INTO `grg_file_manager` VALUES (178, '10002', '101', '274', '2021-10-16 15:20:02', '2021-10-16 15:20:02', '123456789');
INSERT INTO `grg_file_manager` VALUES (179, '10002', '102', '275', '2021-10-16 15:20:03', '2021-10-16 15:20:03', '123456789');
INSERT INTO `grg_file_manager` VALUES (180, '10002', '101', '276', '2021-10-16 15:20:09', '2021-10-16 15:20:09', '123456789');
INSERT INTO `grg_file_manager` VALUES (181, '10002', '102', '277', '2021-10-16 15:20:10', '2021-10-16 15:20:10', '123456789');
INSERT INTO `grg_file_manager` VALUES (182, '10002', '101', '278', '2021-10-16 15:22:37', '2021-10-16 15:22:37', '456789');
INSERT INTO `grg_file_manager` VALUES (183, '10002', '102', '279', '2021-10-16 15:22:37', '2021-10-16 15:22:37', '456789');
INSERT INTO `grg_file_manager` VALUES (184, NULL, '101', '291', '2021-10-16 16:59:03', '2021-10-16 16:59:03', '456789');
INSERT INTO `grg_file_manager` VALUES (185, NULL, '102', '292', '2021-10-16 16:59:04', '2021-10-16 16:59:04', '456789');
INSERT INTO `grg_file_manager` VALUES (186, NULL, '101', '293', '2021-10-16 17:06:09', '2021-10-16 17:06:09', '4567890');
INSERT INTO `grg_file_manager` VALUES (187, NULL, '102', '294', '2021-10-16 17:06:09', '2021-10-16 17:06:09', '4567890');
INSERT INTO `grg_file_manager` VALUES (188, NULL, '101', '295', '2021-10-18 08:39:54', '2021-10-18 08:39:54', '4567890');
INSERT INTO `grg_file_manager` VALUES (189, NULL, '102', '296', '2021-10-18 08:39:54', '2021-10-18 08:39:54', '4567890');
INSERT INTO `grg_file_manager` VALUES (190, NULL, '101', '297', '2021-10-18 09:10:44', '2021-10-18 09:10:44', '1449906616660119554');
INSERT INTO `grg_file_manager` VALUES (191, NULL, '102', '298', '2021-10-18 09:10:45', '2021-10-18 09:10:45', '1449906616660119554');
INSERT INTO `grg_file_manager` VALUES (192, NULL, '101', '299', '2021-10-18 09:21:11', '2021-10-18 09:21:11', '4567890');
INSERT INTO `grg_file_manager` VALUES (193, NULL, '102', '300', '2021-10-18 09:21:12', '2021-10-18 09:21:12', '4567890');
INSERT INTO `grg_file_manager` VALUES (194, NULL, '101', '301', '2021-10-18 09:38:58', '2021-10-18 09:38:58', '1449913682976034818');
INSERT INTO `grg_file_manager` VALUES (195, NULL, '102', '302', '2021-10-18 09:38:59', '2021-10-18 09:38:59', '1449913682976034818');
INSERT INTO `grg_file_manager` VALUES (196, '1234', '101', '304', '2021-10-18 09:47:35', '2021-10-18 09:47:35', '100001');
INSERT INTO `grg_file_manager` VALUES (197, '1234', '102', '305', '2021-10-18 09:47:35', '2021-10-18 09:47:35', '100001');
INSERT INTO `grg_file_manager` VALUES (198, '1234', '101', '306', '2021-10-18 09:50:11', '2021-10-18 09:50:11', '100001');
INSERT INTO `grg_file_manager` VALUES (199, '1234', '102', '307', '2021-10-18 09:50:11', '2021-10-18 09:50:11', '100001');
INSERT INTO `grg_file_manager` VALUES (200, '1234', '101', '308', '2021-10-18 09:58:55', '2021-10-18 09:58:55', '100001');
INSERT INTO `grg_file_manager` VALUES (201, '1234', '101', '309', '2021-10-18 09:58:55', '2021-10-18 09:58:55', '100001');
INSERT INTO `grg_file_manager` VALUES (202, '1234', '102', '310', '2021-10-18 09:58:56', '2021-10-18 09:58:56', '100001');
INSERT INTO `grg_file_manager` VALUES (203, NULL, '101', '311', '2021-10-18 10:12:52', '2021-10-18 10:12:52', '1449921223625789441');
INSERT INTO `grg_file_manager` VALUES (204, NULL, '101', '311', '2021-10-18 10:12:53', '2021-10-18 10:12:53', '1449921223625789441');
INSERT INTO `grg_file_manager` VALUES (205, NULL, '101', '311', '2021-10-18 10:12:54', '2021-10-18 10:12:54', '1449921223625789441');
INSERT INTO `grg_file_manager` VALUES (206, NULL, '101', '314', '2021-10-18 10:12:54', '2021-10-18 10:12:54', '1449921223625789441');
INSERT INTO `grg_file_manager` VALUES (207, NULL, '102', '315', '2021-10-18 10:12:55', '2021-10-18 10:12:55', '1449921223625789441');
INSERT INTO `grg_file_manager` VALUES (208, NULL, '101', '316', '2021-10-18 10:16:57', '2021-10-18 10:16:57', '1449923269837811713');
INSERT INTO `grg_file_manager` VALUES (209, NULL, '102', '317', '2021-10-18 10:16:57', '2021-10-18 10:16:57', '1449923269837811713');
INSERT INTO `grg_file_manager` VALUES (210, NULL, '101', '321', '2021-10-18 10:58:28', '2021-10-18 10:58:28', '1449933777676783617');
INSERT INTO `grg_file_manager` VALUES (211, NULL, '102', '322', '2021-10-18 10:58:28', '2021-10-18 10:58:28', '1449933777676783617');
INSERT INTO `grg_file_manager` VALUES (212, NULL, '101', '323', '2021-10-18 11:02:58', '2021-10-18 11:02:58', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (213, NULL, '102', '324', '2021-10-18 11:02:59', '2021-10-18 11:02:59', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (214, NULL, '101', '325', '2021-10-18 11:03:43', '2021-10-18 11:03:43', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (215, NULL, '101', '326', '2021-10-18 11:03:44', '2021-10-18 11:03:44', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (216, NULL, '102', '327', '2021-10-18 11:03:44', '2021-10-18 11:03:44', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (217, NULL, '101', '328', '2021-10-18 11:03:57', '2021-10-18 11:03:57', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (218, NULL, '102', '329', '2021-10-18 11:03:57', '2021-10-18 11:03:57', '1449934912454119426');
INSERT INTO `grg_file_manager` VALUES (219, NULL, '101', '330', '2021-10-18 11:07:57', '2021-10-18 11:07:57', '1449936059843571713');
INSERT INTO `grg_file_manager` VALUES (220, NULL, '102', '331', '2021-10-18 11:07:57', '2021-10-18 11:07:57', '1449936059843571713');
INSERT INTO `grg_file_manager` VALUES (221, NULL, '101', '332', '2021-10-18 11:13:24', '2021-10-18 11:13:24', '1449937558644707330');
INSERT INTO `grg_file_manager` VALUES (222, NULL, '102', '333', '2021-10-18 11:13:24', '2021-10-18 11:13:24', '1449937558644707330');
INSERT INTO `grg_file_manager` VALUES (223, NULL, '101', '334', '2021-10-18 11:13:43', '2021-10-18 11:13:43', '1449937558644707330');
INSERT INTO `grg_file_manager` VALUES (224, NULL, '102', '335', '2021-10-18 11:13:43', '2021-10-18 11:13:43', '1449937558644707330');
INSERT INTO `grg_file_manager` VALUES (225, NULL, '101', '336', '2021-10-18 11:19:00', '2021-10-18 11:19:00', '1449938931306188802');
INSERT INTO `grg_file_manager` VALUES (226, NULL, '102', '337', '2021-10-18 11:19:00', '2021-10-18 11:19:00', '1449938931306188802');
INSERT INTO `grg_file_manager` VALUES (227, NULL, '101', '338', '2021-10-18 11:27:55', '2021-10-18 11:27:55', '1449941182791643138');
INSERT INTO `grg_file_manager` VALUES (228, NULL, '102', '339', '2021-10-18 11:27:55', '2021-10-18 11:27:55', '1449941182791643138');
INSERT INTO `grg_file_manager` VALUES (229, '1234', '103', '342', '2021-10-18 15:26:35', '2021-10-18 15:26:35', '1018');
INSERT INTO `grg_file_manager` VALUES (230, NULL, '101', '343', '2021-10-19 09:12:26', '2021-10-19 09:12:26', '1450269406520160257');
INSERT INTO `grg_file_manager` VALUES (231, NULL, '102', '344', '2021-10-19 09:12:26', '2021-10-19 09:12:26', '1450269406520160257');
INSERT INTO `grg_file_manager` VALUES (232, NULL, '101', '345', '2021-10-19 10:00:30', '2021-10-19 10:00:30', '1450281495594926081');
INSERT INTO `grg_file_manager` VALUES (233, NULL, '102', '346', '2021-10-19 10:00:30', '2021-10-19 10:00:30', '1450281495594926081');
INSERT INTO `grg_file_manager` VALUES (234, NULL, '103', '347', '2021-10-19 10:03:34', '2021-10-19 10:03:34', '1018');
INSERT INTO `grg_file_manager` VALUES (235, NULL, '101', '348', '2021-10-19 10:06:42', '2021-10-19 10:06:42', '1450283163346665474');
INSERT INTO `grg_file_manager` VALUES (236, NULL, '102', '349', '2021-10-19 10:06:42', '2021-10-19 10:06:42', '1450283163346665474');
INSERT INTO `grg_file_manager` VALUES (237, NULL, '103', '350', '2021-10-19 10:07:05', '2021-10-19 10:07:05', '1018');
INSERT INTO `grg_file_manager` VALUES (238, NULL, '103', '351', '2021-10-19 10:09:53', '2021-10-19 10:09:53', '1018');
INSERT INTO `grg_file_manager` VALUES (239, NULL, '103', '352', '2021-10-19 10:12:43', '2021-10-19 10:12:43', '1018');
INSERT INTO `grg_file_manager` VALUES (240, NULL, '101', '353', '2021-10-19 10:41:38', '2021-10-19 10:41:38', '1450291927109980161');
INSERT INTO `grg_file_manager` VALUES (241, NULL, '102', '354', '2021-10-19 10:41:39', '2021-10-19 10:41:39', '1450291927109980161');
INSERT INTO `grg_file_manager` VALUES (242, '10001', '103', '355', '2021-10-19 10:48:38', '2021-10-19 10:48:38', '1018');
INSERT INTO `grg_file_manager` VALUES (243, NULL, '103', '357', '2021-10-19 10:58:19', '2021-10-19 10:58:19', '1019');
INSERT INTO `grg_file_manager` VALUES (244, NULL, '103', '358', '2021-10-19 11:00:01', '2021-10-19 11:00:01', '1019');
INSERT INTO `grg_file_manager` VALUES (245, NULL, '103', '359', '2021-10-19 13:27:22', '2021-10-19 13:27:22', '1019');
INSERT INTO `grg_file_manager` VALUES (246, NULL, '101', '360', '2021-10-19 13:27:22', '2021-10-19 13:27:22', '1450296496187625474');
INSERT INTO `grg_file_manager` VALUES (247, NULL, '101', '361', '2021-10-19 14:03:48', '2021-10-19 14:03:48', '4567890');
INSERT INTO `grg_file_manager` VALUES (248, NULL, '102', '362', '2021-10-19 14:03:49', '2021-10-19 14:03:49', '4567890');
INSERT INTO `grg_file_manager` VALUES (249, NULL, '101', '363', '2021-10-19 14:04:41', '2021-10-19 14:04:41', '4567890');
INSERT INTO `grg_file_manager` VALUES (250, NULL, '102', '364', '2021-10-19 14:04:41', '2021-10-19 14:04:41', '4567890');
INSERT INTO `grg_file_manager` VALUES (251, NULL, '101', '365', '2021-10-19 14:13:30', '2021-10-19 14:13:30', '4567890');
INSERT INTO `grg_file_manager` VALUES (252, NULL, '102', '366', '2021-10-19 14:13:30', '2021-10-19 14:13:30', '4567890');
INSERT INTO `grg_file_manager` VALUES (253, NULL, '101', '367', '2021-10-19 14:13:53', '2021-10-19 14:13:53', '4567890');
INSERT INTO `grg_file_manager` VALUES (254, NULL, '102', '368', '2021-10-19 14:13:53', '2021-10-19 14:13:53', '4567890');
INSERT INTO `grg_file_manager` VALUES (255, NULL, '101', '369', '2021-10-19 14:19:21', '2021-10-19 14:19:21', '1450346730217390082');
INSERT INTO `grg_file_manager` VALUES (256, NULL, '102', '370', '2021-10-19 14:19:21', '2021-10-19 14:19:21', '1450346730217390082');
INSERT INTO `grg_file_manager` VALUES (257, NULL, '101', '371', '2021-10-19 14:27:14', '2021-10-19 14:27:14', '1450348725741404161');
INSERT INTO `grg_file_manager` VALUES (258, NULL, '102', '372', '2021-10-19 14:27:15', '2021-10-19 14:27:15', '1450348725741404161');
INSERT INTO `grg_file_manager` VALUES (259, NULL, '101', '373', '2021-10-19 14:31:53', '2021-10-19 14:31:53', '1450349901618724866');
INSERT INTO `grg_file_manager` VALUES (260, NULL, '102', '374', '2021-10-19 14:31:53', '2021-10-19 14:31:53', '1450349901618724866');
INSERT INTO `grg_file_manager` VALUES (261, NULL, '101', '375', '2021-10-19 14:35:12', '2021-10-19 14:35:12', '1450350733156270082');
INSERT INTO `grg_file_manager` VALUES (262, NULL, '102', '376', '2021-10-19 14:35:13', '2021-10-19 14:35:13', '1450350733156270082');
INSERT INTO `grg_file_manager` VALUES (263, NULL, '101', '377', '2021-10-19 15:06:37', '2021-10-19 15:06:37', '1450358626865758210');
INSERT INTO `grg_file_manager` VALUES (264, NULL, '102', '378', '2021-10-19 15:06:37', '2021-10-19 15:06:37', '1450358626865758210');
INSERT INTO `grg_file_manager` VALUES (265, NULL, '101', '380', '2021-10-19 15:11:31', '2021-10-19 15:11:31', '1450359873131888641');
INSERT INTO `grg_file_manager` VALUES (266, NULL, '102', '381', '2021-10-19 15:11:31', '2021-10-19 15:11:31', '1450359873131888641');
INSERT INTO `grg_file_manager` VALUES (267, NULL, '101', '382', '2021-10-19 15:14:39', '2021-10-19 15:14:39', '1450360658339151874');
INSERT INTO `grg_file_manager` VALUES (268, NULL, '102', '383', '2021-10-19 15:14:40', '2021-10-19 15:14:40', '1450360658339151874');
INSERT INTO `grg_file_manager` VALUES (269, NULL, '101', '384', '2021-10-19 15:18:28', '2021-10-19 15:18:28', '1450361622764830722');
INSERT INTO `grg_file_manager` VALUES (270, NULL, '102', '385', '2021-10-19 15:18:28', '2021-10-19 15:18:28', '1450361622764830722');
INSERT INTO `grg_file_manager` VALUES (271, NULL, '101', '386', '2021-10-19 15:20:05', '2021-10-19 15:20:05', '1450362040374902785');
INSERT INTO `grg_file_manager` VALUES (272, NULL, '102', '387', '2021-10-19 15:20:05', '2021-10-19 15:20:05', '1450362040374902785');
INSERT INTO `grg_file_manager` VALUES (273, NULL, '101', '388', '2021-10-19 15:21:27', '2021-10-19 15:21:27', '1450362375294271490');
INSERT INTO `grg_file_manager` VALUES (274, NULL, '102', '389', '2021-10-19 15:21:27', '2021-10-19 15:21:27', '1450362375294271490');
INSERT INTO `grg_file_manager` VALUES (275, NULL, '101', '390', '2021-10-19 15:24:05', '2021-10-19 15:24:05', '1450363037331603457');
INSERT INTO `grg_file_manager` VALUES (276, NULL, '102', '391', '2021-10-19 15:24:05', '2021-10-19 15:24:05', '1450363037331603457');
INSERT INTO `grg_file_manager` VALUES (277, NULL, '101', '393', '2021-10-19 15:51:25', '2021-10-19 15:51:25', '1450369899724554241');
INSERT INTO `grg_file_manager` VALUES (278, NULL, '102', '394', '2021-10-19 15:51:25', '2021-10-19 15:51:25', '1450369899724554241');
INSERT INTO `grg_file_manager` VALUES (279, NULL, '101', '395', '2021-10-19 15:55:44', '2021-10-19 15:55:44', '1450370969393709058');
INSERT INTO `grg_file_manager` VALUES (280, NULL, '102', '396', '2021-10-19 15:55:44', '2021-10-19 15:55:44', '1450370969393709058');
INSERT INTO `grg_file_manager` VALUES (281, NULL, '101', '397', '2021-10-19 16:04:05', '2021-10-19 16:04:05', '1450373037131055106');
INSERT INTO `grg_file_manager` VALUES (282, NULL, '102', '398', '2021-10-19 16:04:05', '2021-10-19 16:04:05', '1450373037131055106');
INSERT INTO `grg_file_manager` VALUES (283, NULL, '102', '398', '2021-10-19 16:04:06', '2021-10-19 16:04:06', '1450373037131055106');
INSERT INTO `grg_file_manager` VALUES (284, NULL, '102', '400', '2021-10-19 16:04:08', '2021-10-19 16:04:08', '1450373037131055106');
INSERT INTO `grg_file_manager` VALUES (285, NULL, '101', '402', '2021-10-19 16:13:17', '2021-10-19 16:13:17', '1450375415829905409');
INSERT INTO `grg_file_manager` VALUES (286, NULL, '102', '403', '2021-10-19 16:13:17', '2021-10-19 16:13:17', '1450375415829905409');
INSERT INTO `grg_file_manager` VALUES (287, NULL, '101', '404', '2021-10-19 16:14:26', '2021-10-19 16:14:26', '1450375688681963522');
INSERT INTO `grg_file_manager` VALUES (288, NULL, '102', '405', '2021-10-19 16:14:27', '2021-10-19 16:14:27', '1450375688681963522');
INSERT INTO `grg_file_manager` VALUES (289, NULL, '101', '406', '2021-10-19 16:32:44', '2021-10-19 16:32:44', '1450380305964744705');
INSERT INTO `grg_file_manager` VALUES (290, NULL, '102', '407', '2021-10-19 16:32:44', '2021-10-19 16:32:44', '1450380305964744705');
INSERT INTO `grg_file_manager` VALUES (291, NULL, '101', '408', '2021-10-19 16:41:52', '2021-10-19 16:41:52', '1450382495949275137');
INSERT INTO `grg_file_manager` VALUES (292, NULL, '102', '409', '2021-10-19 16:41:52', '2021-10-19 16:41:52', '1450382495949275137');
INSERT INTO `grg_file_manager` VALUES (293, NULL, '101', '410', '2021-10-19 16:42:54', '2021-10-19 16:42:54', '1450382495949275137');
INSERT INTO `grg_file_manager` VALUES (294, NULL, '102', '411', '2021-10-19 16:42:54', '2021-10-19 16:42:54', '1450382495949275137');
INSERT INTO `grg_file_manager` VALUES (295, NULL, '101', '412', '2021-10-19 16:46:41', '2021-10-19 16:46:41', '4567890');
INSERT INTO `grg_file_manager` VALUES (296, NULL, '102', '413', '2021-10-19 16:46:41', '2021-10-19 16:46:41', '4567890');
INSERT INTO `grg_file_manager` VALUES (297, NULL, '101', '414', '2021-10-19 16:49:35', '2021-10-19 16:49:35', '1450384514810396674');
INSERT INTO `grg_file_manager` VALUES (298, NULL, '102', '415', '2021-10-19 16:49:35', '2021-10-19 16:49:35', '1450384514810396674');
INSERT INTO `grg_file_manager` VALUES (299, NULL, '101', '416', '2021-10-19 18:03:23', '2021-10-19 18:03:23', '1450403091399589889');
INSERT INTO `grg_file_manager` VALUES (300, NULL, '102', '417', '2021-10-19 18:03:24', '2021-10-19 18:03:24', '1450403091399589889');
INSERT INTO `grg_file_manager` VALUES (301, NULL, '101', '418', '2021-10-19 18:24:12', '2021-10-19 18:24:12', '1450408334904524801');
INSERT INTO `grg_file_manager` VALUES (302, NULL, '102', '419', '2021-10-19 18:24:12', '2021-10-19 18:24:12', '1450408334904524801');
INSERT INTO `grg_file_manager` VALUES (303, NULL, '101', '420', '2021-10-19 18:31:07', '2021-10-19 18:31:07', '1450410064329310210');
INSERT INTO `grg_file_manager` VALUES (304, NULL, '102', '421', '2021-10-19 18:31:07', '2021-10-19 18:31:07', '1450410064329310210');
INSERT INTO `grg_file_manager` VALUES (305, NULL, '101', '422', '2021-10-19 18:35:26', '2021-10-19 18:35:26', '1450411192290902018');
INSERT INTO `grg_file_manager` VALUES (306, NULL, '102', '423', '2021-10-19 18:35:26', '2021-10-19 18:35:26', '1450411192290902018');
INSERT INTO `grg_file_manager` VALUES (307, NULL, '101', '426', '2021-10-19 18:38:42', '2021-10-19 18:38:42', '1450412004085858306');
INSERT INTO `grg_file_manager` VALUES (308, NULL, '102', '427', '2021-10-19 18:38:42', '2021-10-19 18:38:42', '1450412004085858306');
INSERT INTO `grg_file_manager` VALUES (309, NULL, '101', '428', '2021-10-19 18:50:14', '2021-10-19 18:50:14', '1450414900487663618');
INSERT INTO `grg_file_manager` VALUES (310, NULL, '102', '429', '2021-10-19 18:50:15', '2021-10-19 18:50:15', '1450414900487663618');
INSERT INTO `grg_file_manager` VALUES (311, NULL, '101', '430', '2021-10-19 18:52:15', '2021-10-19 18:52:15', '1450414900487663618');
INSERT INTO `grg_file_manager` VALUES (312, NULL, '102', '431', '2021-10-19 18:52:15', '2021-10-19 18:52:15', '1450414900487663618');
INSERT INTO `grg_file_manager` VALUES (313, NULL, '101', '432', '2021-10-19 18:54:16', '2021-10-19 18:54:16', '1450415930642280450');
INSERT INTO `grg_file_manager` VALUES (314, NULL, '102', '433', '2021-10-19 18:54:17', '2021-10-19 18:54:17', '1450415930642280450');
INSERT INTO `grg_file_manager` VALUES (315, NULL, '101', '434', '2021-10-19 19:01:30', '2021-10-19 19:01:30', '1450417753830731777');
INSERT INTO `grg_file_manager` VALUES (316, NULL, '102', '435', '2021-10-19 19:01:30', '2021-10-19 19:01:30', '1450417753830731777');
INSERT INTO `grg_file_manager` VALUES (317, NULL, '101', '436', '2021-10-19 19:10:27', '2021-10-19 19:10:27', '1450419957757128705');
INSERT INTO `grg_file_manager` VALUES (318, NULL, '102', '437', '2021-10-19 19:10:28', '2021-10-19 19:10:28', '1450419957757128705');
INSERT INTO `grg_file_manager` VALUES (319, NULL, '101', '438', '2021-10-19 19:12:57', '2021-10-19 19:12:57', '1450420613683359745');
INSERT INTO `grg_file_manager` VALUES (320, NULL, '102', '439', '2021-10-19 19:12:57', '2021-10-19 19:12:57', '1450420613683359745');
INSERT INTO `grg_file_manager` VALUES (321, NULL, '101', '440', '2021-10-19 19:18:29', '2021-10-19 19:18:29', '1450422026245255170');
INSERT INTO `grg_file_manager` VALUES (322, NULL, '102', '441', '2021-10-19 19:18:30', '2021-10-19 19:18:30', '1450422026245255170');
INSERT INTO `grg_file_manager` VALUES (323, NULL, '101', '442', '2021-10-19 19:22:03', '2021-10-19 19:22:03', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (324, NULL, '102', '443', '2021-10-19 19:22:03', '2021-10-19 19:22:03', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (325, NULL, '101', '444', '2021-10-19 19:22:42', '2021-10-19 19:22:42', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (326, NULL, '102', '445', '2021-10-19 19:22:42', '2021-10-19 19:22:42', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (327, NULL, '101', '446', '2021-10-19 19:26:49', '2021-10-19 19:26:49', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (328, NULL, '102', '447', '2021-10-19 19:26:49', '2021-10-19 19:26:49', '1450422911507640321');
INSERT INTO `grg_file_manager` VALUES (329, NULL, '101', '448', '2021-10-19 19:33:10', '2021-10-19 19:33:10', '1450425717744480258');
INSERT INTO `grg_file_manager` VALUES (330, NULL, '102', '449', '2021-10-19 19:33:10', '2021-10-19 19:33:10', '1450425717744480258');
INSERT INTO `grg_file_manager` VALUES (331, NULL, '101', '451', '2021-10-19 19:36:47', '2021-10-19 19:36:47', '1450426610913128450');
INSERT INTO `grg_file_manager` VALUES (332, NULL, '102', '452', '2021-10-19 19:36:47', '2021-10-19 19:36:47', '1450426610913128450');
INSERT INTO `grg_file_manager` VALUES (333, '13711726710', '103', '453', '2021-10-19 19:37:00', '2021-10-19 19:37:00', '1450426610913128450');
INSERT INTO `grg_file_manager` VALUES (334, '13711726710', '103', '454', '2021-10-19 19:43:14', '2021-10-19 19:43:14', '1450428266790793217');
INSERT INTO `grg_file_manager` VALUES (335, '13711726710', '103', '455', '2021-10-19 19:43:59', '2021-10-19 19:43:59', '1450428482352852994');
INSERT INTO `grg_file_manager` VALUES (336, '13711726710', '103', '456', '2021-10-19 19:44:46', '2021-10-19 19:44:46', '1450428482352852994');
INSERT INTO `grg_file_manager` VALUES (337, NULL, '101', '457', '2021-10-19 19:47:23', '2021-10-19 19:47:23', '1450428482352852994');
INSERT INTO `grg_file_manager` VALUES (338, NULL, '102', '458', '2021-10-19 19:47:23', '2021-10-19 19:47:23', '1450428482352852994');
INSERT INTO `grg_file_manager` VALUES (339, NULL, '103', '459', '2021-10-19 19:49:50', '2021-10-19 19:49:50', '1450429948048191490');
INSERT INTO `grg_file_manager` VALUES (340, NULL, '101', '460', '2021-10-20 08:32:48', '2021-10-20 08:32:48', '1450621867080560641');
INSERT INTO `grg_file_manager` VALUES (341, NULL, '102', '461', '2021-10-20 08:32:48', '2021-10-20 08:32:48', '1450621867080560641');
INSERT INTO `grg_file_manager` VALUES (342, NULL, '103', '462', '2021-10-20 08:33:28', '2021-10-20 08:33:28', '1450621867080560641');
INSERT INTO `grg_file_manager` VALUES (343, NULL, '103', '463', '2021-10-20 08:34:25', '2021-10-20 08:34:25', '1450621867080560641');
INSERT INTO `grg_file_manager` VALUES (344, NULL, '101', '466', '2021-10-20 08:41:13', '2021-10-20 08:41:13', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (345, NULL, '102', '467', '2021-10-20 08:41:13', '2021-10-20 08:41:13', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (346, NULL, '101', '465', '2021-10-20 08:41:14', '2021-10-20 08:41:14', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (347, NULL, '101', '464', '2021-10-20 08:41:14', '2021-10-20 08:41:14', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (348, NULL, '101', '464', '2021-10-20 08:41:14', '2021-10-20 08:41:14', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (349, NULL, '101', '465', '2021-10-20 08:41:15', '2021-10-20 08:41:15', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (350, NULL, '101', '464', '2021-10-20 08:41:15', '2021-10-20 08:41:15', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (351, NULL, '103', '468', '2021-10-20 08:41:31', '2021-10-20 08:41:31', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (352, NULL, '103', '469', '2021-10-20 08:44:40', '2021-10-20 08:44:40', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (353, NULL, '101', '470', '2021-10-20 08:53:50', '2021-10-20 08:53:50', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (354, NULL, '102', '471', '2021-10-20 08:53:50', '2021-10-20 08:53:50', '1450623987733360642');
INSERT INTO `grg_file_manager` VALUES (355, NULL, '101', '472', '2021-10-20 08:57:39', '2021-10-20 08:57:39', '1450628162399629313');
INSERT INTO `grg_file_manager` VALUES (356, NULL, '102', '473', '2021-10-20 08:57:40', '2021-10-20 08:57:40', '1450628162399629313');
INSERT INTO `grg_file_manager` VALUES (357, NULL, '101', '474', '2021-10-20 09:02:37', '2021-10-20 09:02:37', '1450629384745525250');
INSERT INTO `grg_file_manager` VALUES (358, NULL, '102', '475', '2021-10-20 09:02:37', '2021-10-20 09:02:37', '1450629384745525250');
INSERT INTO `grg_file_manager` VALUES (359, NULL, '103', '476', '2021-10-20 09:30:38', '2021-10-20 09:30:38', '1450636401514127361');
INSERT INTO `grg_file_manager` VALUES (360, NULL, '103', '477', '2021-10-20 09:34:15', '2021-10-20 09:34:15', '1450637405957660673');
INSERT INTO `grg_file_manager` VALUES (361, NULL, '103', '478', '2021-10-20 09:34:45', '2021-10-20 09:34:45', '1450637405957660673');
INSERT INTO `grg_file_manager` VALUES (362, NULL, '103', '479', '2021-10-20 09:36:22', '2021-10-20 09:36:22', '1450637908380753921');
INSERT INTO `grg_file_manager` VALUES (363, NULL, '101', '480', '2021-10-20 09:44:31', '2021-10-20 09:44:31', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (364, NULL, '102', '481', '2021-10-20 09:44:31', '2021-10-20 09:44:31', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (365, NULL, '103', '482', '2021-10-20 09:44:54', '2021-10-20 09:44:54', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (366, NULL, '103', '483', '2021-10-20 09:44:54', '2021-10-20 09:44:54', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (367, NULL, '103', '484', '2021-10-20 09:47:20', '2021-10-20 09:47:20', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (368, NULL, '103', '485', '2021-10-20 09:50:02', '2021-10-20 09:50:02', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (369, NULL, '103', '486', '2021-10-20 09:53:27', '2021-10-20 09:53:27', '1450639910854729729');
INSERT INTO `grg_file_manager` VALUES (370, NULL, '101', '487', '2021-10-20 10:02:06', '2021-10-20 10:02:06', '1450644383899664386');
INSERT INTO `grg_file_manager` VALUES (371, NULL, '102', '488', '2021-10-20 10:02:07', '2021-10-20 10:02:07', '1450644383899664386');
INSERT INTO `grg_file_manager` VALUES (372, NULL, '103', '489', '2021-10-20 10:02:29', '2021-10-20 10:02:29', '1450644383899664386');
INSERT INTO `grg_file_manager` VALUES (373, NULL, '101', '490', '2021-10-20 10:02:33', '2021-10-20 10:02:33', '1450644346067042305');
INSERT INTO `grg_file_manager` VALUES (374, NULL, '102', '491', '2021-10-20 10:02:33', '2021-10-20 10:02:33', '1450644346067042305');
INSERT INTO `grg_file_manager` VALUES (375, NULL, '101', '492', '2021-10-20 10:03:40', '2021-10-20 10:03:40', '1450644790046703618');
INSERT INTO `grg_file_manager` VALUES (376, NULL, '102', '493', '2021-10-20 10:03:41', '2021-10-20 10:03:41', '1450644790046703618');
INSERT INTO `grg_file_manager` VALUES (377, NULL, '103', '494', '2021-10-20 10:07:32', '2021-10-20 10:07:32', '1450644383899664386');
INSERT INTO `grg_file_manager` VALUES (378, NULL, '103', '495', '2021-10-20 10:07:49', '2021-10-20 10:07:49', '1450644383899664386');
INSERT INTO `grg_file_manager` VALUES (379, NULL, '101', '496', '2021-10-20 10:16:33', '2021-10-20 10:16:33', '1450647992787877890');
INSERT INTO `grg_file_manager` VALUES (380, NULL, '102', '497', '2021-10-20 10:16:33', '2021-10-20 10:16:33', '1450647992787877890');
INSERT INTO `grg_file_manager` VALUES (381, NULL, '103', '498', '2021-10-20 10:20:59', '2021-10-20 10:20:59', '1450647744262782977');
INSERT INTO `grg_file_manager` VALUES (382, NULL, '101', '499', '2021-10-20 10:23:21', '2021-10-20 10:23:21', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (383, NULL, '101', '502', '2021-10-20 10:23:23', '2021-10-20 10:23:23', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (384, NULL, '101', '504', '2021-10-20 10:23:29', '2021-10-20 10:23:29', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (385, NULL, '101', '505', '2021-10-20 10:23:29', '2021-10-20 10:23:29', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (386, NULL, '102', '506', '2021-10-20 10:23:29', '2021-10-20 10:23:29', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (387, NULL, '103', '507', '2021-10-20 10:23:44', '2021-10-20 10:23:44', '1450649723366096897');
INSERT INTO `grg_file_manager` VALUES (388, NULL, '101', '508', '2021-10-20 10:48:10', '2021-10-20 10:48:10', '1450655961621544961');
INSERT INTO `grg_file_manager` VALUES (389, NULL, '102', '509', '2021-10-20 10:48:11', '2021-10-20 10:48:11', '1450655961621544961');
INSERT INTO `grg_file_manager` VALUES (390, NULL, '101', '510', '2021-10-20 10:55:43', '2021-10-20 10:55:43', '1450657904175693825');
INSERT INTO `grg_file_manager` VALUES (391, NULL, '102', '511', '2021-10-20 10:55:43', '2021-10-20 10:55:43', '1450657904175693825');
INSERT INTO `grg_file_manager` VALUES (392, NULL, '101', '512', '2021-10-20 11:42:39', '2021-10-20 11:42:39', '1450669673258434562');
INSERT INTO `grg_file_manager` VALUES (393, NULL, '102', '513', '2021-10-20 11:42:39', '2021-10-20 11:42:39', '1450669673258434562');
INSERT INTO `grg_file_manager` VALUES (394, NULL, '101', '514', '2021-10-20 11:46:59', '2021-10-20 11:46:59', '1450670788528062465');
INSERT INTO `grg_file_manager` VALUES (395, NULL, '102', '515', '2021-10-20 11:46:59', '2021-10-20 11:46:59', '1450670788528062465');
INSERT INTO `grg_file_manager` VALUES (396, NULL, '101', '516', '2021-10-20 11:49:19', '2021-10-20 11:49:19', '1450671355174338561');
INSERT INTO `grg_file_manager` VALUES (397, NULL, '102', '517', '2021-10-20 11:49:19', '2021-10-20 11:49:19', '1450671355174338561');
INSERT INTO `grg_file_manager` VALUES (398, NULL, '103', '518', '2021-10-20 11:49:51', '2021-10-20 11:49:51', '1450671355174338561');
INSERT INTO `grg_file_manager` VALUES (399, NULL, '101', '519', '2021-10-20 13:16:55', '2021-10-20 13:16:55', '1450693340063350785');
INSERT INTO `grg_file_manager` VALUES (400, NULL, '102', '520', '2021-10-20 13:16:56', '2021-10-20 13:16:56', '1450693340063350785');
INSERT INTO `grg_file_manager` VALUES (401, NULL, '103', '521', '2021-10-20 13:17:14', '2021-10-20 13:17:14', '1450693340063350785');
INSERT INTO `grg_file_manager` VALUES (402, NULL, '103', '522', '2021-10-20 13:21:08', '2021-10-20 13:21:08', '1450694467475488769');
INSERT INTO `grg_file_manager` VALUES (403, NULL, '103', '523', '2021-10-20 13:21:48', '2021-10-20 13:21:48', '1450694467475488769');
INSERT INTO `grg_file_manager` VALUES (404, NULL, '101', '524', '2021-10-20 13:31:52', '2021-10-20 13:31:52', '1450697141134573570');
INSERT INTO `grg_file_manager` VALUES (405, NULL, '102', '525', '2021-10-20 13:31:53', '2021-10-20 13:31:53', '1450697141134573570');
INSERT INTO `grg_file_manager` VALUES (406, NULL, '103', '527', '2021-10-20 13:32:06', '2021-10-20 13:32:06', '1450697141134573570');
INSERT INTO `grg_file_manager` VALUES (407, NULL, '103', '529', '2021-10-20 13:40:49', '2021-10-20 13:40:49', '1450697887389335554');
INSERT INTO `grg_file_manager` VALUES (408, NULL, '103', '531', '2021-10-20 14:02:18', '2021-10-20 14:02:18', '1450704860335849473');
INSERT INTO `grg_file_manager` VALUES (409, NULL, '101', '534', '2021-10-20 14:16:04', '2021-10-20 14:16:04', '1450708127753191425');
INSERT INTO `grg_file_manager` VALUES (410, NULL, '102', '535', '2021-10-20 14:16:04', '2021-10-20 14:16:04', '1450708127753191425');
INSERT INTO `grg_file_manager` VALUES (411, NULL, '103', '536', '2021-10-20 14:16:14', '2021-10-20 14:16:14', '1450708127753191425');
INSERT INTO `grg_file_manager` VALUES (412, NULL, '103', '537', '2021-10-20 14:16:42', '2021-10-20 14:16:42', '1450708127753191425');
INSERT INTO `grg_file_manager` VALUES (413, NULL, '103', '538', '2021-10-20 14:25:39', '2021-10-20 14:25:39', '1450710721993129985');
INSERT INTO `grg_file_manager` VALUES (414, NULL, '103', '539', '2021-10-20 14:25:40', '2021-10-20 14:25:40', '1450710721993129985');
INSERT INTO `grg_file_manager` VALUES (415, NULL, '103', '540', '2021-10-20 14:26:29', '2021-10-20 14:26:29', '1450710721993129985');
INSERT INTO `grg_file_manager` VALUES (416, NULL, '103', '541', '2021-10-20 14:29:09', '2021-10-20 14:29:09', '1450710721993129985');
INSERT INTO `grg_file_manager` VALUES (417, NULL, '103', '547', '2021-10-20 15:13:25', '2021-10-20 15:13:25', '1020');
INSERT INTO `grg_file_manager` VALUES (418, NULL, '103', '548', '2021-10-20 15:13:58', '2021-10-20 15:13:58', '1020');
INSERT INTO `grg_file_manager` VALUES (419, NULL, '103', '549', '2021-10-20 15:14:21', '2021-10-20 15:14:21', '1020');
INSERT INTO `grg_file_manager` VALUES (420, NULL, '103', '550', '2021-10-20 15:14:33', '2021-10-20 15:14:33', '1020');
INSERT INTO `grg_file_manager` VALUES (421, NULL, '103', '551', '2021-10-20 15:14:34', '2021-10-20 15:14:34', '1020');
INSERT INTO `grg_file_manager` VALUES (422, NULL, '103', '552', '2021-10-20 15:14:35', '2021-10-20 15:14:35', '1020');
INSERT INTO `grg_file_manager` VALUES (423, NULL, '101', '553', '2021-10-20 15:32:57', '2021-10-20 15:32:57', '1450727624081948673');
INSERT INTO `grg_file_manager` VALUES (424, NULL, '102', '554', '2021-10-20 15:32:57', '2021-10-20 15:32:57', '1450727624081948673');
INSERT INTO `grg_file_manager` VALUES (425, NULL, '103', '555', '2021-10-20 15:33:19', '2021-10-20 15:33:19', '1450727624081948673');
INSERT INTO `grg_file_manager` VALUES (426, NULL, '103', '556', '2021-10-20 15:35:14', '2021-10-20 15:35:14', '1450727624081948673');
INSERT INTO `grg_file_manager` VALUES (427, NULL, '103', '557', '2021-10-20 15:36:38', '2021-10-20 15:36:38', '1020');
INSERT INTO `grg_file_manager` VALUES (428, NULL, '101', '558', '2021-10-20 15:53:01', '2021-10-20 15:53:01', '1450732668328099842');
INSERT INTO `grg_file_manager` VALUES (429, NULL, '102', '559', '2021-10-20 15:53:01', '2021-10-20 15:53:01', '1450732668328099842');
INSERT INTO `grg_file_manager` VALUES (430, NULL, '103', '560', '2021-10-20 16:06:01', '2021-10-20 16:06:01', '1450735593142108162');
INSERT INTO `grg_file_manager` VALUES (431, NULL, '103', '561', '2021-10-20 16:13:06', '2021-10-20 16:13:06', '1020');
INSERT INTO `grg_file_manager` VALUES (432, NULL, '103', '562', '2021-10-20 16:13:58', '2021-10-20 16:13:58', '1450735593142108162');
INSERT INTO `grg_file_manager` VALUES (433, NULL, '103', '563', '2021-10-20 16:14:47', '2021-10-20 16:14:47', '1020');
INSERT INTO `grg_file_manager` VALUES (434, NULL, '103', '564', '2021-10-20 16:20:08', '2021-10-20 16:20:08', '1450739414815752193');
INSERT INTO `grg_file_manager` VALUES (435, NULL, '103', '565', '2021-10-20 16:22:14', '2021-10-20 16:22:14', '1020');
INSERT INTO `grg_file_manager` VALUES (436, NULL, '103', '566', '2021-10-20 16:24:15', '2021-10-20 16:24:15', '1450740504537882625');
INSERT INTO `grg_file_manager` VALUES (437, NULL, '103', '567', '2021-10-20 16:27:41', '2021-10-20 16:27:41', '1450741441063051266');
INSERT INTO `grg_file_manager` VALUES (438, NULL, '103', '568', '2021-10-20 16:28:49', '2021-10-20 16:28:49', '1450741441063051266');
INSERT INTO `grg_file_manager` VALUES (439, NULL, '103', '569', '2021-10-20 16:29:05', '2021-10-20 16:29:05', '1020');
INSERT INTO `grg_file_manager` VALUES (440, NULL, '103', '570', '2021-10-20 16:30:54', '2021-10-20 16:30:54', '1020');
INSERT INTO `grg_file_manager` VALUES (441, NULL, '103', '571', '2021-10-20 16:32:06', '2021-10-20 16:32:06', '1450741441063051266');
INSERT INTO `grg_file_manager` VALUES (442, NULL, '103', '572', '2021-10-20 16:32:33', '2021-10-20 16:32:33', '1450741441063051266');
INSERT INTO `grg_file_manager` VALUES (443, NULL, '103', '573', '2021-10-20 16:32:50', '2021-10-20 16:32:50', '1020');
INSERT INTO `grg_file_manager` VALUES (444, NULL, '103', '574', '2021-10-20 16:35:01', '2021-10-20 16:35:01', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (445, NULL, '103', '575', '2021-10-20 16:36:28', '2021-10-20 16:36:28', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (446, NULL, '103', '576', '2021-10-20 16:41:39', '2021-10-20 16:41:39', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (447, NULL, '103', '580', '2021-10-20 16:42:58', '2021-10-20 16:42:58', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (448, NULL, '103', '583', '2021-10-20 16:44:08', '2021-10-20 16:44:08', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (449, NULL, '103', '584', '2021-10-20 16:44:21', '2021-10-20 16:44:21', '1450743215895371777');
INSERT INTO `grg_file_manager` VALUES (450, NULL, '103', '585', '2021-10-20 16:49:25', '2021-10-20 16:49:25', '1450746830689165314');
INSERT INTO `grg_file_manager` VALUES (451, NULL, '103', '586', '2021-10-20 16:49:46', '2021-10-20 16:49:46', '1450746830689165314');
INSERT INTO `grg_file_manager` VALUES (452, NULL, '103', '587', '2021-10-20 16:52:03', '2021-10-20 16:52:03', '1450747528600379393');
INSERT INTO `grg_file_manager` VALUES (453, NULL, '103', '588', '2021-10-20 16:52:25', '2021-10-20 16:52:25', '1450747528600379393');
INSERT INTO `grg_file_manager` VALUES (454, NULL, '103', '589', '2021-10-20 16:53:16', '2021-10-20 16:53:16', '1450747841512235010');
INSERT INTO `grg_file_manager` VALUES (455, NULL, '103', '590', '2021-10-20 16:53:40', '2021-10-20 16:53:40', '1450747841512235010');
INSERT INTO `grg_file_manager` VALUES (456, NULL, '103', '591', '2021-10-20 16:55:23', '2021-10-20 16:55:23', '1450748402059993089');
INSERT INTO `grg_file_manager` VALUES (457, NULL, '103', '592', '2021-10-20 16:56:43', '2021-10-20 16:56:43', '1450748691739598849');
INSERT INTO `grg_file_manager` VALUES (458, NULL, '101', '593', '2021-10-21 08:36:57', '2021-10-21 08:36:57', '1450985316457406465');
INSERT INTO `grg_file_manager` VALUES (459, NULL, '102', '594', '2021-10-21 08:36:57', '2021-10-21 08:36:57', '1450985316457406465');
INSERT INTO `grg_file_manager` VALUES (460, NULL, '103', '595', '2021-10-21 08:37:14', '2021-10-21 08:37:14', '1450985316457406465');
INSERT INTO `grg_file_manager` VALUES (461, NULL, '101', '596', '2021-10-21 08:46:24', '2021-10-21 08:46:24', '1450987650075877377');
INSERT INTO `grg_file_manager` VALUES (462, NULL, '102', '597', '2021-10-21 08:46:24', '2021-10-21 08:46:24', '1450987650075877377');
INSERT INTO `grg_file_manager` VALUES (463, NULL, '101', '598', '2021-10-21 08:54:37', '2021-10-21 08:54:37', '1450989792392142849');
INSERT INTO `grg_file_manager` VALUES (464, NULL, '102', '599', '2021-10-21 08:54:38', '2021-10-21 08:54:38', '1450989792392142849');
INSERT INTO `grg_file_manager` VALUES (465, NULL, '101', '600', '2021-10-21 09:03:34', '2021-10-21 09:03:34', '1450991989414715393');
INSERT INTO `grg_file_manager` VALUES (466, NULL, '102', '601', '2021-10-21 09:03:35', '2021-10-21 09:03:35', '1450991989414715393');
INSERT INTO `grg_file_manager` VALUES (467, NULL, '103', '602', '2021-10-21 09:04:27', '2021-10-21 09:04:27', '1450991989414715393');
INSERT INTO `grg_file_manager` VALUES (468, NULL, '103', '603', '2021-10-21 09:08:45', '2021-10-21 09:08:45', '1450991989414715393');
INSERT INTO `grg_file_manager` VALUES (469, NULL, '103', '604', '2021-10-21 09:11:54', '2021-10-21 09:11:54', '1450991989414715393');
INSERT INTO `grg_file_manager` VALUES (470, NULL, '103', '605', '2021-10-21 09:30:50', '2021-10-21 09:30:50', '1450998912620978177');
INSERT INTO `grg_file_manager` VALUES (471, NULL, '103', '606', '2021-10-21 09:34:57', '2021-10-21 09:34:57', '1450998912620978177');
INSERT INTO `grg_file_manager` VALUES (472, NULL, '103', '607', '2021-10-21 09:44:41', '2021-10-21 09:44:41', '1450998912620978177');
INSERT INTO `grg_file_manager` VALUES (473, NULL, '101', '610', '2021-10-29 09:03:30', '2021-10-29 09:03:30', '1453890093312630786');
INSERT INTO `grg_file_manager` VALUES (474, NULL, '102', '611', '2021-10-29 09:03:31', '2021-10-29 09:03:31', '1453890093312630786');
INSERT INTO `grg_file_manager` VALUES (475, NULL, '101', '612', '2021-10-29 09:07:32', '2021-10-29 09:07:32', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (476, NULL, '102', '613', '2021-10-29 09:07:32', '2021-10-29 09:07:32', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (477, NULL, '101', '614', '2021-10-29 09:08:00', '2021-10-29 09:08:00', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (478, NULL, '102', '615', '2021-10-29 09:08:00', '2021-10-29 09:08:00', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (479, NULL, '101', '616', '2021-10-29 09:10:58', '2021-10-29 09:10:58', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (480, NULL, '102', '617', '2021-10-29 09:10:59', '2021-10-29 09:10:59', '1453890733438918658');
INSERT INTO `grg_file_manager` VALUES (481, NULL, '101', '618', '2021-10-29 09:59:56', '2021-10-29 09:59:56', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (482, NULL, '102', '619', '2021-10-29 09:59:56', '2021-10-29 09:59:56', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (483, NULL, '101', '620', '2021-10-29 10:00:45', '2021-10-29 10:00:45', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (484, NULL, '102', '621', '2021-10-29 10:00:45', '2021-10-29 10:00:45', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (485, NULL, '101', '622', '2021-10-29 10:02:29', '2021-10-29 10:02:29', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (486, NULL, '102', '623', '2021-10-29 10:02:29', '2021-10-29 10:02:29', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (487, NULL, '101', '624', '2021-10-29 10:04:32', '2021-10-29 10:04:32', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (488, NULL, '102', '625', '2021-10-29 10:04:32', '2021-10-29 10:04:32', '1453904308903579650');
INSERT INTO `grg_file_manager` VALUES (489, NULL, '101', '626', '2021-10-29 10:08:12', '2021-10-29 10:08:12', '1453906304247238658');
INSERT INTO `grg_file_manager` VALUES (490, NULL, '102', '627', '2021-10-29 10:08:12', '2021-10-29 10:08:12', '1453906304247238658');
INSERT INTO `grg_file_manager` VALUES (491, NULL, '101', '628', '2021-10-29 10:14:53', '2021-10-29 10:14:53', '1453908094179053570');
INSERT INTO `grg_file_manager` VALUES (492, NULL, '102', '629', '2021-10-29 10:14:53', '2021-10-29 10:14:53', '1453908094179053570');
INSERT INTO `grg_file_manager` VALUES (493, NULL, '101', '630', '2021-10-29 10:16:04', '2021-10-29 10:16:04', '1453908329156546561');
INSERT INTO `grg_file_manager` VALUES (494, NULL, '102', '631', '2021-10-29 10:16:04', '2021-10-29 10:16:04', '1453908329156546561');
INSERT INTO `grg_file_manager` VALUES (495, NULL, '103', '632', '2021-10-29 10:18:08', '2021-10-29 10:18:08', '1453908329156546561');
INSERT INTO `grg_file_manager` VALUES (496, NULL, '101', '633', '2021-10-29 11:20:30', '2021-10-29 11:20:30', '1453924554221785090');
INSERT INTO `grg_file_manager` VALUES (497, NULL, '102', '634', '2021-10-29 11:20:30', '2021-10-29 11:20:30', '1453924554221785090');
INSERT INTO `grg_file_manager` VALUES (498, NULL, '101', '635', '2021-10-29 11:28:29', '2021-10-29 11:28:29', '1453926598546423810');
INSERT INTO `grg_file_manager` VALUES (499, NULL, '102', '636', '2021-10-29 11:28:29', '2021-10-29 11:28:29', '1453926598546423810');
INSERT INTO `grg_file_manager` VALUES (500, NULL, '101', '637', '2021-10-29 11:33:50', '2021-10-29 11:33:50', '1453927897820168194');
INSERT INTO `grg_file_manager` VALUES (501, NULL, '102', '638', '2021-10-29 11:33:51', '2021-10-29 11:33:51', '1453927897820168194');
INSERT INTO `grg_file_manager` VALUES (502, NULL, '103', '639', '2021-10-29 13:32:28', '2021-10-29 13:32:28', '1453957717593554946');
INSERT INTO `grg_file_manager` VALUES (503, NULL, '103', '640', '2021-10-29 13:33:06', '2021-10-29 13:33:06', '1453957717593554946');
INSERT INTO `grg_file_manager` VALUES (504, NULL, '103', '641', '2021-10-29 13:45:02', '2021-10-29 13:45:02', '1453960701203316737');
INSERT INTO `grg_file_manager` VALUES (505, NULL, '101', '642', '2021-10-29 13:54:05', '2021-10-29 13:54:05', '1453962584965906434');
INSERT INTO `grg_file_manager` VALUES (506, NULL, '102', '643', '2021-10-29 13:54:05', '2021-10-29 13:54:05', '1453962584965906434');
INSERT INTO `grg_file_manager` VALUES (507, NULL, '101', '644', '2021-10-29 13:54:06', '2021-10-29 13:54:06', '1453962584965906434');
INSERT INTO `grg_file_manager` VALUES (508, NULL, '102', '645', '2021-10-29 13:54:06', '2021-10-29 13:54:06', '1453962584965906434');
INSERT INTO `grg_file_manager` VALUES (509, NULL, '101', '646', '2021-10-29 14:00:11', '2021-10-29 14:00:11', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (510, NULL, '102', '647', '2021-10-29 14:00:11', '2021-10-29 14:00:11', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (511, NULL, '103', '648', '2021-10-29 14:01:29', '2021-10-29 14:01:29', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (512, NULL, '103', '649', '2021-10-29 14:01:57', '2021-10-29 14:01:57', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (513, NULL, '103', '650', '2021-10-29 14:03:03', '2021-10-29 14:03:03', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (514, NULL, '103', '651', '2021-10-29 14:04:31', '2021-10-29 14:04:31', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (515, NULL, '101', '652', '2021-10-29 14:05:02', '2021-10-29 14:05:02', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (516, NULL, '102', '653', '2021-10-29 14:05:02', '2021-10-29 14:05:02', '1453964806021840897');
INSERT INTO `grg_file_manager` VALUES (517, NULL, '103', '654', '2021-10-29 14:58:03', '2021-10-29 14:58:03', '1453978603931435009');
INSERT INTO `grg_file_manager` VALUES (518, NULL, '103', '655', '2021-10-29 15:00:54', '2021-10-29 15:00:54', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (519, NULL, '103', '656', '2021-10-29 15:02:23', '2021-10-29 15:02:23', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (520, NULL, '103', '657', '2021-10-29 15:06:50', '2021-10-29 15:06:50', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (521, NULL, '103', '658', '2021-10-29 15:07:21', '2021-10-29 15:07:21', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (522, NULL, '103', '659', '2021-10-29 15:08:24', '2021-10-29 15:08:24', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (523, NULL, '103', '660', '2021-10-29 15:14:02', '2021-10-29 15:14:02', '1453979860456833025');
INSERT INTO `grg_file_manager` VALUES (524, NULL, '101', '661', '2021-10-29 15:22:42', '2021-10-29 15:22:42', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (525, NULL, '102', '662', '2021-10-29 15:22:42', '2021-10-29 15:22:42', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (526, NULL, '103', '663', '2021-10-29 15:23:04', '2021-10-29 15:23:04', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (527, NULL, '103', '664', '2021-10-29 15:23:46', '2021-10-29 15:23:46', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (528, NULL, '103', '665', '2021-10-29 15:24:30', '2021-10-29 15:24:30', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (529, NULL, '103', '666', '2021-10-29 15:25:21', '2021-10-29 15:25:21', '1453985504433930241');
INSERT INTO `grg_file_manager` VALUES (530, NULL, '101', '667', '2021-10-29 16:41:01', '2021-10-29 16:41:01', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (531, NULL, '102', '668', '2021-10-29 16:41:02', '2021-10-29 16:41:02', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (532, NULL, '103', '669', '2021-10-29 16:42:08', '2021-10-29 16:42:08', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (533, NULL, '103', '670', '2021-10-29 16:42:36', '2021-10-29 16:42:36', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (534, NULL, '101', '671', '2021-10-29 16:43:45', '2021-10-29 16:43:45', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (535, NULL, '102', '672', '2021-10-29 16:43:45', '2021-10-29 16:43:45', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (536, NULL, '103', '673', '2021-10-29 16:46:16', '2021-10-29 16:46:16', '1454005228031049729');
INSERT INTO `grg_file_manager` VALUES (537, NULL, '101', '674', '2021-11-02 15:32:07', '2021-11-02 15:32:07', '1455437415729233921');
INSERT INTO `grg_file_manager` VALUES (538, NULL, '102', '675', '2021-11-02 15:32:07', '2021-11-02 15:32:07', '1455437415729233921');
INSERT INTO `grg_file_manager` VALUES (539, NULL, '103', '676', '2021-11-02 15:35:07', '2021-11-02 15:35:07', '1455437415729233921');
INSERT INTO `grg_file_manager` VALUES (540, NULL, '103', '677', '2021-11-02 15:36:11', '2021-11-02 15:36:11', '1455437415729233921');
INSERT INTO `grg_file_manager` VALUES (541, NULL, '101', '678', '2021-11-02 15:39:32', '2021-11-02 15:39:32', '1455439283859652610');
INSERT INTO `grg_file_manager` VALUES (542, NULL, '102', '679', '2021-11-02 15:39:32', '2021-11-02 15:39:32', '1455439283859652610');
INSERT INTO `grg_file_manager` VALUES (543, NULL, '103', '680', '2021-11-02 15:40:54', '2021-11-02 15:40:54', '1455439283859652610');
INSERT INTO `grg_file_manager` VALUES (544, NULL, '103', '681', '2021-11-02 15:44:28', '2021-11-02 15:44:28', '1455439283859652610');
INSERT INTO `grg_file_manager` VALUES (545, NULL, '101', '688', '2021-11-02 16:12:39', '2021-11-02 16:12:39', '1455447673805680641');
INSERT INTO `grg_file_manager` VALUES (546, NULL, '102', '689', '2021-11-02 16:12:39', '2021-11-02 16:12:39', '1455447673805680641');
INSERT INTO `grg_file_manager` VALUES (547, NULL, '101', '690', '2021-11-02 16:15:11', '2021-11-02 16:15:11', '1455448238900064258');
INSERT INTO `grg_file_manager` VALUES (548, NULL, '102', '691', '2021-11-02 16:15:11', '2021-11-02 16:15:11', '1455448238900064258');
INSERT INTO `grg_file_manager` VALUES (549, NULL, '101', '692', '2021-11-02 16:20:36', '2021-11-02 16:20:36', '1455449626451324930');
INSERT INTO `grg_file_manager` VALUES (550, NULL, '102', '693', '2021-11-02 16:20:36', '2021-11-02 16:20:36', '1455449626451324930');
INSERT INTO `grg_file_manager` VALUES (551, NULL, '101', '696', '2021-11-03 15:10:03', '2021-11-03 15:10:03', '1455794303624962049');
INSERT INTO `grg_file_manager` VALUES (552, NULL, '102', '697', '2021-11-03 15:10:03', '2021-11-03 15:10:03', '1455794303624962049');
INSERT INTO `grg_file_manager` VALUES (553, NULL, '101', '698', '2021-11-03 15:16:02', '2021-11-03 15:16:02', '1455795807412969473');
INSERT INTO `grg_file_manager` VALUES (554, NULL, '102', '699', '2021-11-03 15:16:02', '2021-11-03 15:16:02', '1455795807412969473');
INSERT INTO `grg_file_manager` VALUES (555, NULL, '103', '700', '2021-11-03 15:16:19', '2021-11-03 15:16:19', '1455795807412969473');
INSERT INTO `grg_file_manager` VALUES (556, NULL, '101', '701', '2021-11-04 13:56:39', '2021-11-04 13:56:39', '1456138079291727874');
INSERT INTO `grg_file_manager` VALUES (557, NULL, '102', '702', '2021-11-04 13:56:39', '2021-11-04 13:56:39', '1456138079291727874');
INSERT INTO `grg_file_manager` VALUES (558, NULL, '101', '703', '2021-11-04 13:57:24', '2021-11-04 13:57:24', '1456138079291727874');
INSERT INTO `grg_file_manager` VALUES (559, NULL, '102', '704', '2021-11-04 13:57:24', '2021-11-04 13:57:24', '1456138079291727874');
INSERT INTO `grg_file_manager` VALUES (560, NULL, '103', '705', '2021-11-04 14:16:16', '2021-11-04 14:16:16', '1456143010195202049');
INSERT INTO `grg_file_manager` VALUES (561, NULL, '103', '706', '2021-11-04 14:17:15', '2021-11-04 14:17:15', '1456143010195202049');
INSERT INTO `grg_file_manager` VALUES (562, NULL, '103', '707', '2021-11-04 14:22:23', '2021-11-04 14:22:23', '1456143010195202049');
INSERT INTO `grg_file_manager` VALUES (563, NULL, '101', '708', '2021-11-04 14:23:10', '2021-11-04 14:23:10', '1456143010195202049');
INSERT INTO `grg_file_manager` VALUES (564, NULL, '102', '709', '2021-11-04 14:23:10', '2021-11-04 14:23:10', '1456143010195202049');
INSERT INTO `grg_file_manager` VALUES (565, NULL, '103', '710', '2021-11-04 14:52:02', '2021-11-04 14:52:02', '1456151958637928450');
INSERT INTO `grg_file_manager` VALUES (566, NULL, '103', '711', '2021-11-04 14:54:04', '2021-11-04 14:54:04', '1456151958637928450');
INSERT INTO `grg_file_manager` VALUES (567, NULL, '101', '712', '2021-11-04 14:55:35', '2021-11-04 14:55:35', '1456151958637928450');
INSERT INTO `grg_file_manager` VALUES (568, NULL, '102', '713', '2021-11-04 14:55:35', '2021-11-04 14:55:35', '1456151958637928450');
INSERT INTO `grg_file_manager` VALUES (569, NULL, '101', '714', '2021-11-04 15:10:12', '2021-11-04 15:10:12', '1456156675078250498');
INSERT INTO `grg_file_manager` VALUES (570, NULL, '102', '715', '2021-11-04 15:10:12', '2021-11-04 15:10:12', '1456156675078250498');
INSERT INTO `grg_file_manager` VALUES (571, NULL, '103', '716', '2021-11-04 15:11:05', '2021-11-04 15:11:05', '1456156675078250498');
INSERT INTO `grg_file_manager` VALUES (572, NULL, '103', '717', '2021-11-04 15:12:28', '2021-11-04 15:12:28', '1456156675078250498');
INSERT INTO `grg_file_manager` VALUES (573, NULL, '103', '718', '2021-11-04 15:25:58', '2021-11-04 15:25:58', '1456160535326908418');
INSERT INTO `grg_file_manager` VALUES (574, NULL, '103', '719', '2021-11-04 15:38:47', '2021-11-04 15:38:47', '1456160535326908418');
INSERT INTO `grg_file_manager` VALUES (575, NULL, '103', '720', '2021-11-04 16:33:54', '2021-11-04 16:33:54', '1456177643733540865');
INSERT INTO `grg_file_manager` VALUES (576, NULL, '103', '721', '2021-11-04 16:48:16', '2021-11-04 16:48:16', '1456180071358947329');
INSERT INTO `grg_file_manager` VALUES (577, NULL, '101', '722', '2021-11-04 16:53:33', '2021-11-04 16:53:33', '1456182718681997314');
INSERT INTO `grg_file_manager` VALUES (578, NULL, '102', '723', '2021-11-04 16:53:33', '2021-11-04 16:53:33', '1456182718681997314');
INSERT INTO `grg_file_manager` VALUES (579, NULL, '103', '724', '2021-11-04 16:58:02', '2021-11-04 16:58:02', '1456182718681997314');
INSERT INTO `grg_file_manager` VALUES (580, NULL, '101', '725', '2021-11-05 09:07:24', '2021-11-05 09:07:24', '1456427747124076546');
INSERT INTO `grg_file_manager` VALUES (581, NULL, '102', '726', '2021-11-05 09:07:24', '2021-11-05 09:07:24', '1456427747124076546');
INSERT INTO `grg_file_manager` VALUES (582, NULL, '103', '727', '2021-11-05 09:09:32', '2021-11-05 09:09:32', '1456427747124076546');
INSERT INTO `grg_file_manager` VALUES (583, NULL, '101', '728', '2021-11-05 09:13:08', '2021-11-05 09:13:08', '1456429213012353026');
INSERT INTO `grg_file_manager` VALUES (584, NULL, '102', '729', '2021-11-05 09:13:09', '2021-11-05 09:13:09', '1456429213012353026');
INSERT INTO `grg_file_manager` VALUES (585, NULL, '103', '730', '2021-11-05 09:13:35', '2021-11-05 09:13:35', '1456429213012353026');
INSERT INTO `grg_file_manager` VALUES (586, NULL, '101', '731', '2021-11-05 09:27:26', '2021-11-05 09:27:26', '1456432741462204417');
INSERT INTO `grg_file_manager` VALUES (587, NULL, '102', '732', '2021-11-05 09:27:26', '2021-11-05 09:27:26', '1456432741462204417');
INSERT INTO `grg_file_manager` VALUES (588, NULL, '103', '733', '2021-11-05 09:28:06', '2021-11-05 09:28:06', '1456432741462204417');
INSERT INTO `grg_file_manager` VALUES (589, NULL, '101', '734', '2021-11-05 10:05:55', '2021-11-05 10:05:55', '1456442455579320321');
INSERT INTO `grg_file_manager` VALUES (590, NULL, '102', '735', '2021-11-05 10:05:55', '2021-11-05 10:05:55', '1456442455579320321');
INSERT INTO `grg_file_manager` VALUES (591, NULL, '103', '736', '2021-11-05 10:06:23', '2021-11-05 10:06:23', '1456442455579320321');
INSERT INTO `grg_file_manager` VALUES (592, NULL, '103', '737', '2021-11-05 10:12:22', '2021-11-05 10:12:22', '1456442455579320321');
INSERT INTO `grg_file_manager` VALUES (593, NULL, '101', '738', '2021-11-05 10:16:09', '2021-11-05 10:16:09', '1456445018089349122');
INSERT INTO `grg_file_manager` VALUES (594, NULL, '102', '739', '2021-11-05 10:16:09', '2021-11-05 10:16:09', '1456445018089349122');
INSERT INTO `grg_file_manager` VALUES (595, NULL, '101', '740', '2021-11-05 10:23:10', '2021-11-05 10:23:10', '1456446703570411522');
INSERT INTO `grg_file_manager` VALUES (596, NULL, '102', '741', '2021-11-05 10:23:10', '2021-11-05 10:23:10', '1456446703570411522');
INSERT INTO `grg_file_manager` VALUES (597, NULL, '101', '742', '2021-11-05 11:03:21', '2021-11-05 11:03:21', '1456456859372904450');
INSERT INTO `grg_file_manager` VALUES (598, NULL, '102', '743', '2021-11-05 11:03:21', '2021-11-05 11:03:21', '1456456859372904450');
INSERT INTO `grg_file_manager` VALUES (599, NULL, '101', '744', '2021-11-05 11:09:25', '2021-11-05 11:09:25', '1456458404487389186');
INSERT INTO `grg_file_manager` VALUES (600, NULL, '102', '745', '2021-11-05 11:09:25', '2021-11-05 11:09:25', '1456458404487389186');
INSERT INTO `grg_file_manager` VALUES (601, NULL, '101', '746', '2021-11-05 11:32:47', '2021-11-05 11:32:47', '1456464361778995202');
INSERT INTO `grg_file_manager` VALUES (602, NULL, '102', '747', '2021-11-05 11:32:47', '2021-11-05 11:32:47', '1456464361778995202');
INSERT INTO `grg_file_manager` VALUES (603, NULL, '101', '748', '2021-11-05 11:44:31', '2021-11-05 11:44:31', '1456467281585004546');
INSERT INTO `grg_file_manager` VALUES (604, NULL, '102', '749', '2021-11-05 11:44:31', '2021-11-05 11:44:31', '1456467281585004546');
INSERT INTO `grg_file_manager` VALUES (605, NULL, '101', '750', '2021-11-05 13:36:28', '2021-11-05 13:36:28', '1456495489277673473');
INSERT INTO `grg_file_manager` VALUES (606, NULL, '102', '751', '2021-11-05 13:36:28', '2021-11-05 13:36:28', '1456495489277673473');
INSERT INTO `grg_file_manager` VALUES (607, NULL, '101', '752', '2021-11-05 13:43:31', '2021-11-05 13:43:31', '1456496855144382466');
INSERT INTO `grg_file_manager` VALUES (608, NULL, '102', '753', '2021-11-05 13:43:31', '2021-11-05 13:43:31', '1456496855144382466');
INSERT INTO `grg_file_manager` VALUES (609, NULL, '101', '754', '2021-11-05 14:26:51', '2021-11-05 14:26:51', '1456508075360567297');
INSERT INTO `grg_file_manager` VALUES (610, NULL, '102', '755', '2021-11-05 14:26:51', '2021-11-05 14:26:51', '1456508075360567297');
INSERT INTO `grg_file_manager` VALUES (611, NULL, '103', '756', '2021-11-05 14:27:20', '2021-11-05 14:27:20', '1456508075360567297');
INSERT INTO `grg_file_manager` VALUES (612, NULL, '103', '757', '2021-11-05 14:27:46', '2021-11-05 14:27:46', '1456508075360567297');
INSERT INTO `grg_file_manager` VALUES (613, NULL, '103', '758', '2021-11-05 14:28:04', '2021-11-05 14:28:04', '1456508075360567297');
INSERT INTO `grg_file_manager` VALUES (614, NULL, '103', '759', '2021-11-05 14:46:50', '2021-11-05 14:46:50', '1456513000287158273');
INSERT INTO `grg_file_manager` VALUES (615, NULL, '103', '760', '2021-11-05 14:47:20', '2021-11-05 14:47:20', '1456513000287158273');
INSERT INTO `grg_file_manager` VALUES (616, NULL, '101', '761', '2021-11-05 15:29:27', '2021-11-05 15:29:27', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (617, NULL, '102', '762', '2021-11-05 15:29:27', '2021-11-05 15:29:27', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (618, NULL, '103', '763', '2021-11-05 15:30:03', '2021-11-05 15:30:03', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (619, NULL, '103', '764', '2021-11-05 15:30:53', '2021-11-05 15:30:53', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (620, NULL, '103', '765', '2021-11-05 15:31:36', '2021-11-05 15:31:36', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (621, NULL, '103', '766', '2021-11-05 15:32:41', '2021-11-05 15:32:41', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (622, NULL, '103', '767', '2021-11-05 15:33:16', '2021-11-05 15:33:16', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (623, NULL, '103', '768', '2021-11-05 15:33:34', '2021-11-05 15:33:34', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (624, NULL, '103', '769', '2021-11-05 15:34:18', '2021-11-05 15:34:18', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (625, NULL, '103', '770', '2021-11-05 15:39:51', '2021-11-05 15:39:51', '1456523789614104578');
INSERT INTO `grg_file_manager` VALUES (626, NULL, '101', '771', '2021-11-05 15:50:04', '2021-11-05 15:50:04', '1456529096616624129');
INSERT INTO `grg_file_manager` VALUES (627, NULL, '102', '772', '2021-11-05 15:50:04', '2021-11-05 15:50:04', '1456529096616624129');
INSERT INTO `grg_file_manager` VALUES (628, NULL, '101', '773', '2021-11-05 15:58:02', '2021-11-05 15:58:02', '1456531135484575746');
INSERT INTO `grg_file_manager` VALUES (629, NULL, '102', '774', '2021-11-05 15:58:02', '2021-11-05 15:58:02', '1456531135484575746');
INSERT INTO `grg_file_manager` VALUES (630, NULL, '101', '775', '2021-11-05 16:32:16', '2021-11-05 16:32:16', '1456539657358196737');
INSERT INTO `grg_file_manager` VALUES (631, NULL, '102', '776', '2021-11-05 16:32:16', '2021-11-05 16:32:16', '1456539657358196737');
INSERT INTO `grg_file_manager` VALUES (632, NULL, '103', '777', '2021-11-05 16:34:04', '2021-11-05 16:34:04', '1456539657358196737');
INSERT INTO `grg_file_manager` VALUES (633, NULL, '101', '778', '2021-11-08 15:51:55', '2021-11-08 15:51:55', '1457616601319391234');
INSERT INTO `grg_file_manager` VALUES (634, NULL, '102', '779', '2021-11-08 15:51:55', '2021-11-08 15:51:55', '1457616601319391234');
INSERT INTO `grg_file_manager` VALUES (635, NULL, '103', '780', '2021-11-08 15:55:11', '2021-11-08 15:55:11', '1457616601319391234');
INSERT INTO `grg_file_manager` VALUES (636, NULL, '103', '781', '2021-11-08 15:55:51', '2021-11-08 15:55:51', '1457616601319391234');
INSERT INTO `grg_file_manager` VALUES (637, NULL, '103', '782', '2021-11-09 11:56:15', '2021-11-09 11:56:15', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (638, NULL, '103', '783', '2021-11-09 11:56:57', '2021-11-09 11:56:57', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (639, NULL, '101', '784', '2021-11-09 11:58:12', '2021-11-09 11:58:12', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (640, NULL, '102', '785', '2021-11-09 11:58:12', '2021-11-09 11:58:12', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (641, NULL, '101', '786', '2021-11-09 11:58:46', '2021-11-09 11:58:46', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (642, NULL, '102', '787', '2021-11-09 11:58:46', '2021-11-09 11:58:46', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (643, NULL, '101', '788', '2021-11-09 11:59:54', '2021-11-09 11:59:54', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (644, NULL, '102', '789', '2021-11-09 11:59:54', '2021-11-09 11:59:54', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (645, NULL, '101', '790', '2021-11-09 12:01:08', '2021-11-09 12:01:08', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (646, NULL, '102', '791', '2021-11-09 12:01:08', '2021-11-09 12:01:08', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (647, NULL, '101', '792', '2021-11-09 12:01:17', '2021-11-09 12:01:17', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (648, NULL, '102', '793', '2021-11-09 12:01:17', '2021-11-09 12:01:17', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (649, NULL, '101', '794', '2021-11-09 12:02:10', '2021-11-09 12:02:10', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (650, NULL, '102', '795', '2021-11-09 12:02:10', '2021-11-09 12:02:10', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (651, NULL, '101', '796', '2021-11-09 12:02:24', '2021-11-09 12:02:24', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (652, NULL, '102', '797', '2021-11-09 12:02:24', '2021-11-09 12:02:24', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (653, NULL, '101', '798', '2021-11-09 12:02:38', '2021-11-09 12:02:38', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (654, NULL, '102', '799', '2021-11-09 12:02:38', '2021-11-09 12:02:38', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (655, NULL, '103', '800', '2021-11-09 12:05:32', '2021-11-09 12:05:32', '1457919680858210305');
INSERT INTO `grg_file_manager` VALUES (656, NULL, '103', '801', '2021-11-09 13:36:15', '2021-11-09 13:36:15', '1457944304211902466');

SET FOREIGN_KEY_CHECKS = 1;
