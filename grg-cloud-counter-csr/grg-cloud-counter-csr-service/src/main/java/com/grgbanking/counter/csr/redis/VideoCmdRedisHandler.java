package com.grgbanking.counter.csr.redis;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.csr.business.ServiceSessionManagement;
import com.grgbanking.counter.csr.socket.SocketServiceCsrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoCmdRedisHandler implements RedisHandler{

    @Autowired
    SocketServiceCsrImpl socketServiceCsr;
    @Autowired
    ServiceSessionManagement serviceSessionManagement;
    @Override
    public void execute(SocketParam param) {
        String serviceSessionId=param.getHead().getServiceSessionId();
        String clientId=serviceSessionManagement.getAvaliableClientId(serviceSessionId);
        socketServiceCsr.sendMessage(clientId,param);
    }
}
