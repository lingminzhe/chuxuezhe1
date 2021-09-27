package com.grgbanking.counter.common.socket.socket.handler;


import com.grgbanking.counter.common.core.util.SocketParam;

/**
 * socket消息接收接口
 */
public interface SocketMessageHandler {

    /**
     * 设置接收指定apiNo的消息
     */
    String setApiNo();

    /**
     * socket接收消息方法
     *
     * @param param
     */
    void onMessage(String clientId, SocketParam param);

}
