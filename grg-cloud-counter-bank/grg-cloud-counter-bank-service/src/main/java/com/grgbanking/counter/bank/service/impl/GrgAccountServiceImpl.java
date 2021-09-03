package com.grgbanking.counter.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.bank.dao.GrgAccountDao;
import com.grgbanking.counter.bank.entity.GrgAccountEntity;
import com.grgbanking.counter.bank.service.GrgAccountService;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("grgAccountService")
public class GrgAccountServiceImpl extends ServiceImpl<GrgAccountDao, GrgAccountEntity> implements GrgAccountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgAccountEntity> page = this.page(
                new Query<GrgAccountEntity>().getPage(params),
                new QueryWrapper<GrgAccountEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<GrgAccountEntity> getByCustomerId(Integer id) {
        GrgAccountEntity entity = baseMapper.selectById(id);
        System.out.println(entity.toString());

        return null;
    }

}