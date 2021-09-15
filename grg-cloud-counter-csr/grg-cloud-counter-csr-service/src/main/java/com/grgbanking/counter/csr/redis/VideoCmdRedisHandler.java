package com.grgbanking.counter.csr.redis;

import com.grgbanking.counter.csr.business.ServiceSessionManagement;
import com.grgbanking.counter.csr.socket.SocketServiceCsrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VideoCmdRedisHandler implements RedisHandler{

    @Autowired
    SocketServiceCsrImpl socketServiceCsr;
    @Autowired
    ServiceSessionManagement serviceSessionManagement;
    @Override
    public void execute(Object param) {

        Map map=(Map)param;
        Map head=(Map)map.get("head");

        String serviceSessionId=(String)head.get("service_session_id");
        String clientId=serviceSessionManagement.getAvaliableClientId(serviceSessionId);
        socketServiceCsr.sendMessage(clientId,param);
    }
}
