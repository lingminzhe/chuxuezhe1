/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : grg-cloud-counter-iam

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 30/08/2021 10:42:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙',
  `scope` varchar(256) DEFAULT NULL COMMENT '指定客户端申请的权限范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '指定客户端支持的grant_type',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '客户端的重定向URI',
  `authorities` varchar(256) DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒)',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '设置用户是否自动Approval操作',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('web-client', NULL, '$2a$10$uV/VUTBZgPyHX16GSP21pO.ArPPohUXrtXgDS17EXyOL3CAl7W1q6', 'all', 'authorization_code,password,refresh_token,implicit,client_credentials', NULL, NULL, NULL, NULL, NULL, 'true');
COMMIT;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `AREA_PATH` text NOT NULL COMMENT '结构path',
  `I18N_CODE` varchar(100) DEFAULT NULL COMMENT '国际码',
  `AREA_CODE` varchar(100) NOT NULL COMMENT '区域编码',
  `PARENT_CODE` varchar(32) NOT NULL COMMENT '父编码',
  `NAME` varchar(200) NOT NULL COMMENT '名称',
  `DESCRIPTION` text COMMENT '描述',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
BEGIN;
INSERT INTO `sys_area` VALUES (1, 'zhongguo', NULL, 'zhongguo', '0', '中国', '大中国', '1', 'admin', '2021-03-09 16:59:00', 'cyfeng', '2021-04-01 16:23:21', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_area_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_area_user`;
CREATE TABLE `sys_area_user` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `AREA_ID` bigint(22) NOT NULL COMMENT '区域ID',
  `USER_ID` bigint(32) NOT NULL COMMENT '用户ID',
  `IS_LEADER` char(1) DEFAULT NULL COMMENT '是否负责人',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1395565163988627459 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统区域用户关系表';

-- ----------------------------
-- Records of sys_area_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_area_user` VALUES (1393316192847949825, 1, 1, '1', 'admin', '2021-05-15 05:24:01', NULL, NULL);
INSERT INTO `sys_area_user` VALUES (1395565163988627458, 1, 1395565163565002754, '1', 'admin', '2021-05-21 02:20:38', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `AUTHORITY` varchar(255) NOT NULL COMMENT '权限标志',
  `APP_NAME` varchar(255) NOT NULL COMMENT '所属服务名称',
  `APP_DESC` varchar(255) DEFAULT NULL COMMENT '所属服务描述',
  `CLASS_NAME` varchar(255) DEFAULT NULL COMMENT '类名',
  `METHOD_NAME` varchar(255) DEFAULT NULL COMMENT '方法名',
  `FULL_METHOD_NAME` varchar(255) DEFAULT NULL COMMENT '完整方法名',
  `MODULE_NAME` varchar(255) DEFAULT NULL COMMENT '所属模块',
  `MODULE_DESC` varchar(255) DEFAULT NULL COMMENT '所属模块描述',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用(0:停用;1:启用)',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1395275444776890370 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限标识';

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
BEGIN;
INSERT INTO `sys_authority` VALUES (2, 'sys:area:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:28', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (3, 'sys:area:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:28', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (4, 'sys:area:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:28', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (5, 'sys:area:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:28', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (7, 'sys:org:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:28', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (8, 'sys:org:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (9, 'sys:org:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (10, 'sys:org:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (12, 'sys:user:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (13, 'sys:user:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (14, 'sys:user:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (15, 'sys:user:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:29', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (18, 'sys:user:resetpwd', 'aim', NULL, 'grgms-sys-service', 'grgms-sys-service', NULL, 'grgms-sys-service', NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', 'cjia', '2021-05-14 20:05:32', NULL);
INSERT INTO `sys_authority` VALUES (20, 'sys:role:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (21, 'sys:role:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (22, 'sys:role:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (23, 'sys:role:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (25, 'sys:menu:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:30', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (26, 'sys:menu:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (27, 'sys:menu:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (28, 'sys:menu:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (30, 'sys:authority:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (31, 'sys:authority:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (32, 'sys:authority:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (33, 'sys:authority:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:31', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (35, 'sys:dict:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (36, 'sys:dict:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (37, 'sys:dict:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (38, 'sys:dict:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (40, 'sys:i18n:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (41, 'sys:i18n:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (42, 'sys:i18n:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (43, 'sys:i18n:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:32', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (91, 'sys:busiconf:query', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (92, 'sys:biz:conf:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (93, 'sys:biz:conf:switch', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (94, 'sys:biz:conf:edit', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (95, 'sys:biz:conf:delete', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (103, 'sys:log:add', 'aim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'admin', '2021-03-09 16:39:33', NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (1393175301122453505, 'sys:user:resetlock', 'aim', NULL, 'grgms-sys-service', 'grgms-sys-service', NULL, 'grgms-sys-service', NULL, NULL, '1', 'admin', '2021-05-14 20:04:10', 'admin', '2021-05-14 20:04:10', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_biz_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_biz_conf`;
CREATE TABLE `sys_biz_conf` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `BIZ_CODE` varchar(32) NOT NULL COMMENT '业务键',
  `BIZ_INFO` varchar(200) NOT NULL COMMENT '业务值',
  `APP_TYPE` varchar(32) NOT NULL COMMENT '应用类型',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  `IS_ENABLED` char(1) NOT NULL COMMENT '启用状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='业务配置表';

-- ----------------------------
-- Records of sys_biz_conf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `APP_TYPE` varchar(32) DEFAULT NULL COMMENT '应用类别',
  `CODE_TYPE` varchar(128) DEFAULT NULL COMMENT '代码类型',
  `CODE_VALUE` varchar(128) NOT NULL COMMENT '代码值',
  `CODE_VALUE_NAME` varchar(128) NOT NULL COMMENT '代码值名称',
  `I18N_CODE` varchar(128) DEFAULT NULL COMMENT '国际化编码',
  `CODE_ORDER` int(4) DEFAULT NULL COMMENT '顺序',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1391051630975152131 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统字典码表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, 'grgmsSys', 'applicationName', 'grgms-sys-service', 'grgms-sys-service', 'dict.applicationName.grgms-sys-service', 1, '1', 'cyfeng', '2021-03-11 14:30:24', 'lfeng18', '2021-05-13 16:48:00', NULL);
INSERT INTO `sys_dict` VALUES (6, 'grgmsSys', 'i18nDataType', 'system', 'system', NULL, 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (7, 'grgmsSys', 'i18nDataType', 'menu', 'menu', NULL, 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (8, 'grgmsSys', 'i18nDataType', 'button', 'button', NULL, 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (9, 'grgmsSys', 'i18nDataType', 'form', 'form', NULL, 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (10, 'grgmsSys', 'i18nDataType', 'gird', 'gird', NULL, 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (11, 'grgmsSys', 'i18nDataType', 'exception', 'exception', NULL, 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (12, 'grgmsSys', 'i18nDataType', 'dict', 'dict', NULL, 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (13, 'grgmsSys', 'languageType', 'zh', '中文', 'dict.languageType.zh', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (14, 'grgmsSys', 'languageType', 'en', '英文', 'dict.languageType.en', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (15, 'grgmsSys', 'isEnabled', '0', '停用', 'dict.isEnabled.0', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (16, 'grgmsSys', 'isEnabled', '1', '启用', 'dict.isEnabled.1', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (17, 'grgmsSys', 'userGender', '1', '男', 'dict.userGender.1', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (18, 'grgmsSys', 'userGender', '2', '女', 'dict.userGender.2', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (19, 'grgmsSys', 'idType', '100001', '身份证', 'dict.idType.100001', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (20, 'grgmsSys', 'idType', '100002', '军人证', 'dict.idType.10002', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (21, 'grgmsSys', 'idType', '100003', '护照', 'dict.idType.100003', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (22, 'grgmsSys', 'idType', '100004', '户口本', 'dict.idType.100004', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (23, 'grgmsSys', 'idType', '100005', '外国人永久居留证', 'dict.idType.100005', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (24, 'grgmsSys', 'idType', '100006', '武警证', 'dict.idType.100005', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (25, 'grgmsSys', 'idType', '100007', '港澳通行证', 'dict.idType.100007', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (26, 'grgmsSys', 'idType', '100008', '台湾通行证', 'dict.idType.100008', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (27, 'grgmsSys', 'serviceOps', 'elk', '日志管理', 'dict.serviceOps.elk', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (28, 'grgmsSys', 'serviceOps', 'sonarqube', '代码质量检查', 'dict.serviceOps.sonarqube', 1, '1', 'admin', '2021-03-09 16:34:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (29, 'grgmsSys', 'serviceOps', 'fileService', '文件服务', 'dict.serviceOps.fileService', 1, '1', 'admin', '2021-03-09 16:34:02', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (30, 'grgmsSys', 'serviceOps', 'springBootAdmin', '服务监控', 'dict.serviceOps.springBootAdmin', 1, '1', 'admin', '2021-03-09 16:34:02', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (31, 'grgmsSys', 'serviceOps', 'nacos', '注册/配置中心', 'dict.serviceOps.nacos', 1, '1', 'admin', '2021-03-09 16:34:02', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (32, 'grgmsSys', 'serviceOps', 'SkyWalking', '服务链路追踪', 'dict.serviceOps.SkyWalking', 1, '1', 'admin', '2021-03-09 16:34:02', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (33, 'grgmsSys', 'applyType', '1', '授权服务', NULL, 1, '1', 'admin', '2021-03-09 16:35:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (34, 'grgmsSys', 'applyType', '2', '设备管理', NULL, 2, '1', 'admin', '2021-03-09 16:35:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (35, 'grgmsSys', 'roleType', 'manage', '系统管理角色', 'dict.roleType.manage', 1, '1', 'admin', '2021-03-09 16:35:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (36, 'grgmsSys', 'roleType', 'system', '系统用户角色', 'dict.roleType.system', 1, '1', 'admin', '2021-03-09 16:35:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (37, 'grgmsSys', 'menuType', '-1', '系统', 'dict.menuType.-1', 1, '1', 'cyfeng2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (38, 'grgmsSys', 'menuType', '1', '菜单', 'dict.menuType.1', 1, '1', 'cyfeng2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (39, 'grgmsSys', 'menuType', '2', '按钮', 'dict.menuType.2', 1, '1', 'cyfeng2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (40, 'grgmsSys', 'menuType', '0', '目录', 'dict.menuType.0', 1, '1', 'cyfeng2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (41, 'grgmsSys', 'applicationType', 'grgmsSys', '统一用户', 'dict.applicationType.grgmsSys', 1, '1', 'admin', '2021-03-09 16:33:59', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (42, 'grgmsSys', 'applicationType', 'grgmsGateway', '网关服务', 'dict.applicationType.grgmsGateway', 1, '1', 'admin', '2021-03-09 16:33:59', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (43, 'grgmsSys', 'applicationType', 'grgmsRegister', '注册/配置中心', 'dict.applicationType.grgmsRegister', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (44, 'grgmsSys', 'applicationType', 'grgmsScheduler', '调度系统', 'dict.applicationType.grgmsScheduler', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (45, 'grgmsSys', 'applicationType', 'grgmsAuth', '授权服务', 'dict.applicationType.grgmsAuth', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (46, 'grgmsSys', 'applicationType', 'grgmsDevice', '设备管理', 'dict.applicationType.grgmsDevice', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (47, 'grgmsSys', 'applicationType', 'grgmsFileserver', '文件服务', 'dict.applicationType.grgmsFileserver', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (48, 'grgmsSys', 'applicationType', 'grgmsIl', '终端接入', 'dict.applicationType.grgmsIl', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (49, 'grgmsSys', 'applicationType', 'grgmsItx', '联机交易服务', 'dict.applicationType.grgmsItx', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (50, 'grgmsSys', 'applicationType', 'grgmsMam', '运营管理', 'dict.applicationType.grgmsMam', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (51, 'grgmsSys', 'applicationType', 'grgmsMessage', '消息管理', 'dict.applicationType.grgmsMessage', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (52, 'grgmsSys', 'applicationType', 'grgmsResource', '终端静态资源服务', 'dict.applicationType.grgmsResource', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (53, 'grgmsSys', 'applicationType', 'grgmsSent', '冠字号服务', 'dict.applicationType.grgmsSent', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (54, 'grgmsSys', 'applicationType', 'grgmsCommon', '公共服务', 'dict.applicationType.grgmsCommon', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (55, 'grgmsSys', 'applicationType', 'grgmsOpr', '微服务组件管理', 'dict.applicationType.grgmsOpr', 1, '1', 'admin', '2021-03-09 16:34:00', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1372086479814799362, 'grgmsSys', 'opsType', '1', '加钞维护商', 'dict.opsType.1', 1, '1', 'wyzhao', '2021-03-17 15:24:43', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1372433923903299585, 'grgmsSys', 'dataType', '0', '数字类型', 'dict.number', 1, '1', 'cyfeng', '2021-03-18 14:25:20', 'lfeng', '2021-03-23 11:05:14', NULL);
INSERT INTO `sys_dict` VALUES (1372434087133028354, 'grgmsSys', 'dataType', '1', '字符类型', 'dict.string', 2, '1', 'cyfeng', '2021-03-18 14:25:59', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1372434352837992589, 'grgmsSys', 'dataType', '3', '日期类型(yyyy-MM-dd)', 'dict.date', 8, '1', 'cyfeng', '2021-03-18 14:27:02', 'admin', '2021-03-25 16:27:54', NULL);
INSERT INTO `sys_dict` VALUES (1373182417489129473, 'grgmsSys', 'applicationType', 'grgmsWallet', '数字钱包', 'dict.applicationType.grgmsWallet', 18, '1', 'wyzhao', '2021-03-20 15:59:35', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1373808019703361537, 'grgmsSys', 'imptStatus', '1', '成功', 'dict.imptStatus.1', 1, '1', 'lfeng', '2021-03-22 09:25:30', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1373808108324810753, 'grgmsSys', 'imptStatus', '2', '失败', 'dict.imptStatus.2', 2, '1', 'lfeng', '2021-03-22 09:25:51', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1373907382781898753, 'grgmsSys', 'isMust', '1', '必录', 'dict.isMust.1', 1, '1', 'lfeng', '2021-03-22 16:00:20', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1373907454525468673, 'grgmsSys', 'isMust', '0', '非必录', 'dict.isMust.0', 0, '1', 'lfeng', '2021-03-22 16:00:37', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1374917267878518786, 'grgmsSys', 'dataType', '2', '日期类型(yyyy-MM-dd HH:mm:ss)', 'dict.date', 6, '1', 'cyfeng', '2021-03-25 10:53:16', 'cyfeng', '2021-03-25 11:24:27', NULL);
INSERT INTO `sys_dict` VALUES (1374917679641731074, 'grgmsSys', 'dataType', '4', '日期类型(HH:mm:ss)', 'dict.date', 9, '1', 'cyfeng', '2021-03-25 10:54:54', 'admin', '2021-03-25 16:27:47', NULL);
INSERT INTO `sys_dict` VALUES (1375290457856176130, 'grgmsSys', 'lockStatus', '0', '未锁定', 'dict.lockStatus.0', 1, '1', 'lfeng', '2021-03-26 11:36:11', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1375290529465528321, 'grgmsSys', 'lockStatus', '1', '已锁定', 'dict.lockStatus.1', 2, '1', 'lfeng', '2021-03-26 11:36:28', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1377166990258499585, 'grgmsSys', 'applicationName', 'ibankpro-mam-service', 'ibankpro-mam-service', 'dict.applicationName.ibankpro-mam-service', 3, '1', 'lggui', '2021-03-31 15:52:51', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1379327322485714946, 'grgmsSys', 'applicationName', 'ibankpro-il-mng-service', 'ibankpro-il-mng-service', 'dict.applicationName.ibankpro-il-mng-service', 2, '1', 'admin', '2021-04-06 14:57:15', 'hhui12', '2021-04-06 15:29:43', NULL);
INSERT INTO `sys_dict` VALUES (1380058293128425473, 'grgmsSys', 'applicationType', 'ibankproIlCos', '终端接入服务', 'dict.applicationType.ibankproIlCos', 19, '1', 'liufeng', '2021-04-08 15:21:51', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1382574450574131202, 'grgmsSys', 'applicationName', 'grgms-scheduler-service', 'grgms-scheduler-service', 'dict.applicationName.grgms-scheduler-service', 4, '1', 'liufeng', '2021-04-15 14:00:10', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1386688630889672705, 'grgmsSys', 'opsType', '2', 'ATM维护商', 'dict.opsType.2', 2, '1', 'cjia', '2021-04-26 22:28:27', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1386689148370317314, 'grgmsSys', 'opsType', '3', '测试维护商', 'dict.opsType.3', 3, '1', 'cjia', '2021-04-26 22:30:31', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1388050826227904513, 'grgmsSys', 'roleType', 'workticket.manager', '工单管理角色', 'dict.roleType.workticket.manager', 1, '1', 'cyfeng', '2021-04-30 16:41:20', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1388050997506502658, 'grgmsSys', 'roleType', 'workticket.engineer', '工单用户角色', 'dict.roleType.workticket.engineer', 1, '1', 'cyfeng', '2021-04-30 16:42:01', NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1391051630975152130, 'grgmsSys', 'applicationType', 'ibankproCms', '智慧云屏台', 'dict.applicationType.ibankproCms', 1, '1', 'admin', '2021-05-08 23:25:27', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_i18n
-- ----------------------------
DROP TABLE IF EXISTS `sys_i18n`;
CREATE TABLE `sys_i18n` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `APP_TYPE` varchar(32) DEFAULT NULL COMMENT '应用类别',
  `DATA_TYPE` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `I18N_CODE` varchar(128) NOT NULL COMMENT '国际化编码',
  `I18N_LANGUAGE_TYPE` varchar(16) NOT NULL COMMENT '国际化语言类型',
  `I18N_VALUE` varchar(128) NOT NULL COMMENT '国际化值',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1397815341156954114 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统多语言维护信息表';

-- ----------------------------
-- Records of sys_i18n
-- ----------------------------
BEGIN;
INSERT INTO `sys_i18n` VALUES (1, 'grgmsSys', 'btn', 'btn.add', 'zh', '新增', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (2, 'grgmsSys', 'btn', 'btn.add', 'en', 'add', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (3, 'grgmsSys', 'btn', 'btn.edit', 'zh', '编辑', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (4, 'grgmsSys', 'btn', 'btn.edit', 'en', 'edit', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (5, 'grgmsSys', 'btn', 'btn.delete', 'zh', '删除', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (6, 'grgmsSys', 'btn', 'btn.delete', 'en', 'delete', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (7, 'grgmsSys', 'btn', 'btn.export', 'zh', '导出', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (8, 'grgmsSys', 'btn', 'btn.export', 'en', 'export', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (9, 'grgmsSys', 'btn', 'btn.query', 'zh', '查询', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (10, 'grgmsSys', 'btn', 'btn.query', 'en', 'query', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (11, 'grgmsSys', 'btn', 'btn.reset', 'zh', '重置', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (12, 'grgmsSys', 'btn', 'btn.reset', 'en', 'reset', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (15, 'grgmsSys', 'btn', 'btn.save', 'zh', '保存', 'admin', '2021-03-09 16:37:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (16, 'grgmsSys', 'btn', 'btn.save', 'en', 'save', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (17, 'grgmsSys', 'exception', 'exception.com1001', 'zh', '空指针异常', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (18, 'grgmsSys', 'exception', 'exception.com1001', 'en', 'Null pointer exception', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (19, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSys', 'zh', '系统管理平台', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (20, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSys', 'en', 'System management platform', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (21, 'grgmsSys', 'dict', 'dict.applicationType.grgmsFileserver', 'zh', '文件服务', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (22, 'grgmsSys', 'dict', 'dict.applicationType.grgmsFileserver', 'en', 'Document service', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (23, 'grgmsSys', 'dict', 'dict.applicationType.grgmsGateway', 'zh', '网关服务', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (24, 'grgmsSys', 'dict', 'dict.applicationType.grgmsGateway', 'en', 'Gateway Services', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (25, 'grgmsSys', 'dict', 'dict.applicationType.grgmsRegister', 'zh', '注册/配置中心', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (26, 'grgmsSys', 'dict', 'dict.applicationType.grgmsRegister', 'en', 'Registration / Configuration center', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (27, 'grgmsSys', 'dict', 'dict.applicationType.grgmsScheduler', 'zh', '调度系统', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (28, 'grgmsSys', 'dict', 'dict.applicationType.grgmsScheduler', 'en', 'Dispatching system', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (33, 'grgmsSys', 'dict', 'dict.isEnabled.0', 'zh', '停用', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (34, 'grgmsSys', 'dict', 'dict.isEnabled.0', 'en', 'Disable', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (35, 'grgmsSys', 'dict', 'dict.isEnabled.1', 'zh', '启用', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (36, 'grgmsSys', 'dict', 'dict.isEnabled.1', 'en', 'Enable', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (37, 'grgmsSys', 'dict', 'dict.userGender.1', 'zh', '男', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (38, 'grgmsSys', 'dict', 'dict.userGender.1', 'en', 'Male', 'admin', '2021-03-09 16:37:19', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (41, 'grgmsSys', 'dict', 'dict.roleType.manage', 'zh', '系统管理角色', 'admin', '2021-03-09 16:37:20', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (42, 'grgmsSys', 'dict', 'dict.roleType.manage', 'en', 'System management role', 'admin', '2021-03-09 16:37:20', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (43, 'grgmsSys', 'dict', 'dict.roleType.system', 'zh', '系统用户角色', 'admin', '2021-03-09 16:37:20', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (44, 'grgmsSys', 'dict', 'dict.roleType.system', 'en', 'System user role', 'admin', '2021-03-09 16:37:21', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (570, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSys', 'zh', '统一用户', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (571, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSys', 'en', 'grgmsSys', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (572, 'grgmsSys', 'dict', 'dict.applicationType.grgmsGateway', 'zh', '网关服务', 'admin', '2021-03-09 16:37:16', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (573, 'grgmsSys', 'dict', 'dict.applicationType.grgmsGateway', 'en', 'grgmsGateway', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (574, 'grgmsSys', 'dict', 'dict.applicationType.grgmsRegister', 'zh', '注册/配置中心', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (575, 'grgmsSys', 'dict', 'dict.applicationType.grgmsRegister', 'en', 'grgmsRegister', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (576, 'grgmsSys', 'dict', 'dict.applicationType.grgmsScheduler', 'zh', '调度系统', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (577, 'grgmsSys', 'dict', 'dict.applicationType.grgmsScheduler', 'en', 'grgmsScheduler', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (578, 'grgmsSys', 'dict', 'dict.applicationType.grgmsAuth', 'zh', '授权服务', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (579, 'grgmsSys', 'dict', 'dict.applicationType.grgmsAuth', 'en', 'grgmsAuth', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (580, 'grgmsSys', 'dict', 'dict.applicationType.grgmsDevice', 'zh', '设备管理', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (581, 'grgmsSys', 'dict', 'dict.applicationType.grgmsDevice', 'en', 'grgmsDevice', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (582, 'grgmsSys', 'dict', 'dict.applicationType.grgmsFileserver', 'zh', '文件服务', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (583, 'grgmsSys', 'dict', 'dict.applicationType.grgmsFileserver', 'en', 'grgmsFileserver', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (584, 'grgmsSys', 'dict', 'dict.applicationType.grgmsIl', 'zh', '终端接入', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (585, 'grgmsSys', 'dict', 'dict.applicationType.grgmsIl', 'en', 'grgmsIl', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (586, 'grgmsSys', 'dict', 'dict.applicationType.grgmsItx', 'zh', '联机交易服务', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (587, 'grgmsSys', 'dict', 'dict.applicationType.grgmsItx', 'en', 'grgmsItx', 'admin', '2021-03-09 16:37:17', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (588, 'grgmsSys', 'dict', 'dict.applicationType.grgmsMam', 'zh', '运营管理', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (589, 'grgmsSys', 'dict', 'dict.applicationType.grgmsMam', 'en', 'grgmsMam', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (590, 'grgmsSys', 'dict', 'dict.applicationType.grgmsMessage', 'zh', '消息管理', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (591, 'grgmsSys', 'dict', 'dict.applicationType.grgmsMessage', 'en', 'grgmsMessage', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (592, 'grgmsSys', 'dict', 'dict.applicationType.grgmsResource', 'zh', '终端静态资源服务', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (593, 'grgmsSys', 'dict', 'dict.applicationType.grgmsResource', 'en', 'grgmsResource', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (594, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSent', 'zh', '冠字号服务', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (595, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSent', 'en', 'grgmsSent', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (596, 'grgmsSys', 'dict', 'dict.applicationType.grgmsCommon', 'zh', '公共服务', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (597, 'grgmsSys', 'dict', 'dict.applicationType.grgmsCommon', 'en', 'grgmsCommon', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (598, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSysWeb', 'zh', '统一用户前端', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (599, 'grgmsSys', 'dict', 'dict.applicationType.grgmsSysWeb', 'en', 'grgmsSysWeb', 'admin', '2021-03-09 16:37:18', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1001, 'grgmsSys', 'exception', 'exception.com1007', 'zh', '用户不存在', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1002, 'grgmsSys', 'exception', 'exception.com2001', 'zh', 'ID列表不能为空', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1003, 'grgmsSys', 'exception', 'exception.com2002', 'zh', 'ID不能为空', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1004, 'grgmsSys', 'exception', 'exception.com2003', 'zh', '启用状态不能为空', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1005, 'grgmsSys', 'exception', 'exception.com2004', 'zh', '应用类型不能为空', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1006, 'grgmsSys', 'exception', 'exception.login1001', 'zh', '密码错误，累计错误次数达到3次，账户被锁定15分钟', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1007, 'grgmsSys', 'exception', 'exception.login1002', 'zh', '累计错误次数达到3次，账户被锁定15分钟', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1008, 'grgmsSys', 'exception', 'exception.area1001', 'zh', '无法删除已被用户使用的区域', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1009, 'grgmsSys', 'exception', 'exception.area1002', 'zh', '启用失败', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010, 'grgmsSys', 'exception', 'exception.area1003', 'zh', '停用失败', 'admin', '2021-03-19 10:37:33', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1011, 'grgmsSys', 'exception', 'exception.area1004', 'zh', '父区域未启用，不可操作', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1012, 'grgmsSys', 'exception', 'exception.area1005', 'zh', '子区域未禁用，不可操作', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1013, 'grgmsSys', 'exception', 'exception.area1006', 'zh', '区域名称已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1014, 'grgmsSys', 'exception', 'exception.area1007', 'zh', '区域编码已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1015, 'grgmsSys', 'exception', 'exception.area1008', 'zh', '国际化编码已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1016, 'grgmsSys', 'exception', 'exception.area1009', 'zh', '区域结构path已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1017, 'grgmsSys', 'exception', 'exception.area1010', 'zh', '删除失败', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1018, 'grgmsSys', 'exception', 'exception.area1011', 'zh', '操作失败', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1019, 'grgmsSys', 'exception', 'exception.org1001', 'zh', '无法删除已被用户使用的机构', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1020, 'grgmsSys', 'exception', 'exception.org1002', 'zh', '请先启用父机构', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1021, 'grgmsSys', 'exception', 'exception.org1003', 'zh', '机构未找到', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1022, 'grgmsSys', 'exception', 'exception.org1004', 'zh', '无法删除存在子节点的机构', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1023, 'grgmsSys', 'exception', 'exception.org1005', 'zh', '请先停用子机构', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1024, 'grgmsSys', 'exception', 'exception.org1006', 'zh', '请先停用关联用户', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1025, 'grgmsSys', 'exception', 'exception.org1007', 'zh', '机构编码不能为空', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1026, 'grgmsSys', 'exception', 'exception.org1008', 'zh', '启用状态不能为空', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1027, 'grgmsSys', 'exception', 'exception.org1009', 'zh', '国际化编码不能为空', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1028, 'grgmsSys', 'exception', 'exception.org1010', 'zh', '区域不能为空', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1029, 'grgmsSys', 'exception', 'exception.org1011', 'zh', '机构名称不能为空', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1030, 'grgmsSys', 'exception', 'exception.org1012', 'zh', '机构编码不允许为0', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1031, 'grgmsSys', 'exception', 'exception.org1013', 'zh', '机构编码已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1032, 'grgmsSys', 'exception', 'exception.org1014', 'zh', '机构名称已存在', 'admin', '2021-03-19 10:37:34', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1033, 'grgmsSys', 'exception', 'exception.org1015', 'zh', '国际化编码已存在', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1034, 'grgmsSys', 'exception', 'exception.org1016', 'zh', '机构全称已存在', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1035, 'grgmsSys', 'exception', 'exception.user1001', 'zh', '不能删除所属机构相同的用户', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1036, 'grgmsSys', 'exception', 'exception.user1002', 'zh', '用户账号不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1037, 'grgmsSys', 'exception', 'exception.user1003', 'zh', '用户不存在', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1038, 'grgmsSys', 'exception', 'exception.user1004', 'zh', '超级管理员不能停用', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1039, 'grgmsSys', 'exception', 'exception.user1005', 'zh', '原始密码错误', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1040, 'grgmsSys', 'exception', 'exception.user1006', 'zh', '新旧密码相同', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1041, 'grgmsSys', 'exception', 'exception.user1007', 'zh', '无法删除超管用户', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1042, 'grgmsSys', 'exception', 'exception.user1008', 'zh', '邮箱格式不正确', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1043, 'grgmsSys', 'exception', 'exception.user1009', 'zh', '电话格式不正确', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1044, 'grgmsSys', 'exception', 'exception.user1011', 'zh', '账号格式不正确', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1045, 'grgmsSys', 'exception', 'exception.user1012', 'zh', '姓名不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1046, 'grgmsSys', 'exception', 'exception.user1013', 'zh', '移动电话不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1047, 'grgmsSys', 'exception', 'exception.user1014', 'zh', '账号启用状态不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1048, 'grgmsSys', 'exception', 'exception.user1015', 'zh', '角色不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1049, 'grgmsSys', 'exception', 'exception.user1016', 'zh', '管理区域不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1050, 'grgmsSys', 'exception', 'exception.user1017', 'zh', '所属机构不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1051, 'grgmsSys', 'exception', 'exception.user1018', 'zh', '管理机构不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1052, 'grgmsSys', 'exception', 'exception.user1019', 'zh', '密码不能为空', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1053, 'grgmsSys', 'exception', 'exception.user1020', 'zh', '账号已存在', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1054, 'grgmsSys', 'exception', 'exception.user1021', 'zh', '所选管理区域不在范围内', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1055, 'grgmsSys', 'exception', 'exception.user1022', 'zh', '所属机构需要在所选区域内', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1056, 'grgmsSys', 'exception', 'exception.user1023', 'zh', '管理机构要在所属机构内', 'admin', '2021-03-19 10:37:35', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1057, 'grgmsSys', 'exception', 'exception.user1024', 'zh', '用户已被禁用', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1058, 'grgmsSys', 'exception', 'exception.role1001', 'zh', '只可以编辑自己创建的角色', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1059, 'grgmsSys', 'exception', 'exception.role1002', 'zh', '角色名称不能为空', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1060, 'grgmsSys', 'exception', 'exception.role1003', 'zh', '角色类型不能为空', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1061, 'grgmsSys', 'exception', 'exception.role1004', 'zh', '启用状态不能为空', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1062, 'grgmsSys', 'exception', 'exception.role1005', 'zh', '可使用菜单不能为空', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1063, 'grgmsSys', 'exception', 'exception.role1006', 'zh', '启用状态不正确', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1064, 'grgmsSys', 'exception', 'exception.role1007', 'zh', '角色名称已存在', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1065, 'grgmsSys', 'exception', 'exception.role1008', 'zh', '只可以停/启用自己创建的角色', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1066, 'grgmsSys', 'exception', 'exception.role1009', 'zh', '有用户使用的角色不可停用', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1067, 'grgmsSys', 'exception', 'exception.role1010', 'zh', '无法停用超管角色', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1068, 'grgmsSys', 'exception', 'exception.role1011', 'zh', '无法删除超管角色', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1069, 'grgmsSys', 'exception', 'exception.role1012', 'zh', '只可以删除自己创建的角色', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1070, 'grgmsSys', 'exception', 'exception.role1013', 'zh', '当前角色有用户使用无法删除', 'admin', '2021-03-19 10:37:36', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1071, 'grgmsSys', 'exception', 'exception.bizConf1001', 'zh', '该业务配置信息不存在', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1072, 'grgmsSys', 'exception', 'exception.bizConf1002', 'zh', '业务键不能为空', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1073, 'grgmsSys', 'exception', 'exception.bizConf1003', 'zh', '业务值不能为空', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1074, 'grgmsSys', 'exception', 'exception.bizConf1004', 'zh', '业务键应唯一', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1075, 'grgmsSys', 'exception', 'exception.menu1001', 'zh', '无法删除有角色关联的菜单', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1076, 'grgmsSys', 'exception', 'exception.menu1002', 'zh', '无法删除有启用子菜单的菜单', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1077, 'grgmsSys', 'exception', 'exception.menu1003', 'zh', '有启用状态的子级菜单不可停用', 'admin', '2021-03-19 10:38:49', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1078, 'grgmsSys', 'exception', 'exception.menu1004', 'zh', '有停用状态的父级菜单不可启用', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1079, 'grgmsSys', 'exception', 'exception.menu1005', 'zh', '菜单不存在', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1080, 'grgmsSys', 'exception', 'exception.menu1006', 'zh', '菜单编码应唯一', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1081, 'grgmsSys', 'exception', 'exception.menu1007', 'zh', '父编码不能为空', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1082, 'grgmsSys', 'exception', 'exception.menu1008', 'zh', '编码不能为空', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1083, 'grgmsSys', 'exception', 'exception.menu1009', 'zh', '资源类型不能为空', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1084, 'grgmsSys', 'exception', 'exception.menu1010', 'zh', '名称不能为空', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1085, 'grgmsSys', 'exception', 'exception.menu1011', 'zh', '所选父菜单不存在', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1086, 'grgmsSys', 'exception', 'exception.menu1012', 'zh', '系统下只能为目录类型', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1087, 'grgmsSys', 'exception', 'exception.menu1013', 'zh', '目录名称重复', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1088, 'grgmsSys', 'exception', 'exception.menu1014', 'zh', '目录下不能直接添加按钮类型', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1089, 'grgmsSys', 'exception', 'exception.menu1015', 'zh', '名称重复', 'admin', '2021-03-19 10:38:50', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1090, 'grgmsSys', 'exception', 'exception.menu1016', 'zh', '菜单下只能为按钮类型', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1091, 'grgmsSys', 'exception', 'exception.menu1017', 'zh', '按钮名称重复', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1092, 'grgmsSys', 'exception', 'exception.menu1018', 'zh', '按钮下不能添加其他类型', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1093, 'grgmsSys', 'exception', 'exception.menu1019', 'zh', '当前菜单下有子菜单，不能更改父节点或菜单类型', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1094, 'grgmsSys', 'exception', 'exception.menu1020', 'zh', '系统下不能为系统类型', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1095, 'grgmsSys', 'exception', 'exception.authority1001', 'zh', '同一个所属服务下，权限标识不能相同', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1096, 'grgmsSys', 'exception', 'exception.authority1002', 'zh', '无法删除授权给菜单的标识!', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1097, 'grgmsSys', 'exception', 'exception.authority1003', 'zh', '所属服务名不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1098, 'grgmsSys', 'exception', 'exception.authority1004', 'zh', '所属模块名不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1099, 'grgmsSys', 'exception', 'exception.authority1005', 'zh', '权限标识不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1100, 'grgmsSys', 'exception', 'exception.authority1006', 'zh', '所属类名不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1101, 'grgmsSys', 'exception', 'exception.authority1007', 'zh', '所属方法名不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1102, 'grgmsSys', 'exception', 'exception.dict1001', 'zh', '应用类别、代码类型、代码值三者组合值不能重复', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1103, 'grgmsSys', 'exception', 'exception.dict1002', 'zh', '字典不存在', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1104, 'grgmsSys', 'exception', 'exception.dict1003', 'zh', '该字典的国际化编码为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1105, 'grgmsSys', 'exception', 'exception.i18n1001', 'zh', '应用类别、数据类型、国际化编码、语言类型组合值不能重复', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1106, 'grgmsSys', 'exception', 'exception.i18n1002', 'zh', '国际化编码不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1107, 'grgmsSys', 'exception', 'exception.i18n1003', 'zh', '应用类别不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1108, 'grgmsSys', 'exception', 'exception.i18n1004', 'zh', '数据类型不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1109, 'grgmsSys', 'exception', 'exception.i18n1005', 'zh', '国际化语言类型不能为空', 'admin', '2021-03-19 10:38:51', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1110, 'grgmsSys', 'exception', 'exception.i18n1006', 'zh', '国际化值不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1111, 'grgmsSys', 'exception', 'exception.i18n1007', 'zh', '该国际化值对应数据不存在', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1112, 'grgmsSys', 'exception', 'exception.importBatch1001', 'zh', '批次编号不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1113, 'grgmsSys', 'exception', 'exception.importBatch1002', 'zh', '业务入库数量不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1114, 'grgmsSys', 'exception', 'exception.importBatch1003', 'zh', '上传导入模板文件名格式有误', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1115, 'grgmsSys', 'exception', 'exception.importBatch1004', 'zh', '文件名不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1116, 'grgmsSys', 'exception', 'exception.importBatch1005', 'zh', '导入模板必须是模板配置下载的模板', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1117, 'grgmsSys', 'exception', 'exception.importBatch1006', 'zh', '导入模板列数与模板配置列数不符', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1118, 'grgmsSys', 'exception', 'exception.importBatch1007', 'zh', '没有上传模板文件', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1119, 'grgmsSys', 'exception', 'exception.importBatch1008', 'zh', '模板文件没有添加数据', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1120, 'grgmsSys', 'exception', 'exception.importBatch1009', 'zh', '模板文件数据错误', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1121, 'grgmsSys', 'exception', 'exception.importBatch1010', 'zh', '模板文件数据类型错误', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1122, 'grgmsSys', 'exception', 'exception.importBatch1011', 'zh', '导入数据的字典项不存在', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1123, 'grgmsSys', 'exception', 'exception.importBind1001', 'zh', '属性列值不能超过30', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1124, 'grgmsSys', 'exception', 'exception.importBind1002', 'zh', '该业务模板字段属性列已存在', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1125, 'grgmsSys', 'exception', 'exception.importBind1003', 'zh', '该业务模板不存在', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1126, 'grgmsSys', 'exception', 'exception.importBind1004', 'zh', '应用类型不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1127, 'grgmsSys', 'exception', 'exception.importBind1005', 'zh', '业务编号不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1128, 'grgmsSys', 'exception', 'exception.importBind1006', 'zh', '列注释不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1129, 'grgmsSys', 'exception', 'exception.importBind1007', 'zh', '是否必录不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1130, 'grgmsSys', 'exception', 'exception.importBind1008', 'zh', '数据类型不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1131, 'grgmsSys', 'exception', 'exception.importBind1009', 'zh', '数据长度不能为空', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1132, 'grgmsSys', 'exception', 'exception.importBind1010', 'zh', '国际化编码对应的列注释不匹配', 'admin', '2021-03-19 10:38:52', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1133, 'grgmsSys', 'exception', 'exception.importBind1011', 'zh', '国际化编码格式不正确，应为xxx.xxx格式', 'admin', '2021-03-19 10:38:53', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1134, 'grgmsSys', 'exception', 'exception.importBind1012', 'zh', '列字段不能为空', 'admin', '2021-03-19 10:38:53', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1135, 'grgmsSys', 'exception', 'exception.importTemplate1001', 'zh', '业务类型不能为空', 'admin', '2021-03-19 10:38:53', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1136, 'grgmsSys', 'exception', 'exception.importTemplate1002', 'zh', '应用类型应与业务编号组合唯一', 'admin', '2021-03-19 10:38:53', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1137, 'grgmsSys', 'exception', 'exception.importTemplate1003', 'zh', '无此模板信息', 'admin', '2021-03-19 10:38:53', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010010, 'grgmsSys', 'exception', 'exception.AccessDenied', 'zh', '全局访问拒绝', 'admin', '2021-05-14 10:53:14', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010011, 'grgmsSys', 'exception', 'exception.AccessDenied', 'en', 'Global Access Denied', 'admin', '2021-05-14 10:53:14', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010012, 'grgmsSys', 'exception', 'exception.InvalidToken', 'zh', '全局无效令牌', 'admin', '2021-05-14 10:53:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010013, 'grgmsSys', 'exception', 'exception.InvalidToken', 'en', 'Global Invalid Token', 'admin', '2021-05-14 10:53:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010014, 'grgmsSys', 'exception', 'exception.logout1003', 'zh', '过期令牌', 'admin', '2021-05-14 10:53:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010015, 'grgmsSys', 'exception', 'exception.logout1003', 'en', 'Expired Token', 'admin', '2021-05-14 10:53:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010018, 'grgmsSys', 'exception', 'exception.logout1005', 'zh', '检测您之前切换了子系统，但子系统不在同一个身份验证体系，您即将被强制退出', 'admin', '2021-05-14 10:53:15', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1010019, 'grgmsSys', 'exception', 'exception.logout1005', 'en', 'You have switched the subsystem before, but the subsystem is not in the same authentication system', 'admin', '2021-05-14 13:26:43', NULL, NULL, NULL);
INSERT INTO `sys_i18n` VALUES (1352166946330136577, 'grgmsSys', 'login', 'login.title', 'zh', '认证中心', 'lggui2', '2021-01-21 16:11:36', 'lggui2', '2021-01-21 16:11:36', NULL);
INSERT INTO `sys_i18n` VALUES (1352166946388856834, 'grgmsSys', 'login', 'login.title', 'en', 'Authentication Center)', 'lggui2', '2021-01-21 16:11:36', 'lggui2', '2021-01-21 16:11:36', NULL);
INSERT INTO `sys_i18n` VALUES (1352167175175557122, 'grgmsSys', 'login', 'login.account', 'zh', '账号', 'lggui2', '2021-01-21 16:12:31', 'lggui2', '2021-01-21 16:12:31', NULL);
INSERT INTO `sys_i18n` VALUES (1352167175200722945, 'grgmsSys', 'login', 'login.account', 'en', 'Account', 'lggui2', '2021-01-21 16:12:31', 'lggui2', '2021-01-21 16:12:31', NULL);
INSERT INTO `sys_i18n` VALUES (1352167273292910594, 'grgmsSys', 'login', 'login.password', 'zh', '密码', 'lggui2', '2021-01-21 16:12:54', 'lggui2', '2021-01-21 16:12:54', NULL);
INSERT INTO `sys_i18n` VALUES (1352167273313882113, 'grgmsSys', 'login', 'login.password', 'en', 'Password', 'lggui2', '2021-01-21 16:12:54', 'lggui2', '2021-01-21 16:12:54', NULL);
INSERT INTO `sys_i18n` VALUES (1352167458135887873, 'grgmsSys', 'login', 'login.login', 'zh', '登录', 'lggui2', '2021-01-21 16:13:38', 'lggui2', '2021-01-21 16:13:38', NULL);
INSERT INTO `sys_i18n` VALUES (1352167458152665089, 'grgmsSys', 'login', 'login.login', 'en', 'Login', 'lggui2', '2021-01-21 16:13:38', 'lggui2', '2021-01-21 16:13:38', NULL);
INSERT INTO `sys_i18n` VALUES (1352167779373436929, 'grgmsSys', 'login', 'login.valueNotEmpty', 'zh', '不允许为空', 'lggui2', '2021-01-21 16:14:55', 'lggui2', '2021-01-21 16:14:55', NULL);
INSERT INTO `sys_i18n` VALUES (1352167779390214146, 'grgmsSys', 'login', 'login.valueNotEmpty', 'en', 'Value Not Empty', 'lggui2', '2021-01-21 16:14:55', 'lggui2', '2021-01-21 16:14:55', NULL);
INSERT INTO `sys_i18n` VALUES (1352167886097502210, 'grgmsSys', 'login', 'login.errorUserOrpwd', 'zh', '账号或密码有误', 'lggui2', '2021-01-21 16:15:20', 'lggui2', '2021-01-21 16:15:20', NULL);
INSERT INTO `sys_i18n` VALUES (1352167886105890817, 'grgmsSys', 'login', 'login.errorUserOrpwd', 'en', 'User or Password Error', 'lggui2', '2021-01-21 16:15:20', 'lggui2', '2021-01-21 16:15:20', NULL);
INSERT INTO `sys_i18n` VALUES (1352167960403791874, 'grgmsSys', 'login', 'login.errorOvertime', 'zh', '请求超时', 'lggui2', '2021-01-21 16:15:38', 'lggui2', '2021-01-21 16:15:38', NULL);
INSERT INTO `sys_i18n` VALUES (1352167960424763394, 'grgmsSys', 'login', 'login.errorOvertime', 'en', 'Request Overtime', 'lggui2', '2021-01-21 16:15:38', 'lggui2', '2021-01-21 16:15:38', NULL);
INSERT INTO `sys_i18n` VALUES (1352447377660387330, 'grgmsSys', 'dict', 'dict.roleType.system', 'zh', '系统用户角色', 'lggui2', '2021-01-22 10:45:56', 'lggui2', '2021-01-22 10:45:56', NULL);
INSERT INTO `sys_i18n` VALUES (1352447377710718977, 'grgmsSys', 'dict', 'dict.roleType.system', 'en', 'System User Role', 'lggui2', '2021-01-22 10:45:56', 'lggui2', '2021-01-22 10:45:56', NULL);
INSERT INTO `sys_i18n` VALUES (1353906044988387330, 'grgmsSys', 'i18n', 'i18n.i18nCode', 'zh', '国际化编码', 'lggui2', '2021-01-26 11:22:10', 'lggui2', '2021-01-26 11:22:10', NULL);
INSERT INTO `sys_i18n` VALUES (1353906048985559042, 'grgmsSys', 'i18n', 'i18n.i18nCode', 'en', 'Code', 'lggui2', '2021-01-26 11:22:11', 'lggui2', '2021-01-26 11:22:11', NULL);
INSERT INTO `sys_i18n` VALUES (1353912763587522561, 'grgmsSys', 'i18n', 'i18n.i18nValue', 'zh', '国际化值', 'lggui2', '2021-01-26 11:48:52', 'lggui2', '2021-01-26 11:48:52', NULL);
INSERT INTO `sys_i18n` VALUES (1353912763965009922, 'grgmsSys', 'i18n', 'i18n.i18nValue', 'en', 'Value', 'lggui2', '2021-01-26 11:48:52', 'lggui2', '2021-01-26 11:48:52', NULL);
INSERT INTO `sys_i18n` VALUES (1353945896551194626, 'grgmsSys', 'i18n', 'i18n.dataType', 'zh', '数据类型', 'lggui2', '2021-01-26 14:00:31', 'lggui2', '2021-01-26 14:00:31', NULL);
INSERT INTO `sys_i18n` VALUES (1353945896576360450, 'grgmsSys', 'i18n', 'i18n.dataType', 'en', 'Data Type', 'lggui2', '2021-01-26 14:00:31', 'lggui2', '2021-01-26 14:00:31', NULL);
INSERT INTO `sys_i18n` VALUES (1353948517940961282, 'grgmsSys', 'i18n', 'i18n.i18nLanguageType', 'zh', '语言类型', 'lggui2', '2021-01-26 14:10:56', 'lggui2', '2021-01-26 14:10:56', NULL);
INSERT INTO `sys_i18n` VALUES (1353948518033235970, 'grgmsSys', 'i18n', 'i18n.i18nLanguageType', 'en', 'Language Type', 'lggui2', '2021-01-26 14:10:56', 'lggui2', '2021-01-26 14:10:56', NULL);
INSERT INTO `sys_i18n` VALUES (1353953142861565954, 'grgmsSys', 'login', 'login.remember', 'zh', '记住我', 'lggui2', '2021-01-26 14:29:19', 'lggui2', '2021-01-26 14:29:19', NULL);
INSERT INTO `sys_i18n` VALUES (1353953142911897601, 'grgmsSys', 'login', 'login.remember', 'en', 'Remember', 'lggui2', '2021-01-26 14:29:19', 'lggui2', '2021-01-26 14:29:19', NULL);
INSERT INTO `sys_i18n` VALUES (1353955965388988417, 'grgmsSys', 'auth', 'auth.appName', 'zh', '所属服务名', 'lggui2', '2021-01-26 14:40:32', 'lggui2', '2021-01-26 14:40:32', NULL);
INSERT INTO `sys_i18n` VALUES (1353955965418348545, 'grgmsSys', 'auth', 'auth.appName', 'en', 'App Name', 'lggui2', '2021-01-26 14:40:32', 'lggui2', '2021-01-26 14:40:32', NULL);
INSERT INTO `sys_i18n` VALUES (1353956345946578945, 'grgmsSys', 'auth', 'auth.appDesc', 'zh', '服务描述', 'lggui2', '2021-01-26 14:42:02', 'lggui2', '2021-01-26 14:42:02', NULL);
INSERT INTO `sys_i18n` VALUES (1353956345959161857, 'grgmsSys', 'auth', 'auth.appDesc', 'en', 'App Desc', 'lggui2', '2021-01-26 14:42:02', 'lggui2', '2021-01-26 14:42:02', NULL);
INSERT INTO `sys_i18n` VALUES (1353956517829156865, 'grgmsSys', 'auth', 'auth.moduleName', 'zh', '所属模块名', 'lggui2', '2021-01-26 14:42:43', 'lggui2', '2021-01-26 14:42:43', NULL);
INSERT INTO `sys_i18n` VALUES (1353956517841739778, 'grgmsSys', 'auth', 'auth.moduleName', 'en', 'Module Name', 'lggui2', '2021-01-26 14:42:43', 'lggui2', '2021-01-26 14:42:43', NULL);
INSERT INTO `sys_i18n` VALUES (1353956590851989506, 'grgmsSys', 'auth', 'auth.moduleDesc', 'zh', '模块描述', 'lggui2', '2021-01-26 14:43:01', 'lggui2', '2021-01-26 14:43:01', NULL);
INSERT INTO `sys_i18n` VALUES (1353956590860378114, 'grgmsSys', 'auth', 'auth.moduleDesc', 'en', 'Module Desc', 'lggui2', '2021-01-26 14:43:01', 'lggui2', '2021-01-26 14:43:01', NULL);
INSERT INTO `sys_i18n` VALUES (1353956669700710402, 'grgmsSys', 'auth', 'auth.authority', 'zh', '权限标识', 'lggui2', '2021-01-26 14:43:20', 'lggui2', '2021-01-26 14:43:20', NULL);
INSERT INTO `sys_i18n` VALUES (1353956669721681921, 'grgmsSys', 'auth', 'auth.authority', 'en', 'Authority', 'lggui2', '2021-01-26 14:43:20', 'lggui2', '2021-01-26 14:43:20', NULL);
INSERT INTO `sys_i18n` VALUES (1353956743478517761, 'grgmsSys', 'auth', 'auth.description', 'zh', '标识描述', 'lggui2', '2021-01-26 14:43:37', 'lggui2', '2021-01-26 14:43:37', NULL);
INSERT INTO `sys_i18n` VALUES (1353956743499489281, 'grgmsSys', 'auth', 'auth.description', 'en', 'Description', 'lggui2', '2021-01-26 14:43:37', 'lggui2', '2021-01-26 14:43:37', NULL);
INSERT INTO `sys_i18n` VALUES (1353956812936192001, 'grgmsSys', 'auth', 'auth.className', 'zh', '所属类名', 'lggui2', '2021-01-26 14:43:54', 'lggui2', '2021-01-26 14:43:54', NULL);
INSERT INTO `sys_i18n` VALUES (1353956812957163521, 'grgmsSys', 'auth', 'auth.className', 'en', 'Class Name', 'lggui2', '2021-01-26 14:43:54', 'lggui2', '2021-01-26 14:43:54', NULL);
INSERT INTO `sys_i18n` VALUES (1353956895308128257, 'grgmsSys', 'auth', 'auth.methodName', 'zh', '所属方法名', 'lggui2', '2021-01-26 14:44:13', 'lggui2', '2021-01-26 14:44:13', NULL);
INSERT INTO `sys_i18n` VALUES (1353956895329099778, 'grgmsSys', 'auth', 'auth.methodName', 'en', 'Method Name', 'lggui2', '2021-01-26 14:44:13', 'lggui2', '2021-01-26 14:44:13', NULL);
INSERT INTO `sys_i18n` VALUES (1353960512198569985, 'grgmsSys', 'com', 'com.isEnabled', 'zh', '启用状态', 'lggui2', '2021-01-26 14:58:36', 'lggui2', '2021-01-26 14:58:36', NULL);
INSERT INTO `sys_i18n` VALUES (1353960512295038977, 'grgmsSys', 'com', 'com.isEnabled', 'en', 'isEnabled', 'lggui2', '2021-01-26 14:58:36', 'lggui2', '2021-01-26 14:58:36', NULL);
INSERT INTO `sys_i18n` VALUES (1353963854480044034, 'grgmsSys', 'com', 'com.appType', 'zh', '应用类型', 'lggui2', '2021-01-26 15:11:52', 'lggui2', '2021-01-26 15:11:52', NULL);
INSERT INTO `sys_i18n` VALUES (1353963854563930113, 'grgmsSys', 'com', 'com.appType', 'en', 'App Type', 'lggui2', '2021-01-26 15:11:53', 'lggui2', '2021-01-26 15:11:53', NULL);
INSERT INTO `sys_i18n` VALUES (1353964661136977921, 'grgmsSys', 'dic', 'dic.codeValue', 'zh', '代码值', 'lggui2', '2021-01-26 15:15:05', 'lggui2', '2021-01-26 15:15:05', NULL);
INSERT INTO `sys_i18n` VALUES (1353964661212475393, 'grgmsSys', 'dic', 'dic.codeValue', 'en', 'Code Value', 'lggui2', '2021-01-26 15:15:05', 'lggui2', '2021-01-26 15:15:05', NULL);
INSERT INTO `sys_i18n` VALUES (1353964776018964482, 'grgmsSys', 'dic', 'dic.codeValueName', 'zh', '代码值名称', 'lggui2', '2021-01-26 15:15:32', 'lggui2', '2021-01-26 15:15:32', NULL);
INSERT INTO `sys_i18n` VALUES (1353964776111239169, 'grgmsSys', 'dic', 'dic.codeValueName', 'en', 'Code Value Name', 'lggui2', '2021-01-26 15:15:32', 'lggui2', '2021-01-26 15:15:32', NULL);
INSERT INTO `sys_i18n` VALUES (1353964875235225602, 'grgmsSys', 'dic', 'dic.i18nCode', 'zh', '国际化编码', 'lggui2', '2021-01-26 15:15:56', 'lggui2', '2021-01-26 15:15:56', NULL);
INSERT INTO `sys_i18n` VALUES (1353964875365249025, 'grgmsSys', 'dic', 'dic.i18nCode', 'en', 'I18n Code', 'lggui2', '2021-01-26 15:15:56', 'lggui2', '2021-01-26 15:15:56', NULL);
INSERT INTO `sys_i18n` VALUES (1353965084296114177, 'grgmsSys', 'dic', 'dic.codeType', 'zh', '代码类型', 'lggui2', '2021-01-26 15:16:46', 'lggui2', '2021-01-26 15:16:46', NULL);
INSERT INTO `sys_i18n` VALUES (1353965084363223042, 'grgmsSys', 'dic', 'dic.codeType', 'en', 'Code Type', 'lggui2', '2021-01-26 15:16:46', 'lggui2', '2021-01-26 15:16:46', NULL);
INSERT INTO `sys_i18n` VALUES (1372438821718749185, 'grgmsSys', 'btn', 'btn.clear', 'zh', '重置', 'lfeng', '2021-03-18 14:44:48', 'lfeng', '2021-03-18 14:44:48', NULL);
INSERT INTO `sys_i18n` VALUES (1372438823279030274, 'grgmsSys', 'btn', 'btn.clear', 'en', 'Clear', 'lfeng', '2021-03-18 14:44:48', 'lfeng', '2021-03-18 14:44:48', NULL);
INSERT INTO `sys_i18n` VALUES (1372443596560560129, 'grgmsSys', 'btn', 'btn.search', 'zh', '查询', 'lfeng', '2021-03-18 15:03:46', 'lfeng', '2021-03-18 15:03:46', NULL);
INSERT INTO `sys_i18n` VALUES (1372443597030322178, 'grgmsSys', 'btn', 'btn.search', 'en', 'Search', 'lfeng', '2021-03-18 15:03:46', 'lfeng', '2021-03-18 15:03:46', NULL);
INSERT INTO `sys_i18n` VALUES (1372449577545789442, 'grgmsSys', 'btn', 'btn.expand', 'zh', '展开', 'lfeng', '2021-03-18 15:27:32', 'lfeng', '2021-03-18 15:27:32', NULL);
INSERT INTO `sys_i18n` VALUES (1372449577948442625, 'grgmsSys', 'btn', 'btn.expand', 'en', 'Expand', 'lfeng', '2021-03-18 15:27:32', 'lfeng', '2021-03-18 15:27:32', NULL);
INSERT INTO `sys_i18n` VALUES (1372449744688803842, 'grgmsSys', 'btn', 'btn.collapse', 'zh', '收起', 'lfeng', '2021-03-18 15:28:12', 'lfeng', '2021-03-18 15:28:12', NULL);
INSERT INTO `sys_i18n` VALUES (1372449745011765249, 'grgmsSys', 'btn', 'btn.collapse', 'en', 'Collapse', 'lfeng', '2021-03-18 15:28:12', 'lfeng', '2021-03-18 15:28:12', NULL);
INSERT INTO `sys_i18n` VALUES (1372449902175633409, 'grgmsSys', 'exception', 'exception.com1002', 'zh', '类型转换异常', 'cyfeng', '2021-03-18 15:28:50', 'cyfeng', '2021-03-18 15:28:50', NULL);
INSERT INTO `sys_i18n` VALUES (1372449902699921409, 'grgmsSys', 'exception', 'exception.com1002', 'en', 'Type conversion exception', 'cyfeng', '2021-03-18 15:28:50', 'cyfeng', '2021-03-18 15:28:50', NULL);
INSERT INTO `sys_i18n` VALUES (1372450030508752898, 'grgmsSys', 'exception', 'exception.com1003', 'zh', '数组越界异常', 'cyfeng', '2021-03-18 15:29:20', 'cyfeng', '2021-03-18 15:29:20', NULL);
INSERT INTO `sys_i18n` VALUES (1372450030626193410, 'grgmsSys', 'exception', 'exception.com1003', 'en', 'Array out of bounds exception', 'cyfeng', '2021-03-18 15:29:20', 'cyfeng', '2021-03-18 15:29:20', NULL);
INSERT INTO `sys_i18n` VALUES (1372450153632546817, 'grgmsSys', 'exception', 'exception.com1004', 'zh', '未查询到数据', 'cyfeng', '2021-03-18 15:29:50', 'cyfeng', '2021-03-18 15:29:50', NULL);
INSERT INTO `sys_i18n` VALUES (1372450153737404417, 'grgmsSys', 'exception', 'exception.com1004', 'en', 'No data found', 'cyfeng', '2021-03-18 15:29:50', 'cyfeng', '2021-03-18 15:29:50', NULL);
INSERT INTO `sys_i18n` VALUES (1372450263619780609, 'grgmsSys', 'exception', 'exception.com1005', 'zh', '参数不能为空', 'cyfeng', '2021-03-18 15:30:16', 'cyfeng', '2021-03-18 15:30:16', NULL);
INSERT INTO `sys_i18n` VALUES (1372450263741415425, 'grgmsSys', 'exception', 'exception.com1005', 'en', 'Parameter cannot be empty', 'cyfeng', '2021-03-18 15:30:16', 'cyfeng', '2021-03-18 15:30:16', NULL);
INSERT INTO `sys_i18n` VALUES (1372450426220363778, 'grgmsSys', 'exception', 'exception.com1006', 'zh', '操作失败', 'cyfeng', '2021-03-18 15:30:55', 'cyfeng', '2021-03-18 15:30:55', NULL);
INSERT INTO `sys_i18n` VALUES (1372450426329415681, 'grgmsSys', 'exception', 'exception.com1006', 'en', 'operation failed', 'cyfeng', '2021-03-18 15:30:55', 'cyfeng', '2021-03-18 15:30:55', NULL);
INSERT INTO `sys_i18n` VALUES (1372788146978140162, 'grgmsSys', 'status', 'status.isEnabled.0', 'en', 'Disable', 'zlping3', '2021-03-19 13:52:54', 'zlping3', '2021-03-19 13:52:54', NULL);
INSERT INTO `sys_i18n` VALUES (1372842524040790018, 'grgmsSys', 'org', 'org.name.icbc', 'zh', '中国工商银行', 'lfeng', '2021-03-19 17:28:58', 'lfeng', '2021-03-19 17:28:58', NULL);
INSERT INTO `sys_i18n` VALUES (1372842524141453314, 'grgmsSys', 'org', 'org.name.icbc', 'en', 'Industrial and Commercial Bank of China Limited', 'lfeng', '2021-03-19 17:28:58', 'lfeng', '2021-03-19 17:28:58', NULL);
INSERT INTO `sys_i18n` VALUES (1372881463699079169, 'grgmsSys', 'msg', 'msg.input', 'zh', '请输入{0}', 'lfeng', '2021-03-19 20:03:42', 'lfeng', '2021-03-19 20:03:42', NULL);
INSERT INTO `sys_i18n` VALUES (1372881526735273985, 'grgmsSys', 'msg', 'msg.select', 'zh', '请选择{0}', 'lfeng', '2021-03-19 20:03:57', 'lfeng', '2021-03-19 20:03:57', NULL);
INSERT INTO `sys_i18n` VALUES (1372881619639107585, 'grgmsSys', 'msg', 'msg.requestTimeout', 'zh', '请求超时', 'lfeng', '2021-03-19 20:04:19', 'lfeng', '2021-03-19 20:04:19', NULL);
INSERT INTO `sys_i18n` VALUES (1372881675129749505, 'grgmsSys', 'msg', 'msg.networkError', 'zh', '网络错误', 'lfeng', '2021-03-19 20:04:32', 'lfeng', '2021-03-19 20:04:32', NULL);
INSERT INTO `sys_i18n` VALUES (1372882900856373250, 'grgmsSys', 'msg', 'msg.maxByte', 'zh', '最多{0}个中文，{1}个英文', 'lfeng', '2021-03-19 20:09:25', 'lfeng', '2021-03-19 20:09:25', NULL);
INSERT INTO `sys_i18n` VALUES (1372883231614992385, 'grgmsSys', 'msg', 'msg.duplication', 'zh', '{0}重复', 'lfeng', '2021-03-19 20:10:44', 'lfeng', '2021-03-19 20:10:44', NULL);
INSERT INTO `sys_i18n` VALUES (1372883340058722305, 'grgmsSys', 'msg', 'msg.success', 'zh', '操作成功', 'lfeng', '2021-03-19 20:11:09', 'lfeng', '2021-03-19 20:11:09', NULL);
INSERT INTO `sys_i18n` VALUES (1372883544614928386, 'grgmsSys', 'msg', 'msg.confirm', 'zh', '确认{0}', 'lfeng', '2021-03-19 20:11:58', 'lfeng', '2021-03-19 20:11:58', NULL);
INSERT INTO `sys_i18n` VALUES (1372883952833953793, 'grgmsSys', 'com', 'com.operate', 'zh', '操作', 'lfeng', '2021-03-19 20:13:35', 'lfeng', '2021-03-19 20:13:35', NULL);
INSERT INTO `sys_i18n` VALUES (1372884310947823617, 'grgmsSys', 'com', 'com.i18nCode', 'zh', '国际化编码', 'lfeng', '2021-03-19 20:15:01', 'lfeng', '2021-03-19 20:15:01', NULL);
INSERT INTO `sys_i18n` VALUES (1372884378086047745, 'grgmsSys', 'com', 'com.detail', 'zh', '详情', 'lfeng', '2021-03-19 20:15:17', 'lfeng', '2021-03-19 20:15:17', NULL);
INSERT INTO `sys_i18n` VALUES (1372884489419653122, 'grgmsSys', 'com', 'com.createdBy', 'zh', '创建人', 'lfeng', '2021-03-19 20:15:43', 'lfeng', '2021-03-19 20:15:43', NULL);
INSERT INTO `sys_i18n` VALUES (1372884547963748353, 'grgmsSys', 'com', 'com.creationDate', 'zh', '创建日期', 'lfeng', '2021-03-19 20:15:57', 'lfeng', '2021-03-19 20:15:57', NULL);
INSERT INTO `sys_i18n` VALUES (1372884599075536897, 'grgmsSys', 'com', 'com.lastUpdatedBy', 'zh', '修改人', 'lfeng', '2021-03-19 20:16:10', 'lfeng', '2021-03-19 20:16:10', NULL);
INSERT INTO `sys_i18n` VALUES (1372884656931766273, 'grgmsSys', 'com', 'com.lastUpdateDate', 'zh', '修改日期', 'lfeng', '2021-03-19 20:16:23', 'lfeng', '2021-03-19 20:16:23', NULL);
INSERT INTO `sys_i18n` VALUES (1372884860003188737, 'grgmsSys', 'com', 'com.ip', 'zh', 'IP', 'lfeng', '2021-03-19 20:17:12', 'lfeng', '2021-03-19 20:17:12', NULL);
INSERT INTO `sys_i18n` VALUES (1372885508480335873, 'grgmsSys', 'com', 'com.describes', 'zh', '描述', 'lfeng', '2021-03-19 20:19:46', 'lfeng', '2021-03-19 20:19:46', NULL);
INSERT INTO `sys_i18n` VALUES (1372885600553697281, 'grgmsSys', 'com', 'com.statusOff', 'zh', '停用', 'lfeng', '2021-03-19 20:20:08', 'lfeng', '2021-03-19 20:20:08', NULL);
INSERT INTO `sys_i18n` VALUES (1372885661312385025, 'grgmsSys', 'com', 'com.statusOn', 'zh', '启用', 'lfeng', '2021-03-19 20:20:23', 'lfeng', '2021-03-19 20:20:23', NULL);
INSERT INTO `sys_i18n` VALUES (1372887004445966337, 'grgmsSys', 'dict', 'dict.userGender.2', 'zh', '女', 'lfeng', '2021-03-19 20:25:43', 'lfeng', '2021-03-19 20:25:43', NULL);
INSERT INTO `sys_i18n` VALUES (1372887004542435329, 'grgmsSys', 'dict', 'dict.userGender.2', 'en', 'Female', 'lfeng', '2021-03-19 20:25:43', 'lfeng', '2021-03-19 20:25:43', NULL);
INSERT INTO `sys_i18n` VALUES (1372887832015699969, 'grgmsSys', 'i18n', 'i18n.addLanguage', 'zh', '添加语言', 'lfeng', '2021-03-19 20:29:00', 'lfeng', '2021-03-19 20:29:00', NULL);
INSERT INTO `sys_i18n` VALUES (1372887832804229122, 'grgmsSys', 'i18n', 'i18n.addLanguage', 'en', 'Add', 'lfeng', '2021-03-19 20:29:01', 'lfeng', '2021-03-19 20:29:01', NULL);
INSERT INTO `sys_i18n` VALUES (1372888116288847874, 'grgmsSys', 'btn', 'btn.cancel', 'zh', '取消', 'lfeng', '2021-03-19 20:30:08', 'lfeng', '2021-03-19 20:30:08', NULL);
INSERT INTO `sys_i18n` VALUES (1372888116653752322, 'grgmsSys', 'btn', 'btn.cancel', 'en', 'Cancel', 'lfeng', '2021-03-19 20:30:08', 'lfeng', '2021-03-19 20:30:08', NULL);
INSERT INTO `sys_i18n` VALUES (1372888190305730561, 'grgmsSys', 'btn', 'btn.download', 'zh', '下载', 'lfeng', '2021-03-19 20:30:26', 'lfeng', '2021-03-19 20:30:26', NULL);
INSERT INTO `sys_i18n` VALUES (1372888190637080578, 'grgmsSys', 'btn', 'btn.download', 'en', 'Download', 'lfeng', '2021-03-19 20:30:26', 'lfeng', '2021-03-19 20:30:26', NULL);
INSERT INTO `sys_i18n` VALUES (1372897482844958722, 'grgmsSys', 'org', 'org.name.gd_icbc', 'zh', '中国工商银行广东省分行', 'lfeng', '2021-03-19 21:07:21', 'lfeng', '2021-03-19 21:07:21', NULL);
INSERT INTO `sys_i18n` VALUES (1372897482966593538, 'grgmsSys', 'org', 'org.name.gd_icbc', 'en', 'ICBC Guangdong Branch Co., Ltd', 'lfeng', '2021-03-19 21:07:21', 'lfeng', '2021-03-19 21:07:21', NULL);
INSERT INTO `sys_i18n` VALUES (1372931905875374082, 'grgmsSys', 'btn', 'btn.refresh', 'zh', '刷新', 'lfeng', '2021-03-19 23:24:08', 'lfeng', '2021-03-19 23:24:08', NULL);
INSERT INTO `sys_i18n` VALUES (1372931906798120961, 'grgmsSys', 'btn', 'btn.refresh', 'en', 'Refresh', 'lfeng', '2021-03-19 23:24:09', 'lfeng', '2021-03-19 23:24:09', NULL);
INSERT INTO `sys_i18n` VALUES (1373799191561662465, 'grgmsSys', 'menu', 'menu.detail', 'zh', '菜单详情', 'lfeng', '2021-03-22 08:50:25', 'lfeng', '2021-03-22 08:50:25', NULL);
INSERT INTO `sys_i18n` VALUES (1373799803368009729, 'grgmsSys', 'menu', 'menu.type', 'zh', '资源类型', 'lfeng', '2021-03-22 08:52:51', 'lfeng', '2021-03-22 08:52:51', NULL);
INSERT INTO `sys_i18n` VALUES (1373800255220379649, 'grgmsSys', 'menu', 'menu.authorityIdList', 'zh', '权限 id 集合', 'lfeng', '2021-03-22 08:54:39', 'lfeng', '2021-03-22 08:54:39', NULL);
INSERT INTO `sys_i18n` VALUES (1373800328922689538, 'grgmsSys', 'menu', 'menu.icon', 'zh', '图标', 'lfeng', '2021-03-22 08:54:57', 'lfeng', '2021-03-22 08:54:57', NULL);
INSERT INTO `sys_i18n` VALUES (1373800394676793346, 'grgmsSys', 'menu', 'menu.menuCode', 'zh', '编码', 'lfeng', '2021-03-22 08:55:12', 'lfeng', '2021-03-22 08:55:12', NULL);
INSERT INTO `sys_i18n` VALUES (1373800503196020738, 'grgmsSys', 'menu', 'menu.orderNum', 'zh', '排序号', 'lfeng', '2021-03-22 08:55:38', 'lfeng', '2021-03-22 08:55:38', NULL);
INSERT INTO `sys_i18n` VALUES (1373800681625907201, 'grgmsSys', 'menu', 'menu.parentName', 'zh', '父名称', 'lfeng', '2021-03-22 08:56:21', 'lfeng', '2021-03-22 08:56:21', NULL);
INSERT INTO `sys_i18n` VALUES (1373800747015106562, 'grgmsSys', 'menu', 'menu.parentCode', 'zh', '父编码', 'lfeng', '2021-03-22 08:56:36', 'lfeng', '2021-03-22 08:56:36', NULL);
INSERT INTO `sys_i18n` VALUES (1373800895241809921, 'grgmsSys', 'menu', 'menu.buttons', 'zh', '权限按钮', 'lfeng', '2021-03-22 08:57:12', 'lfeng', '2021-03-22 08:57:12', NULL);
INSERT INTO `sys_i18n` VALUES (1373801042524794882, 'grgmsSys', 'menu', 'menu.name', 'zh', '名称', 'lfeng', '2021-03-22 08:57:47', 'lfeng', '2021-03-22 08:57:47', NULL);
INSERT INTO `sys_i18n` VALUES (1373801403264299010, 'grgmsSys', 'auth', 'auth.fullmethodname', 'zh', '完整方法名', 'lfeng', '2021-03-22 08:59:13', 'lfeng', '2021-03-22 08:59:13', NULL);
INSERT INTO `sys_i18n` VALUES (1373801403637592065, 'grgmsSys', 'auth', 'auth.fullmethodname', 'en', 'Full Method Name', 'lfeng', '2021-03-22 08:59:13', 'lfeng', '2021-03-22 08:59:13', NULL);
INSERT INTO `sys_i18n` VALUES (1373809944444301313, 'grgmsSys', 'btn', 'btn.copy', 'zh', '复制', 'lfeng', '2021-03-22 09:33:09', 'lfeng', '2021-03-22 09:33:09', NULL);
INSERT INTO `sys_i18n` VALUES (1373809944838565889, 'grgmsSys', 'btn', 'btn.copy', 'en', 'Copy', 'lfeng', '2021-03-22 09:33:09', 'lfeng', '2021-03-22 09:33:09', NULL);
INSERT INTO `sys_i18n` VALUES (1374173527682347009, 'grgmsSys', 'org', 'org.fullName', 'zh', '机构全称', 'lfeng', '2021-03-23 09:37:54', 'lfeng', '2021-03-23 09:37:54', NULL);
INSERT INTO `sys_i18n` VALUES (1374173610935087106, 'grgmsSys', 'org', 'org.finCode', 'zh', '金融机构编码', 'lfeng', '2021-03-23 09:38:14', 'lfeng', '2021-03-23 09:38:14', NULL);
INSERT INTO `sys_i18n` VALUES (1374173676282343425, 'grgmsSys', 'org', 'org.areaName', 'zh', '区域名称', 'lfeng', '2021-03-23 09:38:29', 'lfeng', '2021-03-23 09:38:29', NULL);
INSERT INTO `sys_i18n` VALUES (1374173751779815425, 'grgmsSys', 'org', 'org.address', 'zh', '地址', 'lfeng', '2021-03-23 09:38:47', 'lfeng', '2021-03-23 09:38:47', NULL);
INSERT INTO `sys_i18n` VALUES (1374173895749300226, 'grgmsSys', 'org', 'org.orgCode', 'zh', '机构编码', 'lfeng', '2021-03-23 09:39:22', 'lfeng', '2021-03-23 09:39:22', NULL);
INSERT INTO `sys_i18n` VALUES (1374174277128974338, 'grgmsSys', 'org', 'org.parentName', 'zh', '父机构', 'lfeng', '2021-03-23 09:40:53', 'lfeng', '2021-03-23 09:40:53', NULL);
INSERT INTO `sys_i18n` VALUES (1374181095091761154, 'grgmsSys', 'btn', 'btn.fold', 'zh', '收起', 'lfeng', '2021-03-23 10:07:58', 'lfeng', '2021-03-23 10:07:58', NULL);
INSERT INTO `sys_i18n` VALUES (1374181095859318785, 'grgmsSys', 'btn', 'btn.fold', 'en', 'Fold', 'lfeng', '2021-03-23 10:07:58', 'lfeng', '2021-03-23 10:07:58', NULL);
INSERT INTO `sys_i18n` VALUES (1374181524659793921, 'grgmsSys', 'area', 'area.areaCode', 'zh', '区域编码', 'lfeng', '2021-03-23 10:09:41', 'lfeng', '2021-03-23 10:09:41', NULL);
INSERT INTO `sys_i18n` VALUES (1374181587138146306, 'grgmsSys', 'area', 'area.name', 'zh', '区域名称', 'lfeng', '2021-03-23 10:09:56', 'lfeng', '2021-03-23 10:09:56', NULL);
INSERT INTO `sys_i18n` VALUES (1374181654070849538, 'grgmsSys', 'area', 'area.parentName', 'zh', '父区域名称', 'lfeng', '2021-03-23 10:10:12', 'lfeng', '2021-03-23 10:10:12', NULL);
INSERT INTO `sys_i18n` VALUES (1374182197799448577, 'grgmsSys', 'user', 'user.name', 'zh', '姓名', 'lfeng', '2021-03-23 10:12:21', 'lfeng', '2021-03-23 10:12:21', NULL);
INSERT INTO `sys_i18n` VALUES (1374182254267363329, 'grgmsSys', 'user', 'user.joinOrgId', 'zh', '所属机构编号', 'lfeng', '2021-03-23 10:12:35', 'lfeng', '2021-03-23 10:12:35', NULL);
INSERT INTO `sys_i18n` VALUES (1374182349188657154, 'grgmsSys', 'user', 'user.email', 'zh', '电子邮箱', 'lfeng', '2021-03-23 10:12:57', 'lfeng', '2021-03-23 10:12:57', NULL);
INSERT INTO `sys_i18n` VALUES (1374182395724460034, 'grgmsSys', 'user', 'user.mobileTelephone', 'zh', '联系电话', 'lfeng', '2021-03-23 10:13:08', 'lfeng', '2021-03-23 10:13:08', NULL);
INSERT INTO `sys_i18n` VALUES (1374182489924333570, 'grgmsSys', 'user', 'user.isEnabled', 'zh', '帐号是否启用', 'lfeng', '2021-03-23 10:13:31', 'lfeng', '2021-03-23 10:13:31', NULL);
INSERT INTO `sys_i18n` VALUES (1374182866367311874, 'grgmsSys', 'user', 'user.gender', 'zh', '性别', 'lfeng', '2021-03-23 10:15:01', 'lfeng', '2021-03-23 10:15:01', NULL);
INSERT INTO `sys_i18n` VALUES (1374183029391519746, 'grgmsSys', 'user', 'user.manageArea', 'zh', '管理区域', 'lfeng', '2021-03-23 10:15:39', 'lfeng', '2021-03-23 10:15:39', NULL);
INSERT INTO `sys_i18n` VALUES (1374183073456877569, 'grgmsSys', 'user', 'user.manageOrg', 'zh', '管理机构', 'lfeng', '2021-03-23 10:15:50', 'lfeng', '2021-03-23 10:15:50', NULL);
INSERT INTO `sys_i18n` VALUES (1374183122672840706, 'grgmsSys', 'user', 'user.password', 'zh', '登录密码', 'lfeng', '2021-03-23 10:16:02', 'lfeng', '2021-03-23 10:16:02', NULL);
INSERT INTO `sys_i18n` VALUES (1374183177916018689, 'grgmsSys', 'user', 'user.confirmPassword', 'zh', '确认密码', 'lfeng', '2021-03-23 10:16:15', 'lfeng', '2021-03-23 10:16:15', NULL);
INSERT INTO `sys_i18n` VALUES (1374183581672304641, 'grgmsSys', 'user', 'user.role', 'zh', '角色', 'lfeng', '2021-03-23 10:17:51', 'lfeng', '2021-03-23 10:17:51', NULL);
INSERT INTO `sys_i18n` VALUES (1374183837390630914, 'grgmsSys', 'user', 'user.credentialsType', 'zh', '证件类型', 'lfeng', '2021-03-23 10:18:52', 'lfeng', '2021-03-23 10:18:52', NULL);
INSERT INTO `sys_i18n` VALUES (1374183892466036738, 'grgmsSys', 'user', 'user.credentialsNumber', 'zh', '证件号码', 'lfeng', '2021-03-23 10:19:05', 'lfeng', '2021-03-23 10:19:05', NULL);
INSERT INTO `sys_i18n` VALUES (1374183948413857794, 'grgmsSys', 'user', 'user.nationality', 'zh', '国籍', 'lfeng', '2021-03-23 10:19:19', 'lfeng', '2021-03-23 10:19:19', NULL);
INSERT INTO `sys_i18n` VALUES (1374184046657040386, 'grgmsSys', 'user', 'user.homeAddress', 'zh', '联系住址', 'lfeng', '2021-03-23 10:19:42', 'lfeng', '2021-03-23 10:19:42', NULL);
INSERT INTO `sys_i18n` VALUES (1374184400337530881, 'grgmsSys', 'role', 'role.name', 'zh', '角色名称', 'lfeng', '2021-03-23 10:21:06', 'lfeng', '2021-03-23 10:21:06', NULL);
INSERT INTO `sys_i18n` VALUES (1374184464028037121, 'grgmsSys', 'role', 'role.roleType', 'zh', '角色类型', 'lfeng', '2021-03-23 10:21:21', 'lfeng', '2021-03-23 10:21:21', NULL);
INSERT INTO `sys_i18n` VALUES (1374184678981922818, 'grgmsSys', 'role', 'role.availableMenu', 'zh', '可使用菜单', 'lfeng', '2021-03-23 10:22:13', 'lfeng', '2021-03-23 10:22:13', NULL);
INSERT INTO `sys_i18n` VALUES (1374184721344393218, 'grgmsSys', 'role', 'role.assignableMenu', 'zh', '可分配菜单', 'lfeng', '2021-03-23 10:22:23', 'lfeng', '2021-03-23 10:22:23', NULL);
INSERT INTO `sys_i18n` VALUES (1374184776516268033, 'grgmsSys', 'role', 'role.authorizedUser', 'zh', '授权用户', 'lfeng', '2021-03-23 10:22:36', 'lfeng', '2021-03-23 10:22:36', NULL);
INSERT INTO `sys_i18n` VALUES (1374186144362688513, 'grgmsSys', 'user', 'user.modifyPassword', 'zh', '修改密码', 'lfeng', '2021-03-23 10:28:02', 'lfeng', '2021-03-23 10:28:02', NULL);
INSERT INTO `sys_i18n` VALUES (1374186395656024065, 'grgmsSys', 'login', 'login.logout', 'zh', '退出登录', 'lfeng', '2021-03-23 10:29:02', 'lfeng', '2021-03-23 10:29:02', NULL);
INSERT INTO `sys_i18n` VALUES (1374186396041900033, 'grgmsSys', 'login', 'login.logout', 'en', 'Logout', 'lfeng', '2021-03-23 10:29:02', 'lfeng', '2021-03-23 10:29:02', NULL);
INSERT INTO `sys_i18n` VALUES (1374187672494436354, 'grgmsSys', 'dict', 'dict.imptStatus.2', 'en', 'Fail', 'lfeng', '2021-03-23 10:34:06', 'lfeng', '2021-03-23 10:34:06', NULL);
INSERT INTO `sys_i18n` VALUES (1374187672863535106, 'grgmsSys', 'dict', 'dict.imptStatus.2', 'zh', '失败', 'lfeng', '2021-03-23 10:34:07', 'lfeng', '2021-03-23 10:34:07', NULL);
INSERT INTO `sys_i18n` VALUES (1374539893035790338, 'grgmsSys', 'btn', 'btn.close', 'zh', '关闭', 'lfeng', '2021-03-24 09:53:42', 'lfeng', '2021-03-24 09:53:42', NULL);
INSERT INTO `sys_i18n` VALUES (1374539893165813761, 'grgmsSys', 'btn', 'btn.close', 'en', 'close', 'lfeng', '2021-03-24 09:53:42', 'lfeng', '2021-03-24 09:53:42', NULL);
INSERT INTO `sys_i18n` VALUES (1374540074359746562, 'grgmsSys', 'btn', 'btn.import', 'zh', '导入', 'lfeng', '2021-03-24 09:54:26', 'lfeng', '2021-03-24 09:54:26', NULL);
INSERT INTO `sys_i18n` VALUES (1374540074770788353, 'grgmsSys', 'btn', 'btn.import', 'en', 'Import', 'lfeng', '2021-03-24 09:54:26', 'lfeng', '2021-03-24 09:54:26', NULL);
INSERT INTO `sys_i18n` VALUES (1374540527453630466, 'grgmsSys', 'btn', 'btn.select', 'zh', '选择', 'lfeng', '2021-03-24 09:56:14', 'lfeng', '2021-03-24 09:56:14', NULL);
INSERT INTO `sys_i18n` VALUES (1374540527982112769, 'grgmsSys', 'btn', 'btn.select', 'en', 'Select', 'lfeng', '2021-03-24 09:56:14', 'lfeng', '2021-03-24 09:56:14', NULL);
INSERT INTO `sys_i18n` VALUES (1374561301044424706, 'grgmsSys', 'msg', 'msg.copySuccess', 'zh', '已经复制到剪贴板', 'lfeng', '2021-03-24 11:18:46', 'lfeng', '2021-03-24 11:18:46', NULL);
INSERT INTO `sys_i18n` VALUES (1374597271718490114, 'grgmsSys', 'menu', 'menu.port', 'zh', '访问端口', 'lfeng', '2021-03-24 13:41:43', 'lfeng', '2021-03-24 13:41:43', NULL);
INSERT INTO `sys_i18n` VALUES (1374597732592807937, 'grgmsSys', 'menu', 'menu.host', 'zh', '访问域名或IP', 'lfeng', '2021-03-24 13:43:32', 'lfeng', '2021-03-24 13:43:32', NULL);
INSERT INTO `sys_i18n` VALUES (1374601294462873601, 'grgmsSys', 'menu', 'menu.systemPath', 'zh', '激活路径', 'lfeng', '2021-03-24 13:57:42', 'lfeng', '2021-03-24 13:57:42', NULL);
INSERT INTO `sys_i18n` VALUES (1374601436347789314, 'grgmsSys', 'menu', 'menu.systemName', 'zh', '系统名称', 'lfeng', '2021-03-24 13:58:15', 'lfeng', '2021-03-24 13:58:15', NULL);
INSERT INTO `sys_i18n` VALUES (1374601708843331585, 'grgmsSys', 'menu', 'menu.catalogName', 'zh', '目录名称', 'lfeng', '2021-03-24 13:59:20', 'lfeng', '2021-03-24 13:59:20', NULL);
INSERT INTO `sys_i18n` VALUES (1374601939412611073, 'grgmsSys', 'menu', 'menu.path', 'zh', '访问路径', 'lfeng', '2021-03-24 14:00:15', 'lfeng', '2021-03-24 14:00:15', NULL);
INSERT INTO `sys_i18n` VALUES (1374602181272956929, 'grgmsSys', 'menu', 'menu.menuName', 'zh', '菜单名称', 'lfeng', '2021-03-24 14:01:13', 'lfeng', '2021-03-24 14:01:13', NULL);
INSERT INTO `sys_i18n` VALUES (1374602270821347330, 'grgmsSys', 'menu', 'menu.buttonName', 'zh', '按钮名称', 'lfeng', '2021-03-24 14:01:34', 'lfeng', '2021-03-24 14:01:34', NULL);
INSERT INTO `sys_i18n` VALUES (1374603103508131841, 'grgmsSys', 'menu', 'menu.inCatalog', 'zh', '菜单只能添加在目录下', 'lfeng', '2021-03-24 14:04:53', 'lfeng', '2021-03-24 14:04:53', NULL);
INSERT INTO `sys_i18n` VALUES (1374603219103150081, 'grgmsSys', 'menu', 'menu.inMenu', 'zh', '按钮只能添加在菜单下', 'lfeng', '2021-03-24 14:05:20', 'lfeng', '2021-03-24 14:05:20', NULL);
INSERT INTO `sys_i18n` VALUES (1374901443433099265, 'grgmsSys', 'msg', 'msg.maxChar', 'zh', '最多可以输入{0}个字符', 'lfeng', '2021-03-25 09:50:23', 'lfeng', '2021-03-25 09:50:23', NULL);
INSERT INTO `sys_i18n` VALUES (1374903265493282818, 'grgmsSys', 'msg', 'msg.useOptionValue', 'zh', '请填入选项中的值', 'lfeng', '2021-03-25 09:57:37', 'lfeng', '2021-03-25 09:57:37', NULL);
INSERT INTO `sys_i18n` VALUES (1375288513649471489, 'grgmsSys', 'dict', 'dict.imptStatus.1', 'en', 'Success', 'lfeng', '2021-03-26 11:28:27', 'lfeng', '2021-03-26 11:28:27', NULL);
INSERT INTO `sys_i18n` VALUES (1375288513938878466, 'grgmsSys', 'dict', 'dict.imptStatus.1', 'zh', '成功', 'lfeng', '2021-03-26 11:28:28', 'lfeng', '2021-03-26 11:28:28', NULL);
INSERT INTO `sys_i18n` VALUES (1375291059273232386, 'grgmsSys', 'msg', 'msg.logOutTips', 'zh', '确认退出系统？', 'lfeng', '2021-03-26 11:38:34', 'lfeng', '2021-03-26 11:38:34', NULL);
INSERT INTO `sys_i18n` VALUES (1375326369621241858, 'grgmsSys', 'auth', 'auth.fullMethodName', 'zh', '完整方法名', 'lfeng', '2021-03-26 13:58:53', 'lfeng', '2021-03-26 13:58:53', NULL);
INSERT INTO `sys_i18n` VALUES (1375326370225221633, 'grgmsSys', 'auth', 'auth.fullMethodName', 'en', 'Full Method Name', 'lfeng', '2021-03-26 13:58:53', 'lfeng', '2021-03-26 13:58:53', NULL);
INSERT INTO `sys_i18n` VALUES (1375327513294696450, 'grgmsSys', 'msg', 'msg.unlock', 'zh', '解锁账号？', 'lfeng', '2021-03-26 14:03:26', 'lfeng', '2021-03-26 14:03:26', NULL);
INSERT INTO `sys_i18n` VALUES (1377155414600216577, 'grgmsSys', 'exception', 'exception.logout1001', 'zh', '超时退出', 'lggui', '2021-03-31 15:06:51', 'lggui', '2021-03-31 15:06:51', NULL);
INSERT INTO `sys_i18n` VALUES (1377155415099338754, 'grgmsSys', 'exception', 'exception.logout1001', 'en', 'Timeout exit', 'lggui', '2021-03-31 15:06:51', 'lggui', '2021-03-31 15:06:51', NULL);
INSERT INTO `sys_i18n` VALUES (1377155657261674497, 'grgmsSys', 'exception', 'exception.logout1002', 'zh', '异地登录强制退出', 'lggui', '2021-03-31 15:07:49', 'lggui', '2021-03-31 15:07:49', NULL);
INSERT INTO `sys_i18n` VALUES (1377155657651744770, 'grgmsSys', 'exception', 'exception.logout1002', 'en', 'Forced logout', 'lggui', '2021-03-31 15:07:49', 'lggui', '2021-03-31 15:07:49', NULL);
INSERT INTO `sys_i18n` VALUES (1377159556387336193, 'grgmsSys', 'org', 'org.orgFilter', 'zh', '筛选机构', 'lfeng18', '2021-03-31 15:23:19', 'lfeng18', '2021-03-31 15:23:19', NULL);
INSERT INTO `sys_i18n` VALUES (1377182538405019650, 'grgmsSys', 'user', 'user.passwordErrorTips', 'zh', '密码由字母和数字组合，6到16位', 'lfeng18', '2021-03-31 16:54:38', 'lfeng18', '2021-03-31 16:54:38', NULL);
INSERT INTO `sys_i18n` VALUES (1377182796371492865, 'grgmsSys', 'user', 'user.confirmPasswordErrorTips', 'zh', '密码不一致', 'lfeng18', '2021-03-31 16:55:40', 'lfeng18', '2021-03-31 16:55:40', NULL);
INSERT INTO `sys_i18n` VALUES (1377441283815014401, 'grgmsSys', 'btn', 'btn.more', 'zh', '更多', 'lfeng18', '2021-04-01 10:02:48', 'lfeng18', '2021-04-01 10:02:48', NULL);
INSERT INTO `sys_i18n` VALUES (1377441284519657473, 'grgmsSys', 'btn', 'btn.more', 'en', 'More', 'lfeng18', '2021-04-01 10:02:48', 'lfeng18', '2021-04-01 10:02:48', NULL);
INSERT INTO `sys_i18n` VALUES (1377441545225011202, 'grgmsSys', 'btn', 'btn.exportAll', 'zh', '导出所有', 'lfeng18', '2021-04-01 10:03:50', 'lfeng18', '2021-04-01 10:03:50', NULL);
INSERT INTO `sys_i18n` VALUES (1377441545636052994, 'grgmsSys', 'btn', 'btn.exportAll', 'en', 'Export All', 'lfeng18', '2021-04-01 10:03:50', 'lfeng18', '2021-04-01 10:03:50', NULL);
INSERT INTO `sys_i18n` VALUES (1377441700972101634, 'grgmsSys', 'btn', 'btn.exportSelection', 'zh', '导出勾选', 'lfeng18', '2021-04-01 10:04:27', 'lfeng18', '2021-04-01 10:04:27', NULL);
INSERT INTO `sys_i18n` VALUES (1377441701055987713, 'grgmsSys', 'btn', 'btn.exportSelection', 'en', 'Export Selection', 'lfeng18', '2021-04-01 10:04:27', 'lfeng18', '2021-04-01 10:04:27', NULL);
INSERT INTO `sys_i18n` VALUES (1377441968501587969, 'grgmsSys', 'btn', 'btn.unfold', 'zh', '展开', 'lfeng18', '2021-04-01 10:05:31', 'lfeng18', '2021-04-01 10:05:31', NULL);
INSERT INTO `sys_i18n` VALUES (1377441968585474049, 'grgmsSys', 'btn', 'btn.unfold', 'en', 'Unfold', 'lfeng18', '2021-04-01 10:05:31', 'lfeng18', '2021-04-01 10:05:31', NULL);
INSERT INTO `sys_i18n` VALUES (1377489338413117442, 'grgmsSys', 'menu', 'menu.selectAuthority', 'zh', '选择权限标识', 'lfeng18', '2021-04-01 13:13:45', 'lfeng18', '2021-04-01 13:13:45', NULL);
INSERT INTO `sys_i18n` VALUES (1377490366864846849, 'grgmsSys', 'log', 'log.method', 'zh', '方法', 'lfeng18', '2021-04-01 13:17:50', 'lfeng18', '2021-04-01 13:17:50', NULL);
INSERT INTO `sys_i18n` VALUES (1377490367380746241, 'grgmsSys', 'log', 'log.method', 'en', 'Method', 'lfeng18', '2021-04-01 13:17:50', 'lfeng18', '2021-04-01 13:17:50', NULL);
INSERT INTO `sys_i18n` VALUES (1377904610252976130, 'grgmsSys', 'dict', 'dict.languageType.en', 'zh', 'English', 'lfeng18', '2021-04-02 16:43:53', 'lfeng18', '2021-04-02 16:43:53', NULL);
INSERT INTO `sys_i18n` VALUES (1377904610374610946, 'grgmsSys', 'dict', 'dict.languageType.en', 'en', 'English', 'lfeng18', '2021-04-02 16:43:54', 'lfeng18', '2021-04-02 16:43:54', NULL);
INSERT INTO `sys_i18n` VALUES (1377904727202754561, 'grgmsSys', 'dict', 'dict.languageType.zh', 'zh', '中文', 'lfeng18', '2021-04-02 16:44:21', 'lfeng18', '2021-04-02 16:44:21', NULL);
INSERT INTO `sys_i18n` VALUES (1377904727316000770, 'grgmsSys', 'dict', 'dict.languageType.zh', 'en', '中文', 'lfeng18', '2021-04-02 16:44:21', 'lfeng18', '2021-04-02 16:44:21', NULL);
INSERT INTO `sys_i18n` VALUES (1381514270147112961, 'grgmsSys', 'user', 'user.joinOrgName', 'zh', '所属机构', 'liufeng', '2021-04-12 15:47:23', 'liufeng', '2021-04-12 15:47:24', NULL);
INSERT INTO `sys_i18n` VALUES (1381514346177261569, 'grgmsSys', 'user', 'user.username', 'zh', '账号', 'liufeng', '2021-04-12 15:47:42', 'liufeng', '2021-04-12 15:47:42', NULL);
INSERT INTO `sys_i18n` VALUES (1381514405862207489, 'grgmsSys', 'user', 'user.isLocked', 'zh', '是否锁定', 'liufeng', '2021-04-12 15:47:56', 'liufeng', '2021-04-12 15:47:56', NULL);
INSERT INTO `sys_i18n` VALUES (1382502258532446210, 'grgmsSys', 'biz', 'biz.bizCode', 'zh', '业务编号', 'liufeng', '2021-04-15 09:13:18', 'liufeng', '2021-04-15 09:13:18', NULL);
INSERT INTO `sys_i18n` VALUES (1382502258951876610, 'grgmsSys', 'biz', 'biz.bizCode', 'en', 'Business Num', 'liufeng', '2021-04-15 09:13:18', 'liufeng', '2021-04-15 09:13:18', NULL);
INSERT INTO `sys_i18n` VALUES (1382502345182572545, 'grgmsSys', 'biz', 'biz.bizInfo', 'zh', '业务名称', 'liufeng', '2021-04-15 09:13:39', 'liufeng', '2021-04-15 09:13:39', NULL);
INSERT INTO `sys_i18n` VALUES (1382502345337761793, 'grgmsSys', 'biz', 'biz.bizInfo', 'en', 'Business Name', 'liufeng', '2021-04-15 09:13:39', 'liufeng', '2021-04-15 09:13:39', NULL);
INSERT INTO `sys_i18n` VALUES (1384122400517419009, 'grgmsSys', 'com', 'com.view', 'en', 'View', 'lfeng18', '2021-04-19 20:31:10', 'lfeng18', '2021-04-19 20:31:10', NULL);
INSERT INTO `sys_i18n` VALUES (1384122400894906370, 'grgmsSys', 'com', 'com.view', 'zh', '查看窗口', 'lfeng18', '2021-04-19 20:31:10', 'lfeng18', '2021-04-19 20:31:10', NULL);
INSERT INTO `sys_i18n` VALUES (1384122829879930882, 'grgmsSys', 'msg', 'msg.currentSelectData', 'zh', '当前所选数据', 'lfeng18', '2021-04-19 20:32:53', 'lfeng18', '2021-04-19 20:32:53', NULL);
INSERT INTO `sys_i18n` VALUES (1384123128610844674, 'grgmsSys', 'msg', 'msg.errorIpOrPort', 'zh', 'IP或端口格式错误', 'lfeng18', '2021-04-19 20:34:04', 'lfeng18', '2021-04-19 20:34:04', NULL);
INSERT INTO `sys_i18n` VALUES (1384125952174354433, 'grgmsSys', 'i18n', 'date.startTimeStr', 'zh', '开始时间', 'lfeng18', '2021-04-19 20:45:17', 'lfeng18', '2021-04-19 20:45:17', NULL);
INSERT INTO `sys_i18n` VALUES (1384125952451178497, 'grgmsSys', 'i18n', 'date.startTimeStr', 'en', 'starttime', 'lfeng18', '2021-04-19 20:45:17', 'lfeng18', '2021-04-19 20:45:17', NULL);
INSERT INTO `sys_i18n` VALUES (1384126003667824642, 'grgmsSys', 'i18n', 'date.endTimeStr', 'zh', '结束时间', 'lfeng18', '2021-04-19 20:45:29', 'lfeng18', '2021-04-19 20:45:29', NULL);
INSERT INTO `sys_i18n` VALUES (1384126004217278465, 'grgmsSys', 'i18n', 'date.endTimeStr', 'en', 'endtime', 'lfeng18', '2021-04-19 20:45:29', 'lfeng18', '2021-04-19 20:45:29', NULL);
INSERT INTO `sys_i18n` VALUES (1384127861572530178, 'grgmsSys', 'i18n', 'scheduler.week', 'zh', '周', 'lfeng18', '2021-04-19 20:52:52', 'lfeng18', '2021-04-19 20:52:52', NULL);
INSERT INTO `sys_i18n` VALUES (1384127862159732738, 'grgmsSys', 'i18n', 'scheduler.week', 'en', 'week', 'lfeng18', '2021-04-19 20:52:52', 'lfeng18', '2021-04-19 20:52:52', NULL);
INSERT INTO `sys_i18n` VALUES (1384127897576435714, 'grgmsSys', 'i18n', 'scheduler.day', 'en', 'day', 'lfeng18', '2021-04-19 20:53:01', 'lfeng18', '2021-04-19 20:53:01', NULL);
INSERT INTO `sys_i18n` VALUES (1384127897828093954, 'grgmsSys', 'i18n', 'scheduler.day', 'zh', '日', 'lfeng18', '2021-04-19 20:53:01', 'lfeng18', '2021-04-19 20:53:01', NULL);
INSERT INTO `sys_i18n` VALUES (1384127992652918785, 'grgmsSys', 'i18n', 'scheduler.second', 'en', 'second', 'lfeng18', '2021-04-19 20:53:23', 'lfeng18', '2021-04-19 20:53:23', NULL);
INSERT INTO `sys_i18n` VALUES (1384127992996851713, 'grgmsSys', 'i18n', 'scheduler.second', 'zh', '秒', 'lfeng18', '2021-04-19 20:53:24', 'lfeng18', '2021-04-19 20:53:24', NULL);
INSERT INTO `sys_i18n` VALUES (1384128092057923586, 'grgmsSys', 'i18n', 'scheduler.minute', 'en', 'minute', 'lfeng18', '2021-04-19 20:53:47', 'lfeng18', '2021-04-19 20:53:47', NULL);
INSERT INTO `sys_i18n` VALUES (1384128092468965378, 'grgmsSys', 'i18n', 'scheduler.minute', 'zh', '分', 'lfeng18', '2021-04-19 20:53:47', 'lfeng18', '2021-04-19 20:53:47', NULL);
INSERT INTO `sys_i18n` VALUES (1384128183867043841, 'grgmsSys', 'i18n', 'scheduler.hour', 'en', 'hour', 'lfeng18', '2021-04-19 20:54:09', 'lfeng18', '2021-04-19 20:54:09', NULL);
INSERT INTO `sys_i18n` VALUES (1384128184353583105, 'grgmsSys', 'i18n', 'scheduler.hour', 'zh', '时', 'lfeng18', '2021-04-19 20:54:09', 'lfeng18', '2021-04-19 20:54:09', NULL);
INSERT INTO `sys_i18n` VALUES (1384139464288268290, 'grgmsSys', 'dic', 'dic.codeOrder', 'zh', '排序号', 'liufeng', '2021-04-19 21:38:59', 'liufeng', '2021-04-19 21:38:59', NULL);
INSERT INTO `sys_i18n` VALUES (1384139464535732225, 'grgmsSys', 'dic', 'dic.codeOrder', 'en', 'Order', 'liufeng', '2021-04-19 21:38:59', 'liufeng', '2021-04-19 21:38:59', NULL);
INSERT INTO `sys_i18n` VALUES (1384140628136325121, 'grgmsSys', 'btn', 'btn.submit', 'en', 'Submit', 'liufeng', '2021-04-19 21:43:36', 'liufeng', '2021-04-19 21:43:36', NULL);
INSERT INTO `sys_i18n` VALUES (1384140628367011841, 'grgmsSys', 'btn', 'btn.submit', 'zh', '提交', 'liufeng', '2021-04-19 21:43:36', 'liufeng', '2021-04-19 21:43:36', NULL);
INSERT INTO `sys_i18n` VALUES (1385532225864364034, 'grgmsSys', 'com', 'com.createTime', 'en', 'Creation time', 'lfeng18', '2021-04-23 17:53:19', 'lfeng18', '2021-04-23 17:53:19', NULL);
INSERT INTO `sys_i18n` VALUES (1385532226715807745, 'grgmsSys', 'com', 'com.createTime', 'zh', '创建时间', 'lfeng18', '2021-04-23 17:53:19', 'lfeng18', '2021-04-23 17:53:19', NULL);
INSERT INTO `sys_i18n` VALUES (1385532703897579522, 'grgmsSys', 'com', 'com.status', 'zh', '状态', 'lfeng18', '2021-04-23 17:55:13', 'lfeng18', '2021-04-23 17:55:13', NULL);
INSERT INTO `sys_i18n` VALUES (1385532704233123841, 'grgmsSys', 'com', 'com.status', 'en', 'Status', 'lfeng18', '2021-04-23 17:55:13', 'lfeng18', '2021-04-23 17:55:13', NULL);
INSERT INTO `sys_i18n` VALUES (1385532842619990018, 'grgmsSys', 'com', 'com.state', 'zh', '状态', 'lfeng18', '2021-04-23 17:55:46', 'lfeng18', '2021-04-23 17:55:46', NULL);
INSERT INTO `sys_i18n` VALUES (1385532842972311553, 'grgmsSys', 'com', 'com.state', 'en', 'State', 'lfeng18', '2021-04-23 17:55:46', 'lfeng18', '2021-04-23 17:55:46', NULL);
INSERT INTO `sys_i18n` VALUES (1386317297102127106, 'grgmsSys', 'exception', 'exception.i18n1008', 'zh', '数据类型应与国际化编码前缀一致', 'cyfeng', '2021-04-25 21:52:54', 'cyfeng', '2021-04-25 21:52:54', NULL);
INSERT INTO `sys_i18n` VALUES (1386317299530629122, 'grgmsSys', 'exception', 'exception.i18n1008', 'en', 'The data type should be consistent with the international code prefix', 'cyfeng', '2021-04-25 21:52:55', 'cyfeng', '2021-04-25 21:52:55', NULL);
INSERT INTO `sys_i18n` VALUES (1386585724387618817, 'grgmsSys', 'i18n', 'i18n.fileName', 'zh', '国际化列表', 'lfeng18', '2021-04-26 15:39:32', 'lfeng18', '2021-04-26 15:39:32', NULL);
INSERT INTO `sys_i18n` VALUES (1386585725243256833, 'grgmsSys', 'i18n', 'i18n.fileName', 'en', 'I18n List', 'lfeng18', '2021-04-26 15:39:33', 'lfeng18', '2021-04-26 15:39:33', NULL);
INSERT INTO `sys_i18n` VALUES (1386606584070696962, 'grgmsSys', 'date', 'date.hhmmss', 'zh', '时分秒', 'cyfeng', '2021-04-26 17:02:26', 'cyfeng', '2021-04-26 17:02:26', NULL);
INSERT INTO `sys_i18n` VALUES (1386606827021561857, 'grgmsSys', 'date', 'date.yyyymmdd', 'zh', '年月日', 'cyfeng', '2021-04-26 17:03:24', 'cyfeng', '2021-04-26 17:03:24', NULL);
INSERT INTO `sys_i18n` VALUES (1386607134766034946, 'grgmsSys', 'date', 'date.yyyymmddhhmmss', 'zh', '年月日时分秒', 'cyfeng', '2021-04-26 17:04:37', 'cyfeng', '2021-04-26 17:04:37', NULL);
INSERT INTO `sys_i18n` VALUES (1386960999884726273, 'grgmsSys', 'exception', 'exception.opsOrg1009', 'zh', '维护商名称或国际化编码不能重复', 'cyfeng', '2021-04-27 16:30:45', 'cyfeng', '2021-04-27 16:30:45', NULL);
INSERT INTO `sys_i18n` VALUES (1387066084493914114, 'grgmsSys', 'msg', 'msg.testV', 'zh', '账号由字母和数字组合账号由字母和数字组合账号由字母和数字组合组合组', 'lfeng18', '2021-04-27 23:28:19', 'lfeng18', '2021-04-27 23:28:19', NULL);
INSERT INTO `sys_i18n` VALUES (1387066493941870594, 'grgmsSys', 'msg', 'msg.testV32', 'zh', '账号由字母和数字组合账号由字母和数字组合账号由字母和数字组合组合', 'lfeng18', '2021-04-27 23:29:57', 'lfeng18', '2021-04-27 23:29:57', NULL);
INSERT INTO `sys_i18n` VALUES (1387066822922104834, 'grgmsSys', 'msg', 'msg.testV10', 'zh', '账号由字母和数字组合', 'lfeng18', '2021-04-27 23:31:15', 'lfeng18', '2021-04-27 23:31:15', NULL);
INSERT INTO `sys_i18n` VALUES (1387066891310231554, 'grgmsSys', 'msg', 'msg.testV11', 'zh', '账号由字母和数字组合在', 'lfeng18', '2021-04-27 23:31:32', 'lfeng18', '2021-04-27 23:31:32', NULL);
INSERT INTO `sys_i18n` VALUES (1387067438432022530, 'grgmsSys', 'msg', 'msg.testV16', 'zh', '账号由字母和数字组合账号由字母和', 'lfeng18', '2021-04-27 23:33:42', 'lfeng18', '2021-04-27 23:33:42', NULL);
INSERT INTO `sys_i18n` VALUES (1387067526361411585, 'grgmsSys', 'msg', 'msg.testV17', 'zh', '账号由字母和数字组合账号由字母和和', 'lfeng18', '2021-04-27 23:34:03', 'lfeng18', '2021-04-27 23:34:03', NULL);
INSERT INTO `sys_i18n` VALUES (1387346122762121217, 'grgmsSys', 'msg', 'msg.inputCorrectValue', 'zh', '请输入正确的{0}', 'lfeng18', '2021-04-28 18:01:05', 'lfeng18', '2021-04-28 18:01:05', NULL);
INSERT INTO `sys_i18n` VALUES (1387354692538888193, 'grgmsSys', 'ops', 'ops.usernameErrorTips', 'zh', '账号由字母和数字组合，4到16位 ', 'lfeng18', '2021-04-28 18:35:09', 'lfeng18', '2021-04-28 18:35:09', NULL);
INSERT INTO `sys_i18n` VALUES (1387358723684462594, 'grgmsSys', 'exception', 'exception.area1012', 'zh', '不能停用已被机构使用的区域', 'lkrong', '2021-04-28 18:51:10', 'lkrong', '2021-04-28 18:51:10', NULL);
INSERT INTO `sys_i18n` VALUES (1387386289984139266, 'grgmsSys', 'btn', 'btn.resetpwd', 'zh', '重置密码', 'lfeng18', '2021-04-28 20:40:42', 'lfeng18', '2021-04-28 20:40:42', NULL);
INSERT INTO `sys_i18n` VALUES (1387386290281934850, 'grgmsSys', 'btn', 'btn.resetpwd', 'en', 'Reset Password', 'lfeng18', '2021-04-28 20:40:42', 'lfeng18', '2021-04-28 20:40:42', NULL);
INSERT INTO `sys_i18n` VALUES (1387398058899173377, 'grgmsSys', 'msg', 'msg.resetPasswordTip', 'zh', '确认重置密码为“111111” ？', 'lfeng18', '2021-04-28 21:27:28', 'lfeng18', '2021-04-28 21:27:28', NULL);
INSERT INTO `sys_i18n` VALUES (1387403927565135874, 'grgmsSys', 'msg', 'msg.resetPasswordSuccess', 'zh', '密码重置成功，默认密码为111111', 'lfeng18', '2021-04-28 21:50:47', 'lfeng18', '2021-04-28 21:50:47', NULL);
INSERT INTO `sys_i18n` VALUES (1387403927879708673, 'grgmsSys', 'msg', 'msg.resetPasswordSuccess', 'en', 'Reset password success，Default password is 111111', 'lfeng18', '2021-04-28 21:50:47', 'lfeng18', '2021-04-28 21:50:47', NULL);
INSERT INTO `sys_i18n` VALUES (1387422847760101377, 'grgmsSys', 'biz', 'biz.exportFileName', 'zh', '业务配置管理', 'lfeng18', '2021-04-28 23:05:58', 'lfeng18', '2021-04-28 23:05:58', NULL);
INSERT INTO `sys_i18n` VALUES (1387424629211033602, 'grgmsSys', 'msg', 'msg.hasChildNoStop', 'en', 'The stop object contains child nodes and cannot be stop ', 'lfeng18', '2021-04-28 23:13:03', 'lfeng18', '2021-04-28 23:13:03', NULL);
INSERT INTO `sys_i18n` VALUES (1387424629693378562, 'grgmsSys', 'msg', 'msg.hasChildNoStop', 'zh', '所停用对象包含子节点，不允许停用', 'lfeng18', '2021-04-28 23:13:03', 'lfeng18', '2021-04-28 23:13:03', NULL);
INSERT INTO `sys_i18n` VALUES (1387706276795936770, 'grgmsSys', 'org', 'org.orgCodeErrorTips', 'zh', '机构编码由字母、数字和下划线组合', 'lfeng18', '2021-04-29 17:52:13', 'lfeng18', '2021-04-29 17:52:13', NULL);
INSERT INTO `sys_i18n` VALUES (1387706945913253890, 'grgmsSys', 'org', 'org.finCodeErrorTips', 'zh', '金融机构编码由字母和数字组合', 'lfeng18', '2021-04-29 17:54:52', 'lfeng18', '2021-04-29 17:54:52', NULL);
INSERT INTO `sys_i18n` VALUES (1387770011812130818, 'grgmsSys', 'msg', 'msg.delete', 'zh', '确认删除{0}？', 'admin', '2021-04-29 22:05:28', 'admin', '2021-04-29 22:05:28', NULL);
INSERT INTO `sys_i18n` VALUES (1387775242486706178, 'grgmsSys', 'msg', 'msg.multiDelete', 'zh', '确认删除【{0}】等 {1} 条数据？', 'admin', '2021-04-29 22:26:16', 'admin', '2021-04-29 22:26:16', NULL);
INSERT INTO `sys_i18n` VALUES (1387780372464431106, 'grgmsSys', 'auth', 'auth.delName', 'zh', '权限', 'lfeng18', '2021-04-29 22:46:39', 'lfeng18', '2021-04-29 22:46:39', NULL);
INSERT INTO `sys_i18n` VALUES (1387780998132953089, 'grgmsSys', 'biz', 'biz.delName', 'zh', '业务配置', 'lfeng18', '2021-04-29 22:49:08', 'lfeng18', '2021-04-29 22:49:08', NULL);
INSERT INTO `sys_i18n` VALUES (1387781847156551682, 'grgmsSys', 'menu', 'menu.delName', 'zh', '菜单', 'lfeng18', '2021-04-29 22:52:30', 'lfeng18', '2021-04-29 22:52:30', NULL);
INSERT INTO `sys_i18n` VALUES (1387782252846411777, 'grgmsSys', 'dic', 'dic.delName', 'zh', '字典码', 'lfeng18', '2021-04-29 22:54:07', 'lfeng18', '2021-04-29 22:54:07', NULL);
INSERT INTO `sys_i18n` VALUES (1387783190017503233, 'grgmsSys', 'com', 'com.warnning', 'zh', '提示', 'lfeng18', '2021-04-29 22:57:50', 'lfeng18', '2021-04-29 22:57:50', NULL);
INSERT INTO `sys_i18n` VALUES (1387783190323687426, 'grgmsSys', 'com', 'com.warnning', 'en', 'Warnning', 'lfeng18', '2021-04-29 22:57:50', 'lfeng18', '2021-04-29 22:57:50', NULL);
INSERT INTO `sys_i18n` VALUES (1387783587775934466, 'grgmsSys', 'btn', 'btn.confirm', 'zh', '确认', 'lfeng18', '2021-04-29 22:59:25', 'lfeng18', '2021-04-29 22:59:25', NULL);
INSERT INTO `sys_i18n` VALUES (1387783588065341441, 'grgmsSys', 'btn', 'btn.confirm', 'en', 'Confirm ', 'lfeng18', '2021-04-29 22:59:25', 'lfeng18', '2021-04-29 22:59:25', NULL);
INSERT INTO `sys_i18n` VALUES (1387784926631325697, 'grgmsSys', 'i18n', 'i18n.delName', 'zh', '国际化', 'lfeng18', '2021-04-29 23:04:44', 'lfeng18', '2021-04-29 23:04:44', NULL);
INSERT INTO `sys_i18n` VALUES (1387785154050682882, 'grgmsSys', 'area', 'area.delName', 'zh', '区域', 'lfeng18', '2021-04-29 23:05:39', 'lfeng18', '2021-04-29 23:05:39', NULL);
INSERT INTO `sys_i18n` VALUES (1387787845736886273, 'grgmsSys', 'role', 'role.delName', 'zh', '角色', 'lfeng18', '2021-04-29 23:16:20', 'lfeng18', '2021-04-29 23:16:20', NULL);
INSERT INTO `sys_i18n` VALUES (1387788424039133186, 'grgmsSys', 'user', 'user.delName', 'zh', '用户 ', 'lfeng18', '2021-04-29 23:18:38', 'lfeng18', '2021-04-29 23:18:38', NULL);
INSERT INTO `sys_i18n` VALUES (1387790971252535297, 'grgmsSys', 'user', 'user.unlock', 'zh', '解锁账号', 'admin', '2021-04-29 23:28:46', 'admin', '2021-04-29 23:28:46', NULL);
INSERT INTO `sys_i18n` VALUES (1387791774608551938, 'grgmsSys', 'user', 'user.oldPassword', 'zh', '旧密码', 'admin', '2021-04-29 23:31:57', 'admin', '2021-04-29 23:31:57', NULL);
INSERT INTO `sys_i18n` VALUES (1387791847149039618, 'grgmsSys', 'user', 'user.newPassword', 'zh', '新密码', 'admin', '2021-04-29 23:32:14', 'admin', '2021-04-29 23:32:14', NULL);
INSERT INTO `sys_i18n` VALUES (1387793616168054785, 'grgmsSys', 'user', 'user.passwordTooSimpleTips', 'zh', '密码过于简单，请修改密码', 'admin', '2021-04-29 23:39:16', 'admin', '2021-04-29 23:39:16', NULL);
INSERT INTO `sys_i18n` VALUES (1388041690056519682, 'grgmsSys', 'com', 'com.copyright', 'en', 'Copyright {0} 2020 GRGBanking Equipment Co.Ltd', 'lfeng18', '2021-04-30 16:05:02', 'lfeng18', '2021-04-30 16:05:02', NULL);
INSERT INTO `sys_i18n` VALUES (1388041690111045633, 'grgmsSys', 'com', 'com.copyright', 'zh', 'Copyright {0} 2021 广州广电运通金融电子股份有限公司', 'lfeng18', '2021-04-30 16:05:02', 'lfeng18', '2021-04-30 16:05:02', NULL);
INSERT INTO `sys_i18n` VALUES (1388044218965979137, 'grgmsSys', 'btn', 'btn.upload', 'en', 'Upload', 'admin', '2021-04-30 16:15:05', 'admin', '2021-04-30 16:15:05', NULL);
INSERT INTO `sys_i18n` VALUES (1388044219242803202, 'grgmsSys', 'btn', 'btn.upload', 'zh', '上传', 'admin', '2021-04-30 16:15:05', 'admin', '2021-04-30 16:15:05', NULL);
INSERT INTO `sys_i18n` VALUES (1388045416565600258, 'grgmsSys', 'btn', 'btn.sure', 'zh', '确定', 'admin', '2021-04-30 16:19:50', 'admin', '2021-04-30 16:19:50', NULL);
INSERT INTO `sys_i18n` VALUES (1388050053414809602, 'grgmsSys', 'dict', 'dict.roleType.workticket.manager', 'zh', '工单管理角色', 'cyfeng', '2021-04-30 16:38:16', 'cyfeng', '2021-04-30 16:38:16', NULL);
INSERT INTO `sys_i18n` VALUES (1388050258579189762, 'grgmsSys', 'dict', 'dict.roleType.workticket.engineer', 'zh', '工单用户角色', 'cyfeng', '2021-04-30 16:39:05', 'cyfeng', '2021-04-30 16:39:05', NULL);
INSERT INTO `sys_i18n` VALUES (1390328713874272258, 'grgmsSys', 'date', 'date.goodMorning1', 'en', 'Good morning,', 'lfeng18', '2021-05-06 23:32:51', 'lfeng18', '2021-05-06 23:32:51', NULL);
INSERT INTO `sys_i18n` VALUES (1390328714276925442, 'grgmsSys', 'date', 'date.goodMorning1', 'zh', '凌晨好，', 'lfeng18', '2021-05-06 23:32:51', 'lfeng18', '2021-05-06 23:32:51', NULL);
INSERT INTO `sys_i18n` VALUES (1390328752440897538, 'grgmsSys', 'date', 'date.goodMorning2', 'zh', '早上好，', 'lfeng18', '2021-05-06 23:33:00', 'lfeng18', '2021-05-06 23:33:00', NULL);
INSERT INTO `sys_i18n` VALUES (1390328752600281090, 'grgmsSys', 'date', 'date.goodMorning2', 'en', 'Good morning,', 'lfeng18', '2021-05-06 23:33:00', 'lfeng18', '2021-05-06 23:33:00', NULL);
INSERT INTO `sys_i18n` VALUES (1390328791716360193, 'grgmsSys', 'date', 'date.goodMorning3', 'zh', '上午好，', 'lfeng18', '2021-05-06 23:33:09', 'lfeng18', '2021-05-06 23:33:09', NULL);
INSERT INTO `sys_i18n` VALUES (1390328791879938049, 'grgmsSys', 'date', 'date.goodMorning3', 'en', 'Good morning,', 'lfeng18', '2021-05-06 23:33:09', 'lfeng18', '2021-05-06 23:33:09', NULL);
INSERT INTO `sys_i18n` VALUES (1390328856245727233, 'grgmsSys', 'date', 'date.goodAfternoon1', 'en', 'Good afternoon,', 'lfeng18', '2021-05-06 23:33:25', 'lfeng18', '2021-05-06 23:33:25', NULL);
INSERT INTO `sys_i18n` VALUES (1390328856421888002, 'grgmsSys', 'date', 'date.goodAfternoon1', 'zh', '中午好，', 'lfeng18', '2021-05-06 23:33:25', 'lfeng18', '2021-05-06 23:33:25', NULL);
INSERT INTO `sys_i18n` VALUES (1390328909572108289, 'grgmsSys', 'date', 'date.goodAfternoon2', 'en', 'Good afternoon,', 'lfeng18', '2021-05-06 23:33:37', 'lfeng18', '2021-05-06 23:33:37', NULL);
INSERT INTO `sys_i18n` VALUES (1390328909861515266, 'grgmsSys', 'date', 'date.goodAfternoon2', 'zh', '下午好，', 'lfeng18', '2021-05-06 23:33:37', 'lfeng18', '2021-05-06 23:33:37', NULL);
INSERT INTO `sys_i18n` VALUES (1390328960788754434, 'grgmsSys', 'date', 'date.goodEvening1', 'en', 'Good evening,', 'lfeng18', '2021-05-06 23:33:49', 'lfeng18', '2021-05-06 23:33:49', NULL);
INSERT INTO `sys_i18n` VALUES (1390328960939749377, 'grgmsSys', 'date', 'date.goodEvening1', 'zh', '傍晚好，', 'lfeng18', '2021-05-06 23:33:50', 'lfeng18', '2021-05-06 23:33:50', NULL);
INSERT INTO `sys_i18n` VALUES (1390329000982769666, 'grgmsSys', 'date', 'date.goodEvening2', 'zh', '晚上好，', 'lfeng18', '2021-05-06 23:33:59', 'lfeng18', '2021-05-06 23:33:59', NULL);
INSERT INTO `sys_i18n` VALUES (1390329001142153217, 'grgmsSys', 'date', 'date.goodEvening2', 'en', 'Good evening,', 'lfeng18', '2021-05-06 23:33:59', 'lfeng18', '2021-05-06 23:33:59', NULL);
INSERT INTO `sys_i18n` VALUES (1390329061061980161, 'grgmsSys', 'date', 'date.goodEvening3', 'zh', '夜里好，', 'lfeng18', '2021-05-06 23:34:13', 'lfeng18', '2021-05-06 23:34:13', NULL);
INSERT INTO `sys_i18n` VALUES (1390329061254918146, 'grgmsSys', 'date', 'date.goodEvening3', 'en', 'Good evening,', 'lfeng18', '2021-05-06 23:34:13', 'lfeng18', '2021-05-06 23:34:13', NULL);
INSERT INTO `sys_i18n` VALUES (1390331096943587330, 'grgmsSys', 'date', 'date.lastLoginTime', 'zh', '上次登录时间：', 'lfeng18', '2021-05-06 23:42:19', 'lfeng18', '2021-05-06 23:42:19', NULL);
INSERT INTO `sys_i18n` VALUES (1390331097308491778, 'grgmsSys', 'date', 'date.lastLoginTime', 'en', 'Last login time:', 'lfeng18', '2021-05-06 23:42:19', 'lfeng18', '2021-05-06 23:42:19', NULL);
INSERT INTO `sys_i18n` VALUES (1390331552017182721, 'grgmsSys', 'date', 'date.lastPwdSetTime', 'zh', '上次修改密码时间：', 'lfeng18', '2021-05-06 23:44:07', 'lfeng18', '2021-05-06 23:44:07', NULL);
INSERT INTO `sys_i18n` VALUES (1390331552176566274, 'grgmsSys', 'date', 'date.lastPwdSetTime', 'en', 'Last update password time:', 'lfeng18', '2021-05-06 23:44:07', 'lfeng18', '2021-05-06 23:44:07', NULL);
INSERT INTO `sys_i18n` VALUES (1390947396471623681, 'grgmsSys', 'exception', 'exception.menu1021', 'zh', '同一菜单下，按钮名称唯一', 'cyfeng', '2021-05-08 16:31:16', 'cyfeng', '2021-05-08 16:31:16', NULL);
INSERT INTO `sys_i18n` VALUES (1390947477144866817, 'grgmsSys', 'exception', 'exception.menu1022', 'zh', '同一应用类型下，系统、目录、菜单名称唯一', 'cyfeng', '2021-05-08 16:31:35', 'cyfeng', '2021-05-08 16:31:35', NULL);
INSERT INTO `sys_i18n` VALUES (1392036271605444609, 'grgmsSys', 'org', 'org.name', 'zh', '机构名称', 'lfeng18', '2021-05-11 16:38:04', 'lfeng18', '2021-05-11 16:38:04', NULL);
INSERT INTO `sys_i18n` VALUES (1392051770250784770, 'grgmsSys', 'area', 'area.rootCannotEdit', 'zh', '根节点不允许修改', 'lfeng18', '2021-05-11 17:39:39', 'lfeng18', '2021-05-11 17:39:39', NULL);
INSERT INTO `sys_i18n` VALUES (1392051770531803137, 'grgmsSys', 'area', 'area.rootCannotEdit', 'en', 'Root no allow edit', 'lfeng18', '2021-05-11 17:39:39', 'lfeng18', '2021-05-11 17:39:39', NULL);
INSERT INTO `sys_i18n` VALUES (1392760263500918786, 'grgmsSys', 'user', 'user.usernameErrorTips', 'zh', '账号由字母和数字组合，4到16位 ', 'lfeng18', '2021-05-13 16:34:57', 'lfeng18', '2021-05-13 16:34:57', NULL);
INSERT INTO `sys_i18n` VALUES (1393173562872852481, 'grgmsSys', 'exception', 'exception.logout1004', 'zh', '请先登录', 'cjia', '2021-05-14 19:57:15', 'cjia', '2021-05-14 19:57:15', NULL);
INSERT INTO `sys_i18n` VALUES (1393173562994487297, 'grgmsSys', 'exception', 'exception.logout1004', 'en', 'Please Login System', 'cjia', '2021-05-14 19:57:15', 'cjia', '2021-05-14 19:57:15', NULL);
INSERT INTO `sys_i18n` VALUES (1395206860221243394, 'grgmsSys', 'strategy2', 'strategy2.sceneName', 'zh', '场景名称', 'wyxu', '2021-05-20 10:36:51', 'wyxu', '2021-05-20 10:36:51', NULL);
INSERT INTO `sys_i18n` VALUES (1395206860363849730, 'grgmsSys', 'strategy2', 'strategy2.sceneName', 'en', 'Scene Name', 'wyxu', '2021-05-20 10:36:51', 'wyxu', '2021-05-20 10:36:51', NULL);
INSERT INTO `sys_i18n` VALUES (1395207091696492546, 'grgmsSys', 'strategy2', 'strategy2.strategyName', 'zh', '策略名称', 'wyxu', '2021-05-20 10:37:47', 'wyxu', '2021-05-20 10:37:47', NULL);
INSERT INTO `sys_i18n` VALUES (1395207091725852673, 'grgmsSys', 'strategy2', 'strategy2.strategyName', 'en', 'Strategy Name', 'wyxu', '2021-05-20 10:37:47', 'wyxu', '2021-05-20 10:37:47', NULL);
INSERT INTO `sys_i18n` VALUES (1395209094174662657, 'grgmsSys', 'strategy2', 'strategy2.strategyCode', 'zh', '策略编码', 'wyxu', '2021-05-20 10:45:44', 'wyxu', '2021-05-20 10:45:44', NULL);
INSERT INTO `sys_i18n` VALUES (1395209094795419650, 'grgmsSys', 'strategy2', 'strategy2.strategyCode', 'en', 'Strategy Code', 'wyxu', '2021-05-20 10:45:44', 'wyxu', '2021-05-20 10:45:44', NULL);
INSERT INTO `sys_i18n` VALUES (1397789656434634754, 'grgmsSys', 'org', 'org.rootCannotEdit', 'en', 'Root no allow edit', 'admin', '2021-05-27 13:39:58', 'admin', '2021-05-27 13:39:58', NULL);
INSERT INTO `sys_i18n` VALUES (1397789657239941122, 'grgmsSys', 'org', 'org.rootCannotEdit', 'zh', '根节点不允许修改', 'admin', '2021-05-27 13:39:58', 'admin', '2021-05-27 13:39:58', NULL);
INSERT INTO `sys_i18n` VALUES (1397791458899030017, 'grgmsSys', 'area', 'area.parentNameErrorTips', 'zh', '父区域不能为本身', 'admin', '2021-05-27 13:47:08', 'admin', '2021-05-27 13:47:08', NULL);
INSERT INTO `sys_i18n` VALUES (1397791645264539649, 'grgmsSys', 'org', 'org.parentNameErrorTips', 'zh', '父节点不能为本身', 'admin', '2021-05-27 13:47:52', 'admin', '2021-05-27 13:47:52', NULL);
INSERT INTO `sys_i18n` VALUES (1397810928559464449, 'grgmsSys', 'btn', 'btn.addorcopy', 'zh', '新增/复制', 'admin', '2021-05-27 15:04:30', 'admin', '2021-05-27 15:04:30', NULL);
INSERT INTO `sys_i18n` VALUES (1397810928597213186, 'grgmsSys', 'btn', 'btn.addorcopy', 'en', 'add or copy', 'admin', '2021-05-27 15:04:30', 'admin', '2021-05-27 15:04:30', NULL);
INSERT INTO `sys_i18n` VALUES (1397815341156954113, 'grgmsSys', 'menu', 'menu.inCatalogOrSystem', 'zh', '目录只能添加在系统或目录下', 'lfeng18', '2021-05-27 15:22:02', 'lfeng18', '2021-05-27 15:22:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `OPERATION` varchar(150) DEFAULT NULL COMMENT '操作说明',
  `METHOD` varchar(200) DEFAULT NULL COMMENT '调用方法',
  `PARAMS` text COMMENT '操作参数',
  `IP` varchar(64) DEFAULT NULL COMMENT '来源IP',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  `TIME` bigint(22) DEFAULT NULL COMMENT '执行时长',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `PARENT_CODE` varchar(100) NOT NULL COMMENT '父编码',
  `MENU_CODE` varchar(100) NOT NULL COMMENT '编码',
  `MENU_PATH` text NOT NULL COMMENT '结构PATH',
  `APP_TYPE` varchar(32) NOT NULL COMMENT '应用类型',
  `TYPE` varchar(16) NOT NULL COMMENT '资源类型',
  `NAME` varchar(64) NOT NULL COMMENT '名称',
  `I18N_CODE` varchar(100) DEFAULT NULL COMMENT '国际化编码',
  `HOST` varchar(32) DEFAULT NULL COMMENT '请求主机',
  `PORT` varchar(8) DEFAULT NULL COMMENT '请求端口',
  `PATH` text COMMENT '请求路径',
  `ICON` text COMMENT '图标',
  `ORDER_NUM` int(22) DEFAULT NULL COMMENT '序号',
  `DESCRIPTION` text COMMENT '描述',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用(0:停用;1:启用)',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1393133328562941955 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '0', 'unitUser', 'unitUser', 'grgmsSys', '-1', '用户中心', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'admin', '2021-03-31 13:57:54', NULL);
INSERT INTO `sys_menu` VALUES (2, 'unitUser', 'orgUser', 'unitUser_1001', 'grgmsSys', '0', '机构用户', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 'orgUser', 'areaMan', 'unitUser_1001_1001', 'grgmsSys', '1', '区域管理', NULL, NULL, NULL, 'unitUser/orgUser/areaMan', 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'liufeng', '2021-04-08 16:32:46', NULL);
INSERT INTO `sys_menu` VALUES (5, 'areaMan', 'areaAdd', 'unitUser_1001_1001_1002', 'grgmsSys', '2', '新增/复制', 'btn.addorcopy', NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'admin', '2021-05-27 15:10:38', NULL);
INSERT INTO `sys_menu` VALUES (6, 'areaMan', 'areaEdit', 'unitUser_1001_1001_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'qwer', '2021-05-13 15:08:58', NULL);
INSERT INTO `sys_menu` VALUES (7, 'areaMan', 'areaDelete', 'unitUser_1001_1001_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'qwer', '2021-05-13 15:09:00', NULL);
INSERT INTO `sys_menu` VALUES (8, 'areaMan', 'areaSwitch', 'unitUser_1001_1001_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:31', 'cyfeng', '2021-05-21 15:27:01', NULL);
INSERT INTO `sys_menu` VALUES (9, 'orgUser', 'orgMan', 'unitUser_1001_1002', 'grgmsSys', '1', '机构管理', NULL, NULL, NULL, 'unitUser/orgUser/orgMan', 'icon-ic_tongyiyonghu', 2, NULL, '1', 'admin', '2021-03-09 16:46:31', 'admin', '2021-03-31 14:21:32', NULL);
INSERT INTO `sys_menu` VALUES (11, 'orgMan', 'orgAdd', 'unitUser_1001_1002_1002', 'grgmsSys', '2', '新增/复制', 'btn.addorcopy', NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', 'admin', '2021-05-27 15:10:46', NULL);
INSERT INTO `sys_menu` VALUES (12, 'orgMan', 'orgEdit', 'unitUser_1001_1002_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (13, 'orgMan', 'orgDelete', 'unitUser_1001_1002_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (14, 'orgMan', 'orgSwitch', 'unitUser_1001_1002_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', 'cyfeng', '2021-05-21 15:27:14', NULL);
INSERT INTO `sys_menu` VALUES (15, 'orgUser', 'userMan', 'unitUser_1001_1003', 'grgmsSys', '1', '用户管理', NULL, NULL, NULL, 'unitUser/orgUser/userMan', 'icon-ic_tongyiyonghu', 3, NULL, '1', 'admin', '2021-03-09 16:46:32', 'admin', '2021-03-31 14:34:48', NULL);
INSERT INTO `sys_menu` VALUES (17, 'userMan', 'userAdd', 'unitUser_1001_1003_1002', 'grgmsSys', '2', '新增', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (18, 'userMan', 'userEdit', 'unitUser_1001_1003_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (19, 'userMan', 'userDelete', 'unitUser_1001_1003_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (20, 'userMan', 'userSwitch', 'unitUser_1001_1003_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', 'cyfeng', '2021-05-21 15:27:32', NULL);
INSERT INTO `sys_menu` VALUES (22, 'userMan', 'userResetpwd', 'unitUser_1001_1003_1007', 'grgmsSys', '2', '重置密码', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '0', 'admin', '2021-03-09 16:46:32', 'qwer', '2021-05-13 15:22:39', NULL);
INSERT INTO `sys_menu` VALUES (23, 'userMan', 'userLocked', 'unitUser_1001_1003_1008', 'grgmsSys', '2', '重置锁定', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:32', 'cjia', '2021-05-14 20:06:56', NULL);
INSERT INTO `sys_menu` VALUES (24, 'orgUser', 'roleMan', 'unitUser_1001_1004', 'grgmsSys', '1', '角色管理', NULL, NULL, NULL, 'unitUser/orgUser/roleMan', 'icon-ic_tongyiyonghu', 4, NULL, '1', 'admin', '2021-03-09 16:46:32', 'admin', '2021-03-31 14:34:55', NULL);
INSERT INTO `sys_menu` VALUES (26, 'roleMan', 'roleAdd', 'unitUser_1001_1004_1002', 'grgmsSys', '2', '新增/复制', 'btn.addorcopy', NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'admin', '2021-05-27 15:10:54', NULL);
INSERT INTO `sys_menu` VALUES (27, 'roleMan', 'roleEdit', 'unitUser_1001_1004_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (28, 'roleMan', 'roleDelete', 'unitUser_1001_1004_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (29, 'roleMan', 'roleSwitch', 'unitUser_1001_1004_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'cyfeng', '2021-05-21 15:28:00', NULL);
INSERT INTO `sys_menu` VALUES (30, 'unitUser', 'sysConf', 'unitUser_1002', 'grgmsSys', '0', '系统配置', NULL, NULL, NULL, NULL, 'icon-ic_jigouyuyonghu', 2, NULL, '1', 'admin', '2021-03-09 16:46:34', 'admin', '2021-03-31 14:29:59', NULL);
INSERT INTO `sys_menu` VALUES (31, 'sysConf', 'menuMan', 'unitUser_1002_1001', 'grgmsSys', '1', '菜单管理', NULL, NULL, NULL, 'unitUser/sysConf/menuMan', 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (33, 'menuMan', 'menuAdd', 'unitUser_1002_1001_1002', 'grgmsSys', '2', '新增/复制', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'cyfeng', '2021-05-21 17:00:20', NULL);
INSERT INTO `sys_menu` VALUES (34, 'menuMan', 'menuEdit', 'unitUser_1002_1001_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'lfeng18', '2021-05-13 23:53:00', NULL);
INSERT INTO `sys_menu` VALUES (35, 'menuMan', 'menuDelete', 'unitUser_1002_1001_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'qwer', '2021-05-13 15:10:04', NULL);
INSERT INTO `sys_menu` VALUES (36, 'menuMan', 'menuSwitch', 'unitUser_1002_1001_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'cyfeng', '2021-05-21 15:28:12', NULL);
INSERT INTO `sys_menu` VALUES (37, 'sysConf', 'authorityMan', 'unitUser_1002_1002', 'grgmsSys', '1', '权限管理', NULL, NULL, NULL, 'unitUser/sysConf/authorityMan', 'icon-ic_tongyiyonghu', 2, NULL, '1', 'admin', '2021-03-09 16:46:34', 'admin', '2021-03-31 14:22:30', NULL);
INSERT INTO `sys_menu` VALUES (39, 'authorityMan', 'authorityAdd', 'unitUser_1002_1002_1002', 'grgmsSys', '2', '新增/复制', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'cyfeng', '2021-05-21 17:00:32', NULL);
INSERT INTO `sys_menu` VALUES (40, 'authorityMan', 'authorityEdit', 'unitUser_1002_1002_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'lfeng18', '2021-05-14 00:00:04', NULL);
INSERT INTO `sys_menu` VALUES (41, 'authorityMan', 'authorityDelete', 'unitUser_1002_1002_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (42, 'authorityMan', 'authoritySwitch', 'unitUser_1002_1002_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:34', 'cyfeng', '2021-05-21 15:28:23', NULL);
INSERT INTO `sys_menu` VALUES (43, 'sysConf', 'dictMan', 'unitUser_1002_1003', 'grgmsSys', '1', '字典码管理', NULL, NULL, NULL, 'unitUser/sysConf/dictMan', 'icon-ic_tongyiyonghu', 3, NULL, '1', 'admin', '2021-03-09 16:46:36', 'admin', '2021-03-31 14:22:45', NULL);
INSERT INTO `sys_menu` VALUES (45, 'dictMan', 'dictAdd', 'unitUser_1002_1003_1002', 'grgmsSys', '2', '新增/复制', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', 'cyfeng', '2021-05-21 17:00:42', NULL);
INSERT INTO `sys_menu` VALUES (46, 'dictMan', 'dictEdit', 'unitUser_1002_1003_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (47, 'dictMan', 'dictDelete', 'unitUser_1002_1003_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (48, 'dictMan', 'dictSwitch', 'unitUser_1002_1003_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', 'cyfeng', '2021-05-21 15:28:33', NULL);
INSERT INTO `sys_menu` VALUES (49, 'sysConf', 'i18nMan', 'unitUser_1002_1004', 'grgmsSys', '1', '国际化管理', NULL, NULL, NULL, 'unitUser/sysConf/i18nMan', 'icon-ic_tongyiyonghu', 4, NULL, '1', 'admin', '2021-03-09 16:46:36', 'admin', '2021-03-31 14:22:56', NULL);
INSERT INTO `sys_menu` VALUES (51, 'i18nMan', 'i18nAdd', 'unitUser_1002_1004_1002', 'grgmsSys', '2', '新增/复制', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', 'cyfeng', '2021-05-21 17:00:56', NULL);
INSERT INTO `sys_menu` VALUES (52, 'i18nMan', 'i18nEdit', 'unitUser_1002_1004_1003', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (53, 'i18nMan', 'i18nDelete', 'unitUser_1002_1004_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (54, 'i18nMan', 'i18nSwitch', 'unitUser_1002_1004_1005', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, 'icon-ic_tongyiyonghu', 1, NULL, '1', 'admin', '2021-03-09 16:46:36', 'cyfeng', '2021-05-21 15:28:44', NULL);
INSERT INTO `sys_menu` VALUES (1376802748468588546, 'logMan', 'queryLog', 'unitUser_1004_1001', 'grgmsSys', '1', '操作日志', NULL, NULL, NULL, 'unitUser/sysConf/queryLog', NULL, 1, NULL, '1', 'lfeng18', '2021-03-30 15:45:29', 'admin', '2021-03-31 14:23:17', NULL);
INSERT INTO `sys_menu` VALUES (1377131348203958273, 'unitUser', 'logMan', 'unitUser_1004', 'grgmsSys', '0', '日志管理', NULL, NULL, NULL, NULL, 'icon-ic_baobiao', 5, NULL, '1', 'admin', '2021-03-31 13:31:13', 'admin', '2021-04-02 16:35:44', NULL);
INSERT INTO `sys_menu` VALUES (1377790317158363137, 'unitUser', 'busiConfig', 'unitUser_1006', 'grgmsSys', '0', '业务配置', NULL, NULL, NULL, NULL, 'icon-ic_miyuejihuo', 4, NULL, '1', 'admin', '2021-04-02 09:09:44', 'admin', '2021-04-02 16:35:52', NULL);
INSERT INTO `sys_menu` VALUES (1377792512410939394, 'busiConfig', 'bizMan', 'unitUser_1006_1001', 'grgmsSys', '1', '业务配置管理', NULL, NULL, NULL, 'unitUser/bizConf/bizMan', NULL, 1, NULL, '1', 'admin', '2021-04-02 09:18:27', 'admin', '2021-04-02 11:13:37', NULL);
INSERT INTO `sys_menu` VALUES (1377797772038795265, 'bizMan', 'busiconf.add', 'unitUser_1006_1001_1002', 'grgmsSys', '2', '新增', NULL, NULL, NULL, NULL, NULL, 2, NULL, '1', 'cyfeng', '2021-04-02 09:39:21', 'cyfeng', '2021-04-02 09:39:21', NULL);
INSERT INTO `sys_menu` VALUES (1377798912361000962, 'bizMan', 'busiconf.status', 'unitUser_1006_1001_1003', 'grgmsSys', '2', '停用/启用', NULL, NULL, NULL, NULL, NULL, 4, NULL, '1', 'cyfeng', '2021-04-02 09:43:53', 'cyfeng', '2021-04-02 09:43:53', NULL);
INSERT INTO `sys_menu` VALUES (1377799160529580034, 'bizMan', 'busiconf.update', 'unitUser_1006_1001_1004', 'grgmsSys', '2', '修改', NULL, NULL, NULL, NULL, NULL, 5, NULL, '1', 'cyfeng', '2021-04-02 09:44:52', 'cyfeng', '2021-04-02 09:44:52', NULL);
INSERT INTO `sys_menu` VALUES (1377799300422201345, 'bizMan', 'busiconf.delete', 'unitUser_1006_1001_1005', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, NULL, 6, NULL, '1', 'cyfeng', '2021-04-02 09:45:26', 'cyfeng', '2021-04-02 09:45:26', NULL);
INSERT INTO `sys_menu` VALUES (1379602570175082498, 'busiConfig', 'canaryMan', 'unitUser_1006_1002', 'grgmsSys', '1', '灰度配置管理', NULL, NULL, NULL, 'canaryMan/list', NULL, 1, NULL, '1', 'lfeng18', '2021-04-07 09:10:59', 'lfeng18', '2021-04-07 09:10:59', NULL);
INSERT INTO `sys_menu` VALUES (1382973882180333570, 'executorMan', 'executorManSearch', 'unitUser_1007_1003_1001', 'grgmsSys', '2', '查看', NULL, NULL, NULL, NULL, NULL, 1, NULL, '1', 'liufeng', '2021-04-16 16:27:22', 'liufeng', '2021-04-16 16:27:22', NULL);
INSERT INTO `sys_menu` VALUES (1382974117182992385, 'executorMan', 'executorManSave', 'unitUser_1007_1003_1002', 'grgmsSys', '2', '保存', NULL, NULL, NULL, NULL, NULL, 1, NULL, '1', 'liufeng', '2021-04-16 16:28:18', 'liufeng', '2021-04-16 16:28:18', NULL);
INSERT INTO `sys_menu` VALUES (1382974331272851458, 'executorMan', 'executorManUpdate', 'unitUser_1007_1003_1003', 'grgmsSys', '2', '更新', NULL, NULL, NULL, NULL, NULL, 1, NULL, '1', 'liufeng', '2021-04-16 16:29:09', 'liufeng', '2021-04-16 16:29:09', NULL);
INSERT INTO `sys_menu` VALUES (1382974444330315777, 'executorMan', 'executorManDelete', 'unitUser_1007_1003_1004', 'grgmsSys', '2', '删除', NULL, NULL, NULL, NULL, NULL, 1, NULL, '1', 'liufeng', '2021-04-16 16:29:36', 'liufeng', '2021-04-16 16:29:36', NULL);
INSERT INTO `sys_menu` VALUES (1393107758202515457, 'templateMan', 'imptManAdd', 'unitUser_1003_1003_1005', 'grgmsSys', '2', '新增/复制', 'btn.addorcopy', NULL, NULL, NULL, NULL, 4, NULL, '1', 'lfeng18', '2021-05-14 15:35:46', 'cyfeng', '2021-05-31 09:11:21', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_authority`;
CREATE TABLE `sys_menu_authority` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `AUTHORITY_ID` bigint(22) NOT NULL COMMENT '权限ID',
  `MENU_ID` bigint(22) NOT NULL COMMENT '菜单ID',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用(0:停用;1:启用)',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1399242803081588739 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单授权表';

-- ----------------------------
-- Records of sys_menu_authority
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu_authority` VALUES (9, 9, 13, '1', 'admin', '2021-03-09 16:39:58', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (12, 12, 17, '1', 'admin', '2021-03-09 16:39:58', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (14, 14, 19, '1', 'admin', '2021-03-09 16:39:58', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (22, 22, 28, '1', 'admin', '2021-03-09 16:40:00', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (27, 27, 35, '1', 'admin', '2021-03-09 16:40:01', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (36, 36, 46, '1', 'admin', '2021-03-09 16:40:02', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (37, 37, 47, '1', 'admin', '2021-03-09 16:40:02', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (41, 41, 52, '1', 'admin', '2021-03-09 16:40:02', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (42, 42, 53, '1', 'admin', '2021-03-09 16:40:02', NULL, NULL, NULL);
INSERT INTO `sys_menu_authority` VALUES (1377906744923025410, 52, 1374591826022920193, '1', 'lfeng18', '2021-04-02 16:52:22', 'lfeng18', '2021-04-02 16:52:22', NULL);
INSERT INTO `sys_menu_authority` VALUES (1392870501679919105, 26, 34, '1', 'lfeng18', '2021-05-13 23:53:00', 'lfeng18', '2021-05-13 23:53:00', NULL);
INSERT INTO `sys_menu_authority` VALUES (1392872280157089794, 31, 40, '1', 'lfeng18', '2021-05-14 00:00:04', 'lfeng18', '2021-05-14 00:00:04', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395255249580617730, 13, 18, '1', 'cyfeng', '2021-05-20 13:49:08', 'cyfeng', '2021-05-20 13:49:08', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395255281394413569, 8, 12, '1', 'cyfeng', '2021-05-20 13:49:16', 'cyfeng', '2021-05-20 13:49:16', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395255325120032770, 3, 6, '1', 'cyfeng', '2021-05-20 13:49:26', 'cyfeng', '2021-05-20 13:49:26', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395279502526935041, 18, 22, '1', 'lfeng18', '2021-05-20 15:25:31', 'lfeng18', '2021-05-20 15:25:31', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642268374822913, 5, 8, '1', 'cyfeng', '2021-05-21 15:27:01', 'cyfeng', '2021-05-21 15:27:01', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642398331138049, 15, 20, '1', 'cyfeng', '2021-05-21 15:27:32', 'cyfeng', '2021-05-21 15:27:32', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642515826176001, 23, 29, '1', 'cyfeng', '2021-05-21 15:28:00', 'cyfeng', '2021-05-21 15:28:00', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642568049455106, 28, 36, '1', 'cyfeng', '2021-05-21 15:28:12', 'cyfeng', '2021-05-21 15:28:12', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642614056775681, 33, 42, '1', 'cyfeng', '2021-05-21 15:28:23', 'cyfeng', '2021-05-21 15:28:23', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395642703252844546, 43, 54, '1', 'cyfeng', '2021-05-21 15:28:44', 'cyfeng', '2021-05-21 15:28:44', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395665802769756161, 30, 39, '1', 'cyfeng', '2021-05-21 17:00:32', 'cyfeng', '2021-05-21 17:00:32', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395665847346819074, 35, 45, '1', 'cyfeng', '2021-05-21 17:00:42', 'cyfeng', '2021-05-21 17:00:42', NULL);
INSERT INTO `sys_menu_authority` VALUES (1395665906331316225, 40, 51, '1', 'cyfeng', '2021-05-21 17:00:56', 'cyfeng', '2021-05-21 17:00:56', NULL);
INSERT INTO `sys_menu_authority` VALUES (1397812472843476994, 2, 5, '1', 'admin', '2021-05-27 15:10:38', 'admin', '2021-05-27 15:10:38', NULL);
INSERT INTO `sys_menu_authority` VALUES (1397812507610062850, 7, 11, '1', 'admin', '2021-05-27 15:10:46', 'admin', '2021-05-27 15:10:46', NULL);
INSERT INTO `sys_menu_authority` VALUES (1397812542926102529, 20, 26, '1', 'admin', '2021-05-27 15:10:54', 'admin', '2021-05-27 15:10:54', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PARENT_CODE` varchar(100) NOT NULL COMMENT '父编码',
  `ORG_PATH` text NOT NULL COMMENT '结构path',
  `I18N_CODE` varchar(100) NOT NULL COMMENT '国际码',
  `ORG_CODE` varchar(100) NOT NULL COMMENT '编码',
  `FIN_CODE` varchar(32) DEFAULT NULL COMMENT '金融机构编码',
  `NAME` varchar(200) NOT NULL COMMENT '名称',
  `FULLNAME` text COMMENT '机构全称',
  `AREA_ID` bigint(22) DEFAULT NULL COMMENT '区域id',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用1=启用,0=不启用',
  `ADDRESS` text COMMENT '地址',
  `DESCRIPTION` text COMMENT '描述',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` VALUES (1, '0', 'icbc', 'name.icbc', 'icbc', NULL, '中国工商银行', '中国工商银行', 1, '1', '北京市东城区中国工商银行大厦', '中国工商银行', 'admin', '2021-03-09 16:40:40', 'admin', '2021-03-31 11:13:11', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user`;
CREATE TABLE `sys_org_user` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `ORG_ID` bigint(22) NOT NULL COMMENT '机构ID',
  `USER_ID` bigint(22) NOT NULL COMMENT '用户ID',
  `IS_LEADER` char(1) DEFAULT NULL COMMENT '是否负责人',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1395565163976044547 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统机构用户关系表';

-- ----------------------------
-- Records of sys_org_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_org_user` VALUES (1393316192675983362, 1, 1, '0', 'admin', '2021-05-15 05:24:01', NULL, NULL, NULL);
INSERT INTO `sys_org_user` VALUES (1393316192759869442, 1, 1, '1', 'admin', '2021-05-15 05:24:01', NULL, NULL, NULL);
INSERT INTO `sys_org_user` VALUES (1395565163946684418, 1, 1395565163565002754, '0', 'admin', '2021-05-21 02:20:38', NULL, NULL, NULL);
INSERT INTO `sys_org_user` VALUES (1395565163976044546, 1, 1395565163565002754, '1', 'admin', '2021-05-21 02:20:38', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `NAME` varchar(32) NOT NULL COMMENT '角色名称',
  `ROLE_TYPE` varchar(25) DEFAULT NULL COMMENT '角色类型',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `IS_ENABLED` char(1) NOT NULL COMMENT '是否启用',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1395541917012484098 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员角色', 'manage', NULL, '1', 'admin', '2021-03-09 16:40:55', 'admin', '2021-06-02 05:37:15', NULL);
INSERT INTO `sys_role` VALUES (4, '维护管理员角色', 'workticket.manager', '工单用户角色', '1', 'admin', '2021-03-09 16:40:56', 'admin', '2021-05-15 06:41:22', NULL);
INSERT INTO `sys_role` VALUES (5, '维护工程师角色', 'workticket.engineer', '工单用户角色', '1', 'admin', '2021-03-15 16:03:51', 'admin', '2021-03-22 10:45:35', NULL);
INSERT INTO `sys_role` VALUES (1395541917012484097, '系统用户', 'system', '', '1', 'admin', '2021-05-20 19:48:16', 'admin', '2021-06-02 05:39:55', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ROLE_ID` bigint(22) NOT NULL COMMENT '用户编号',
  `MENU_ID` bigint(22) NOT NULL COMMENT '菜单编号',
  `IS_LEADER` char(1) NOT NULL COMMENT '是否负责人：0可使用，1可分配',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  `IS_ENABLED` char(1) NOT NULL COMMENT '启用状态：0停用，1启用',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1399963969173786979 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1399963296684249089, 1, 1, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637698, 1, 2, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637699, 1, 3, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637700, 1, 5, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637701, 1, 6, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637702, 1, 7, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637703, 1, 8, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637704, 1, 9, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637705, 1, 11, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637706, 1, 12, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637707, 1, 13, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637708, 1, 14, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637709, 1, 15, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637710, 1, 17, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637711, 1, 18, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637712, 1, 19, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637713, 1, 20, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637714, 1, 23, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637715, 1, 24, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637716, 1, 26, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637717, 1, 27, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637718, 1, 28, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637719, 1, 29, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637720, 1, 30, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637721, 1, 31, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637722, 1, 33, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637723, 1, 34, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637724, 1, 35, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637725, 1, 36, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637726, 1, 37, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637727, 1, 39, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637728, 1, 40, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637729, 1, 41, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637730, 1, 42, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637731, 1, 43, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637732, 1, 45, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637733, 1, 46, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637734, 1, 47, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637735, 1, 48, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637736, 1, 49, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637737, 1, 51, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637738, 1, 52, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637739, 1, 53, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637740, 1, 54, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637749, 1, 1393107758202515457, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637750, 1, 1377131348203958273, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637751, 1, 1376802748468588546, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637770, 1, 1377790317158363137, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637771, 1, 1377792512410939394, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637772, 1, 1377797772038795265, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637773, 1, 1377798912361000962, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637774, 1, 1377799160529580034, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963296692637775, 1, 1377799300422201345, '1', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651010, 1, 1, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651011, 1, 2, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651012, 1, 3, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651013, 1, 5, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651014, 1, 6, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651015, 1, 7, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651016, 1, 8, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651017, 1, 9, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651018, 1, 11, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651019, 1, 12, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651020, 1, 13, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651021, 1, 14, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651022, 1, 15, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651023, 1, 17, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651024, 1, 18, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651025, 1, 19, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651026, 1, 20, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651027, 1, 23, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651028, 1, 24, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651029, 1, 26, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651030, 1, 27, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651031, 1, 28, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651032, 1, 29, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651033, 1, 30, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651034, 1, 31, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651035, 1, 33, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651036, 1, 34, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651037, 1, 35, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651038, 1, 36, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651039, 1, 37, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651040, 1, 39, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651041, 1, 40, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651042, 1, 41, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651043, 1, 42, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651044, 1, 43, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651045, 1, 45, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651046, 1, 46, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651047, 1, 47, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651048, 1, 48, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651049, 1, 49, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651050, 1, 51, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651051, 1, 52, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651052, 1, 53, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651053, 1, 54, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651062, 1, 1393107758202515457, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651063, 1, 1377131348203958273, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651064, 1, 1376802748468588546, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651083, 1, 1377790317158363137, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651084, 1, 1377792512410939394, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651085, 1, 1377797772038795265, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651086, 1, 1377798912361000962, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651087, 1, 1377799160529580034, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963297124651088, 1, 1377799300422201345, '0', 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346114, 1395541917012484097, 1, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346115, 1395541917012484097, 2, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346116, 1395541917012484097, 3, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346117, 1395541917012484097, 5, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346118, 1395541917012484097, 6, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346119, 1395541917012484097, 7, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346120, 1395541917012484097, 8, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346121, 1395541917012484097, 9, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346122, 1395541917012484097, 11, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346123, 1395541917012484097, 12, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346124, 1395541917012484097, 13, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346125, 1395541917012484097, 14, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346126, 1395541917012484097, 15, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346127, 1395541917012484097, 17, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346128, 1395541917012484097, 18, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346129, 1395541917012484097, 19, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346130, 1395541917012484097, 20, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346131, 1395541917012484097, 23, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346132, 1395541917012484097, 24, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346133, 1395541917012484097, 26, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346134, 1395541917012484097, 27, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346135, 1395541917012484097, 28, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346136, 1395541917012484097, 29, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346137, 1395541917012484097, 30, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346138, 1395541917012484097, 31, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346139, 1395541917012484097, 33, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346140, 1395541917012484097, 34, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346141, 1395541917012484097, 35, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346142, 1395541917012484097, 36, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346143, 1395541917012484097, 37, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346144, 1395541917012484097, 39, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346145, 1395541917012484097, 40, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346146, 1395541917012484097, 41, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346147, 1395541917012484097, 42, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346148, 1395541917012484097, 43, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346149, 1395541917012484097, 45, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346150, 1395541917012484097, 46, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346151, 1395541917012484097, 47, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346152, 1395541917012484097, 48, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346153, 1395541917012484097, 49, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346154, 1395541917012484097, 51, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346155, 1395541917012484097, 52, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346156, 1395541917012484097, 53, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346157, 1395541917012484097, 54, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346166, 1395541917012484097, 1393107758202515457, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346167, 1395541917012484097, 1377131348203958273, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346168, 1395541917012484097, 1376802748468588546, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346187, 1395541917012484097, 1377790317158363137, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346188, 1395541917012484097, 1377792512410939394, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346189, 1395541917012484097, 1377797772038795265, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346190, 1395541917012484097, 1377798912361000962, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346191, 1395541917012484097, 1377799160529580034, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346192, 1395541917012484097, 1377799300422201345, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346193, 1395541917012484097, 1379602570175082498, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346211, 1395541917012484097, 1382973882180333570, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346212, 1395541917012484097, 1382974117182992385, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346213, 1395541917012484097, 1382974331272851458, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969056346214, 1395541917012484097, 1382974444330315777, '1', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786626, 1395541917012484097, 1, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786627, 1395541917012484097, 2, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786628, 1395541917012484097, 3, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786629, 1395541917012484097, 5, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786630, 1395541917012484097, 6, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786631, 1395541917012484097, 7, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786632, 1395541917012484097, 8, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786633, 1395541917012484097, 9, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786634, 1395541917012484097, 11, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786635, 1395541917012484097, 12, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786636, 1395541917012484097, 13, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786637, 1395541917012484097, 14, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786638, 1395541917012484097, 15, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786639, 1395541917012484097, 17, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786640, 1395541917012484097, 18, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786641, 1395541917012484097, 19, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786642, 1395541917012484097, 20, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786643, 1395541917012484097, 23, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786644, 1395541917012484097, 24, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786645, 1395541917012484097, 26, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786646, 1395541917012484097, 27, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786647, 1395541917012484097, 28, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786648, 1395541917012484097, 29, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786649, 1395541917012484097, 30, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786650, 1395541917012484097, 31, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786651, 1395541917012484097, 33, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786652, 1395541917012484097, 34, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786653, 1395541917012484097, 35, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786654, 1395541917012484097, 36, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786655, 1395541917012484097, 37, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786656, 1395541917012484097, 39, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786657, 1395541917012484097, 40, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786658, 1395541917012484097, 41, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786659, 1395541917012484097, 42, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786660, 1395541917012484097, 43, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786661, 1395541917012484097, 45, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786662, 1395541917012484097, 46, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786663, 1395541917012484097, 47, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786664, 1395541917012484097, 48, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786665, 1395541917012484097, 49, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786666, 1395541917012484097, 51, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786667, 1395541917012484097, 52, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786668, 1395541917012484097, 53, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786669, 1395541917012484097, 54, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786678, 1395541917012484097, 1393107758202515457, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786679, 1395541917012484097, 1377131348203958273, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786680, 1395541917012484097, 1376802748468588546, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786699, 1395541917012484097, 1377790317158363137, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786700, 1395541917012484097, 1377792512410939394, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786701, 1395541917012484097, 1377797772038795265, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786702, 1395541917012484097, 1377798912361000962, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786703, 1395541917012484097, 1377799160529580034, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786704, 1395541917012484097, 1377799300422201345, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786705, 1395541917012484097, 1379602570175082498, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786723, 1395541917012484097, 1382973882180333570, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786724, 1395541917012484097, 1382974117182992385, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786725, 1395541917012484097, 1382974331272851458, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
INSERT INTO `sys_role_menu` VALUES (1399963969173786726, 1395541917012484097, 1382974444330315777, '0', 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `ROLE_ID` bigint(22) NOT NULL COMMENT '角色ID',
  `USER_ID` bigint(22) NOT NULL COMMENT '用户ID',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1399963969450610690 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色用户关系表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES (1399963297401475074, 1, 1, 'admin', '2021-06-02 05:37:15', NULL, NULL, NULL);
INSERT INTO `sys_role_user` VALUES (1399963969450610689, 1395541917012484097, 1395565163565002754, 'admin', '2021-06-02 05:39:55', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `USERNAME` varchar(32) NOT NULL COMMENT '帐号',
  `NAME` varchar(100) NOT NULL COMMENT '姓名',
  `TYPE` varchar(32) DEFAULT NULL COMMENT '用户类型',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `PASSWORD_CHANGED_DATE` datetime DEFAULT NULL COMMENT '密码最后一次修改时间',
  `IS_ENABLED` char(1) NOT NULL COMMENT '帐号是否启用',
  `IS_LOCKED` char(1) DEFAULT NULL COMMENT '帐号是否锁定',
  `LOCKED_REASON` varchar(100) DEFAULT NULL COMMENT '帐号锁定原因',
  `LOCKED_TIME` datetime DEFAULT NULL COMMENT '帐号锁定时间',
  `SEX` char(1) NOT NULL COMMENT '性别',
  `BIRTHDATE` datetime DEFAULT NULL COMMENT '生日',
  `NATIONALITY` varchar(6) DEFAULT NULL COMMENT '国籍',
  `CREDENTIALS_TYPE` varchar(32) DEFAULT NULL COMMENT '证件类型',
  `CREDENTIALS_NUMBER` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮件地址',
  `MOBILE_TELEPHONE` varchar(16) NOT NULL COMMENT '移动电话',
  `HOME_TELEPHONE` varchar(16) DEFAULT NULL COMMENT '家庭电话',
  `OFFICE_TELEPHONE` varchar(16) DEFAULT NULL COMMENT '办公电话',
  `FAX` varchar(16) DEFAULT NULL COMMENT '传真',
  `HOME_ADDRESS` text COMMENT '家庭住址',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `OPERATION_ID` varchar(16) DEFAULT NULL COMMENT '操作序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1395565163565002755 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', NULL, '$2a$10$H3p/ptZ9U8LoR1kwF5CF/eGn92yFTPO6ma0jgeOhVJMsw/oFyez.6', NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 'admin@grgbanking.com', '18888888888', NULL, NULL, NULL, NULL, 'admin', '2021-03-09 16:41:37', 'admin', '2021-05-15 05:24:01', NULL);
INSERT INTO `sys_user` VALUES (1395565163565002754, 'test', '测试普通用户', NULL, '$2a$10$H3p/ptZ9U8LoR1kwF5CF/eGn92yFTPO6ma0jgeOhVJMsw/oFyez.6', NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, '', '', '', '12356985415@qq.com', '13659542569', NULL, NULL, NULL, '', 'admin', '2021-05-21 02:20:38', NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
