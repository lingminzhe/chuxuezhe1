package com.grgbanking.counter.common.socket;

import com.grgbanking.counter.common.socket.config.GrgSocketConfig;
import com.grgbanking.counter.common.socket.server.SocketServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * socket 配置
 *
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(GrgSocketConfig.class)
@Import({ SocketServer.class})
@ConditionalOnProperty(prefix = GrgSocketConfig.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class SocketAutoConfiguration {


}
