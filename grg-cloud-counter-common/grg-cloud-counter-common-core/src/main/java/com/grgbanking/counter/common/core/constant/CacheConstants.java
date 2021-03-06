package com.grgbanking.counter.common.core.constant;

/**
 *
 * 缓存的key 常量
 *
 * @author grgbanking
 */
public interface CacheConstants {

	/**
	 * 全局缓存
	 * <p/>
	 * {@code @Cacheable(value = CacheConstants.GLOBALLY+CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")}
	 */
	String GLOBALLY = "gl:";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

	/**
	 * 菜单信息缓存
	 */
	String MENU_DETAILS = "menu_details";

	/**
	 * 用户信息缓存
	 */
	String USER_DETAILS = "user_details";

	/**
	 * 角色信息缓存
	 */
	String ROLE_DETAILS = "role_details";

	/**
	 * 字典信息缓存
	 */
	String DICT_DETAILS = "dict_details";

	/**
	 * oauth 客户端信息
	 */
	String CLIENT_DETAILS_KEY = "grg_oauth:client:details";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = GLOBALLY + "event_key";

	/**
	 * 公众号 reload
	 */
	String MP_REDIS_RELOAD_TOPIC = "mp_redis_reload_topic";

	/**
	 * 支付 reload 事件
	 */
	String PAY_REDIS_RELOAD_TOPIC = "pay_redis_reload_topic";

	/**
	 * 参数缓存
	 */
	String PARAMS_DETAILS = "params_details";

	/**
	 * 租户缓存 (不区分租户)
	 */
	String TENANT_DETAILS = GLOBALLY + "tenant_details";

	/**
	 * 客户端配置缓存
	 */
	String CLIENT_FLAG = "client_config_flag";

	/**
	 * 数据字典默认路径
	 */
	String DICT_DEFAULT_KEY = "GRG_COUNTER_DICT:";



}
