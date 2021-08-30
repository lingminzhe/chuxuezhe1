//package com.grgbanking.counter.gateway.filter;
//
//import com.grgbanking.counter.common.core.constant.SecurityConstants;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;
//
///**
// * @program: iBank-pro
// * @description: 全局拦截器, 作用所有的微服务
// * <p>
// * 1. 对请求头中 from 参数进行清洗,避免外部访问到内部标识的不需要鉴权接口
// * 2. 重写 StripPrefix = 1,支持全局
// * 3. 支持 swagger 添加 X-Forwarded-Prefix header
// * <p>
// * @author: chainos
// * @create: 2020-11-24 09:12
// */
//@Component
//public class RequestGlobalFilter implements GlobalFilter, Ordered {
////    private static final String HEADER_NAME = "X-Forwarded-Prefix";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 1. 清洗请求头中 from 参数
//        // 1. 清洗请求头中from 参数
//        ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> httpHeaders.remove(SecurityConstants.FROM)).build();
//
//        // 2. 重写 StripPrefix = 1
//        addOriginalRequestUrl(exchange, request.getURI());
//        String rawPath = request.getURI().getRawPath();
//        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/")).skip(1L).collect(Collectors.joining("/"));
//        ServerHttpRequest newRequest = request.mutate().path(newPath).build();
//        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());
//
//        return chain.filter(exchange.mutate().request(newRequest.mutate().build()).build());
//    }
//
//    @Override
//    public int getOrder() {
//        return -1000;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
