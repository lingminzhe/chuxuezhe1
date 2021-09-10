package com.grgbanking.counter.app.socket;

import com.grgbanking.counter.app.business.BusinessHandler;
import com.grgbanking.counter.app.business.VideoBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BusinessFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public  BusinessHandler findHandler(String serviceType) {
        switch (serviceType){
            case "video":
                return applicationContext.getBean(VideoBusiness.class);
            default:
                break;
        }
        return null;
    }
}
