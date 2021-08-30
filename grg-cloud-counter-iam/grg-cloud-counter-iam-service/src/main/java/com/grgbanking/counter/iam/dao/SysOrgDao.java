package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryBo;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryListBo;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOrgDao extends BaseMapper<SysOrgEntity> {
    /***
     * @Description 根据父级的path子及path
     * @param parentPath
     * @return
     **/
    String getOrgPathsByParent(@Param("path") String parentPath);

    /***
     * @Description 根据父级的path子及path
     * @param newPath  新的父级头
     * @param oldPath  旧的的父级头
     * @return
     **/
    Integer updateChildPath(@Param("oldPath") String oldPath, @Param("newPath") String newPath);

    /**
     * 可以根据orgPath查询子级
     *
     * @param orgPath
     * @param isEnabled 状态 可以null
     * @param userId    用户id 可以null
     * @return
     */
    List<SysOrgEntity> childList(@Param(("orgPath")) String orgPath, @Param(("isEnabled")) String isEnabled, @Param(("userId")) Long userId);

    /**
     * 超级管理员根据orgPath查询子级
     *
     * @param orgPath
     * @param isEnabled 状态 可以null
     * @param
     * @return
     */
    List<SysOrgEntity> childListForAdmin(@Param(("orgPath")) String orgPath, @Param(("isEnabled")) String isEnabled);

    /**
     * 根据机构列表和参数查询机构信息
     *
     * @return
     */
    List<SysOrgEntity> querByOrglist(@Param("org") SysOrgQueryListBo org, @Param("userId") Long userId, @Param("isLeader") String isLeader);

    //当前用户所在组织Ids

    /***
     * @Description
     * @param userId 用户id
     * @param isLeader  是否负责人
     * @return
     **/
    List<String> queryOrgPathsByUserId(@Param("userId") Long userId, @Param("isLeader") String isLeader);

    /***
     * @Description 根据机构查询实体查询组织机构列表
     * @param bo
     * @return
     **/
    List<SysOrgEntity> queryOrgList(@Param("bo") SysOrgQueryBo bo);

}
