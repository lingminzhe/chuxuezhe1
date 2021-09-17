package com.grgbanking.counter.app.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.app.redis.CsrRedisPublisher;
import com.grgbanking.counter.app.redis.CsrRedisReceiver;

import com.grgbanking.counter.app.tencent.service.TencentService;
import com.grgbanking.counter.app.vo.CusAgentVideoVo;
import com.grgbanking.counter.common.socket.constant.RedisConstant;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VideoCmdSocketHandler implements SocketHandler {

    @Autowired
    CsrRedisPublisher publisher;

    @Autowired
    CsrRedisReceiver receiver;


    @Autowired
    SocketServiceAppImpl socketServiceApp;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TencentService tencentService;

    @Override
    public void execute(Object param, String fromClientId) {
        try {
            Map map=(Map)param;
            Map head=(Map)map.get("head");
            Map body=(Map)map.get("body");
            String serviceSessionId=(String)head.get("service_session_id");
            serviceSessionManagement.addSession(serviceSessionId,fromClientId);

//            String clientId = serviceSessionManagement.getAvaliableClientId(serviceSessionId);
//            socketServiceApp.sendMessage();

            SocketIOClient client = SocketServer.getClient(fromClientId);
            String employee_id = (String)redisTemplate.opsForList().leftPop(RedisConstant.EMPLOYEE_SERVICE_QUEUE);
            //如果当前无坐席
            if (employee_id == null){
                Long count = 0L;
                GrgCusEmployeeServiceEntity employeeService = new GrgCusEmployeeServiceEntity();
                String user_id = fromClientId;
                employeeService.setUserId(user_id);
                employeeService.setEmployeeId(employee_id);
                //此set用来去重，防止用户重复排队
                Boolean isMenber = redisTemplate.opsForSet().isMember(RedisConstant.CUSTOMER_WAITING_SET, user_id);
                if (!isMenber){
                    redisTemplate.opsForSet().add(RedisConstant.CUSTOMER_WAITING_SET, user_id);
                    count = redisTemplate.opsForList().rightPush(RedisConstant.CUSTOMER_WAITING_QUEUE, user_id);
                }else {
                    //Long waitCount = redisTemplate.opsForList().indexOf("customer_waiting_queue", user_id);
                    String userId = (String)redisTemplate.opsForList().index(RedisConstant.CUSTOMER_WAITING_QUEUE, count);
                    while (userId != null){
                        if (userId == user_id){
                            break;
                        }
                        count ++;
                        userId = (String)redisTemplate.opsForList().index(RedisConstant.CUSTOMER_WAITING_QUEUE, count);
                    }
                }
                System.out.println("准备发送等待事件!");
                //若从客服队列取不到客服,返回等待数据
                head.put("code", 500);
                head.put("msg", "坐席全忙，当前为第" + count + "个");
                client.sendEvent("push_event", map);
            }else {
                redisTemplate.opsForSet().remove(RedisConstant.EMPLOYEE_SERVICE_SET, employee_id);
                CusAgentVideoVo cusAgentVideoVo = new CusAgentVideoVo();
                cusAgentVideoVo.setEmployeeId(employee_id);
                cusAgentVideoVo.setUserSig(tencentService.getUserSig(fromClientId));
                cusAgentVideoVo.setUserId(fromClientId);
                body.put("employee_id", cusAgentVideoVo.getEmployeeId());
                body.put("user_id", cusAgentVideoVo.getUserId());
                head.put("api_no", "video_cmd");
                //通知当前客服接入视频
                publisher.publish("video_cmd",param);
                head.put("msg", "当前有坐席接入： " + cusAgentVideoVo.getEmployeeId());
                head.put("code", 200);
                body.put("user_sig", cusAgentVideoVo.getUserSig());
                System.out.println("准备发送接入视频事件!");
                //通知当前用户接入视频
                client.sendEvent("push_event", map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
