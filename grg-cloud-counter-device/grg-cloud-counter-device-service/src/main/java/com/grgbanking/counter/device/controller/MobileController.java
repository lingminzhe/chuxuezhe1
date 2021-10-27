package com.grgbanking.counter.device.controller;

import com.grgbanking.counter.device.dto.BusiSmsDTO;
import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.impl.LineupAbstractService;
import com.grgbanking.counter.csr.api.dubbo.RemoteBusiInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@Api(tags = "短信平台")
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @DubboReference
    private RemoteBusiInfoService busiInfoService;

    @DubboReference
    private RemoteMobileService mobileService;

    @Autowired
    private LineupAbstractService lineupAbstractService;

    @Autowired
    private RedisBroadcastService broadcastService;


    private final int length = 11;

    @ApiOperation(value = "发送短信")
    @GetMapping("/{mobile}")
    public Resp sendSmsCode(@PathVariable String mobile) {
        //11位手机号码
        if (mobile.length()==length) {
            boolean b = mobileService.sendSmsCode(mobile);
            if (b) {
                return Resp.success("短信发送成功");
            } else {
                return Resp.failed("短信发送失败");
            }
        }
        return Resp.failed("输入的手机号码须为11位");
    }

    @ApiOperation(value = "验证短信验证码")
    @PostMapping("/verifySmsCode")
    public Resp verifySmsCode(@RequestBody MobileSmsVo mobile) {
        boolean b = mobileService.verifySmsCode(mobile);
        String employee = lineupAbstractService.findEmployee(mobile.getUserId());
        if (b) {
            SocketParamHead success = SocketParamHead.success("verifySms", 0, "success");
            success.setClientId(employee);
            Map<String, Object> map = new HashMap<>();
            map.put("result", "success");
            SocketParam socketParam = SocketParam.success(success, map);
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, socketParam);
            return Resp.success("验证成功!");
        }else {
            SocketParamHead success = SocketParamHead.success("verifySms", 500, "failed");
            success.setClientId(employee);
            SocketParam socketParam = SocketParam.failed(success);
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, socketParam);
            return Resp.failed("短信验证码验证失败，请重试。");
        }
    }

    /**
     * 发送业务办理成功短信
     * @param busiSmsDTO
     * @return
     */
    @ApiOperation(value = "发送业务办理成功短信")
    @PostMapping("/sendBusiSms")
    public Resp sendBusiSms(@RequestBody BusiSmsDTO busiSmsDTO) {
        String mobile = busiSmsDTO.getMobile();
        String busiNo = busiSmsDTO.getBusiNo();
        String name = busiInfoService.getBusiNameByNo(busiNo);
//        String sessionId = lineupAbstractService.findSessionId(grgCustomerVo.getCustomerId());


        //11位手机号码
        if (mobile.length()==length) {
            boolean b = mobileService.sendBusiSms(mobile,name);
            if (b) {
                return Resp.success("短信发送成功");
            } else {
                return Resp.failed("短信发送失败");
            }
        }
        return Resp.failed("输入的手机号码须为11位");
    }
}
