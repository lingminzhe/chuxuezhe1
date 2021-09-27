package com.grgbanking.counter.common.socket.socket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(SocketConfigProperties.PREFIX)
public class SocketConfigProperties {

    public static final String PREFIX = "socket";

    /**
     * 开启socket
     */
    private Boolean enabled = true;

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
    private Integer bossCount;

    /**
     *
     */
    private Integer workCount;

    /**
     *
     */
    private Boolean allowCustomRequests;

    /**
     * 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
     */
    private Integer upgradeTimeout;

    /**
     * Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
     */
    private Integer pingTimeout;

    /**
     * Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
     */
    private Integer pingInterval;


}
