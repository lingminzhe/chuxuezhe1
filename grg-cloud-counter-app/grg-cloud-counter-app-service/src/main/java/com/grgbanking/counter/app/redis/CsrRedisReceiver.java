package com.grgbanking.counter.app.redis;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.socket.constant.RedisConstant;
import com.grgbanking.counter.common.socket.server.SocketServer;
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

    /**
     * 主要用来呼叫用户接收视频
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {

        Map map = (Map)redisTemplate.getValueSerializer().deserialize(message.getBody());
        Map head = (Map)map.get("head");
        Map body = (Map)map.get("body");
        String serviceType=(String)head.get("api_no");
        head.put("msg", "当前有客服接入视频: " + body.get("employee_id"));
        System.out.println("客户收到的报文： " + JSON.toJSONString(map));
        // RedisHandler handler = redisHandlerFactory.findHandler(serviceType);
        // String serviceSessionId=serviceType+":"+schema+":"+termId;
        // handler.execute(serviceSessionId,map);
        // handler.execute(map);
        String user_id = (String)body.get("user_id");
        SocketIOClient client = SocketServer.getClient(user_id);
        if (client != null){
            client.sendEvent("push_event", map);
        }

    }


}
