package com.grgbanking.counter.csr.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 无消息处理器处理的消息将在本类接收,默认情况转发给app服务
 */
@Slf4j
@Service
public class DefaultRedisBroadcastHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private RedisBroadcastService redisBroadcastService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.DEFAULT_HANDLER_NAME;
    }

    @Override
    public void onMessage(String channel, SocketParam param) {
        /**给APP服务转发广播消息，APP服务务必有处理该广播的处理器，否则CSR服务又会把消息转发回来*/
        log.info("没有处理广播消息的handler，此处是默认接收消息：{}", param);
        redisBroadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, param);
    }
}
