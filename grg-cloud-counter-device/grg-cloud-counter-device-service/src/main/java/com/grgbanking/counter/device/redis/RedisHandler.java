package com.grgbanking.counter.device.redis;

import com.grgbanking.counter.common.core.util.SocketParam;

public interface RedisHandler {
    void execute(SocketParam param);
}
