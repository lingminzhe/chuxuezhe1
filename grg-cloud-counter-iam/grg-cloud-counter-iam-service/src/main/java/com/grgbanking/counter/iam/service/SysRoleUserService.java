package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysRoleUserEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lggui
 * @Date 2021年1月14日
 */
public interface SysRoleUserService extends IService<SysRoleUserEntity> {

    /**
     * 先删再加批量保存用户与角色关系
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    boolean saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据角色ID查找用户
     *
     * @param roleId
     * @return
     */
    List<SysRoleUserEntity> queryListByRoleId(Long roleId);

    /**
     * 根据角色ID查找用户
     *
     * @param roleId
     * @return
     */
    List<SysRoleUserEntity> queryListByRoleIdList(List<Long> roleId);

    /**
     * 根据角色ID查找用户数量
     *
     * @param roleId
     * @return
     */
    Long countUserByRoleId(Long roleId);


    void saveOrUpdate(List<Long> userId, Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

    /***
     * @Description 通过用户id获取该用户的所有角色
     * @param userId
     * @return
     **/
    List<SysRoleUserEntity> queryAllRoleIdByUserId(Long userId);

    /***
     * @Description 删除用户与角色关系
     * @param userIds   用户id列表
     * @return
     **/
    boolean delRoleUserByUserId(List<Long> userIds);

    /***
     * @Description 通过用户id获取该用户的所有角色
     * @param userId
     * @return
     **/
    List<SysRoleUserEntity> queryAllRoleByUserId(List<Long> userId);

    /***
     * @Description 通过用户id获取该用户的所有角色
     * @param userId
     * @return
     **/
    List<Long> queryAllRoleIdByUserId(List<Long> userId);

}
