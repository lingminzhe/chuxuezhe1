package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgCusBusiInfoEntity;

import java.util.List;

public interface RemoteBusiInfoService {

    List<GrgCusBusiInfoEntity> findList(String customerId,String busiStatus);

    GrgCusBusiInfoEntity getOne(String id);

}
