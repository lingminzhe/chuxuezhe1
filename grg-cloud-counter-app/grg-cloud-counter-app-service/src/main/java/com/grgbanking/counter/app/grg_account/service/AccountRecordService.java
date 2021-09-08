package com.grgbanking.counter.app.grg_account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.app.grg_account.entity.AccountRecordEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.Map;

/**
 * 银行卡业务操作记录表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
public interface AccountRecordService extends IService<AccountRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

