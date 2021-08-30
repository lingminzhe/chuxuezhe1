package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import com.grgbanking.counter.iam.entity.SysI18nEntity;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.iam.api.bo.SysI18nBo;
import com.grgbanking.counter.iam.api.vo.SysI18nDataTypeVo;

import java.util.List;
import java.util.Map;

/**
 * 系统多语言维护信息表
 */
public interface SysI18nService extends IService<SysI18nEntity> {

    List<SysI18nDataTypeVo> i18nList(String appType, String i18nLanguageType);

    PageUtils queryPage(Map<String, Object> params);

    IPage queryPageList(Page page, SysI18nBo sysI18nBo);

    void saveBatchData(List<SysI18nBo> sysI18nBoList);

    List<SysI18nEntity> queryInfoByCode(String appType, String dataType, String i18nCode);

    Long updateSysI18nBatch(List<SysI18nBo> sysI18nBoList);

    List<SysI18nDataTypeVo> getI18nInfoByTypes(String appType, String i18nLanguageType);

    List<SysI18nEntity> getI18nListByIds(List<Long> ids);

    List<SysI18nEntity> getI18nInfoByDict(SysDictEntity dict);

    String getI18nValueByCode(String i18nCode,Boolean englishFlag);

}

