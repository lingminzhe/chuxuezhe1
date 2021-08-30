package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysRoleEntity;
import com.grgbanking.counter.iam.api.bo.SysRoleListBo;
import com.grgbanking.counter.iam.api.bo.SysRoleSaveBo;
import com.grgbanking.counter.iam.api.vo.SysRoleVo;

import java.util.List;

/**
 * 角色
 *
 * @author MARK lggui1@grgbanking.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /***
     * @Description 根据角色名称、角色类型、描述信息、启用状态查询角色列表
     * @param page
     * @param sysRoleListBo
     * @return
     **/
    IPage<SysRoleVo> queryPage(Page page, SysRoleListBo sysRoleListBo);

    /**
     * 新增角色
     *
     * @param sysRoleSaveBo
     */
    void saveOrUpdate(SysRoleSaveBo sysRoleSaveBo);

    /***
     * @Description 通过id集合批量查找
     * @param roleIds
     * @return
     **/
    List<SysRoleEntity> queryRoleIdList(List roleIds);

    /***
     * @Description 批量删除角色
     * @param roleIds
     * @return
     **/
    Boolean deleteBatch(List roleIds);

    Long countByName(String roleName, Long roleId);

    /***
     * @Description 停用启用
     * @param id
     * @param status
     * @return
     **/
    Integer updateStatus(Long id, String status);

    /***
     * @Description 根据角色id查询角色信息
     * @param id
     * @return
     **/
    SysRoleVo queryOne(Long id);

    /**
     * 通过用户帐号获取用户角色
     *
     * @param username
     * @return
     */
    List<SysRoleEntity> getUserRoleListByUsername(String username);

    //管理员查所有角色
    List<SysRoleEntity> getUserRoleListByAdmin();

}
