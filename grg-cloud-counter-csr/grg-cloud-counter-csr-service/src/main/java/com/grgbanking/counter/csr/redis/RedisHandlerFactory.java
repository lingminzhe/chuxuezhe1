package com.grgbanking.counter.csr.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RedisHandlerFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public RedisHandler findHandler(String serviceType) {
        switch (serviceType){

            case "video_cmd":
                return applicationContext.getBean(VideoCmdRedisHandler.class);
            default:
                break;
        }
        return null;
    }
}
