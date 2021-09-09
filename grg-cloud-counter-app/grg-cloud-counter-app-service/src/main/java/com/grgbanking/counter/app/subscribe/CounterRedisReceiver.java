package com.grgbanking.counter.app.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

@Component
public class CounterRedisReceiver  implements MessageListener {

    Map map;
    Object lock=new Object();

    @Autowired
    RedisTemplate redisTemplate;

    ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void onMessage(Message message, byte[] bytes) {

        map = (Map)redisTemplate.getValueSerializer().deserialize(message.getBody());
        synchronized (lock){
            lock.notifyAll();
        }
    }

    public Future<Object> receive(String serviceSessionId) {
        Future<Object> future = executor.submit(() -> {
            synchronized (lock){
                while (true){
                    lock.wait();
                    if(serviceSessionId.equals(map.get("service_session_id"))){
                        break;
                    }
                }
                return map;
            }

        });
        return future;
    }
}
