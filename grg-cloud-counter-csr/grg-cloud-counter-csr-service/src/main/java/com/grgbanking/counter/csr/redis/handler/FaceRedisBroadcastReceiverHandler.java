package com.grgbanking.counter.csr.redis.handler;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 人脸认证处理handler
 */
@Slf4j
@Component
public class FaceRedisBroadcastReceiverHandler extends RedisBroadcastAbstractHandler {

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.FACE_RECOGNITION;
    }

    /**
     * 广播消息需要再次封装
     * @param param
     */
    @Override
    public void onMessage(String channel, SocketParam param) {

    }

}
