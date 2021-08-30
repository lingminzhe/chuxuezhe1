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
	String PLATFORM_LOGIN_CAPTCHA = "grg-cloud-counter:platform:login:captcha:";

	/**
	 * 菜单信息缓存
	 */
	String MENU_DETAILS = "menu_details";

	/**
	 * 用户信息缓存
	 */
	String USER_DETAILS = "user_details";

	/**
	 * 字典信息缓存
	 */
	String DICT_DETAILS = "dict_details";

	/**
	 * oauth 客户端信息
	 */
	String CLIENT_DETAILS_KEY = "ibank_oauth:client:details";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = "event_key";

	/**
	 * 路由存放
	 */
	String ROUTE_KEY = "gateway_route_key";

	/**
	 * redis reload 事件
	 */
	String ROUTE_REDIS_RELOAD_TOPIC = "gateway_redis_route_reload_topic";

	/**
	 * 内存reload 时间
	 */
	String ROUTE_JVM_RELOAD_TOPIC = "gateway_jvm_route_reload_topic";

	/**
	 * 参数缓存
	 */
	String PARAMS_DETAILS = "params_details";

	/**
	 * 终端信息属性缓存
	 */
	String DEVICE_INFO_COLUMN = "device:info:column";

	/**
	 * 终端信息
	 */
	String DEVICE_INFO_LIST = "device:info:list";


}
