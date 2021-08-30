package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统区域表
 *
 * @author zzwei6
 * @email
 * @date 2021年1月6日10:08:29
 */
@Mapper
public interface SysAreaDao extends BaseMapper<SysAreaEntity> {

    String getMaxAreaPath(String parentAreaPath);

    // List<SysAreaEntity> getListByUsername(@Param(("userName")) String userName);

    // List<SysAreaEntity> getListByUserId(@Param(("userId")) Long userId);


}
