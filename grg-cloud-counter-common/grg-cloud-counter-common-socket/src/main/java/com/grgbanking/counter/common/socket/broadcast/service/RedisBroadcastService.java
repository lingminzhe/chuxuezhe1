package com.grgbanking.counter.common.socket.broadcast.service;

import com.grgbanking.counter.common.core.util.SocketParam;

/**
 * redis广播消息Service
 */
public interface RedisBroadcastService {

    /**
     * 发送广播消息
     * @param channel
     * @param param
     * @return
     */
    boolean sendBroadcast(String channel, SocketParam param);

}
