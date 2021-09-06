package com.grgbanking.counter.common.core.constant;

/**
 * 认证授权框架设计的常量
 * @author grgbanking
 */
public interface SecurityConstants {

	/**
	 * 刷新
	 */
	String REFRESH_TOKEN = "refresh_token";

	/**
	 * 验证码有效期
	 */
	int CODE_TIME = 180;

	/**
	 * 验证码长度
	 */
	String CODE_SIZE = "4";

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";

	/**
	 * 前缀
	 */
	String GRG_PREFIX = "grg_";

	/**
	 * token 相关前缀
	 */
	String TOKEN_PREFIX = "token:";

	/**
	 * oauth 相关前缀
	 */
	String OAUTH_PREFIX = "oauth:";

	/**
	 * 授权码模式code key 前缀
	 */
	String OAUTH_CODE_PREFIX = "oauth:code:";

	/**
	 * 项目的license
	 */
	String GRG_LICENSE = "made by grgbanking";


	/**
	 * OAUTH URL
	 */
	String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * 手机号登录URL
	 */
	String SMS_TOKEN_URL = "/mobile/token/sms";

	/**
	 * 社交登录URL
	 */
	String SOCIAL_TOKEN_URL = "/mobile/token/social";

	/**
	 * 自定义登录URL
	 */
	String MOBILE_TOKEN_URL = "/mobile/token/*";

	/**
	 * 微信获取OPENID
	 */
	String WX_AUTHORIZATION_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token"
			+ "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	/**
	 * 微信小程序OPENID
	 */
	String MINI_APP_AUTHORIZATION_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session"
			+ "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

	/**
	 * 资源服务器默认bean名称
	 */
	String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

	/**
	 * 客户端模式
	 */
	String CLIENT_CREDENTIALS = "client_credentials";

	/**
	 * 用户ID字段
	 */
	String DETAILS_USER_ID = "id";

	/**
	 * 用户名
	 */
	String DETAILS_USERNAME = "username";

	/**
	 * 用户基本信息
	 */
	String DETAILS_USER = "user_info";

	/**
	 * 用户名phone
	 */
	String DETAILS_PHONE = "phone";

	/**
	 * 头像
	 */
	String DETAILS_AVATAR = "avatar";

	/**
	 * 用户部门字段
	 */
	String DETAILS_DEPT_ID = "deptId";

	/**
	 * 租户ID 字段
	 */
	String DETAILS_TENANT_ID = "tenantId";

	/**
	 * 协议字段
	 */
	String DETAILS_LICENSE = "license";

	/**
	 * 激活字段 兼容外围系统接入
	 */
	String ACTIVE = "active";

	/**
	 * AES 加密
	 */
	String AES = "aes";

}
