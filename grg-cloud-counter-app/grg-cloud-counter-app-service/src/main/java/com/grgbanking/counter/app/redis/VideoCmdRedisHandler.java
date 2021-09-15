package com.grgbanking.counter.app.redis;

import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.app.socket.SocketServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VideoCmdRedisHandler implements RedisHandler{
    @Autowired
    SocketServiceAppImpl socketServiceApp;
    @Autowired
    ServiceSessionManagement serviceSessionManagement;
    @Override
    public void execute(Object param) {
        Map map=(Map)param;
        Map head=(Map)map.get("head");

        String serviceSessionId=(String)head.get("service_session_id");
        String clientId=serviceSessionManagement.getAvaliableClientId(serviceSessionId);
        socketServiceApp.sendMessage(clientId,param);
    }
}
