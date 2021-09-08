package com.grgbanking.counter.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行卡业务操作记录表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@Mapper
public interface GrgAccountRecordDao extends BaseMapper<GrgAccountRecordEntity> {
	
}
