package com.grgbanking.counter.csr.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.server.SocketServer;
import com.grgbanking.counter.common.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 这是socket的实现类，自行实现的Demo，可根据实际情况进行定制开发
 */
@Slf4j
@Service
public class SocketServiceImpl extends SocketAbstractService {

    /**
     * 连接成功回调，进行redis的相关操作，比如客户端和服务器的关联关系的绑定等
     * @param clientId
     */
    @Override
    public void connected(String clientId) {
        log.info("客户端连接到实现类了：{}",clientId);
    }

    /**
     * 断开连接回调，进行redis的相关操作，比如客户端和服务器的关联关系的移除等
     * @param clientId
     */
    @Override
    public void disconnect(String clientId) {
        log.info("客户端连断开实现类了：{}",clientId);
    }

    /**
     * 获取客户端id的逻辑，自行实现
     */
    @Override
    public String getClientId(SocketIOClient client) {
        return "1000";
    }

    /**
     * 接收到消息回调
     * @param data      消息内容
     * @param fromClientId  消息来源的客户端Id
     * @return
     */
    @Override
    public boolean receiveMessage(Resp data, String fromClientId) {
        log.info("客户端消息发送到实现类了,客户端ID：{}，消息内容：{}",fromClientId,data);

        /**
         * 父类发送消息的函数
         */
        sendMessage(SocketServer.getClient("1000"),Resp.success("这是服务器消息"));
        return false;
    }
}
