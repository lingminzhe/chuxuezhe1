package com.grgbanking.counter.app.grg_account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.app.grg_account.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联系人信息表
 * 
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {
	
}
