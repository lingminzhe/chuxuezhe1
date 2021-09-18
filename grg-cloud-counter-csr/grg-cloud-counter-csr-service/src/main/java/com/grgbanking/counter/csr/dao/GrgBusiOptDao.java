package com.grgbanking.counter.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 业务操作流水表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@Mapper
public interface GrgBusiOptDao extends BaseMapper<GrgBusiOptEntity> {

    String selectMaxOptNo();

    String getTodayBusiOptNum(@Param("employeeId") String employeeId);

    String getTodayCallOptNum(@Param("employeeId") String employeeId);

    String getMonthBusiOptNum(@Param("employeeId") String employeeId);

    String getMonthCallOptNum(@Param("employeeId") String employeeId);
}
