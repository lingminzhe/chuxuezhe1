package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.bank.api.dubbo.RemoteCusAccountService;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.bank.api.vo.BankCardVo;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 *
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@Api(tags = "业务接口")
@RestController
@RequestMapping("/customer")
public class GrgCusInfoController {

    @Autowired
    RedisBroadcastService broadcastService;

    @Autowired
    LineupService lineupService;

    @DubboReference
    RemoteCusInfoService remoteCusInfoService;

    @DubboReference
    RemoteCusAccountService remoteCusAccountService;

    @DubboReference
    RemoteUserService remoteUserService;

    @ApiOperation("通过证件号码或手机号查询用户信息")
    @PostMapping("/get")
    public Resp<GrgCusInfoEntity> getPersonalInfo(@RequestBody Map<String, String> param) {
        String number = param.get("number");
        GrgCusInfoEntity grgCusInfoEntity = remoteCusInfoService.getByCardNoOrIdNo(number);
        if(grgCusInfoEntity == null){
            return Resp.failed("用户信息不存在！");
        }
        return Resp.success(grgCusInfoEntity);
    }

    @ApiOperation("信息修改")
    @PostMapping("/cus/info/update")
    public Resp<String> updateCusInfo(@RequestBody GrgCusInfoEntity cusInfoEntity, HttpServletRequest request) {
        boolean b = remoteCusInfoService.updateCusInfo(cusInfoEntity);
        SysUserEntity sysUser = remoteUserService.currentUser(request.getHeader("Authorization"));
        //封装SocketParam报文
        String customerId = lineupService.findCustomer(String.valueOf(sysUser.getUserId()));
        SocketParamHead socketParamHead = SocketParamHead.success("businessResult", "100001");
        socketParamHead.setClientId(customerId);

        if (b){
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, SocketParam.success(socketParamHead));
            return Resp.success("更新成功");
        }else {
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, SocketParam.failed(socketParamHead));
            return Resp.failed("更新失败");
        }
    }

    @ApiOperation("银行卡状态更改")
    @PostMapping("/account/update")
    public Resp<String> updateCardStatus(@RequestBody BankCardVo bankCardVo, HttpServletRequest request) {
        Boolean isSuccess = remoteCusAccountService.updateCardStatus(bankCardVo);
        String token = request.getHeader("Authorization");
        SysUserEntity grgUser = remoteUserService.currentUser(token);
        //封装socketparam报文
        Integer cardStatus = bankCardVo.getCardStatus();
        SocketParamHead paramHead = SocketParamHead.success("resultReportLoss", "110001");
        paramHead.setClientId(lineupService.findCustomer(String.valueOf(grgUser.getUserId())));
        if (cardStatus == 1){
            paramHead.setApiNo("cardActivation");
            paramHead.setBusiNo("120001");
        }

        if (isSuccess){
            paramHead.setMsg("更新成功");
            broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, SocketParam.success(paramHead));
            return Resp.success("更新成功");
        }
        paramHead.setMsg("更新失败");
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, SocketParam.success(paramHead));
        return Resp.failed("更新失败");
    }

}

