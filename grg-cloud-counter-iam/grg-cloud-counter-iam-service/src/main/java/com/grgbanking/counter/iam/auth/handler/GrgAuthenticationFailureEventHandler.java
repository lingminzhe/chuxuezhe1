package com.grgbanking.counter.iam.auth.handler;

import com.grgbanking.counter.common.security.handler.AuthenticationFailureHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@Slf4j
@Component
@AllArgsConstructor
public class GrgAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

	/**
	 * 异步处理，登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 * @param request 请求
	 * @param response 响应
	 */
	@Async
	@Override
	@SneakyThrows
	public void handle(AuthenticationException authenticationException, Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
//		SysLogDTO sysLog = SysLogUtils.getSysLog(request, username);
//		sysLog.setTitle(username + "用户登录");
//		sysLog.setType(LogTypeEnum.ERROR.getType());
//		sysLog.setParams(username);
//		sysLog.setException(authenticationException.getLocalizedMessage());
//		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));
//		sysLog.setTenantId(Integer.parseInt(tenantKeyStrResolver.key()));
//
//		logService.saveLog(sysLog, SecurityConstants.FROM_IN);

		log.error("用户：{} 登录失败", username, authenticationException);
	}

}
