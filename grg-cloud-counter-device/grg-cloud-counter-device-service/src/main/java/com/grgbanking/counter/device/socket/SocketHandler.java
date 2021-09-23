package com.grgbanking.counter.device.socket;

import com.grgbanking.counter.common.core.util.SocketParam;

public interface SocketHandler {
    void execute(SocketParam param, String fromClientId);
}
