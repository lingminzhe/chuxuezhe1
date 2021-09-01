package com.grgbanking.counter.app.grg_account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.app.grg_account.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行卡详细表
 * 
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Mapper
public interface AccountDao extends BaseMapper<AccountEntity> {
	
}
