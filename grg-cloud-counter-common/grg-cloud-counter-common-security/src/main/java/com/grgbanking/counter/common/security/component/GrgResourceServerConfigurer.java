package com.grgbanking.counter.common.security.component;

import com.grgbanking.counter.common.security.handler.FormAuthenticationFailureHandler;
import com.grgbanking.counter.common.security.handler.SsoLogoutSuccessHandler;
import com.grgbanking.counter.common.security.handler.TenantSavedRequestAwareAuthenticationSuccessHandler;
import com.grgbanking.counter.common.security.mobile.MobileSecurityConfigurer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 资源服务器配置
 * ResourceServerConfigurerAdapter和WebSecurityConfigurerAdapter都配置一样的url，则ResourceServerConfigurerAdapter会生效，因为ResourceServerConfigurerAdapter的order=3低于WebSecurityConfigurerAdapter的order=100，order数值越小优先级越高
 */
@Slf4j
public class GrgResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Autowired
	protected AuthenticationEntryPoint resourceAuthExceptionEntryPoint;

	@Autowired
	private PermitAllUrlResolver permitAllUrlResolver;

	@Autowired
	private ResourceServerTokenServices resourceServerTokenServices;

	@Autowired
	private TenantSavedRequestAwareAuthenticationSuccessHandler tenantSavedRequestAwareAuthenticationSuccessHandler;

	@Autowired
	private FormAuthenticationFailureHandler formAuthenticationFailureHandler;

	@Autowired
	private SsoLogoutSuccessHandler ssoLogoutSuccessHandler;

	@Autowired
	private MobileSecurityConfigurer mobileSecurityConfigurer;
	/**
	 * 默认的配置，对外暴露
	 * @param httpSecurity
	 */
	@Override
	@SneakyThrows
	public void configure(HttpSecurity httpSecurity) {
		// 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
//		httpSecurity.headers().frameOptions().disable();
//		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
//		// 配置对外暴露接口
//		permitAllUrlResolver.registry(registry);
//		registry.anyRequest().authenticated().and().csrf().disable();


		httpSecurity.headers().frameOptions().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
		// 配置对外暴露接口
		permitAllUrlResolver.registry(registry);
		httpSecurity.formLogin().loginPage("/token/login").loginProcessingUrl("/token/form")
				.successHandler(tenantSavedRequestAwareAuthenticationSuccessHandler)
				.failureHandler(formAuthenticationFailureHandler).and().logout()
				.logoutSuccessHandler(ssoLogoutSuccessHandler).deleteCookies("JSESSIONID").invalidateHttpSession(true)
				.and().authorizeRequests().antMatchers("/token/**", "/actuator/**", "/mobile/**").permitAll()
				.anyRequest().authenticated().and().csrf().disable().apply(mobileSecurityConfigurer);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).tokenServices(resourceServerTokenServices);
	}

}
