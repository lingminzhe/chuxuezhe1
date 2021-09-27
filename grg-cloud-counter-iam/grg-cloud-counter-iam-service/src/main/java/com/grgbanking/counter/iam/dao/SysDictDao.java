package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.api.entity.SysDictEntity;
import com.grgbanking.counter.iam.vo.DictWithItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {

    List<DictWithItemVo> getDictAndItem();
}
