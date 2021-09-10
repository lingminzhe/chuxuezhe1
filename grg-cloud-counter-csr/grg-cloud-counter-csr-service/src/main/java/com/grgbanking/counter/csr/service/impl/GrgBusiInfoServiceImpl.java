package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgBusiInfoDao;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("grgBusiInfoService")
public class GrgBusiInfoServiceImpl extends ServiceImpl<GrgBusiInfoDao, GrgBusiInfoEntity> implements GrgBusiInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgBusiInfoEntity> page = this.page(
                new Query<GrgBusiInfoEntity>().getPage(params),
                new QueryWrapper<GrgBusiInfoEntity>()
        );

        return new PageUtils(page);
    }

}