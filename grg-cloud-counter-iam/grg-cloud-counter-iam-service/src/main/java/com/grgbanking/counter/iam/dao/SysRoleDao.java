package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色管理
 *
 * @author MARK xx@grgbanking.com
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<SysRoleEntity> queryRoleIdList(String createUserId);

    /**
     * 查找当前用户有权限查看的用户又是是属于该角色的用户
     *
     * @param userId
     * @return
     */
    List<Long> queryRoleUserIdByCurrentUser(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
