package com.grgbanking.counter.device.business;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceSessionManagement {
    Map<String,String> map=new HashMap<>();

    public void addSession(String sessionId,String clientId){
        map.put(sessionId,clientId);
    }

    public String getAvaliableClientId(String sessionId){
        return map.get(sessionId);
    }

    public String getSessionId(String clientId){
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(clientId))
                .map(item -> item.getValue())
                .findFirst()
                .orElse(null);
    }


}

