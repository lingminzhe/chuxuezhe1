package com.grgbanking.counter.iam.dao;

import com.grgbanking.counter.iam.api.entity.SysFileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件管理表 Mapper 接口
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Mapper
public interface SysFileDao extends BaseMapper<SysFileEntity> {

}
