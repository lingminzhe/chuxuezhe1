package com.grgbanking.counter.app.socket;

import com.grgbanking.counter.device.socket.RegisterSocketHandler;
import com.grgbanking.counter.device.socket.SocketHandler;
import com.grgbanking.counter.device.socket.VideoCmdSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SocketHandlerFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public SocketHandler findHandler(String serviceType) {
        switch (serviceType){
            case "register":
                return applicationContext.getBean(RegisterSocketHandler.class);
            case "video_cmd":
                return applicationContext.getBean(VideoCmdSocketHandler.class);
            default:
                break;
        }
        return null;
    }
}
