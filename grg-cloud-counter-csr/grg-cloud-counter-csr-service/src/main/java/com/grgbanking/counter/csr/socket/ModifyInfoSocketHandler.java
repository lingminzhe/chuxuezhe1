package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.csr.business.ServiceSessionManagement;
import com.grgbanking.counter.csr.redis.AppRedisPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public void execute(Object param,String clientId) {
        try {

            Map map =(Map) param;
            Map head=(Map)map.get("head");
            String serviceType=(String)head.get("tran_code");
            String schema=(String)head.get("user_login_type");
            String termId=(String)head.get("user_login_id");


            Map body=new HashMap();
            body.put("msg","");
            map.put("body",body);
            publisher.publish(serviceType,map);
            //socketServiceCsr.sendMessage(clientId,map);

        } catch (Exception e){

        }
    }


}
