//package com.grgbanking.counter.iam.listener;
//
//import com.grgbanking.counter.iam.constans.AppCoreConstants;
//import com.grgbanking.counter.iam.constans.RespI18nConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @program: iBank-pro
// * @description: 登陆成功监听
// * @author: chainos
// * @create: 2020-11-26 19:26
// */
//@Slf4j
//@Component
//public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
//
//        UserDetails userDetails = (UserDetails) authenticationSuccessEvent.getAuthentication().getPrincipal();
//        String username = userDetails.getUsername();
//        // 排除非用户名的客户端检验
//        // TODO mcy
////        if (!ResouceServerConfig.RESOURCE_ID.equals(username) && !ResouceServerConfig.TOKEN_FLAG.equals(username)) {
////
////            Object retryCountObj = redisTemplate.opsForValue().get(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username));
////            Integer retryCount = retryCountObj == null ? 0 : Integer.valueOf(String.valueOf(retryCountObj));
////            // 如果达到累计失败次数，且未超过锁定冻结时间
////            if (retryCount != null && retryCount >= AppCoreConstants.LOCK_NUM) {
////                throw new CheckedException(String.format(RespI18nConstants.LOGIN1002.getCode(), AppCoreConstants.LOCK_NUM, AppCoreConstants.LOCK_TIME));
////            }
////            // 重置失败次数为0
////            redisTemplate.opsForValue().set(String.format(AppCoreConstants.USER_LOGIN_LOCKED, username), 0, AppCoreConstants.LOCK_TIME, TimeUnit.MINUTES);
////
////        }
//    }
//}
