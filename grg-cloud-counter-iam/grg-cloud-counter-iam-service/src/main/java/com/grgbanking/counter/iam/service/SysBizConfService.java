package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysBizConfEntity;
import com.grgbanking.counter.common.data.util.PageUtils;
import com.grgbanking.counter.iam.api.bo.SysBizConfBo;
import com.grgbanking.counter.iam.api.vo.SysBizConfVo;

import java.util.List;
import java.util.Map;

/**
 * 系统业务配置表
 */
public interface SysBizConfService extends IService<SysBizConfEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage queryPage(Page page, String appType,String isEnabled);

    void checkBizData(SysBizConfBo sysBizConfBo);

    void saveDataByBo(SysBizConfBo sysBizConfBo);

    void updateByBo(SysBizConfBo sysBizConfBo);

    SysBizConfVo getBizConfVoById(Long id);

    void changeStatus(Long id,String isEnabled);

    List<SysBizConfVo> getBizListByImportBizNo(String appType,String bizNo);

}

