/**
 * Copyright (c)2021 GRGBanking All rights reserved.
 *
 * https://www.grgbanking.com
 *
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.validator;


import com.grgbanking.counter.common.core.exception.CheckedException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author MARK xx@grgbanking.com
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws CheckedException  校验不通过，则报CheckedException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws CheckedException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append(" ");
            }
            throw new CheckedException(msg.toString());
        }
    }
}
