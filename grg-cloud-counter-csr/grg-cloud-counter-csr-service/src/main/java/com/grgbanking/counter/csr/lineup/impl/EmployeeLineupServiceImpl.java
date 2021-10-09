package com.grgbanking.counter.csr.lineup.impl;

import cn.hutool.core.util.RandomUtil;
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
import com.grgbanking.counter.common.socket.socket.entity.EmployeeService;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private SocketAbstractService socketService;

    @Override
    public void login(String clientId) {
        String customerId = lineupService.findCustomer(clientId);
        if (StringUtils.hasText(customerId)){
            SocketParamHead head = SocketParamHead.success(SocketApiNoConstants.VIDEO_CMD, CommonConstants.SUCCESS, "当前用户未挂断，业务办理中");
            EmployeeService employeeService = new EmployeeService();
            employeeService.setEmployeeId(clientId);
            employeeService.setCustomerId(customerId);
            SocketParam<EmployeeService> param = SocketParam.success(head, employeeService);
            socketService.sendMessage(clientId, param);
            return;
        }
    }

    @Override
    public void finish(String clientId) {
        redisTemplate.opsForHash().put(LineupConstants.EMPLOYEE_ONLINE_VIDEO_KEY, clientId, null);

        /**
         * 坐席结束服务，继续检查是否还有正在排队的用户
         */
        lineupService.check();
    }

    /**
     * 提醒坐席有用户进行视频呼叫
     * TODO 后续在此增加坐席选取的算法
     */
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
        log.info("已经分配到坐席: {}", employeeId);
        String customerId = accept(employeeId);
        if (!StringUtils.hasText(customerId)){
            log.info("当前无用户接入视频，客服将继续监听客户队列......");
            return;
        }

        SocketParamHead head = SocketParamHead.success(SocketApiNoConstants.VIDEO_CMD, CommonConstants.SUCCESS, "有用户申请视频协助");
        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeId(employeeId);
        employeeService.setCustomerId(customerId);
        /**body只放坐席客户端id*/
        SocketParam<EmployeeService> param = SocketParam.success(head, employeeService);
        /**给APP服务发送提醒广播*/
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, param);
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
}
