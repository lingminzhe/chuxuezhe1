package com.grgbanking.counter.common.socket.broadcast.service.impl;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisBroadcastServiceImpl implements RedisBroadcastService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean sendBroadcast(String channel, SocketParam param) {
        redisTemplate.convertAndSend(channel, param);
        return true;
    }

}
