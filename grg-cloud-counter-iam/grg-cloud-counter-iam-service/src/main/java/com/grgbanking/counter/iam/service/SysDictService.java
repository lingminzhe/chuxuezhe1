package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.iam.api.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
public interface SysDictService extends IService<SysDictEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<Map<String, Map<String, String>>> listDictWithItem();
}
