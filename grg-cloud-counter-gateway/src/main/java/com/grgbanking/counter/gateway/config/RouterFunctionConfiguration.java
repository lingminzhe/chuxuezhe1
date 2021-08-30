//package com.grgbanking.counter.gateway.config;
//
//import com.grgbanking.counter.gateway.handler.ImageCodeHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//
///**
// * @author grgbanking
// * @date 路由配置信息
// */
//@Slf4j
//@Configuration
//public class RouterFunctionConfiguration {
//
//    @Autowired
//    private ImageCodeHandler imageCodeHandler;
////    @Autowired
////    private SwaggerResourceHandler swaggerResourceHandler;
////    @Autowired
////    private SwaggerUiHandler swaggerUiHandler;
////    @Autowired
////    private SwaggerSecurityHandler swaggerSecurityHandler;
//
////    @Bean
////    public RouterFunction routerFunction() {
////        return RouterFunctions
////                .route(RequestPredicates.GET("/code")
////                        .and(RequestPredicates.accept(MediaType.APPLICATION_OCTET_STREAM)), imageCodeHandler)
////                .andRoute(RequestPredicates.GET("/swagger-resources")
////                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
////                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
////                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
////                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
////                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
////
////    }
//
//    @Bean
//    public RouterFunction routerFunction() {
//        return RouterFunctions
//                .route(RequestPredicates.GET("/oauth/code")
//                        .and(RequestPredicates.accept(MediaType.APPLICATION_OCTET_STREAM)), imageCodeHandler);
//
//    }
//
//}
