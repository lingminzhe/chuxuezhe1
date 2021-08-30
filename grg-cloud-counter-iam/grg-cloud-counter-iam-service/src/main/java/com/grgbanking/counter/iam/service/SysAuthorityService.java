package com.grgbanking.counter.iam.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.iam.api.bo.SysAuthorityBo;

import java.util.List;
import java.util.Map;

/**
 * 权限标识
 */
public interface SysAuthorityService extends IService<SysAuthorityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage queryPageList(Page page, SysAuthorityBo sysAuthorityBo);

    IPage queryEnabledPageList(Page page, SysAuthorityBo sysAuthorityBo);

    void saveAuthorityByBo(SysAuthorityBo sysAuthorityBo);

    void updateAuthByBo(SysAuthorityBo sysAuthorityBo);

    void deleteByIds(List<Long> id);

    void updateToStatus(Long id, String isEnabled);

    /**
     * 根据ID列表查询权限标识拼接字符串
     */
    String getAuthorityToString(List<Long> ids);

    /**
     * 根据ID列表查询权限标识
     */
    List<String> getAuthorityList(List<Long> ids);

    /**
     * 根据用户id查询用户权限数据（关联菜单表来反查权限）
     *
     * @param userId
     * @param isEnabled
     * @return
     */
    List<SysAuthorityEntity> getAuthorityByUserId(Long userId, String isEnabled);
}

