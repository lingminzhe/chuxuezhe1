package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgCusEmployeeServiceEntity;

public interface RemoteCusBusiService {

    GrgCusEmployeeServiceEntity getEmployeeService(String userId);

}
