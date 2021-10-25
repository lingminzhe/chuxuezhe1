package com.grgbanking.counter.device.controller;

import com.grgbanking.counter.csr.api.dubbo.RemoteBusiInfoService;
import com.grgbanking.counter.csr.api.dubbo.RemoteBusiOptService;
import com.grgbanking.counter.csr.api.entity.GrgCusBusiInfoEntity;
import com.grgbanking.counter.csr.api.entity.GrgCusBusiOptEntity;
import com.grgbanking.counter.device.dto.CusAccountDto;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusAccountEntity;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "个人中心")
@RestController
@RequestMapping("/cus")
public class CusInfoController {

    @DubboReference
    RemoteCusInfoService remoteCusInfoService;

    @DubboReference
    RemoteBusiInfoService remoteBusiInfoService;

    @DubboReference
    RemoteBusiOptService remoteBusiOptService;

    @DubboReference
    RemoteUserService remoteUserService;

    @ApiOperation("个人详细")
    @PostMapping("/personal/info")
    public Resp<GrgCusInfoEntity> getPersonalInfo(@RequestBody CusAccountDto cusAccountDto, HttpServletRequest request) {
        SysUserEntity sysUser = remoteUserService.currentUser(request.getHeader("Authorization"));
        GrgCusInfoEntity cusInfo = remoteCusInfoService.findCusInfo(String.valueOf(sysUser.getUserId()));
        if (cusInfo != null) {
            return Resp.success(cusInfo);
        }
        return Resp.failed("无法查询到用户数据!");
    }

    @ApiOperation("银行卡详情")
    @GetMapping("/card/{id}")
    public Resp<GrgCusAccountEntity> getCard(@PathVariable("id") String id) {
        GrgCusAccountEntity cusAccount = remoteCusInfoService.findCusAccount(id);
        return Resp.success(cusAccount);
    }

    @ApiOperation("银行卡列表")
    @PostMapping("/card/list")
    public Resp<List<GrgCusAccountEntity>> getCardList(@RequestBody CusAccountDto cusAccountDto) {
        List<GrgCusAccountEntity> cusAccountList = remoteCusInfoService.findCusAccountList(cusAccountDto.getUserId());
        return Resp.success(cusAccountList);
    }

    @ApiOperation("新增银行卡")
    @PostMapping("/save/card")
    public Resp<String> saveBankCard(@RequestBody GrgCusAccountEntity grgCusAccountEntity) {
        boolean b = remoteCusInfoService.saveBankCard(grgCusAccountEntity);
        if (b != true) {
            return Resp.failed("新增失败!");
        }
        return Resp.success("新增成功!");
    }

    @ApiOperation("绑定银行卡")
    @PostMapping("/bind/card")
    public Resp<String> bindBankCard(@RequestBody GrgCusAccountEntity grgCusAccountEntity) {
        boolean b = remoteCusInfoService.bindBankCard(grgCusAccountEntity);
        if (b)
            return Resp.success("绑定银行卡成功");
        return Resp.failed("绑定银行卡失败");
    }

    @ApiOperation("银行卡流水列表")
    @PostMapping("/card/itemized/list")
    public Resp<List<GrgCusBusiOptEntity>> getCardSequenceList(@RequestBody CusAccountDto cusAccountDto) {
        List<GrgCusBusiOptEntity> list = remoteBusiOptService.findList(cusAccountDto.getUserId());
        return Resp.success(list);
    }

    @ApiOperation("银行卡流水详情")
    @GetMapping("/card/itemized/{id}")
    public Resp<GrgCusBusiOptEntity> getCardSequence(@PathVariable("id") String id) {
        GrgCusBusiOptEntity one = remoteBusiOptService.getOne(id);
        return Resp.success(one);
    }

    @ApiOperation("办理业务列表")
    @PostMapping("/business/list")
    public Resp<List<GrgCusBusiInfoEntity>> getBuinessList(@Valid @RequestBody CusAccountDto cusAccountDto) {
        List<GrgCusBusiInfoEntity> list = remoteBusiInfoService.findList(cusAccountDto.getUserId(),cusAccountDto.getBusiStatus());
        return Resp.success(list);
    }

    @ApiOperation("办理业务详情")
    @GetMapping("/business/{id}")
    public Resp<GrgCusBusiInfoEntity> getBusiness(@PathVariable("id") String id) {
        GrgCusBusiInfoEntity one = remoteBusiInfoService.getOne(id);
        return Resp.success(one);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
//            @ApiImplicitParam(name = "accountId", value = "银行卡id", required = true),
//    })
//    @ApiOperation("删除")
//    @GetMapping("/delete/card")
//    public Resp deleteBankCard(@RequestBody CusAccountDto cusAccountDto) {
//
//        return Resp.failed("无法查询到用户数据!");
//    }

}
