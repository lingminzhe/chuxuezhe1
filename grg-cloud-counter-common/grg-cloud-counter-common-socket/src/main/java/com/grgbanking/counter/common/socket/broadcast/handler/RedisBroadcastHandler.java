package com.grgbanking.counter.common.socket.broadcast.handler;


import com.grgbanking.counter.common.core.util.SocketParam;

/**
 * 广播消息分发接口
 */
public interface RedisBroadcastHandler {

    /**
     * 设置当前服务接收Redis广播的频道名
     * @return
     */
    String setChannel();

    /**
     * 设置当前类接收Redis广播的业务编号
     * @return
     */
    String setApiNo();

    /**
     * 接收redis广播消息
     * @param channel
     * @param param
     */
    void onMessage(String channel, SocketParam param);


}
