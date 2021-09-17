package com.grgbanking.counter.csr.redis;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.lock.components.LockTemplate;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.csr.entity.CusAgentVideoVo;
import com.grgbanking.counter.csr.service.TencentService;
import com.grgbanking.counter.csr.socket.SocketServiceCsrImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class AppRedisReceiver implements MessageListener {

    @Autowired
    SocketServiceCsrImpl socketService;
    @Autowired
    LockTemplate lockTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TencentService tencentService;

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
        Map body = (Map)map.get("body");
        log.info("当前坐席获取到的报文： {}", map);
//        RedisHandler handler = redisHandlerFactory.findHandler(serviceType);
//        handler.execute(map);

        String serviceType=(String)head.get("api_no");
        //若是视频事件
        if (serviceType.equals("video_cmd")){
            String employee_id = (String) body.get("employee_id");
            SocketIOClient client = SocketServer.getClient(employee_id);
            if (client != null){
                System.out.println("当前坐席以获取到消息");
                String user_id = (String) body.get("user_id");
                CusAgentVideoVo cusAgentVideoVo = new CusAgentVideoVo();
                cusAgentVideoVo.setUserId(user_id);
                cusAgentVideoVo.setEmployeeId(employee_id);
                cusAgentVideoVo.setUserSig(tencentService.getUserSig(employee_id));
                cusAgentVideoVo.setEmployeeId(employee_id);
                body.put("user_sig", cusAgentVideoVo.getUserSig());
                head.put("api_no", "video_cmd");
                head.put("code", 200);
                head.put("msg", "当前有客户接入视频: " + user_id);
                client.sendEvent("push_event", map);
            }
            //获取所有坐席，并通知变更当前排队统计
            Map<String, SocketIOClient> clientMap = SocketServer.clientMap();
            clientMap.forEach((k, v) -> {

            });
        }


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
