package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.dao.SysAreaUserDao;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import com.grgbanking.counter.iam.entity.SysAreaUserEntity;
import com.grgbanking.counter.iam.service.SysAreaUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SysAreaUserServiceImpl extends ServiceImpl<SysAreaUserDao, SysAreaUserEntity> implements SysAreaUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysAreaUserEntity> page = this.page(
                new Query<SysAreaUserEntity>().getPage(params),
                new QueryWrapper<SysAreaUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysAreaUserEntity> getListByAreaId(Long areaId, Long userId) {
        QueryWrapper<SysAreaUserEntity> qw = new QueryWrapper<SysAreaUserEntity>();
        qw.lambda().eq(SysAreaUserEntity::getAreaId, areaId);
        qw.lambda().ne(SysAreaUserEntity::getUserId, userId);
        List<SysAreaUserEntity> list = this.baseMapper.selectList(qw);
        return list;
    }

    @Override
    public List<SysAreaUserEntity> getListByUserId(Long userId) {
        QueryWrapper<SysAreaUserEntity> qw = new QueryWrapper<SysAreaUserEntity>();
        qw.lambda().eq(SysAreaUserEntity::getUserId, userId);
        List<SysAreaUserEntity> list = this.baseMapper.selectList(qw);
        return list;
    }

    @Override
    public void deleteByAreaId(Long areaId) {
        QueryWrapper<SysAreaUserEntity> qw = new QueryWrapper<SysAreaUserEntity>();
        qw.lambda().eq(SysAreaUserEntity::getAreaId, areaId);
        this.baseMapper.delete(qw);
    }

    @Override
    public void saveByArea(SysAreaEntity area) {
        SysAreaUserEntity sysAreaUser = new SysAreaUserEntity();
        sysAreaUser.setAreaId(area.getId());//区域ID
        sysAreaUser.setUserId(SecurityContextUtil.getUserId());//用户ID
        sysAreaUser.setCreatedBy(area.getCreatedBy());//用户ID
        sysAreaUser.setCreationDate(area.getCreationDate());//创建时间
        sysAreaUser.setId(UUIDUtils.idNumber());//ID(主键)
        sysAreaUser.setIsLeader(Operate.YES.getCode());//是否负责人
        baseMapper.insert(sysAreaUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delAreaUserByUserId(List<Long> userIds) {
        //删除用户与区域关系
        QueryWrapper<SysAreaUserEntity> ew = new QueryWrapper<>();
        ew.lambda().in(SysAreaUserEntity::getUserId, userIds);
        int delete = this.baseMapper.delete(ew);
        return delete > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Long userId, List<Long> areaIdList, String isLeader) {
        if (areaIdList == null || areaIdList.size() == 0) {
            return false;
        }
        //先删除用户与区域关系

        delAreaUserByUserId(Arrays.asList(userId));

        List<SysAreaUserEntity> role = new ArrayList<>();
        //保存用户与区域关系
        for (Long areaId : areaIdList) {
            SysAreaUserEntity sys = new SysAreaUserEntity();
            sys.setUserId(userId);
            sys.setAreaId(areaId);
            sys.setId(UUIDUtils.idNumber());
            sys.setCreationDate(new Date());
            sys.setCreatedBy(SecurityContextUtil.getUsername());
            sys.setIsLeader(isLeader);
            role.add(sys);
        }
        return this.saveBatch(role);
    }
}