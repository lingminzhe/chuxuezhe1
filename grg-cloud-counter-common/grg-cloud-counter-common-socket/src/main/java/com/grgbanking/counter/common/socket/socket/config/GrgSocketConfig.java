package com.grgbanking.counter.common.socket.socket.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * socket配置类
 *
 */
@Slf4j
@Configuration
public class GrgSocketConfig {

	@Autowired
	private SocketConfigProperties configProperties;

	/**
	 * @return SocketIOServer
	 */
	@Bean
	public SocketIOServer socketIOServer() {
		log.info("启用Socket的服务");
		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setTcpNoDelay(true);
		socketConfig.setSoLinger(0);
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		config.setSocketConfig(socketConfig);
		config.setHostname(configProperties.getHost());
		config.setPort(configProperties.getPort());
		config.setBossThreads(configProperties.getBossCount());
		config.setWorkerThreads(configProperties.getWorkCount());
		config.setAllowCustomRequests(configProperties.getAllowCustomRequests());
		config.setUpgradeTimeout(configProperties.getUpgradeTimeout());
		config.setPingTimeout(configProperties.getPingTimeout());
		config.setPingInterval(configProperties.getPingInterval());

//		config.setKeyStorePassword("123456");
//		InputStream stream = null;
//		try {
//			stream = new FileInputStream("C:\\keystore.jks");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		config.setKeyStore(stream);
		return new SocketIOServer(config);
	}

}
