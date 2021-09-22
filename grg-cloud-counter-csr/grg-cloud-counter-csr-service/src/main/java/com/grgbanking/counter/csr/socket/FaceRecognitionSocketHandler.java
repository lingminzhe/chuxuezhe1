package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.common.core.util.SocketParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class FaceRecognitionSocketHandler implements SocketHandler {


    @Autowired
    SocketServiceCsrImpl socketServiceCsr;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void execute(SocketParam param, String clientId) {
        try {
            param.getHead().setMsg("注册成功");
            socketServiceCsr.sendMessage(clientId,param);

        } catch (Exception e){

        }
    }


}
