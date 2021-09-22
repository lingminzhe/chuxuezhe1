package com.grgbanking.counter.csr.redis;

import com.grgbanking.counter.csr.redis.AppRedisReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Autowired
    AppRedisReceiver appMsgReceiver;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 可以添加多个 messageListener，配置不同的交换机
        container.addMessageListener(appMsgReceiver,  PatternTopic.of("csr:video_cmd"));
 //       container.addMessageListener(appMsgReceiver,  PatternTopic.of("csr:video"));
 //       container.addMessageListener(appMsgReceiver,  PatternTopic.of("csr:video"));
        return container;
    }

}
