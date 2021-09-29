package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/get/{no}")
    public Resp<GrgCusInfoEntity> getPersonalInfo(@PathVariable String no) {
        GrgCusInfoEntity grgCusInfoEntity = remoteCusInfoService.getByCardNoOrIdNo(no);
        return Resp.success(grgCusInfoEntity);
    }

}
