package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.entity.SysOrgUserEntity;

import java.util.List;

/**
 * 用户与机构对应关系表
 *
 * @author lggui1
 * @email
 * @date 2021年1月7日
 */
public interface SysOrgUserService extends IService<SysOrgUserEntity> {

    /***
     * @Description 通过组织ID查询关联用户
     * @param orgId
     * @param status 状态
     * @return
     **/
    List<SysOrgUserEntity> queryOrgUserForOrgId(Long orgId, String status);

    /***
     * @Description 根据用户id查询关联组织
     * @param Id
     * @return
     **/
    List<SysOrgUserEntity> queryListByUserId(Long Id, String isLeader);

    /***
     * @Description 根据用户id查询关联组织
     * @param userIds
     * @return
     **/
    List<SysOrgUserEntity> queryListByUserId(List<Long> userIds, String isLeader);

    /**
     * 先删再加批量保存用户与组织关系
     *
     * @param userId    用户id
     * @param orgIdList 机构id
     * @param isLeader  是否负责人
     * @return
     */

    boolean saveOrUpdate(Long userId, List<Long> orgIdList, String isLeader);

    /***
     * @Description 删除用户机构关系
     * @param userId 用户集合
     * @param isLeader    是否负责人
     * @return
     **/
    boolean delOrgUserByUserId(List<Long> userId, String isLeader);

}

