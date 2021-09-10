package com.grgbanking.counter.app.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CounterRedisPublisher {

    private final String channel="csr";
    @Autowired
    RedisTemplate redisTemplate;

    public void publish(Object msg){
        redisTemplate.convertAndSend(channel,msg);
    }

}
