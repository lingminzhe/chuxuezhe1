package com.grgbanking.counter.app.socket;

import com.grgbanking.counter.app.lineup.service.impl.CustomerLineupServiceImpl;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SocketServiceImpl extends SocketAbstractService {

    @Autowired
    private CustomerLineupServiceImpl lineupService;

    @Override
    public void connected(String clientId) {
        log.info("用户终端上线:{}", clientId);
        Long rank = lineupService.rank(clientId);
        Map<String, Object> body = new HashMap<>();
        body.put("rank", rank);
        sendMessage(clientId,SocketParam.success(body));
    }

    @Override
    public void disconnect(String clientId) {
        log.info("用户终端端开连接:{}", clientId);
    }

    /**
     * 当无apiNo或者该apiNo无对应处理器时，消息将会传递到此函数
     *
     * @param clientId 消息来源的客户端Id
     * @param param    消息内容
     * @return
     */
    @Override
    public boolean onMessage(String clientId, SocketParam param) {
        log.info("接收到默认无人处理的消息,ClientId:{},消息：{}", clientId, param);
        return false;
    }

}
