package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author MARK xx@grgbanking.com
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * @param roleId
     * @param menuIdList
     * @param isLeader   是否负责人 1是，0否
     * @return
     * @Description 新增或更新角色菜单都先删除
     * @author lggui1
     **/
    void saveOrUpdate(Long roleId, List<Long> menuIdList, String isLeader);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId, String isLeader);


    /**
     * 根据菜单ID，获取SYS_ROLE_MENU集合
     */
    List<SysRoleMenuEntity> getListByMenuId(Long menuId);

    /***
     * @Description
     * @param roleId 角色ID
     * @param isLeader 是否负责人
     * @return
     **/
    int deleteBatch(Long roleId, String isLeader);

    /**
     * 根据角色ID数组，删除
     */
    int deleteBatch(List<Long> roleId);


}
