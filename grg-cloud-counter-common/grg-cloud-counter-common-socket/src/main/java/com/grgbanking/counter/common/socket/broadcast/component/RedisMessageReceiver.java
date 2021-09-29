package com.grgbanking.counter.common.socket.broadcast.component;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.handler.RedisBroadcastHandler;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 接收Redis广播消息
 */
@Slf4j
@Component
public class RedisMessageReceiver implements MessageListener, ApplicationContextAware {

    @Autowired
    private RedisTemplate redisTemplate;

    private RedisBroadcastHandler defaultHandler;

    private Map<String, RedisBroadcastHandler> handlers = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        collectHandlers(applicationContext);
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        SocketParam param = (SocketParam) redisTemplate.getValueSerializer().deserialize(message.getBody());
        String channel = new String(message.getChannel());
        String apiNo = param.getHead().getApiNo();
        log.debug("接收到广播消息，channel:{},apiNo:{}", channel, apiNo);
        if (!StringUtils.hasText(apiNo)) {
            log.info("apiNo为空，将把广播消息转发到默认接收器");
            defaultHandler.onMessage(channel, param);
            return;
        }
        String handlerKey = channel.concat(":").concat(apiNo);
        RedisBroadcastHandler messageHandler = handlers.get(handlerKey);
        if (messageHandler == null) {
            log.debug("广播处理器为空，将把广播消息转发到默认接收器：{}", handlerKey);
            defaultHandler.onMessage(channel, param);
            return;
        }
        messageHandler.onMessage(channel, param);

    }

    /**
     * 收集Redis广播消息处理的handlers
     *
     * @param applicationContext
     */
    private void collectHandlers(ApplicationContext applicationContext) {
        Map<String, RedisBroadcastHandler> messageReceivers = applicationContext.getBeansOfType(RedisBroadcastHandler.class);
        if (CollectionUtils.isEmpty(messageReceivers)) {
            return;
        }
        messageReceivers.forEach((key, messageHandler) -> {
            if (!StringUtils.hasText(messageHandler.setChannel()) || !StringUtils.hasText(messageHandler.setApiNo())) {
                log.error("请设置处理广播消息的handler的Channel或者apiNo,，channel：{},apiNo:{},handler：{}", messageHandler.setChannel(), messageHandler.setApiNo(), key);
                SpringApplication.exit(applicationContext);
            }
            String mapKey = messageHandler.setChannel().concat(":").concat(messageHandler.setApiNo());
            if (handlers.containsKey(mapKey)) {
                log.error("不能存在相同的channel和aipNo handler：{}", mapKey);
                SpringApplication.exit(applicationContext);
            }
            if (messageHandler.setApiNo().equals(SocketApiNoConstants.DEFAULT_HANDLER_NAME)) {
                if (defaultHandler == null) {
                    defaultHandler = messageHandler;
                }else {
                    log.error("存在多个广播消息默认处理器，请检查");
                    SpringApplication.exit(applicationContext);
                }
            }else {
                handlers.put(mapKey, messageHandler);
            }
        });
    }

}
