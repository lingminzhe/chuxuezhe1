package com.grgbanking.counter.csr.redis;


import com.grgbanking.counter.common.lock.components.LockTemplate;
import com.grgbanking.counter.csr.socket.SocketServiceCsrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppRedisReceiver implements MessageListener {

    @Autowired
    SocketServiceCsrImpl socketService;
    @Autowired
    LockTemplate lockTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisHandlerFactory redisHandlerFactory;
    public void receiveMessage(String message) {
        // TODO 这里是收到通道的消息之后执行的方法
        System.out.println("消费了消息: "+message);
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String s = new String(message.getChannel());

        Map map = (Map)redisTemplate.getValueSerializer().deserialize(message.getBody());
        Map head = (Map)map.get("head");
        String serviceType=(String)head.get("tran_code");
        RedisHandler handler = redisHandlerFactory.findHandler(serviceType);
        handler.execute(map);

//        List<String> values = redisTemplate.opsForHash().values("grg-csr-service");
//        int randIndex = RandomUtil.randomInt(values.size());
//        String countTermId = values.get(randIndex);
//
//        String appTermId=new String(message.getBody());
//        RLock lock = lockTemplate.lock(LockPrefixEnum.MAM_TERM_ALIVE, appTermId);
//        lock.lock();
//       // socketService.sendMessage(countTermId,null);
//        lock.unlock();

    }
}
