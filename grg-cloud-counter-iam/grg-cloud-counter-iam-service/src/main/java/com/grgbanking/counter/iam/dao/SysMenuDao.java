package com.grgbanking.counter.iam.dao;

import com.grgbanking.counter.iam.api.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 通过角色编号查询菜单
     * @param roleId 角色ID
     * @return
     */
    List<SysMenuEntity> listMenusByRoleId(Long roleId);

}
