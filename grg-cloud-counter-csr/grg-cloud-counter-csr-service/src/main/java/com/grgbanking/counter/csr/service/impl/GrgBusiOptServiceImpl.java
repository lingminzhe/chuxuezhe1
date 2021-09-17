package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    GrgBusiOptDao busiOptDao;

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
    public synchronized void saveBusiOpt(GrgBusiOptEntity grgBusiOpt) {
        //业务操作流水号busi_opt_no
        if (grgBusiOpt.getBusiOptNo()==null){
            //设置流水号
            String s = busiOptDao.selectMaxOptNo();
            Integer c = Integer.parseInt(s)+1;
            grgBusiOpt.setBusiOptNo(c.toString());
        }
        if (grgBusiOpt.getBusiOptStatus()==null){
            //(1、已完成  2、正在进行 3、未完成
            grgBusiOpt.setBusiOptStatus("2");
        }
        this.save(grgBusiOpt);
    }

    @Override
    public void updateByBusiOptNo(GrgBusiOptEntity grgBusiOpt) {
        UpdateWrapper<GrgBusiOptEntity> wrapper = new UpdateWrapper<GrgBusiOptEntity>().eq("busi_opt_no", grgBusiOpt.getBusiOptNo());

        this.baseMapper.update(grgBusiOpt,wrapper);

    }

}