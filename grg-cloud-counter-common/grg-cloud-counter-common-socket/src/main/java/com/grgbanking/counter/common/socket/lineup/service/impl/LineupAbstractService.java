package com.grgbanking.counter.common.socket.lineup.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.grgbanking.counter.common.core.constant.CommonConstants;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 提醒坐席有用户进行视频呼叫
     * TODO 后续在此增加坐席选取的算法
     */
    @Override
    public void remind() {
        List<String> employeeIds = new ArrayList<>();
        Cursor<Map.Entry<String, String>> cursor = redisTemplate.opsForHash().scan(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, null);
        while (cursor.hasNext()) {
            Map.Entry<String, String> entry = cursor.next();
            if (StringUtils.hasText(entry.getValue())) {
                continue;
            }
            /**把空闲的坐席选出*/
            employeeIds.add(entry.getKey());
        }
        if (CollectionUtils.isEmpty(employeeIds)) {
            log.info("当前无空闲的坐席，用户将继续在排队......");
            return;
        }
        /**随机选取其中一个坐席*/
        String employeeId = employeeIds.get(RandomUtil.randomInt(0, employeeIds.size()));
        SocketParamHead head = SocketParamHead.success(SocketApiNoConstants.VIDEO_CMD, CommonConstants.SUCCESS, "有用户申请视频协助");
        /**body只放坐席客户端id*/
        SocketParam param = SocketParam.success(head, employeeId);
        /**给坐席服务发送提醒广播*/
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
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


}
