package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import com.grgbanking.counter.iam.api.bo.SysAreaBo;
import com.grgbanking.counter.iam.api.vo.SysAreaVo;

import java.util.List;

/**
 * 系统区域表
 */
public interface SysAreaService extends IService<SysAreaEntity> {

    IPage queryPage(Page page, String name, String areaCode, String isEnabled, String i18nCode);

    /**
     * 区域列表树
     */
    List<SysAreaVo> queryAllTree(String name, String areaCode, String isEnabled, String i18nCode);

    List<SysAreaVo> queryTreeListNotAdminRole(String name, String areaCode, String isEnabled, String i18nCode, Long userId);

    /**
     * 区域列表树
     */
    List<SysAreaVo> queryTreeByUsername(String username);

    List<SysAreaVo> queryListByUserId(Long userId);

    Long countByName(String name, Long id);

    Long countByAreaCode(String areaCode, Long id);

    Long countByI18nCode(String I18nCode, Long id);

    Long countByAreaPath(String areaPath, Long id);

    void saveAndUpdate(SysAreaBo SysAreaBo);

    boolean removeByListIds(List<Long> ids);

    boolean enableByListIds(List<Long> ids);

    boolean enable(Long id);

    boolean disableByListIds(List<Long> ids);

    boolean disable(Long id);

    SysAreaEntity getByAreaCode(String areaCode);

    /**
     * 父子区域列表
     */
    List<SysAreaVo> queryChildByAreaCode(String areaCode);

    /**
     * 查询全部区域按照列表返回
     *
     * @return
     */
    List<SysAreaVo> queryAllList();

    void checkSaveAndUpdateData(SysAreaBo sysAreaBo);

    /**
     * 超级管理员返回启用状态的区域
     *
     * @return
     */
    List<SysAreaVo> queryAreaForAdminEnabled();

    List<String> getCodeListByIds(List<Long> areaListIds);

}

