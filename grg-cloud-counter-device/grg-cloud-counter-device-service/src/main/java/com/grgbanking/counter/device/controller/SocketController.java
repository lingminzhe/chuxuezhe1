package com.grgbanking.counter.device.controller;

import com.alibaba.fastjson.JSON;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * socket通讯的测试controller
 */
@RestController
@RequestMapping("app")
@Slf4j
public class SocketController {

    @Autowired
    private LineupService lineupService;

    @Autowired
    private RedisBroadcastService broadcastService;

    /**
     * 正常结束视频通话
     * @param param  用户端的id
     * @return
     */
    @PostMapping("finish")
    public Resp finish(@RequestBody Map<String, String> param){
        // TODO 端开视频
        //...
        log.info("finish接口：{}", JSON.toJSONString(param));
        /**删除与坐席的关系*/
        try {
            String clientId = param.get("clientId");
            String employeeId = lineupService.findEmployee(clientId);
            lineupService.finish(clientId);
            lineupService.finishSession(clientId);
            Map<String, Object> body = new HashMap<>(1);
            body.put("businessStatus", Boolean.TRUE);
            SocketParamHead socketParamHead = SocketParamHead.success("endBusiness", 0, "success");
            socketParamHead.setClientId(employeeId);
            SocketParam socketParam = SocketParam.success(socketParamHead, body);
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, socketParam);
            return Resp.success("结束视频成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.failed("不在等待队列中");
        }
    }

}
