//package com.grgbanking.counter.gateway.filter;
//
//import cn.hutool.core.util.StrUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.grgbanking.counter.common.core.constant.CommonConstants;
//import com.grgbanking.counter.common.core.constant.SecurityConstants;
//import com.grgbanking.counter.common.core.exception.ValidateCodeException;
//import com.grgbanking.counter.common.core.util.Resp;
//import com.grgbanking.counter.gateway.config.FilterIgnorePropertiesConfig;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
///**
// * @program: iBank-pro
// * @description: 验证码过滤器
// * @author: chainos
// * @create: 2020-12-14 14:21
// */
//@Slf4j
//@Component
//public class ValidateCodeGatewayFilterFactory extends AbstractGatewayFilterFactory {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
//
//    @Override
//    public GatewayFilter apply(Object config) {
//        return (exchange, chain) -> {
//            // 不是登录请求，直接向下执行
//            ServerHttpRequest request = exchange.getRequest();
//            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
//                return chain.filter(exchange);
//            }
//
//            // 刷新token，直接向下执行
//            String grantType = request.getQueryParams().getFirst("grant_type");
//            if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
//                return chain.filter(exchange);
//            }
//
//            try {
//                // 客户端设置不校验， 直接向下执行
//                String clientInfos = request.getQueryParams().getFirst("client_id");
//                if (filterIgnorePropertiesConfig.getClients().contains(clientInfos)) {
//                    return chain.filter(exchange);
//                }
//
//                //校验验证码
//                checkCode(request);
//            } catch (Exception e) {
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.OK);
//                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                try {
//                    return response.writeWith(
//                        Mono.just(
//                            response.bufferFactory().wrap(
//                                objectMapper.writeValueAsBytes(
//                                    Resp.builder()
//                                        .msg(e.getMessage())
//                                        .code(CommonConstants.FAIL)
//                                        .build()
//                                )
//                            )
//                        )
//                    );
//                } catch (JsonProcessingException e1) {
//                    log.error("对象输出异常", e1);
//                }
//            }
//
//            return chain.filter(exchange);
//        };
//    }
//
//    /**
//     * 检查code
//     *
//     * @param request
//     */
//    @SneakyThrows
//    private void checkCode(ServerHttpRequest request) {
//        String code = request.getQueryParams().getFirst("code");
//
//        if (StrUtil.isBlank(code)) {
//            throw new ValidateCodeException("验证码为空");
//        }
//
//        String random = request.getQueryParams().getFirst("random");
//
//        String key = CommonConstants.PLATFORM_LOGIN_CAPTCHA + random;
//
//        if (!redisTemplate.hasKey(key)) {
//            throw new ValidateCodeException("验证码标识已失效");
//        }
//
//        Object codeObj = redisTemplate.opsForValue().get(key);
//
//        if (codeObj == null) {
//            redisTemplate.delete(key);
//            throw new ValidateCodeException("验证码值已失效");
//        }
//
//        String saveCode = codeObj.toString();
//        if (StrUtil.isBlank(saveCode)) {
//            redisTemplate.delete(key);
//            throw new ValidateCodeException("验证码已失效");
//        }
//
//        if (!StrUtil.equalsIgnoreCase(saveCode, code)) {
//            throw new ValidateCodeException("验证码不正确");
//        }
//
//        redisTemplate.delete(key);
//    }
//}
