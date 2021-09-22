package com.grgbanking.counter.common.socket.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.server.SocketServer;

public abstract class SocketAbstractService implements SocketService {

    public static final String EVENT_NAME = "push_event";

    /**
     *  发送消息给对方
     * @param param
     */
    public void sendMessage(SocketIOClient client, SocketParam param){
        client.sendEvent(EVENT_NAME,param);
    }

    public void sendMessage(String clientId, SocketParam param){
        SocketIOClient client = SocketServer.getClient(clientId);
        sendMessage(client,param);
    }

}
