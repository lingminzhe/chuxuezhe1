package com.grgbanking.counter.device.controller;

import com.grgbanking.counter.csr.api.dubbo.RemoteCusBusiService;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import com.grgbanking.counter.device.dto.CusAccountDto;
import com.grgbanking.counter.device.tencent.service.TencentService;
import com.grgbanking.counter.device.vo.CusAgentVideoVo;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "业务中心")
@RestController
@RequestMapping("/busi")
public class CusBusiController {

    @DubboReference
    RemoteCusBusiService remoteCusBusiService;

    @Autowired
    TencentService tencentService;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("获取视频通讯")
    @GetMapping("/get/video")
    public Resp<CusAgentVideoVo> getPersonalInfo(@RequestBody CusAccountDto cusAccountDto) {
        CusAgentVideoVo cusAgentVideoVo = new CusAgentVideoVo();
        String userId = cusAccountDto.getUserId();
        GrgCusEmployeeServiceEntity employeeService = remoteCusBusiService.getEmployeeService(userId);
        if (employeeService != null){
            cusAgentVideoVo.setEmployeeId(employeeService.getEmployeeId());
            cusAgentVideoVo.setUserSig(tencentService.getUserSig(userId));
            cusAgentVideoVo.setUserId(userId);
            return Resp.success(cusAgentVideoVo);
        }else {
            Long count = redisTemplate.opsForList().rightPush("customer_waiting_queue", cusAgentVideoVo);
            return Resp.failed("坐席全忙，当前为第" + count + "个");
        }
    }

}
