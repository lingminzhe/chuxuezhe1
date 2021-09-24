package com.grgbanking.counter.device.redis;

import com.grgbanking.counter.device.business.ServiceSessionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CsrRedisPublisher {

    private final String channel_prefix="csr:";
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;

    public void publish(String type,Object msg){
        String channel=channel_prefix+type;

        Map map=(Map) msg;
        redisTemplate.convertAndSend(channel,msg);
    }

}
