package com.grgbanking.counter.app.controller;

import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @DubboReference
    private RemoteMobileService mobileService;

    @ApiOperation(value = "验证短信验证码")
    @PostMapping("/verifySmsCode")
    public Resp verifySmsCode(@RequestBody MobileSmsVo mobile) {
        boolean b = mobileService.verifySmsCode(mobile);
        if (b) {
            return Resp.success("验证成功!");
        }else {
            return Resp.failed("短信验证码验证失败，请重试。");
        }
    }

    @ApiOperation(value = "发送短信")
    @GetMapping("/{mobile}")
    public Resp sendSmsCode(@PathVariable String mobile) {
        boolean b = mobileService.sendSmsCode(mobile);
        if (b){
            return Resp.success("短信发送成功");
        }else {
            return Resp.failed("短信发送失败");
        }
    }
}
