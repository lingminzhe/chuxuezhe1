package com.grgbanking.counter.iam.dubbo;


import com.grgbanking.counter.iam.api.bo.SysLogBo;
import com.grgbanking.counter.iam.api.dubbo.LogRemoteService;
import com.grgbanking.counter.iam.service.SysLogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class LogRemoteServiceImpl implements LogRemoteService {


    @Autowired
    private SysLogService sysLogService;

    @Override
    public void save(SysLogBo sysLogBo) {
        sysLogService.saveSysLog(sysLogBo);
    }

}
