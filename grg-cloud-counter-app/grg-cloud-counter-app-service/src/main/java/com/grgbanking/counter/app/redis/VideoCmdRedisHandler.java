package com.grgbanking.counter.app.redis;

import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.app.socket.SocketServiceAppImpl;
import com.grgbanking.counter.common.core.util.SocketParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoCmdRedisHandler implements RedisHandler {

    @Autowired
    SocketServiceAppImpl socketServiceApp;
    @Autowired
    ServiceSessionManagement serviceSessionManagement;

    @Override
    public void execute(SocketParam param) {
        String serviceSessionId = param.getHead().getServiceSessionId();
        String clientId = serviceSessionManagement.getAvaliableClientId(serviceSessionId);
        socketServiceApp.sendMessage(clientId, param);
    }
}
