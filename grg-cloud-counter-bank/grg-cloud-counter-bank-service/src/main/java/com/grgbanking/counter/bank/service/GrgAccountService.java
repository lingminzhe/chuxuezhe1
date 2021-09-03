package com.grgbanking.counter.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 银行卡详细表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:55
 */
public interface GrgAccountService extends IService<GrgAccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GrgAccountEntity> getByCustomerId(Integer id);
}

