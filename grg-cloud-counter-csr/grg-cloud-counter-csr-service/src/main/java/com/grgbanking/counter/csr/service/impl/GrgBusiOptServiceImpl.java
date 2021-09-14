package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgBusiOptDao;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("grgBusiOptService")
public class GrgBusiOptServiceImpl extends ServiceImpl<GrgBusiOptDao, GrgBusiOptEntity> implements GrgBusiOptService {

    @Autowired
    private GrgBusiInfoService busiInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgBusiOptEntity> page = this.page(
                new Query<GrgBusiOptEntity>().getPage(params),
                new QueryWrapper<GrgBusiOptEntity>()
        );

        return new PageUtils(page);
    }

    /**
     *  id,busi_no,user_id,customer_id,busi_opt_status,busi_opt_no
     * @param grgBusiOpt
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBusiOpt(GrgBusiOptEntity grgBusiOpt) {
        //业务操作流水号busi_opt_no

        this.save(grgBusiOpt);
    }

}