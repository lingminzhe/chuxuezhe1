package com.grgbanking.counter.device.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 无消息处理器处理的消息将在本类接收,默认情况转发给csr服务
 */
@Slf4j
@Service
public class DefaultRedisBroadcastHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private SocketAbstractService socketService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.DEFAULT_HANDLER_NAME;
    }

    /**
     * 默认直接透传用户终端
     *
     * @param param 消息内容
     * @return
     */
    @Override
    public void onMessage(String channel, SocketParam param) {
        log.info("默认处理器 收到的报文{}", param);
        String clientId = param.getHead().getClientId();
        param.getHead().setClientId(null);
        socketService.sendMessage(clientId,param);
    }
}
