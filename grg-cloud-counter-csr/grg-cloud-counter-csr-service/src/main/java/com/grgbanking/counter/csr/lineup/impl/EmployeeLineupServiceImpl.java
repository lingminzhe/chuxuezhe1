package com.grgbanking.counter.csr.lineup.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

@Slf4j
@Service
public class EmployeeLineupServiceImpl extends LineupAbstractService {

    @Autowired
    private RedisBroadcastService broadcastService;

    @Autowired
    private LineupService lineupService;

    @Override
    public void login(String clientId) {
        redisTemplate.opsForHash().putIfAbsent(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId, null);
    }

    @Override
    public void finish(String clientId) {
        redisTemplate.opsForHash().put(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId, null);

        /**
         * 坐席结束服务，继续检查是否还有正在排队的用户
         */
        lineupService.check();
    }

    @Override
    public boolean connectionChanged(String clientId, SocketConnectStatusEnum statusEnum) {
        String customerId = (String) redisTemplate.opsForHash().get(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId);
        /**
         * 没有正在服务用户，不需要发送警告广播
         */
        if (!StringUtils.hasText(customerId)) {
            return false;
        }
        SocketParam param = SocketParam.success(SocketParamHead.success(SocketApiNoConstants.CONNECTION_STATUS_CHANGE, CommonConstants.SUCCESS, statusEnum == SocketConnectStatusEnum.UP ? "对方已上线" : "对方已掉线"), customerId);
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, param);
        return true;
    }

    /**
     * 接受用户的视频呼叫
     *
     * @param clientId
     * @return
     */
    public String accept(String clientId) {
        Set<String> set = redisTemplate.opsForZSet().range(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, 0, 0);
        if (CollectionUtils.isEmpty(set)) {
            log.info("暂无排队的用户");
            return null;
        }
        String customerId = set.stream().findFirst().get();
        redisTemplate.opsForZSet().remove(LineupConstants.CUSTOMER_VIDEO_QUEUE_KEY, customerId);
        /**把该坐席与取出的用户进行绑定，代表该坐席接受了该用户的视频呼叫*/
        redisTemplate.opsForHash().put(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId, customerId);
        return customerId;
    }

    /**
     * 登出
     *
     * @param clientId
     */
    public void logout(String clientId) {
        redisTemplate.opsForHash().delete(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId);
    }
}
