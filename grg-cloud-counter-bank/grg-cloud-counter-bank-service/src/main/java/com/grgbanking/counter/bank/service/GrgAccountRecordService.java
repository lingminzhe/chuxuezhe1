package com.grgbanking.counter.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 银行卡业务操作记录表
 *
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
public interface GrgAccountRecordService extends IService<GrgAccountRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GrgAccountRecordEntity> getByCustomerId(Integer id);
}

