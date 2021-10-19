package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.api.entity.SysDictItemEntity;

import java.util.List;

/**
 * <p>
 * 字典项 服务类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
public interface SysDictItemService extends IService<SysDictItemEntity> {

    /**
     * 根据字典type 找到对应的字典列表
     * @param type
     * @return
     */
    List<SysDictItemEntity> getDictItemByType(String type);
}
