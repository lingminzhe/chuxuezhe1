package com.grgbanking.counter.app.socket;

import com.grgbanking.counter.common.core.util.SocketParam;

public interface SocketHandler {
    void execute(SocketParam param, String fromClientId);
}
