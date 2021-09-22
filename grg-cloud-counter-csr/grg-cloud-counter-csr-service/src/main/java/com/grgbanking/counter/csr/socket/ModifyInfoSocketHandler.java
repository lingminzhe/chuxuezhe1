package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.csr.business.ServiceSessionManagement;
import com.grgbanking.counter.csr.redis.AppRedisPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class ModifyInfoSocketHandler implements SocketHandler {


    @Autowired
    SocketServiceCsrImpl socketServiceCsr;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;

    @Autowired
    AppRedisPublisher publisher;

    String serviceType="modify_info";

    @Override
    public void execute(SocketParam param, String clientId) {
        try {
            publisher.publish(serviceType,param);
            //socketServiceCsr.sendMessage(clientId,map);

        } catch (Exception e){

        }
    }


}
