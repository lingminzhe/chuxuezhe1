/**
 * Copyright (c)2021 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.sentinel.handle;

import com.grgbanking.counter.common.core.exception.*;
import com.grgbanking.counter.common.core.util.Resp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author MARK xx@grgbanking.com
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerResolver {

//    @Getter
//    @AllArgsConstructor
//    public enum RespI18nConstants {
//        AccessDenied("exception.AccessDenied", "全局访问拒绝"),
//        InvalidToken("exception.InvalidToken", "全局无效令牌"),
//        ;
//        private String code;
//        private String value;
//    }

    @ExceptionHandler(CheckedException.class)
    public Resp handleCheckedException(CheckedException e) {
        log.error("统一异常处理拦截到异常CheckedException", e);
        return Resp.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(ValidateCodeException.class)
    public Resp handleValidateCodeException(ValidateCodeException e) {
        log.error("统一异常处理拦截到异常ValidateCodeException");
        return Resp.failed(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Resp exception(Exception e) {
        log.error("统一异常处理拦截到异常Exception:{}", e.getMessage(), e);
        return Resp.failed();
    }

}
