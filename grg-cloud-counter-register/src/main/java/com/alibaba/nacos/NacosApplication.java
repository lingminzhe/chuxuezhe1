package com.alibaba.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author nacos
 * <p>
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.nacos")
@ServletComponentScan
@EnableScheduling
public class NacosApplication {

	public static void main(String[] args) {
		System.setProperty("nacos.standalone", "true");
		SpringApplication.run(NacosApplication.class, args);
	}
}

