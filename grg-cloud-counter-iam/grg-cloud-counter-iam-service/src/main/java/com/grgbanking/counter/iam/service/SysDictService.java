package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.api.bo.SysDictBo;
import com.grgbanking.counter.iam.api.vo.SysDictVo;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import java.util.List;

/**
 * 数据字典
 */
public interface SysDictService extends IService<SysDictEntity> {

    IPage<SysDictVo> querySysDictList(Page page, SysDictBo sysDictBo);

    boolean enable(Long id);

    boolean disable(Long id);

    void save(SysDictBo sysDictBo);

    void update(SysDictBo sysDictBo);

    void deleteBatch(Long[] codes);

    List<SysDictVo> getListByAppType(String appType);

    List<SysDictVo> getListByCodeType(String codeType);

}

