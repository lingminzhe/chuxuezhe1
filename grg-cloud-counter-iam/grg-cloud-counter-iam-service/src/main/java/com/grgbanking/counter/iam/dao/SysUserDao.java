package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.api.bo.SysUserQueryBo;
import com.grgbanking.counter.iam.api.vo.SysUserQueryVo;
import com.grgbanking.counter.iam.api.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户
 *
 * @author MARK lggui1
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    IPage<SysUserQueryVo> listPage(Page page, @Param("vo") SysUserQueryBo vo, @Param("orgPath") String orgPath, @Param("userId") Long userId, @Param("userType") String userType);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户ID查询所有可使用\可分配的启用的权限标识
     */
    List<String> queryAllAuthority(@Param("userId")Long userId,@Param("isEnabled") String isEnabled,@Param("isLeader")String isLeader);

    /**
     * 根据用户ID查询启用状态中的可分配/可使用菜单的权限标识列表
     */
    List<SysAuthorityEntity> queryAuthorityListByUserId(@Param("userId")Long userId, @Param("isEnabled") String isEnabled, @Param("isLeader")String isLeader);

    /**
     * 更新用户信息
     */
    Integer updateUser(@Param("user") SysUserEntity user);

    /***
     * @Description
     * @param user
     * @return
     **/
    Integer restPassword(@Param("user") SysUserEntity user);


    /***
     * @Description 根据机构id查询机构下的所属用户
     * @param orgId       机构id
     * @param isLeader       0所属用户，1管理用户
     * @param status 停用启用状态
     * @return
     **/
    List<SysUserVo> getAuthMessageUsers(@Param("orgId") Long orgId, @Param("isLeader") String isLeader, @Param("status") String status);
}
