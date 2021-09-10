package com.grgbanking.counter.app.business;

import com.grgbanking.counter.app.publish.CounterRedisPublisher;
import com.grgbanking.counter.app.socket.SocketServiceAppImpl;
import com.grgbanking.counter.app.subscribe.CounterRedisReceiver;
import com.grgbanking.counter.common.core.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class VideoBusiness implements BusinessHandler{

    @Autowired
    CounterRedisPublisher publisher;

    @Autowired
    CounterRedisReceiver receiver;


    @Autowired
    SocketServiceAppImpl socketServiceApp;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;



    @Override
    public void execute(Object param) {
        try {
            String serviceSessionId=(String)param;
            Map<String,String> map=new HashMap<>();
            map.put("service_type","video");
            map.put("service_session_id",serviceSessionId);
            publisher.publish(map);
            Future<Object> future=receiver.receive(serviceSessionId);
            String appClientId = serviceSessionManagement.getAppClientId(serviceSessionId);
            socketServiceApp.sendMessage(appClientId,Resp.success("收到你的消息了，请稍安勿躁"));
            Object o = future.get();
            if(o!=null){
                socketServiceApp.sendMessage(appClientId,Resp.success(o));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
