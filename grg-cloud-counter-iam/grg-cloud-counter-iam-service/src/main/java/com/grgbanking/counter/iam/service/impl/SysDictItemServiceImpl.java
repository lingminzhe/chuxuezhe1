package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grgbanking.counter.iam.api.entity.SysDictItemEntity;
import com.grgbanking.counter.iam.dao.SysDictItemDao;
import com.grgbanking.counter.iam.service.SysDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemDao, SysDictItemEntity> implements SysDictItemService {

    @Override
    public List<SysDictItemEntity> getDictItemByType(String type) {
        List<SysDictItemEntity> entities = this.baseMapper.selectList(new QueryWrapper<SysDictItemEntity>().eq("type", type));

        return entities;
    }
}
