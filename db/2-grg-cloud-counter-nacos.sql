/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : grg-cloud-counter-nacos

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/09/2021 20:36:07
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'application.yml', 'DEFAULT_GROUP', '', '', '# 本配置为所有项目所公用，配置对所有项目均生效\n#配置文件密文加密的密钥\njasypt:\n  encryptor:\n    # 不能修改此值\n    password: d5fr9gJZJSd7xqD4CHnLKv3qbz5lHQyb\n    algorithm: PBEWithMD5AndDES\n    iv-generator-classname: org.jasypt.iv.NoIvGenerator	\n\ndubbo:\n  application:\n    id: ${spring.application.name}\n  registry:\n    # 指定 Dubbo 服务注册中心的地址\n    address: spring-cloud://${spring.cloud.nacos.discovery.server-addr}\n  scan:\n    base-packages: com.grgbanking.counter\n  protocol:\n    # 协议名称, 指明是 dubbo\n    name: dubbo\n    # 协议端口，-1 表示自增端口，从 20880 开始\n    port: -1\n  consumer:\n    # 启动时不检查引用服务是否可用\n    check: false\n\n# mybatis-plus 配置\nmybatis-plus:\n  tenant-enable: ture\n  mapper-locations: classpath:/mappers/**.xml\n  global-config:\n    banner: false\n    db-config:\n      db-type: mysql\n      id-type: auto\n      select-strategy: not_empty\n      insert-strategy: not_empty\n      update-strategy: not_empty\n  type-handlers-package: com.grgbanking.counter.common.data.handler\n  configuration:\n    jdbc-type-for-null: null\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: ALWAYS\n\n# swagger统一配置\nswagger:\n  enabled: true\n  title: ${spring.application.name}\n  license: Powered By Grgbanking\n  licenseUrl: https://www.grgbanking.com/\n  terms-of-service-url: https://www.grgbanking.com/\n  contact:\n    email: admin@grgbanking.com\n    url: https://www.grgbanking.com/about.html\n  authorization:\n    name: GrgCloudCounter OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://gateway:9999/iam/oauth/token\n', 'ea57cd151d7bba97a211e3669445f543', '2021-08-26 07:00:04', '2021-08-30 08:10:26', '', '127.0.0.1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'grg-cloud-counter-gateway.yml', 'DEFAULT_GROUP', '', '', 'spring:\n  cloud:\n    gateway:\n      globalcors:\n        corsConfigurations:\n          \'[/**]\':\n            allowedOriginPatterns: \'*\'\n            allowed-methods: \'*\'\n            allowed-headers: \'*\'\n            allow-credentials: true\n            exposedHeaders: \'Content-Disposition,Content-Type,Cache-Control\'\n      # Filters definitions that are applied to every route\n      default-filters:\n        - StripPrefix=1\n      # List of Routes.\n      # - id：路由id     uri：路由转发地址或者服务     - Path：请求uri匹配规则\n      routes:\n        # =============认证服务========================\n        - id: grg-cloud-counter-iam\n          uri: lb://grg-cloud-counter-iam\n          predicates:\n            - Path=/iam/**\n          filters:\n            - name: ValidateCodeGatewayFilter\n        # =============坐席服务========================\n        - id: grg-cloud-counter-csr\n          uri: lb://grg-cloud-counter-csr\n          predicates:\n            - Path=/csr/**\n        # =============视频服务========================\n        - id: grg-cloud-counter-video\n          uri: lb://grg-cloud-counter-video\n          predicates:\n            - Path=/video/**\n        # =============云柜台APP后台服务========================\n        - id: grg-cloud-counter-app\n          uri: lb://grg-cloud-counter-app\n          predicates:\n            - Path=/app/**\n        # =============云柜台文件后台服务========================\n        - id: grg-cloud-counter-oss\n          uri: lb://grg-cloud-counter-oss\n          predicates:\n            - Path=/oss/**\n        # =============监控服务========================\n        - id: grg-cloud-counter-monitor\n          uri: lb://grg-cloud-counter-monitor\n          predicates:\n            - Path=/monitor/**', '3ed98d6338edb8469b7a5147643b6a06', '2021-08-26 07:07:56', '2021-09-03 14:07:06', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'grg-cloud-counter-iam.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n\n\n   \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    # url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: root123@\n    # password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\n  freemarker:\n    allow-request-override: false\n    allow-session-override: false\n    cache: true\n    charset: UTF-8\n    check-template-location: true\n    content-type: text/html\n    enabled: true\n    expose-request-attributes: false\n    expose-session-attributes: false\n    expose-spring-macro-helpers: true\n    prefer-file-system-access: true\n    suffix: .ftl\n    template-loader-path: classpath:/templates/\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'\n\n', '96b6505074c8518a851ba7649746b56d', '2021-08-26 07:32:56', '2021-09-03 13:41:34', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (11, 'redis.yml', 'DEFAULT_GROUP', '', '', 'spring: \n  redis:\n    # password: ENC(3YIjfd4q53aJPlOow+9mgA==)\n    lettuce:\n      pool:\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\n        max-wait: -1     # 连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-idle: 10      # 连接池中的最大空闲连接\n        min-idle: 5       # 连接池中的最小空闲连接\n\n    # 单机模式\n    host: localhost\n    port: 6379\n\n    #哨兵模式\n    #    sentinel:\n    #      master: mymaster # redis哨兵名称，在sentinel配置文件配置\n    #      nodes:\n    #        - 10.1.231.60:7001\n    #        - 10.1.231.60:7002\n    #        - 10.1.231.60:7003\n\n    # cluster模式\n    # cluster:\n    #   max-redirects: 100\n    #   nodes:\n    #     - 10.1.231.13:7001\n    #     - 10.1.231.13:7002\n    #     - 10.1.231.13:7003\n    #     - 10.1.231.13:7004\n    #     - 10.1.231.13:7005\n    #     - 10.1.231.13:7006\n', 'c96d7f7967f84d349b81d0931902143d', '2021-08-26 08:40:46', '2021-09-03 13:40:37', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (14, 'grg-cloud-counter-csr.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n        - /tencent/**\n        - /grg_account/**\n        \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://127.0.0.1/grg-cloud-counter-csr?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: grg@123\n    driver-class-name: com.mysql.cj.jdbc.Driver\n#     password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam\'\n\n# Socket相关属性配置\nsocket:\n  enabled: true\n  host: localhost\n  port: 9843\n  # socket连接数大小（如只监听一个端口boss线程组为1即可）\n  bossCount: 1\n  workCount: 100\n  allowCustomRequests: true\n  # 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间\n  upgradeTimeout: 30000\n  # Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件\n  pingTimeout: 60000\n  # Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔\n  pingInterval: 25000\n\n#腾讯人脸核身api\ntencent:\n  face:\n    version: 1.0.0\n    appId: TIDAoMBH\n    secret: R0O2HAq90Wp1JnnU8YJ4YlfAhEGvBAvM5gVpi85tU1QnjosoKvvY9eLZEOB1yLGf\n    access_token_url: https://miniprogram-kyc.tencentcloudapi.com/api/oauth2/access_token\n    ticket_url: https://miniprogram-kyc.tencentcloudapi.com/api/oauth2/api_ticket\n    geth5faceid_url: https://miniprogram-kyc.tencentcloudapi.com/api/server/h5/geth5faceid\n    certification_url: https://{optimalDomain}/api/pc/login', 'cd587d3dcfb03073c716b6405764178d', '2021-08-27 08:31:03', '2021-09-01 08:20:57', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (16, 'grg-cloud-counter-video.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n        - /query\n        \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-video?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-iam,grg-cloud-counter-csr\'', 'b63047fe65fe7c4d5e0c8dc655ec0ec9', '2021-08-27 08:32:36', '2021-08-31 00:37:25', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (17, 'grg-cloud-counter-app.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n        - /grg_account/**\n        - //tencent/**\n   \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-app?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'grg-cloud-counter-oss\'\n\n# Socket相关属性配置\nsocket:\n  enabled: true\n  host: localhost\n  port: 9843\n  # socket连接数大小（如只监听一个端口boss线程组为1即可）\n  bossCount: 1\n  workCount: 100\n  allowCustomRequests: true\n  # 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间\n  upgradeTimeout: 30000\n  # Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件\n  pingTimeout: 60000\n  # Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔\n  pingInterval: 25000\n\ntencent:\n  # 腾讯e证通接入 用于小程序、h5身份核验\n  e-certification:\n    secretId: AKIDxiDSQ8ZjdHfndFax6yZuylIabLq41h6E\n    secretKey: tWsynLcdOt74kAKEsGNDn68tOcZFE1Xp\n    merchantId: 0NSJ2108191435178974\n  # 腾讯视频密钥获取  \n  video:\n    sdkAppId: 1400563240\n    secretKey: 12c61a9fade38ebf4a786f258dad3b2654a6ab92234635b13d396a996025e797', '1938bd530b64b54ac50fdfb1a81f9cb0', '2021-08-31 08:39:57', '2021-09-02 08:37:49', '', '127.0.0.1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (35, 'grg-cloud-counter-oss.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n   \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20:3306/grg-cloud-counter-iam?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'\n\n\n\n# 文件系统\noss:\n  endpoint: http://10.252.37.58:9000\n  access-key: minioadmin\n  secret-key: minioadmin\n  bucket-name: counter', 'a82f7456c0c113ac39bf96ac20fbe493', '2021-09-01 08:48:37', '2021-09-02 00:57:30', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (52, 'grg-cloud-counter-bank.yml', 'DEFAULT_GROUP', '', '', '## spring security 配置，配置本服务不需要权限即可访问的接口列表\nsecurity:\n  oauth2:\n    client:\n      ignore-urls:\n        - /error\n        - /actuator/**\n        - /webjars/**\n        - /swagger**/** \n        - /doc.html\n        - /v3/api-docs\n        \n# 数据源\nspring:\n  datasource:\n    url: jdbc:mysql://10.252.21.20/grg-cloud-counter-bank?useUnicode=true&characterEncoding=UTF-8&useSSL=false\n    username: root\n    password: ENC(icJbl9/sr7qFO53QMkqOwTEf42CBi9NfXhEQEGYxNAg=)\n    driver-class-name: com.mysql.cj.jdbc.Driver\n\n\ndubbo:\n  cloud:\n    # 设置订阅的应用列表，多个服务以英文逗号分隔\n    subscribed-services: \'\'\n', '3edc3973dcf1ab7cc876012e224d62eb', '2021-09-03 05:18:34', '2021-09-03 05:20:00', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', 'yaml', '');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
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
