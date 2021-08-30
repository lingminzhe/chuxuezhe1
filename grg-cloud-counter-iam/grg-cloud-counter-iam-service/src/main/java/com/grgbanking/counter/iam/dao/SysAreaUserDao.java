package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysAreaUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统区域用户关系表
 * 
 */
@Mapper
public interface SysAreaUserDao extends BaseMapper<SysAreaUserEntity> {
	
}
