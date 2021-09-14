package com.grgbanking.counter.csr.dubbo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grg.banking.counter.csr.api.dubbo.RemoteBusiOptService;
import com.grg.banking.counter.csr.api.entity.GrgCusBusiInfoEntity;
import com.grg.banking.counter.csr.api.entity.GrgCusBusiOptEntity;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class RemoteCusBusiOptServiceImpl implements RemoteBusiOptService {

    @Autowired
    GrgBusiOptService busiOptService;

    @Override
    public List<GrgCusBusiOptEntity> findList(String customerId) {
        QueryWrapper<GrgBusiOptEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customerId);
        List<GrgBusiOptEntity> list = busiOptService.list(wrapper);
        List<GrgCusBusiOptEntity> busiInfoEntities = new ArrayList<>(list.size());
        list.forEach(grgBusiOptEntity -> {
            GrgCusBusiOptEntity grgCusBusiOptEntity = new GrgCusBusiOptEntity();
            BeanUtils.copyProperties(grgBusiOptEntity, grgCusBusiOptEntity);
            busiInfoEntities.add(grgCusBusiOptEntity);
        });
        return busiInfoEntities;
    }

    @Override
    public GrgCusBusiOptEntity getOne(String id) {
        GrgBusiOptEntity busiOptEntity = busiOptService.getById(id);
        GrgCusBusiOptEntity grgCusBusiOptEntity = new GrgCusBusiOptEntity();
        BeanUtils.copyProperties(busiOptEntity, grgCusBusiOptEntity);
        return grgCusBusiOptEntity;
    }

}
