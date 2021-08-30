package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.api.bo.SysUserBo;
import com.grgbanking.counter.iam.api.bo.SysUserPasswordBo;
import com.grgbanking.counter.iam.api.bo.SysUserQueryBo;
import com.grgbanking.counter.iam.api.bo.SysUserUpdateBo;
import com.grgbanking.counter.iam.api.vo.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author lggui1
 * @Date 2021/1/12
 * @Description 用户业务接口
 **/
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 更新用户启用停用状态
     *
     * @param username
     * @param enable   可以为空，空则用数据库数据取反
     * @return
     */
    Integer status(String username, String enable);

    /**
     * 操作redis解除锁定
     *
     * @param username
     * @return
     */
    boolean unlockCache(String username);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUserEntity getUserByUsername(String username);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    SysUserEntity getUserById(Long id);

    /**
     * 根据ID集合查询用户
     *
     * @param ids
     * @return
     */
    List<SysUserEntity> getUserByIds(List<Long> ids);

    /**
     * 新增用户
     *
     * @param vo
     * @return
     */
    boolean save(SysUserBo vo);

    /**
     * 修改用户信息
     *
     * @param vo
     * @return
     */
    boolean update(SysUserUpdateBo vo);

    /**
     * 重置密码
     *
     * @param username
     * @return
     */
    boolean resetPassword(String username);

    /***
     * @Description 修改密码
     * @param sysUserPasswordBo
     * @return
     **/
    boolean updatePassword(SysUserPasswordBo sysUserPasswordBo);

    /***
     * @Description 分页查询机构下的用户
     * @param page
     * @param bo
     * @return
     **/
    IPage<SysUserQueryVo> queryPage(Page page, SysUserQueryBo bo, Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /***
     * @Description 1.当前删除人不能删除所属机构相同的用户
     * 删除SYS_USER
     * 删除SYS_ORG_USER
     * 删除SYS_AREA_USER
     * 删除SYS_ROLE_USER
     * @param userIds
     * @return
     **/
    boolean delUser(List<Long> userIds);

    /***
     * @Description 根据用户账号查询用户信息
     * @param username
     * @return
     **/
    SysUserInfoVo getUserInfoByUsername(String username);

    /**
     * 根据用户账号获取导航菜单和可使用的权限标识
     */
    SysNavVo nav(String username);

    /**
     * 判断是否超管角色
     */
    boolean isSuperUser(Long userId);

    /**
     * 判断是否超管角色
     */
    boolean isSuperUser(List<Long> userIds);

    /**
     * 根据用户ID查询启用状态中的可分配菜单的权限标识列表
     */
    List<SysAuthorityEntity> queryAuthorityListByUserId(Long userId, String isEnabled, String isLeader);

}
