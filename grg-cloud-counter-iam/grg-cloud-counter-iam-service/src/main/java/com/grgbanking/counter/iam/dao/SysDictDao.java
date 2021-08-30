package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典表
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {

}
