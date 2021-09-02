package com.grgbanking.counter.common.socket.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * socket配置类
 *
 */
@Slf4j
@Getter
@Setter
@RefreshScope
@ConfigurationProperties(GrgSocketConfig.PREFIX)
public class GrgSocketConfig {

	public static final String PREFIX = "socket";

	/**
	 * 开启socket
	 */
	private boolean enabled = true;

	/**
	 * host在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
	 */
	private String host;

	/**
	 * socket端口
	 */
	private Integer port;

	/**
	 * socket连接数大小（如只监听一个端口boss线程组为1即可）
	 */
	private int bossCount;

	/**
	 *
	 */
	private int workCount;

	/**
	 *
	 */
	private boolean allowCustomRequests;

	/**
	 * 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
	 */
	private int upgradeTimeout;

	/**
	 * Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
	 */
	private int pingTimeout;

	/**
	 * Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
	 */
	private int pingInterval;


	/**
	 * 以下配置在上面的application.properties中已经注明
	 * @return
	 */
	@Bean
	public SocketIOServer socketIOServer() {
		log.info("启用socketio的服务");
		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setTcpNoDelay(true);
		socketConfig.setSoLinger(0);
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		config.setSocketConfig(socketConfig);
		config.setHostname(host);
		config.setPort(port);
		config.setBossThreads(bossCount);
		config.setWorkerThreads(workCount);
		config.setAllowCustomRequests(allowCustomRequests);
		config.setUpgradeTimeout(upgradeTimeout);
		config.setPingTimeout(pingTimeout);
		config.setPingInterval(pingInterval);
		return new SocketIOServer(config);
	}
}
