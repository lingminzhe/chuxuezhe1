package com.grgbanking.counter.csr.business;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServiceSessionManagement {
    Map<String, String> map = new HashMap<>();

    public void addSession(String sessionId, String clientId) {
        map.put(sessionId, clientId);
    }

    public String getAvaliableClientId(String sessionId) {
        if (map.get(sessionId) != null) {
            return map.get(sessionId);
        }
        Set<Map.Entry<String, EnumCsrClientStatus>> entries = mapCsrClientStatus.entrySet();
        Map.Entry<String, EnumCsrClientStatus> entry = entries.stream().filter(
                item -> item.equals(EnumCsrClientStatus.Ready)
        ).findAny().get();
        map.put(sessionId, entry.getKey());
        return entry.getKey();
    }

    Map<String, EnumCsrClientStatus> mapCsrClientStatus = new HashMap<>();

    public void updateCsrClientStatus(String clientId, EnumCsrClientStatus status) {
        mapCsrClientStatus.put(clientId, status);
    }
}



