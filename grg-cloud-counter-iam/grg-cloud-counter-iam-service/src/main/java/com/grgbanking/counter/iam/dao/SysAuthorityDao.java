package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限标识
 */
@Mapper
public interface SysAuthorityDao extends BaseMapper<SysAuthorityEntity> {

    /**
     * 根据用户id查询用户权限数据（关联菜单表来反查权限）
     *
     * @param userId    userId
     * @param isEnabled 菜单状态
     * @return
     */
    List<SysAuthorityEntity> getAuthorityByUserId(@Param("userId") Long userId, @Param("isEnabled") String isEnabled);

}
