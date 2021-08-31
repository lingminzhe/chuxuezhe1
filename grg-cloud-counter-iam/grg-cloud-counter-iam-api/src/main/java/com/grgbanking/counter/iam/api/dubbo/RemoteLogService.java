package com.grgbanking.counter.iam.api.dubbo;


import com.grgbanking.counter.iam.api.dto.SysLogDTO;

public interface RemoteLogService {

    void save(SysLogDTO sysLogBo);

}
