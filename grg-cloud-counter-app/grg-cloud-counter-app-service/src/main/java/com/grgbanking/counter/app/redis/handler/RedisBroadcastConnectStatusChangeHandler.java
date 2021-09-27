package com.grgbanking.counter.app.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 连接状态发生变化的handler
 */
@Slf4j
@Service
public class RedisBroadcastConnectStatusChangeHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private SocketAbstractService socketService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.CONNECTION_STATUS_CHANGE;
    }

    @Override
    public void onMessage(String channel, SocketParam param) {
        /**给该用户发送对方的连接状态的变更通知，body里直接是终端id，所以直接取出来用*/
        socketService.sendMessage((String)param.getBody(),param);
    }

}
