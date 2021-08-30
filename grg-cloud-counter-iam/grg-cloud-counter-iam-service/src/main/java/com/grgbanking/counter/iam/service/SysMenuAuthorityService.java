package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysMenuAuthorityEntity;
import com.grgbanking.counter.iam.api.bo.SysMenuAuthorityBo;

import java.util.List;

public interface SysMenuAuthorityService extends IService<SysMenuAuthorityEntity> {

    /**
     * 添加
     *
     * @param sysMenuAuthorityBo
     */
    void add(SysMenuAuthorityBo sysMenuAuthorityBo);

    /**
     * 条件查找单个实体
     *
     * @param id
     * @return 查询结果(SysMenuAuthorityEntity)
     */
    SysMenuAuthorityEntity getById(Long id);

    /**
     * 根据menuId删除列表
     *
     * @param menuId
     * @return
     */

    void deleteAuthorityByMenuId(Long menuId);

    /**
     * 根据menuId集合删除列表
     *
     * @param menuIdList
     * @return
     */

    void deleteAuthorityByMenuIds(List<Long> menuIdList);

    List<SysMenuAuthorityEntity> queryListByAuthorityId(Long authorityId);

}
