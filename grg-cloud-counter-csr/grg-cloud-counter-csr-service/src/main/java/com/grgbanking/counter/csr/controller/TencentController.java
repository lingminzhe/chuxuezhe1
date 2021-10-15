package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.service.TencentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tencent")
@Api(tags = "腾讯接口")
@Slf4j
public class TencentController {

    @Autowired
    TencentService tencentService;

    /**
     * 获取视频通讯userSig
     * @param userId
     * @return
     */
    @CrossOrigin
    @ApiOperation("获取视频通讯userSig")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true)
    @PostMapping("/getUserSig")
    public Resp<String> getUserSig(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        return Resp.success(tencentService.getUserSig(userId));
    }

}
