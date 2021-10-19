package com.grgbanking.counter.csr.api.dubbo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.csr.api.entity.GrgCusBusiInfoEntity;

import java.util.List;

/**
 * @author yekaitao
 */
public interface RemoteBusiInfoService extends IService<GrgCusBusiInfoEntity> {

    List<GrgCusBusiInfoEntity> findList(String customerId,String busiStatus);

    GrgCusBusiInfoEntity getOne(String id);

    String getBusiNameByNo(String busiNo);
}
