package com.grgbanking.counter.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.bank.dao.GrgAccountRecordDao;
import com.grgbanking.counter.bank.entity.GrgAccountRecordEntity;
import com.grgbanking.counter.bank.service.GrgAccountRecordService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("grgAccountRecordService")
public class GrgAccountRecordServiceImpl extends ServiceImpl<GrgAccountRecordDao, GrgAccountRecordEntity> implements GrgAccountRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgAccountRecordEntity> page = this.page(
                new Query<GrgAccountRecordEntity>().getPage(params),
                new QueryWrapper<GrgAccountRecordEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据传入的Account ID  查找相关的流水记录
     * @param id
     * @return
     */
    @Override
    public List<GrgAccountRecordEntity> getByCustomerId(Integer id) {

        List<GrgAccountRecordEntity> entity = baseMapper.selectList(new QueryWrapper<GrgAccountRecordEntity>().eq("account_id",id));

        return entity;

    }

}