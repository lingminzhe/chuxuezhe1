package com.grgbanking.counter.common.security.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源暴露处理器
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.oauth2.client")
public class PermitAllUrlResolver {

	@Getter
	@Setter
	private List<String> ignoreUrls = new ArrayList<>();

	/**
	 * 获取对外暴露的URL，注册到 spring security
	 * @param registry spring security context
	 */
	public void registry(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		for (String url : getIgnoreUrls()) {
			log.info("添加对外暴露的地址：{}",url);
			registry.antMatchers(url).permitAll();
		}
	}

}
