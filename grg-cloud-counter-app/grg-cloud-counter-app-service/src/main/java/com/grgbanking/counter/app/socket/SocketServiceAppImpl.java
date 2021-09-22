package com.grgbanking.counter.app.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    public void preStart(SocketIOServer socketIOServer) {

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
        String user_id = client.getHandshakeData().getSingleUrlParam("user_id");
        return user_id;
    }

    /**
     * 接收到消息回调
     * @param param      消息内容
     * @param fromClientId  消息来源的客户端Id
     * @return
     */
    @Override
    public boolean receiveMessage(SocketParam param, String fromClientId) {
        log.info("客户端消息发送到实现类了,客户端ID：{}，消息内容：{}",fromClientId,param);
        SocketHandler handler= socketHandlerFactory.findHandler(param.getHead().getApiNo());
        if(handler!=null){
            handler.execute(param,fromClientId);
        }


       // sendMessage(SocketServer.getClient(fromClientId),Resp.success("这是服务器消息"));
        return false;
    }

}
