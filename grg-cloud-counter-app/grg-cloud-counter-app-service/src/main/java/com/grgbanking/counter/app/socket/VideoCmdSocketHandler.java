package com.grgbanking.counter.app.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.app.business.ServiceSessionManagement;
import com.grgbanking.counter.app.redis.CsrRedisPublisher;
import com.grgbanking.counter.app.redis.CsrRedisReceiver;
import com.grgbanking.counter.app.tencent.service.TencentService;
import com.grgbanking.counter.app.vo.CusAgentVideoVo;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.constant.RedisConstant;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public void execute(SocketParam param, String fromClientId) {
        try {
            Map<String,Object> body = (Map)param.getBody();
            String serviceSessionId=param.getHead().getServiceSessionId();
            serviceSessionManagement.addSession(serviceSessionId,fromClientId);

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
                param.getHead().setCode(CommonConstants.FAIL);
                param.getHead().setMsg("坐席全忙，当前为第" + count + "个");
                client.sendEvent("push_event", param);
            }else {
                redisTemplate.opsForSet().remove(RedisConstant.EMPLOYEE_SERVICE_SET, employee_id);
                CusAgentVideoVo cusAgentVideoVo = new CusAgentVideoVo();
                cusAgentVideoVo.setEmployeeId(employee_id);
                cusAgentVideoVo.setUserSig(tencentService.getUserSig(fromClientId));
                cusAgentVideoVo.setUserId(fromClientId);
                body.put("employee_id", cusAgentVideoVo.getEmployeeId());
                body.put("user_id", cusAgentVideoVo.getUserId());
                param.getHead().setApiNo("video_cmd");
                //通知当前客服接入视频
                publisher.publish("video_cmd",param);
                param.getHead().setMsg("当前有坐席接入： " + cusAgentVideoVo.getEmployeeId());
                body.put("user_sig", cusAgentVideoVo.getUserSig());
                System.out.println("准备发送接入视频事件!");
                //通知当前用户接入视频
                client.sendEvent("push_event", param);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
