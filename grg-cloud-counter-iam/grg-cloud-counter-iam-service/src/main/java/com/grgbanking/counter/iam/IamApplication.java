package com.grgbanking.counter.iam;

import com.grgbanking.counter.common.security.annotation.EnableGrgResourceServer;
import com.grgbanking.counter.common.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwagger
@EnableGrgResourceServer
@SpringBootApplication
@EnableDiscoveryClient
public class IamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

}
