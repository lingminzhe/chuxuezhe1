package com.grgbanking.counter.csr.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.csr.api.dubbo.RemoteBusiInfoService;
import com.grgbanking.counter.csr.api.entity.GrgCusBusiInfoEntity;
import com.grgbanking.counter.csr.dao.GrgCusBusiInfoDao;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行业务增删改查
 */
@DubboService
public class RemoteCusBusiInfoServiceImpl extends ServiceImpl<GrgCusBusiInfoDao, GrgCusBusiInfoEntity> implements RemoteBusiInfoService {

    @Autowired
    GrgBusiInfoService grgBusiInfoService;

    @Autowired
    GrgBusiOptService grgBusiOptService;

    @Override
    public List<GrgCusBusiInfoEntity> findList(String customerId, String busiStatus) {
        QueryWrapper<GrgBusiOptEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customerId);
        wrapper.eq("busi_opt_status", busiStatus);
        List<GrgBusiOptEntity> list = grgBusiOptService.list(wrapper);
        List<GrgCusBusiInfoEntity> busiInfoEntities = new ArrayList<>();
        list.forEach(grgBusiInfoEntity -> {
            GrgCusBusiInfoEntity grgBusiOptEntity = new GrgCusBusiInfoEntity();
            BeanUtils.copyProperties(grgBusiInfoEntity, grgBusiOptEntity);
            busiInfoEntities.add(grgBusiOptEntity);
        });
        busiInfoEntities.forEach(grgBusiOptEntity -> {
            String busiNo = grgBusiOptEntity.getBusiNo();
            QueryWrapper<GrgBusiInfoEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("busi_no", busiNo);
            GrgBusiInfoEntity one = grgBusiInfoService.getOne(queryWrapper, true);
            grgBusiOptEntity.setBusiType(one.getBusiType());
            grgBusiOptEntity.setBusiName(one.getBusiName());
            grgBusiOptEntity.setBusiStatus(one.getBusiStatus());
        });
        return busiInfoEntities;
    }

    @Override
    public GrgCusBusiInfoEntity getOne(String id) {
        GrgBusiInfoEntity busiOptEntity = grgBusiInfoService.getById(id);
        GrgCusBusiInfoEntity grgCusBusiInfoEntity = new GrgCusBusiInfoEntity();
        BeanUtils.copyProperties(busiOptEntity, grgCusBusiInfoEntity);
        return grgCusBusiInfoEntity;
    }

    @Override
    public String getBusiNameByNo(String busiNo) {
        GrgCusBusiInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<GrgCusBusiInfoEntity>().eq("busi_no", busiNo));

        return entity.getBusiName();
    }
}
