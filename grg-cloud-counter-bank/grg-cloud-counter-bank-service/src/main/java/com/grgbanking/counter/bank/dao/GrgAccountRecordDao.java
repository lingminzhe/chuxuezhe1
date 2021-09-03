package com.grgbanking.counter.bank.dao;

import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 银行卡业务操作记录表 Mapper 接口
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-02
 */
@Mapper
public interface GrgAccountRecordDao extends BaseMapper<GrgAccountRecordEntity> {

}
