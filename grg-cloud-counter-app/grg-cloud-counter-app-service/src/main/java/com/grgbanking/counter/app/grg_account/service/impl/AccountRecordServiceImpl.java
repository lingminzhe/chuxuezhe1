package com.grgbanking.counter.app.grg_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.app.grg_account.dao.AccountRecordDao;
import com.grgbanking.counter.app.grg_account.entity.AccountRecordEntity;
import com.grgbanking.counter.app.grg_account.service.AccountRecordService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accountInfoDetailsService")
public class AccountRecordServiceImpl extends ServiceImpl<AccountRecordDao, AccountRecordEntity> implements AccountRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountRecordEntity> page = this.page(
                new Query<AccountRecordEntity>().getPage(params),
                new QueryWrapper<AccountRecordEntity>()
        );

        return new PageUtils(page);
    }

}