package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgEmployeeServiceDao;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeServiceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("grgEmployeeServiceService")
public class GrgEmployeeServiceServiceImpl extends ServiceImpl<GrgEmployeeServiceDao, GrgEmployeeServiceEntity> implements GrgEmployeeServiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgEmployeeServiceEntity> page = this.page(
                new Query<GrgEmployeeServiceEntity>().getPage(params),
                new QueryWrapper<GrgEmployeeServiceEntity>()
        );

        return new PageUtils(page);
    }

}