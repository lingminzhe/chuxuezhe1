package com.grgbanking.counter.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.security.utils.GrgSecurityMessageSourceUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 异常处理 {@link AuthenticationException } 不同细化异常处理
 *
 */
@Slf4j
@Component
@AllArgsConstructor
public class GrgCommenceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Resp<String> result = new Resp<>();
		result.setMsg(authException.getMessage());
		result.setData(authException.getMessage());
		result.setCode(CommonConstants.FAIL);

		if (authException instanceof CredentialsExpiredException || authException instanceof InsufficientAuthenticationException) {
			String msg = GrgSecurityMessageSourceUtil.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", authException.getMessage());
			result.setMsg(msg);
		}

		if (authException instanceof UsernameNotFoundException) {
			String msg = GrgSecurityMessageSourceUtil.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", authException.getMessage());
			result.setMsg(msg);
		}

		if (authException instanceof BadCredentialsException) {
			String msg = GrgSecurityMessageSourceUtil.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.badClientCredentials", authException.getMessage());
			result.setMsg(msg);
		}

		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
