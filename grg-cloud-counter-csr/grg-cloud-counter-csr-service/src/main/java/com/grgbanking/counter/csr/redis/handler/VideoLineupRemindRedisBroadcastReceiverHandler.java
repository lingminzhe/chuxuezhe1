package com.grgbanking.counter.csr.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.lock.components.RedisLockTemplate;
import com.grgbanking.counter.common.lock.enums.LockNameEnum;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 视频通话结束，通知检查是否还有用户正在视频呼叫排队
 */
@Slf4j
@Component
public class VideoLineupRemindRedisBroadcastReceiverHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private LineupService lineupService;

    @Autowired
    private RedisLockTemplate redisLockTemplate;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.CUSTOMER_VIDEO_LINEUP_CHECK;
    }

    @Override
    public void onMessage(String channel, SocketParam param) {
        RLock lock = redisLockTemplate.tryLock(LockNameEnum.CHECK_CUSTOMER_VIDEO_APPLY);
        lineupService.remind();
        redisLockTemplate.unlock(lock);
    }

}
