package com.grg.banking.counter.csr.api.dubbo;

import com.grg.banking.counter.csr.api.entity.GrgCusBusiInfoEntity;

import java.util.List;

public interface RemoteBusiInfoService {

    List<GrgCusBusiInfoEntity> findList(String customerId);

    GrgCusBusiInfoEntity getOne(String id);

}
