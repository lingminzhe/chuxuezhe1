package com.grgbanking.counter.csr.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 视频呼叫处理handler
 */
@Slf4j
@Component
public class VideoRedisBroadcastReceiverHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private SocketAbstractService socketAbstractService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.VIDEO_CMD;
    }

    @Override
    public void onMessage(String channel, SocketParam param) {
        String clientId = String.valueOf(param.getBody());
        /**
         * 给指定坐席发送socket消息通知有用户进行视频申请
         */
        log.info("发送视频呼叫通知坐席：{}",clientId);
        socketAbstractService.sendMessage(clientId,param);
    }

}
