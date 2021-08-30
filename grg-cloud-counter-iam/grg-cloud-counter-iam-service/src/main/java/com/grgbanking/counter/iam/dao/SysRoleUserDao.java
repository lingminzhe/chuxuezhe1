package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysRoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 *
 * @author MARK xx@grgbanking.com
 */
@Mapper
public interface SysRoleUserDao extends BaseMapper<SysRoleUserEntity> {

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
