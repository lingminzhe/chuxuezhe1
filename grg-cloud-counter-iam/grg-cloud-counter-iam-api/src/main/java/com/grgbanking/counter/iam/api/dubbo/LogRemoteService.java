package com.grgbanking.counter.iam.api.dubbo;


import com.grgbanking.counter.iam.api.bo.SysLogBo;

public interface LogRemoteService {

    void save(SysLogBo sysLogBo);

}
