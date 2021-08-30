package com.grgbanking.counter.iam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.api.vo.SysBizConfVo;
import com.grgbanking.counter.iam.entity.SysBizConfEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 系统业务配置表
 * 
 */
@Mapper
public interface SysBizConfDao extends BaseMapper<SysBizConfEntity> {

    List<SysBizConfVo> getPanelBizInfoList(@Param("appType")String appType, @Param("bizNo")String bizNo);

}
