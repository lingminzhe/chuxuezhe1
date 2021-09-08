package com.grgbanking.counter.app.grg_account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.app.grg_account.entity.CustomerEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.Map;

/**
 * 联系人信息表
 *
 * @author Ye Kaitao
 * @date 2021-08-30
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

