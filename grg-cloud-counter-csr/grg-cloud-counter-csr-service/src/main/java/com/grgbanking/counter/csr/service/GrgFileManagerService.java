package com.grgbanking.counter.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;

import java.util.Map;

/**
 * 附件管理表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
public interface GrgFileManagerService extends IService<GrgFileManagerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    GrgFileManagerEntity getByCustomerId(String customerId);
}

