package com.grgbanking.counter.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author grgbanking
 *
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 * @return {String}
	 */
	String value();

}
