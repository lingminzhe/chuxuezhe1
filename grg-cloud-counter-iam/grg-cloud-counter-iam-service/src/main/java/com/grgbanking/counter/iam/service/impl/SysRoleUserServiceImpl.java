package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.iam.dao.SysRoleUserDao;
import com.grgbanking.counter.iam.entity.SysRoleUserEntity;
import com.grgbanking.counter.iam.service.SysRoleUserService;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户与角色对应关系
 *
 * @author MARK xx@grgbanking.com
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserDao, SysRoleUserEntity> implements SysRoleUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (roleIdList == null || roleIdList.size() == 0) {
            return false;
        }
        //先删除用户与角色关系
        delRoleUserByUserId(Arrays.asList(userId));

        List<SysRoleUserEntity> role = new ArrayList<>();
        //保存用户与角色关系
        for (Long roleId : roleIdList) {
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);
            sysRoleUserEntity.setId(UUIDUtils.idNumber());
            sysRoleUserEntity.setCreationDate(new Date());
            sysRoleUserEntity.setCreatedBy(SecurityContextUtil.getUsername());
            role.add(sysRoleUserEntity);
        }
        return this.saveBatch(role);
    }

    @Override
    public boolean delRoleUserByUserId(List<Long> userIds) {
        //删除用户与区域关系
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().in(SysRoleUserEntity::getUserId, userIds);
        int delete = this.baseMapper.delete(ew);
        return delete > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(List<Long> userId, Long roleId) {
        //先删除用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("ROLE_ID", roleId);
        this.removeByMap(map);

        if (userId == null || userId.size() == 0) {
            return;
        }
         List<SysRoleUserEntity> roleUserList = new ArrayList<>();
        //保存用户与角色关系
        for (Long user : userId) {
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(user);
            sysRoleUserEntity.setRoleId(roleId);
            sysRoleUserEntity.setCreatedBy(SecurityContextUtil.getUsername());
            sysRoleUserEntity.setCreationDate(new Date());
            sysRoleUserEntity.setId(UUIDUtils.idNumber());
            roleUserList.add(sysRoleUserEntity);
        }
        this.saveBatch(roleUserList);
    }

    @Override
    public List<SysRoleUserEntity> queryListByRoleId(Long roleId) {
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysRoleUserEntity::getRoleId, roleId);
        return this.baseMapper.selectList(ew);
    }

    @Override
    public List<SysRoleUserEntity> queryListByRoleIdList(List<Long> roleIds) {
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().in(SysRoleUserEntity::getRoleId, roleIds);
        return this.baseMapper.selectList(ew);
    }

    @Override
    public Long countUserByRoleId(Long roleId) {
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysRoleUserEntity::getRoleId, roleId);
        return this.baseMapper.selectCount(ew);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }

    @Override
    public List<SysRoleUserEntity> queryAllRoleIdByUserId(Long userId) {
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().eq(SysRoleUserEntity::getUserId, userId);
        List<SysRoleUserEntity> sysRoleUserEntities = this.baseMapper.selectList(ew);
        return sysRoleUserEntities;
    }

    @Override
    public List<SysRoleUserEntity> queryAllRoleByUserId(List<Long> userIds) {
        QueryWrapper<SysRoleUserEntity> ew = new QueryWrapper<>();
        ew.lambda().in(SysRoleUserEntity::getUserId, userIds);
        List<SysRoleUserEntity> sysRoleUserEntities = this.baseMapper.selectList(ew);
        return sysRoleUserEntities;
    }

    @Override
    public List<Long> queryAllRoleIdByUserId(List<Long> userIds) {
        List<SysRoleUserEntity> roleUserList = queryAllRoleByUserId(userIds);
        if (CollectionUtil.isNotEmpty(roleUserList)) {
            List<Long> roleList = roleUserList.stream().map(s -> s.getRoleId()).distinct().collect(Collectors.toList());
            return roleList;
        }
        return new ArrayList<>();
    }

}
