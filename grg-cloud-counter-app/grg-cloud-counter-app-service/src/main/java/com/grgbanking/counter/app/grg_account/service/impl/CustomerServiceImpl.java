package com.grgbanking.counter.app.grg_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.app.grg_account.dao.CustomerDao;
import com.grgbanking.counter.app.grg_account.entity.CustomerEntity;
import com.grgbanking.counter.app.grg_account.service.CustomerService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("customerInfoService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }

}