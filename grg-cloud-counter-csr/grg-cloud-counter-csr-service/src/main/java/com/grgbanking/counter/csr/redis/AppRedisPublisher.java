package com.grgbanking.counter.csr.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRedisPublisher {

    private final String channelPrefix="app:";
    @Autowired
    RedisTemplate redisTemplate;

    public void publish(String type,Object msg){
        String channel=channelPrefix+type;
        redisTemplate.convertAndSend(channel,msg);
    }

}
