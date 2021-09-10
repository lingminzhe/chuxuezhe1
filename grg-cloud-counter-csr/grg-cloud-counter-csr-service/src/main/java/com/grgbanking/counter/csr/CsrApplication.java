package com.grgbanking.counter.csr;


//import com.grgbanking.counter.common.swagger.annotation.EnableSwagger;

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
public class CsrApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsrApplication.class, args);
    }

}
