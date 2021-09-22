package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.csr.redis.AppRedisPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@EnableScheduling
public class VideoCmdSocketHandler implements SocketHandler {


    @Autowired
    AppRedisPublisher redisPublisher;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void execute(SocketParam param, String clientId) {
        try {
            redisPublisher.publish(param.getHead().getApiNo(),param);
        } catch (Exception e){

        }
    }


}
