/*
 Navicat Premium Data Transfer

 Source Server         : 39
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 10.252.21.39:3306
 Source Schema         : grg-cloud-counter-iam

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 09/11/2021 16:01:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
                                         `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                         `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `access_token_validity` int(0) NULL DEFAULT NULL,
                                         `refresh_token_validity` int(0) NULL DEFAULT NULL,
                                         `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `enabled` int(0) NULL DEFAULT 1,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES (1, 'web-client', NULL, '$2a$10$uV/VUTBZgPyHX16GSP21pO.ArPPohUXrtXgDS17EXyOL3CAl7W1q6', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 43200, 2592000, NULL, 'true', 1);
INSERT INTO `oauth_client_details` VALUES (2, 'sms', NULL, '$2a$10$uV/VUTBZgPyHX16GSP21pO.ArPPohUXrtXgDS17EXyOL3CAl7W1q6', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 259200, 2592000, NULL, 'true', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `dept_id` bigint(0) NOT NULL AUTO_INCREMENT,
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                             `enabled` int(0) NULL DEFAULT 1,
                             `parent_id` bigint(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '广州', 1, '2018-01-22 19:00:23', '2021-09-22 08:30:12', 1, 0);

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
                                      `ancestor` bigint(0) NOT NULL COMMENT '祖先节点',
                                      `descendant` bigint(0) NOT NULL COMMENT '后代节点',
                                      PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
                                      INDEX `idx1`(`ancestor`) USING BTREE,
                                      INDEX `idx2`(`descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (2, 12);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (7, 12);
INSERT INTO `sys_dept_relation` VALUES (8, 8);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
                             `dict_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `system` int(0) NULL DEFAULT 0,
                             `enabled` int(0) NULL DEFAULT 1,
                             PRIMARY KEY (`dict_id`) USING BTREE,
                             INDEX `sys_dict_del_flag`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'log_type', '日志类型', '2019-03-19 11:06:44', '2021-08-31 15:03:52', '异常、正常', 1, 1);
INSERT INTO `sys_dict` VALUES (2, 'social_type', '社交登录', '2019-03-19 11:09:44', '2021-08-31 15:03:52', '微信、QQ', 1, 1);
INSERT INTO `sys_dict` VALUES (3, 'leave_status', '请假状态', '2019-03-19 11:09:44', '2021-08-31 15:03:52', '未提交、审批中、完成、驳回', 1, 1);
INSERT INTO `sys_dict` VALUES (4, 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2021-08-31 15:03:52', 'quartz', 1, 1);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2021-08-31 15:03:52', '发布状态、运行状态', 1, 1);
INSERT INTO `sys_dict` VALUES (6, 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2021-08-31 15:03:52', '正常、异常', 1, 1);
INSERT INTO `sys_dict` VALUES (7, 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2021-08-31 15:03:52', '周期', 1, 1);
INSERT INTO `sys_dict` VALUES (8, 'gender', '性别', '2019-03-27 13:44:06', '2021-08-31 15:03:52', '微信用户性别', 1, 1);
INSERT INTO `sys_dict` VALUES (9, 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2021-08-31 15:03:52', '公众号订阅状态', 1, 1);
INSERT INTO `sys_dict` VALUES (10, 'response_type', '回复', '2019-03-28 21:29:21', '2021-08-31 15:03:52', '微信消息是否已回复', 1, 1);
INSERT INTO `sys_dict` VALUES (11, 'param_type', '参数配置', '2019-04-29 18:20:47', '2021-08-31 15:03:52', '检索、原文、报表、安全、文档、消息、其他', 1, 1);
INSERT INTO `sys_dict` VALUES (12, 'status_type', '租户状态', '2019-05-15 16:31:08', '2021-08-31 15:03:52', '租户状态', 1, 1);
INSERT INTO `sys_dict` VALUES (13, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2021-08-31 15:03:52', '系统类不能修改', 1, 1);
INSERT INTO `sys_dict` VALUES (14, 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2021-08-31 15:03:52', '支付渠道状态（0-正常，1-冻结）', 1, 1);
INSERT INTO `sys_dict` VALUES (15, 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2021-08-31 15:03:52', '不同的支付方式', 1, 1);
INSERT INTO `sys_dict` VALUES (16, 'order_status', '订单状态', '2019-06-27 08:17:40', '2021-08-31 15:03:52', '支付订单状态', 1, 1);
INSERT INTO `sys_dict` VALUES (17, 'grant_types', '授权类型', '2019-08-13 07:34:10', '2021-08-31 15:03:52', NULL, 1, 1);
INSERT INTO `sys_dict` VALUES (18, 'style_type', '前端风格', '2020-02-07 03:49:28', '2021-08-31 15:03:52', '0-Avue 1-element', 1, 1);
INSERT INTO `sys_dict` VALUES (19, 'captcha_flag_types', '验证码开关', '2020-11-18 06:53:25', '2021-08-31 15:03:52', '是否校验验证码', 1, 1);
INSERT INTO `sys_dict` VALUES (20, 'enc_flag_types', '前端密码加密', '2020-11-18 06:54:44', '2021-08-31 15:03:52', '前端密码是否加密传输', 1, 1);
INSERT INTO `sys_dict` VALUES (21, 'busi_type', '业务类别', '2021-09-08 13:11:15', '2021-09-08 13:11:15', '对公、对私', 0, 1);
INSERT INTO `sys_dict` VALUES (22, 'busi_status', '业务状态', '2021-09-08 13:11:40', '2021-09-08 13:11:40', '启动、停用', 0, 1);
INSERT INTO `sys_dict` VALUES (23, 'busi_opt_status', '业务办理状态', '2021-09-08 13:23:34', '2021-09-08 13:23:34', '完毕、未完成、注销', 0, 1);
INSERT INTO `sys_dict` VALUES (24, 'file_busi_type', '文件业务类型', '2021-09-08 13:58:01', '2021-10-18 10:25:04', '附件业务类型', 0, 1);
INSERT INTO `sys_dict` VALUES (25, 'busi_employee_status', '业务人员状态', '2021-09-22 16:24:21', '2021-09-22 16:25:02', NULL, 0, 1);
INSERT INTO `sys_dict` VALUES (26, 'account_status', '账户状态', '2021-09-27 09:18:25', '2021-09-27 09:19:07', '0:已激活;1:未激活; 2:已挂失', 0, 1);
INSERT INTO `sys_dict` VALUES (27, 'card_type', '卡类型', '2021-09-27 09:34:42', '2021-09-29 15:38:36', '1：借记卡  2：信用卡', 0, 1);
INSERT INTO `sys_dict` VALUES (28, 'file_type', '文件类型', '2021-09-27 15:53:44', '2021-09-27 15:53:56', NULL, 0, 1);
INSERT INTO `sys_dict` VALUES (29, 'business_type', '业务类型', '2021-09-29 15:25:32', '2021-09-30 14:50:07', '1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级', 0, 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
                                  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                  `dict_id` bigint(0) NOT NULL,
                                  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `enabled` int(0) NULL DEFAULT 1,
                                  PRIMARY KEY (`id`) USING BTREE,
                                  INDEX `sys_dict_value`(`value`) USING BTREE,
                                  INDEX `sys_dict_label`(`label`) USING BTREE,
                                  INDEX `sys_dict_del_flag`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (2, 1, '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (3, 2, 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (4, 2, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (5, 3, '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (6, 3, '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (7, 3, '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (8, 3, '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (9, 4, '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (10, 4, '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (11, 4, '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (12, 4, '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (13, 4, '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (14, 5, '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (15, 5, '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (16, 5, '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (17, 6, '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (18, 6, '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (19, 7, '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (20, 7, '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (21, 7, '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (22, 8, '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2021-08-31 15:03:42', '微信-男', 1);
INSERT INTO `sys_dict_item` VALUES (23, 8, '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2021-08-31 15:03:42', '女-微信', 1);
INSERT INTO `sys_dict_item` VALUES (24, 8, '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2021-08-31 15:03:42', 'x性别未知', 1);
INSERT INTO `sys_dict_item` VALUES (25, 9, '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2021-08-31 15:03:42', '公众号-未关注', 1);
INSERT INTO `sys_dict_item` VALUES (26, 9, '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2021-08-31 15:03:42', '公众号-已关注', 1);
INSERT INTO `sys_dict_item` VALUES (27, 10, '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2021-08-31 15:03:42', '微信消息-未回复', 1);
INSERT INTO `sys_dict_item` VALUES (28, 10, '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2021-08-31 15:03:42', '微信消息-已回复', 1);
INSERT INTO `sys_dict_item` VALUES (29, 11, '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2021-08-31 15:03:42', '检索', 1);
INSERT INTO `sys_dict_item` VALUES (30, 11, '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2021-08-31 15:03:42', '原文', 1);
INSERT INTO `sys_dict_item` VALUES (31, 11, '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2021-08-31 15:03:42', '报表', 1);
INSERT INTO `sys_dict_item` VALUES (32, 11, '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2021-08-31 15:03:42', '安全', 1);
INSERT INTO `sys_dict_item` VALUES (33, 11, '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2021-08-31 15:03:42', '文档', 1);
INSERT INTO `sys_dict_item` VALUES (34, 11, '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2021-08-31 15:03:42', '消息', 1);
INSERT INTO `sys_dict_item` VALUES (35, 11, '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2021-08-31 15:03:42', '其他', 1);
INSERT INTO `sys_dict_item` VALUES (36, 11, '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2021-08-31 15:03:42', '默认', 1);
INSERT INTO `sys_dict_item` VALUES (37, 12, '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2021-08-31 15:03:42', '状态正常', 1);
INSERT INTO `sys_dict_item` VALUES (38, 12, '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2021-08-31 15:03:42', '状态冻结', 1);
INSERT INTO `sys_dict_item` VALUES (39, 13, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2021-08-31 15:03:42', '不能修改删除', 1);
INSERT INTO `sys_dict_item` VALUES (40, 13, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2021-08-31 15:03:42', '可以修改', 1);
INSERT INTO `sys_dict_item` VALUES (41, 14, '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (42, 14, '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (43, 15, 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2021-08-31 15:03:42', '支付宝wap支付', 1);
INSERT INTO `sys_dict_item` VALUES (44, 15, 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2021-08-31 15:03:42', '微信公众号支付', 1);
INSERT INTO `sys_dict_item` VALUES (45, 16, '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2021-08-31 15:03:42', '订单支付成功', 1);
INSERT INTO `sys_dict_item` VALUES (46, 16, '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2021-08-31 15:03:42', '订单支付完成', 1);
INSERT INTO `sys_dict_item` VALUES (47, 16, '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2021-08-31 15:03:42', '订单待支付', 1);
INSERT INTO `sys_dict_item` VALUES (48, 16, '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2021-08-31 15:03:42', '订单支付失败', 1);
INSERT INTO `sys_dict_item` VALUES (49, 2, 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2021-08-31 15:03:42', '码云', 1);
INSERT INTO `sys_dict_item` VALUES (50, 2, 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2021-08-31 15:03:42', 'http://gitee.huaxiadaowei.com/#/authredirect', 1);
INSERT INTO `sys_dict_item` VALUES (51, 17, 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (52, 17, 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (53, 17, 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (54, 17, 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (55, 17, 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2021-08-31 15:03:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (56, 18, '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (57, 18, '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2021-08-31 15:03:42', '', 1);
INSERT INTO `sys_dict_item` VALUES (58, 19, '0', '关', 'captcha_flag_types', '不校验验证码', 0, '2020-11-18 06:53:58', '2021-08-31 15:03:42', '不校验验证码 -0', 1);
INSERT INTO `sys_dict_item` VALUES (59, 19, '1', '开', 'captcha_flag_types', '校验验证码', 1, '2020-11-18 06:54:15', '2021-08-31 15:03:42', '不校验验证码-1', 1);
INSERT INTO `sys_dict_item` VALUES (60, 20, '0', '否', 'enc_flag_types', '不加密', 0, '2020-11-18 06:55:31', '2021-08-31 15:03:42', '不加密-0', 1);
INSERT INTO `sys_dict_item` VALUES (61, 20, '1', '是', 'enc_flag_types', '加密', 1, '2020-11-18 06:55:51', '2021-08-31 15:03:42', '加密-1', 1);
INSERT INTO `sys_dict_item` VALUES (62, 21, '1', '对公', 'busi_type', NULL, 0, '2021-09-08 13:12:25', '2021-09-08 13:13:00', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (63, 21, '2', '对私', 'busi_type', NULL, 0, '2021-09-08 13:13:51', '2021-09-08 13:14:14', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (64, 22, '0', '初始化', 'busi_status', NULL, 0, '2021-09-08 13:15:34', '2021-09-08 13:15:34', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (65, 22, '1', '启用', 'busi_status', NULL, 0, '2021-09-08 13:16:28', '2021-09-08 13:16:49', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (66, 22, '2', '停用', 'busi_status', NULL, 0, '2021-09-08 13:16:39', '2021-09-08 13:16:52', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (67, 23, '1', '完成', 'busi_opt_status', NULL, 0, '2021-09-08 13:23:55', '2021-09-08 13:25:20', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (68, 23, '0', '初始化', 'busi_opt_status', NULL, 0, '2021-09-08 13:24:11', '2021-09-08 13:24:36', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (69, 23, '9', '注销', 'busi_opt_status', NULL, 0, '2021-09-08 13:24:23', '2021-09-08 13:24:38', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (70, 23, '2', '未完成', 'busi_opt_status', NULL, 0, '2021-09-08 13:25:02', '2021-09-08 13:25:15', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (71, 24, '101', '身份证-正面', 'file_busi_type', NULL, 0, '2021-09-08 14:02:36', '2021-09-27 15:53:08', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (72, 24, '102', '身份证-反面', 'file_busi_type', NULL, 0, '2021-09-08 14:02:45', '2021-09-27 15:53:10', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (73, 24, '001', '个人照', 'file_busi_type', NULL, 0, '2021-09-08 14:03:11', '2021-09-27 15:53:12', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (74, 24, '103', '签名', 'file_busi_type', NULL, 0, '2021-09-08 14:03:31', '2021-10-18 15:40:53', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (75, 25, '1', '签入', 'busi_employee_status', NULL, 0, '2021-09-22 16:25:28', '2021-09-22 16:25:52', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (76, 25, '2', '就绪', 'busi_employee_status', NULL, 0, '2021-09-22 16:25:28', '2021-09-22 16:27:56', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (77, 25, '3', '办理', 'busi_employee_status', NULL, 0, '2021-09-22 16:25:28', '2021-09-22 16:28:07', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (78, 25, '4', '小憩', 'busi_employee_status', NULL, 0, '2021-09-22 16:25:28', '2021-09-22 16:28:12', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (79, 25, '5', '签出', 'busi_employee_status', NULL, 0, '2021-09-22 16:25:28', '2021-09-22 16:28:15', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (81, 26, '0', '未激活', 'account_status', NULL, 0, '2021-09-27 09:59:38', '2021-09-27 09:59:38', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (82, 26, '1', '已激活', 'account_status', NULL, 0, '2021-09-27 10:00:16', '2021-09-27 10:00:18', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (83, 26, '2', '已挂失', 'account_status', NULL, 0, '2021-09-27 10:00:37', '2021-09-27 10:00:42', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (84, 27, '1', '借记卡', 'card_type', NULL, 0, '2021-09-27 10:01:22', '2021-09-27 10:01:22', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (85, 27, '2', '信用卡', 'card_type', NULL, 0, '2021-09-27 10:01:33', '2021-09-27 10:01:36', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (86, 28, '1', 'JPG', 'file_type', NULL, 0, '2021-09-27 15:54:26', '2021-09-27 15:54:26', NULL, 1);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
                             `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名',
                             `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
                             `file_md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件md5值',
                             `file_size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
                             `create_user` bigint(0) NULL DEFAULT NULL COMMENT '上传者id',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
                             `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             `enabled` int(0) NULL DEFAULT 1 COMMENT '0：删除 1：未删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 802 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (173, 'TdMjZjX4whnwxsThysV3z2Ak1XmrpjM56GnoKP', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, 101, '2021-10-14 10:51:31', NULL, '2021-10-14 10:51:31', 1);
INSERT INTO `sys_file` VALUES (174, 'e1W0QLH9xb4O4cZ8ev4ASiC1sHHnu546DdYLdW', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, 101, '2021-10-14 10:51:32', NULL, '2021-10-14 10:51:32', 1);
INSERT INTO `sys_file` VALUES (175, 'ddjwgpcOmQC2L9b4VSqvN3w4EBRnnUJ2tNWDT', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, 101, '2021-10-14 14:06:53', NULL, '2021-10-14 14:06:53', 1);
INSERT INTO `sys_file` VALUES (176, 'tXxddBGFhopCCr4oFX5CaQdtEIhdwhgzgu', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, 101, '2021-10-14 14:09:13', NULL, '2021-10-14 14:09:13', 1);
INSERT INTO `sys_file` VALUES (177, 'skhA26TzBmVdzdr35meJ5DZ95QsPJPCLYfaZ5gE', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, 101, '2021-10-14 14:09:32', NULL, '2021-10-14 14:09:32', 1);
INSERT INTO `sys_file` VALUES (178, 'AzWdO7aWIAWHICHUq0cgIOU486bYcROS', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-14 14:19:39', NULL, '2021-10-14 14:19:39', 1);
INSERT INTO `sys_file` VALUES (179, 'vFAWGoEbJYL7YZluLTmOLxI6OoF1KknKqh', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-14 14:19:39', NULL, '2021-10-14 14:19:39', 1);
INSERT INTO `sys_file` VALUES (180, 'JKAGNLcn3xch62L8PCXdHhJk4osy2gyscdCFgp', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-14 14:26:14', NULL, '2021-10-14 14:26:14', 1);
INSERT INTO `sys_file` VALUES (181, 'XxPthtUmr2u9k3QoJbZCcfr70EjxmDgkDTWK', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-14 14:26:14', NULL, '2021-10-14 14:26:14', 1);
INSERT INTO `sys_file` VALUES (182, 'iMPXSOEZ0hkz0KSVfA3OKXjd6uh0Vm5cfFIMx', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-14 14:29:14', NULL, '2021-10-14 14:29:14', 1);
INSERT INTO `sys_file` VALUES (183, 'wWyc8P1vBKj9cOl0RrdCeVRkGrVKizEjc2BXb7', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-14 14:29:14', NULL, '2021-10-14 14:29:14', 1);
INSERT INTO `sys_file` VALUES (184, 'TUy8wKL9zRszlcmMxFMv62JjUYfrTNUr', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 08:25:47', NULL, '2021-10-15 08:25:47', 1);
INSERT INTO `sys_file` VALUES (185, 'AWrzPfzDaXlekjLntVDwCJgF54LlOQCX6U0ChPP', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 09:53:26', NULL, '2021-10-15 09:53:26', 1);
INSERT INTO `sys_file` VALUES (186, 'aduEbqbkp3iWDzRV1JWWFP3lFhiD9R7a', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 10:17:21', NULL, '2021-10-15 10:17:21', 1);
INSERT INTO `sys_file` VALUES (187, 'iqhvcnYP6BUHPGVni2Vp4Y2wyEUol8u6ao', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 10:17:22', NULL, '2021-10-15 10:17:22', 1);
INSERT INTO `sys_file` VALUES (188, '7CtYLfei0OZeNbQXpo4z68WSeStdxVr1C6YQm', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 10:26:47', NULL, '2021-10-15 10:26:47', 1);
INSERT INTO `sys_file` VALUES (189, 'Ny3AxgTdS3Rii732BqptFxHwCZRZhmB0JS', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 10:26:47', NULL, '2021-10-15 10:26:47', 1);
INSERT INTO `sys_file` VALUES (190, 'NeD1y5PCkc5wGsSnffQnQPQhpc1cUTZ0ilezcG', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 10:35:08', NULL, '2021-10-15 10:35:08', 1);
INSERT INTO `sys_file` VALUES (191, 'HgYbH4QeqIpXaOPkrWfqvv6RU90dDvmYjj0Y', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 10:35:08', NULL, '2021-10-15 10:35:08', 1);
INSERT INTO `sys_file` VALUES (192, 'quiMJAjsz9QAOLa35jAlFgqMBd81anSkqF', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 10:46:36', NULL, '2021-10-15 10:46:36', 1);
INSERT INTO `sys_file` VALUES (193, 'ePYpFXwHkQzcVhWv8m1gZqkNFLGYc2CnM2Jyu', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 10:46:36', NULL, '2021-10-15 10:46:36', 1);
INSERT INTO `sys_file` VALUES (194, 'otxKyf7sq2RXUvwn113rBeOO1unVmRUpu', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 10:54:13', NULL, '2021-10-15 10:54:13', 1);
INSERT INTO `sys_file` VALUES (195, 'GnUY0QYdMFdJtQJPyXUHnjR8w9pVUzljh', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 10:54:14', NULL, '2021-10-15 10:54:14', 1);
INSERT INTO `sys_file` VALUES (196, 'hlVNIGm3s0buFRMPsFqPGrY051GXQBf4hXYr', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 11:03:16', NULL, '2021-10-15 11:03:16', 1);
INSERT INTO `sys_file` VALUES (197, 'vjEjabVXqND4nf6f2bA6aNeaLTTJq2sQOEM', 'counter', 'pic2.jpg', 'image/jpeg', 'd782bb3dc09b0113eeb82bdd6f89f046', 51602, NULL, '2021-10-15 11:03:16', NULL, '2021-10-15 11:03:16', 1);
INSERT INTO `sys_file` VALUES (198, 's3O8qCVNtR3Ymx8qqUAjuqRPlsMY0oQCNluh', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 11:25:10', NULL, '2021-10-15 11:25:10', 1);
INSERT INTO `sys_file` VALUES (199, 'lLCKJTsYxjaHaCBDsPA2jlIijTCCX3sQ', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 11:27:19', NULL, '2021-10-15 11:27:19', 1);
INSERT INTO `sys_file` VALUES (200, 'zF7c8CGh7uN1Lb8AaX8hMyr9nPvbWsF9EmToB', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 14:15:00', NULL, '2021-10-15 14:15:00', 1);
INSERT INTO `sys_file` VALUES (201, 'MieQTwKMV0rTlg9XDUozyFjtd6P9nwKX1', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-15 14:16:01', NULL, '2021-10-15 14:16:01', 1);
INSERT INTO `sys_file` VALUES (202, 'P4JtD6hXL7J77rCQjgVulo2UhXeHlDdIo', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:50:32', NULL, '2021-10-15 14:50:32', 1);
INSERT INTO `sys_file` VALUES (203, 'OLbILYapr3Ss8PNl4VOyB4RI1YfeJ8lMW', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:50:33', NULL, '2021-10-15 14:50:33', 1);
INSERT INTO `sys_file` VALUES (204, 'qfe1bpz90SlA6zK21H2KDEIntqb5aLde', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:51:23', NULL, '2021-10-15 14:51:23', 1);
INSERT INTO `sys_file` VALUES (205, 'L5ev3o1e7YAKH9gpT2ZmmkXKZBCDej8Wz6s7Jhz', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:51:23', NULL, '2021-10-15 14:51:23', 1);
INSERT INTO `sys_file` VALUES (206, 'eGssRc176rHNtP3EfB1wePDF9ljZcjBEyxY7dLX', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:52:17', NULL, '2021-10-15 14:52:17', 1);
INSERT INTO `sys_file` VALUES (207, 'CaVibyRT83QRKYYzsLDhtvp5qglSYVXsHov', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-15 14:52:17', NULL, '2021-10-15 14:52:17', 1);
INSERT INTO `sys_file` VALUES (208, 'bXZfJyr9eI7f62Z7Tc4lPouLNZEUepL7Czl', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-15 15:48:22', NULL, '2021-10-15 15:48:22', 1);
INSERT INTO `sys_file` VALUES (209, 'c14Eu6Gb1xYO74t8j5vQQZZAuMO17LHRzF4on', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-15 15:48:26', NULL, '2021-10-15 15:48:26', 1);
INSERT INTO `sys_file` VALUES (210, 'pcJ20Fp32UmQpSBw5TjJZXmx86MoNk9E2w4lj', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-15 15:50:32', NULL, '2021-10-15 15:50:32', 1);
INSERT INTO `sys_file` VALUES (211, '70jrTkhb31LRNse3vEwO8UruF7Krv5Uv69w', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-15 15:50:32', NULL, '2021-10-15 15:50:32', 1);
INSERT INTO `sys_file` VALUES (212, 'uOut3m8tGLUHPPn6N11I5knIqODJ2VJhY', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-15 15:52:54', NULL, '2021-10-15 15:52:54', 1);
INSERT INTO `sys_file` VALUES (213, 'lpk3AswS0eMqtrZ3zvwcbhWCdDjP6gZF7RUt', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-15 15:52:55', NULL, '2021-10-15 15:52:55', 1);
INSERT INTO `sys_file` VALUES (233, 'Z82lf3036ezuWSh6ImhrdPCqBtaffUH0RfcD', 'counter', NULL, '101', '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 08:38:34', NULL, '2021-10-16 08:38:34', 1);
INSERT INTO `sys_file` VALUES (234, 'f6VrfpmOyQejFG2BP7EDQoXtjoZkIObbii', 'counter', NULL, '102', 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-16 08:38:35', NULL, '2021-10-16 08:38:35', 1);
INSERT INTO `sys_file` VALUES (235, 'Ogh0OYtwOrnpO5M81obSG9PyTqleg7PR2jAXA', 'counter', NULL, '101', '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 08:46:06', NULL, '2021-10-16 08:46:06', 1);
INSERT INTO `sys_file` VALUES (236, 'ZbZDeDi8DmBvOJjD2wFydR2YbtyBPYNBs', 'counter', NULL, '102', 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-16 08:46:06', NULL, '2021-10-16 08:46:06', 1);
INSERT INTO `sys_file` VALUES (237, 'KxMx0xySsbLuTXf7iJmA2zbEDwIUl9WYzY6mxv', 'counter', NULL, '101', '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 08:54:47', NULL, '2021-10-16 08:54:47', 1);
INSERT INTO `sys_file` VALUES (238, 'cgj6J7kkktA3eYt4cDrxE97y3Zlqcsg9s', 'counter', NULL, '102', 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-16 08:54:48', NULL, '2021-10-16 08:54:48', 1);
INSERT INTO `sys_file` VALUES (239, 'UvVFwYDiq0nWFpZQLY4TCO4z4HbjYcqEuTNM2UO', 'counter', NULL, '101', '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 08:58:45', NULL, '2021-10-16 08:58:45', 1);
INSERT INTO `sys_file` VALUES (240, 'EQAqFsm6iwUDTyzOa99oEm2PpBQUgYfDaamsCj', 'counter', NULL, '102', 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-16 08:58:45', NULL, '2021-10-16 08:58:45', 1);
INSERT INTO `sys_file` VALUES (241, 'RZF1qwUyOY4SUAxHp4YWSVRi8KFWoVeeBoTOZc', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:09:37', NULL, '2021-10-16 10:09:37', 1);
INSERT INTO `sys_file` VALUES (242, '1mk23GzHQacno7oxLVdPpDvw2m3W53B5', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:10:31', NULL, '2021-10-16 10:10:31', 1);
INSERT INTO `sys_file` VALUES (243, 'g8mtKwMpHFYUgoQHyLQyPjmGBdydaGP90k6iIqB', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:12:24', NULL, '2021-10-16 10:12:24', 1);
INSERT INTO `sys_file` VALUES (244, '7cIddRa4OdsbqnCistZcGY6oWKvAtimFDqR6Wq', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:12:56', NULL, '2021-10-16 10:12:56', 1);
INSERT INTO `sys_file` VALUES (245, 'aFLozEDWDTA6IlvWskGsDJOJyyMbfDBq0Q', 'counter', NULL, '102', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:12:56', NULL, '2021-10-16 10:12:56', 1);
INSERT INTO `sys_file` VALUES (246, 'E6RVfCGT17IgMsAyPgyIXIppk9E5XWTHmrW7Ysu', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:33:25', NULL, '2021-10-16 10:33:25', 1);
INSERT INTO `sys_file` VALUES (247, 'E6RVfCGT17IgMsAyPgyIXIppk9E5XWTHmrW7Ysu', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:35:40', NULL, '2021-10-16 10:35:40', 1);
INSERT INTO `sys_file` VALUES (248, 'cp24jMqnyo338OV7lxZFRRwUd28v4TQstyZ7TU0', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:35:40', NULL, '2021-10-16 10:35:40', 1);
INSERT INTO `sys_file` VALUES (249, 'aAHBIxtPg4ghjuR0JDxlD3wnNJdQrQJPl', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:36:42', NULL, '2021-10-16 10:36:42', 1);
INSERT INTO `sys_file` VALUES (250, 'LDPByml8Q4mNIWTF0vuwlOvRDgkYaPpIq', 'counter', NULL, '102', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:36:42', NULL, '2021-10-16 10:36:42', 1);
INSERT INTO `sys_file` VALUES (251, 'A3o1M6iPldPFfvQkZESbLJLzbugl1D21JrvkuG', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:41:12', NULL, '2021-10-16 10:41:12', 1);
INSERT INTO `sys_file` VALUES (252, '4zyljeFIyNRXv6vglhcBpsnkvaq9f3G1l41DvSe', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:42:47', NULL, '2021-10-16 10:42:47', 1);
INSERT INTO `sys_file` VALUES (253, 'tn0kMYszog7oWjhEACikbPJuIsKTiq32cX5IMm', 'counter', NULL, '101', '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:42:47', NULL, '2021-10-16 10:42:47', 1);
INSERT INTO `sys_file` VALUES (254, 'nWieL3yvbl0UXGzstOllQjLqrUY1GLUcdUj', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:44:19', NULL, '2021-10-16 10:44:19', 1);
INSERT INTO `sys_file` VALUES (255, '9zGuUCDQ7hcS9VNnimDEqAlh3mX2XXuop', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:45:21', NULL, '2021-10-16 10:45:21', 1);
INSERT INTO `sys_file` VALUES (256, 'yuqEbparbkx9p3XmVbngRL8sOauY3MMcgQho90c', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:45:21', NULL, '2021-10-16 10:45:21', 1);
INSERT INTO `sys_file` VALUES (257, '01a3wAho59x860QO1ilLjFLXtOKw0YIGkwl', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:45:28', NULL, '2021-10-16 10:45:28', 1);
INSERT INTO `sys_file` VALUES (258, '5f9kwW1Pzd9IA487U79B8vmfFWp4jKQ0b7', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 10:45:28', NULL, '2021-10-16 10:45:28', 1);
INSERT INTO `sys_file` VALUES (259, 'CkEEeKdZ415S2JoNs9bXi2a5qlqLmauq', 'counter', 'pic1.jpg', 'image/jpeg', '8f729ab1029900f148635f551d6e9f6d', 362950, NULL, '2021-10-16 11:13:56', NULL, '2021-10-16 11:13:56', 1);
INSERT INTO `sys_file` VALUES (260, 'W19pjmDRp0I66GGg5DoLhAKX4MbYflbBJ9', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 11:14:03', NULL, '2021-10-16 11:14:03', 1);
INSERT INTO `sys_file` VALUES (261, 'PUPNbUl1z8f3oxcXewl1a3qLgCuGB8wEKk', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 11:14:04', NULL, '2021-10-16 11:14:04', 1);
INSERT INTO `sys_file` VALUES (262, 'W19pjmDRp0I66GGg5DoLhAKX4MbYflbBJ9', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 11:14:04', NULL, '2021-10-16 11:14:04', 1);
INSERT INTO `sys_file` VALUES (263, 'kftxIbaqRSnfuL1yN3VhSBI8sXpPQl90', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 11:14:05', NULL, '2021-10-16 11:14:05', 1);
INSERT INTO `sys_file` VALUES (264, 'mlPjj9LV8hJWiiY9ocOUMUXPUwoeEjpLm', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 13:27:59', NULL, '2021-10-16 13:27:59', 1);
INSERT INTO `sys_file` VALUES (265, 'HwOYFDMeUoCVMauLvCjjEE2ZU49nZdtJVv6SUqK', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:35:48', NULL, '2021-10-16 13:35:48', 1);
INSERT INTO `sys_file` VALUES (266, 'SbSm9K7N4f7G5SGL1tCh8hVbEbB4uHICq', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:35:48', NULL, '2021-10-16 13:35:48', 1);
INSERT INTO `sys_file` VALUES (267, 'NApF80EX5XNJDu6E7FvpBpO9MwrOkfae', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:35:58', NULL, '2021-10-16 13:35:58', 1);
INSERT INTO `sys_file` VALUES (268, 'm9V5E8baLa3YLZuhAOJdLDMv97vbRkLgX', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:35:58', NULL, '2021-10-16 13:35:58', 1);
INSERT INTO `sys_file` VALUES (269, 'n8H93POJg2IUIa1DV1t5rwG4zc3YKeV6oI', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:36:57', NULL, '2021-10-16 13:36:57', 1);
INSERT INTO `sys_file` VALUES (270, 'G1LpVJ4tn5cTTq7LqJ2ceAZQ4g0Uce0Fq', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 13:36:57', NULL, '2021-10-16 13:36:57', 1);
INSERT INTO `sys_file` VALUES (271, '46fTNESmM9E6FOzADLJ3rshCPHRy8w3Li', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 14:43:45', NULL, '2021-10-16 14:43:45', 1);
INSERT INTO `sys_file` VALUES (272, 'Hti5l9ubeBWVRc1soqmZ0sP2x9bO69q4SsUk2', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:19:34', NULL, '2021-10-16 15:19:34', 1);
INSERT INTO `sys_file` VALUES (273, 'tZeH7F2MH10XIMvjuchKRvEIOm5YRxeEoWNyX7s', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:19:34', NULL, '2021-10-16 15:19:34', 1);
INSERT INTO `sys_file` VALUES (274, 'OM2Wmsvvo9JM4zAJSYtj98opuvzBHT8Fn9AIU', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:20:02', NULL, '2021-10-16 15:20:02', 1);
INSERT INTO `sys_file` VALUES (275, 'YidIScjY1K1qgmFWqwUXStEl69H48Ct49qo7x', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:20:03', NULL, '2021-10-16 15:20:03', 1);
INSERT INTO `sys_file` VALUES (276, 'ctzPm3Ke3POUmmJrJNFclX9mNBXpZPBqpJxF', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:20:09', NULL, '2021-10-16 15:20:09', 1);
INSERT INTO `sys_file` VALUES (277, '03tn3PHWjORhpmK7bsUaeUOwNJirNstI9z', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:20:10', NULL, '2021-10-16 15:20:10', 1);
INSERT INTO `sys_file` VALUES (278, 'WF18GzPh6OoLhPGhTxSvJgxiRC6n4F5nKz3h', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:22:37', NULL, '2021-10-16 15:22:37', 1);
INSERT INTO `sys_file` VALUES (279, '8vzqdpY3OkTHAeDGUKLSKWohFQWval1zC2', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 15:22:37', NULL, '2021-10-16 15:22:37', 1);
INSERT INTO `sys_file` VALUES (280, 'PP1z6iDRUhuFQUXOAw4ycsd5LdX4Cs86C1ndj0L', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:15:47', NULL, '2021-10-16 16:15:47', 1);
INSERT INTO `sys_file` VALUES (281, 'SlwDYBiJFLVoelo5B3j1gyfZohUjRinNOf0D3d', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:24:21', NULL, '2021-10-16 16:24:21', 1);
INSERT INTO `sys_file` VALUES (282, 'jlr6lFJoxfeAzqUECjQWX3mdP17PCUKPctrZCc', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:29:53', NULL, '2021-10-16 16:29:53', 1);
INSERT INTO `sys_file` VALUES (283, 'tmU8C9DIyCjPfC29K58AMquBfIvnrazOEo', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:30:51', NULL, '2021-10-16 16:30:51', 1);
INSERT INTO `sys_file` VALUES (284, 'bEWRzezX4K8E9mAEfWBVa1eZrm5CH41g', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:30:52', NULL, '2021-10-16 16:30:52', 1);
INSERT INTO `sys_file` VALUES (285, 'zTIlo03ilanoEDRImGi95VOigFFyJgrvtg3TqO', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:30:53', NULL, '2021-10-16 16:30:53', 1);
INSERT INTO `sys_file` VALUES (286, '49bsiSa5UKVuZUttRbkDrCWG5yHuk0lk75hB', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:42:41', NULL, '2021-10-16 16:42:41', 1);
INSERT INTO `sys_file` VALUES (287, 'pFotpmTYGdw6jdPMwogAPSCw7prOQxKD', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:44:39', NULL, '2021-10-16 16:44:39', 1);
INSERT INTO `sys_file` VALUES (288, '29Pa5RgSgYflOAg7zFcgbL1x6wh7WbDjzG7eNcD', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-16 16:49:07', NULL, '2021-10-16 16:49:07', 1);
INSERT INTO `sys_file` VALUES (289, 'HqC8SNiCdDGhVsycxqnWze7QZ401xJWDHa', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 16:58:04', NULL, '2021-10-16 16:58:04', 1);
INSERT INTO `sys_file` VALUES (290, 'EQKZPrp1uWtlGTRoPimb1A6nTeyLoFUBT', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 16:58:37', NULL, '2021-10-16 16:58:37', 1);
INSERT INTO `sys_file` VALUES (291, 'AiMc85kNmQsKlRBQUuWnau2NWcIUuDwuAAyH', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 16:59:03', NULL, '2021-10-16 16:59:03', 1);
INSERT INTO `sys_file` VALUES (292, 'i4TWxtmRA0qhstBU4KGnqZqUtWMpXG5PmN', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 16:59:04', NULL, '2021-10-16 16:59:04', 1);
INSERT INTO `sys_file` VALUES (293, '8LzlNOfYfcrnhVi7NUSgMrG7hBfWvjjm', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 17:06:09', NULL, '2021-10-16 17:06:09', 1);
INSERT INTO `sys_file` VALUES (294, 'nmqf05IQzZzHTp6U72HxQvCoUxQjziVTnJ', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-16 17:06:09', NULL, '2021-10-16 17:06:09', 1);
INSERT INTO `sys_file` VALUES (295, '7KkRR1s09G9X4Wf9EVRjyyreUrICbd0N8uw', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 08:39:53', NULL, '2021-10-18 08:39:53', 1);
INSERT INTO `sys_file` VALUES (296, 'NJr95y8MRMyhC7ihY3pv0cHlRnK7PfmCmzBr', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 08:39:54', NULL, '2021-10-18 08:39:54', 1);
INSERT INTO `sys_file` VALUES (297, '2iQJQM9HhrlEY7mYMCZw6f7FJVlLXLCeu5YTUr', 'counter', NULL, NULL, '4498a3af9ed56fdcef61907215b8cadb', 658138, NULL, '2021-10-18 09:10:44', NULL, '2021-10-18 09:10:44', 1);
INSERT INTO `sys_file` VALUES (298, 'wlo5SuiEmFVdNlZiIRioklrXYHKdeLaPU', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-18 09:10:45', NULL, '2021-10-18 09:10:45', 1);
INSERT INTO `sys_file` VALUES (299, 'zy6qftdf9rkR8CSo0p6RptzV2Prjb0nABbF', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:21:11', NULL, '2021-10-18 09:21:11', 1);
INSERT INTO `sys_file` VALUES (300, 'n5pwi4uBrKcaH43NTlkgabzJXfs5nTXmzun', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:21:11', NULL, '2021-10-18 09:21:11', 1);
INSERT INTO `sys_file` VALUES (301, 'rFkFqpZm1oAo7fD0drkQSQBwE4zDnJEn', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-18 09:38:58', NULL, '2021-10-18 09:38:58', 1);
INSERT INTO `sys_file` VALUES (302, 'nmOMVm3Deqh81GjJwqfy3UaeKivwMHuZHUb24', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-18 09:38:59', NULL, '2021-10-18 09:38:59', 1);
INSERT INTO `sys_file` VALUES (303, 'ff1Eoq9ob2r8jtgYg0IhrWFevhTvECaTxdRa3DB', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:45:45', NULL, '2021-10-18 09:45:45', 1);
INSERT INTO `sys_file` VALUES (304, 'n3PJgY4wqiLwmSWBXlXZ1oM7s1zvyk5Frq', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:47:35', NULL, '2021-10-18 09:47:35', 1);
INSERT INTO `sys_file` VALUES (305, 'dxILDOqVR1GzbV1vn8loBiAOyYIn3urCqe', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:47:35', NULL, '2021-10-18 09:47:35', 1);
INSERT INTO `sys_file` VALUES (306, 'rhlBTUkt6YKrNlkwQmMOyE5Giq8x5ahCHRI2XzW', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:50:11', NULL, '2021-10-18 09:50:11', 1);
INSERT INTO `sys_file` VALUES (307, 'auuu2WytaaMHt1NHTBZCBX7aWWWcJuGOUT2', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:50:11', NULL, '2021-10-18 09:50:11', 1);
INSERT INTO `sys_file` VALUES (308, 'Ya7DIa8q6iON4zSi0mazVV9leCBiBMVEZJaV', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:58:54', NULL, '2021-10-18 09:58:54', 1);
INSERT INTO `sys_file` VALUES (309, 'Oog7Z00J7Z9OvJpzhCCIaqlcm7IYaZOIPtV', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:58:55', NULL, '2021-10-18 09:58:55', 1);
INSERT INTO `sys_file` VALUES (310, 'yZNbcm9hwlspNjI8K3e7893lJedz6LXqcy', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 09:58:56', NULL, '2021-10-18 09:58:56', 1);
INSERT INTO `sys_file` VALUES (311, 'Nt0InFTcfBasdZwmWKff0MT5aza8sS2MePn', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:12:52', NULL, '2021-10-18 10:12:52', 1);
INSERT INTO `sys_file` VALUES (312, 'q7PYgyIWPyeW99sGvUGfPCLnCnkA153y', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:12:53', NULL, '2021-10-18 10:12:53', 1);
INSERT INTO `sys_file` VALUES (313, 'q7PYgyIWPyeW99sGvUGfPCLnCnkA153y', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:12:54', NULL, '2021-10-18 10:12:54', 1);
INSERT INTO `sys_file` VALUES (314, 'mSjyBdseQ94fjFLi7UUUpQEzsx7CTydbXd', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:12:54', NULL, '2021-10-18 10:12:54', 1);
INSERT INTO `sys_file` VALUES (315, 'OTSV2pqoMbiz4rVRo6oQfgUrzbgUO7aiZw5Jvd', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:12:55', NULL, '2021-10-18 10:12:55', 1);
INSERT INTO `sys_file` VALUES (316, 'FEDRHO5t0JhBWynLb63uQzqDiFeRYcSdi0', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:16:57', NULL, '2021-10-18 10:16:57', 1);
INSERT INTO `sys_file` VALUES (317, 'NKl0WWdEeAuvcncA58nb7qCdoHJyUsd5ba', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:16:57', NULL, '2021-10-18 10:16:57', 1);
INSERT INTO `sys_file` VALUES (318, 'VK0jOzJFoO7Pi9I91NrL4pOPBdLbwijuB0hL4o', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-18 10:33:44', NULL, '2021-10-18 10:33:44', 1);
INSERT INTO `sys_file` VALUES (319, 'X9Wk9sArh4XqtG1Rj5lLDcV42SGXpImbexuUuQ', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-18 10:38:39', NULL, '2021-10-18 10:38:39', 1);
INSERT INTO `sys_file` VALUES (320, 'LTooPu0CoFK2eEmCueCi4nPzrye2Y0BQyKZ64D', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-18 10:38:49', NULL, '2021-10-18 10:38:49', 1);
INSERT INTO `sys_file` VALUES (321, 'YFqaNgR5aypGQPIyns3NHpgWtueB8oKp', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:58:27', NULL, '2021-10-18 10:58:27', 1);
INSERT INTO `sys_file` VALUES (322, 'D3FNAFVWa1Zi923V821LtamUn0FYqUMGBW', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 10:58:28', NULL, '2021-10-18 10:58:28', 1);
INSERT INTO `sys_file` VALUES (323, 'SGA9SyKFCPVj34IipcYRRt2DomR7G9TSkYmtI', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:02:58', NULL, '2021-10-18 11:02:58', 1);
INSERT INTO `sys_file` VALUES (324, 'lUbhoWsTiJWx0V3rfY2k0Jq3GpOly3Pl', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:02:58', NULL, '2021-10-18 11:02:58', 1);
INSERT INTO `sys_file` VALUES (325, 'vZH92013YBX2LoEprDbZPC8xrvWdI49yEsBEqKP', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:03:42', NULL, '2021-10-18 11:03:42', 1);
INSERT INTO `sys_file` VALUES (326, 'FWPBuJgjhWIetY8E2LDnBi8byLZQuO13L3Ia', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:03:43', NULL, '2021-10-18 11:03:43', 1);
INSERT INTO `sys_file` VALUES (327, '7IbJLeTJ9KWJhXQIu68Qz98kvDvkoMNgR4C', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:03:44', NULL, '2021-10-18 11:03:44', 1);
INSERT INTO `sys_file` VALUES (328, 'Z6Q9IDXwhDS12nAQofUUKNOWxgl4OUU1G', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:03:57', NULL, '2021-10-18 11:03:57', 1);
INSERT INTO `sys_file` VALUES (329, 'SctXEyqxOn4nqq2rx2NgoPRzkQfo5YZk3NoI', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:03:57', NULL, '2021-10-18 11:03:57', 1);
INSERT INTO `sys_file` VALUES (330, 'FDKcJYqB1I3XUq8zCfS3s9GaMb2T3iHL', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:07:56', NULL, '2021-10-18 11:07:56', 1);
INSERT INTO `sys_file` VALUES (331, 'OUjxs54uHTiiOZLnLDgeM2YtTcHOEoGrYi3', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:07:57', NULL, '2021-10-18 11:07:57', 1);
INSERT INTO `sys_file` VALUES (332, '65pjdBO6ducBNojFSYN3aIdfHTYGEMBOT', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-18 11:13:24', NULL, '2021-10-18 11:13:24', 1);
INSERT INTO `sys_file` VALUES (333, 'gInyq07b1V09KZ95ZjAVvvmfNasT52rWYmpE9Ym', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-18 11:13:24', NULL, '2021-10-18 11:13:24', 1);
INSERT INTO `sys_file` VALUES (334, '7vsIhtzyjJg6C32YHkW8EBiK7bD1yuW1a8kbKEI', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:13:43', NULL, '2021-10-18 11:13:43', 1);
INSERT INTO `sys_file` VALUES (335, 'C222mmj3m15CCxFvmFVRwx8LvL8RtaSlcKWqp', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:13:43', NULL, '2021-10-18 11:13:43', 1);
INSERT INTO `sys_file` VALUES (336, 'yE7e2JJBenqlrdC9u3tun0nKjGBBDQWd8RvZ3', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:18:59', NULL, '2021-10-18 11:18:59', 1);
INSERT INTO `sys_file` VALUES (337, 'OOsl96LAL6yZ5q2KfeS2fXE9akrRDxUYC', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:19:00', NULL, '2021-10-18 11:19:00', 1);
INSERT INTO `sys_file` VALUES (338, 'TvNTucCe163C0Wslwv3W2PxQocHXoF0i', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:27:55', NULL, '2021-10-18 11:27:55', 1);
INSERT INTO `sys_file` VALUES (339, 'dJOZZA3ah0tiuUg6y0Ibt4pLw0SPgKDj', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:27:55', NULL, '2021-10-18 11:27:55', 1);
INSERT INTO `sys_file` VALUES (340, 'dQINND9a28bHe4vBW8VmpVGwPEzlmw9nsWzHiS', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:37:24', NULL, '2021-10-18 11:37:24', 1);
INSERT INTO `sys_file` VALUES (341, 'ArJPMOwhCDovKZfvj5Lk2zO2aPlyw3VBV5uWJxW', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-18 11:37:25', NULL, '2021-10-18 11:37:25', 1);
INSERT INTO `sys_file` VALUES (342, 'pnVU9QnwcygA8lZkAZspIeJ7QdoiCW6GtD', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-18 15:26:35', NULL, '2021-10-18 15:26:35', 1);
INSERT INTO `sys_file` VALUES (343, 'QbLsmyEEc6urEq2tJzfeZc4EQuW6Yn2iujsNb', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 09:12:26', NULL, '2021-10-19 09:12:26', 1);
INSERT INTO `sys_file` VALUES (344, 'VE9857Vfb4pUfTbKYWdhq206yxj7kyj73GYuLL', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 09:12:26', NULL, '2021-10-19 09:12:26', 1);
INSERT INTO `sys_file` VALUES (345, 'gGW59wtouY6jkkdORBXHrp5WtZ9eORYmMAnI5jy', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 10:00:30', NULL, '2021-10-19 10:00:30', 1);
INSERT INTO `sys_file` VALUES (346, 'HVALu4OFYnZD4lLx3Os8oXoDgi3j4i4T', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 10:00:30', NULL, '2021-10-19 10:00:30', 1);
INSERT INTO `sys_file` VALUES (347, '1vtEdAsmrOiqCtg3OLaRTSw0Ejhy9g3rj6T', 'counter', NULL, NULL, '17fb14d1f76a4ab2623347a753f3b224', 2023, NULL, '2021-10-19 10:03:34', NULL, '2021-10-19 10:03:34', 1);
INSERT INTO `sys_file` VALUES (348, 'MX8wVVtgZOG7fEVuK1KihMT90SeA98KFnFCZwVj', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 10:06:42', NULL, '2021-10-19 10:06:42', 1);
INSERT INTO `sys_file` VALUES (349, 'MiCnLusTVxdvwMBWrY2je6RH0T4GAt7E', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 10:06:42', NULL, '2021-10-19 10:06:42', 1);
INSERT INTO `sys_file` VALUES (350, 'zFsVqEsKr9JiEs449ZzhGPpMIc3gvpoAH', 'counter', NULL, NULL, 'ddc039cf60e7b752693778ea644f5e4b', 1619, NULL, '2021-10-19 10:07:05', NULL, '2021-10-19 10:07:05', 1);
INSERT INTO `sys_file` VALUES (351, 'aws6t1W0LNdtumvFGluupBm6FgQ0O2he', 'counter', NULL, NULL, '78f81435bfbc8cad05ae34850e3c8a9d', 3479, NULL, '2021-10-19 10:09:52', NULL, '2021-10-19 10:09:52', 1);
INSERT INTO `sys_file` VALUES (352, 'xPk85exhR4NhEQUV1dhjobTLQLQ3MPWwp6yx', 'counter', NULL, NULL, 'd3632218cf04c8ad69e87994821afe30', 1510, NULL, '2021-10-19 10:12:43', NULL, '2021-10-19 10:12:43', 1);
INSERT INTO `sys_file` VALUES (353, 'tmKVsgfdl6bQUyZkjPZwQCKpiWvHmrf4', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 10:41:38', NULL, '2021-10-19 10:41:38', 1);
INSERT INTO `sys_file` VALUES (354, 'kMSCqALBQ08Ws5vSWOKGIyzGoAMDtQJMzcLmjYy', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 10:41:39', NULL, '2021-10-19 10:41:39', 1);
INSERT INTO `sys_file` VALUES (355, '6jKK4CqL6faQScG2o4OU6RUiauGhhY3Br6a41Bf', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 10:48:37', NULL, '2021-10-19 10:48:37', 1);
INSERT INTO `sys_file` VALUES (356, 'mGhISWSBdw1TUS1TQWiizEGoJNlUhqPQm31', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 10:57:44', NULL, '2021-10-19 10:57:44', 1);
INSERT INTO `sys_file` VALUES (357, 'HyAPjtNO8fG4zF3cvjrE1u42VljhRoUv1HvHN', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 10:58:19', NULL, '2021-10-19 10:58:19', 1);
INSERT INTO `sys_file` VALUES (358, 'qcMcPFDoYaYPwU0GQJtd86qoQmFG6bjOiQl', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 11:00:01', NULL, '2021-10-19 11:00:01', 1);
INSERT INTO `sys_file` VALUES (359, 'XDipylBozFtYK0O3vDNZEz058DuZmNkz', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 13:27:22', NULL, '2021-10-19 13:27:22', 1);
INSERT INTO `sys_file` VALUES (360, 'PwMKRIdMZ8mucTgnGiVk07jhevYu4VFCJKgPTb', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 13:27:22', NULL, '2021-10-19 13:27:22', 1);
INSERT INTO `sys_file` VALUES (361, 'nBlUoqxviZNdxKRGmBN6Qd7y0CbfaQUNkVU8V', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-19 14:03:48', NULL, '2021-10-19 14:03:48', 1);
INSERT INTO `sys_file` VALUES (362, 'K1mqd08ZRhWYXwLJ1kAeAJJLzROWkurQzB0Io', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-19 14:03:48', NULL, '2021-10-19 14:03:48', 1);
INSERT INTO `sys_file` VALUES (363, '53KEvgiu0ONneHWz4DyNArJhY8IU8hZOv3FO', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-19 14:04:41', NULL, '2021-10-19 14:04:41', 1);
INSERT INTO `sys_file` VALUES (364, '54WrCsaZlcqLIEVpSawjWTKtE24MqNNK', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-10-19 14:04:41', NULL, '2021-10-19 14:04:41', 1);
INSERT INTO `sys_file` VALUES (365, 'G2xTz2TGt7g2MFscj76157InGExsRyxok00', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:13:29', NULL, '2021-10-19 14:13:29', 1);
INSERT INTO `sys_file` VALUES (366, 'WZySWIWl7GhN3Qsw5DjBJiFrAU8cbX3x2G7XjII', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:13:30', NULL, '2021-10-19 14:13:30', 1);
INSERT INTO `sys_file` VALUES (367, 'P5NRlfqbkYemixeN7dPrQxZV3QLArxROzpJ', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:13:53', NULL, '2021-10-19 14:13:53', 1);
INSERT INTO `sys_file` VALUES (368, 'oBn9WXzJSs3E3Avpg83E9kYute9wVUP7SpX5pBo', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:13:53', NULL, '2021-10-19 14:13:53', 1);
INSERT INTO `sys_file` VALUES (369, 'dJXZYnmjOFmPY4DRWBGDiKyGYTpswqYIhXd', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:19:21', NULL, '2021-10-19 14:19:21', 1);
INSERT INTO `sys_file` VALUES (370, 'o58urWQFwNPXcD8qSEreLVOTxGL96s3iMwidg', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:19:21', NULL, '2021-10-19 14:19:21', 1);
INSERT INTO `sys_file` VALUES (371, '8lYrj6x966q77vVzglzhk66JvkFAxhDbsSbsg', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:27:14', NULL, '2021-10-19 14:27:14', 1);
INSERT INTO `sys_file` VALUES (372, 'F3LxZ6QzTfgJ3sveEDGZy1BSGiC9jSH9C7Q', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:27:15', NULL, '2021-10-19 14:27:15', 1);
INSERT INTO `sys_file` VALUES (373, 'CsTSZMdIt5O9kPwbN7MWRrgE43VFl9M0zK', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:31:53', NULL, '2021-10-19 14:31:53', 1);
INSERT INTO `sys_file` VALUES (374, 'IlbksR0Ot8kIaKzFofwoWvIa1EBZeBBup', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:31:53', NULL, '2021-10-19 14:31:53', 1);
INSERT INTO `sys_file` VALUES (375, 'sy6oU7CMHPYF7yI2QQkXzjSh7wiOkh0X', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:35:12', NULL, '2021-10-19 14:35:12', 1);
INSERT INTO `sys_file` VALUES (376, 'zUvJ6sNZcFc2tJW6isW0rOzJPl3GTt1JioFEhA', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 14:35:13', NULL, '2021-10-19 14:35:13', 1);
INSERT INTO `sys_file` VALUES (377, 'vDlK9j04grfBZddvOZYBD1TcINfMbqIcNz0lf', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:06:37', NULL, '2021-10-19 15:06:37', 1);
INSERT INTO `sys_file` VALUES (378, '1GRKwirNwGa50LCArlw6EE2qZRcTjva1UDUwL46', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:06:37', NULL, '2021-10-19 15:06:37', 1);
INSERT INTO `sys_file` VALUES (379, 'vWrfpw4CCTN7UoQT7D04lNnvCPHn2Gpo', 'counter', NULL, NULL, '31a3204632ce56521669fecdf7d34c26', 1459, NULL, '2021-10-19 15:11:15', NULL, '2021-10-19 15:11:15', 1);
INSERT INTO `sys_file` VALUES (380, 'ssQ4EvWwkEgDOjvZ0jNdfg78n1Ofl8PCGU', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:11:31', NULL, '2021-10-19 15:11:31', 1);
INSERT INTO `sys_file` VALUES (381, 'M8ryctKcqy5W02yAsQsX4GZsAHsqf12ppJjMq', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:11:31', NULL, '2021-10-19 15:11:31', 1);
INSERT INTO `sys_file` VALUES (382, 'ODda6zf7lYiaXqEg0CdiQJ0tbOKJSkU4TyFX6Og', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:14:39', NULL, '2021-10-19 15:14:39', 1);
INSERT INTO `sys_file` VALUES (383, 'ZoS5RKp44MNhftFSxDCXDobFDndufiW6', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:14:40', NULL, '2021-10-19 15:14:40', 1);
INSERT INTO `sys_file` VALUES (384, 'ONBAn7aInhqltaxTQbo3xXc8pNc5yWNOB', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:18:28', NULL, '2021-10-19 15:18:28', 1);
INSERT INTO `sys_file` VALUES (385, 'hoTuOBNOi6bMV1tZeaF85ChfWU2fRnKw8m', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:18:28', NULL, '2021-10-19 15:18:28', 1);
INSERT INTO `sys_file` VALUES (386, 'rHfup6peqawhgDzmbOEcwFKCuxlFHUw3Zf', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:20:05', NULL, '2021-10-19 15:20:05', 1);
INSERT INTO `sys_file` VALUES (387, '3DB65vb1oz10rZv7uIN0et7Mdb4J2XyGia', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:20:05', NULL, '2021-10-19 15:20:05', 1);
INSERT INTO `sys_file` VALUES (388, 'dl7riiUcOZOY1yYNHts1FKTBbto5mbSv', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:21:27', NULL, '2021-10-19 15:21:27', 1);
INSERT INTO `sys_file` VALUES (389, 'wQiTeoUGkeY6WGSoSTvUVtOU8y4suONhO7Ucmv', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:21:27', NULL, '2021-10-19 15:21:27', 1);
INSERT INTO `sys_file` VALUES (390, 'DbMd4Pk2KkCgODxSsgEr5VKcje2w9WkYk1', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:24:04', NULL, '2021-10-19 15:24:04', 1);
INSERT INTO `sys_file` VALUES (391, 'M8aSQhBkAsDy0uW4LVmbxLQ3ZjnSBqJn8HtTY3t', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:24:05', NULL, '2021-10-19 15:24:05', 1);
INSERT INTO `sys_file` VALUES (392, '2WKYkihrVJ1quRxossiXPHll2nUYRq7gT8kY', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:49:31', NULL, '2021-10-19 15:49:31', 1);
INSERT INTO `sys_file` VALUES (393, '0M4n2PzIePzEGOqnJdN1KZHIQCFTK3iIjCe1vvX', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:51:25', NULL, '2021-10-19 15:51:25', 1);
INSERT INTO `sys_file` VALUES (394, 'OgIbJZuqTPTRuZ8CGy0jIyGeouWUS4OZV2q', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 15:51:25', NULL, '2021-10-19 15:51:25', 1);
INSERT INTO `sys_file` VALUES (395, 'OAo5qchOVFhprZewMKOSaXkSAnCBz0HNr', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 15:55:43', NULL, '2021-10-19 15:55:43', 1);
INSERT INTO `sys_file` VALUES (396, 'i7YAAmhI3UkudEqT9hyefuZzEJLIkua40Fv', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 15:55:44', NULL, '2021-10-19 15:55:44', 1);
INSERT INTO `sys_file` VALUES (397, 'qDLXruxZxcLuMULvhDl0CnrEaZP3iwBMzkoMD', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:04:04', NULL, '2021-10-19 16:04:04', 1);
INSERT INTO `sys_file` VALUES (398, '7T8azSKkmonbq4s7AX6RJgMqUbZTAXt5nHl', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:04:05', NULL, '2021-10-19 16:04:05', 1);
INSERT INTO `sys_file` VALUES (399, 'qVZJUKatgqCE32bOCpdceOzC1A45UyULXBDSC4', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:04:06', NULL, '2021-10-19 16:04:06', 1);
INSERT INTO `sys_file` VALUES (400, 'J9VSUZgo8NezJXNUWmc5zpXkr1dIDaAIymoqw9Q', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:04:07', NULL, '2021-10-19 16:04:07', 1);
INSERT INTO `sys_file` VALUES (401, 'qVZJUKatgqCE32bOCpdceOzC1A45UyULXBDSC4', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:04:07', NULL, '2021-10-19 16:04:07', 1);
INSERT INTO `sys_file` VALUES (402, 'p9MDwdbxNy1gxeVLcTuCovpapoqAA8nglhX6gw', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:13:17', NULL, '2021-10-19 16:13:17', 1);
INSERT INTO `sys_file` VALUES (403, 'NV2jDRrqIidz4F3dOSZpURAuUlDGM3LXPhC4c', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:13:17', NULL, '2021-10-19 16:13:17', 1);
INSERT INTO `sys_file` VALUES (404, '6zijelT324Zq1EPAenBngJLcsDSxdmdh2F3dnZ1', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 16:14:26', NULL, '2021-10-19 16:14:26', 1);
INSERT INTO `sys_file` VALUES (405, 'ZleDag6DgxRTugIVg5t9VUAq6p4gvoCgWybWWtt', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-19 16:14:26', NULL, '2021-10-19 16:14:26', 1);
INSERT INTO `sys_file` VALUES (406, 'Pu5OFcYXDIkHC0nfcHErVpYLoGCPe937', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:32:44', NULL, '2021-10-19 16:32:44', 1);
INSERT INTO `sys_file` VALUES (407, 'HjFnykxOOfaDgnqkZhy9BNAaDr2uax6GjW1k9F', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:32:44', NULL, '2021-10-19 16:32:44', 1);
INSERT INTO `sys_file` VALUES (408, 'NYxsZ840uTZRRsdERX3U9DA5P8yUYOB4Skph1j2', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:41:52', NULL, '2021-10-19 16:41:52', 1);
INSERT INTO `sys_file` VALUES (409, 'PW19ae0JGptGDVP5ZAJZUAvDthyEll7KeRCjsRJ', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:41:52', NULL, '2021-10-19 16:41:52', 1);
INSERT INTO `sys_file` VALUES (410, 'Q3Ryo78WfhEg9NZmF5SZGPZNDVs5Oi6DNgPwg', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:42:54', NULL, '2021-10-19 16:42:54', 1);
INSERT INTO `sys_file` VALUES (411, '7DCidAE1P4XHg52mbYFHYDqF2KG412JukB', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:42:54', NULL, '2021-10-19 16:42:54', 1);
INSERT INTO `sys_file` VALUES (412, 'rzOSfOgeuRCcNVsEsa598XHulgBx89C7f8', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-19 16:46:41', NULL, '2021-10-19 16:46:41', 1);
INSERT INTO `sys_file` VALUES (413, 'dfNOMLESXBJN2yzHBm89yFrfht8GN69Wny7FS', 'counter', NULL, NULL, '6fd79f92dd1ee26c7a2bf17e908675be', 396737, NULL, '2021-10-19 16:46:41', NULL, '2021-10-19 16:46:41', 1);
INSERT INTO `sys_file` VALUES (414, 'NCo9X6qkXXg4jClRbfCrila3TcrU7sO7', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:49:35', NULL, '2021-10-19 16:49:35', 1);
INSERT INTO `sys_file` VALUES (415, 'CPNMh5yrRK3sgObmyZb6BWqLWqjt4fg1RrUVO', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 16:49:35', NULL, '2021-10-19 16:49:35', 1);
INSERT INTO `sys_file` VALUES (416, 'BwRG6Ukw9MrqrwAvNFF2kf2K4R2437VDR4FIiB', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:03:23', NULL, '2021-10-19 18:03:23', 1);
INSERT INTO `sys_file` VALUES (417, 'BQrFtSUel4CYwQL6F4bCrVcxBCEZtj0ZUw6CM', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:03:24', NULL, '2021-10-19 18:03:24', 1);
INSERT INTO `sys_file` VALUES (418, '73cSPg9IPune7oM4EV5z8az7fPD2BTRmcDTUCeC', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:24:12', NULL, '2021-10-19 18:24:12', 1);
INSERT INTO `sys_file` VALUES (419, 'llkZkb9klLytBfPKqJ5SnsX9ObMJCjrCF', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:24:12', NULL, '2021-10-19 18:24:12', 1);
INSERT INTO `sys_file` VALUES (420, 'ZCgjfmGODW3mMCvC6wJFi70NivhP4QIfh', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:31:07', NULL, '2021-10-19 18:31:07', 1);
INSERT INTO `sys_file` VALUES (421, '5CR5HRo1gOT6yLfmuyMd3fSR2xHlvsZaA7uzuW', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:31:07', NULL, '2021-10-19 18:31:07', 1);
INSERT INTO `sys_file` VALUES (422, 'OXA5BQa8MXDcHtMDjToRN3hKlQLQeFrZdamH0Eq', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:35:26', NULL, '2021-10-19 18:35:26', 1);
INSERT INTO `sys_file` VALUES (423, 'LXDFwVVZaSFeIyc0ysfsJFeWdEd8bhARAN1g75f', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:35:26', NULL, '2021-10-19 18:35:26', 1);
INSERT INTO `sys_file` VALUES (424, 'NTsVYOPykU75688q0aAe9P4Y1ZmJ8BqZ3mP8aH', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:38:40', NULL, '2021-10-19 18:38:40', 1);
INSERT INTO `sys_file` VALUES (425, 'NTsVYOPykU75688q0aAe9P4Y1ZmJ8BqZ3mP8aH', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:38:41', NULL, '2021-10-19 18:38:41', 1);
INSERT INTO `sys_file` VALUES (426, 'lKcfX19JBinNz30NuDb1KX1lA4WNjbUEi', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:38:42', NULL, '2021-10-19 18:38:42', 1);
INSERT INTO `sys_file` VALUES (427, 'IiIO0lTxiZbQ748ClDcmY5Aox308kFJ1Hmv', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:38:42', NULL, '2021-10-19 18:38:42', 1);
INSERT INTO `sys_file` VALUES (428, 'pR4ldI8hnWbJiO6eQSrlAeI1KPdhWq53t1kp3', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:50:14', NULL, '2021-10-19 18:50:14', 1);
INSERT INTO `sys_file` VALUES (429, 'ABN42WDs7l1h6FizeKeNA1yXxm9lisNKgURpk', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:50:15', NULL, '2021-10-19 18:50:15', 1);
INSERT INTO `sys_file` VALUES (430, 'tzYQ13UQP0iOFkJnhqXmMwo5vl5YVp6Fw3', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:52:15', NULL, '2021-10-19 18:52:15', 1);
INSERT INTO `sys_file` VALUES (431, 'C13gmfHRS6ZhujCyDPZPhG5SyKRWQJo8cb', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:52:15', NULL, '2021-10-19 18:52:15', 1);
INSERT INTO `sys_file` VALUES (432, 'PGlncFssSRwQHW7TWjimF8Yx7H9xljOi28qL', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:54:16', NULL, '2021-10-19 18:54:16', 1);
INSERT INTO `sys_file` VALUES (433, 'Gv0JvIkkXsmNy2h2WacOuhvm8yNc4v9ps9O', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 18:54:17', NULL, '2021-10-19 18:54:17', 1);
INSERT INTO `sys_file` VALUES (434, 'SGDPaH74EMglAkXWT1tNSM9Oo8PEzjKGqHkVH', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:01:30', NULL, '2021-10-19 19:01:30', 1);
INSERT INTO `sys_file` VALUES (435, 'E4zDKHzYlA1xbmdxcPRNHV86JA0myKDtHQ7oUh', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:01:30', NULL, '2021-10-19 19:01:30', 1);
INSERT INTO `sys_file` VALUES (436, 'lysEOAmq2Joflf4CxX9k5A0HOKhDnz5LNMWK', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:10:27', NULL, '2021-10-19 19:10:27', 1);
INSERT INTO `sys_file` VALUES (437, '0bISsCOny71mSAhNYtvLbMbDkF98MoSZpil5I3', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:10:28', NULL, '2021-10-19 19:10:28', 1);
INSERT INTO `sys_file` VALUES (438, 'FXoeljVYhOI1YU5pH5PPfOQ6uNEsBXEdA8k', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:12:57', NULL, '2021-10-19 19:12:57', 1);
INSERT INTO `sys_file` VALUES (439, 'EYGZJhJnwd4AHy7YByECFYCxHOfMTxPDq8', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:12:57', NULL, '2021-10-19 19:12:57', 1);
INSERT INTO `sys_file` VALUES (440, 'O7SokIE05MkFLRpH7chFVWJOS1Rvbxyf', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:18:29', NULL, '2021-10-19 19:18:29', 1);
INSERT INTO `sys_file` VALUES (441, '7NNBoi1WPclIXz8n7QJyuMEmLLh6rAIu9r1', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:18:30', NULL, '2021-10-19 19:18:30', 1);
INSERT INTO `sys_file` VALUES (442, 'Q3aKK9sKaJHJfRQnbwFiYqludUNdOBCg', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:22:03', NULL, '2021-10-19 19:22:03', 1);
INSERT INTO `sys_file` VALUES (443, 'y0UkuEnbU6ShH1UhoWOq7geNP6JXwtO9q', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:22:03', NULL, '2021-10-19 19:22:03', 1);
INSERT INTO `sys_file` VALUES (444, 'qNt25YWuCGdLtuSLJG9trDenJZQaEHQ3EpMIqVA', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:22:42', NULL, '2021-10-19 19:22:42', 1);
INSERT INTO `sys_file` VALUES (445, '1ByECC31OrfpZTAgHnKv8XCOCydtzoUyJQ', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:22:42', NULL, '2021-10-19 19:22:42', 1);
INSERT INTO `sys_file` VALUES (446, 'JQKE3GSYkhAT9C9q65FqmhCSXtafMtbJukeT', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:26:49', NULL, '2021-10-19 19:26:49', 1);
INSERT INTO `sys_file` VALUES (447, 'yBsKMaicXjvZ38MikQuLGjSRLCop3kYTIe4ONi', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:26:49', NULL, '2021-10-19 19:26:49', 1);
INSERT INTO `sys_file` VALUES (448, 'dquEi5gTfubvl5jXNt2EmA9k7rgmSHjeGMWs2g', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:33:10', NULL, '2021-10-19 19:33:10', 1);
INSERT INTO `sys_file` VALUES (449, 'br6MoON4VBoqs61XdFgJzkuB9ySOoaRHsLLPiRc', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:33:10', NULL, '2021-10-19 19:33:10', 1);
INSERT INTO `sys_file` VALUES (450, '1ymJ1e8S2aTFjCygJCP9hpI2ZqnhnMiODO2gfqk', 'counter', NULL, NULL, '3d3402fbc48f50e6eea8b39b16505815', 1289, NULL, '2021-10-19 19:33:25', NULL, '2021-10-19 19:33:25', 1);
INSERT INTO `sys_file` VALUES (451, 'vSGzYDRh1ns9xIL3H6DOvoeuWJJ0MRapEii', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:36:47', NULL, '2021-10-19 19:36:47', 1);
INSERT INTO `sys_file` VALUES (452, 'mioJ0deSZUs62RUUa3XgESlAFSQ0gnWPvfJxaHr', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:36:47', NULL, '2021-10-19 19:36:47', 1);
INSERT INTO `sys_file` VALUES (453, 'FS7aprjGXXLqEv3vdhZPU3epB0s6BrbA1TUZz', 'counter', NULL, NULL, '91aacc6caafff1264a4544d62bcb2ad7', 1553, NULL, '2021-10-19 19:37:00', NULL, '2021-10-19 19:37:00', 1);
INSERT INTO `sys_file` VALUES (454, 'vTj3M78b88aXJiVG8sAfymKIGSIkIdXAZNfWO7', 'counter', NULL, NULL, '20d8a0e3d61120fbbb31cba77ebb7858', 1427, NULL, '2021-10-19 19:43:14', NULL, '2021-10-19 19:43:14', 1);
INSERT INTO `sys_file` VALUES (455, 'SunyavCuXel0PwFaqzq7l5C93wkLIjtdfJC4', 'counter', NULL, NULL, 'ddc76138841ffd7f0fca62e4fada1488', 1331, NULL, '2021-10-19 19:43:59', NULL, '2021-10-19 19:43:59', 1);
INSERT INTO `sys_file` VALUES (456, 'DeLAiFBUtUuD4sTXER2wvOLFK0hGDcUs0i5', 'counter', NULL, NULL, 'dedb76ec4a29dc7ded1dd0680f528f9b', 1395, NULL, '2021-10-19 19:44:46', NULL, '2021-10-19 19:44:46', 1);
INSERT INTO `sys_file` VALUES (457, '0Mrq9FDsJ1wfJufYUSv2TBG9anIo64A1j2LdWy0', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:47:23', NULL, '2021-10-19 19:47:23', 1);
INSERT INTO `sys_file` VALUES (458, 'ntACC4kAU5WrzHVewX2twDlwnhgbjUmU', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-19 19:47:23', NULL, '2021-10-19 19:47:23', 1);
INSERT INTO `sys_file` VALUES (459, 'FI3Pd6Je2dNoR2QCwZyiMfQvL7tlYaYfV', 'counter', NULL, NULL, 'ff8500b9c7e24d833c23e6c0125c2bd6', 1342, NULL, '2021-10-19 19:49:50', NULL, '2021-10-19 19:49:50', 1);
INSERT INTO `sys_file` VALUES (460, '6BUUx7Ehg204LhMJBXcbnLEP6muw6S3Oztpm', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:32:48', NULL, '2021-10-20 08:32:48', 1);
INSERT INTO `sys_file` VALUES (461, 'wnrMycea9m6pDOKhIQvBqAJuVmX3czwr', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:32:48', NULL, '2021-10-20 08:32:48', 1);
INSERT INTO `sys_file` VALUES (462, 'l9a5AS6EXiBe2u07WVzoPcPtdk2SjWMDfDS', 'counter', NULL, NULL, '8fcfc471e661c74159c81e5a6e2112be', 1770, NULL, '2021-10-20 08:33:28', NULL, '2021-10-20 08:33:28', 1);
INSERT INTO `sys_file` VALUES (463, 'ShgZO5rlIW5LZVc8gu7nMq8CUWhYruuPJW', 'counter', NULL, NULL, '575427002eb3b22e57be8625c48a5ea8', 1465, NULL, '2021-10-20 08:34:25', NULL, '2021-10-20 08:34:25', 1);
INSERT INTO `sys_file` VALUES (464, '2PVQyZDbTDcKuDvnoNgOEwr5o5sgkZttSH31u59', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:41:12', NULL, '2021-10-20 08:41:12', 1);
INSERT INTO `sys_file` VALUES (465, 'CHVhO6ErdNA7YW0wDIWznNyPZyFnAGvmhwoQTXj', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:41:12', NULL, '2021-10-20 08:41:12', 1);
INSERT INTO `sys_file` VALUES (466, 's0MBN2RtGk3n5i3Xp7utGS7zbQDmOmn3JZR', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:41:13', NULL, '2021-10-20 08:41:13', 1);
INSERT INTO `sys_file` VALUES (467, 'vDB0lMHEtzD329b0yHEn27ChAs2fwoZIO3v', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:41:13', NULL, '2021-10-20 08:41:13', 1);
INSERT INTO `sys_file` VALUES (468, 'khmR7HYGf8EDSAvocRiwUsBAona6AduJ0ABHr', 'counter', NULL, NULL, '1c78fc4004de7cc140a4da249d0a7f74', 2045, NULL, '2021-10-20 08:41:31', NULL, '2021-10-20 08:41:31', 1);
INSERT INTO `sys_file` VALUES (469, 'QlBXafpY4wHe3KHflCkTmZfpfnnHho4BjCFy', 'counter', NULL, NULL, '3092ac0d5a5c76a02dfb8468db887b6c', 1496, NULL, '2021-10-20 08:44:40', NULL, '2021-10-20 08:44:40', 1);
INSERT INTO `sys_file` VALUES (470, 'e4qU20RbKjh28duWrA2mpdywA3OZQT4EIWj', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:53:50', NULL, '2021-10-20 08:53:50', 1);
INSERT INTO `sys_file` VALUES (471, '1fZtkOjXAWRygINBJU2DRBoLvpEwVPm5', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:53:50', NULL, '2021-10-20 08:53:50', 1);
INSERT INTO `sys_file` VALUES (472, 'SxcbDqQEYELQq63BEfEZlP0kAxfVkx0g6', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:57:39', NULL, '2021-10-20 08:57:39', 1);
INSERT INTO `sys_file` VALUES (473, 'SDDqLVQ3Ni2TV3wzOeDdAY1XJHzmcix94Jl', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 08:57:40', NULL, '2021-10-20 08:57:40', 1);
INSERT INTO `sys_file` VALUES (474, 'MLbRLitfhns1tiHg3FoxKaPg5368uvib', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 09:02:37', NULL, '2021-10-20 09:02:37', 1);
INSERT INTO `sys_file` VALUES (475, 'Pd5Fn8R47bR88kzSizKTtceWJLaoHqRcr7LAs', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 09:02:37', NULL, '2021-10-20 09:02:37', 1);
INSERT INTO `sys_file` VALUES (476, 'evJ1XHrtG5kP87bjYfuuLWLpcXWoLNvzwK0', 'counter', NULL, NULL, '69b8f4bc8d29eb0b3d3a1c6ca66ba663', 1666, NULL, '2021-10-20 09:30:38', NULL, '2021-10-20 09:30:38', 1);
INSERT INTO `sys_file` VALUES (477, 'EM4qzof45aNeLwL3bsKRZc0aofyRYa81', 'counter', NULL, NULL, 'ad418828d7c80200eb92e99ea036084d', 976, NULL, '2021-10-20 09:34:15', NULL, '2021-10-20 09:34:15', 1);
INSERT INTO `sys_file` VALUES (478, 'FinlJMnRYLBx35BNbQSKWNoRzQctht34m', 'counter', NULL, NULL, '320767b4b99fbaa2166c44d7c9111173', 823, NULL, '2021-10-20 09:34:45', NULL, '2021-10-20 09:34:45', 1);
INSERT INTO `sys_file` VALUES (479, '26wHzEv90QQp0u8KWwmqaKgiirRMgj7k6', 'counter', NULL, NULL, '1a798495bee859ed10af9f0c9e9a9017', 1218, NULL, '2021-10-20 09:36:22', NULL, '2021-10-20 09:36:22', 1);
INSERT INTO `sys_file` VALUES (480, 'awRYzQFPvOiPjQSyq8b1RJK0Rwdl8EZ30jW', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 09:44:30', NULL, '2021-10-20 09:44:30', 1);
INSERT INTO `sys_file` VALUES (481, 'sbGMCjHkwLvocm0lk122iL044qgdpZ0GfYO0IVT', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 09:44:31', NULL, '2021-10-20 09:44:31', 1);
INSERT INTO `sys_file` VALUES (482, 'Mvgt8zhutCgUo6QsLvKnr4FPjHQwXhXpW', 'counter', NULL, NULL, '580678a4eee058464d42ffe2097aae4f', 1078, NULL, '2021-10-20 09:44:54', NULL, '2021-10-20 09:44:54', 1);
INSERT INTO `sys_file` VALUES (483, 'W1cnaNGIaJHpE3PdH2iGelIFq339nyhblC', 'counter', NULL, NULL, '580678a4eee058464d42ffe2097aae4f', 1078, NULL, '2021-10-20 09:44:54', NULL, '2021-10-20 09:44:54', 1);
INSERT INTO `sys_file` VALUES (484, 'h6BlTgUJqDVnoeeQEQgpuUs8wbW6QcOlNHZeqjs', 'counter', NULL, NULL, 'ce826856796ddd0d249942395e048396', 805, NULL, '2021-10-20 09:47:19', NULL, '2021-10-20 09:47:19', 1);
INSERT INTO `sys_file` VALUES (485, 'wJufTw5bL6KDcUvNtr1DwOedxPXePR7MWI', 'counter', NULL, NULL, '28e6a61eb4e6c195bb43f7b385cf7854', 1101, NULL, '2021-10-20 09:50:02', NULL, '2021-10-20 09:50:02', 1);
INSERT INTO `sys_file` VALUES (486, 'VMToOJnoVo6bwKHiu59j7XkyV7Yf6ntEYlOyWk', 'counter', NULL, NULL, 'ef1b96a1ee6e21782f862bd586d417bc', 1157, NULL, '2021-10-20 09:53:27', NULL, '2021-10-20 09:53:27', 1);
INSERT INTO `sys_file` VALUES (487, '2dpg99avBvfHnw9kwCPMtazh8Ulm8HewPh', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:02:06', NULL, '2021-10-20 10:02:06', 1);
INSERT INTO `sys_file` VALUES (488, 'Muk3eQYwPzqqex7M8SbpRShRqvWZxQC0hUDq', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:02:06', NULL, '2021-10-20 10:02:06', 1);
INSERT INTO `sys_file` VALUES (489, 'FrUaOqXOrByoqy8RDRu5j0wkY3MYHp8VPP', 'counter', NULL, NULL, 'e807203d103ce5b6b8aaa14bc608e4c8', 1673, NULL, '2021-10-20 10:02:29', NULL, '2021-10-20 10:02:29', 1);
INSERT INTO `sys_file` VALUES (490, 'cHwrcvzfX8u3He1wQTKgw88xyWOX2wwsi8y2xC', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:02:33', NULL, '2021-10-20 10:02:33', 1);
INSERT INTO `sys_file` VALUES (491, 'UkQNU4Hxu0ruv0tLFNQvLAKMpAvVeUEbIT', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:02:33', NULL, '2021-10-20 10:02:33', 1);
INSERT INTO `sys_file` VALUES (492, 'GT6nGdCG15rf4GAT9JrEaZ9INehJ38aILfpP', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:03:40', NULL, '2021-10-20 10:03:40', 1);
INSERT INTO `sys_file` VALUES (493, 'dSeI9UeUQqnbEWPOoJa6Vlo7OH1LQoNzPOZP', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:03:41', NULL, '2021-10-20 10:03:41', 1);
INSERT INTO `sys_file` VALUES (494, 'zyZOLxf1JeyvP3FdpUflmdVUjH9zXt4t', 'counter', NULL, NULL, 'cef58fa6e1b0ad50622e5925a5b44870', 2225, NULL, '2021-10-20 10:07:32', NULL, '2021-10-20 10:07:32', 1);
INSERT INTO `sys_file` VALUES (495, 'h7dmb1lnSk4WMw84HlFY5KQtE4bRfmQIuqbTYpG', 'counter', NULL, NULL, '9b2bfd40bdb553c121c3a5c776bba91e', 3275, NULL, '2021-10-20 10:07:48', NULL, '2021-10-20 10:07:48', 1);
INSERT INTO `sys_file` VALUES (496, 'tBPJIOFFjLnrMaBZXGhEPB6FHBl9BHQjkmh6QBs', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:16:33', NULL, '2021-10-20 10:16:33', 1);
INSERT INTO `sys_file` VALUES (497, 'JaYk2jErT6WnjpyxsGfLc7M5OKqGdi5MK5dWFw', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:16:33', NULL, '2021-10-20 10:16:33', 1);
INSERT INTO `sys_file` VALUES (498, 'Ra80ebo4Wy5LbS0MWQHHyt8EUVGY9eZNLVmGPt5', 'counter', NULL, NULL, 'f54aebdbb1571836a9836a224ecdb61b', 1325, NULL, '2021-10-20 10:20:59', NULL, '2021-10-20 10:20:59', 1);
INSERT INTO `sys_file` VALUES (499, 'JiHBjDtX7S4OqJiDhkwRJWES5hjZldG6Z3z', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:20', NULL, '2021-10-20 10:23:20', 1);
INSERT INTO `sys_file` VALUES (500, 'mfQDEwjh25mfyqalFrd8dvz6ECMyBxQ6', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:21', NULL, '2021-10-20 10:23:21', 1);
INSERT INTO `sys_file` VALUES (501, 'mfQDEwjh25mfyqalFrd8dvz6ECMyBxQ6', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:22', NULL, '2021-10-20 10:23:22', 1);
INSERT INTO `sys_file` VALUES (502, 'oG8qdL8J01j8oaXUBozcudKPWJpmJfKEU', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:22', NULL, '2021-10-20 10:23:22', 1);
INSERT INTO `sys_file` VALUES (503, 'mfQDEwjh25mfyqalFrd8dvz6ECMyBxQ6', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:23', NULL, '2021-10-20 10:23:23', 1);
INSERT INTO `sys_file` VALUES (504, 'zVPV8wJgZ4aEhVJcDgpjMM6mzSrDg2F1a', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:29', NULL, '2021-10-20 10:23:29', 1);
INSERT INTO `sys_file` VALUES (505, 'f7qghBPWTTVD3XJS51L9NuacOKebPO1bdxvdcw', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:29', NULL, '2021-10-20 10:23:29', 1);
INSERT INTO `sys_file` VALUES (506, 'eEs1ASN6zgYso4Z6eZYpZ96AxFlEBIlAjL', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:23:29', NULL, '2021-10-20 10:23:29', 1);
INSERT INTO `sys_file` VALUES (507, 'R3QniUCOPlGHPL3o6amdDwAGhkue79RApW', 'counter', NULL, NULL, '65190db49c4e9e91349c3dec3409b78d', 1872, NULL, '2021-10-20 10:23:44', NULL, '2021-10-20 10:23:44', 1);
INSERT INTO `sys_file` VALUES (508, 'NGdU97Ljf3bWFV7r5otJ5TRISSDffQtcXMD', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:48:10', NULL, '2021-10-20 10:48:10', 1);
INSERT INTO `sys_file` VALUES (509, 'PX0afnXPH6lnSr5vqiNVrwaXskbjJfeQK6L', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 10:48:11', NULL, '2021-10-20 10:48:11', 1);
INSERT INTO `sys_file` VALUES (510, 'fvLK4xhpdtELBptdsU4KOes7yw5z5B3xzP4z', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:55:43', NULL, '2021-10-20 10:55:43', 1);
INSERT INTO `sys_file` VALUES (511, 'MwK6mIV4fGSfBHXtfJjk1joGS7O2BCYbN2Vvy1', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 10:55:43', NULL, '2021-10-20 10:55:43', 1);
INSERT INTO `sys_file` VALUES (512, 'Ngvj2LX7eS4AOhCR68AL9LroejXNWKgrMeRC9', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:42:39', NULL, '2021-10-20 11:42:39', 1);
INSERT INTO `sys_file` VALUES (513, 'hvkcHXk6L4TEjFcDclFuwevQjiQQfYPWleCDNm', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:42:39', NULL, '2021-10-20 11:42:39', 1);
INSERT INTO `sys_file` VALUES (514, '9Ms7UJRQ5RMrpEXtnmCe3RgKADmA7clh', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:46:59', NULL, '2021-10-20 11:46:59', 1);
INSERT INTO `sys_file` VALUES (515, 'EvYTeUqWdsInFVVYyvQoXIjpKSOdPsok8N', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:46:59', NULL, '2021-10-20 11:46:59', 1);
INSERT INTO `sys_file` VALUES (516, 'MW0UNrSRzyyife0SRNkFNBNEFJkuYV79aEVaoq', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:49:19', NULL, '2021-10-20 11:49:19', 1);
INSERT INTO `sys_file` VALUES (517, 'm9sKe8lca5IfeAGApldb5sbBYWhALW8Irohi', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 11:49:19', NULL, '2021-10-20 11:49:19', 1);
INSERT INTO `sys_file` VALUES (518, 'LMd4Fh2PDBC5KKZNqGYsh6lVmPDFQdue', 'counter', NULL, NULL, '2869541624637f399a0811590777b6d0', 1960, NULL, '2021-10-20 11:49:51', NULL, '2021-10-20 11:49:51', 1);
INSERT INTO `sys_file` VALUES (519, 'JEwnYt0pXel469vUVbsaYgBo8TC9k1A9hU', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 13:16:55', NULL, '2021-10-20 13:16:55', 1);
INSERT INTO `sys_file` VALUES (520, 'gjLzToOuWZuBWzEHYR5Z0wdyjInQefxtsn94uvC', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 13:16:55', NULL, '2021-10-20 13:16:55', 1);
INSERT INTO `sys_file` VALUES (521, 'DfzkFjo9hcJ4SEjvgj2qlIDq1PK7OFEl6bBbiwK', 'counter', NULL, NULL, '816e42789c14d75c044af785e8244674', 1785, NULL, '2021-10-20 13:17:14', NULL, '2021-10-20 13:17:14', 1);
INSERT INTO `sys_file` VALUES (522, 'jdXRGR6a7MBQJicj3cRxDj4a3Lv0J5QgzxpFG', 'counter', NULL, NULL, '72da13f6ac011bd0181d8fdd8ed39288', 1462, NULL, '2021-10-20 13:21:08', NULL, '2021-10-20 13:21:08', 1);
INSERT INTO `sys_file` VALUES (523, 'lM5eK1pww9FAXRIi3rGei4NWdtLa55KkqaA', 'counter', NULL, NULL, '147116eb3897a64e91e972bc8e3fabfb', 1761, NULL, '2021-10-20 13:21:48', NULL, '2021-10-20 13:21:48', 1);
INSERT INTO `sys_file` VALUES (524, 'IKD411EocRS3rdPJQ8H6Yx9ARVXyRgVo', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 13:31:52', NULL, '2021-10-20 13:31:52', 1);
INSERT INTO `sys_file` VALUES (525, 'nFFQW2qrdumwtxxiRMrMxPFYxtZ7pmu2E', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 13:31:53', NULL, '2021-10-20 13:31:53', 1);
INSERT INTO `sys_file` VALUES (526, 'jzZkdROoC3VNLS7VZlvKdHhXQxPF19T8', 'counter', NULL, NULL, 'af4f551ed40835069089c372aa35e07b', 1459, NULL, '2021-10-20 13:32:00', NULL, '2021-10-20 13:32:00', 1);
INSERT INTO `sys_file` VALUES (527, 'lsUrFGcqC23cSAg9d89cHXJPvZIuN1jpj', 'counter', NULL, NULL, 'e1fed4f8eeb92cfbbb858fa621ed18bd', 1425, NULL, '2021-10-20 13:32:06', NULL, '2021-10-20 13:32:06', 1);
INSERT INTO `sys_file` VALUES (528, 'tVNnKghQ9DPAbViVvFVWgbKOjxmD5vE0Ks', 'counter', NULL, NULL, 'acf15e62010902befff9a39bf9984fc2', 1795, NULL, '2021-10-20 13:33:32', NULL, '2021-10-20 13:33:32', 1);
INSERT INTO `sys_file` VALUES (529, 'FhSj5vpnLkBLwU1mJVULJT8jHnKMjYdg7W', 'counter', NULL, NULL, 'c201e199fe4d239058ded84628202917', 2667, NULL, '2021-10-20 13:40:49', NULL, '2021-10-20 13:40:49', 1);
INSERT INTO `sys_file` VALUES (530, '0CViamiscZnEMkYbGX2sZNst10bwHlDbT', 'counter', NULL, NULL, '7b99619747ba3c104d0b4d6026e553fd', 4010, NULL, '2021-10-20 13:58:01', NULL, '2021-10-20 13:58:01', 1);
INSERT INTO `sys_file` VALUES (531, 'gWwRzIRPNWFaXgYkDhbXigxuR1PeMmrXr0Kam', 'counter', NULL, NULL, 'da565c7a3de303b390d8bbd6b6461755', 1292, NULL, '2021-10-20 14:02:18', NULL, '2021-10-20 14:02:18', 1);
INSERT INTO `sys_file` VALUES (532, 'xSxKHkJIU678uxVY8vQpk3Ow1Lr1ZfzQ3nVoOP', 'counter', NULL, NULL, 'b0f50add5665d349d08457827e657f61', 3394, NULL, '2021-10-20 14:07:01', NULL, '2021-10-20 14:07:01', 1);
INSERT INTO `sys_file` VALUES (533, 'eZrwnkNIbF0hD4AHPtxj8kvb78MKPkBp1', 'counter', NULL, NULL, 'a35100332f8c2b26d1c7711133d16cca', 1903, NULL, '2021-10-20 14:11:42', NULL, '2021-10-20 14:11:42', 1);
INSERT INTO `sys_file` VALUES (534, 'i0Kdbqg2J0cmoPr8tu2DkWCc9rIS64DMMsX2', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 14:16:04', NULL, '2021-10-20 14:16:04', 1);
INSERT INTO `sys_file` VALUES (535, '6Q80neOUhZiN9bIrLHLW3wYLOXLXJeq4yH2', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 14:16:04', NULL, '2021-10-20 14:16:04', 1);
INSERT INTO `sys_file` VALUES (536, 'z1URvrCPbh6FE2CaG4FcrR14jEtCnJZHXKNkQ', 'counter', NULL, NULL, 'c4b778e51ee91bdd9cea0fb740589b9', 1093, NULL, '2021-10-20 14:16:14', NULL, '2021-10-20 14:16:14', 1);
INSERT INTO `sys_file` VALUES (537, 'ajVRg11mKSFpg75HTWr5sb8zNntrXrzu', 'counter', NULL, NULL, 'a86b82a7385e33b6f46daf26ae27db97', 861, NULL, '2021-10-20 14:16:42', NULL, '2021-10-20 14:16:42', 1);
INSERT INTO `sys_file` VALUES (538, 'eooUc2LLOOsoXB37rgbfi7NIYOb4wfmmo2FqH', 'counter', NULL, NULL, 'da6781ebb26cbd0c6e030c823ff63b6b', 1154, NULL, '2021-10-20 14:25:39', NULL, '2021-10-20 14:25:39', 1);
INSERT INTO `sys_file` VALUES (539, 'epUJ31jrVi0BfoyU5YTPkeDs16T5EaNCT', 'counter', NULL, NULL, 'da6781ebb26cbd0c6e030c823ff63b6b', 1154, NULL, '2021-10-20 14:25:40', NULL, '2021-10-20 14:25:40', 1);
INSERT INTO `sys_file` VALUES (540, '1ByYTKGQ0lwqG7Y5ZpvXrnj49iZkuZh4Y7', 'counter', NULL, NULL, 'aca97fde7fb181a0490a1cbd40309020', 1072, NULL, '2021-10-20 14:26:29', NULL, '2021-10-20 14:26:29', 1);
INSERT INTO `sys_file` VALUES (541, 'K5ohioaq9zL0BBgbbbdOGTt78DrvznqVEJsi', 'counter', NULL, NULL, '9df6c74bae6197dd02f00a0daaba642f', 1322, NULL, '2021-10-20 14:29:09', NULL, '2021-10-20 14:29:09', 1);
INSERT INTO `sys_file` VALUES (542, 'gG1PlwCbTPzD0qWSuqfVIvfkMdhtmgtDQK', 'counter', NULL, NULL, '5a0662a4476331bb94081d4afb89ecf5', 2195, NULL, '2021-10-20 15:04:55', NULL, '2021-10-20 15:04:55', 1);
INSERT INTO `sys_file` VALUES (543, 'bE8Q3rCsbPGLDf2XYERbyxtXRBcPOQNqM0W', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:10:33', NULL, '2021-10-20 15:10:33', 1);
INSERT INTO `sys_file` VALUES (544, 'uiFjIrEQhM1NSJUsi64Ed6UWAgQjuaT6bQSuFhG', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:10:34', NULL, '2021-10-20 15:10:34', 1);
INSERT INTO `sys_file` VALUES (545, 'iPCgqWj0uJSzk82KmmqwLqsQTdqc6djc0gx', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:10:49', NULL, '2021-10-20 15:10:49', 1);
INSERT INTO `sys_file` VALUES (546, 'hQrqwdJ9ZFI5Ooy79yFEhSvCDpXGUuN9P', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:12:04', NULL, '2021-10-20 15:12:04', 1);
INSERT INTO `sys_file` VALUES (547, 'rLG32wM9hAzjApNB2VrVFxOpBabv3aLYr', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:13:25', NULL, '2021-10-20 15:13:25', 1);
INSERT INTO `sys_file` VALUES (548, '5pUnpfESG65u9OLhfPW5lHrJPoHoX6W4Pv', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:13:58', NULL, '2021-10-20 15:13:58', 1);
INSERT INTO `sys_file` VALUES (549, 'NjdgqgRo3Ra0Sgg5ivXU7roS63TCLF6Bx3js', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:14:21', NULL, '2021-10-20 15:14:21', 1);
INSERT INTO `sys_file` VALUES (550, 'tNjqYD9z2AYJ5eYw2QmdBcvP9noN7kZluP', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:14:33', NULL, '2021-10-20 15:14:33', 1);
INSERT INTO `sys_file` VALUES (551, 'QWFp4ESWure2lwWivTnBlfnxd0D5fl8wP', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:14:34', NULL, '2021-10-20 15:14:34', 1);
INSERT INTO `sys_file` VALUES (552, 'OH5JdxLajDS0bPFwYe8qTVuoDBHgOGvzFca', 'counter', NULL, NULL, '87ba7d579401342fa424258dc534eb5d', 1536, NULL, '2021-10-20 15:14:35', NULL, '2021-10-20 15:14:35', 1);
INSERT INTO `sys_file` VALUES (553, '5Kd9xOfg0wrZfluUgdkMGh88oSrdRASesS', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 15:32:57', NULL, '2021-10-20 15:32:57', 1);
INSERT INTO `sys_file` VALUES (554, 'DOyLASjEvf2rH3H53z8S5CAztzxjvwVTe', 'counter', NULL, NULL, 'c36fa3a5e1e78c9010fc93f8a5d514f7', 356485, NULL, '2021-10-20 15:32:57', NULL, '2021-10-20 15:32:57', 1);
INSERT INTO `sys_file` VALUES (555, 'gY8j2Q9KXDzcG78kE4WEgMSaUAspENreaHP', 'counter', NULL, NULL, 'b69e1b818c54c8bd51418f3caf1b5784', 6560, NULL, '2021-10-20 15:33:18', NULL, '2021-10-20 15:33:18', 1);
INSERT INTO `sys_file` VALUES (556, 'bawnVWYFEwE1PplhT6CLpYETtApMlTStgt3ozD1', 'counter', NULL, NULL, 'ddaaf6cecee6f20d0493767f5cc1ea5d', 3137, NULL, '2021-10-20 15:35:14', NULL, '2021-10-20 15:35:14', 1);
INSERT INTO `sys_file` VALUES (557, 'nCElWQTmjWT8f6G0IeDBeVwm2G8jNdDduy7A0Wg', 'counter', NULL, NULL, '1528ec21abf712739637b8b24cf9dddd', 1536, NULL, '2021-10-20 15:36:38', NULL, '2021-10-20 15:36:38', 1);
INSERT INTO `sys_file` VALUES (558, 'LJsTnl2aPoRXGtl7LiIut14RtD6vOMHsFIP', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 15:53:01', NULL, '2021-10-20 15:53:01', 1);
INSERT INTO `sys_file` VALUES (559, 'sSopDVBwSkcKpCLXi9eYJoEenccG7ROyP4pPY4', 'counter', NULL, NULL, 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-20 15:53:01', NULL, '2021-10-20 15:53:01', 1);
INSERT INTO `sys_file` VALUES (560, 'VLLitLxbRaNZ9mnJZEL1BbRjO81MgPjJaZHH', 'counter', NULL, NULL, '9ee6a9f9a820f06bc6584d64bb6d0e57', 1539, NULL, '2021-10-20 16:06:01', NULL, '2021-10-20 16:06:01', 1);
INSERT INTO `sys_file` VALUES (561, 'Gah4aTXdqrAtAfAUHe0NWTVcvyCcehgYQlSY6', 'counter', NULL, 'svg', '1528ec21abf712739637b8b24cf9dddd', 1536, NULL, '2021-10-20 16:13:06', NULL, '2021-10-20 16:13:06', 1);
INSERT INTO `sys_file` VALUES (562, 'Ds0sQA2LQgpSCXzwtuonZjf1lqT52lI88oMTS', 'counter', NULL, NULL, '1c301e4b785432461763da734715173b', 1897, NULL, '2021-10-20 16:13:58', NULL, '2021-10-20 16:13:58', 1);
INSERT INTO `sys_file` VALUES (563, 'VcMQiaNB39S9Dk9JydrX30y4ulzxeV84', 'counter', NULL, 'svg', 'ae08bce4e574c8538acf2fcb186ea7cc', 1536, NULL, '2021-10-20 16:14:47', NULL, '2021-10-20 16:14:47', 1);
INSERT INTO `sys_file` VALUES (564, 'aWVyg2hMxDQlWPsyma2nFVzT7XFRPmnY3', 'counter', NULL, NULL, 'a9dab1973d6f9c6563f8809142eb71d4', 2850, NULL, '2021-10-20 16:20:08', NULL, '2021-10-20 16:20:08', 1);
INSERT INTO `sys_file` VALUES (565, 'aS8L4zfdqtBlvRYNm5u5xKzuYsMolmt87', 'counter', NULL, 'svg', 'a9dab1973d6f9c6563f8809142eb71d4', 2850, NULL, '2021-10-20 16:22:14', NULL, '2021-10-20 16:22:14', 1);
INSERT INTO `sys_file` VALUES (566, '3mhpNxw0fhpJZnS0TQAmZ03Ps7NvFDdejCH4', 'counter', NULL, 'svg', '4986ba45b88d96c90cfd89771870f660', 3300, NULL, '2021-10-20 16:24:15', NULL, '2021-10-20 16:24:15', 1);
INSERT INTO `sys_file` VALUES (567, 'ZuLuW8CtJxRWWp1gn88lOiFskvlWqyY1C4D', 'counter', NULL, 'svg', 'bfed4595783b5b14ca9003e7d190809f', 1409, NULL, '2021-10-20 16:27:41', NULL, '2021-10-20 16:27:41', 1);
INSERT INTO `sys_file` VALUES (568, 'jHyqjbBePQp2AXnVtPac8efK09cyyZsLQsWq', 'counter', NULL, 'svg', 'e3ff4eda1317db7ba4a2857fa7c65207', 1296, NULL, '2021-10-20 16:28:49', NULL, '2021-10-20 16:28:49', 1);
INSERT INTO `sys_file` VALUES (569, 'iFbVly3bxAfA7vSWexdXjFZH3P2PjXHetKeRMlZ', 'counter', NULL, NULL, 'a9dab1973d6f9c6563f8809142eb71d4', 2850, NULL, '2021-10-20 16:29:05', NULL, '2021-10-20 16:29:05', 1);
INSERT INTO `sys_file` VALUES (570, 'bppcYc4nKA4iv4CogKJHa30V2NiTAjY3', 'counter', NULL, NULL, 'a9dab1973d6f9c6563f8809142eb71d4', 2850, NULL, '2021-10-20 16:30:54', NULL, '2021-10-20 16:30:54', 1);
INSERT INTO `sys_file` VALUES (571, 'tUKb50bLIrqqL5FfexySWmxaerrMIheoB', 'counter', NULL, 'svg', '199c6ccb3d9398a30f222f9506529d5d', 2640, NULL, '2021-10-20 16:32:06', NULL, '2021-10-20 16:32:06', 1);
INSERT INTO `sys_file` VALUES (572, '0jTkf2dgrSls6iihJY6epzZYjXZ0740mHRbdvU', 'counter', NULL, 'svg', 'f21a434697227f0b31679c5c31a90e8d', 2706, NULL, '2021-10-20 16:32:33', NULL, '2021-10-20 16:32:33', 1);
INSERT INTO `sys_file` VALUES (573, 'p7Gxsuc4JUzIh0RVaX82hBEF5VZ3Aqcn', 'counter', NULL, 'svg', 'a9dab1973d6f9c6563f8809142eb71d4', 2850, NULL, '2021-10-20 16:32:50', NULL, '2021-10-20 16:32:50', 1);
INSERT INTO `sys_file` VALUES (574, 'TCcrNpBaR5mkBW6WWQLlw4Mp4Ypq3lRbu', 'counter', NULL, 'svg', '6332da2b67f6b872992a9be94cf2c2dd', 2317, NULL, '2021-10-20 16:35:01', NULL, '2021-10-20 16:35:01', 1);
INSERT INTO `sys_file` VALUES (575, 'Fwf6hnr9W0oVcjxyl1PhnjgQBHez8FmYX', 'counter', NULL, 'svg', 'f8273e5d07116eae00a1ec7affada58', 3050, NULL, '2021-10-20 16:36:28', NULL, '2021-10-20 16:36:28', 1);
INSERT INTO `sys_file` VALUES (576, 'KBjpqutzfADomJaXdydolSNDjirkaPL8HEPO', 'counter', NULL, 'svg', '79ffb4c7fa2b871cfbcc9d6d811e7f61', 1373, NULL, '2021-10-20 16:41:39', NULL, '2021-10-20 16:41:39', 1);
INSERT INTO `sys_file` VALUES (577, 'kuY3zIJYFlrKNYJ9QT39DKgtb0TQO2tF7rmn', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:55', NULL, '2021-10-20 16:42:55', 1);
INSERT INTO `sys_file` VALUES (578, 'cfDsJ9tNc6DYwvVWzb4FSnsokWnEfOwqLkP', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:56', NULL, '2021-10-20 16:42:56', 1);
INSERT INTO `sys_file` VALUES (579, 'kuY3zIJYFlrKNYJ9QT39DKgtb0TQO2tF7rmn', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:56', NULL, '2021-10-20 16:42:56', 1);
INSERT INTO `sys_file` VALUES (580, 'YQ0LENzPywXdJ6ilhM0xfv4Y80Ud5HOolaAZ', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:57', NULL, '2021-10-20 16:42:57', 1);
INSERT INTO `sys_file` VALUES (581, 'cfDsJ9tNc6DYwvVWzb4FSnsokWnEfOwqLkP', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:57', NULL, '2021-10-20 16:42:57', 1);
INSERT INTO `sys_file` VALUES (582, 'kuY3zIJYFlrKNYJ9QT39DKgtb0TQO2tF7rmn', 'counter', NULL, 'svg', 'b0ff5611659ad00ee96f09869cbdb71f', 1457, NULL, '2021-10-20 16:42:57', NULL, '2021-10-20 16:42:57', 1);
INSERT INTO `sys_file` VALUES (583, 'LMEidQVJNATI7zvs00y2IU6YZ3I8j1xcT', 'counter', NULL, 'svg', 'eb251e63f8b787d5efbb3609e828c530', 1509, NULL, '2021-10-20 16:44:07', NULL, '2021-10-20 16:44:07', 1);
INSERT INTO `sys_file` VALUES (584, 'psgdD2GER9uUOmc7GE7202VrUoBsC0rX', 'counter', NULL, 'svg', 'bbbacc66e466b23c1d14dce2f9d30cdb', 1698, NULL, '2021-10-20 16:44:20', NULL, '2021-10-20 16:44:20', 1);
INSERT INTO `sys_file` VALUES (585, 'g2TYg22e2tqSZt0Jke1UsGvkuW73wRmD', 'counter', NULL, 'svg', 'e53bc57e6197e50f4d26e04d0353e5f9', 1535, NULL, '2021-10-20 16:49:25', NULL, '2021-10-20 16:49:25', 1);
INSERT INTO `sys_file` VALUES (586, '0nlWxbkIB6dSMD3h2uHaH6rNmWMIVcgJsoWKqAt', 'counter', NULL, 'svg', '73df482b712ddfbe16d0b2573080a32a', 1760, NULL, '2021-10-20 16:49:46', NULL, '2021-10-20 16:49:46', 1);
INSERT INTO `sys_file` VALUES (587, '0HI4gSi94T3ZnV2aomfYMUFLhie72n6gGgd5', 'counter', NULL, 'svg', 'f015a1d8e964e66200d8566c06f79e6f', 1571, NULL, '2021-10-20 16:52:03', NULL, '2021-10-20 16:52:03', 1);
INSERT INTO `sys_file` VALUES (588, 'hIpcS0Ggap5Y7K6bVHPdRpajrOHcA59i1G0Hf', 'counter', NULL, 'svg', '5391a0febbdc89c26b982e90855308aa', 1315, NULL, '2021-10-20 16:52:25', NULL, '2021-10-20 16:52:25', 1);
INSERT INTO `sys_file` VALUES (589, 'PQN8jF6paqIgQ0LnHJdqsnheaNt8Nl7vKK', 'counter', NULL, 'svg', 'cb652d46f3a832b7bfd38ec08e654034', 1386, NULL, '2021-10-20 16:53:16', NULL, '2021-10-20 16:53:16', 1);
INSERT INTO `sys_file` VALUES (590, 'D9wbpR47Ri1gr8qpyRFQyf0GgZOaFvy107S', 'counter', NULL, 'svg', '318fe03a57139a8d46873f8093e09447', 3903, NULL, '2021-10-20 16:53:40', NULL, '2021-10-20 16:53:40', 1);
INSERT INTO `sys_file` VALUES (591, 'iJnPO6lMa6rqv5osMoaLWsPGFSBRRG5FtS', 'counter', NULL, 'svg', '7fc7b40fde2fe012e72d74ab4437494e', 1257, NULL, '2021-10-20 16:55:23', NULL, '2021-10-20 16:55:23', 1);
INSERT INTO `sys_file` VALUES (592, 'tpMccIzpI1gyhnWLGKbp2j2rAEPjmWor9cziT', 'counter', NULL, 'svg', '50bd4bf5af0784621809a153fe307553', 1428, NULL, '2021-10-20 16:56:43', NULL, '2021-10-20 16:56:43', 1);
INSERT INTO `sys_file` VALUES (593, 'Atmg6QzP5v9LAQmPp4xqqDmX3UUhK7zcdVgF', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:36:57', NULL, '2021-10-21 08:36:57', 1);
INSERT INTO `sys_file` VALUES (594, 'twr6SHRweri8LPgpa33ZAIaFyTWyC4443O6r1', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:36:57', NULL, '2021-10-21 08:36:57', 1);
INSERT INTO `sys_file` VALUES (595, 'TkWTnam5ID0OPjHrvZBnCG2r4bvEp1AmSe', 'counter', NULL, 'svg', 'c59459f09e844241f8802c5b7a6b7b89', 3529, NULL, '2021-10-21 08:37:14', NULL, '2021-10-21 08:37:14', 1);
INSERT INTO `sys_file` VALUES (596, 'hKHWrJxSNJ1sQsLsYfGcJr7yDXDds8Nj5', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:46:24', NULL, '2021-10-21 08:46:24', 1);
INSERT INTO `sys_file` VALUES (597, 'cHxAtxjwAdmYiG0k93z2tMHivj5m8vAFz2mopXT', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:46:24', NULL, '2021-10-21 08:46:24', 1);
INSERT INTO `sys_file` VALUES (598, 'P1KhBj9XQV8ME2sjaVT879qAihmKYUJDSeGCnBN', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:54:37', NULL, '2021-10-21 08:54:37', 1);
INSERT INTO `sys_file` VALUES (599, 'cXP1g8rr4ZesqoBKxkjc0Jc5iFn6BNlgC1Lf', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 08:54:38', NULL, '2021-10-21 08:54:38', 1);
INSERT INTO `sys_file` VALUES (600, 'tg3ejkOuWRX7laVUTFcnXYEGoyolLBJAbuAK93', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 09:03:34', NULL, '2021-10-21 09:03:34', 1);
INSERT INTO `sys_file` VALUES (601, 'PC0q0EUVuD7G8e2KSLmewrbAt8xHN9qV', 'counter', NULL, 'svg', 'bdedb23fce217966b272ca3c7edccdba', 126029, NULL, '2021-10-21 09:03:35', NULL, '2021-10-21 09:03:35', 1);
INSERT INTO `sys_file` VALUES (602, 'IxmF2qLP8lkgVHI46j35mCrPT5eHxbGAVsqrM', 'counter', NULL, 'svg', '3f9f68af47f5f1df069159743c069061', 2088, NULL, '2021-10-21 09:04:27', NULL, '2021-10-21 09:04:27', 1);
INSERT INTO `sys_file` VALUES (603, 'GBWRQ33nAGz6XgB2yNB7wT7uKNWVJW9j4tl', 'counter', NULL, 'svg', '1c906ec09370090195122ecf905212b', 1375, NULL, '2021-10-21 09:08:45', NULL, '2021-10-21 09:08:45', 1);
INSERT INTO `sys_file` VALUES (604, 'WmXH9nzF2SxiutePI48eMOo33hsyzShXye', 'counter', NULL, 'svg', '1fa226a69cdbd0fea334f8f69d938b18', 1510, NULL, '2021-10-21 09:11:54', NULL, '2021-10-21 09:11:54', 1);
INSERT INTO `sys_file` VALUES (605, 'wjnXngquKcHEunrEk1jmeOTjcMMQrOGYD8G', 'counter', NULL, 'svg', 'b469d0224e7d3f347ce65bc753df427c', 1325, NULL, '2021-10-21 09:30:50', NULL, '2021-10-21 09:30:50', 1);
INSERT INTO `sys_file` VALUES (606, '0hgPJ2pwsc1uhBtMHDUxlc6w0JYg7jExvu', 'counter', NULL, 'svg', '625bb046d3d8fcc001b5c4e837de758e', 1371, NULL, '2021-10-21 09:34:57', NULL, '2021-10-21 09:34:57', 1);
INSERT INTO `sys_file` VALUES (607, 'dwjyeTyK8jZAGcuCAP9R09HYhF6nArMT1wrG', 'counter', NULL, 'svg', '53e288ec6a1fdf88f66f2ca2330dd44f', 903, NULL, '2021-10-21 09:44:41', NULL, '2021-10-21 09:44:41', 1);
INSERT INTO `sys_file` VALUES (608, '3cT7ZzUgitMwYkoxsCEQfjPqcicx9oVd', 'counter', NULL, NULL, 'ec522062648e2b79fe0e14d59e3299bf', 87257, NULL, '2021-10-28 16:29:25', NULL, '2021-10-28 16:29:25', 1);
INSERT INTO `sys_file` VALUES (609, 'vBXkBPHvoI5qmjZZZlHz7cr5su5zfBKvI', 'counter', NULL, NULL, '575ed586303eb475c642cd91279dc4e6', 113832, NULL, '2021-10-28 16:55:56', NULL, '2021-10-28 16:55:56', 1);
INSERT INTO `sys_file` VALUES (610, 'Jpp9rUGveqjk26gNOEsQBopCG5nNxCEUkeiiU', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:03:30', NULL, '2021-10-29 09:03:30', 1);
INSERT INTO `sys_file` VALUES (611, 'm7miAibPeSHc2DZ4dzzTLPwc0KfsCpSZj9w', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:03:31', NULL, '2021-10-29 09:03:31', 1);
INSERT INTO `sys_file` VALUES (612, 'wtmicvf2xEcp4ZAbh0ZIVqD4rztEMc0hXvDGi', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:07:31', NULL, '2021-10-29 09:07:31', 1);
INSERT INTO `sys_file` VALUES (613, '1vSvJGv1tMXalOteFuvNso7xO68ftu4SSe', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:07:32', NULL, '2021-10-29 09:07:32', 1);
INSERT INTO `sys_file` VALUES (614, 'BrhAWWpbYoxm09O7CFaimRTFSCYlGVtnbW', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:07:59', NULL, '2021-10-29 09:07:59', 1);
INSERT INTO `sys_file` VALUES (615, 'h96ddYPxG4PXnlIqYp3Izp24OV1phjZmiM7Bs', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:08:00', NULL, '2021-10-29 09:08:00', 1);
INSERT INTO `sys_file` VALUES (616, 'KI5payQcCUOvkQ25Ex8g4dHWQ9PNJvJliGFSLme', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:10:58', NULL, '2021-10-29 09:10:58', 1);
INSERT INTO `sys_file` VALUES (617, 'jt8SUUQ79mQJucyrZOssijevuncATG5x57jy6p', 'counter', NULL, NULL, '83e0aaee5f1bcada2e53c6e39500b67a', 158652, NULL, '2021-10-29 09:10:59', NULL, '2021-10-29 09:10:59', 1);
INSERT INTO `sys_file` VALUES (618, 'p61bGHBmCEIkr5NEZdayQKd2qLVLLC84iJ', 'counter', NULL, NULL, '575ed586303eb475c642cd91279dc4e6', 113832, NULL, '2021-10-29 09:59:55', NULL, '2021-10-29 09:59:55', 1);
INSERT INTO `sys_file` VALUES (619, 'KX98hF5ECXvUDlFsuvBHQlaYypdscjYRclm9Dn', 'counter', NULL, NULL, '575ed586303eb475c642cd91279dc4e6', 113832, NULL, '2021-10-29 09:59:56', NULL, '2021-10-29 09:59:56', 1);
INSERT INTO `sys_file` VALUES (620, 'Saquv6y2mBMDwoDHIra0iuOZEAzUvpSVp8hln', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:00:45', NULL, '2021-10-29 10:00:45', 1);
INSERT INTO `sys_file` VALUES (621, 'ejherE7uRsQfJIhY8etlUBBRHThoSmTHm73uic', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:00:45', NULL, '2021-10-29 10:00:45', 1);
INSERT INTO `sys_file` VALUES (622, 'Fz5LJqr4KvK7Ax72tjlfjA7WsIM3TvyLOBR', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:02:29', NULL, '2021-10-29 10:02:29', 1);
INSERT INTO `sys_file` VALUES (623, 'GbKiHAx5qvQML6XcNH5FkTKBNsLLuPL1wvZHAc', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:02:29', NULL, '2021-10-29 10:02:29', 1);
INSERT INTO `sys_file` VALUES (624, 'WKBu6kOxGU86s6CSOPltfkdLuBJ7cZaNjNN', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:04:32', NULL, '2021-10-29 10:04:32', 1);
INSERT INTO `sys_file` VALUES (625, '5H8kPA4YZcfMbOkFhG38CzCEjVjUCCq3', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:04:32', NULL, '2021-10-29 10:04:32', 1);
INSERT INTO `sys_file` VALUES (626, '95xiifkV5ISKhg3chXUC8NjSnKMY98PS0', 'counter', NULL, NULL, 'e195c895dee141da90db804fa127905d', 14001, NULL, '2021-10-29 10:08:12', NULL, '2021-10-29 10:08:12', 1);
INSERT INTO `sys_file` VALUES (627, 'CzXmbIedt9aHK9KlrFTbeGaGOY59d479tB2S9mg', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-10-29 10:08:12', NULL, '2021-10-29 10:08:12', 1);
INSERT INTO `sys_file` VALUES (628, '8TkNzhorZ997knI5KpIIB26007nmP4CPIwivxst', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:14:53', NULL, '2021-10-29 10:14:53', 1);
INSERT INTO `sys_file` VALUES (629, 'IZ6lqR491Zs9qLC9AE32icrffk8X9fl6Z', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:14:53', NULL, '2021-10-29 10:14:53', 1);
INSERT INTO `sys_file` VALUES (630, 'lDczdbGdGbM0S7nUdPZRycqMCPmg1phjXUlYK6', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:16:04', NULL, '2021-10-29 10:16:04', 1);
INSERT INTO `sys_file` VALUES (631, '9NmhAStbkp0w8rB69EQGa6YFpV2ESkYnQ0dN', 'counter', NULL, NULL, '78df5ba397367a70bb54ae25e6e83a42', 150906, NULL, '2021-10-29 10:16:04', NULL, '2021-10-29 10:16:04', 1);
INSERT INTO `sys_file` VALUES (632, '6DxIHIsrS8ExEy98BolqoYVlJu7fAzl0buR.svg', 'counter', NULL, 'svg', '506fbb862edb9a9479d7ae47ef2489e1', 4045, NULL, '2021-10-29 10:18:08', NULL, '2021-10-29 10:18:08', 1);
INSERT INTO `sys_file` VALUES (633, 'BK3EzTaNcgUHvlBPKlWECACAIjbnNzPj1HQRe', 'counter', NULL, NULL, 'e8ab814ed0a56df06d1fb4d40340d4d6', 17868, NULL, '2021-10-29 11:20:30', NULL, '2021-10-29 11:20:30', 1);
INSERT INTO `sys_file` VALUES (634, 'a05gYt8veEYSpDBTBYvxmcLo90Kc34vIsTk', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-10-29 11:20:30', NULL, '2021-10-29 11:20:30', 1);
INSERT INTO `sys_file` VALUES (635, 'IDpsTTeJsVW63po2ucISXtsM0Ou8rE8SI', 'counter', NULL, NULL, 'e195c895dee141da90db804fa127905d', 14001, NULL, '2021-10-29 11:28:29', NULL, '2021-10-29 11:28:29', 1);
INSERT INTO `sys_file` VALUES (636, 'v9ehdK4fjCxnkFPy2ApK1BuJAWaOfaFASjfmwsS', 'counter', NULL, NULL, 'b23b8c9b53218c03eb7dc57c30a94cb1', 7506, NULL, '2021-10-29 11:28:29', NULL, '2021-10-29 11:28:29', 1);
INSERT INTO `sys_file` VALUES (637, 'lh4NVoMCJhKhk2AL4aNyqg7kvbXhChu540', 'counter', NULL, NULL, '4461b9ecd4e2ed5706661ff3dd184866', 198612, NULL, '2021-10-29 11:33:50', NULL, '2021-10-29 11:33:50', 1);
INSERT INTO `sys_file` VALUES (638, 'ilFTdrcx2FDu3Ovb6m9Ipo6frKx50QHMHkdGk', 'counter', NULL, NULL, '4461b9ecd4e2ed5706661ff3dd184866', 198612, NULL, '2021-10-29 11:33:50', NULL, '2021-10-29 11:33:50', 1);
INSERT INTO `sys_file` VALUES (639, 'A5ukmaqG2HTpK5s8e2gzlTwnV1Mp5YH6H5', 'counter', NULL, NULL, 'e4f78ba1c7fdf7e72cad23979419f068', 17242, NULL, '2021-10-29 13:32:28', NULL, '2021-10-29 13:32:28', 1);
INSERT INTO `sys_file` VALUES (640, 'y9KOoxfPieMdjt1ZzLXtdzGNCyValz9w', 'counter', NULL, NULL, '219802cb43b0fcb5fbee991899b2a33e', 17312, NULL, '2021-10-29 13:33:06', NULL, '2021-10-29 13:33:06', 1);
INSERT INTO `sys_file` VALUES (641, 'XIhIpERa36ax3Do4qxFYBEq3BsszZoIytZdbow', 'counter', NULL, NULL, '70e0c21c6c70b0706293d5815f2713a0', 17156, NULL, '2021-10-29 13:45:02', NULL, '2021-10-29 13:45:02', 1);
INSERT INTO `sys_file` VALUES (642, 'O1iV91EkDlBXYRumam8jE704SMJurSuo9eZxI', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 13:54:05', NULL, '2021-10-29 13:54:05', 1);
INSERT INTO `sys_file` VALUES (643, 'jnZ82QYlSFcmxBNrRUsIR0tY3n9mslOuSTxExiu', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 13:54:05', NULL, '2021-10-29 13:54:05', 1);
INSERT INTO `sys_file` VALUES (644, 'ynuN6rACNjUu3HjaTksBMaTzt5TVrzi1', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 13:54:06', NULL, '2021-10-29 13:54:06', 1);
INSERT INTO `sys_file` VALUES (645, 'eGVq1csJbabLkX4fnp8UMWyuSOf23XmReDa', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 13:54:06', NULL, '2021-10-29 13:54:06', 1);
INSERT INTO `sys_file` VALUES (646, 'ILOcFeoyvbF6j1HlLRP99B0YyGfk0JXi0HfsN', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 14:00:11', NULL, '2021-10-29 14:00:11', 1);
INSERT INTO `sys_file` VALUES (647, 'N5J6tDmjetAJ7G3jt5hxdzFnzFqZiS7mp4Uur', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 14:00:11', NULL, '2021-10-29 14:00:11', 1);
INSERT INTO `sys_file` VALUES (648, 'GQFtoki9Y2CzsX3V8amxk26E3aBziIzmMN6jTQg', 'counter', NULL, NULL, 'a3a87f4c21b651bc1253aa0cd221ffc6', 18236, NULL, '2021-10-29 14:01:29', NULL, '2021-10-29 14:01:29', 1);
INSERT INTO `sys_file` VALUES (649, 'vtHCRxBLNEOS3dR2dQXg1BDSNqslRHhz9DM5fq', 'counter', NULL, NULL, 'dbbc9a9087aeeb3ba24d80870c60f696', 15327, NULL, '2021-10-29 14:01:57', NULL, '2021-10-29 14:01:57', 1);
INSERT INTO `sys_file` VALUES (650, 'ZAysFHzwjbh7aAdYLq7hFsCSG79gVpLkFW1', 'counter', NULL, NULL, '585edd14e519c43aaea4189407a4fc3f', 14370, NULL, '2021-10-29 14:03:03', NULL, '2021-10-29 14:03:03', 1);
INSERT INTO `sys_file` VALUES (651, 'bAAFZcpjTB21QNpzzaSX6nK8CGjPQLlft8lndK', 'counter', NULL, NULL, '2bc04111be26e8a9a2389bad54461cea', 16364, NULL, '2021-10-29 14:04:31', NULL, '2021-10-29 14:04:31', 1);
INSERT INTO `sys_file` VALUES (652, '4wgaL7STPqyiVHihioiVAu30q25ofpvnCtwny', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 14:05:02', NULL, '2021-10-29 14:05:02', 1);
INSERT INTO `sys_file` VALUES (653, 'pBPwK7k6XLCXtab6xVBMwsh3yLrEzrjtnoiy', 'counter', NULL, NULL, '2c748cb11f2b013773d118dfc18dd0c', 38724, NULL, '2021-10-29 14:05:02', NULL, '2021-10-29 14:05:02', 1);
INSERT INTO `sys_file` VALUES (654, 'bNYpZvUzsMY20Yu8EfjRmYV7SlSMUSr4L8.svg', 'counter', NULL, 'svg', '6d6aeb721ff7ea03360819d1e4d89446', 1489, NULL, '2021-10-29 14:58:03', NULL, '2021-10-29 14:58:03', 1);
INSERT INTO `sys_file` VALUES (655, 'Ufz3oZ7sno34ZimYBgUs4RYlkWbQiqFUAx4BeRg.svg', 'counter', NULL, 'svg', '5a134bb46263e36354160029ebb072e9', 1350, NULL, '2021-10-29 15:00:54', NULL, '2021-10-29 15:00:54', 1);
INSERT INTO `sys_file` VALUES (656, 'WU8IgWSVYx1JUuOPew6GPQHUNo06I10eHp93CR.svg', 'counter', NULL, 'svg', '48eeca7f1dc575f5d912528653970a12', 1559, NULL, '2021-10-29 15:02:23', NULL, '2021-10-29 15:02:23', 1);
INSERT INTO `sys_file` VALUES (657, '9Y5s5hfg5rr8fE6tkQgi70S69ptoXpDNTPKiYmj.svg', 'counter', NULL, 'svg', '386efa1fa2a3d3a38894a72527b42601', 1510, NULL, '2021-10-29 15:06:50', NULL, '2021-10-29 15:06:50', 1);
INSERT INTO `sys_file` VALUES (658, '6FjDvAjEFrZQjv41BarMgYM0wBSaVXacl7Bo5.svg', 'counter', NULL, 'svg', '6b09a8a5d6532d3767fb3fa46446a111', 1196, NULL, '2021-10-29 15:07:21', NULL, '2021-10-29 15:07:21', 1);
INSERT INTO `sys_file` VALUES (659, 'o12rtFtcieX3hr9oGsYjm2t0eNgXPtfTI.svg', 'counter', NULL, 'svg', '9d0f0ea1a058fa69bc7882916f707e4d', 1389, NULL, '2021-10-29 15:08:24', NULL, '2021-10-29 15:08:24', 1);
INSERT INTO `sys_file` VALUES (660, 'ZaApgxiLjrY2Jp1ZnXY9Fs3PrbCgHGsFmx.svg', 'counter', NULL, 'svg', 'b8ddba8109f692cfdbbbdf7ca5e1b1c', 3050, NULL, '2021-10-29 15:14:02', NULL, '2021-10-29 15:14:02', 1);
INSERT INTO `sys_file` VALUES (661, 'wzUAw3OPINobNU7NDaIIZtQEMGLOO2olX', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-10-29 15:22:42', NULL, '2021-10-29 15:22:42', 1);
INSERT INTO `sys_file` VALUES (662, '5TNgYT1CgPFxgzsCJkL2MwATspiyLe0Qg5vv0HD', 'counter', NULL, NULL, 'b23b8c9b53218c03eb7dc57c30a94cb1', 7506, NULL, '2021-10-29 15:22:42', NULL, '2021-10-29 15:22:42', 1);
INSERT INTO `sys_file` VALUES (663, 'fpJWKJbVod5OVlojlMd7NRN1xlPSQm2H.svg', 'counter', NULL, 'svg', 'be74119d6de845dc87ba2c663ab56bb1', 1552, NULL, '2021-10-29 15:23:04', NULL, '2021-10-29 15:23:04', 1);
INSERT INTO `sys_file` VALUES (664, 'H73ealXekJ1lnzsovHG6El916gQs4otYtDUH.svg', 'counter', NULL, 'svg', '69b714c7a3ad8510143b1c9ac68941f2', 1278, NULL, '2021-10-29 15:23:46', NULL, '2021-10-29 15:23:46', 1);
INSERT INTO `sys_file` VALUES (665, 'DiqSZU8rRRjTr6VdSMCkMscal16WMAM0f.svg', 'counter', NULL, 'svg', 'fcf5e7ecb846a18478e9cff0bd2b897e', 1974, NULL, '2021-10-29 15:24:30', NULL, '2021-10-29 15:24:30', 1);
INSERT INTO `sys_file` VALUES (666, 'FbPHh0lkUsEJm2CSFX3tZdSRWcCpcp6mu.svg', 'counter', NULL, 'svg', '558ededf14a25f953b56e387d82547f8', 1543, NULL, '2021-10-29 15:25:21', NULL, '2021-10-29 15:25:21', 1);
INSERT INTO `sys_file` VALUES (667, 'J7QOqoLbFgilcrJv34PkDlSgWFuspESR', 'counter', NULL, NULL, 'e8ab814ed0a56df06d1fb4d40340d4d6', 17868, NULL, '2021-10-29 16:41:01', NULL, '2021-10-29 16:41:01', 1);
INSERT INTO `sys_file` VALUES (668, 'bpY8oVre6yHj4hC3E6NTsfLaIQvg9GJXQL4hFE', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-10-29 16:41:01', NULL, '2021-10-29 16:41:01', 1);
INSERT INTO `sys_file` VALUES (669, 'NFGAljMDmQwZ6s7nqRkdmRrZNXr1jUHhWLGwo.svg', 'counter', NULL, 'svg', 'b68a19aa2b5ebd12f5fcb4b7e9f2e63', 2049, NULL, '2021-10-29 16:42:08', NULL, '2021-10-29 16:42:08', 1);
INSERT INTO `sys_file` VALUES (670, 'd9phpIxAR6HX0pPMTZA64NnAVSyAY4crM.svg', 'counter', NULL, 'svg', '8d88a42271b804539d1833045aff4a9a', 1957, NULL, '2021-10-29 16:42:36', NULL, '2021-10-29 16:42:36', 1);
INSERT INTO `sys_file` VALUES (671, 'V6wzGN4JdcP1XvgGb58ionDIOilbtitf6VPh', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-10-29 16:43:45', NULL, '2021-10-29 16:43:45', 1);
INSERT INTO `sys_file` VALUES (672, 'vMjjBfOheUmU3QnUaKYhxMe4QDXTyt7S3', 'counter', NULL, NULL, 'e8ab814ed0a56df06d1fb4d40340d4d6', 17868, NULL, '2021-10-29 16:43:45', NULL, '2021-10-29 16:43:45', 1);
INSERT INTO `sys_file` VALUES (673, 'nSUL90FncKkNJqYJFWtRjHXeUb4bYSAfoC8y.svg', 'counter', NULL, 'svg', '54bcff837f167cadac9bdb310dea672d', 1614, NULL, '2021-10-29 16:46:16', NULL, '2021-10-29 16:46:16', 1);
INSERT INTO `sys_file` VALUES (674, 'S4zfGfVhG0g2e4lMhV9LAqvd6RMwda9u1ZABr7', 'counter', NULL, NULL, '67cad0666c1d10b8c21c4339416a0fec', 119875, NULL, '2021-11-02 15:32:06', NULL, '2021-11-02 15:32:06', 1);
INSERT INTO `sys_file` VALUES (675, '17xEw6o7SqrvFVajY5TciCKelrhW6StJvBA4mo', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 15:32:07', NULL, '2021-11-02 15:32:07', 1);
INSERT INTO `sys_file` VALUES (676, 'RFXhHxXeKt70S7HP9DB7yifaRegx3JPM0j.svg', 'counter', NULL, 'svg', '2e234fd4d71de7374e8baa28e99cb5dd', 1036, NULL, '2021-11-02 15:35:07', NULL, '2021-11-02 15:35:07', 1);
INSERT INTO `sys_file` VALUES (677, 'kuD1bva4Di8uLhAjjZTZJHP0MmbGJqvA0ZgzJ.svg', 'counter', NULL, 'svg', 'f110be977ba10ae776a80709703393e', 825, NULL, '2021-11-02 15:36:11', NULL, '2021-11-02 15:36:11', 1);
INSERT INTO `sys_file` VALUES (678, 'KF4eFEKuhQ8WzhFlS8lJ29bYJQzpUFBbDM', 'counter', NULL, NULL, '67cad0666c1d10b8c21c4339416a0fec', 119875, NULL, '2021-11-02 15:39:32', NULL, '2021-11-02 15:39:32', 1);
INSERT INTO `sys_file` VALUES (679, '2CAjqQN9d3CNfMpVblO93vXFBsJ4lxxNaTt4', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 15:39:32', NULL, '2021-11-02 15:39:32', 1);
INSERT INTO `sys_file` VALUES (680, 'u6Dmxnw6pdVwb43Gxb0Q0fTo4UrfNGCnorSG9.svg', 'counter', NULL, 'svg', 'e0eea80eb3edca12a819dc3039c23fbc', 721, NULL, '2021-11-02 15:40:54', NULL, '2021-11-02 15:40:54', 1);
INSERT INTO `sys_file` VALUES (681, 'cduKuB46WEiBDUkfIZnhnxLcKGcS33oE.svg', 'counter', NULL, 'svg', 'acd47f3c1c2d9e94ce74127d094fb857', 732, NULL, '2021-11-02 15:44:28', NULL, '2021-11-02 15:44:28', 1);
INSERT INTO `sys_file` VALUES (682, 'OAMU9PlwEmCNVSoZ8uikSTUb8P4z0DFyi1BP', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:03:00', NULL, '2021-11-02 16:03:00', 1);
INSERT INTO `sys_file` VALUES (683, 'q7RDKkD5KsCb7iCSm42z5ZlXbiebHhKf0nC', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:03:03', NULL, '2021-11-02 16:03:03', 1);
INSERT INTO `sys_file` VALUES (684, 'eeTUsmguTjrJx40cYrw5SNAHGQmznzCyBEo6uA', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:03:05', NULL, '2021-11-02 16:03:05', 1);
INSERT INTO `sys_file` VALUES (685, '2DbNm6OSRAo6D8me4mmT77lDWBQ28uye8', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:03:07', NULL, '2021-11-02 16:03:07', 1);
INSERT INTO `sys_file` VALUES (686, '5fsWe8gbMbHlxrH9ETpUMapwsNVdroi9Km5wg', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:03:11', NULL, '2021-11-02 16:03:11', 1);
INSERT INTO `sys_file` VALUES (687, 'QvGwZQpumCKF7JaiLxvgvxtuPAcddj6PfI.svg', 'counter', NULL, 'svg', '8ad5b2a7f94fe54a45bd4994a54e6045', 1040, NULL, '2021-11-02 16:03:48', NULL, '2021-11-02 16:03:48', 1);
INSERT INTO `sys_file` VALUES (688, 'exQdT1ytFHYP5DlPhkOUQsS22LhboAJl', 'counter', NULL, NULL, '3f100223aa6c67b430f83c4da8b213cf', 128358, NULL, '2021-11-02 16:12:39', NULL, '2021-11-02 16:12:39', 1);
INSERT INTO `sys_file` VALUES (689, '8DW6YLWmK6DtZ99wADOmy0ErM8zTz648qycuzB', 'counter', NULL, NULL, '67cad0666c1d10b8c21c4339416a0fec', 119875, NULL, '2021-11-02 16:12:39', NULL, '2021-11-02 16:12:39', 1);
INSERT INTO `sys_file` VALUES (690, 'xKyIyYJDYO4wtJSf8RYQ0vyZ0F1dy0rwSdn', 'counter', NULL, NULL, '1e1fabced6488b9e2e295080582c6d10', 7932, NULL, '2021-11-02 16:15:11', NULL, '2021-11-02 16:15:11', 1);
INSERT INTO `sys_file` VALUES (691, 'NQEkCQ6ARk2glzd9LpDlsftpO2meY16C26X2', 'counter', NULL, NULL, 'a146b505bcda74b6e647b49e0e45ab9e', 7730, NULL, '2021-11-02 16:15:11', NULL, '2021-11-02 16:15:11', 1);
INSERT INTO `sys_file` VALUES (692, 'aeLlcPcSizM6c962pGxbS9oXPWLEITYw', 'counter', NULL, NULL, '1e1fabced6488b9e2e295080582c6d10', 7932, NULL, '2021-11-02 16:20:35', NULL, '2021-11-02 16:20:35', 1);
INSERT INTO `sys_file` VALUES (693, '27EEVdcJDO4WBNINxCM76DRx07NXhQBW0dCPPGU', 'counter', NULL, NULL, '1e1fabced6488b9e2e295080582c6d10', 7932, NULL, '2021-11-02 16:20:36', NULL, '2021-11-02 16:20:36', 1);
INSERT INTO `sys_file` VALUES (694, '6XQbs6utSGFDeBCJsGAPD1folSpoUWP6', 'counter', 'Snipaste_2021-09-27_16-56-16.png', 'image/png', '68f004e1597f494f392eb24d0681689e', 142524, 111, '2021-11-03 14:49:40', NULL, '2021-11-03 14:49:40', 1);
INSERT INTO `sys_file` VALUES (695, 'mo6w57LvVxVnEr6yFuSe4GDSGSyQaTbr1', 'counter', 'Snipaste_2021-09-27_16-56-16.png', 'image/png', '68f004e1597f494f392eb24d0681689e', 142524, NULL, '2021-11-03 14:49:47', NULL, '2021-11-03 14:49:47', 1);
INSERT INTO `sys_file` VALUES (696, 'oyqa5pofpu8PFblSmOwZiVELRdx2IqruOMJd4tR', 'counter', NULL, NULL, 'b78511ad7932aa9a564874b2399a4dd1', 403304, NULL, '2021-11-03 15:10:03', NULL, '2021-11-03 15:10:03', 1);
INSERT INTO `sys_file` VALUES (697, 'XAK7g2RNrdw1vZitz6IJE4ARFhv3jx9L', 'counter', NULL, NULL, 'e0bd5504e899676ae979a74ed18a9cf7', 151781, NULL, '2021-11-03 15:10:03', NULL, '2021-11-03 15:10:03', 1);
INSERT INTO `sys_file` VALUES (698, 'zE8N3jWPYiYrdnfoisZ50LzRIJOwnXxown31YIM', 'counter', NULL, NULL, 'f3080f83aa4720d511e0c5b421a13961', 162621, NULL, '2021-11-03 15:16:02', NULL, '2021-11-03 15:16:02', 1);
INSERT INTO `sys_file` VALUES (699, 'AoxMLEkKttrRl7TbZ7yWU2lbEUyPL3eha', 'counter', NULL, NULL, '67cad0666c1d10b8c21c4339416a0fec', 119875, NULL, '2021-11-03 15:16:02', NULL, '2021-11-03 15:16:02', 1);
INSERT INTO `sys_file` VALUES (700, 'FNfF8An6LCXLhcL4f1ym3X8j2xW9yZe6nW2.svg', 'counter', NULL, 'svg', '1aa5e54bfd161cbcbd3e29811d62be64', 764, NULL, '2021-11-03 15:16:19', NULL, '2021-11-03 15:16:19', 1);
INSERT INTO `sys_file` VALUES (701, 'uz2yEkhan1DCtLtBEDdoNDqqEYgZkBHX4X4rdgO', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-04 13:56:39', NULL, '2021-11-04 13:56:39', 1);
INSERT INTO `sys_file` VALUES (702, 'qN9xWyjjcChZmFe9DQAvu9F5sbBDXEPOEc', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-04 13:56:39', NULL, '2021-11-04 13:56:39', 1);
INSERT INTO `sys_file` VALUES (703, 'bCFCqBPMGNgKM53q8asGy3d5HXUelNfh0mbCr', 'counter', NULL, NULL, '866541be8fb811c483d36d04cf398ca2', 38564, NULL, '2021-11-04 13:57:24', NULL, '2021-11-04 13:57:24', 1);
INSERT INTO `sys_file` VALUES (704, 'ab8yGDfgPWgWKzqiQ3LwwGsdFc1rKaCmhPh', 'counter', NULL, NULL, 'ee41207293bb680c3b4d64b1a145f53d', 33756, NULL, '2021-11-04 13:57:24', NULL, '2021-11-04 13:57:24', 1);
INSERT INTO `sys_file` VALUES (705, 'rIjO5svGA8a7ZyBRChwrWqzBuU8IC2F7ICOwSiB.svg', 'counter', NULL, 'svg', 'c986bfc17cc73c4ae39bd0d1e9236f1d', 3786, NULL, '2021-11-04 14:16:16', NULL, '2021-11-04 14:16:16', 1);
INSERT INTO `sys_file` VALUES (706, 'RdAjN9G0k0Ze1G7jaXvUkooYX6fPQL1oVuofcx8.svg', 'counter', NULL, 'svg', 'fe5b48fa6a756c9f1fdd718074dd1ab6', 609, NULL, '2021-11-04 14:17:15', NULL, '2021-11-04 14:17:15', 1);
INSERT INTO `sys_file` VALUES (707, 'xYbRzj7t1aut28n02Ygth0G0w5zGKM6AVWMZ.svg', 'counter', NULL, 'svg', '7120908ff70aa7242f974fe1e5c8ee4d', 718, NULL, '2021-11-04 14:22:23', NULL, '2021-11-04 14:22:23', 1);
INSERT INTO `sys_file` VALUES (708, 'kBlQQfUbEreGnETPQ4fThfo5xAP1dRZ9', 'counter', NULL, NULL, '866541be8fb811c483d36d04cf398ca2', 38564, NULL, '2021-11-04 14:23:10', NULL, '2021-11-04 14:23:10', 1);
INSERT INTO `sys_file` VALUES (709, 'SKBKC48nPJXTZNTQHzyG5WXyqDnSkz0O29cBep', 'counter', NULL, NULL, '3644db7ae4cbd9274283fec01d27d66a', 49697, NULL, '2021-11-04 14:23:10', NULL, '2021-11-04 14:23:10', 1);
INSERT INTO `sys_file` VALUES (710, 'BJ1Dpf3suq6T1gq0Qhf7hvhqE0Zouqafa31wc7.svg', 'counter', NULL, 'svg', '39b4c687a2cad4f67fb80ae8d208c7bd', 2155, NULL, '2021-11-04 14:52:02', NULL, '2021-11-04 14:52:02', 1);
INSERT INTO `sys_file` VALUES (711, '5FLuEyeary6QJbZWZKxMHbPOcBBUj3W4pXCY3r6.svg', 'counter', NULL, 'svg', '131ada4d66928d69284b26e2cb4e51ee', 1302, NULL, '2021-11-04 14:54:03', NULL, '2021-11-04 14:54:03', 1);
INSERT INTO `sys_file` VALUES (712, 'yqHgBpcCstxv29X58p9SmVykgJHYloPkjEa3Z', 'counter', NULL, NULL, 'b4256180c4f1c6ae11ba29c87f8ade3', 21261, NULL, '2021-11-04 14:55:35', NULL, '2021-11-04 14:55:35', 1);
INSERT INTO `sys_file` VALUES (713, 'd4z9b40BboaqzE2nlGL0KLMHEVd9ecOkkA', 'counter', NULL, NULL, 'd04b1f9d788971cd62aae1ea577ee8ad', 27125, NULL, '2021-11-04 14:55:35', NULL, '2021-11-04 14:55:35', 1);
INSERT INTO `sys_file` VALUES (714, 'L8fLrgb9ocnaM2jj0Fc3MiqcNVSl1RBEzNs', 'counter', NULL, NULL, 'b78511ad7932aa9a564874b2399a4dd1', 403304, NULL, '2021-11-04 15:10:12', NULL, '2021-11-04 15:10:12', 1);
INSERT INTO `sys_file` VALUES (715, 'xWgIkrr6WB7BgHUHtqWe0LKYX22oxgONvkIUX', 'counter', NULL, NULL, 'b78511ad7932aa9a564874b2399a4dd1', 403304, NULL, '2021-11-04 15:10:12', NULL, '2021-11-04 15:10:12', 1);
INSERT INTO `sys_file` VALUES (716, 'esMHWlEbmnsK6gOp5NzBHeWQVcyf7KVobeLvyTg.svg', 'counter', NULL, 'svg', '627712efb3a0d6a309a191bb703f94d2', 805, NULL, '2021-11-04 15:11:05', NULL, '2021-11-04 15:11:05', 1);
INSERT INTO `sys_file` VALUES (717, 'mluBm0JN4ZilG136l5nYlAWf7KsO2Xkb34f9H.svg', 'counter', NULL, 'svg', 'f85e88888b15b8698c64216aa9b0d190', 821, NULL, '2021-11-04 15:12:28', NULL, '2021-11-04 15:12:28', 1);
INSERT INTO `sys_file` VALUES (718, 'nVke03O1kutWWhi0YJcoMgTs7b8tXJNroHcUE8q.svg', 'counter', NULL, 'svg', 'a6b6fed108e0685a57e9026a833a46c6', 662, NULL, '2021-11-04 15:25:58', NULL, '2021-11-04 15:25:58', 1);
INSERT INTO `sys_file` VALUES (719, 'iYJ7O9yfziQgquDNRRlBdeAgZ11Xmaque4K.svg', 'counter', NULL, 'svg', 'd47db30a93b7b278edbc19e0f7c32f45', 1554, NULL, '2021-11-04 15:38:47', NULL, '2021-11-04 15:38:47', 1);
INSERT INTO `sys_file` VALUES (720, 'Vr5fyfY7EqfbWROqD9uG9EUeaIJXMx0cC5A', 'counter', NULL, NULL, '64cd287e3a1e46a14ed1aad539d75562', 7023, NULL, '2021-11-04 16:33:54', NULL, '2021-11-04 16:33:54', 1);
INSERT INTO `sys_file` VALUES (721, '9YpfICH8NjMFlG7uerXR5KfFWKU7EhO8CXTrZ2', 'counter', NULL, NULL, '4c6941cf5dd83605cbc3c0ff43eb751c', 5616, NULL, '2021-11-04 16:48:16', NULL, '2021-11-04 16:48:16', 1);
INSERT INTO `sys_file` VALUES (722, '030YN1Uoupgwb8cI6ugu5GpaI1Y2wPbu', 'counter', NULL, NULL, '43892e7eb758c5bc127394d7ba4aa8ab', 32722, NULL, '2021-11-04 16:53:33', NULL, '2021-11-04 16:53:33', 1);
INSERT INTO `sys_file` VALUES (723, 'fcivt5HkQqqrq6DCbp7HauyQtej7jQnxmZWVnb', 'counter', NULL, NULL, '1c78c13563edc5446276107ab63d3b51', 37544, NULL, '2021-11-04 16:53:33', NULL, '2021-11-04 16:53:33', 1);
INSERT INTO `sys_file` VALUES (724, 'hRwCtWsAdZ4s191Xt0ElXM9583sc6lKSGmha', 'counter', NULL, NULL, '762503c2e739cb20390d1fbd54690eea', 5235, NULL, '2021-11-04 16:58:02', NULL, '2021-11-04 16:58:02', 1);
INSERT INTO `sys_file` VALUES (725, 'd17U8eou46pinTAEHuPa9ooGgGepuVrjy54', 'counter', NULL, NULL, '7c3dda258824fa48d0834f608248a879', 32913, NULL, '2021-11-05 09:07:24', NULL, '2021-11-05 09:07:24', 1);
INSERT INTO `sys_file` VALUES (726, 'x7tT93iwTv0I0IWhHU7loie9XPNjavn4', 'counter', NULL, NULL, 'e2f11b137db1931dbc5ed3107238135d', 37691, NULL, '2021-11-05 09:07:24', NULL, '2021-11-05 09:07:24', 1);
INSERT INTO `sys_file` VALUES (727, 'lxtFq1YaiQUnwZZWUJqmD6FbTzjlH2j7L8m', 'counter', NULL, NULL, 'fcfefd9842d6ee6f1a548a71c1f4eb06', 4907, NULL, '2021-11-05 09:09:32', NULL, '2021-11-05 09:09:32', 1);
INSERT INTO `sys_file` VALUES (728, 'htFDngF56MQqn6bmhsrIBmrz4ciHb6OPjO2Fv', 'counter', NULL, NULL, '755b6e59a4f58d3188e06e36e12fd982', 32847, NULL, '2021-11-05 09:13:08', NULL, '2021-11-05 09:13:08', 1);
INSERT INTO `sys_file` VALUES (729, 'WHHaRl6G3G8ewKaZV1xSlTinyugxPwoS', 'counter', NULL, NULL, 'fb0f6e8cfb118d17aeafd68913bec7bc', 38102, NULL, '2021-11-05 09:13:09', NULL, '2021-11-05 09:13:09', 1);
INSERT INTO `sys_file` VALUES (730, 'uUZDdaAUfw94WwemXSLsjSqboNUy9jWm1tnY', 'counter', NULL, NULL, 'd11c0aeb610702af498c8b89003b5689', 6199, NULL, '2021-11-05 09:13:35', NULL, '2021-11-05 09:13:35', 1);
INSERT INTO `sys_file` VALUES (731, '70iFpNIjKPinPjJKkcz2G3gDBzpUTJZomqKqc', 'counter', NULL, NULL, 'b7bdee0d81cdb504174b6895fa5d51c8', 33176, NULL, '2021-11-05 09:27:26', NULL, '2021-11-05 09:27:26', 1);
INSERT INTO `sys_file` VALUES (732, '6iNtSm8bWrDvBi9B0eB5Uu1avVPSOR6c', 'counter', NULL, NULL, '584c52282ad37cb55c89e2ae1534d1db', 37801, NULL, '2021-11-05 09:27:26', NULL, '2021-11-05 09:27:26', 1);
INSERT INTO `sys_file` VALUES (733, 'G34Lk9QSodPuEJHyvRhS8MKg19TsruoaOa', 'counter', NULL, NULL, 'e659994ba4e69239ee125c8759a9f9b1', 10924, NULL, '2021-11-05 09:28:06', NULL, '2021-11-05 09:28:06', 1);
INSERT INTO `sys_file` VALUES (734, '9BrqB9AkiFk9r0rebXa3PG15DtTskBvuIT33z65', 'counter', NULL, NULL, '4f36678778fb9b603ac22b44983dafbc', 33007, NULL, '2021-11-05 10:05:55', NULL, '2021-11-05 10:05:55', 1);
INSERT INTO `sys_file` VALUES (735, 'HM7soYtKVwuOiuIkDPBYlLKYxNmjnqml', 'counter', NULL, NULL, '76b9552982d947ff94a43ce10beec47', 37633, NULL, '2021-11-05 10:05:55', NULL, '2021-11-05 10:05:55', 1);
INSERT INTO `sys_file` VALUES (736, 'HhNlmQenUHjTHpSZuSVGBBJJI7R3sRgE', 'counter', NULL, NULL, 'fea488bcda769d033d48b0ae0bcb2514', 5916, NULL, '2021-11-05 10:06:23', NULL, '2021-11-05 10:06:23', 1);
INSERT INTO `sys_file` VALUES (737, 'YcOtZLoYngnayLBKQyGkBO8Cm4NUqrLvkH', 'counter', NULL, NULL, '23790408097a048424234a559152423f', 6308, NULL, '2021-11-05 10:12:22', NULL, '2021-11-05 10:12:22', 1);
INSERT INTO `sys_file` VALUES (738, '8dM6MKjG47pMIlTJsS8QCeT6pyTsgtZasx', 'counter', NULL, NULL, 'c8d55a53587c0cccbddae697e4123b8c', 32491, NULL, '2021-11-05 10:16:09', NULL, '2021-11-05 10:16:09', 1);
INSERT INTO `sys_file` VALUES (739, 'USSgOJujMGft8NnxAemyiy97N4wYzM2H', 'counter', NULL, NULL, '45cd0f25179629e952a38ae13671004c', 38143, NULL, '2021-11-05 10:16:09', NULL, '2021-11-05 10:16:09', 1);
INSERT INTO `sys_file` VALUES (740, 'SRP0kUNYvHq7GBIRGYpimv8FZ1pGRUURRwQew', 'counter', NULL, NULL, '3ac4be742cf54fc90548ecd6a80e392b', 32927, NULL, '2021-11-05 10:23:10', NULL, '2021-11-05 10:23:10', 1);
INSERT INTO `sys_file` VALUES (741, 'c40PpceBvmOSsonJy2eXvc0JQMkI3uH8Fea', 'counter', NULL, NULL, '1fe44829bede26ae5029490ce8f02c44', 38092, NULL, '2021-11-05 10:23:10', NULL, '2021-11-05 10:23:10', 1);
INSERT INTO `sys_file` VALUES (742, 'qJzXiZ2DHCv6TBWQP97EDR2MzQuLPjFDYT4fStZ', 'counter', NULL, NULL, '477ac87fb0c58bcdd5bb1aa979f080da', 32522, NULL, '2021-11-05 11:03:21', NULL, '2021-11-05 11:03:21', 1);
INSERT INTO `sys_file` VALUES (743, 'LQK3GGroXim61hSeTelZORafD5zHnzzUzEAxu', 'counter', NULL, NULL, 'ab2e9cd25c0f2977a3069435224b48e3', 38113, NULL, '2021-11-05 11:03:21', NULL, '2021-11-05 11:03:21', 1);
INSERT INTO `sys_file` VALUES (744, 'N4ud0udPtoV7BiefF5VBnma7a4P29keaUUMUf', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-05 11:09:25', NULL, '2021-11-05 11:09:25', 1);
INSERT INTO `sys_file` VALUES (745, '7UA4i7cI6esUEguosr1h9ogE8OZmue9mIrC10SW', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-05 11:09:25', NULL, '2021-11-05 11:09:25', 1);
INSERT INTO `sys_file` VALUES (746, 'DtFB6jOGyz2kNcU287mRFvRUMC0QKU1F', 'counter', NULL, NULL, '47460245918531411b75345a854988f4', 32616, NULL, '2021-11-05 11:32:47', NULL, '2021-11-05 11:32:47', 1);
INSERT INTO `sys_file` VALUES (747, 'wHs0SBpsqdgSDsgPVSlSQVGFfVUatmjsW4X', 'counter', NULL, NULL, 'c2329cff3c70526258377ac56bdfb6', 38191, NULL, '2021-11-05 11:32:47', NULL, '2021-11-05 11:32:47', 1);
INSERT INTO `sys_file` VALUES (748, 'gHmLLOv7fwNNuPDd9ds47oDsyr9q49hNILwfv', 'counter', NULL, NULL, '2d94925ef325e09aa5d89d01dde8ddff', 33029, NULL, '2021-11-05 11:44:31', NULL, '2021-11-05 11:44:31', 1);
INSERT INTO `sys_file` VALUES (749, 'c6dEm3eTVE5QrD3UvtvjhuCOINV0RqzGIQaM1', 'counter', NULL, NULL, 'a9c04213bfc0eaa82f23b447f2d49932', 38243, NULL, '2021-11-05 11:44:31', NULL, '2021-11-05 11:44:31', 1);
INSERT INTO `sys_file` VALUES (750, 'qgrerOrSmfaUsRZCFVxKegypC4MMKelMh', 'counter', NULL, NULL, '7e23ebcf90456f3130bac56f34b5886f', 33165, NULL, '2021-11-05 13:36:28', NULL, '2021-11-05 13:36:28', 1);
INSERT INTO `sys_file` VALUES (751, 'QMbHvWzCPvhLihm7itTZqQ3Xmyn6O0cI', 'counter', NULL, NULL, '7506be365213e6b5f4a8fb3868dc52fd', 37641, NULL, '2021-11-05 13:36:28', NULL, '2021-11-05 13:36:28', 1);
INSERT INTO `sys_file` VALUES (752, 'PBcK5MsrOKXNU9JxkKHitFSdpdiiZs8T', 'counter', NULL, NULL, '381ed2ae14a4ac6849834121f2aa5340', 32803, NULL, '2021-11-05 13:43:30', NULL, '2021-11-05 13:43:30', 1);
INSERT INTO `sys_file` VALUES (753, '1NZeWJ18ioZneuzDCFzsvearajR9Lcqhe1l26', 'counter', NULL, NULL, '9cb9d3abe8aa4392799f9c071fb436b', 37840, NULL, '2021-11-05 13:43:31', NULL, '2021-11-05 13:43:31', 1);
INSERT INTO `sys_file` VALUES (754, 'Q3niRZDJvbqnN97znQTHcnhRGgK3ilDMTFG', 'counter', NULL, NULL, 'b2bf0598497e588c1244d982c2ad377', 33271, NULL, '2021-11-05 14:26:51', NULL, '2021-11-05 14:26:51', 1);
INSERT INTO `sys_file` VALUES (755, 'VXwM5aKAp7YafgxxQelufWzigwp0knTk', 'counter', NULL, NULL, 'c5c7ae0d238fe17f974890fec25d1b6e', 37613, NULL, '2021-11-05 14:26:51', NULL, '2021-11-05 14:26:51', 1);
INSERT INTO `sys_file` VALUES (756, 'aAQKGPkvxqapzL2S0QTxfFtG7PJ4U2tNHiZn', 'counter', NULL, NULL, '2a0553e0a8c78524c09e91b5a40b7c5c', 5656, NULL, '2021-11-05 14:27:20', NULL, '2021-11-05 14:27:20', 1);
INSERT INTO `sys_file` VALUES (757, 'KbodCAC6uDqQJGXhkZA9zS3flJimFbVri', 'counter', NULL, NULL, 'fbfaf1c3673a818a90b79022c175e155', 4498, NULL, '2021-11-05 14:27:46', NULL, '2021-11-05 14:27:46', 1);
INSERT INTO `sys_file` VALUES (758, 'tQzXZ7QRZhMYpYpPkJuEWxZNQpVq0tU4X5qk', 'counter', NULL, NULL, '3d14eb1944697e29be76d74d285620e3', 6242, NULL, '2021-11-05 14:28:03', NULL, '2021-11-05 14:28:03', 1);
INSERT INTO `sys_file` VALUES (759, 'Z5F07BIfYNSh8V78VdgHKo2hL76SZTl4E.svg', 'counter', NULL, 'svg', '1c868e83ad5f0b39ff2c2f11ff18813e', 1627, NULL, '2021-11-05 14:46:50', NULL, '2021-11-05 14:46:50', 1);
INSERT INTO `sys_file` VALUES (760, 'LXb5hV7DWheGfHiYywHYl0gCJO4asHDIlH.svg', 'counter', NULL, 'svg', '924d231ce309cc5a19525ba43f813963', 1871, NULL, '2021-11-05 14:47:20', NULL, '2021-11-05 14:47:20', 1);
INSERT INTO `sys_file` VALUES (761, '7pRpo97UwtHJuqMtvKSo8v4FwZnXY7WKvT', 'counter', NULL, NULL, '80b4288687b4c6436290952c85f2d04d', 33247, NULL, '2021-11-05 15:29:27', NULL, '2021-11-05 15:29:27', 1);
INSERT INTO `sys_file` VALUES (762, 'aM5wkBfc4mUGpxBzZp3IrMwm2h11816168NciND', 'counter', NULL, NULL, 'b0fbf565d2602347ee757efc8caeb674', 37512, NULL, '2021-11-05 15:29:27', NULL, '2021-11-05 15:29:27', 1);
INSERT INTO `sys_file` VALUES (763, 'yzY9YkFQfvF1NUheZNveGr2AGwgZOMA2s', 'counter', NULL, NULL, '51e39d2207420f32dce142ddbdd8f2d5', 18836, NULL, '2021-11-05 15:30:03', NULL, '2021-11-05 15:30:03', 1);
INSERT INTO `sys_file` VALUES (764, 'vh85Xu39GD1hrBgUQ6WWI3cbays9limnk', 'counter', NULL, NULL, 'ca8c6ebf238eb06c2b231056d2c856f2', 12752, NULL, '2021-11-05 15:30:53', NULL, '2021-11-05 15:30:53', 1);
INSERT INTO `sys_file` VALUES (765, 'WjfVszVNXB0h43IhtkbICbQanPXVGztbwb', 'counter', NULL, NULL, 'a76a19a4cacae43f61d268075c45c896', 12047, NULL, '2021-11-05 15:31:36', NULL, '2021-11-05 15:31:36', 1);
INSERT INTO `sys_file` VALUES (766, '1ob5jyDlWbjOQnJxMlnPqQoxtAtWpyXl9FD', 'counter', NULL, NULL, '1fc99696fd16cd12f7753aecdea917a7', 6245, NULL, '2021-11-05 15:32:41', NULL, '2021-11-05 15:32:41', 1);
INSERT INTO `sys_file` VALUES (767, 'rizZAc2pB0reqNtrjQdQsbXGXcksU7LGa3KZG', 'counter', NULL, NULL, 'c776636a52f6b6b089031b371aa10775', 14163, NULL, '2021-11-05 15:33:16', NULL, '2021-11-05 15:33:16', 1);
INSERT INTO `sys_file` VALUES (768, 'a6DLSgwMu4Ek2YXHcwZ7SzHfNuBUMwC9', 'counter', NULL, NULL, '41f3f0324f0c733db3af115ae86e72c', 16211, NULL, '2021-11-05 15:33:34', NULL, '2021-11-05 15:33:34', 1);
INSERT INTO `sys_file` VALUES (769, 'tEixh9JyAf7EYBPWBXd0W7mxlayUbzhqS', 'counter', NULL, NULL, '5c800ba4273a7b61c934295edd0910b6', 16578, NULL, '2021-11-05 15:34:18', NULL, '2021-11-05 15:34:18', 1);
INSERT INTO `sys_file` VALUES (770, 'iFMyTtWssdWh93Xq3lVpAuH58FqhJchRpycU', 'counter', NULL, NULL, 'a8ddd79bbc0b6fad8502f921286148f9', 11648, NULL, '2021-11-05 15:39:51', NULL, '2021-11-05 15:39:51', 1);
INSERT INTO `sys_file` VALUES (771, 'eXl9foXVusHBP0DU5evdp7uEOn9van67LnBv2hz', 'counter', NULL, NULL, '5f11b6d0554b795aa672b56a542cef63', 32571, NULL, '2021-11-05 15:50:04', NULL, '2021-11-05 15:50:04', 1);
INSERT INTO `sys_file` VALUES (772, 'XnXnWDIdrjSJhdySXgpriskPfNPBukHQuujV', 'counter', NULL, NULL, 'df2c0f76f52b0704ecdeec66f67523fa', 38345, NULL, '2021-11-05 15:50:04', NULL, '2021-11-05 15:50:04', 1);
INSERT INTO `sys_file` VALUES (773, 'LpAQfInwOyLZPRcmkUUDUKMCdMHITEDyMa', 'counter', NULL, NULL, 'bf3770f2fa4cfe4b64b9209f1b9e9218', 32892, NULL, '2021-11-05 15:58:02', NULL, '2021-11-05 15:58:02', 1);
INSERT INTO `sys_file` VALUES (774, 'iuUJpAVPlShoFeZEou0hbmpN1egXVrGQMWjt', 'counter', NULL, NULL, '7cf0f00f4d7e6087793a03b92aefdda3', 37853, NULL, '2021-11-05 15:58:02', NULL, '2021-11-05 15:58:02', 1);
INSERT INTO `sys_file` VALUES (775, 'Vro3JGVKmC9HUZK8SNZ1MUFL6db0JVvIUg', 'counter', NULL, NULL, 'c088b72c5164fd8ed5a48a7d391de612', 33251, NULL, '2021-11-05 16:32:16', NULL, '2021-11-05 16:32:16', 1);
INSERT INTO `sys_file` VALUES (776, 'FWP13WZ8j9YC5GgeDmqqYMaKOEuzDijfig', 'counter', NULL, NULL, 'b15881591a7e1ceb8aa9aafee943fd', 37667, NULL, '2021-11-05 16:32:16', NULL, '2021-11-05 16:32:16', 1);
INSERT INTO `sys_file` VALUES (777, 'X7mGSnwQ2p594Zc362GeTpuo1aEUCyHs88aWE5s', 'counter', NULL, NULL, '14c305a862ef7f54bcf0f87928ad13b9', 1061, NULL, '2021-11-05 16:34:04', NULL, '2021-11-05 16:34:04', 1);
INSERT INTO `sys_file` VALUES (778, '8k6baFXIObV5nHR9I4LeU5JaXYxwesduKUQiN', 'counter', NULL, NULL, '866541be8fb811c483d36d04cf398ca2', 38564, NULL, '2021-11-08 15:51:55', NULL, '2021-11-08 15:51:55', 1);
INSERT INTO `sys_file` VALUES (779, 'SfFFFF9fbICKhc7QD3TdTTYHEruUvxx8dHxuI', 'counter', NULL, NULL, '3644db7ae4cbd9274283fec01d27d66a', 49697, NULL, '2021-11-08 15:51:55', NULL, '2021-11-08 15:51:55', 1);
INSERT INTO `sys_file` VALUES (780, '4KsXgJPrqrcnqWJiWJf4ptShmSyeLvo1nyEp9h.svg', 'counter', NULL, 'svg', 'a4e68970d241e13f3180ced82bcc1041', 2509, NULL, '2021-11-08 15:55:11', NULL, '2021-11-08 15:55:11', 1);
INSERT INTO `sys_file` VALUES (781, '656UiiKeoeJzXk0dFgpd8KfyBfjb0ahWijHoGgP.svg', 'counter', NULL, 'svg', '48ca6d3c8d36d7460d10aa70ea096ef', 2334, NULL, '2021-11-08 15:55:51', NULL, '2021-11-08 15:55:51', 1);
INSERT INTO `sys_file` VALUES (782, 'sXE6ELIABoLGwRoPJQ9KysPauavOMQSqS.svg', 'counter', NULL, 'svg', '8fb89814f20191a4e35954b90594937e', 6625, NULL, '2021-11-09 11:56:15', NULL, '2021-11-09 11:56:15', 1);
INSERT INTO `sys_file` VALUES (783, '2j1pUiB8Y9BNpoqQqBRfmvsVoB4ew6pv9Z.svg', 'counter', NULL, 'svg', '56f42290c0ef2745064afbd91ca3b10d', 6413, NULL, '2021-11-09 11:56:57', NULL, '2021-11-09 11:56:57', 1);
INSERT INTO `sys_file` VALUES (784, 'GFHfLqur0JZl29du88ffAwiHbIvpqMzkGOrW6', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 11:58:12', NULL, '2021-11-09 11:58:12', 1);
INSERT INTO `sys_file` VALUES (785, 'NhC6m2E5KZGnRWV9VSq78l53m8sG5QnIhQ8ZA', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 11:58:12', NULL, '2021-11-09 11:58:12', 1);
INSERT INTO `sys_file` VALUES (786, '8mVGLwN5sCyvbU7Ry1aip31rXZEL2CEEY', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 11:58:46', NULL, '2021-11-09 11:58:46', 1);
INSERT INTO `sys_file` VALUES (787, 'UcyPiJpYGCd2fGugvp7rgTBJhnOsAY1F', 'counter', NULL, NULL, '57c119ef02b186084c53ea997e3900be', 119871, NULL, '2021-11-09 11:58:46', NULL, '2021-11-09 11:58:46', 1);
INSERT INTO `sys_file` VALUES (788, 'cEHT7jy4jlwBkA4shFOhaGRsUwPXqEf6yWaIQen', 'counter', NULL, NULL, '57c119ef02b186084c53ea997e3900be', 119871, NULL, '2021-11-09 11:59:54', NULL, '2021-11-09 11:59:54', 1);
INSERT INTO `sys_file` VALUES (789, '9G7QYSo7V8Konwpi2Bz5XhyFZGkpA2qU', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 11:59:54', NULL, '2021-11-09 11:59:54', 1);
INSERT INTO `sys_file` VALUES (790, 'dO3hDjJ0YEjgpHYPnLdXWM0lgGlBJGUlRuYc', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:01:08', NULL, '2021-11-09 12:01:08', 1);
INSERT INTO `sys_file` VALUES (791, 'IyeN9GLNNJJ1hDP75SIpfsi1swoyNP92b', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:01:08', NULL, '2021-11-09 12:01:08', 1);
INSERT INTO `sys_file` VALUES (792, 'AxQnpNK3uJmDYE9m0w5bzEqNVp7SjtVToyuThd', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:01:16', NULL, '2021-11-09 12:01:16', 1);
INSERT INTO `sys_file` VALUES (793, '0fsvmOFQFhWIpHVZ8UPCdvM2exf944yVYWDHJc', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:01:17', NULL, '2021-11-09 12:01:17', 1);
INSERT INTO `sys_file` VALUES (794, 'cNQ8tWvMIpjjX0XQSfAMxgAdT0O4L0Jki1SVKtt', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:02:10', NULL, '2021-11-09 12:02:10', 1);
INSERT INTO `sys_file` VALUES (795, 'IwZe05WZaCYeyyxhKpaqXnzzNnbMrpiDactBu', 'counter', NULL, NULL, '57c119ef02b186084c53ea997e3900be', 119871, NULL, '2021-11-09 12:02:10', NULL, '2021-11-09 12:02:10', 1);
INSERT INTO `sys_file` VALUES (796, 'MvCD0WREgVWZo6D1iruMl79FWSVwyElfC11R', 'counter', NULL, NULL, '57c119ef02b186084c53ea997e3900be', 119871, NULL, '2021-11-09 12:02:24', NULL, '2021-11-09 12:02:24', 1);
INSERT INTO `sys_file` VALUES (797, 'lyTaclpoPXG9SEzmciApj8Nktxx99JLmUuOQaR', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:02:24', NULL, '2021-11-09 12:02:24', 1);
INSERT INTO `sys_file` VALUES (798, 'hGJyk3cF0SKRwgFlrDkgw8bP1yHmm63J6EF', 'counter', NULL, NULL, 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:02:38', NULL, '2021-11-09 12:02:38', 1);
INSERT INTO `sys_file` VALUES (799, 'cDg2Yaiw1R2fXrIU2bAOi3WTHk4ebErcOLhpV3', 'counter', NULL, NULL, '57c119ef02b186084c53ea997e3900be', 119871, NULL, '2021-11-09 12:02:38', NULL, '2021-11-09 12:02:38', 1);
INSERT INTO `sys_file` VALUES (800, 'mxZ79xm5BNuJlUj3Wvfem8HlYN0HIXsRYIwX.svg', 'counter', NULL, 'svg', 'd41d8cd98f00b204e9800998ecf8427e', 0, NULL, '2021-11-09 12:05:32', NULL, '2021-11-09 12:05:32', 1);
INSERT INTO `sys_file` VALUES (801, '1z80FrO0mOZF4paHbJ59jeTKoVhFfHb3I0IpR.svg', 'counter', NULL, 'svg', '76ae30cf3fb7e9d76b4c48ca33939ecb', 1444, NULL, '2021-11-09 13:36:15', NULL, '2021-11-09 13:36:15', 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
                            `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
                            `type` int(0) NULL DEFAULT 0,
                            `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                            `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                            `remote_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
                            `time` bigint(0) NULL DEFAULT NULL COMMENT '执行时间',
                            `enabled` int(0) NULL DEFAULT 1,
                            `exception` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `sys_log_create_by`(`create_by`) USING BTREE,
                            INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
                            INDEX `sys_log_type`(`type`) USING BTREE,
                            INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                             `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID',
                             `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `sort` int(0) NULL DEFAULT 1 COMMENT '排序值',
                             `keep_alive` int(0) NULL DEFAULT 0,
                             `type` int(0) NULL DEFAULT 0,
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             `enabled` int(0) NULL DEFAULT 1,
                             PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1000, '权限管理', NULL, '/user', -1, 'icon-quanxianguanli', 0, 0, 0, '2018-09-28 08:29:53', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1100, '用户管理', NULL, '/admin/user/index', 1000, 'icon-yonghuguanli', 1, 0, 0, '2017-11-02 22:24:37', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, 1, 0, 1, '2017-11-08 09:52:09', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, 1, 0, 1, '2017-11-08 09:52:48', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, 1, 0, 1, '2017-11-08 09:54:01', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1200, '菜单管理', NULL, '/admin/menu/index', 1000, 'icon-caidanguanli', 2, 0, 0, '2017-11-08 09:57:27', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, 1, 0, 1, '2017-11-08 10:15:53', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, 1, 0, 1, '2017-11-08 10:16:23', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, 1, 0, 1, '2017-11-08 10:16:43', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1300, '角色管理', NULL, '/admin/role/index', 1000, 'icon-jiaoseguanli', 3, 0, 0, '2017-11-08 10:13:37', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, 1, 0, 1, '2017-11-08 10:14:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, 1, 0, 1, '2017-11-08 10:14:41', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, 1, 0, 1, '2017-11-08 10:14:59', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1304, '分配权限', 'sys_role_perm', NULL, 1300, NULL, 1, 0, 1, '2018-04-20 07:22:55', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1400, '部门管理', NULL, '/admin/dept/index', 1000, 'icon-web-icon-', 4, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, 1, 0, 1, '2018-01-20 14:56:16', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, 1, 0, 1, '2018-01-20 14:56:59', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, 1, 0, 1, '2018-01-20 14:57:28', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1500, '租户管理', '', '/admin/tenant/index', 1000, 'icon-erji-zuhushouye', 5, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1501, '租户新增', 'admin_systenant_add', NULL, 1500, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1502, '租户修改', 'admin_systenant_edit', NULL, 1500, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (1503, '租户删除', 'admin_systenant_del', NULL, 1500, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2000, '系统管理', NULL, '/admin', -1, 'icon-xitongpeizhi', 1, 0, 0, '2017-11-07 20:56:00', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2100, '日志管理', NULL, '/admin/log/index', 2000, 'icon-rizhi', 5, 0, 0, '2017-11-20 14:06:22', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2101, '日志删除', 'sys_log_del', NULL, 2100, NULL, 1, 0, 1, '2017-11-20 20:37:37', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2200, '字典管理', NULL, '/admin/dict/index', 2000, 'icon-navicon-zdgl', 6, 0, 0, '2017-11-29 11:30:52', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2201, '字典删除', 'sys_dict_del', NULL, 2200, NULL, 1, 0, 1, '2017-11-29 11:30:11', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2202, '字典新增', 'sys_dict_add', NULL, 2200, NULL, 1, 0, 1, '2018-05-11 22:34:55', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2203, '字典修改', 'sys_dict_edit', NULL, 2200, NULL, 1, 0, 1, '2018-05-11 22:36:03', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2210, '参数管理', NULL, '/admin/param/index', 2000, 'icon-canshu', 7, 1, 0, '2019-04-29 22:16:50', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2211, '参数新增', 'admin_syspublicparam_add', NULL, 2210, NULL, 1, 0, 1, '2019-04-29 22:17:36', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2212, '参数删除', 'admin_syspublicparam_del', NULL, 2210, NULL, 1, 0, 1, '2019-04-29 22:17:55', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2213, '参数编辑', 'admin_syspublicparam_edit', NULL, 2210, NULL, 1, 0, 1, '2019-04-29 22:18:14', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2300, '代码生成', '', '/gen/index', 9000, 'icon-weibiaoti46', 1, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2400, '终端管理', '', '/admin/client/index', 2000, 'icon-bangzhushouji', 9, 1, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2401, '客户端新增', 'sys_client_add', NULL, 2400, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2402, '客户端修改', 'sys_client_edit', NULL, 2400, NULL, 1, 0, 1, '2018-05-15 21:37:06', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2403, '客户端删除', 'sys_client_del', NULL, 2400, NULL, 1, 0, 1, '2018-05-15 21:39:16', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2500, '密钥管理', '', '/admin/social/index', 2000, 'icon-miyue', 10, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2501, '密钥新增', 'sys_social_details_add', NULL, 2500, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2502, '密钥修改', 'sys_social_details_edit', NULL, 2500, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2503, '密钥删除', 'sys_social_details_del', NULL, 2500, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2600, '令牌管理', NULL, '/admin/token/index', 2000, 'icon-denglvlingpai', 11, 0, 0, '2018-09-04 05:58:41', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2601, '令牌删除', 'sys_token_del', NULL, 2600, NULL, 1, 0, 1, '2018-09-04 05:59:50', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2700, '动态路由', NULL, '/admin/route/index', 2000, 'icon-luyou', 12, 0, 0, '2018-09-04 05:58:41', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2800, 'Quartz管理', '', '/daemon/job-manage/index', 2000, 'icon-guanwangfangwen', 8, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2810, '任务新增', 'job_sys_job_add', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2820, '任务修改', 'job_sys_job_edit', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2830, '任务删除', 'job_sys_job_del', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2840, '任务暂停', 'job_sys_job_shutdown_job', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2850, '任务开始', 'job_sys_job_start_job', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2860, '任务刷新', 'job_sys_job_refresh_job', NULL, 2800, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (2870, '执行任务', 'job_sys_job_run_job', NULL, 2800, '1', 0, 0, 1, '2019-08-08 15:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3000, '系统监控', NULL, '/daemon', -1, 'icon-msnui-supervise', 2, 0, 2, '2018-07-27 01:13:21', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3100, '服务监控', NULL, 'http://127.0.0.1:5001', 3000, 'icon-server', 0, 0, 0, '2018-06-26 10:50:32', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3101, '流量监控', NULL, 'http://127.0.0.1:5020', 3000, 'icon-liuliang', 0, 0, 0, '2018-06-26 10:50:32', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3110, '缓存监控', NULL, '/monitor/redis/index', 3000, 'icon-qingchuhuancun', 1, 1, 0, '2019-05-08 23:51:27', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3200, '接口文档', NULL, 'http://127.0.0.1:9999/swagger-ui/index.html', 3000, 'icon-wendang', 1, 0, 0, '2018-06-26 10:50:32', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3300, '事务监控', NULL, '/tx/index', 3000, 'icon-gtsquanjushiwufuwuGTS', 5, 0, 0, '2018-08-19 11:02:39', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3400, '在线事务', NULL, '/tx/model', 3000, 'icon-online', 6, 0, 0, '2018-08-19 11:32:04', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3500, '文档扩展', NULL, 'http://127.0.0.1:9999/doc.html', 3000, 'icon-wendang', 2, 0, 0, '2018-06-26 10:50:32', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3600, 'Quartz日志', '', '/daemon/job-log/index', 3000, 'icon-gtsquanjushiwufuwuGTS', 8, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (3700, '注册配置', NULL, '', 3000, 'icon-line', 10, 0, 0, '2018-01-25 11:08:52', '2020-03-24 08:57:37', 1);
INSERT INTO `sys_menu` VALUES (4000, '协同管理', NULL, '/activti', -1, 'icon-kuaisugongzuoliu_o', 3, 0, 0, '2018-09-26 01:38:13', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4100, '模型管理', NULL, '/activiti/index', 4000, 'icon-weibiaoti13', 1, 0, 0, '2018-09-26 01:39:07', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4101, '模型管理', 'act_model_manage', NULL, 4100, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4200, '流程管理', '/activiti/process', '/activiti/process', 4000, 'icon-liucheng', 2, 0, 0, '2018-09-26 06:41:05', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4201, '流程管理', 'act_process_manage', NULL, 4200, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4300, '请假管理', '/activiti/leave', '/activiti/leave', 4000, 'icon-qingjia', 3, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4301, '请假新增', 'act_leavebill_add', NULL, 4300, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4302, '请假修改', 'act_leavebill_edit', NULL, 4300, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4303, '请假删除', 'act_leavebill_del', NULL, 4300, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4400, '待办任务', '/activiti/task', '/activiti/task', 4000, 'icon-renwu', 4, 0, 0, '2018-09-27 09:52:31', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (4401, '流程管理', 'act_task_manage', NULL, 4400, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5000, '支付管理', NULL, '/pay', -1, 'icon-pay6zhifu', 4, 0, 0, '2019-05-30 15:28:03', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5100, '渠道管理', NULL, '/pay/paychannel/index', 5000, 'icon-zhifuqudaoguanli', 1, 0, 0, '2019-05-30 15:32:17', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5110, '增加渠道', 'pay_paychannel_add', NULL, 5100, NULL, 1, 0, 1, '2019-05-30 15:46:14', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5120, '编辑渠道', 'pay_paychannel_edit', NULL, 5100, NULL, 1, 0, 1, '2019-05-30 15:46:35', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5130, '删除渠道', 'pay_paychannel_del', NULL, 5100, NULL, 1, 0, 1, '2019-05-30 15:47:08', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5200, '收银台', NULL, '/pay/cd/index', 5000, 'icon-shouyintai', 0, 0, 0, '2019-05-30 19:44:00', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5300, '商品订单', '', '/pay/goods/index', 5000, 'icon-dingdan', 2, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5310, '商品订单新增', 'generator_paygoodsorder_add', NULL, 5300, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5320, '商品订单修改', 'generator_paygoodsorder_edit', NULL, 5300, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5330, '商品订单删除', 'generator_paygoodsorder_del', NULL, 5300, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5400, '支付订单', '', '/pay/orders/index', 5000, 'icon-zhifu', 3, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5410, '支付订单新增', 'generator_paytradeorder_add', NULL, 5400, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5420, '支付订单修改', 'generator_paytradeorder_edit', NULL, 5400, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5430, '支付订单删除', 'generator_paytradeorder_del', NULL, 5400, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5500, '回调记录', '', '/pay/notify/index', 5000, 'icon-huitiao', 4, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5510, '记录新增', 'generator_paynotifyrecord_add', NULL, 5500, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5520, '记录修改', 'generator_paynotifyrecord_edit', NULL, 5500, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (5530, '记录删除', 'generator_paynotifyrecord_del', NULL, 5500, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6000, '微信管理', NULL, '/mp', -1, 'icon-gongzhonghao', 4, 0, 0, '2018-09-26 01:38:13', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6100, '账号管理', '', '/mp/wxaccount/index', 6000, 'icon-weixincaidan', 8, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6101, '公众号新增', 'mp_wxaccount_add', '', 6100, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6102, '公众号修改', 'mp_wxaccount_edit', NULL, 6100, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6103, '公众号删除', 'mp_wxaccount_del', NULL, 6100, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6200, '粉丝管理', '', '/mp/wxaccountfans/index', 6000, 'icon-fensiguanli', 8, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6201, '粉丝新增', 'mp_wxaccountfans_add', NULL, 6200, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6202, '粉丝修改', 'mp_wxaccountfans_edit', NULL, 6200, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6203, '粉丝删除', 'mp_wxaccountfans_del', NULL, 6200, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6204, '粉丝同步', 'mp_wxaccountfans_sync', NULL, 6200, '1', 3, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6300, '消息管理', '', '/mp/wxfansmsg/index', 6000, 'icon-xiaoxiguanli', 8, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6301, '消息新增', 'mp_wxmsg_add', NULL, 6300, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6302, '消息修改', 'mp_wxmsg_edit', NULL, 6300, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6303, '消息删除', 'mp_wxmsg_del', NULL, 6300, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6400, '菜单设置', NULL, '/mp/wxmenu/index', 6000, 'icon-anniu_weixincaidanlianjie', 6, 0, 0, '2019-03-29 15:20:12', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6401, '保存', 'mp_wxmenu_add', NULL, 6400, NULL, 1, 0, 1, '2019-03-29 15:43:22', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6402, '发布', 'mp_wxmenu_push', NULL, 6400, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6403, '删除', 'mp_wxmenu_del', NULL, 6400, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6500, '运营数据', NULL, '/mp/wxstatistics/index', 6000, 'icon-zhexiantu', 7, 0, 0, '2019-04-14 00:15:35', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6600, '素材管理', NULL, '/mp/wxmaterial/index', 6000, 'icon-sucaisads', 999, 0, 0, '2020-04-27 15:25:17', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6601, '素材维护', 'mp_wxmaterial_add', NULL, 6600, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6602, '素材删除', 'mp_wxmaterial_del', NULL, 6600, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6700, '自动回复', NULL, '/mp/wxautoreply/index', 6000, 'icon-huifu', 998, 0, 0, '2020-04-27 15:25:17', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6701, '新增回复', 'mp_wxautoreply_add', NULL, 6700, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6702, '编辑回复', 'mp_wxautoreply_edit', NULL, 6700, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (6703, '删除回复', 'mp_wxautoreply_del', NULL, 6700, NULL, 1, 0, 1, '2019-03-29 15:43:54', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (7000, '报表设计', NULL, 'http://127.0.0.1:5006/ureport/designer', -1, 'icon-icon-p_mrpbaobiao', 9, 0, 0, '2019-08-12 09:35:16', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (8000, '文件管理', NULL, '/admin/file/index', 2000, 'icon-wenjianguanli', 6, 0, 0, '2019-06-25 12:44:46', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (8001, '删除文件', 'sys_file_del', NULL, 8000, NULL, 1, 0, 1, '2019-06-25 13:41:41', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9000, '开发平台', NULL, '/gen', -1, 'icon-shejiyukaifa-', 9, 0, 0, '2019-08-12 09:35:16', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9001, '表单管理', '', '/gen/form', 9000, 'icon-record', 3, 0, 0, '2018-01-20 13:17:19', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9002, '表单新增', 'gen_form_add', NULL, 9001, '1', 0, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9003, '表单修改', 'gen_form_edit', NULL, 9001, '1', 1, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9004, '表单删除', 'gen_form_del', NULL, 9001, '1', 2, 0, 1, '2018-05-15 21:35:18', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9005, '数据源管理', NULL, '/gen/datasource', 9000, 'icon-mysql', 0, 0, 0, '2019-08-12 09:42:11', '2021-08-31 15:03:06', 1);
INSERT INTO `sys_menu` VALUES (9006, '表单设计', NULL, '/gen/design', 9000, 'icon-biaodanbiaoqian', 2, 0, 0, '2019-08-16 10:08:56', '2021-08-31 15:03:06', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` bigint(0) NOT NULL AUTO_INCREMENT,
                             `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                             `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
                             `enabled` int(0) NULL DEFAULT 1,
                             PRIMARY KEY (`role_id`) USING BTREE,
                             INDEX `role_idx1_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '管理员', '2017-10-29 15:45:51', '2021-08-31 15:02:49', 1);
INSERT INTO `sys_role` VALUES (2, '坐席', 'ROLE_TELLER', '坐席', '2021-09-22 08:29:21', NULL, 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
                                  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
                                  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1304);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 1500);
INSERT INTO `sys_role_menu` VALUES (1, 1501);
INSERT INTO `sys_role_menu` VALUES (1, 1502);
INSERT INTO `sys_role_menu` VALUES (1, 1503);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2210);
INSERT INTO `sys_role_menu` VALUES (1, 2211);
INSERT INTO `sys_role_menu` VALUES (1, 2212);
INSERT INTO `sys_role_menu` VALUES (1, 2213);
INSERT INTO `sys_role_menu` VALUES (1, 2300);
INSERT INTO `sys_role_menu` VALUES (1, 2400);
INSERT INTO `sys_role_menu` VALUES (1, 2401);
INSERT INTO `sys_role_menu` VALUES (1, 2402);
INSERT INTO `sys_role_menu` VALUES (1, 2403);
INSERT INTO `sys_role_menu` VALUES (1, 2500);
INSERT INTO `sys_role_menu` VALUES (1, 2501);
INSERT INTO `sys_role_menu` VALUES (1, 2502);
INSERT INTO `sys_role_menu` VALUES (1, 2503);
INSERT INTO `sys_role_menu` VALUES (1, 2600);
INSERT INTO `sys_role_menu` VALUES (1, 2601);
INSERT INTO `sys_role_menu` VALUES (1, 2700);
INSERT INTO `sys_role_menu` VALUES (1, 2800);
INSERT INTO `sys_role_menu` VALUES (1, 2810);
INSERT INTO `sys_role_menu` VALUES (1, 2820);
INSERT INTO `sys_role_menu` VALUES (1, 2830);
INSERT INTO `sys_role_menu` VALUES (1, 2840);
INSERT INTO `sys_role_menu` VALUES (1, 2850);
INSERT INTO `sys_role_menu` VALUES (1, 2860);
INSERT INTO `sys_role_menu` VALUES (1, 2870);
INSERT INTO `sys_role_menu` VALUES (1, 3000);
INSERT INTO `sys_role_menu` VALUES (1, 3100);
INSERT INTO `sys_role_menu` VALUES (1, 3101);
INSERT INTO `sys_role_menu` VALUES (1, 3110);
INSERT INTO `sys_role_menu` VALUES (1, 3200);
INSERT INTO `sys_role_menu` VALUES (1, 3300);
INSERT INTO `sys_role_menu` VALUES (1, 3400);
INSERT INTO `sys_role_menu` VALUES (1, 3500);
INSERT INTO `sys_role_menu` VALUES (1, 3600);
INSERT INTO `sys_role_menu` VALUES (1, 4000);
INSERT INTO `sys_role_menu` VALUES (1, 4100);
INSERT INTO `sys_role_menu` VALUES (1, 4101);
INSERT INTO `sys_role_menu` VALUES (1, 4200);
INSERT INTO `sys_role_menu` VALUES (1, 4201);
INSERT INTO `sys_role_menu` VALUES (1, 4300);
INSERT INTO `sys_role_menu` VALUES (1, 4301);
INSERT INTO `sys_role_menu` VALUES (1, 4302);
INSERT INTO `sys_role_menu` VALUES (1, 4303);
INSERT INTO `sys_role_menu` VALUES (1, 4400);
INSERT INTO `sys_role_menu` VALUES (1, 4401);
INSERT INTO `sys_role_menu` VALUES (1, 5000);
INSERT INTO `sys_role_menu` VALUES (1, 5100);
INSERT INTO `sys_role_menu` VALUES (1, 5110);
INSERT INTO `sys_role_menu` VALUES (1, 5120);
INSERT INTO `sys_role_menu` VALUES (1, 5130);
INSERT INTO `sys_role_menu` VALUES (1, 5200);
INSERT INTO `sys_role_menu` VALUES (1, 5300);
INSERT INTO `sys_role_menu` VALUES (1, 5310);
INSERT INTO `sys_role_menu` VALUES (1, 5320);
INSERT INTO `sys_role_menu` VALUES (1, 5330);
INSERT INTO `sys_role_menu` VALUES (1, 5400);
INSERT INTO `sys_role_menu` VALUES (1, 5410);
INSERT INTO `sys_role_menu` VALUES (1, 5420);
INSERT INTO `sys_role_menu` VALUES (1, 5430);
INSERT INTO `sys_role_menu` VALUES (1, 5500);
INSERT INTO `sys_role_menu` VALUES (1, 5510);
INSERT INTO `sys_role_menu` VALUES (1, 5520);
INSERT INTO `sys_role_menu` VALUES (1, 5530);
INSERT INTO `sys_role_menu` VALUES (1, 6000);
INSERT INTO `sys_role_menu` VALUES (1, 6100);
INSERT INTO `sys_role_menu` VALUES (1, 6101);
INSERT INTO `sys_role_menu` VALUES (1, 6102);
INSERT INTO `sys_role_menu` VALUES (1, 6103);
INSERT INTO `sys_role_menu` VALUES (1, 6200);
INSERT INTO `sys_role_menu` VALUES (1, 6201);
INSERT INTO `sys_role_menu` VALUES (1, 6202);
INSERT INTO `sys_role_menu` VALUES (1, 6203);
INSERT INTO `sys_role_menu` VALUES (1, 6204);
INSERT INTO `sys_role_menu` VALUES (1, 6300);
INSERT INTO `sys_role_menu` VALUES (1, 6301);
INSERT INTO `sys_role_menu` VALUES (1, 6302);
INSERT INTO `sys_role_menu` VALUES (1, 6303);
INSERT INTO `sys_role_menu` VALUES (1, 6304);
INSERT INTO `sys_role_menu` VALUES (1, 6305);
INSERT INTO `sys_role_menu` VALUES (1, 6400);
INSERT INTO `sys_role_menu` VALUES (1, 6401);
INSERT INTO `sys_role_menu` VALUES (1, 6402);
INSERT INTO `sys_role_menu` VALUES (1, 6500);
INSERT INTO `sys_role_menu` VALUES (1, 6600);
INSERT INTO `sys_role_menu` VALUES (1, 6601);
INSERT INTO `sys_role_menu` VALUES (1, 6602);
INSERT INTO `sys_role_menu` VALUES (1, 6700);
INSERT INTO `sys_role_menu` VALUES (1, 6701);
INSERT INTO `sys_role_menu` VALUES (1, 6702);
INSERT INTO `sys_role_menu` VALUES (1, 6703);
INSERT INTO `sys_role_menu` VALUES (1, 7000);
INSERT INTO `sys_role_menu` VALUES (1, 8000);
INSERT INTO `sys_role_menu` VALUES (1, 8001);
INSERT INTO `sys_role_menu` VALUES (1, 9000);
INSERT INTO `sys_role_menu` VALUES (1, 9001);
INSERT INTO `sys_role_menu` VALUES (1, 9002);
INSERT INTO `sys_role_menu` VALUES (1, 9003);
INSERT INTO `sys_role_menu` VALUES (1, 9004);
INSERT INTO `sys_role_menu` VALUES (1, 9005);
INSERT INTO `sys_role_menu` VALUES (1, 9006);

-- ----------------------------
-- Table structure for sys_social_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_auth_user`;
CREATE TABLE `sys_social_auth_user`  (
                                         `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                         `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
                                         `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
                                         `customer_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
                                         `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
                                         `wx_openid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信登录openId',
                                         `mini_openid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信小程序openId',
                                         `lock_flag` int(0) NULL DEFAULT 0 COMMENT '0-未锁定，1-已锁定',
                                         `enabled` int(0) NULL DEFAULT 1 COMMENT '1-可用；0-禁用',
                                         `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
                                         `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                         `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
                                         `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         PRIMARY KEY (`id`) USING BTREE,
                                         UNIQUE INDEX `phone_unique`(`phone`) USING BTREE,
                                         UNIQUE INDEX `wx_openid_unique`(`wx_openid`) USING BTREE,
                                         UNIQUE INDEX `mini_openid_unique`(`mini_openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户社交账户认证信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_social_auth_user
-- ----------------------------
INSERT INTO `sys_social_auth_user` VALUES (4, '18302040699', NULL, NULL, '18302040699', NULL, NULL, 0, 1, NULL, '2021-09-07 15:05:28', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (5, '15295996760', NULL, NULL, '15295996760', NULL, NULL, 0, 1, NULL, '2021-09-07 15:06:16', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (6, '15295996762', NULL, NULL, '15295996762', NULL, NULL, 0, 1, NULL, '2021-09-07 15:07:17', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (8, '13711726710', NULL, NULL, '13711726710', NULL, NULL, 0, 1, NULL, '2021-09-09 14:03:01', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (9, '13800138000', NULL, NULL, '13800138000', NULL, NULL, 0, 1, NULL, '2021-09-23 10:19:50', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (10, '13800138001', NULL, NULL, '13800138001', NULL, NULL, 0, 1, NULL, '2021-09-23 11:11:09', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (11, '18319981182', NULL, NULL, '18319981182', NULL, NULL, 0, 1, NULL, '2021-10-08 14:31:37', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (12, '15767526746', NULL, NULL, '15767526746', NULL, NULL, 0, 1, NULL, '2021-10-12 19:35:50', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (13, '18127639031', NULL, NULL, '18127639031', NULL, NULL, 0, 1, NULL, '2021-10-15 11:29:04', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (14, '13711726544', NULL, NULL, '13711726544', NULL, NULL, 0, 1, NULL, '2021-10-18 16:18:11', NULL, NULL, NULL);
INSERT INTO `sys_social_auth_user` VALUES (15, '13751710142', NULL, NULL, '13751710142', NULL, NULL, 0, 1, NULL, '2021-11-09 11:44:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                             `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                             `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
                             `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                             `lock_flag` int(0) NULL DEFAULT 0 COMMENT '0-未锁定，1-已锁定',
                             `enabled` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '1-可用；0-禁用',
                             `user_sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '1-男；0-女',
                             `login_time` datetime(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`user_id`) USING BTREE,
                             INDEX `user_idx1_username`(`nick_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('101', '1001', '坐席1号', '$2a$10$7Zw8ZCQiRsnLs7xIumMEaOJvCoLirFjUMz089vQbHjdV0nzZ38fuq', '13138001380', NULL, '2', '2021-09-16 14:34:19', '2021-10-21 14:26:12', 0, '1', '0', NULL);
INSERT INTO `sys_user` VALUES ('102', '1002', '坐席2号', '$2a$10$7Zw8ZCQiRsnLs7xIumMEaOJvCoLirFjUMz089vQbHjdV0nzZ38fuq', '13138001380', NULL, '2', '2021-09-16 14:34:42', '2021-10-21 14:26:17', 0, '1', '0', NULL);
INSERT INTO `sys_user` VALUES ('103', '1003', '坐席3号', '$2a$10$7Zw8ZCQiRsnLs7xIumMEaOJvCoLirFjUMz089vQbHjdV0nzZ38fuq', '13138001380', NULL, '2', '2021-10-16 14:21:45', '2021-10-21 14:26:23', 0, '1', '0', NULL);
INSERT INTO `sys_user` VALUES ('104', '1004', '坐席4号', '$2a$10$7Zw8ZCQiRsnLs7xIumMEaOJvCoLirFjUMz089vQbHjdV0nzZ38fuq', '13138001380', NULL, '2', '2021-10-16 14:36:43', '2021-10-26 16:04:15', 0, '1', '0', NULL);
INSERT INTO `sys_user` VALUES ('105', '1005', '坐席5号', '$2a$10$7Zw8ZCQiRsnLs7xIumMEaOJvCoLirFjUMz089vQbHjdV0nzZ38fuq', '13138001380', NULL, '2', '2021-10-21 17:55:17', '2021-10-21 17:55:43', 0, '1', '0', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
                                  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (101, 1);
INSERT INTO `sys_user_role` VALUES (102, 2);
INSERT INTO `sys_user_role` VALUES (103, 2);

SET FOREIGN_KEY_CHECKS = 1;
