package com.grgbanking.counter.common.socket.socket.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;

/**
 * Socket服务启动，实现类继承此抽象类
 */
@Slf4j
public abstract class SocketAbstractService implements SocketService {

    /**消息的Event Name */
    public static final String PUSH_EVENT_NAME = "push_event";

    /**
     *  发送消息给对方
     * @param param
     */
    public void sendMessage(String clientId, SocketParam param){
        SocketIOClient client = SocketServer.getClient(clientId);
        if (client == null) {
            return;
        }
        log.info("发送socket消息，clientId:{},param:{}",clientId,param);
        client.sendEvent(PUSH_EVENT_NAME,param);
    }

}
