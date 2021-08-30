/**
 * Copyright (c)2017-2020 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.dao.SysRoleMenuDao;
import com.grgbanking.counter.iam.entity.SysRoleMenuEntity;
import com.grgbanking.counter.iam.service.SysRoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author MARK xx@grgbanking.com
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList, String isLeader) {
        //先删除角色与菜单关系
        deleteBatch(roleId, isLeader);

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        List<SysRoleMenuEntity> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIdList) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setId(UUIDUtils.idNumber());
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);
            sysRoleMenuEntity.setCreationDate(new Date());
            sysRoleMenuEntity.setCreatedBy(SecurityContextUtil.getUsername());
            sysRoleMenuEntity.setIsLeader(isLeader);
            sysRoleMenuEntity.setIsEnabled(Operate.YES.code());
            roleMenuList.add(sysRoleMenuEntity);
        }
        this.saveBatch(roleMenuList);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId, String isLeader) {
        return baseMapper.queryMenuIdList(roleId, isLeader);
    }

    @Override
    public List<SysRoleMenuEntity> getListByMenuId(Long menuId) {
        QueryWrapper<SysRoleMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysRoleMenuEntity::getMenuId, menuId);
        return this.list(wrapper);
    }

    @Override
    public int deleteBatch(Long roleId, String isLeader) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ROLE_ID", roleId);
        if (StringUtils.isNotBlank(isLeader)) {
            map.put("IS_LEADER", isLeader);
        }
        return baseMapper.deleteByMap(map);
    }

    @Override
    public int deleteBatch(List<Long> roleId) {
        return this.baseMapper.deleteBatch(roleId);
    }

}
