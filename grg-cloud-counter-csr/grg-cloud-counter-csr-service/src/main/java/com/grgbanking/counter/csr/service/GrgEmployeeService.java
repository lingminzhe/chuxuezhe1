package com.grgbanking.counter.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 坐席客服表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13 10:55:49
 */
public interface GrgEmployeeService extends IService<GrgEmployeeServiceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    GrgEmployeeServiceEntity getByEmployeeId(String id);

    List<GrgEmployeeServiceEntity> getAllFreeEmployee();

    GrgEmployeeServiceEntity getFreeEmployee(String userId);
}

