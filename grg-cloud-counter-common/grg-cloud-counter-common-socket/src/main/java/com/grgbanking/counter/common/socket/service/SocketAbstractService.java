package com.grgbanking.counter.common.socket.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.Resp;

public abstract class SocketAbstractService implements SocketService {

    public static final String EVENT_NAME = "push_event";

    /**
     *  发送消息给对方
     * @param data
     */
    public void sendMessage(SocketIOClient client, Resp data){
        client.sendEvent(EVENT_NAME,data);
    }

}
