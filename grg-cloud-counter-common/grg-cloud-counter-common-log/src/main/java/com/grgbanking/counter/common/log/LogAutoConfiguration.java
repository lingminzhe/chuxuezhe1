package com.grgbanking.counter.common.log;

import com.grgbanking.counter.common.log.aspect.SysLogAspect;
import com.grgbanking.counter.common.log.aspect.SysLogListener;
import com.grgbanking.counter.iam.api.dubbo.LogRemoteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author grgbanking
 *
 * 日志自动配置
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {


	private final LogRemoteService logRemoteService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(logRemoteService);
	}

	@Bean
	public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
		return new SysLogAspect(publisher);
	}

}
