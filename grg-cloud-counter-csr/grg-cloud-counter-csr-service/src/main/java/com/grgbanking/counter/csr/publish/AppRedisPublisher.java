package com.grgbanking.counter.csr.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRedisPublisher {

    private final String channel="app";
    @Autowired
    RedisTemplate redisTemplate;

    public void publish(Object msg){
        redisTemplate.convertAndSend(channel,msg);
    }

}
