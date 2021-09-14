package com.grgbanking.counter.app;


import com.grgbanking.counter.common.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 */
//@EnableGrgResourceServer
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
