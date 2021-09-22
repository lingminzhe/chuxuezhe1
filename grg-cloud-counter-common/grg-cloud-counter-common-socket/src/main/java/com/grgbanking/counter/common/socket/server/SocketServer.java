package com.grgbanking.counter.common.socket.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.service.SocketAbstractService;
import com.grgbanking.counter.common.socket.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SocketServer {

    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    @Autowired
    private SocketService socketService;

    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    private void start() throws Exception {
        /**监听客户端连接*/
        socketIOServer.addConnectListener(client -> {
            log.info("有客户端连接上来了");
            String clientId = socketService.getClientId(client);
            if (StringUtils.hasText(clientId)) {
                log.info("客户端ID：{}", clientId);
                log.info("SessionId：{}", client.getSessionId());
                log.info("RemoteAddress：{}", client.getRemoteAddress());
                log.info("Transport：{}", client.getTransport());
                clientMap.put(clientId, client);
                socketService.connected(clientId);
            } else {
                log.error("客户端ID为空");
            }
        });

        /**监听客户端断开连接*/
        socketIOServer.addDisconnectListener(client -> {
            String clientId = socketService.getClientId(client);
            if (StringUtils.hasText(clientId)) {
                clientMap.remove(clientId);
                log.info("断开连接,clientId：{}，sessionId：{}", clientId,client.getSessionId());
                client.disconnect();
                socketService.disconnect(clientId);
            }else {
                log.error("客户端断开连接，但是获取不到clientId");
            }
        });

        /**添加消息接收监听器*/
        socketIOServer.addEventListener(SocketAbstractService.EVENT_NAME, SocketParam.class, new DataListener<SocketParam>() {
            @Override
            public void onData(SocketIOClient client, SocketParam param, AckRequest ackRequest) throws Exception {
                client.getHandshakeData();
                log.info("接收到消息：{}",param);
                socketService.receiveMessage(param,socketService.getClientId(client));
            }
        });

        /**启动Socket服务*/
        socketIOServer.start();
    }

    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    /**
     * 根据客户端Id获取当前节点的client
     * @param clientId
     * @return
     */
    public static SocketIOClient getClient(String clientId) {
        if (!StringUtils.hasText(clientId)) {
            return null;
        }
        return clientMap.get(clientId);
    }

    /**
     * 获取当前节点所有的连接的客户端
     * @return
     */
    public static Map<String, SocketIOClient> clientMap(){
        return clientMap;
    }

}
