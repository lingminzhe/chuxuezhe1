package com.grgbanking.counter.csr.redis.handler;


import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.handler.RedisBroadcastHandler;

/**
 * 消息分发抽象类，具体到每个消息处理器继承本类
 */
public abstract class RedisBroadcastAbstractHandler implements RedisBroadcastHandler {

    /**
     * 本服务监听的广播渠道，原则上一个服务只需设置一个
     * @return
     */
    @Override
    public String setChannel() {
        return RedisBroadcastConstants.BROADCAST_CHANNEL_CSR;
    }

}
