package com.grgbanking.counter.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
public interface GrgBusiInfoService extends IService<GrgBusiInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

