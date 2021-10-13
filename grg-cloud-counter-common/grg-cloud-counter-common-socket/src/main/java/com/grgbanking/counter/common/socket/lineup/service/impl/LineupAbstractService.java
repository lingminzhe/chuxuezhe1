package com.grgbanking.counter.common.socket.lineup.service.impl;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.constant.LineupConstants;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.Map;

@Slf4j
public abstract class LineupAbstractService implements LineupService {

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    private RedisBroadcastService broadcastService;

    @Override
    public Long rank() {
        return redisTemplate.opsForZSet().zCard(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY);
    }

    @Override
    public void check() {
        if (rank() <= 0) {
            return;
        }
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR,SocketParam.success(SocketParamHead.success(SocketApiNoConstants.CUSTOMER_VIDEO_LINEUP_CHECK)));
    }


    @Override
    public String findEmployee(String clientId) {
        Cursor<Map.Entry<String, String>> result = redisTemplate.opsForHash().scan(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, ScanOptions.NONE);
        while (result.hasNext()) {
            Map.Entry<String, String> entry = result.next();
            if (clientId.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String findCustomer(String clientId) {
        return (String)redisTemplate.opsForHash().get(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY,clientId);
    }

    @Override
    public String findSessionId(String clientId) {
        return (String) redisTemplate.opsForHash().get(LineupConstants.BUSI_SESSION_KEY, clientId);
    }

    @Override
    public Boolean finishSession(String clientId) {
        Boolean hasKey = false;
        if (clientId != null){
            hasKey = redisTemplate.opsForHash().hasKey(LineupConstants.BUSI_SESSION_KEY, clientId);
        }
        Long delete = 0L;
        if (hasKey){
            delete = redisTemplate.opsForHash().delete(LineupConstants.BUSI_SESSION_KEY, clientId);
        }
        return delete > 0;
    }
}
