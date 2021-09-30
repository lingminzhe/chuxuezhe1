package com.grgbanking.counter.common.socket.broadcast.config;

import com.grgbanking.counter.common.socket.broadcast.component.RedisBroadcastMessageReceiver;
import com.grgbanking.counter.common.socket.broadcast.handler.RedisBroadcastHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis广播监听注册配置
 */
@Slf4j
@Configuration
public class RedisBroadcastConfig implements ApplicationContextAware {

    @Autowired
    private RedisBroadcastMessageReceiver redisBroadcastMessageReceiver;

    private Set<String> channels = new HashSet<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        collectBroadcastChannels(applicationContext);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        channels.forEach(channel -> {
            container.addMessageListener(redisBroadcastMessageReceiver, new PatternTopic(channel));
        });
        return container;
    }

    /**
     * 收集Redis广播消息处理的handlers
     * @param applicationContext
     */
    private void collectBroadcastChannels(ApplicationContext applicationContext){
        Map<String, RedisBroadcastHandler> messageReceivers = applicationContext.getBeansOfType(RedisBroadcastHandler.class);
        if (CollectionUtils.isEmpty(messageReceivers)) {
            return;
        }
        messageReceivers.forEach((key, messageHandler) -> {
            if(StringUtils.hasText(messageHandler.setChannel())) {
                channels.add(messageHandler.setChannel());
            }
        });
    }

}
