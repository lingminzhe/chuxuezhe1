package com.grgbanking.counter.iam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryBo;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryListBo;
import com.grgbanking.counter.iam.api.vo.SysOrgOneVo;
import com.grgbanking.counter.iam.api.vo.SysOrgVo;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import com.grgbanking.counter.common.core.util.Resp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lggui1
 * @date 2021年1月6日
 */
@Service
public interface SysOrgService extends IService<SysOrgEntity> {

    /***
     * @Description 校验必填项
     * @param entity
     * @return 必填项空抛出异常
     **/
    void checkNotNull(SysOrgEntity entity);

    /**
     * 机构编码不允许为0且不允许重复、机构名称不允许重复、机构全称不允许重复、国际化编码不允许重复
     *
     * @param entity
     * @return true 已存在
     */
    void alreadyField(SysOrgEntity entity);

    /***
     * @Description 新增
     * @param entity
     * @param userId
     * @return
     **/
    boolean save(SysOrgEntity entity, Long userId);

    /***
     * @Description 更新
     * @param entity
     * @param userId
     * @return
     **/
    boolean update(SysOrgEntity entity, Long userId);

    /***
     * @Description 修改停用启用状态
     * @param id 主键
     * @param status  状态
     * @return
     **/
    Integer updateStatus(Long id, String status);

    /**
     * 机构列表删除
     * 如果如果该机构存在子级则不允许删除，不管子级是不是停用
     * 传递的是父级到子级的完整数组也不能删除
     *
     * @param id
     * @return
     */
    Resp delete(List<Long> id);

    /**
     * 根据机构ID集合及条件查询机构列表
     *
     * @param condition 查询条件
     * @param userId    用户id
     * @param isLeader  是否负责人
     * @return
     */
    List<SysOrgEntity> querByOrglist(SysOrgQueryListBo condition, Long userId, String isLeader);

    /**
     * 根据条件及用户id查询用户管理的机构列表
     *
     * @param org
     * @param userId
     * @return 树形列表数据
     */
    List<SysOrgVo> listTree(SysOrgQueryListBo org, Long userId);

    /**
     * 根据条件及用户id查询用户管理的机构列表
     *
     * @param org
     * @param userId
     * @return 列表数据（非树形）
     */
    List<SysOrgVo> manageOrgList(SysOrgQueryListBo org, Long userId);

    /**
     * 根据条件及用户id查询用户管理的机构列表
     * 只查询启用状态的
     * 列表
     *
     * @param
     * @param userId
     * @return 列表数据（非树形）
     */
    List<SysOrgVo> manageOrgListEnable(Long userId);

    /**
     * 根据条件及用户id查询用户管理的机构树
     * 只查询启用状态的
     * 树形
     *
     * @param
     * @param userId
     * @return 列表数据（非树形）
     */
    List<SysOrgVo> manageOrgTreeEnable(Long userId);

    /**
     * 根据条件及用户id查询用户,及区域ID查询启用状态的机构树
     * 只查询启用状态的
     * 树形
     *
     * @param
     * @param userId
     * @return 列表数据（非树形）
     */
    List<SysOrgVo> manageOrgTreeEnable(Long userId, List<Long> areaIdList);


    /***
     * @Description 查询单个机构放回
     * @param id
     * @return
     **/
    SysOrgEntity queryOneById(Long id);

    /**
     * 通过区域ID集合查询机构列表
     *
     * @param AreaIds
     * @param status  启用状态，空查所有
     * @return
     */
    List<SysOrgEntity> queryByAreaIds(List<Long> AreaIds, String status);

    /***
     * @Description 通过orgid查询本身及子级
     * @param orgId
     * @param status 启用状态 可以空
     * @param userId   用户id 可以空
     * @return
     **/
    List<SysOrgEntity> childListByOrgId(Long orgId, String status, Long userId);

    /***
     * @Description 根据机构编码查询
     * @param orgCode
     * @return
     **/
    SysOrgEntity queryOneByOrgCode(String orgCode);

    List<SysOrgEntity> getListByAreaId(Long areaId);

    SysOrgOneVo queryForUpdateById(Long id);

    List<String> getCodeListByIds(List<Long> manageOrgIds);
}

