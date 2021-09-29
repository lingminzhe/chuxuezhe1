package com.grgbanking.counter.app.redis.handler;

import com.grgbanking.counter.app.tencent.service.TencentService;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.entity.EmployeeService;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 连接状态发生变化的handler
 */
@Slf4j
@Service
public class RedisBusiHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private SocketAbstractService socketService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.BUSI_NO;
    }

    @Override
    public void onMessage(String channel, SocketParam param) {
        socketService.sendMessage(param.getHead().getMsg(), param);
    }

}
