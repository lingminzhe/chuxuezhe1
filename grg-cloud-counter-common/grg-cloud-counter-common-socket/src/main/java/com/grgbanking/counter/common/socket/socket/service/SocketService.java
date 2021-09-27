package com.grgbanking.counter.common.socket.socket.service;

import com.grgbanking.counter.common.core.util.SocketParam;

public interface SocketService {

    /**
     * 客户端连接上来了
     *
     * @param clientId
     */
    void connected(String clientId);

    /**
     * 客户端断开连接了，实现此方法进行数据关联
     *
     * @param clientId
     */
    void disconnect(String clientId);

    /**
     * 当消息中无apiNo或者该apiNo无对应处理器时，消息将会传递到此函数
     *
     * @param clientId 消息来源的客户端Id
     * @param param    消息内容
     * @return
     */
    boolean onMessage(String clientId, SocketParam param);

}
