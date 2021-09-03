package com.grgbanking.counter.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.bank.dao.GrgCustomerDao;
import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import com.grgbanking.counter.bank.service.GrgCustomerService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("grgCustomerService")
public class GrgCustomerServiceImpl extends ServiceImpl<GrgCustomerDao, GrgCustomerEntity> implements GrgCustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgCustomerEntity> page = this.page(
                new Query<GrgCustomerEntity>().getPage(params),
                new QueryWrapper<GrgCustomerEntity>()
        );

        return new PageUtils(page);
    }

}