package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgBusiOptDao;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("grgBusiOptService")
public class GrgBusiOptServiceImpl extends ServiceImpl<GrgBusiOptDao, GrgBusiOptEntity> implements GrgBusiOptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgBusiOptEntity> page = this.page(
                new Query<GrgBusiOptEntity>().getPage(params),
                new QueryWrapper<GrgBusiOptEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBusiOpt(GrgBusiOptEntity grgBusiOpt) {

        this.save(grgBusiOpt);
    }

}