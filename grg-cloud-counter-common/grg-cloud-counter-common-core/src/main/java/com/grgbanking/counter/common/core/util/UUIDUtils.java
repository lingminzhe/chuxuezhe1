package com.grgbanking.counter.common.core.util;

import java.util.UUID;

/**
 * UUID生成
 *
 * @date 2021年10月25日 下午5:04:17
 *
 * @version 1.0 2021年10月25日 Tom create
 * 
 * @copyright Copyright © 2021 广电运通 All rights reserved.
 */
public class UUIDUtils {

	private static BankIdentifierGenerator defaultIdentifierGenerator = new BankIdentifierGenerator();
	/** 获取UUID */
	public static String id() {
		Long longId = defaultIdentifierGenerator.nextId(new Object());
		return String.valueOf(longId.longValue());
	}

	/** 获取UUID */
	public static Long idNumber() {
		Long longId = defaultIdentifierGenerator.nextId(new Object());
		return longId.longValue();
	}

	/** 获取UUID */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

}
