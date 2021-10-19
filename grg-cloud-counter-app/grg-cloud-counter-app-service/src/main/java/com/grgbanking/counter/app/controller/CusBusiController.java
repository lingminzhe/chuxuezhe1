package com.grgbanking.counter.app.controller;

import com.alibaba.fastjson.JSON;
import com.grgbanking.counter.app.dto.CusAccountDto;
import com.grgbanking.counter.app.tencent.service.TencentService;
import com.grgbanking.counter.app.vo.CusAgentVideoVo;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusAccountService;
import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.bank.api.entity.CreditCardEntity;
import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.csr.api.dubbo.RemoteCusBusiService;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "业务中心")
@RestController
@RequestMapping("/busi")
@Slf4j
public class CusBusiController {
    @DubboReference
    RemoteMobileService remoteMobileService;
    @DubboReference
    RemoteCusAccountService remoteCusAccountService;

    @DubboReference
    RemoteCusBusiService remoteCusBusiService;

    @Autowired
    TencentService tencentService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisBroadcastService broadcastService;

    @Autowired
    private LineupService lineupService;

    @ApiOperation("获取视频通讯")
    @GetMapping("/get/video")
    public Resp<CusAgentVideoVo> getPersonalInfo(@RequestBody CusAccountDto cusAccountDto) {
        CusAgentVideoVo cusAgentVideoVo = new CusAgentVideoVo();
        String userId = cusAccountDto.getUserId();
        GrgCusEmployeeServiceEntity employeeService = remoteCusBusiService.getEmployeeService(userId);
        if (employeeService != null) {
            cusAgentVideoVo.setEmployeeId(employeeService.getEmployeeId());
            cusAgentVideoVo.setUserSig(tencentService.getUserSig(userId));
            cusAgentVideoVo.setUserId(userId);
            return Resp.success(cusAgentVideoVo);
        } else {
            Long count = redisTemplate.opsForList().rightPush("customer_waiting_queue", cusAgentVideoVo);
            return Resp.failed("坐席全忙，当前为第" + count + "个");
        }
    }

    @ApiOperation("激活码校验")
    @PostMapping("/verify/activation")
    public Resp<String> validateActivationCode(@RequestBody @Validated CreditCardEntity creditCardEntity) {
        HashMap<Object, Object> map = new HashMap<>();
        Boolean tag = remoteCusAccountService.acvCard(creditCardEntity);
        if (!tag) {
            map.put("message", "激活码校验失败");
            SocketParam param = SocketParam.failed(map);
            param.getHead().setApiNo(SocketApiNoConstants.ACTIVATION_CHECK);
            log.info("cvvCode接口报文: {}", JSON.toJSONString(param));
            return Resp.failed("激活码校验失败,激活码不匹配");
        }
        map.put("message", "激活码校验成功");
        SocketParam param = SocketParam.success(map);
        param.getHead().setApiNo(SocketApiNoConstants.ACTIVATION_CHECK);
        log.info("cvvCode接口报文: {}", JSON.toJSONString(param));
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
        return Resp.success("激活码校验成功");
    }

    @ApiOperation("验证码校验")
    @PostMapping("/verify/auth")
    public Resp<String> validateAuthCode(@RequestBody MobileSmsVo mobileSmsVo) {
        HashMap<Object, Object> map = new HashMap<>();
        boolean flag = remoteMobileService.verifySmsCode(mobileSmsVo);
        if (flag == false) {
            map.put("message", "验证码校验失败");
            SocketParam param = SocketParam.success(map);
            param.getHead().setApiNo(SocketApiNoConstants.AUTH_CHECK);
            return Resp.failed("验证码校验失败");
        }
        map.put("message", "验证码校验成功");
        SocketParam param = SocketParam.success(map);
        param.getHead().setApiNo(SocketApiNoConstants.AUTH_CHECK);
        log.info("authCode接口报文: {}", JSON.toJSONString(param));
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
        return Resp.success("验证码校验成功");
    }
}
