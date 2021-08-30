package com.grgbanking.counter.common.security.serializer;

import com.grgbanking.counter.common.core.constant.SecurityConstants;
import com.grgbanking.counter.common.security.component.GrgRedisTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @date 2020/9/29
 * <p>
 * redis token store 自动配置
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class GrgTokenStoreAutoConfiguration {

	private final RedisConnectionFactory connectionFactory;

	@Bean
	public TokenStore tokenStore() {
		GrgRedisTokenStore tokenStore = new GrgRedisTokenStore(connectionFactory);
		tokenStore.setPrefix(SecurityConstants.GRG_PREFIX + SecurityConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication);
			}
		});
		return tokenStore;
	}

}
