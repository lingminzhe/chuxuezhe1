//package com.grgbanking.counter.iam.listener;
//
//import com.grgbanking.counter.common.core.exception.CheckedException;
//import com.grgbanking.counter.iam.constans.AppCoreConstants;
//import com.grgbanking.counter.iam.constans.RespI18nConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @program: iBank-pro
// * @description: 登陆失败监听
// * @author: chainos
// * @create: 2020-11-26 19:49
// */
//@Slf4j
//@Component
//public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
//
//        String username = (String) authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal();
//        // 排除非用户名的客户端检验
//        // TODO mcy
////        if (!ResouceServerConfig.RESOURCE_ID.equals(username) && !ResouceServerConfig.TOKEN_FLAG.equals(username)) {
////
////            Object retryCountObj = redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username));
////            Integer retryCount = retryCountObj == null ? 1 : 1 + Integer.valueOf(String.valueOf(retryCountObj));
////            // 失败次数在范围内，则累加
////            if (retryCount <= AppCoreConstants.LOCK_NUM) {
////                // 初始化
////                if (retryCountObj == null) {
////                    redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username), retryCount, AppCoreConstants.LOCK_TIME, TimeUnit.MINUTES);
////                } else {
////                    redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username), retryCount, 0);
////                }
////            }
////
////            // 如果达到累计失败次数
////            if (retryCount >= AppCoreConstants.LOCK_NUM) {
////                throw new CheckedException(String.format(RespI18nConstants.LOGIN1001.getCode(), AppCoreConstants.LOCK_NUM, AppCoreConstants.LOCK_TIME));
////            }
////        }
//    }
//}
