package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.api.entity.SysSocialAuthUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 客户社交账户认证信息表 Mapper 接口
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-03
 */
@Mapper
public interface SysSocialAuthUserDao extends BaseMapper<SysSocialAuthUserEntity> {

}
