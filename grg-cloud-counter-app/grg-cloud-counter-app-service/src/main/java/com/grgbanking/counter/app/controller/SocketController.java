package com.grgbanking.counter.app.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param clientId  用户端的id
     * @return
     */
    @GetMapping("finish")
    public Resp finish(String clientId){
        // TODO 端开视频
        //...

        /**删除与坐席的关系*/
        lineupService.finish(clientId);
        return Resp.success("结束视频成功");
    }

}
