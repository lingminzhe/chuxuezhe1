package com.grgbanking.counter.app.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CsrRedisReceiver implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisHandlerFactory redisHandlerFactory;

    @Override
    public void onMessage(Message message, byte[] bytes) {

        Map map = (Map)redisTemplate.getValueSerializer().deserialize(message.getBody());
        Map head = (Map)map.get("head");
        String serviceType=(String)head.get("tran_code");
        RedisHandler handler = redisHandlerFactory.findHandler(serviceType);
       // String serviceSessionId=serviceType+":"+schema+":"+termId;
       // handler.execute(serviceSessionId,map);
        handler.execute(map);


    }


}
