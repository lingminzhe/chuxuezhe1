package com.grgbanking.counter.bank.exception;

import com.grgbanking.counter.common.core.util.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * bank服务统一的异常处理
 * @author: Ye Kaitao
 * @create: 2021-09-07
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.grgbanking.counter.bank.controller")
public class GrgExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Resp handleValidException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型{}",e.getMessage(),e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>(16);
        result.getFieldErrors().forEach(item->{
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field,message);
        });

        return Resp.failed(map,"提交的数据不合法").setCode(400);
    }

    //TODO 更大范围的异常处理 Throwable

}
