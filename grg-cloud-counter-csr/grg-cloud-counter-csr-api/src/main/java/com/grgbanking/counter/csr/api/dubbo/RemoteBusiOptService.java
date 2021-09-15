package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgCusBusiOptEntity;

import java.util.List;

public interface RemoteBusiOptService {

    List<GrgCusBusiOptEntity> findList(String customerId);

    GrgCusBusiOptEntity getOne(String id);

}
