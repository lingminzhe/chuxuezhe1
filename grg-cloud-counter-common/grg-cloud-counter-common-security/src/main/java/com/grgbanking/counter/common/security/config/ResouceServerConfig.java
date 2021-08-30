//package com.grgbanking.counter.common.security.config;
//
//import lombok.Data;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @program: iBank-pro
// * @description: 加载对外暴露url并进行放授权处理
// * @author: chainos
// * @create: 2020-11-24 20:50
// */
//@Configuration
//@EnableResourceServer
//@Data
//@RefreshScope
//@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
//@ConfigurationProperties(prefix = "security.oauth2.client")
//public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
//
//    public static final String RESOURCE_ID = "grg-cloud-counter-client";
//    public static final String TOKEN_FLAG = "access-token";
//
//    private List<String> ignoreUrls = new ArrayList<>();
//
//    @Autowired
//    private DefaultTokenServices grgTokenService;
//
//    @Autowired
//    private AuthenticationEntryPoint grgOAuth2AuthenticationEntryPoint;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID)
//                .tokenServices(grgTokenService)
//                .authenticationEntryPoint(grgOAuth2AuthenticationEntryPoint)
//                .stateless(true);
//    }
//
//    /**
//     * 配置对外暴露的URL
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    @SneakyThrows
//    public void configure(HttpSecurity http) {
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        // 通过配置文件管理对外暴露的URL
//        ignoreUrls.forEach(url -> registry.antMatchers(url).permitAll());
//
//        registry.anyRequest().authenticated()
//                .and().csrf().disable();
//
//        //允许使用iframe 嵌套，避免swagger-ui 不被加载
//        http.headers().frameOptions().disable();
//    }
//}
