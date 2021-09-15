package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.csr.redis.AppRedisPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@EnableScheduling
public class VideoCmdSocketHandler implements SocketHandler {


    @Autowired
    AppRedisPublisher redisPublisher;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void execute(Object param,String clientId) {
        try {

            Map map =(Map) param;
            Map head=(Map)map.get("head");
            String serviceType=(String)head.get("tran_code");
//            String schema=(String)head.get("user_login_type");
//            String termId=(String)head.get("user_login_id");
//
//
//            Map body=new HashMap();
//            body.put("msg","注册成功");
//            map.put("body",body);

            redisPublisher.publish(serviceType,param);

        } catch (Exception e){

        }
    }


}
