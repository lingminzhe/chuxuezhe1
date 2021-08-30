package com.grgbanking.counter.common.security.component;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
			List<String> strings = StrUtil.split(url, "|");

			// 仅配置对外暴露的URL ，则注册到 spring security的为全部方法
			if (strings.size() == 1) {
				log.info("添加对外暴露的地址：{}",strings.get(0));
				registry.antMatchers(strings.get(0)).permitAll();
				continue;
			}

			// 当配置对外的URL|GET,POST 这种形式，则获取方法列表 并注册到 spring security
			if (strings.size() == 2) {
				for (String method : StrUtil.split(strings.get(1), StrUtil.COMMA)) {
					registry.antMatchers(HttpMethod.valueOf(method), strings.get(0)).permitAll();
				}
				continue;
			}
			log.warn("{} 配置无效，无法配置对外暴露", url);
		}
	}

}
