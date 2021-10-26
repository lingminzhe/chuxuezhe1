package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
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
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 手机验证码
 */
@RestController
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {

	@DubboReference
	private RemoteMobileService mobileService;

	@DubboReference
	private RemoteUserService remoteUserService;

	@Autowired
	private RedisBroadcastService broadcastService;

	@Autowired
	private LineupService lineupService;


	@ApiOperation(value = "发送短信")
	@GetMapping("/{mobile}")
	public Resp sendSmsCode(@PathVariable String mobile, HttpServletRequest request) {
		boolean b = mobileService.sendSmsCode(mobile);
		if (b){
			SysUserEntity authorization = remoteUserService.currentUser(request.getHeader("Authorization"));
			String customerId = lineupService.findCustomer(String.valueOf(authorization.getUserId()));
			SocketParamHead head = SocketParamHead.success("verifySms");
			head.setClientId(customerId);
			broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, SocketParam.success(head));
			return Resp.success("短信发送成功");
		}else {
			return Resp.failed("短信发送失败");
		}
	}



}
