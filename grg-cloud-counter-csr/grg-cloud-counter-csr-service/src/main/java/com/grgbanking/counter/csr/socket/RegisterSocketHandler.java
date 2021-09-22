package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.csr.business.ServiceSessionManagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@EnableScheduling
public class RegisterSocketHandler implements SocketHandler {


    @Autowired
    SocketServiceCsrImpl socketServiceCsr;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ServiceSessionManagement serviceSessionManagement;

    private final String redisKeyPrefix ="grg-cloud-counter-csr-register";
    private String instanceId= UUIDUtils.uuid();


    @Override
    public void execute(SocketParam param, String clientId) {
        try {

            register(clientId,param.getHead().getUserLoginType(),param.getHead().getUserLoginId());

            param.getHead().setCode(CommonConstants.SUCCESS);
            param.getHead().setMsg("注册成功");
            socketServiceCsr.sendMessage(clientId,param);

        } catch (Exception e){

        }
    }

    public void unregister(String clientId){
        String key= redisKeyPrefix +":"+instanceId;
        Map<String,String> map =(Map<String,String>)redisTemplate.opsForValue().get(key);
        if(map!=null&&!map.isEmpty()){
            map.remove(clientId);
            redisTemplate.opsForValue().set(key,map,1, TimeUnit.HOURS);
        }
    }

    public synchronized void register(String clientId, String schema, String termId) {

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
