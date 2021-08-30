//package com.grgbanking.counter.common.security.config;
//
//import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
////import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class OAuth2ResourceServerConfig extends GlobalMethodSecurityConfiguration {
//
//    /**
//     * 默认权限前缀
//     */
//    @Value("${spring.application.name}")
//    private String appName;
//
//    /**
//     * 设置权限标识的默认前缀（权限校验时，用户需有权限前缀+接口权限标识权限才能访问）
//     * 数据库中对应的权限表的值为服务名+权限标识
//     * @return oAuth2MethodSecurityExpressionHandler
//     */
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        SecurityContextUtil.setDefaultPrefix(appName + ":");
//
//        OAuth2MethodSecurityExpressionHandler oAuth2MethodSecurityExpressionHandler = new OAuth2MethodSecurityExpressionHandler();
//        oAuth2MethodSecurityExpressionHandler.setDefaultRolePrefix(appName + ":");
//        return oAuth2MethodSecurityExpressionHandler;
//    }
//}
//
//
