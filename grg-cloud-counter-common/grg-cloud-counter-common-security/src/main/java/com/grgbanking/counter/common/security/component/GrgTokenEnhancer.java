package com.grgbanking.counter.common.security.component;

import com.grgbanking.counter.common.core.constant.SecurityConstants;
import com.grgbanking.counter.common.security.service.GrgUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * token增强，登录接口返回用户信息等。客户端模式不增强。
 */
public class GrgTokenEnhancer implements TokenEnhancer {


	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (SecurityConstants.CLIENT_CREDENTIALS.equals(authentication.getOAuth2Request().getGrantType())) {
			return accessToken;
		}

		final Map<String, Object> additionalInfo = new HashMap<>();
		GrgUser grgUser = (GrgUser) authentication.getUserAuthentication().getPrincipal();
		additionalInfo.put(SecurityConstants.DETAILS_USER, grgUser);
//		additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.GRG_LICENSE);
//		additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
