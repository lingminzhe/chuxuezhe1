package com.grgbanking.counter.common.socket.broadcast.constant;

/**
 * redis广播渠道定义
 * 多个服务之间通过广播通讯的广播渠道，原则上一个服务只监听一个渠道，根据广播消息里的aipNo使用不同的handler处理
 */
public class RedisBroadcastConstants {

    /**
     * 广播消息的前缀，其他广播消息务必加上此前缀，可以通用统一封装的类处理
     */
    public static final String BROADCAST_PREFIX = "grg:counter:broadcast:";

    /**
     * 发送给坐席服务的广播渠道名
     */
    public final static String BROADCAST_CHANNEL_CSR = BROADCAST_PREFIX.concat("csr");

    /**
     * 发送给APP服务的广播渠道名
     */
    public final static String BROADCAST_CHANNEL_APP = BROADCAST_PREFIX.concat("app");

    /**
     * 发送给DEVICE服务的广播渠道名
     */
    public final static String BROADCAST_CHANNEL_DEVICE = BROADCAST_PREFIX.concat("device");

}
