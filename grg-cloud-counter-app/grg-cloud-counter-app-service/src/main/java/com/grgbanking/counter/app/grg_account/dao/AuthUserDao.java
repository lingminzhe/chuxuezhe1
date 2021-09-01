package com.grgbanking.counter.app.grg_account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.app.grg_account.entity.AuthUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户认证信息表
 * 
 * @author Ye Kaitao
 * @email ${email}
 * @date 2021-09-01 09:08:03
 */
@Mapper
public interface AuthUserDao extends BaseMapper<AuthUserEntity> {
	
}
