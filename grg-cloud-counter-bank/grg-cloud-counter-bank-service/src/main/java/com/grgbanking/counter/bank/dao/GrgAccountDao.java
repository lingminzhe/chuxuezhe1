package com.grgbanking.counter.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行卡详细表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:55
 */
@Mapper
public interface GrgAccountDao extends BaseMapper<GrgAccountEntity> {
	
}
