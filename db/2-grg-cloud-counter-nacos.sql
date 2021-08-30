/*
 Navicat Premium Data Transfer

 Source Server         : 10.252.21.20
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 10.252.21.20:3306
 Source Schema         : grg-cloud-counter-nacos

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 30/08/2021 14:27:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_configdata_relation_pubs
-- ----------------------------
DROP TABLE IF EXISTS `app_configdata_relation_pubs`;
CREATE TABLE `app_configdata_relation_pubs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(128) NOT NULL,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_app_pub_config_datagroup` (`app_name`,`data_id`,`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_configdata_relation_pubs
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for app_configdata_relation_subs
-- ----------------------------
DROP TABLE IF EXISTS `app_configdata_relation_subs`;
CREATE TABLE `app_configdata_relation_subs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(128) NOT NULL,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_app_sub_config_datagroup` (`app_name`,`data_id`,`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_configdata_relation_subs
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for app_list
-- ----------------------------
DROP TABLE IF EXISTS `app_list`;
CREATE TABLE `app_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(128) NOT NULL,
  `is_dynamic_collect_disabled` smallint(6) DEFAULT '0',
  `last_sub_info_collected_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sub_info_lock_owner` varchar(128) DEFAULT NULL,
  `sub_info_lock_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_appname` (`app_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_list
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `app_name` varchar(128) DEFAULT NULL,
  `content` text,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` varchar(128) DEFAULT NULL,
  `src_ip` varchar(20) DEFAULT NULL,
  `c_desc` varchar(256) DEFAULT NULL,
  `c_use` varchar(64) DEFAULT NULL,
  `effect` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `c_schema` mediumtext,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE,
  KEY `configinfo_dataid_key_idx` (`data_id`) USING BTREE,
  KEY `configinfo_groupid_key_idx` (`group_id`) USING BTREE,
  KEY `configinfo_dataid_group_key_idx` (`data_id`,`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:9999/iam/oauth/token\n\n', '07c458fce323b90906635d8eafd37855', '2021-08-26 07:00:04', '2021-08-30 06:24:53', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'grg-cloud-counter-gateway.yml', 'DEFAULT_GROUP', '', '', 'spring:\n  cloud:\n    gateway:\n      globalcors:\n        corsConfigurations:\n          \'[/**]\':\n            allowedOriginPatterns: \'*\'\n            allowed-methods: \'*\'\n            allowed-headers: \'*\'\n            allow-credentials: true\n            exposedHeaders: \'Content-Disposition,Content-Type,Cache-Control\'\n      # Filters definitions that are applied to every route\n      default-filters:\n        - StripPrefix=1\n      # List of Routes.\n      # - id：路由id     uri：路由转发地址或者服务     - Path：请求uri匹配规则\n      routes:\n        # =============认证服务========================\n        - id: grg-cloud-counter-iam\n          uri: lb://grg-cloud-counter-iam\n          predicates:\n            - Path=/iam/**\n        #   filters:\n            # - name: ValidateCode\n        # =============坐席服务========================\n        - id: grg-cloud-counter-csr\n          uri: lb://grg-cloud-counter-csr\n          predicates:\n            - Path=/csr/**\n        # =============视频服务========================\n        - id: grg-cloud-counter-video\n          uri: lb://grg-cloud-counter-video\n          predicates:\n            - Path=/video/**\n        # =============监控服务========================\n        - id: grg-cloud-counter-monitor\n          uri: lb://grg-cloud-counter-monitor\n          predicates:\n            - Path=/monitor/**', '5ef4e99f742bf6ef374478dd4346d23d', '2021-08-26 07:07:56', '2021-08-28 04:48:45', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n   \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'', '43c74e84d948115004bafc31de71f4c5', '2021-08-26 07:32:56', '2021-08-30 06:22:03', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (11, 'redis.yml', 'DEFAULT_GROUP', '', '', 'spring: \n  redis:\n    password: ENC(3YIjfd4q53aJPlOow+9mgA==)\n    lettuce:\n      pool:\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\n        max-wait: -1     # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-idle: 10      # 连接池中的最大空闲连接\n        min-idle: 5       # 连接池中的最小空闲连接\n\n    # 单机模式\n    # host: localhost\n    # port: 6379\n\n    #哨兵模式\n    #    sentinel:\n    #      master: mymaster # redis哨兵名称，在sentinel配置文件配置\n    #      nodes:\n    #        - 10.1.231.60:7001\n    #        - 10.1.231.60:7002\n    #        - 10.1.231.60:7003\n\n    # cluster模式\n    cluster:\n      max-redirects: 100\n      nodes:\n        - 10.1.231.13:7001\n        - 10.1.231.13:7002\n        - 10.1.231.13:7003\n        - 10.1.231.13:7004\n        - 10.1.231.13:7005\n        - 10.1.231.13:7006\n', '00e218b1731ae8f31ff6a3ecb60103ae', '2021-08-26 08:40:46', '2021-08-30 06:26:30', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (14, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '03b09f100cd2227844803225038ac9f6', '2021-08-27 08:31:03', '2021-08-30 06:26:53', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (16, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '6f6d5a041dc06c26210f05158a46defe', '2021-08-27 08:32:36', '2021-08-30 06:27:06', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `datum_id` varchar(255) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL,
  `content` text,
  `gmt_modified` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `app_name` varchar(128) DEFAULT NULL,
  `content` text,
  `beta_ips` varchar(1024) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` varchar(128) DEFAULT NULL,
  `src_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `tag_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL,
  `content` text,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` varchar(128) DEFAULT NULL,
  `src_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL,
  `tag_name` varchar(128) NOT NULL,
  `tag_type` varchar(64) DEFAULT NULL,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`) USING BTREE,
  KEY `config_tags_tenant_id_idx` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(128) DEFAULT '',
  `quota` int(11) DEFAULT '0',
  `usage` int(11) DEFAULT '0',
  `max_size` int(11) DEFAULT '0',
  `max_aggr_count` int(11) DEFAULT '0',
  `max_aggr_size` int(11) DEFAULT '0',
  `max_history_count` int(11) DEFAULT '0',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(20) NOT NULL,
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `app_name` varchar(128) DEFAULT NULL,
  `content` text,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` varchar(128) DEFAULT NULL,
  `src_ip` varchar(20) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE,
  KEY `hisconfiginfo_dataid_key_idx` (`data_id`) USING BTREE,
  KEY `hisconfiginfo_gmt_create_idx` (`gmt_create`) USING BTREE,
  KEY `hisconfiginfo_gmt_modified_idx` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (1, 1, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'f570c689b1f29b49f5adae6737e3881c', '2010-05-05 00:00:00', '2021-08-28 04:42:11', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 2, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'e202c77a706e7251809b00e55d6d0bb6', '2010-05-05 00:00:00', '2021-08-28 04:43:40', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (14, 3, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', 'server:\n  port: 8010', 'a9b1dc297405e5c40b41c21a529a0880', '2010-05-05 00:00:00', '2021-08-28 04:44:28', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (16, 4, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', 'server:\n  port: 7080', '5397d659cce5be96176442500cd4a026', '2010-05-05 00:00:00', '2021-08-28 04:44:55', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (2, 5, 'grg-cloud-counter-gateway.yml', 'DEFAULT_GROUP', '', '', 'spring:\n  cloud:\n    gateway:\n      globalcors:\n        corsConfigurations:\n          \'[/**]\':\n            allowedOriginPatterns: \'*\'\n            allowed-methods: \'*\'\n            allowed-headers: \'*\'\n            allow-credentials: true\n            exposedHeaders: \'Content-Disposition,Content-Type,Cache-Control\'\n      # Filters definitions that are applied to every route\n      default-filters:\n        - StripPrefix=1\n      # List of Routes.\n      # - id：路由id     uri：路由转发地址或者服务     - Path：请求uri匹配规则\n      routes:\n        # =============认证服务========================\n        - id: grg-cloud-counter-iam\n          uri: lb://grg-cloud-counter-iam\n          predicates:\n            - Path=/iam/**\n        #   filters:\n            # - name: ValidateCode\n        # =============坐席服务========================\n        - id: grg-cloud-counter-csr\n          uri: lb://grg-cloud-counter-csr\n          predicates:\n            - Path=/csr/**\n        # =============视频服务========================\n        - id: grg-cloud-counter-video\n          uri: lb://grg-cloud-counter-video\n          predicates:\n            - Path=/video/**\n        # =============文件服务========================\n        - id: grg-cloud-counter-oss\n          uri: lb://grg-cloud-counter-oss\n          predicates:\n            - Path=/oss/**\n        # =============监控服务========================\n        - id: grg-cloud-counter-monitor\n          uri: lb://grg-cloud-counter-monitor\n          predicates:\n            - Path=/monitor/**', '7d70618357d6d8f12df628c447ed481c', '2010-05-05 00:00:00', '2021-08-28 04:48:45', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 6, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'e202c77a706e7251809b00e55d6d0bb6', '2010-05-05 00:00:00', '2021-08-28 05:03:59', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 7, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  # cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    # subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', '7faafa395236856203bedfc99849439a', '2010-05-05 00:00:00', '2021-08-28 05:10:11', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 8, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  # cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    # subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: true\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'ae17f06678f52f38dff5f97ca682e85e', '2010-05-05 00:00:00', '2021-08-28 05:11:08', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 9, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  # cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    # subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', '7faafa395236856203bedfc99849439a', '2010-05-05 00:00:00', '2021-08-28 05:13:10', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 10, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  # cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    # subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: true\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'ae17f06678f52f38dff5f97ca682e85e', '2010-05-05 00:00:00', '2021-08-28 05:15:17', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 11, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  # cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    # subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', '7faafa395236856203bedfc99849439a', '2010-05-05 00:00:00', '2021-08-28 05:20:10', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 12, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/*/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'e202c77a706e7251809b00e55d6d0bb6', '2010-05-05 00:00:00', '2021-08-29 08:45:54', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 13, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置\n# security:\n  # oauth2:\n  #   client:\n  #     client-id: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     client-secret: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     scope: server\n  #     ignore-urls:\n  #       - /error\n  #       - /druid/**\n  #       - /actuator/**\n  #       - /v2/api-docs\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', 'b28fedef69c8636af20e48f748215b80', '2010-05-05 00:00:00', '2021-08-29 14:05:27', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 14, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n  #     client-id: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     client-secret: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     scope: server\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v2/api-docs\n        - sys/area/tree\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', 'a371d0f355f1ae5df6e45e85cb718a8b', '2010-05-05 00:00:00', '2021-08-30 01:11:02', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 15, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n  #     client-id: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     client-secret: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     scope: server\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v2/api-docs\n        - /sys/area/tree\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', 'ff865f6ffb129c9f9bcb6792c06bd48b', '2010-05-05 00:00:00', '2021-08-30 01:21:51', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 16, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n  #     client-id: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     client-secret: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     scope: server\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n        - /sys/area/tree\n        - /biz/conf\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', '6a8d9c5d0ffb177ba19e1e777960c369', '2010-05-05 00:00:00', '2021-08-30 01:24:24', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 17, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n  #     client-id: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     client-secret: ENC(ltJPpR50wT0oIY9kfOe1Iw==)\n  #     scope: server\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n        - /sys/area/tree\n        - /biz/conf/query/list\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', '91905a7c08f80ce6f0bc2c0b362ae753', '2010-05-05 00:00:00', '2021-08-30 01:33:25', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 18, 'application.yml', 'DEFAULT_GROUP', '', '', '#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  cloud:\n    # 设置订阅的应用列表，默认为 * 订阅所有应用\n    subscribed-services: \'*\'\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n\n\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'fc4cd24377d87c8e9e2d63fe8810fd55', '2010-05-05 00:00:00', '2021-08-30 01:34:33', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (14, 19, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver', 'd01e65b3a3d27761061c958cdb92cffe', '2010-05-05 00:00:00', '2021-08-30 01:36:29', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (16, 20, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver', 'd742b6ee2a8aab8aaa106eddd94c00f3', '2010-05-05 00:00:00', '2021-08-30 01:36:41', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 21, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n        - /sys/area/tree\n        - /biz/conf/query/list\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n', '504aa0fb158641f55ab5d016bb6a77dd', '2010-05-05 00:00:00', '2021-08-30 02:10:45', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (11, 22, 'redis.yml', 'DEFAULT_GROUP', '', '', 'spring: \n  redis:\n    password: ENC(3YIjfd4q53aJPlOow+9mgA==)\n    lettuce:\n      pool:\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\n        max-wait: -1     # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-idle: 10      # 连接池中的最大空闲连接\n        min-idle: 5       # 连接池中的最小空闲连接\n\n    # 单机模式\n    host: localhost\n    port: 6379\n\n    #哨兵模式\n    #    sentinel:\n    #      master: mymaster # redis哨兵名称，在sentinel配置文件配置\n    #      nodes:\n    #        - 10.1.231.60:7001\n    #        - 10.1.231.60:7002\n    #        - 10.1.231.60:7003\n\n    # cluster模式\n    # cluster:\n      # max-redirects: 100\n      # nodes:\n        # - 10.1.231.13:7001\n        # - 10.1.231.13:7002\n        # - 10.1.231.13:7003\n        # - 10.1.231.13:7004\n        # - 10.1.231.13:7005\n        # - 10.1.231.13:7006\n', 'bf23c4febce8aa493b7b4995b9729976', '2010-05-05 00:00:00', '2021-08-30 05:20:52', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 23, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n        - /sys/area/tree\n        - /biz/conf/query/list\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'', '9372e4aea8c3a18ea2220a2e5dc1ff23', '2010-05-05 00:00:00', '2021-08-30 05:21:22', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (14, 24, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '84b2de3d92217788bc67846e8f44b287', '2010-05-05 00:00:00', '2021-08-30 05:23:53', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (16, 25, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '29a9c6bcd421b357a1385cff78535db4', '2010-05-05 00:00:00', '2021-08-30 05:24:08', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (16, 26, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '1e3fd273a810a21c32d6fcbf56b55c1b', '2010-05-05 00:00:00', '2021-08-30 05:24:25', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (14, 27, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: rESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', 'f2a62e912f8ff2adfe743afca24cd24c', '2010-05-05 00:00:00', '2021-08-30 05:52:17', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 28, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n        - /sys/area/tree\n        - /biz/conf/query/list\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'', '2dc000e3f5b87dc26f1a2c8cb62d7996', '2010-05-05 00:00:00', '2021-08-30 05:54:27', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 29, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'bcd1611660baf79dc28366dcf6f64454', '2010-05-05 00:00:00', '2021-08-30 05:54:46', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 30, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\n## spring security 配置，配置本服务不需要权限即可访问的接口\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', '004de14a12b61e225e2ee71e9e57267a', '2010-05-05 00:00:00', '2021-08-30 05:56:17', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 31, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /sys/area/tree\n        - /biz/conf/query/list\n\n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'', '27eb1e4e9354632c9ebe57a278702953', '2010-05-05 00:00:00', '2021-08-30 05:56:51', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 32, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.ibank.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'bcd1611660baf79dc28366dcf6f64454', '2010-05-05 00:00:00', '2021-08-30 06:20:32', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (3, 33, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /druid/**\n        - /actuator/**\n        - /v3/api-docs\n   \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n# 文件系统\noss:\n  endpoint: http://127.0.0.1:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: grgbanking\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'', '1b74c0eb6f5a5c0f8c2b86d72fd36907', '2010-05-05 00:00:00', '2021-08-30 06:22:03', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 34, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥，密文生成工具类在com.grgbanking.counter.common.core.encrypt.JasyptUtil\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', 'aa853ab5862901f7f57b1ababa03eaef', '2010-05-05 00:00:00', '2021-08-30 06:23:56', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (1, 35, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:8081/auth/oauth/token\n\n', '946e10018906807c78255a4b952636c5', '2010-05-05 00:00:00', '2021-08-30 06:24:53', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (11, 36, 'redis.yml', 'DEFAULT_GROUP', '', '', 'spring: \n  redis:\n    password: ENC(3YIjfd4q53aJPlOow+9mgA==)\n    lettuce:\n      pool:\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\n        max-wait: -1     # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-idle: 10      # 连接池中的最大空闲连接\n        min-idle: 5       # 连接池中的最小空闲连接\n\n    # 单机模式\n    # host: localhost\n    # port: 6379\n\n    #哨兵模式\n    #    sentinel:\n    #      master: mymaster # redis哨兵名称，在sentinel配置文件配置\n    #      nodes:\n    #        - 10.1.231.60:7001\n    #        - 10.1.231.60:7002\n    #        - 10.1.231.60:7003\n\n    # cluster模式\n    cluster:\n      max-redirects: 100\n      nodes:\n        - 10.1.231.13:7001\n        - 10.1.231.13:7002\n        - 10.1.231.13:7003\n        - 10.1.231.13:7004\n        - 10.1.231.13:7005\n        - 10.1.231.13:7006\n', '00e218b1731ae8f31ff6a3ecb60103ae', '2010-05-05 00:00:00', '2021-08-30 06:26:30', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (14, 37, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '9a981f86b368aa9ace41108a6a43d494', '2010-05-05 00:00:00', '2021-08-30 06:26:53', 'nacos', '0:0:0:0:0:0:0:1', 'U');
INSERT INTO `his_config_info` VALUES (16, 38, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ESoxa3kXR3IsFgKZ\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'', '1e3fd273a810a21c32d6fcbf56b55c1b', '2010-05-05 00:00:00', '2021-08-30 06:27:06', 'nacos', '0:0:0:0:0:0:0:1', 'U');
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(512) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `uk_username_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(128) DEFAULT '',
  `quota` int(11) DEFAULT '0',
  `usage` int(11) DEFAULT '0',
  `max_size` int(11) DEFAULT '0',
  `max_aggr_count` int(11) DEFAULT '0',
  `max_aggr_size` int(11) DEFAULT '0',
  `max_history_count` int(11) DEFAULT '0',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kp` varchar(128) NOT NULL,
  `tenant_id` varchar(128) DEFAULT '',
  `tenant_name` varchar(128) DEFAULT '',
  `tenant_desc` varchar(256) DEFAULT NULL,
  `create_source` varchar(32) DEFAULT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`) USING BTREE,
  KEY `tenant_info_tenant_id_idx` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
