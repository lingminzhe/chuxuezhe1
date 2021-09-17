package com.grgbanking.counter.app.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.common.core.util.UUIDUtils;
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
public class SocketServiceAppImpl extends SocketAbstractService {
    private final String redisKeyPrefix ="grg-cloud-counter-app-register";
    private String instanceId= UUIDUtils.uuid();


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SocketHandlerFactory socketHandlerFactory;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;

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
        String key= redisKeyPrefix +":"+instanceId;
        Map<String,String> map =(Map<String,String>)redisTemplate.opsForValue().get(key);
        if(map!=null&&!map.isEmpty()){
            map.remove(clientId);
            redisTemplate.opsForValue().set(key,map,1, TimeUnit.HOURS);
        }
        log.info("客户端连断开了：{}",clientId);
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
    public boolean receiveMessage(Object data, String fromClientId) { log.info("客户端消息发送到实现类了,客户端ID：{}，消息内容：{}",fromClientId,data);

        Map map=(Map)data;

        Map head=(Map)map.get("head");


        String serviceType=(String)head.get("tran_code");
        String schema=(String)head.get("user_login_type");
        String termId=(String)head.get("user_login_id");



        SocketHandler handler= socketHandlerFactory.findHandler(serviceType);
        if(handler!=null){
            handler.execute(data,fromClientId);
        }


       // sendMessage(SocketServer.getClient(fromClientId),Resp.success("这是服务器消息"));
        return false;
    }

}
