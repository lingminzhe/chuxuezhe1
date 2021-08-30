package com.grgbanking.counter.common.security.annotation;

import com.grgbanking.counter.common.security.component.GrgResourceServerAutoConfiguration;
import com.grgbanking.counter.common.security.component.GrgSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ GrgResourceServerAutoConfiguration.class, GrgSecurityBeanDefinitionRegistrar.class })
public @interface EnableGrgResourceServer {

}
