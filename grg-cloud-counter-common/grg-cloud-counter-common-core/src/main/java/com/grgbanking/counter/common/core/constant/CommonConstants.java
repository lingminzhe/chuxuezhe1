package com.grgbanking.counter.common.core.constant;

/**
 *
 * 公共常量
 *
 * @author grgbanking
 */
public interface CommonConstants {

	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "1";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "iabnk-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "grg-cloud-counter";

	/**
	 * 公共参数
	 */
	String GRG_PUBLIC_PARAM_KEY = "GRG_PUBLIC_PARAM_KEY";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = 500;

	/**
	 * 成功标记-字符串
	 */
	String SUCCESS_MESSAGE = "success";

	/**
	 * 失败标记-字符串
	 */
	String FAILURE_MESSAGE = "未知异常，请联系管理员";

	/**
	 * 验证码前缀
	 */
	String PLATFORM_LOGIN_CAPTCHA = "grg-cloud-counter:platform:login:captcha:";

	/**区域状态 1 启用；0 禁用**/
	public static final String enableFlag = "enableFlag";
	public static final String enableFlag_1 = "1"; //启用
	public static final String enableFlag_0 = "0"; //禁用
}
