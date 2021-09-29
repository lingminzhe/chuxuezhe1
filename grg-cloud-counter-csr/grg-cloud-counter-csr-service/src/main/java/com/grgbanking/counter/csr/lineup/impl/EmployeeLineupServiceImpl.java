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

/**
 * 坐席端关于视频呼叫排队的实现
 */
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
        String customerId = findCustomer(clientId);
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
     * 登出
     *
     * @param clientId
     */
    public void logout(String clientId) {
        redisTemplate.opsForHash().delete(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId);
    }
}
