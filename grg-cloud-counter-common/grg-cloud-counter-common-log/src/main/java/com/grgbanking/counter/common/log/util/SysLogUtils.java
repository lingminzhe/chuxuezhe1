package com.grgbanking.counter.common.log.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 系统日志工具类
 *
 * @author grgbanking
 */
@UtilityClass
public class SysLogUtils {


	/**
	 * 获取用户名称
	 * @return username
	 */
	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getName();
	}

}
