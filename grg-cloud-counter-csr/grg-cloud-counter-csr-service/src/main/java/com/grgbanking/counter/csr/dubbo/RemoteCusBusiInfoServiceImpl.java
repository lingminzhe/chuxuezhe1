package com.grgbanking.counter.csr.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grg.banking.counter.csr.api.dubbo.RemoteBusiInfoService;
import com.grg.banking.counter.csr.api.entity.GrgCusBusiInfoEntity;
import com.grg.banking.counter.csr.api.entity.GrgCusBusiOptEntity;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行业务增删改查
 */
public class RemoteCusBusiInfoServiceImpl implements RemoteBusiInfoService {

    @Autowired
    GrgBusiInfoService grgBusiInfoService;

    @Override
    public List<GrgCusBusiInfoEntity> findList(String customerId) {
        QueryWrapper<GrgBusiInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customerId);
        List<GrgBusiInfoEntity> list = grgBusiInfoService.list(wrapper);
        List<GrgCusBusiInfoEntity> busiInfoEntities = new ArrayList<>();
        list.forEach(grgBusiInfoEntity -> {
            GrgCusBusiInfoEntity grgBusiOptEntity = new GrgCusBusiInfoEntity();
            BeanUtils.copyProperties(grgBusiInfoEntity, grgBusiOptEntity);
            busiInfoEntities.add(grgBusiOptEntity);
        });
        return busiInfoEntities;
    }

    @Override
    public GrgCusBusiInfoEntity getOne(String id) {
        GrgBusiInfoEntity busiOptEntity = grgBusiInfoService.getById(id);
        GrgCusBusiInfoEntity grgCusBusiInfoEntity = new GrgCusBusiInfoEntity();
        BeanUtils.copyProperties(busiOptEntity, grgCusBusiInfoEntity);
        return grgCusBusiInfoEntity;
    }
}
