package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.PlatformAdminRoleIdUtil;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysRoleListBo;
import com.grgbanking.counter.iam.api.bo.SysRoleSaveBo;
import com.grgbanking.counter.iam.api.vo.SysRoleVo;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysRoleDao;
import com.grgbanking.counter.iam.entity.SysRoleEntity;
import com.grgbanking.counter.iam.entity.SysRoleUserEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysRoleMenuService;
import com.grgbanking.counter.iam.service.SysRoleService;
import com.grgbanking.counter.iam.service.SysRoleUserService;
import com.grgbanking.counter.iam.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author MARK lggui1@grgbanking.com
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Long countByName(String name, Long id) {
        QueryWrapper<SysRoleEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(StringUtils.isNotBlank(name), SysRoleEntity::getName, name);
        qw.lambda().ne(id != null, SysRoleEntity::getId, id);

        return this.count(qw);
    }

    @Override
    public IPage<SysRoleVo> queryPage(Page page, SysRoleListBo sysRoleListBo) {
        boolean notAdminRoleId = true;
        try {
            notAdminRoleId = SecurityContextUtil.notAdminRole();
        }catch (Exception e) {
            e.printStackTrace();
        }

        QueryWrapper<SysRoleEntity> qw = new QueryWrapper<>();
        qw.lambda().like(StringUtils.isNotBlank(sysRoleListBo.getName()), SysRoleEntity::getName, sysRoleListBo.getName())
                .like(StringUtils.isNotBlank(sysRoleListBo.getDescription()), SysRoleEntity::getDescription, sysRoleListBo.getDescription())
                .eq(StringUtils.isNotBlank(sysRoleListBo.getIsEnabled()), SysRoleEntity::getIsEnabled, sysRoleListBo.getIsEnabled())
                .eq(StringUtils.isNotBlank(sysRoleListBo.getRoleType()), SysRoleEntity::getRoleType, sysRoleListBo.getRoleType())
                .ne(notAdminRoleId == true, SysRoleEntity::getId, PlatformAdminRoleIdUtil.getPlatformAdminRoleId());
        IPage iPage = this.page(page, qw);
        List<SysRoleVo> vos = new ArrayList<>();
        BeanUtils.copyProperties(iPage.getRecords(),vos,SysRoleVo.class);
        iPage.setRecords(vos);
        return iPage;
    }

    @Override
    public List<SysRoleEntity> getUserRoleListByUsername(String username) {
        SysUserEntity userByUsername = sysUserService.getUserByUsername(username);
        Assert.isNull(userByUsername, RespI18nConstants.COM1007.getCode());
        List<SysRoleUserEntity> sysRoleUserEntities = sysRoleUserService.queryAllRoleIdByUserId(userByUsername.getId());
        if (CollectionUtil.isEmpty(sysRoleUserEntities)) {
            return new ArrayList<>();
        }
        List<Long> collect = sysRoleUserEntities.stream().map(s -> s.getRoleId()).collect(Collectors.toList());
        QueryWrapper<SysRoleEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysRoleEntity::getIsEnabled, Operate.ENABLE.code())
                .in(SysRoleEntity::getId, collect);
        List<SysRoleEntity> sysRoleEntities = this.baseMapper.selectList(ew);
        return sysRoleEntities;
    }

    @Override
    public List<SysRoleEntity> getUserRoleListByAdmin() {
        QueryWrapper<SysRoleEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysRoleEntity::getIsEnabled, Operate.ENABLE.code());
        List<SysRoleEntity> sysRoleEntities = this.baseMapper.selectList(ew);
        return sysRoleEntities;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(SysRoleSaveBo sysRoleSaveBo) {
        checkNotnull(sysRoleSaveBo);
        SysRoleEntity roleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(sysRoleSaveBo, roleEntity);
        if (roleEntity.getId() == null) {
            saveRole(roleEntity);
        } else {
            updateRole(roleEntity);
        }
        //保存角色与菜单关系 可分配
        sysRoleMenuService.saveOrUpdate(roleEntity.getId(), sysRoleSaveBo.getAllocMenuIdList(), Operate.YES.code());
        //保存角色与菜单关系 可使用
        sysRoleMenuService.saveOrUpdate(roleEntity.getId(), sysRoleSaveBo.getUseMenuIdList(), Operate.NO.code());
        //保存角色与用户关系
        sysRoleUserService.saveOrUpdate(sysRoleSaveBo.getUserIdList(), roleEntity.getId());
    }


    public void saveRole(SysRoleEntity sysRoleEntity) {
        checkAlreadyHave(sysRoleEntity);
        sysRoleEntity.setCreatedBy(SecurityContextUtil.getUsername());
        sysRoleEntity.setCreationDate(new Date());
        sysRoleEntity.setId(UUIDUtils.idNumber());
        this.save(sysRoleEntity);
    }

    public void updateRole(SysRoleEntity sysRoleEntity) {
        checkAlreadyHave(sysRoleEntity);
        checkAuthority(sysRoleEntity.getId(), RespI18nConstants.ROLE1001.getCode());
        sysRoleEntity.setLastUpdateDate(new Date());
        sysRoleEntity.setLastUpdatedBy(SecurityContextUtil.getUsername());
        this.baseMapper.updateById(sysRoleEntity);
    }

    public void checkNotnull(SysRoleSaveBo sysRoleSaveBo) {
        Assert.isNull(sysRoleSaveBo, RespI18nConstants.COM1005.getCode());
        Assert.isBlank(sysRoleSaveBo.getName(), RespI18nConstants.ROLE1002.getCode());
        Assert.isBlank(sysRoleSaveBo.getRoleType(), RespI18nConstants.ROLE1003.getCode());
        Assert.isBlank(sysRoleSaveBo.getIsEnabled(), RespI18nConstants.ROLE1004.getCode());
        Assert.state(CollectionUtil.isEmpty(sysRoleSaveBo.getUseMenuIdList()), RespI18nConstants.ROLE1005.getCode());
        checkFormat(sysRoleSaveBo);

    }

    public void checkFormat(SysRoleSaveBo sysRoleSaveBo) {
        if (!(Operate.DISABLE.code().equals(sysRoleSaveBo.getIsEnabled()) || Operate.ENABLE.code().equals(sysRoleSaveBo.getIsEnabled()))) {
            Assert.state(true, RespI18nConstants.ROLE1006.getCode());
        }
    }

    public void checkAlreadyHave(SysRoleEntity sysRoleEntity) {
        Long count = countByName(sysRoleEntity.getName(), sysRoleEntity.getId());
        Assert.state(count > 0, RespI18nConstants.ROLE1007.getCode());
    }

    public SysRoleEntity checkAuthority(Long id, String msg) {
        SysRoleEntity role = this.baseMapper.selectById(id);
        Assert.isNull(role, RespI18nConstants.COM1004.getCode());
        boolean eq = SecurityContextUtil.getUsername().equals(role.getCreatedBy());
        //处理超级管理员
        Assert.state((!eq) && SecurityContextUtil.notAdminRole(), msg);
        return role;
    }

    @Override
    public Integer updateStatus(Long id, String status) {
        SysRoleEntity entity = checkAuthority(id, RespI18nConstants.ROLE1008.getCode());
        if (status.equals(Operate.DISABLE.code())) {
            return disable(entity);
        } else {
            return enable(entity);
        }

    }

    @Override
    public SysRoleVo queryOne(Long id) {
        SysRoleVo bo = new SysRoleVo();
        SysRoleEntity role = this.getById(id);
        Assert.isNull(role, RespI18nConstants.COM1004.getCode());
        BeanUtils.copyProperties(role, bo);
        //查询角色 可使用
        List<Long> useMenuIdLis = sysRoleMenuService.queryMenuIdList(id, Operate.NO.code());
        //查询角色 可分配用
        List<Long> allocMenuIdList = sysRoleMenuService.queryMenuIdList(id, Operate.YES.code());
        bo.setAllocMenuIdList(allocMenuIdList);
        bo.setUseMenuIdList(useMenuIdLis);
        //查询使用该角色用户
        List<Long> list = this.baseMapper.queryRoleUserIdByCurrentUser(SecurityContextUtil.getUserId(), id);
        if (CollectionUtil.isNotEmpty(list)) {
            makeUserForIdentity(id, bo, list);

        } else {
            bo.setUserIdList(new ArrayList<>());
            bo.setUnmanageUserIdList(new ArrayList<>());
        }

        return bo;
    }

    /**
     * 用户列表对管理员和非管理员处理
     *
     * @roleId 角色ID
     * @bo 返回实体
     * @list 当前用户可以看得到的使用该角色的用户
     */
    public void makeUserForIdentity(Long roleId, SysRoleVo bo, List<Long> list) {
        //获得角色下用户列表
        List<SysRoleUserEntity> userList = sysRoleUserService.queryListByRoleId(roleId);
        List<Long> userIdList = userList.stream().map(s -> s.getUserId()).collect(Collectors.toList());

        if (SecurityContextUtil.notAdminRole()) {
            //非管理员 取出角色下所有用户和他能看到的用户差集
            List<Long> reduce1 = userIdList.stream().filter(item -> !list.contains(item)).collect(Collectors.toList());
            bo.setUserIdList(list);
            bo.setUnmanageUserIdList(reduce1);
        } else {
            bo.setUserIdList(userIdList);
            bo.setUnmanageUserIdList(new ArrayList<>());
        }

    }


    public Integer enable(SysRoleEntity entity) {
        entity.setIsEnabled(Operate.ENABLE.code());
        return this.baseMapper.updateById(entity);
    }

    public Integer disable(SysRoleEntity entity) {
        checDisable(entity);
        entity.setIsEnabled(Operate.DISABLE.code());
        return this.baseMapper.updateById(entity);
    }

    public void checDisable(SysRoleEntity entity) {
        Long i = sysRoleUserService.countUserByRoleId(entity.getId());
        Assert.state(i > 0, RespI18nConstants.ROLE1009.getCode());
        Assert.state(entity.getId().equals(PlatformAdminRoleIdUtil.getPlatformAdminRoleId()), RespI18nConstants.ROLE1010.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBatch(List roleIds) {
        Boolean flag = false;
        checkDel(roleIds);

        //删除角色
        flag = this.removeByIds(roleIds);
        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);
        //删除角色与用户关联
        Long[] ids = new Long[roleIds.size()];
        roleIds.toArray(ids);
        sysRoleUserService.deleteBatch(ids);
        return flag;
    }

    private void checkDel(List roleIds) {

        Assert.state(roleIds.contains(PlatformAdminRoleIdUtil.getPlatformAdminRoleId()), RespI18nConstants.ROLE1011.getCode());

        List<SysRoleEntity> roleList = queryRoleIdList(roleIds);
        Assert.state(CollectionUtil.isEmpty(roleList), RespI18nConstants.COM1004.getCode());
        for (SysRoleEntity role : roleList) {
            boolean equals = SecurityContextUtil.getUsername().equals(role.getCreatedBy());
            Assert.state(!equals && SecurityContextUtil.notAdminRole(), RespI18nConstants.ROLE1012.getCode());
        }
        List list = sysRoleUserService.queryListByRoleIdList(roleIds);
        Assert.state(list.size() > 0, RespI18nConstants.ROLE1013.getCode());
    }

    @Override
    public List<SysRoleEntity> queryRoleIdList(List roleIds) {
        QueryWrapper<SysRoleEntity> query = new QueryWrapper<>();
        query.lambda().in(SysRoleEntity::getId, roleIds);
        return this.baseMapper.selectList(query);
    }

}
