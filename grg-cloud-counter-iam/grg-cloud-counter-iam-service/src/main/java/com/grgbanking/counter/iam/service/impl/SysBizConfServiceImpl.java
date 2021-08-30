package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysBizConfBo;
import com.grgbanking.counter.iam.api.vo.SysBizConfVo;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysBizConfDao;
import com.grgbanking.counter.iam.entity.SysBizConfEntity;
import com.grgbanking.counter.iam.service.SysBizConfService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysBizConfServiceImpl extends ServiceImpl<SysBizConfDao, SysBizConfEntity> implements SysBizConfService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysBizConfEntity> page = this.page(
                new Query<SysBizConfEntity>().getPage(params),
                new QueryWrapper<SysBizConfEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage queryPage(Page page, String appType,String isEnabled) {
         QueryWrapper<SysBizConfEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(StringUtils.isNotBlank(appType), SysBizConfEntity::getAppType, appType)
                            .eq(StringUtils.isNotBlank(isEnabled),SysBizConfEntity::getIsEnabled,isEnabled)
                            .orderByDesc(SysBizConfEntity::getCreationDate);
        IPage<SysBizConfEntity> result = this.page(page,wrapper);
        return result;
    }

    @Override
    public void changeStatus(Long id, String isEnabled) {
        SysBizConfEntity sysBiz = this.baseMapper.selectById(id);
        if(sysBiz == null){
            throw new CheckedException(RespI18nConstants.BIZ_CONF1001.getCode());
        }
        sysBiz.setIsEnabled(isEnabled);
        sysBiz.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysBiz.setLastUpdateDate(new DateTime());
        int count = this.baseMapper.updateById(sysBiz);
    }

    @Override
    public void saveDataByBo(SysBizConfBo bo) {
        if(StringUtils.isBlank(bo.getIsEnabled())){
            throw new CheckedException(RespI18nConstants.COM2003.getCode());
        }
        checkBizData(bo);//校验数据
        SysBizConfEntity sysBizConf = new SysBizConfEntity();
        BeanUtils.copyProperties(bo, sysBizConf);
        sysBizConf.setId(UUIDUtils.idNumber());
        sysBizConf.setCreatedBy(SecurityContextUtil.getUsername());
        sysBizConf.setCreationDate(new DateTime());
        sysBizConf.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysBizConf.setLastUpdateDate(new DateTime());
        save(sysBizConf);
    }

    @Override
    public void updateByBo(SysBizConfBo bo) {
        if (bo.getId() == null) {
            throw new CheckedException(RespI18nConstants.COM2002.getCode());
        }
        checkBizData(bo);
        SysBizConfEntity sysBizConfEntity = this.baseMapper.selectById(bo.getId());
        if(sysBizConfEntity == null){
            throw new CheckedException(RespI18nConstants.BIZ_CONF1001.getCode());
        }
        sysBizConfEntity.setAppType(bo.getAppType());
        sysBizConfEntity.setBizCode(bo.getBizCode());
        sysBizConfEntity.setBizInfo(bo.getBizInfo());
        sysBizConfEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysBizConfEntity.setLastUpdateDate(new DateTime());
        this.baseMapper.updateById(sysBizConfEntity);
    }

    @Override
    public SysBizConfVo getBizConfVoById(Long id) {
        SysBizConfVo result = new SysBizConfVo();
        SysBizConfEntity sysBizConf = this.baseMapper.selectById(id);
        if(sysBizConf == null){
           throw new CheckedException(RespI18nConstants.BIZ_CONF1001.getCode());
        }
        BeanUtils.copyProperties(sysBizConf,result);
        return result;
    }

    @Override
    public void checkBizData(SysBizConfBo sysBizConfBo) {
        QueryWrapper<SysBizConfEntity> qw = new QueryWrapper();
        if (StringUtils.isBlank(sysBizConfBo.getAppType())) {
            throw new CheckedException(RespI18nConstants.COM2004.getCode());
        }
        if (StringUtils.isBlank(sysBizConfBo.getBizCode())) {
            throw new CheckedException(RespI18nConstants.BIZ_CONF1002.getCode());
        }
        if (StringUtils.isBlank(sysBizConfBo.getBizInfo())) {
            throw new CheckedException(RespI18nConstants.BIZ_CONF1003.getCode());
        }
        //校验key的唯一性
        if (StringUtils.isNotBlank(sysBizConfBo.getBizCode())) {
            qw.lambda().eq(SysBizConfEntity::getBizCode, sysBizConfBo.getBizCode())
                    .ne(sysBizConfBo.getId() != null, SysBizConfEntity::getId, sysBizConfBo.getId());
            Long count = this.count(qw);
            if (count > 0) {
                throw new CheckedException(RespI18nConstants.BIZ_CONF1004.getCode());
            }
        }
    }

    @Override
    public List<SysBizConfVo> getBizListByImportBizNo(String appType,String bizNo) {
        List<SysBizConfVo> result = this.baseMapper.getPanelBizInfoList(appType, bizNo);
         return result;
    }

}