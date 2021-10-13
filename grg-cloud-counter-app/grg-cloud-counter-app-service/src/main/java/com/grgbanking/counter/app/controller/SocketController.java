package com.grgbanking.counter.app.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * socket通讯的测试controller
 */
@RestController
@RequestMapping("app")
public class SocketController {

    @Autowired
    private LineupService lineupService;

    /**
     * 正常结束视频通话
     * @param param  用户端的id
     * @return
     */
    @PostMapping("finish")
    public Resp finish(@RequestBody Map<String, String> param){
        // TODO 端开视频
        //...

        /**删除与坐席的关系*/
        try {
            String clientId = param.get("clientId");
            lineupService.finish(clientId);
            lineupService.finishSession(clientId);
            return Resp.success("结束视频成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.failed("不在等待队列中");
        }
    }

}
