package com.grgbanking.counter.csr.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.constant.RedisConstant;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.common.socket.service.SocketAbstractService;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.TencentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 这是socket的实现类，自行实现的Demo，可根据实际情况进行定制开发
 */
@Slf4j
@Service
public class SocketServiceCsrImpl extends SocketAbstractService {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SocketHandlerFactory socketHandlerFactory;

    @Autowired
    TencentService tencentService;


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
        RegisterSocketHandler register = (RegisterSocketHandler) socketHandlerFactory.findHandler("register");
        register.unregister(clientId);
        log.info("客户端连断开了：{}",clientId);
    }


    /**
     * 获取客户端id的逻辑，自行实现
     */
    @Override
    public String getClientId(SocketIOClient client) {
        String agent_id = client.getHandshakeData().getSingleUrlParam("user_id");
        return agent_id;
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
        Map<String,Object> body = (Map)param.getBody();
        String apiNo = param.getHead().getApiNo();
        if (apiNo.equals("prepared")){
            String user_id = (String)redisTemplate.opsForList().leftPop(RedisConstant.CUSTOMER_WAITING_QUEUE);
            if (user_id != null){
                redisTemplate.opsForSet().remove(RedisConstant.CUSTOMER_WAITING_SET, user_id);
                log.info("当前客户队列有用户，取出第一位：{}", user_id);
                body.put("user_id", user_id);
                body.put("employee_id", fromClientId);
                body.put("user_sig", tencentService.getUserSig(fromClientId));
                param.getHead().setApiNo("video_cmd");
                param.getHead().setMsg("当前有客户接入视频： " + user_id);
                apiNo = "video_cmd";
                SocketIOClient client = SocketServer.getClient(fromClientId);
                client.sendEvent("push_event", param);
            }else {
                String employee_id = fromClientId;
                GrgCusEmployeeServiceEntity employeeServiceEntity = new GrgCusEmployeeServiceEntity();
                employeeServiceEntity.setEmployeeId(employee_id);
                Boolean isMenber = redisTemplate.opsForSet().isMember(RedisConstant.EMPLOYEE_SERVICE_SET, employee_id);
                if (!isMenber){
                    redisTemplate.opsForSet().add(RedisConstant.EMPLOYEE_SERVICE_SET, employee_id);
                    redisTemplate.opsForList().rightPush(RedisConstant.EMPLOYEE_SERVICE_QUEUE, employee_id);
                }
                log.info("当前无用户，客服id: {}进入客服队列", employee_id);
            }
        }

       SocketHandler handler= socketHandlerFactory.findHandler(apiNo);
        if(handler!=null){
            handler.execute(param,fromClientId);
        }

       // sendMessage(SocketServer.getClient(fromClientId),Resp.success("这是服务器消息"));
        return false;
    }



}
