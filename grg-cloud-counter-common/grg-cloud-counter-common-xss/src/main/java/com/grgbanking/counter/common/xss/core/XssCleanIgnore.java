package com.grgbanking.counter.common.xss.core;

import java.lang.annotation.*;

/**
 * 忽略 xss
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XssCleanIgnore {

}
