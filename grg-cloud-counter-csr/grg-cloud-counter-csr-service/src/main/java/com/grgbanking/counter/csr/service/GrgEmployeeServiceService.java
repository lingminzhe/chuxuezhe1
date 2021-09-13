package com.grgbanking.counter.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;

import java.util.Map;

/**
 * 坐席客服表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13 10:55:49
 */
public interface GrgEmployeeServiceService extends IService<GrgEmployeeServiceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

