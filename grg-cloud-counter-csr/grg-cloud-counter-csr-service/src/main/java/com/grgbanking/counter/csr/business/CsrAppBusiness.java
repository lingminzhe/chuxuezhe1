package com.grgbanking.counter.csr.business;

import com.grgbanking.counter.csr.publish.AppRedisPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsrAppBusiness {

    @Autowired
    AppRedisPublisher appRedisPublisher;

    public void onMessage(){
        appRedisPublisher.publish("坐席返回成功");
    }
}
