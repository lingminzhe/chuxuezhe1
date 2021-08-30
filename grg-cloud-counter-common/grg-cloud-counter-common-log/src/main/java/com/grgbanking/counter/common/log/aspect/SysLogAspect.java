package com.grgbanking.counter.common.log.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.log.bo.SysLogBo;
import com.grgbanking.counter.common.log.event.SysLogEvent;
import com.grgbanking.counter.common.log.util.SysLogUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * 操作日志使用spring event异步入库
 *
 * @author grgbanking
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {

    private final ApplicationEventPublisher publisher;

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {

        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLogBo sysLogBo = new SysLogBo();
        sysLogBo.setCreatedBy(SysLogUtils.getUsername());
        sysLogBo.setOperation(sysLog.value());
        sysLogBo.setMethod(strClassName.concat(".").concat(strMethodName).concat("()"));
        sysLogBo.setParams(Arrays.toString(point.getArgs()));
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        sysLogBo.setTime(endTime - startTime);
        sysLogBo.setIp(ServletUtil.getClientIP(request));
        sysLogBo.setCreationDate(new Date());

        publisher.publishEvent(new SysLogEvent(sysLogBo));
        return obj;
    }

}
