package com.grgbanking.counter.iam.service;

import com.grgbanking.counter.iam.api.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SysMenuEntity> findMenuByRoleId(Long roleId);

}
