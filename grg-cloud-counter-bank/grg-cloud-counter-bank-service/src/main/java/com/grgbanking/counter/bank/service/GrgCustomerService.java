package com.grgbanking.counter.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.Map;

/**
 * 客户详情表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
public interface GrgCustomerService extends IService<GrgCustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

