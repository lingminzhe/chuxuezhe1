package com.grg.banking.counter.csr.api.dubbo;

import com.grg.banking.counter.csr.api.entity.GrgCusBusiOptEntity;

import java.util.List;

public interface RemoteBusiOptService {

    List<GrgCusBusiOptEntity> findList(String customerId);

    GrgCusBusiOptEntity getOne(String id);

}
