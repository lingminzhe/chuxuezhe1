package com.grgbanking.counter.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件管理表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09 09:12:51
 */
@Mapper
public interface GrgFileManagerDao extends BaseMapper<GrgFileManagerEntity> {

    GrgFileManagerEntity getByCustomerId(String customerId);
}
