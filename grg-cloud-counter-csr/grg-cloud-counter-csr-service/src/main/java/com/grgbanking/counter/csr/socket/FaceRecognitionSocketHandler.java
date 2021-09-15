package com.grgbanking.counter.csr.socket;

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
public class FaceRecognitionSocketHandler implements SocketHandler {


    @Autowired
    SocketServiceCsrImpl socketServiceCsr;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void execute(Object param,String clientId) {
        try {

            Map map =(Map) param;
            Map head=(Map)map.get("head");
            String serviceType=(String)head.get("tran_code");
            String schema=(String)head.get("user_login_type");
            String termId=(String)head.get("user_login_id");


            Map body=new HashMap();
            body.put("msg","注册成功");
            map.put("body",body);
            socketServiceCsr.sendMessage(clientId,map);

        } catch (Exception e){

        }
    }


}
