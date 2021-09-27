package com.grgbanking.counter.csr.socket.handler;


import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.handler.SocketMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModifyInfoHandler implements SocketMessageHandler {

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.MODIFY_INFO;
    }

    @Override
    public void onMessage(String clientId, SocketParam param) {
        log.info("接收到修改信息：{}",param);
    }
}
