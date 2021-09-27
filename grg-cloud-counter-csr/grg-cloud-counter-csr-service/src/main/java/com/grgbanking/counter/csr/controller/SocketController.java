package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.exception.BizException;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.lineup.impl.EmployeeLineupServiceImpl;
import com.grgbanking.counter.csr.service.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * socket通讯的测试controller
 */
@RestController
@RequestMapping("csr")
public class SocketController {

    @Autowired
    private EmployeeLineupServiceImpl lineupService;

    @Autowired
    private TencentService tencentService;

    /**
     * 坐席接到通知后，点击接受用户视频请求
     *
     * @param clientId 坐席id
     * @return
     */
    @GetMapping("accept")
    public Resp accept(String clientId) {
        String customerId = lineupService.accept(clientId);
        if (!StringUtils.hasText(customerId)) {
            throw new BizException("暂无用户呼叫");
        }
        // TODO 进行视频呼叫
        return Resp.success();
    }

    /**
     * 正常结束视频通话
     *
     * @param clientId
     * @return
     */
    @GetMapping("finish")
    public Resp finish(String clientId) {
        lineupService.finish(clientId);
        return Resp.success("结束视频成功");
    }

    /**
     * 退出坐席服务
     * @param clientId
     * @return
     */
    @GetMapping("logout")
    public Resp logout(String clientId){
        lineupService.logout(clientId);
        return Resp.success("退出坐席视频服务成功");
    }
}
