package com.grgbanking.counter.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grgbanking.counter.iam.entity.SysOrgUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与机构对应关系表
 *
 * @author lggui1
 * @email xx@grgbanking.com
 * @date 2021-04-11 12:32:55
 */
@Mapper
public interface SysOrgUserDao extends BaseMapper<SysOrgUserEntity> {

    /**
     * 根据组织id和状态查询与该组织关联的用户
     *
     * @param orgId
     * @param status
     * @return
     */
    List<SysOrgUserEntity> queryOrgUserForOrgId(@Param("orgId") Long orgId, @Param("status") String status);

    Integer delOrgUserByUserId(@Param("userIds") List<Long> userIds, @Param("isLeader") String isLeader);

    /***
     * @Description 通过用户id和闯入的orgPath来查询他的管理组织
     * @param
     * @return
     **/
    List<Long> queryMangeOrgbyUserAndPath(@Param("userId") Long userId, @Param("orgPath") String orgPath);
}
