package com.grgbanking.counter.app.lineup.service.impl;


import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.constant.LineupConstants;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.lineup.service.impl.LineupAbstractService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.constant.SocketConnectStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户端关于视频呼叫排队的实现
 */
@Slf4j
@Service
public class CustomerLineupServiceImpl extends LineupAbstractService {

    @Autowired
    private RedisBroadcastService broadcastService;

    @Autowired
    private LineupService lineupService;

    @Override
    public void login(String clientId) {
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            if (clientId.equals(item.getValue())) {
                log.error("该客户端正在视频通话，无法加入视频呼叫排队");
                return;
            }
        }
        redisTemplate.opsForZSet().addIfAbsent(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, clientId, 1);
    }

    @Override
    public void finish(String clientId) {
        /**
         * 把该客户端从排队队列移除
         */
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            if (clientId.equals(item.getValue())) {
                redisTemplate.opsForZSet().remove(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, clientId);
                return;
            }
        }
        /**
         * 把该客户端从正在视频呼叫服务中移除
         */
        Cursor<Map.Entry<String, String>> result = redisTemplate.opsForHash().scan(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, ScanOptions.NONE);
        while (result.hasNext()) {
            Map.Entry<String, String> entry = result.next();
            if (clientId.equals(entry.getValue())) {
                redisTemplate.opsForHash().put(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, entry.getKey(), null);
                return;
            }
        }

        /**
         * 用户结束服务，说明有坐席空闲，继续检查是否还有正在排队的用户
         */
        lineupService.check();
    }

    @Override
    public boolean connectionChanged(String clientId, SocketConnectStatusEnum statusEnum) {
        Cursor<Map.Entry<String, String>> result = redisTemplate.opsForHash().scan(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, ScanOptions.NONE);
        while (result.hasNext()) {
            Map.Entry<String, String> entry = result.next();
            /**掉线后，还在被坐席服务中，说明是异常掉线，发出告警广播通知坐席*/
            if (clientId.equals(entry.getValue())) {
                SocketParam param = SocketParam.success(SocketParamHead.success(SocketApiNoConstants.CONNECTION_STATUS_CHANGE, CommonConstants.SUCCESS, statusEnum == SocketConnectStatusEnum.UP ? "对方已上线" : "对方已掉线"), entry.getValue());
                broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
                return true;
            }
        }
        return false;
    }

    /**
     * 查询当前终端的排队名次
     *
     * @param clientId
     * @return
     */
    public Long rank(String clientId) {
        return redisTemplate.opsForZSet().rank(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, clientId);
    }


}
