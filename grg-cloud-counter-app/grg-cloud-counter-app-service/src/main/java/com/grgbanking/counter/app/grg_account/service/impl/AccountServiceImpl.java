package com.grgbanking.counter.app.grg_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.app.grg_account.dao.AccountDao;
import com.grgbanking.counter.app.grg_account.entity.AccountEntity;
import com.grgbanking.counter.app.grg_account.service.AccountService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountEntity> page = this.page(
                new Query<AccountEntity>().getPage(params),
                new QueryWrapper<AccountEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据客户id查找对应的银行卡账户信息
     * @param account
     * @return
     */
    @Override
    public AccountEntity getAccountById(AccountEntity account) {

        if (account == null){
            log.warn("Account is null");
            return null;
        }
        //获取客户id
        Integer customerId = account.getCustomerId();
        AccountEntity infoEntity = getById(customerId);

        if (infoEntity == null){
            log.error("infoEntity is null");
            return null;
        }

        return infoEntity;
    }

}