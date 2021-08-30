//package com.grgbanking.counter.gateway.handler;
//
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//import com.grgbanking.counter.common.core.constant.CommonConstants;
//import com.grgbanking.counter.common.core.constant.SecurityConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.util.FastByteArrayOutputStream;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.HandlerFunction;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @program: iBank-pro
// * @description: 验证码生成类
// * @author: chainos
// * @create: 2020-12-11 16:52
// */
//@Slf4j
//@Component
//public class ImageCodeHandler implements HandlerFunction<ServerResponse> {
//    private final static String JPEG = "jpeg";
//
//    @Autowired
//    private DefaultKaptcha ibpKaptchaConfiguration;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
//        // 生成验证码
//        String text = ibpKaptchaConfiguration.createText();
//        BufferedImage image = ibpKaptchaConfiguration.createImage(text);
//
//        // 保存验证码信息
//        String random = serverRequest.queryParam("random").get();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.opsForValue().set(CommonConstants.PLATFORM_LOGIN_CAPTCHA + random, text
//                , SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
//
//        // 转换流信息写出
//        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
//        try {
//            ImageIO.write(image, JPEG, os);
//        } catch (IOException e) {
//            log.error("ImageIO write err", e);
//            return Mono.error(e);
//        }
//
//        return ServerResponse
//                .status(HttpStatus.OK)
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
//    }
//}