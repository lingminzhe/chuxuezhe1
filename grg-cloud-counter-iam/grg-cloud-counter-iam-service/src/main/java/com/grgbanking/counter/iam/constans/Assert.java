package com.grgbanking.counter.iam.constans;


import com.grgbanking.counter.common.core.exception.CheckedException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 *
 * @author MARK xx@grgbanking.com
 */
public abstract class Assert {

    // "" 和 null 都会抛出异常
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CheckedException(message);
        }
    }

    // "" 的情况下不抛出异常
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CheckedException(message);
        }
    }

    // 表达式未true的情况抛出异常
    public static void state(boolean flag, String message) {
        if (flag) {
            throw new CheckedException(message);
        }
    }
}
