package com.grgbanking.counter.iam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.OauthClientDetailsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统客户端资源列表
 * 
 */
@Mapper
public interface OauthClientDetailsDao extends BaseMapper<OauthClientDetailsEntity> {
	
}
