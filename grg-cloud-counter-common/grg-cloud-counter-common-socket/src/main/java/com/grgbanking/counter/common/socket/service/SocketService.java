package com.grgbanking.counter.common.socket.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.grgbanking.counter.common.core.util.Resp;

public interface SocketService {

    /**
     * 添加自定义监听器以及其他处理
     * @param socketIOServer
     */
    void addListener(SocketIOServer socketIOServer);

    /**
     * 客户端连接上来了
     * @param clientId
     */
    void connected(String clientId);

    /**
     * 客户端断开连接了，实现此方法进行数据关联
     * @param clientId
     */
    void disconnect(String clientId);

    /**
     * 获取客户端标识，由使用方实现获取方式
     * @param client
     * @return
     */
    String getClientId(SocketIOClient client);

    /**
     * 接收消息
     * @param data      消息内容
     * @param fromClientId  消息来源的客户端Id
     * @return
     */
    boolean receiveMessage(Resp data,String fromClientId);

}
