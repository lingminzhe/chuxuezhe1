//package com.grgbanking.counter.gateway.config;
//
//import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
//import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
//import com.grgbanking.counter.common.core.util.Resp;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.PostConstruct;
//
///**
// * 在网关拦截到sentinel限流后进行自定义返回值
// * @Author:mcyang
// * @Date:2020/11/18 10:06 上午
// */
//@Slf4j
//@Configuration
//public class GatewaySentinelBlockHandler {
//
//    @PostConstruct
//    private void initBlockHandler(){
//        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
//            @Override
//            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
//                log.error("网关触发限流",throwable);
//                return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(Resp.error(HttpStatus.TOO_MANY_REQUESTS.value(),"网关-触发系统保护")));
//            }
//        };
//        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
//    }
//}
