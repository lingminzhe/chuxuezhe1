package com.grgbanking.counter.app.grg_account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.app.grg_account.entity.AccountEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.Map;

/**
 * 银行卡详细表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AccountEntity getAccountById(AccountEntity account);

}

