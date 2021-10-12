package com.grgbanking.counter.bank.controller;

import com.grgbanking.counter.bank.MobileSmsVo;
import com.grgbanking.counter.bank.service.MobileService;
import com.grgbanking.counter.common.core.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {

	private final MobileService mobileService;

	@ApiOperation(value = "发送短信")
	@GetMapping("/{mobile}")
	public Resp sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}

	@ApiOperation(value = "验证短信验证码")
	@PostMapping("/verifySmsCode")
	public Resp verifySmsCode(@RequestBody MobileSmsVo mobile) {
		return mobileService.verifySmsCode(mobile);
	}

}
