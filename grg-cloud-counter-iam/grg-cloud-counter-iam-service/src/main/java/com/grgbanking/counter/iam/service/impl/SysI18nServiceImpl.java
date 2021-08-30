package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysI18nDao;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import com.grgbanking.counter.iam.entity.SysI18nEntity;
import com.grgbanking.counter.iam.service.SysI18nService;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.common.data.util.Query;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysI18nBo;
import com.grgbanking.counter.iam.api.vo.SysI18nDataType;
import com.grgbanking.counter.iam.api.vo.SysI18nDataTypeVo;
import com.grgbanking.counter.iam.service.redis.SysI18nRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SysI18nServiceImpl extends ServiceImpl<SysI18nDao, SysI18nEntity> implements SysI18nService {

    @Autowired
    private SysI18nRedisService sysI18nRedisService;

    @Override
    public List<SysI18nDataTypeVo> i18nList(String appType, String i18nLanguageType) {
        List<SysI18nDataTypeVo> cacheResult = sysI18nRedisService.getCacheList(appType, i18nLanguageType);
        if (cacheResult != null && cacheResult.size() > 0) {
            return cacheResult;
        }
        List<SysI18nDataTypeVo> result = getI18nInfoByTypes(appType, i18nLanguageType);
        //缓存保存
        sysI18nRedisService.saveCacheList(appType, i18nLanguageType, result);
        return result;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysI18nEntity> page = this.page(
                new Query<SysI18nEntity>().getPage(params),
                new QueryWrapper<SysI18nEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage queryPageList(Page page, SysI18nBo bo) {
        QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StringUtils.isNotBlank(bo.getAppType()), SysI18nEntity::getAppType, bo.getAppType())
                .like(StringUtils.isNotBlank(bo.getDataType()), SysI18nEntity::getDataType, bo.getDataType())
                .like(StringUtils.isNotBlank(bo.getI18nCode()), SysI18nEntity::getI18nCode, bo.getI18nCode())
                .like(StringUtils.isNotBlank(bo.getI18nValue()), SysI18nEntity::getI18nValue, bo.getI18nValue())
                .eq(StringUtils.isNotBlank(bo.getI18nLanguageType()), SysI18nEntity::getI18nLanguageType, bo.getI18nLanguageType())
                .orderByDesc(SysI18nEntity::getDataType).orderByDesc(SysI18nEntity::getI18nCode);
        IPage<SysI18nEntity> result = this.page(page, wrapper);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchData(List<SysI18nBo> boList) {
        if (boList != null && boList.size() > 0) {
            for (SysI18nBo bo : boList) {
                checkSysI18nBo(bo);
                String i18nValue = bo.getI18nValue();
                String i18nLanguageType = bo.getI18nLanguageType();
                Long count = checkUniqueCode(bo.getI18nCode(), bo.getAppType(), bo.getDataType(), i18nLanguageType);
                if (count > 0) {
                    throw new CheckedException(RespI18nConstants.I18N1001.getCode());
                }
                SysI18nEntity sysI18nEntity = new SysI18nEntity();
                sysI18nEntity.setId(UUIDUtils.idNumber());
                sysI18nEntity.setAppType(bo.getAppType());
                sysI18nEntity.setDataType(bo.getDataType());
                sysI18nEntity.setI18nCode(bo.getI18nCode());
                sysI18nEntity.setI18nValue(i18nValue);
                sysI18nEntity.setI18nLanguageType(i18nLanguageType);
                sysI18nEntity.setCreatedBy(SecurityContextUtil.getUsername());
                sysI18nEntity.setCreationDate(new DateTime());
                sysI18nEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
                sysI18nEntity.setLastUpdateDate(new DateTime());
                int res = this.baseMapper.insert(sysI18nEntity);
                if (res > 0) {
                    sysI18nRedisService.delI18nCaches();
                }
            }
        }
    }

    @Override
    public List<SysI18nEntity> queryInfoByCode(String appType, String dataType, String i18nCode) {
        if (StringUtils.isBlank(i18nCode)) {
            throw new CheckedException(RespI18nConstants.I18N1002.getCode());
        }
        if (StringUtils.isBlank(appType)) {
            throw new CheckedException(RespI18nConstants.I18N1003.getCode());
        }
        if (StringUtils.isBlank(dataType)) {
            throw new CheckedException(RespI18nConstants.I18N1004.getCode());
        }
        QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysI18nEntity::getAppType, appType)
                .eq(SysI18nEntity::getDataType, dataType)
                .eq(SysI18nEntity::getI18nCode, i18nCode);
        return this.list(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateSysI18nBatch(List<SysI18nBo> sysI18nBoList) {
        Long cott = 0L;
        String appType = sysI18nBoList.get(0).getAppType();
        String dataType = sysI18nBoList.get(0).getDataType();
        String i18nCode = sysI18nBoList.get(0).getI18nCode();
        List<SysI18nEntity> i18nList = queryInfoByCode(appType, dataType, i18nCode);
        if (i18nList != null && i18nList.size() > 0) {
            List<Long> ids = i18nList.stream().map(SysI18nEntity::getId).distinct().collect(Collectors.toList());
            int row = this.baseMapper.deleteBatchIds(ids);//删除元素
            if (row > 0) {
                sysI18nRedisService.delI18nCaches();
            }
            for (SysI18nBo bo : sysI18nBoList) {
                checkSysI18nBo(bo);
                Long count = checkUniqueCode(bo.getI18nCode(), bo.getAppType(), bo.getDataType(), bo.getI18nLanguageType());
                if (count > 0) {
                    throw new CheckedException(RespI18nConstants.I18N1001.getCode());
                }
                SysI18nEntity sysI18nEntity = new SysI18nEntity();
                sysI18nEntity.setId(UUIDUtils.idNumber());
                sysI18nEntity.setAppType(bo.getAppType());
                sysI18nEntity.setDataType(bo.getDataType());
                sysI18nEntity.setI18nCode(bo.getI18nCode());
                sysI18nEntity.setI18nValue(bo.getI18nValue());
                sysI18nEntity.setI18nLanguageType(bo.getI18nLanguageType());
                sysI18nEntity.setCreatedBy(SecurityContextUtil.getUsername());
                sysI18nEntity.setCreationDate(new DateTime());
                sysI18nEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
                sysI18nEntity.setLastUpdateDate(new DateTime());
                int res = this.baseMapper.insert(sysI18nEntity);
                if (res > 0) {
                    cott++;
                }
            }
        }
        return cott;
    }

    @Override
    public List<SysI18nEntity> getI18nListByIds(List<Long> ids) {
        if (ids.size() < 1) {
            return null;
        }
        return this.baseMapper.selectBatchIds(ids);
    }

    @Override
    public List<SysI18nEntity> getI18nInfoByDict(SysDictEntity dict) {
        QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(dict.getAppType())) {
            wrapper.lambda().eq(SysI18nEntity::getAppType, dict.getAppType());//应用类别
        }
        if (StringUtils.isNoneBlank(dict.getI18nCode())) {
            String i18nCode = dict.getI18nCode();
            if(dict.getI18nCode().indexOf("dict")>-1){
                wrapper.lambda().eq(SysI18nEntity::getI18nCode, dict.getI18nCode().substring(5));
            }else {
                wrapper.lambda().eq(SysI18nEntity::getI18nCode, dict.getI18nCode());
            }
        }
        wrapper.lambda().eq(SysI18nEntity::getDataType, "dict");

        return this.list(wrapper);
    }

    @Override
    public List<SysI18nDataTypeVo> getI18nInfoByTypes(String appType, String i18nLanguageType) {
        if (StringUtils.isBlank(appType)) {
            throw new CheckedException(RespI18nConstants.I18N1003.getCode());
        }
        if (StringUtils.isBlank(i18nLanguageType)) {
            throw new CheckedException(RespI18nConstants.I18N1005.getCode());
        }
        QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysI18nEntity::getAppType, appType)
                .eq(SysI18nEntity::getI18nLanguageType, i18nLanguageType);
        List<SysI18nEntity> sysI18nEntities = this.list(wrapper);
        List<String> sortList = sysI18nEntities.stream().map(SysI18nEntity::getDataType).distinct().collect(Collectors.toList());
        List<SysI18nDataTypeVo> result = new ArrayList<>();
        for (int i = 0; i < sortList.size(); i++) {
            List<SysI18nEntity> indataList = new ArrayList<>();
            String dataType = sortList.get(i);
            if (StringUtils.isNotBlank(dataType)) {
                for (SysI18nEntity i18n : sysI18nEntities) {
                    if (dataType.equals(i18n.getDataType())) {
                        indataList.add(i18n);
                    }
                }
                SysI18nDataTypeVo sysI18nDataTypeVo = new SysI18nDataTypeVo();
                List<SysI18nDataType> typeList = new ArrayList<>();
                for (SysI18nEntity item : indataList) {
                    SysI18nDataType sysI18nDataType = new SysI18nDataType();
                    sysI18nDataType.setI18nCode(item.getI18nCode());
                    sysI18nDataType.setI18nValue(item.getI18nValue());
                    typeList.add(sysI18nDataType);
                }
                sysI18nDataTypeVo.setDataType(dataType);
                sysI18nDataTypeVo.setDataTypeList(typeList);
                result.add(sysI18nDataTypeVo);
            }
        }
        return result;
    }

    public Long checkUniqueCode(String code, String appType, String dataType, String i18nLanguageType) {
        QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysI18nEntity::getAppType, appType)
                .eq(SysI18nEntity::getDataType, dataType)
                .eq(SysI18nEntity::getI18nCode, code)
                .eq(SysI18nEntity::getI18nLanguageType, i18nLanguageType);
        return this.count(wrapper);
    }

    @Override
    public String getI18nValueByCode(String i18nCode, Boolean englishFlag) {
        String languageType = (englishFlag == true?"en":"zh");
        int startIndex = i18nCode.indexOf(".");
        if(startIndex == -1){
            throw new CheckedException(RespI18nConstants.IMPORT_BIND1011.getCode());
        }
        String dataType = i18nCode.substring(0,startIndex);
       QueryWrapper<SysI18nEntity> wrapper = new QueryWrapper<>();
       wrapper.lambda().eq(SysI18nEntity::getAppType,"grgmsSys")//统一用户
               .eq(SysI18nEntity::getDataType,dataType)//字典
               .eq(SysI18nEntity::getI18nCode,i18nCode)
               .eq(SysI18nEntity::getI18nLanguageType,languageType);

        return this.baseMapper.selectOne(wrapper).getI18nValue();
    }

    public void checkSysI18nBo(SysI18nBo bo) {
        if (StringUtils.isBlank(bo.getAppType())) {
            throw new CheckedException(RespI18nConstants.I18N1003.getCode());
        }
        if (StringUtils.isBlank(bo.getDataType())) {
            throw new CheckedException(RespI18nConstants.I18N1004.getCode());
        }
        if (StringUtils.isBlank(bo.getI18nCode())) {
            throw new CheckedException(RespI18nConstants.I18N1002.getCode());
        }
        if (StringUtils.isBlank(bo.getI18nValue())) {
            throw new CheckedException(RespI18nConstants.I18N1006.getCode());
        }
        if (StringUtils.isBlank(bo.getI18nLanguageType())) {
            throw new CheckedException(RespI18nConstants.I18N1005.getCode());
        }
        int startIndex = bo.getI18nCode().indexOf(".");
        if(startIndex == -1){
            throw new CheckedException(RespI18nConstants.IMPORT_BIND1011.getCode());
        }
        String dataType = bo.getI18nCode().substring(0,startIndex);
        if(!bo.getDataType().equals(dataType)){
            throw new CheckedException(RespI18nConstants.I18N1008.getCode());
        }
    }

}