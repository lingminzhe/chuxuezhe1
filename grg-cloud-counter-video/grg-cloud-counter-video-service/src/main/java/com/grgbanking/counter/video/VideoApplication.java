package com.grgbanking.counter.video;

//import com.grgbanking.counter.common.security.annotation.EnableGrgResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 */
//@EnableGrgResourceServer
@SpringBootApplication
@EnableDiscoveryClient
public class VideoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }

}
