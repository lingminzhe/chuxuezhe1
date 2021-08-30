package com.grgbanking.counter.iam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志
 *
 * @author MARK xx@grgbanking.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

    Long getPageCount(@Param("username")String username,@Param("ip") String ip,@Param("startDate") String startDate,@Param("endDate") String endDate);

    List<SysLogEntity> getPageList(@Param("username")String username,@Param("ip") String ip,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("startIndex") Long startIndex,@Param("endIndex") Long endIndex,@Param("pageSize") Long pageSize);

}
