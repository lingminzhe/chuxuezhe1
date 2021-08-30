package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysI18nEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统多语言维护信息表
 */
@Mapper
public interface SysI18nDao extends BaseMapper<SysI18nEntity> {

}
