package com.grgbanking.counter.app.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.common.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 这是socket的实现类，自行实现的Demo，可根据实际情况进行定制开发
 */
@Slf4j
@Service
@EnableScheduling
public class SocketServiceImpl extends SocketAbstractService {
    private final String redisKeyPrefix ="grg-cloud-counter-app-register";
    private String instanceId= UUIDUtils.uuid();


    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void addListener(SocketIOServer socketIOServer) {

    }



    /**
     * 连接成功回调，进行redis的相关操作，比如客户端和服务器的关联关系的绑定等
     * @param clientId
     */
    @Override
    public void connected(String clientId) {

        log.info("客户端已连接：{}",clientId);

    }

    /**
     * 断开连接回调，进行redis的相关操作，比如客户端和服务器的关联关系的移除等
     * @param clientId
     */
    @Override
    public void disconnect(String clientId) {
        log.info("客户端连断开实现类了：{}",clientId);
        String key=instanceId+":"+clientId;
        redisTemplate.opsForHash().delete("grg-cloud-counter-app-register",key);
    }

    /**
     * 获取客户端id的逻辑，自行实现
     */
    @Override
    public String getClientId(SocketIOClient client) {
        return client.getSessionId().toString();
    }

    /**
     * 接收到消息回调
     * @param data      消息内容
     * @param fromClientId  消息来源的客户端Id
     * @return
     */
    @Override
    public boolean receiveMessage(Resp data, String fromClientId) {
        log.info("客户端消息发送到实现类了,客户端ID：{}，消息内容：{}",fromClientId,data);

        /**
         * 父类发送消息的函数
         */
        if("video".equals(data.getData())){
            //发布消息
            redisTemplate.convertAndSend("video",fromClientId);
        }


        sendMessage(SocketServer.getClient(fromClientId),Resp.success("这是服务器消息"));
        return false;
    }

    @Override
    public synchronized void register(SocketIOClient client,String schema,String termId) {
        String clientId=getClientId(client);
        String key= redisKeyPrefix +":"+instanceId;
        String hashKey=clientId;
        String value=schema+":"+termId;

        Map<String,String> map =(Map<String,String>)redisTemplate.opsForValue().get(key);
        if(map==null){
            map=new HashMap<>();
        }else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(entry.getValue().equals(value)){
                    map.remove(entry.getKey());
                }
            }
        }
        map.put(hashKey,value);

        redisTemplate.opsForValue().set(key,map,1, TimeUnit.HOURS);
        log.info("客户端注册了{},{},{}",clientId,schema,termId);
    }

    //每隔20分钟重置一下redis超时时间，防止过期删除
    @Scheduled(fixedRate =20*60*1000)
    public synchronized void monitorRedis(){
        String key= redisKeyPrefix +":"+instanceId;
        Object o = redisTemplate.opsForValue().get(key);
        if(o!=null){
            redisTemplate.opsForValue().set(key,o,1, TimeUnit.HOURS);
        }
    }

}
