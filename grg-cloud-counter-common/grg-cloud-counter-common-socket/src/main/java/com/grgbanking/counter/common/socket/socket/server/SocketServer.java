package com.grgbanking.counter.common.socket.socket.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.store.MemoryStore;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.core.util.SocketParamHead;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.constant.SocketConnectStatusEnum;
import com.grgbanking.counter.common.socket.socket.handler.SocketMessageHandler;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Socket服务
 */
@Slf4j
@Component
public class SocketServer implements ApplicationContextAware {

    /**
     * 用来存已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketServer;

    @Autowired
    private SocketAbstractService socketService;

    @Autowired
    private LineupService lineupService;

    private Map<String, SocketMessageHandler> handlers = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        collectHandlers(applicationContext);
    }

    /**启动Socket服务*/
    @PostConstruct
    private void startSocketServer() {
        socketServer.addConnectListener(client -> {
            String clientId = getClientId(client);
            log.info("有终端连接上来，ClientId：{}", clientId);
            if (!StringUtils.hasText(clientId)) {
                log.info("有终端连接上来，没有传ClientId，断开连接");
                client.disconnect();
                return;
            }
            clientMap.put(clientId, client);
            lineupService.login(clientId);
            lineupService.connectionChanged(clientId, SocketConnectStatusEnum.UP);
            lineupService.check();
            socketService.connected(clientId);
        });
        socketServer.addDisconnectListener(client -> {
            client.disconnect();
            String clientId = getClientId(client);
            if (!StringUtils.hasText(clientId)) {
                log.error("客户端断开连接，但是获取不到clientId");
                return;
            }
            log.error("断开连接,clientId：{}", clientId);
            clientMap.remove(clientId);
            lineupService.connectionChanged(clientId, SocketConnectStatusEnum.DOWN);
            socketService.disconnect(clientId);
        });
        socketServer.addEventListener(SocketAbstractService.PUSH_EVENT_NAME, SocketParam.class, (client, param, ackRequest) -> {
            client.getHandshakeData();
            String apiNo = param.getHead().getApi_no();
            log.info("socket接收到消息，apiNo：{},消息：{}", apiNo, param);
            if (CollectionUtils.isEmpty(handlers) || !StringUtils.hasText(apiNo)) {
                log.error("接收到Socket消息，但无接收器处理或者无业务编号，将把消息转发到默认的onMessage方法");
                socketService.onMessage(getClientId(client), param);
                return;
            }
            SocketMessageHandler socketMessageHandler = handlers.get(apiNo);
            if (socketMessageHandler != null) {
                socketMessageHandler.onMessage(getClientId(client), param);
            } else {
                log.error("接收到Socket消息，但找不到处理的handler，将把消息转发到默认的onMessage方法");
                socketService.onMessage(getClientId(client), param);
            }
        });
        socketServer.start();
    }

    /**服务关闭*/
    @PreDestroy
    private void stopSocketServer() {
        log.info("停止socketServer...");
        if (socketServer != null) {
            socketServer.stop();
            socketServer = null;
        }
    }

    /**
     * 获取客户端ID
     *
     * @param client
     * @return
     */
    private String getClientId(SocketIOClient client) {
        return client.getHandshakeData().getSingleUrlParam("user_id");
    }

    /**
     * 根据客户端Id获取当前节点的client
     *
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
     * 收集Socket消息处理的handlers
     *
     * @param applicationContext
     */
    private void collectHandlers(ApplicationContext applicationContext) {
        Map<String, SocketMessageHandler> messageReceivers = applicationContext.getBeansOfType(SocketMessageHandler.class);
        if (CollectionUtils.isEmpty(messageReceivers)) {
            return;
        }
        messageReceivers.forEach((key, messageHandler) -> {
            if (!StringUtils.hasText(messageHandler.setApiNo())) {
                log.error("请设置处理socket消息的handler的apiNo,handler：{}", key);
                SpringApplication.exit(applicationContext);
            }
            if (handlers.containsKey(messageHandler.setApiNo())) {
                log.error("不能存在相同apiNo的handler：{}", messageHandler.setApiNo());
                SpringApplication.exit(applicationContext);
            }
            handlers.put(messageHandler.setApiNo(), messageHandler);
        });
    }

}
