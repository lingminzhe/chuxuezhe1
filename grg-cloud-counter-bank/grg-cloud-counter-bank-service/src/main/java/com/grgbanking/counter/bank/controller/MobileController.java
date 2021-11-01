package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.api.SmsApi;
import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {

	private final RemoteMobileService mobileService;

	@Autowired
	private SmsApi smsApi;

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

	@SneakyThrows
	@GetMapping("/abc")
	public Resp test() {
		smsApi.getSmsApi("15767526746","测试");
		return Resp.success();
	}

}
