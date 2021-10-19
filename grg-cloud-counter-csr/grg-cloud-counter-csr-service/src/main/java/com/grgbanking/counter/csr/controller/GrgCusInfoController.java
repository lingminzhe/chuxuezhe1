package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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

    @DubboReference
    RemoteCusInfoService remoteCusInfoService;

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

}

