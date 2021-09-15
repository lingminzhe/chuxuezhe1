package com.grgbanking.counter.csr.dubbo;

import com.grgbanking.counter.csr.api.dubbo.RemoteCusBusiService;
import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteCusBusiServiceImpl implements RemoteCusBusiService {

    @Autowired
    GrgEmployeeService grgEmployeeService;

    @Override
    public GrgCusEmployeeServiceEntity getEmployeeService(String userId) {
        GrgEmployeeServiceEntity freeEmployee = grgEmployeeService.getFreeEmployee(userId);
        GrgCusEmployeeServiceEntity grgCusEmployeeServiceEntity = null;
        if (freeEmployee != null){
            grgCusEmployeeServiceEntity = new GrgCusEmployeeServiceEntity();
            BeanUtils.copyProperties(freeEmployee, grgCusEmployeeServiceEntity);
        }
        return grgCusEmployeeServiceEntity;
    }

}
