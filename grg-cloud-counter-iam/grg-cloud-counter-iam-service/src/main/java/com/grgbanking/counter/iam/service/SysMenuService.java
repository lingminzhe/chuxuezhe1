package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysMenuEntity;
import com.grgbanking.counter.iam.api.bo.SysMenuBo;
import com.grgbanking.counter.iam.api.vo.SysMenuTreeVo;
import com.grgbanking.counter.iam.api.vo.SysMenuVo;

import java.util.List;

/**
 * 系统菜单表
 */
public interface SysMenuService extends IService<SysMenuEntity> {


    List<SysMenuVo> queryList(String appType, String name, String type, String isEnabled);

    void deleteIds(List<Long> ids);

    /**
     * 根据menuPath查询启用的子菜单
     */
    List<SysMenuEntity> getChildsByMenuPath(String menuPath, Long id);

    void updateStatusByMenuId(Long id, String isEnabled);

    /**
     * 根据用户名查询启用状态中的可分配菜单的权限标识列表
     */

    List<SysAuthorityEntity> getAuthorityListByUsername(String username);

    /**
     * 超级管理员查询启用状态中的所有菜单树
     */

    List<SysMenuTreeVo> getEnabledMenuAllTree();

    /**
     * 根据userId查询启用状态中的可分配菜单树
     */

    List<SysMenuTreeVo> getMenuLeaderTree(Long userId);

    /**
     * 根据userId查询启用状态中的可使用菜单树
     */

    List<SysMenuTreeVo> getMenuUseTree(Long userId);

    /**
     * 根据菜单id查询菜单信息
     */
    SysMenuVo queryMenuVoById(Long id);

    /**
     * 根据PARENT_CODE查询父级菜单
     */

    SysMenuEntity getParentMenuByMenuCode(String parentCode);

    /**
     * 新增菜单信息
     */
    Boolean saveMenuByBo(SysMenuBo sysMenuBo);


    /**
     * 更新菜单信息
     */
    Boolean updateMenuByBo(SysMenuBo sysMenuBo);


}

