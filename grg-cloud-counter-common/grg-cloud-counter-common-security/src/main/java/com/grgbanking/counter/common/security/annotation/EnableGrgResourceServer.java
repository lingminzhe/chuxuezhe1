package com.grgbanking.counter.common.security.annotation;

import com.grgbanking.counter.common.security.component.GrgLocalResourceServerConfigurerAdapter;
import com.grgbanking.counter.common.security.component.GrgResourceServerAutoConfiguration;
import com.grgbanking.counter.common.security.component.GrgSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@EnableGrgResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ GrgResourceServerAutoConfiguration.class })
public @interface EnableGrgResourceServer {

}
