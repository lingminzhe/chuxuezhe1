package com.grgbanking.counter.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 社交登录类型
 *
 * @author grgbanking
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

	/**
	 * 账号密码登录
	 */
	PWD("PWD", "账号密码登录"),

	/**
	 * 验证码登录
	 */
	SMS("SMS", "验证码登录"),

	/**
	 * 微信登录
	 */
	WECHAT("WX", "微信登录"),

	/**
	 * 微信小程序
	 */
	MINI_APP("MINI", "微信小程序");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
