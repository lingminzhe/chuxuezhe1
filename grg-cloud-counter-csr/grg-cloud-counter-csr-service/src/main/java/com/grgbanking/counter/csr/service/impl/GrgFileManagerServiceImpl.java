package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.dao.GrgFileManagerDao;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("grgFileManagerService")
public class GrgFileManagerServiceImpl extends ServiceImpl<GrgFileManagerDao, GrgFileManagerEntity> implements GrgFileManagerService {

    @Autowired
    private GrgFileManagerDao fileManagerDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgFileManagerEntity> page = this.page(
                new Query<GrgFileManagerEntity>().getPage(params),
                new QueryWrapper<GrgFileManagerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public GrgFileManagerEntity getByCustomerId(String customerId) {
        return fileManagerDao.getByCustomerId(customerId);
    }

}