package com.grgbanking.counter.app.business;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceSessionManagement {
    Map<String,String> map=new HashMap<>();
    public void addSession(String sessionId,String clientId){
        map.put(sessionId,clientId);
    }

    public String getAppClientId(String sessionId){
        return map.get(sessionId);
    }
}

