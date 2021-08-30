package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysOrgUserDao;
import com.grgbanking.counter.iam.entity.SysOrgUserEntity;
import com.grgbanking.counter.iam.service.SysOrgUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class SysOrgUserServiceImpl extends ServiceImpl<SysOrgUserDao, SysOrgUserEntity> implements SysOrgUserService {

    @Override
    public List<SysOrgUserEntity> queryOrgUserForOrgId(Long orgId, String status) {
        List<SysOrgUserEntity> sysOrgUserEntities = this.baseMapper.queryOrgUserForOrgId(orgId, status);
        return sysOrgUserEntities;
    }

    @Override
    public List<SysOrgUserEntity> queryListByUserId(Long Id, String isLeader) {
        QueryWrapper<SysOrgUserEntity> query = new QueryWrapper<>();
        query.lambda().eq(SysOrgUserEntity::getUserId, Id).
                eq(StringUtils.isNotBlank(isLeader), SysOrgUserEntity::getIsLeader, isLeader);
        List<SysOrgUserEntity> sysOrgUserEntities = this.baseMapper.selectList(query);
        return sysOrgUserEntities;
    }

    @Override
    public List<SysOrgUserEntity> queryListByUserId(List<Long> userIds, String isLeader) {
        QueryWrapper<SysOrgUserEntity> query = new QueryWrapper<>();
        query.lambda().in(SysOrgUserEntity::getUserId, userIds)
                .eq(StringUtils.isNotBlank(isLeader), SysOrgUserEntity::getIsLeader, isLeader);
        List<SysOrgUserEntity> sysOrgUserEntities = this.baseMapper.selectList(query);
        return sysOrgUserEntities;
    }

    @Override
    public boolean save(SysOrgUserEntity entity) {
        checkNotNull(entity);
        entity.setCreationDate(new Date());
        entity.setId(UUIDUtils.idNumber());
        return this.baseMapper.insert(entity) == 1 ? true : false;
    }

    public boolean delOrgUserByUserId(List<Long> userId, String isLeader) {
        //先删除用户与角色关系
        Integer integer = this.baseMapper.delOrgUserByUserId(userId, isLeader);
        return integer > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Long userId, List<Long> orgIdList, String isLeader) {
        if (orgIdList == null || orgIdList.size() == 0) {
            return false;
        }
        //先删除用户与角色关系
        delOrgUserByUserId(Arrays.asList(userId), isLeader);

        List<SysOrgUserEntity> role = new ArrayList<>();
        //保存用户与机构关系
        for (Long orgId : orgIdList) {
            SysOrgUserEntity sys = new SysOrgUserEntity();
            sys.setIsLeader(isLeader);
            sys.setUserId(userId);
            sys.setOrgId(orgId);
            sys.setId(UUIDUtils.idNumber());
            sys.setCreationDate(new Date());
            sys.setCreatedBy(SecurityContextUtil.getUsername());
            role.add(sys);
        }
        return this.saveBatch(role);
    }

    public void checkNotNull(SysOrgUserEntity entity) {
        Assert.isNull(entity, RespI18nConstants.COM1005.getCode());
        Assert.isNull(entity.getOrgId(), RespI18nConstants.ORG1003.getCode());
        Assert.isNull(entity.getUserId(), RespI18nConstants.USER1002.getCode());
    }

    public List<SysOrgUserEntity> getByOrgIdList(List ids) {
        QueryWrapper<SysOrgUserEntity> query = new QueryWrapper<>();
        query.lambda().in(SysOrgUserEntity::getOrgId, ids);
        List<SysOrgUserEntity> sysOrgUserEntities = this.baseMapper.selectList(query);
        return sysOrgUserEntities;
    }

    public List<Long> queryMangeOrgbyUserAndPath(Long userId, String orgpath) {
        return this.baseMapper.queryMangeOrgbyUserAndPath(userId, orgpath);
    }

}