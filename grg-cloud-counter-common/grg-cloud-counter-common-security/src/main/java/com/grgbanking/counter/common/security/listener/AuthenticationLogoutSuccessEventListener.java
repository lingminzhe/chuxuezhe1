package com.grgbanking.counter.common.security.listener;

import cn.hutool.core.collection.CollUtil;
import com.grgbanking.counter.common.security.base.GrgUser;
import com.grgbanking.counter.common.security.handler.AuthenticationLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date 2021/06/22 退出事件监听器
 */
public class AuthenticationLogoutSuccessEventListener implements ApplicationListener<LogoutSuccessEvent> {

	@Autowired(required = false)
	private AuthenticationLogoutHandler logoutHandler;

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(LogoutSuccessEvent event) {
		OAuth2Authentication authentication = (OAuth2Authentication) event.getSource();
		if (logoutHandler != null && isUserAuthentication(authentication)) {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			HttpServletResponse response = requestAttributes.getResponse();
			logoutHandler.handle(authentication, request, response);
		}
	}

	private boolean isUserAuthentication(Authentication authentication) {
		return authentication.getPrincipal() instanceof GrgUser || CollUtil.isNotEmpty(authentication.getAuthorities());
	}

}
