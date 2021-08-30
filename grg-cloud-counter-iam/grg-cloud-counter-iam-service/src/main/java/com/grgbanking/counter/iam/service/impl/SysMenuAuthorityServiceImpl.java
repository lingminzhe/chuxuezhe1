package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.iam.api.bo.SysMenuAuthorityBo;
import com.grgbanking.counter.iam.dao.SysMenuAuthorityDao;
import com.grgbanking.counter.iam.entity.SysMenuAuthorityEntity;
import com.grgbanking.counter.iam.service.SysMenuAuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuAuthorityServiceImpl extends ServiceImpl<SysMenuAuthorityDao, SysMenuAuthorityEntity> implements SysMenuAuthorityService {

    @Override
    public void add(SysMenuAuthorityBo sysMenuAuthorityBo) {
        SysMenuAuthorityEntity sysMenuAuthorityEntity = new SysMenuAuthorityEntity();
        BeanUtils.copyProperties(sysMenuAuthorityBo, sysMenuAuthorityEntity);
        baseMapper.insert(sysMenuAuthorityEntity);
    }

    @Override
    public SysMenuAuthorityEntity getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void deleteAuthorityByMenuId(Long menuId) {
        QueryWrapper<SysMenuAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuAuthorityEntity::getMenuId, menuId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void deleteAuthorityByMenuIds(List<Long> menuIdList) {
        QueryWrapper<SysMenuAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SysMenuAuthorityEntity::getMenuId, menuIdList);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<SysMenuAuthorityEntity> queryListByAuthorityId(Long authorityId) {
        QueryWrapper<SysMenuAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuAuthorityEntity::getAuthorityId, authorityId);
        List<SysMenuAuthorityEntity> result = this.list(wrapper);
        return result;
    }

}
