package com.grgbanking.counter.csr.socket.handler;


import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.handler.SocketMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 人脸认证Handler
 */
@Slf4j
@Service
public class FaceRecognitionHandler implements SocketMessageHandler {

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.FACE_RECOGNITION;
    }

    @Override
    public void onMessage(String clientId, SocketParam param) {
        log.info("接收到人脸认证呼叫：{}",param);
    }
}
