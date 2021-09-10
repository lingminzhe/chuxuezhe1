package com.grgbanking.counter.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;

import java.util.Map;

/**
 * 业务操作流水表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
public interface GrgBusiOptService extends IService<GrgBusiOptEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

