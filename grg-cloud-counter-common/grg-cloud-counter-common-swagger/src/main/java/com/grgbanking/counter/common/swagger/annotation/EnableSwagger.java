package com.grgbanking.counter.common.swagger.annotation;

import com.grgbanking.counter.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.lang.annotation.*;

/**
 * 开启 swagger
 *
 * @author grgbanking
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableOpenApi
@Import({ SwaggerAutoConfiguration.class})
public @interface EnableSwagger {

}
