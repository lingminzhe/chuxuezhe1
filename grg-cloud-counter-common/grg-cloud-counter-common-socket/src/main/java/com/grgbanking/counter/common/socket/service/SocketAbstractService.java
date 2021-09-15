package com.grgbanking.counter.common.socket.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.server.SocketServer;

public abstract class SocketAbstractService implements SocketService {

    public static final String EVENT_NAME = "push_event";

    /**
     *  发送消息给对方
     * @param data
     */
    public void sendMessage(SocketIOClient client, Resp data){
        client.sendEvent(EVENT_NAME,data);
    }

    public void sendMessage(String clientId, Resp data){
        SocketIOClient client = SocketServer.getClient(clientId);
        sendMessage(client,data);
    }

    public void sendMessage(String clientId, Object data){
        SocketIOClient client = SocketServer.getClient(clientId);
        client.sendEvent(EVENT_NAME,data);
    }

}
