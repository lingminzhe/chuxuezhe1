package com.grgbanking.counter.app.socket;

import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.app.redis.CsrRedisPublisher;
import com.grgbanking.counter.app.redis.CsrRedisReceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VideoCmdSocketHandler implements SocketHandler {

    @Autowired
    CsrRedisPublisher publisher;

    @Autowired
    CsrRedisReceiver receiver;


    @Autowired
    SocketServiceAppImpl socketServiceApp;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;




    @Override
    public void execute(Object param, String fromClientId) {
        try {
            Map map=(Map)param;
            Map head=(Map)map.get("head");
            String serviceSessionId=(String)head.get("service_session_id");
            serviceSessionManagement.addSession(serviceSessionId,fromClientId);
            publisher.publish("video_cmd",param);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
