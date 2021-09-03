package com.grgbanking.counter.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.bank.entity.GrgCustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户详情表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@Mapper
public interface GrgCustomerDao extends BaseMapper<GrgCustomerEntity> {
	
}
