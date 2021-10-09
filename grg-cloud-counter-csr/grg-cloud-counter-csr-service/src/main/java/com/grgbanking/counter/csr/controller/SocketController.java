package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.lineup.constant.LineupConstants;
import com.grgbanking.counter.csr.lineup.impl.EmployeeLineupServiceImpl;
import com.grgbanking.counter.csr.service.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * socket通讯的测试controller
 */
@RestController
@RequestMapping("csr")
public class SocketController {

    @Autowired
    private EmployeeLineupServiceImpl lineupService;

    /**
     * 正常结束视频通话
     *
     * @param clientId
     * @return
     */
    @PostMapping("finish")
    public Resp finish(@RequestBody Map<String, String> param) {
        String clientId = param.get("clientId");
        lineupService.finish(clientId);
        return Resp.success("结束视频成功");
    }

    /**
     * 退出坐席服务
     * @param clientId
     * @return
     */
    @PostMapping("logout")
    public Resp logout(@RequestBody Map<String, String> param){
        String clientId = param.get("clientId");
        lineupService.logout(clientId);
        return Resp.success("退出坐席视频服务成功");
    }
}
