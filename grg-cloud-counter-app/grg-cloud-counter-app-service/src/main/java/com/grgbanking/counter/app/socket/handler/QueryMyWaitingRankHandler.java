package com.grgbanking.counter.app.socket.handler;


import com.grgbanking.counter.app.lineup.service.impl.CustomerLineupServiceImpl;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.handler.SocketMessageHandler;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import com.grgbanking.counter.common.socket.socket.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 查看我的视频呼叫排队的名次
 */
@Slf4j
@Service
public class QueryMyWaitingRankHandler implements SocketMessageHandler {

    @Autowired
    private CustomerLineupServiceImpl lineupService;

    @Autowired
    private SocketAbstractService socketService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.QUERY_WAITING_RANK;
    }

    @Override
    public void onMessage(String clientId, SocketParam param) {
        Long rank = lineupService.rank(clientId);
        Map<String, Object> body = new HashMap<>();
        body.put("rank", rank);
        param.setBody(body);
        log.info("查询我的排队名次，clientId：{}，名次：{}",clientId,rank);
        socketService.sendMessage(clientId,param);
    }
}
