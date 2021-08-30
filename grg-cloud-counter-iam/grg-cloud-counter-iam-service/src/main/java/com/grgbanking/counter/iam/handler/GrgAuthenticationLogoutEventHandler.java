

package com.grgbanking.counter.iam.handler;

import com.grgbanking.counter.common.security.handler.AuthenticationLogoutHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出事件处理
 *
 * @date 2021/06/22
 */
@Slf4j
@Component
@AllArgsConstructor
public class GrgAuthenticationLogoutEventHandler implements AuthenticationLogoutHandler {

//	private final RemoteLogService logService;

//	private final KeyStrResolver tenantKeyStrResolver;

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 * @param request 请求
	 * @param response 返回
	 */
	@Async
	@Override
	public void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
//		SysLogDTO sysLog = SysLogUtils.getSysLog(request, username);
//		sysLog.setTitle(username + "用户退出");
//		sysLog.setParams(username);
//
//		// 获取clientId 信息
//		OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
//		sysLog.setServiceId(auth2Authentication.getOAuth2Request().getClientId());
//		sysLog.setTenantId(Integer.parseInt(tenantKeyStrResolver.key()));
//		// 保存退出的token
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//		sysLog.setParams(token);
//
//		logService.saveLog(sysLog, SecurityConstants.FROM_IN);
		log.info("用户：{} 退出成功, token:{}  已注销", username, token);
	}

}
