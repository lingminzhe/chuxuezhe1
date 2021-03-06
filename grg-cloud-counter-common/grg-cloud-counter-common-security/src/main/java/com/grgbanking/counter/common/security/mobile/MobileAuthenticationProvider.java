package com.grgbanking.counter.common.security.mobile;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import com.grgbanking.counter.common.security.component.GrgPreAuthenticationChecks;
import com.grgbanking.counter.common.security.service.GrgUserDetailsService;
import com.grgbanking.counter.common.security.utils.GrgSecurityMessageSourceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 手机登录校验逻辑 验证码登录、社交登录
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

	private MessageSourceAccessor messages = GrgSecurityMessageSourceUtil.getAccessor();

	private UserDetailsChecker detailsChecker = new GrgPreAuthenticationChecks();

	@Getter
	@Setter
	private GrgUserDetailsService userDetailsService;

	@Override
	@SneakyThrows
	public Authentication authenticate(Authentication authentication) {
		MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
		String[] principal = mobileAuthenticationToken.getPrincipal().toString().split(StringPool.AT);
		UserDetails userDetails = userDetailsService.loadUserBySocial(LoginTypeEnum.valueOf(principal[0]),principal[1]);
		if (userDetails == null) {
			log.error("Authentication failed: no credentials provided");
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", "Noop Bind Account"));
		}

		// 检查账号状态
		detailsChecker.check(userDetails);

		MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
		authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
