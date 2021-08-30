package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import com.grgbanking.counter.iam.entity.SysAreaUserEntity;
import com.grgbanking.counter.common.data.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统区域用户关系表
 */
public interface SysAreaUserService extends IService<SysAreaUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysAreaUserEntity> getListByAreaId(Long areaId, Long userId);

    List<SysAreaUserEntity> getListByUserId(Long userId);

    void deleteByAreaId(Long areaId);

    void saveByArea(SysAreaEntity area);

    /**
     * 先删再加批量保存用户与区域关系
     *
     * @param userId     用户id
     * @param areaIdList 区域id
     * @param areaIdList 是否负责人
     * @return
     */
    boolean saveOrUpdate(Long userId, List<Long> areaIdList, String isLeader);

    /**
     * 删除区域用户
     *
     * @param userIds
     * @return
     */
    boolean delAreaUserByUserId(List<Long> userIds);
}

