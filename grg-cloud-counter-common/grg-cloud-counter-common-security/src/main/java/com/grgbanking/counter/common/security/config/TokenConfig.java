//package com.grgbanking.counter.common.security.config;
//
//import com.grgbanking.counter.common.security.services.GrgOAuth2AuthenticationEntryPoint;
//import com.grgbanking.counter.common.security.services.GrgTokenServices;
//import com.grgbanking.counter.common.security.services.GrgTokenStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@Configuration
//public class TokenConfig {
//    /**
//     * redis工厂，默认使用lettue
//     */
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    // token有效期，设置30分钟
//    public static int accessTokenTime = 30 * 60;
//    // refresh_token有效期，设置30分钟
//    public static int refreshTokenTime = 30 * 60;
//
//    public static final String PLATFORM_LOGIN_TICKOUT = "grg-cloud-counter:platform:login:tickout:";
//    public static final String PLATFORM_LOGIN_EXPIRED = "grg-cloud-counter:platform:login:expired:";
//
//    @Bean
//    public TokenStore grgTokenStore() {
//        //使用redis存储token
//        GrgTokenStore redisTokenStore = new GrgTokenStore(redisTemplate.getConnectionFactory());
//        //设置redis token存储中的前缀
//        redisTokenStore.setPrefix("grg-cloud-counter:platform:login:oauth:");
//
//        return redisTokenStore;
//    }
//
//    @Bean
//    public DefaultTokenServices grgTokenService() {
//        TokenStore tokenStore = grgTokenStore();
//        GrgTokenServices tokenServices = new GrgTokenServices(tokenStore);
//        //配置token存储
//        tokenServices.setTokenStore(tokenStore);
//        //开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
//        tokenServices.setSupportRefreshToken(true);
//        //复用refresh_token指的是登陆后初次生成的refresh_token一直保持不变，直到过期；非重复指的是在每一次使用refresh_token刷新access_token的过程中，refresh_token也随之更新，即生成新的refresh_token
//        tokenServices.setReuseRefreshToken(true);
//        //token有效期，设置30分钟
//        tokenServices.setAccessTokenValiditySeconds(accessTokenTime);
//        //refresh_token有效期，设置30分钟
//        tokenServices.setRefreshTokenValiditySeconds(refreshTokenTime);
//
//        return tokenServices;
//    }
//
//    @Bean
//    public AuthenticationEntryPoint grgOAuth2AuthenticationEntryPoint() {
//        return new GrgOAuth2AuthenticationEntryPoint();
//    }
//}
