package com.grgbanking.counter.iam.dubbo;


import com.grgbanking.counter.iam.api.dto.SysLogDTO;
import com.grgbanking.counter.iam.api.dubbo.RemoteLogService;
import com.grgbanking.counter.iam.api.entity.SysLogEntity;
import com.grgbanking.counter.iam.service.SysLogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteLogServiceImpl implements RemoteLogService {


    @Autowired
    private SysLogService sysLogService;

    @Override
    public void save(SysLogDTO sysLogDTO) {
        SysLogEntity entity = new SysLogEntity();
        BeanUtils.copyProperties(sysLogDTO,entity);
        sysLogService.save(entity);
    }

}
