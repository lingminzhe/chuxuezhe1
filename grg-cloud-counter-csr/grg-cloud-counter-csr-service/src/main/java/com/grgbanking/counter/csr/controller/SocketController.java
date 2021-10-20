package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.constant.LineupConstants;
import com.grgbanking.counter.csr.lineup.impl.EmployeeLineupServiceImpl;
import com.grgbanking.counter.csr.service.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * socket通讯的测试controller
 */
@RestController
@RequestMapping("csr")
public class SocketController {

    @Autowired
    private EmployeeLineupServiceImpl lineupService;

    @Autowired
    private RedisBroadcastService broadcastService;

    /**
     * 正常结束视频通话
     *
     * @param param
     * @return
     */
    @PostMapping("finish")
    public Resp finish(@RequestBody Map<String, String> param) {
        String clientId = param.get("clientId");
        String customerId = lineupService.findCustomer(clientId);
        lineupService.finish(clientId);
        lineupService.finishSession(customerId);
        //发送结束事件给客户端
        Map<String, Object> body = new HashMap<>(1);
        body.put("businessStatus", Boolean.TRUE);
        SocketParamHead socketParamHead = SocketParamHead.success("endBusiness", 0, "success");
        socketParamHead.setClientId(customerId);
        SocketParam socketParam = SocketParam.success(socketParamHead, body);
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, socketParam);
        return Resp.success("结束视频成功");
    }

    /**
     * 退出坐席服务
     * @param param
     * @return
     */
    @PostMapping("logout")
    public Resp logout(@RequestBody Map<String, String> param){
        String clientId = param.get("clientId");
        lineupService.logout(clientId);
        return Resp.success("退出坐席视频服务成功");
    }
}
