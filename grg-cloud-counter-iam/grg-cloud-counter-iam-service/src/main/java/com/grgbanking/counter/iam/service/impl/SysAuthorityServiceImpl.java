package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysAuthorityDao;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysMenuAuthorityEntity;
import com.grgbanking.counter.iam.service.SysAuthorityService;
import com.grgbanking.counter.iam.service.SysMenuAuthorityService;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysAuthorityBo;
import com.grgbanking.counter.iam.service.redis.SysAuthorityRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityDao, SysAuthorityEntity> implements SysAuthorityService {

    @Autowired
    private SysAuthorityRedisService sysAuthorityRedisService;

    @Autowired
    private SysMenuAuthorityService sysMenuAuthorityService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysAuthorityEntity> page = this.page(
                new Query<SysAuthorityEntity>().getPage(params),
                new QueryWrapper<SysAuthorityEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage queryPageList(Page page, SysAuthorityBo bo) {
        QueryWrapper<SysAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StringUtils.isNotBlank(bo.getAppName()), SysAuthorityEntity::getAppName, bo.getAppName())
                .like(StringUtils.isNotBlank(bo.getModuleName()), SysAuthorityEntity::getModuleName, bo.getModuleName())
                .like(StringUtils.isNotBlank(bo.getAuthority()), SysAuthorityEntity::getAuthority, bo.getAuthority())
                .like(StringUtils.isNotBlank(bo.getClassName()), SysAuthorityEntity::getClassName, bo.getClassName())
                .like(StringUtils.isNotBlank(bo.getMethodName()), SysAuthorityEntity::getMethodName, bo.getMethodName())
                .eq(StringUtils.isNotBlank(bo.getIsEnabled()), SysAuthorityEntity::getIsEnabled, bo.getIsEnabled());
        IPage<SysAuthorityEntity> result = this.page(page, wrapper);
        return result;
    }

    @Override
    public IPage queryEnabledPageList(Page page, SysAuthorityBo bo) {
        QueryWrapper<SysAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StringUtils.isNotBlank(bo.getAppName()), SysAuthorityEntity::getAppName, bo.getAppName())
                .eq(StringUtils.isNotBlank(bo.getModuleName()), SysAuthorityEntity::getModuleName, bo.getModuleName())
                .eq(StringUtils.isNotBlank(bo.getAuthority()), SysAuthorityEntity::getAuthority, bo.getAuthority())
                .eq(StringUtils.isNotBlank(bo.getClassName()), SysAuthorityEntity::getClassName, bo.getClassName())
                .eq(StringUtils.isNotBlank(bo.getMethodName()), SysAuthorityEntity::getMethodName, bo.getMethodName())
                .eq(SysAuthorityEntity::getIsEnabled, Operate.ENABLE.code());
        IPage<SysAuthorityEntity> result = this.page(page, wrapper);
        return result;
    }

    @Override
    public void saveAuthorityByBo(SysAuthorityBo bo) {
        checkAuthData(bo);
        Long count = checkBeforeSave(bo.getAppName(), bo.getAuthority());
        if (count > 0) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1001.getCode());
        }
        SysAuthorityEntity sysAuthorityEntity = new SysAuthorityEntity();
        BeanUtils.copyProperties(bo, sysAuthorityEntity);
        sysAuthorityEntity.setId(UUIDUtils.idNumber());
        sysAuthorityEntity.setCreatedBy("admin");
        sysAuthorityEntity.setCreationDate(new DateTime());
        sysAuthorityEntity.setLastUpdatedBy("admin");
        sysAuthorityEntity.setLastUpdateDate(new DateTime());
        this.baseMapper.insert(sysAuthorityEntity);
    }

    @Override
    public void updateAuthByBo(SysAuthorityBo bo) {
        if (null == bo.getId()) {
            throw new CheckedException(RespI18nConstants.COM2002.getCode());
        }
        checkAuthData(bo);
        QueryWrapper<SysAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().ne(SysAuthorityEntity::getId, bo.getId())
                .eq(SysAuthorityEntity::getAppName, bo.getAppName())
                .eq(SysAuthorityEntity::getAuthority, bo.getAuthority());
        Long count = this.count(wrapper);
        if (count > 0) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1001.getCode());
        }
        SysAuthorityEntity sysAuthorityEntity = new SysAuthorityEntity();
        BeanUtils.copyProperties(bo, sysAuthorityEntity);
        sysAuthorityEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysAuthorityEntity.setLastUpdateDate(new DateTime());
        int ret = this.baseMapper.updateById(sysAuthorityEntity);
        if (ret > 0) {
            sysAuthorityRedisService.delAuthorityCaches();
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (ids.size() < 1) {
            throw new CheckedException(RespI18nConstants.COM2001.getCode());
        }
        for (Long authorityId : ids) {
            List<SysMenuAuthorityEntity> menuAuthList = sysMenuAuthorityService.queryListByAuthorityId(authorityId);
            if (menuAuthList.size() > 0) {
                throw new CheckedException(RespI18nConstants.AUTHORITY1002.getCode());
            }
        }
        Boolean flag = this.removeByIds(ids);
        if (flag) {
            sysAuthorityRedisService.delAuthorityCaches();
        }
    }

    /**
     * 根据ID列表查询权限标识拼接字符串
     */
    @Override
    public String getAuthorityToString(List<Long> ids) {
        List<String> auList = getAuthorityList(ids);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < auList.size(); i++) {
            sb.append(auList.get(i));
            if (i < (auList.size() - 1)) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 根据ID列表查询权限标识
     */
    @Override
    public List<String> getAuthorityList(List<Long> ids) {
        List<SysAuthorityEntity> sysAuList = this.baseMapper.selectBatchIds(ids);
        List<String> auList = sysAuList.stream().map(SysAuthorityEntity::getAuthority).distinct().collect(Collectors.toList());
        return auList;
    }

    @Override
    public List<SysAuthorityEntity> getAuthorityByUserId(Long userId, String isEnabled) {
        return this.baseMapper.getAuthorityByUserId(userId, isEnabled);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateToStatus(Long id, String isEnabled) {
        SysAuthorityEntity sysAuth;
        if (null == id) {
            throw new CheckedException(RespI18nConstants.COM2002.getCode());
        }
        if (StringUtils.isBlank(isEnabled)) {
            throw new CheckedException(RespI18nConstants.COM2003.getCode());
        }
        List<SysMenuAuthorityEntity> menuAuthList = sysMenuAuthorityService.queryListByAuthorityId(id);
        if (menuAuthList != null && menuAuthList.size() > 0) {//(0:停用;1:启用)
            for (SysMenuAuthorityEntity item : menuAuthList) {
                item.setIsEnabled(isEnabled);
                item.setLastUpdatedBy(SecurityContextUtil.getUsername());
                item.setLastUpdateDate(new DateTime());
                sysMenuAuthorityService.updateById(item);
            }
        }
        sysAuth = this.baseMapper.selectById(id);
        sysAuth.setIsEnabled(isEnabled);
        sysAuth.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysAuth.setLastUpdateDate(new DateTime());
        this.baseMapper.updateById(sysAuth);

    }

    public Long checkBeforeSave(String appName, String authority) {
        QueryWrapper<SysAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysAuthorityEntity::getAppName, appName)
                .eq(SysAuthorityEntity::getAuthority, authority);
        Long count = this.count(wrapper);
        return count;
    }

    public void checkAuthData(SysAuthorityBo bo) {
        if (StringUtils.isBlank(bo.getAppName())) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1003.getCode());
        }
        if (StringUtils.isBlank(bo.getModuleName())) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1004.getCode());
        }
        if (StringUtils.isBlank(bo.getAuthority())) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1005.getCode());
        }
        if (StringUtils.isBlank(bo.getClassName())) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1006.getCode());
        }
        if (StringUtils.isBlank(bo.getMethodName())) {
            throw new CheckedException(RespI18nConstants.AUTHORITY1007.getCode());
        }
        if (StringUtils.isBlank(bo.getIsEnabled())) {
            throw new CheckedException(RespI18nConstants.COM2003.getCode());
        }
    }
}