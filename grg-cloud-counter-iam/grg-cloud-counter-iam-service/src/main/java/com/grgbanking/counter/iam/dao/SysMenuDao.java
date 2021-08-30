package com.grgbanking.counter.iam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.api.vo.SysMenuVo;
import com.grgbanking.counter.iam.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单表
 *
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    String getMenuPathsByParent(@Param("path") String parentPath);

    List<SysMenuVo> getAddAuthorityList(@Param("appType")String appType, @Param("name") String name, @Param("type") String type, @Param("isEnabled") String isEnabled);

    /**
     * 根据用户ID查询(可分配/可使用)菜单
     * */
    List<SysMenuEntity> getMenuListByUserId(@Param("userId")Long userId,@Param("isEnabled")String isEnabled,@Param("isLeader")String isLeader);


}
