package com.grgbanking.counter.csr.socket;

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
            case "modify_info":
                return applicationContext.getBean(ModifyInfoSocketHandler.class);
            case "face_recognition":
                return applicationContext.getBean(FaceRecognitionSocketHandler.class);
            default:
                break;
        }
        return null;
    }
}
