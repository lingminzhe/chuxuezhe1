package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysDictBo;
import com.grgbanking.counter.iam.api.vo.SysDictVo;
import com.grgbanking.counter.iam.api.vo.SysI18nDataType;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysDictDao;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import com.grgbanking.counter.iam.entity.SysI18nEntity;
import com.grgbanking.counter.iam.service.SysDictService;
import com.grgbanking.counter.iam.service.SysI18nService;
import com.grgbanking.counter.iam.service.redis.SysDictRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Autowired
    private SysI18nService sysI18nService;

    @Autowired
    private SysDictRedisService sysDictRedisService;

    @Override
    public IPage<SysDictVo> querySysDictList(Page page, SysDictBo sysDictBo) {
        QueryWrapper<SysDictEntity> qw = new QueryWrapper<>();

        qw.lambda().eq(StringUtils.isNotBlank(sysDictBo.getAppType()), SysDictEntity::getAppType, sysDictBo.getAppType());//应用类别
        qw.lambda().like(StringUtils.isNotBlank(sysDictBo.getCodeType()), SysDictEntity::getCodeType, sysDictBo.getCodeType());//代码类型
        qw.lambda().like(StringUtils.isNotBlank(sysDictBo.getCodeValue()), SysDictEntity::getCodeValue, sysDictBo.getCodeValue());//代码值
        qw.lambda().like(StringUtils.isNotBlank(sysDictBo.getCodeValueName()), SysDictEntity::getCodeValueName, sysDictBo.getCodeValueName());//代码值名称
        qw.lambda().like(StringUtils.isNotBlank(sysDictBo.getI18nCode()), SysDictEntity::getI18nCode, sysDictBo.getI18nCode());//国际化编码
        qw.lambda().eq(StringUtils.isNotBlank(sysDictBo.getIsEnabled()), SysDictEntity::getIsEnabled, sysDictBo.getIsEnabled());//是否启用

        return this.page(page, qw);
    }

    @Override
    public boolean enable(Long id) {
        SysDictEntity sysDict = this.getById(id);

        sysDict.setIsEnabled(Operate.ENABLE.getCode());

        this.baseMapper.updateById(sysDict);

        return true;
    }

    @Override
    public boolean disable(Long id) {
        SysDictEntity sysDict = this.getById(id);

        sysDict.setIsEnabled(Operate.DISABLE.getCode());

        this.baseMapper.updateById(sysDict);

        return true;
    }

    @Override
    public void save(SysDictBo sysDictBo) {
        SysDictEntity sysDictEntity = new SysDictEntity();
        BeanUtils.copyProperties(sysDictBo, sysDictEntity);

        this.checkData(sysDictEntity);
        sysDictEntity.setId(UUIDUtils.idNumber());
        sysDictEntity.setCreatedBy(SecurityContextUtil.getUsername());
        sysDictEntity.setCreationDate(new Date());
        sysDictEntity.setIsEnabled(sysDictBo.getIsEnabled());

        baseMapper.insert(sysDictEntity);
    }


    @Override
    public void update(SysDictBo sysDictBo) {
        SysDictEntity sysDictEntity = this.getById(sysDictBo.getId());

        this.checkData(sysDictEntity);
        sysDictEntity.setAppType(sysDictBo.getAppType());//应用类别
        sysDictEntity.setCodeType(sysDictBo.getCodeType());//代码类型
        sysDictEntity.setCodeValue(sysDictBo.getCodeValue());//代码值
        sysDictEntity.setCodeValueName(sysDictBo.getCodeValueName());//代码值名称
        sysDictEntity.setI18nCode(sysDictBo.getI18nCode());//国际化编码
        //sysDictEntity.setIsEnabled(sysDictBo.getIsEnabled());//是否启用
        sysDictEntity.setCodeOrder(sysDictBo.getCodeOrder());//顺序号
        sysDictEntity.setLastUpdateDate(new Date());
        sysDictEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());

        this.updateById(sysDictEntity);
    }

    private void checkData(SysDictEntity sysDictEntity) {
        String code = sysDictEntity.getI18nCode();

        int startIndex = code.indexOf(".");
        if(startIndex == -1){
            throw new CheckedException(RespI18nConstants.IMPORT_BIND1011.getCode());
        }
        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        //应用类别、代码类型、代码值三者组合值满足
        //1、	若应用类别相同，则代码类型不能相同；
        //2、	若应用类别和代码类型相同，则代码值不能相同
        wrapper.lambda()
                .eq(SysDictEntity::getAppType, sysDictEntity.getAppType())
                .eq(SysDictEntity::getCodeType, sysDictEntity.getCodeType())
                .eq(SysDictEntity::getCodeValue, sysDictEntity.getCodeValue());

        if (null != sysDictEntity.getId()) {
            wrapper.lambda().ne(SysDictEntity::getId, sysDictEntity.getId());
        }

        Assert.state(this.count(wrapper) > 0, "应用类别、代码类型、代码值三者组合值重复，请检查重新输入!");

    }

    @Override
    public void deleteBatch(Long[] codes) {
        this.removeByIds(Arrays.asList(codes));
    }

    @Override
    public List<SysDictVo> getListByAppType(String appType) {
        QueryWrapper<SysDictEntity> wq = new QueryWrapper<>();

        wq.lambda().eq(SysDictEntity::getAppType, appType);

        List<SysDictEntity> list = this.list(wq);
        List<SysDictVo> sysDictVoList = new ArrayList<>();

        for (SysDictEntity sysDict : list) {
            SysDictVo sysDictVo = new SysDictVo();
            BeanUtils.copyProperties(sysDict, sysDictVo);
            List<SysI18nEntity> i18nInfoByDict = sysI18nService.getI18nInfoByDict(sysDict);
            List<SysI18nDataType> sysI18nDataTypeList = new ArrayList<>();
            for (SysI18nEntity sysI18nEntity : i18nInfoByDict) {
                SysI18nDataType sysI18nDataType = new SysI18nDataType();
                sysI18nDataType.setI18nLanguageType(sysI18nEntity.getI18nLanguageType());
                sysI18nDataType.setI18nValue(sysI18nEntity.getI18nValue());
                sysI18nDataTypeList.add(sysI18nDataType);
            }
            sysDictVo.setI18n(sysI18nDataTypeList);
            sysDictVoList.add(sysDictVo);
        }

        sysDictRedisService.saveCacheList(appType, sysDictVoList);

        return sysDictVoList;
    }

    @Override
    public List<SysDictVo> getListByCodeType(String codeType) {
        QueryWrapper<SysDictEntity> wq = new QueryWrapper<>();

        wq.lambda().eq(SysDictEntity::getCodeType, codeType);

        List<SysDictEntity> list = this.list(wq);
        List<SysDictVo> sysDictVoList = new ArrayList<>();

        for (SysDictEntity sysDict : list) {
            SysDictVo sysDictVo = new SysDictVo();
            BeanUtils.copyProperties(sysDict, sysDictVo);
            List<SysI18nEntity> i18nInfoByDict = sysI18nService.getI18nInfoByDict(sysDict);
            List<SysI18nDataType> sysI18nDataTypeList = new ArrayList<>();
            for (SysI18nEntity sysI18nEntity : i18nInfoByDict) {
                SysI18nDataType sysI18nDataType = new SysI18nDataType();
                sysI18nDataType.setI18nLanguageType(sysI18nEntity.getI18nLanguageType());
                sysI18nDataType.setI18nValue(sysI18nEntity.getI18nValue());
                sysI18nDataTypeList.add(sysI18nDataType);
            }
            sysDictVo.setI18n(sysI18nDataTypeList);
            sysDictVoList.add(sysDictVo);
        }

        return sysDictVoList;
    }

}