package com.grgbanking.counter.app.grg_account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.app.grg_account.entity.AccountRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行卡业务操作记录表
 * 
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Mapper
public interface AccountRecordDao extends BaseMapper<AccountRecordEntity> {
	
}
