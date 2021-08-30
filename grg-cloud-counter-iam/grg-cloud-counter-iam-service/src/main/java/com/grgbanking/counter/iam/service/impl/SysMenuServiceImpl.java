package com.grgbanking.counter.iam.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.Assert;
import com.grgbanking.counter.iam.constans.MenuType;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.dao.SysMenuDao;
import com.grgbanking.counter.iam.entity.*;
import com.grgbanking.counter.iam.service.*;
import com.grgbanking.counter.common.core.util.UUIDUtils;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysMenuBo;
import com.grgbanking.counter.iam.api.vo.SysMenuTreeVo;
import com.grgbanking.counter.iam.api.vo.SysMenuVo;
import com.grgbanking.counter.iam.service.redis.SysMenuRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysMenuAuthorityService sysMenuAuthorityService;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuRedisService sysMenuRedisService;

    @Override
    public List<SysMenuVo> queryList(String appType, String name, String type, String isEnabled) {
        List<SysMenuVo> sysMenuVos = this.baseMapper.getAddAuthorityList(appType, name, type, isEnabled);
        List<Long> uniqueIds = sysMenuVos.stream().map(SysMenuVo::getId).distinct().collect(Collectors.toList());
        List<SysMenuVo> result = new ArrayList<>();
        Map<Long,Object> map=new HashMap<>();
        for(SysMenuVo sysMenuVo:sysMenuVos){
            List<Long> authorityIds = new ArrayList<>();
            if(null != sysMenuVo.getAuthorityId()){
                authorityIds.add(sysMenuVo.getAuthorityId());
                sysMenuVo.setAuthorityIdList(authorityIds);
            }
            if(StringUtils.isNotBlank(sysMenuVo.getAuthority())){
                sysMenuVo.setAuthorityMarks(sysMenuVo.getAuthority());
            }
            map.put(sysMenuVo.getId(),sysMenuVo);
            if(map.containsKey(sysMenuVo.getId()) && MenuType.BUTTON.getValue().equals(sysMenuVo.getType())){
                SysMenuVo vo= (SysMenuVo)map.get(sysMenuVo.getId());
                List<Long> auIds = vo.getAuthorityIdList() == null?new ArrayList<>():vo.getAuthorityIdList();
                auIds.add(sysMenuVo.getAuthorityId());
                vo.setAuthorityIdList(auIds);
                if(StringUtils.isNotBlank(vo.getAuthorityMarks()) && StringUtils.isNotBlank(sysMenuVo.getAuthority())){
                    vo.setAuthorityMarks(vo.getAuthorityMarks()+","+sysMenuVo.getAuthority());
                }
                if(StringUtils.isBlank(vo.getAuthorityMarks()) && StringUtils.isNotBlank(sysMenuVo.getAuthority())){
                    vo.setAuthorityMarks(sysMenuVo.getAuthority());
                }
                map.put(sysMenuVo.getId(),vo);
            }
        }
        for(Long mapId:uniqueIds){
            SysMenuVo mapMenu = (SysMenuVo)map.get(mapId);
            result.add(mapMenu);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIds(List<Long> ids) {
        if (ids.size() < 1) {
            throw new CheckedException(RespI18nConstants.COM2001.getCode());
        }
        for (Long id : ids) {
            SysMenuEntity menu = this.baseMapper.selectById(id);
            List<SysMenuEntity> childList = getChildsByMenuPath(menu.getMenuPath(), id);
            if (childList != null && childList.size() > 0) {
                throw new CheckedException(RespI18nConstants.MENU1002.getCode());
            }
            List<SysRoleMenuEntity> roleMenuList = sysRoleMenuService.getListByMenuId(id);
            if (roleMenuList != null && roleMenuList.size() > 0) {
                throw new CheckedException(RespI18nConstants.MENU1001.getCode());
            }
            List<SysMenuEntity> childStopList = getChildsStopByMenuPath(menu.getMenuPath());
            List<Long> childIds = childStopList.stream().map(SysMenuEntity::getId).distinct().collect(Collectors.toList());
            childIds.add(id);
            Boolean flag = this.removeByIds(childIds);//删除菜单及子菜单
            sysMenuAuthorityService.deleteAuthorityByMenuIds(childIds);//删除权限标识
            if (flag) {//删除缓存
                sysMenuRedisService.delMenuCaches();
            }
        }
    }

    /**
     * 根据menuPath查询启用的子菜单
     */
    @Override
    public List<SysMenuEntity> getChildsByMenuPath(String menuPath, Long id) {
        QueryWrapper<SysMenuEntity> wq = new QueryWrapper<>();
        wq.lambda().likeRight(SysMenuEntity::getMenuPath, menuPath)
                .ne(SysMenuEntity::getId, id)
                .eq(SysMenuEntity::getIsEnabled, Operate.ENABLE.code());
        return this.list(wq);
    }

    @Override
    public void updateStatusByMenuId(Long id, String isEnabled) {
        SysMenuEntity menu = this.baseMapper.selectById(id);
        if (Operate.DISABLE.code().equals(isEnabled)) {//停用
            List<SysMenuEntity> childList = getChildsByMenuPath(menu.getMenuPath(), id);
            if (childList != null && childList.size() > 0) {
                throw new CheckedException(RespI18nConstants.MENU1003.getCode());
            }
            updateMenuAndRoleMenu(menu, isEnabled);
        }
        if (Operate.ENABLE.code().equals(isEnabled)) {//启用
            List<SysMenuEntity> parentList = getParentStopByParentCode(menu.getParentCode());
            if (parentList != null && parentList.size() > 0) {
                throw new CheckedException(RespI18nConstants.MENU1004.getCode());
            }
            updateMenuAndRoleMenu(menu, isEnabled);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateMenuAndRoleMenu(SysMenuEntity menu, String isEnabled) {
        menu.setIsEnabled(isEnabled);
        menu.setLastUpdatedBy(SecurityContextUtil.getUsername());
        menu.setLastUpdateDate(new DateTime());
        int count = this.baseMapper.updateById(menu);
        List<SysRoleMenuEntity> roleMenuList = sysRoleMenuService.getListByMenuId(menu.getId());
        List<SysRoleMenuEntity> resultList = new ArrayList<>();
        if (roleMenuList != null && roleMenuList.size() > 0) {
            for (SysRoleMenuEntity roleMenu : roleMenuList) {
                roleMenu.setIsEnabled(isEnabled);
                roleMenu.setLastUpdatedBy(SecurityContextUtil.getUsername());
                roleMenu.setLastUpdateDate(new DateTime());
                resultList.add(roleMenu);
            }
            this.sysRoleMenuService.updateBatchById(resultList);
        }
        if (count > 0) {//删除缓存
            sysMenuRedisService.delMenuCaches();
        }
    }

    /**
     * 根据子菜单的menuPath查询停用的子菜单
     */
    public List<SysMenuEntity> getChildsStopByMenuPath(String menuPath) {
        QueryWrapper<SysMenuEntity> wq = new QueryWrapper<>();
        wq.lambda().likeLeft(SysMenuEntity::getMenuPath, menuPath)
                .eq(SysMenuEntity::getIsEnabled, Operate.DISABLE.code());
        return this.list(wq);
    }

    /**
     * 根据子菜单的parentCode查询停用的父级菜单
     */
    public List<SysMenuEntity> getParentStopByParentCode(String parentCode) {
        QueryWrapper<SysMenuEntity> wq = new QueryWrapper<>();
        wq.lambda().eq(SysMenuEntity::getMenuCode, parentCode)
                .eq(SysMenuEntity::getIsEnabled, Operate.DISABLE.code());
        return this.list(wq);
    }

    /**
     * 根据用户名查询启用状态中的可分配菜单的权限标识列表
     */
    @Override
    public List<SysAuthorityEntity> getAuthorityListByUsername(String username) {
        Long userId = null;
        List<SysAuthorityEntity> result = new ArrayList<>();
        if (StringUtils.isBlank(username)) {
            userId = SecurityContextUtil.getUserId();
        } else {
            SysUserEntity user = sysUserService.getUserByUsername(username);
            if (user == null) {
                throw new CheckedException(RespI18nConstants.COM1007.getCode());
            }
            userId = user.getId();
        }
        List<SysAuthorityEntity> authorityList = sysUserService.queryAuthorityListByUserId(userId,Operate.ENABLE.getCode(),Operate.LEADER.getCode());
        if(CollectionUtil.isNotEmpty(authorityList)){
            return authorityList;
        }
        return result;
    }

    /**
     * 超级管理员查询启用状态中的所有菜单树
     */
    @Override
    public List<SysMenuTreeVo> getEnabledMenuAllTree() {
        QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuEntity::getIsEnabled, Operate.ENABLE.code());
        List<SysMenuEntity> menuList = this.list(wrapper);
        List<SysMenuTreeVo> treeVo = new ArrayList<>();
        BeanUtils.copyProperties(menuList, treeVo,SysMenuTreeVo.class);
        if (treeVo != null && treeVo.size() > 0) {
            return buildMenuTree(treeVo);
        }
        return new ArrayList<>();
    }


    /**
     * 根据用户ID查询启用状态中的可分配菜单树
     */
    @Override
    public List<SysMenuTreeVo> getMenuLeaderTree(Long userId) {
         List<SysMenuEntity> menuList = this.baseMapper.getMenuListByUserId(userId,Operate.ENABLE.getCode(),Operate.LEADER.code());
         if(CollectionUtil.isNotEmpty(menuList)){
             List<SysMenuTreeVo> treeVo = new ArrayList<>();
             BeanUtils.copyProperties(menuList, treeVo, SysMenuTreeVo.class);
             return buildMenuTree(treeVo);
         }
        return new ArrayList<>();
    }

    /**
     * 根据用户ID查询启用状态中的可使用菜单树
     */
    @Override
    public List<SysMenuTreeVo> getMenuUseTree(Long userId) {
        List<SysMenuEntity> menuList = this.baseMapper.getMenuListByUserId(userId,Operate.ENABLE.getCode(),Operate.DISLEADER.getCode());
        if(CollectionUtil.isNotEmpty(menuList)){
            List<SysMenuTreeVo> treeVo = new ArrayList<>();
            BeanUtils.copyProperties(menuList, treeVo, SysMenuTreeVo.class);
            return buildMenuTree(treeVo);
        }
        return new ArrayList<>();
    }

    /**
     * 根据菜单id查询菜单信息
     */
    @Override
    public SysMenuVo queryMenuVoById(Long id) {
        SysMenuVo sysMenuVo = new SysMenuVo();
        String authorityMarks = null;
        SysMenuEntity menu = this.baseMapper.selectById(id);
        if (menu == null) {
            throw new CheckedException(RespI18nConstants.MENU1005.getCode());
        }
        sysMenuVo = new SysMenuVo();
        BeanUtils.copyProperties(menu, sysMenuVo);
        QueryWrapper<SysMenuAuthorityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuAuthorityEntity::getMenuId, id);
        List<SysMenuAuthorityEntity> menuAuthList = sysMenuAuthorityService.list(wrapper);
        if (menuAuthList != null && menuAuthList.size() > 0) {
            List<Long> authorityIdList = menuAuthList.stream().map(SysMenuAuthorityEntity::getAuthorityId).distinct().collect(Collectors.toList());
            authorityMarks = sysAuthorityService.getAuthorityToString(authorityIdList);
            sysMenuVo.setAuthorityIdList(authorityIdList);
            sysMenuVo.setAuthorityMarks(authorityMarks);
        }
        return sysMenuVo;
    }

    /**
     * 新增菜单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveMenuByBo(SysMenuBo bo) {
        checkMenuBo(bo);//参数不为空判断
        checkNameUnique(bo);//名称校验
        if (StringUtils.isBlank(bo.getIsEnabled())) {
            throw new CheckedException(RespI18nConstants.COM2003.getCode());
        }
        Long codeNumber = getCountByMenuCode(bo.getMenuCode());
        if (codeNumber > 0) {
            throw new CheckedException(RespI18nConstants.MENU1006.getCode());
        }
        checkMenuData(bo);//菜单新增规则判断
        SysMenuEntity sysMenu = new SysMenuEntity();
        Long id = UUIDUtils.idNumber();
        sysMenu.setId(id);
        sysMenu.setMenuCode(bo.getMenuCode());
        sysMenu.setAppType(bo.getAppType());
        sysMenu.setType(bo.getType());
        sysMenu.setName(bo.getName());
        sysMenu.setI18nCode(bo.getI18nCode() == null ? "" : bo.getI18nCode());
        if (MenuType.SYSTEM.getValue().equals(bo.getType())) {//系统
            sysMenu.setParentCode(MenuType.SYSTEM_PARENT.getValue());
            sysMenu.setIcon(bo.getIcon() == null ? "" : bo.getIcon());
            sysMenu.setPath(bo.getPath() == null ? "" : bo.getPath());
            sysMenu.setHost(bo.getHost() == null ? "" : bo.getHost());
            sysMenu.setPort(bo.getPort() == null ? "" : bo.getPort());
        }
        if (!MenuType.SYSTEM.getValue().equals(bo.getType())) {//非系统
            if (StringUtils.isBlank(bo.getParentCode())) {
                throw new CheckedException(RespI18nConstants.MENU1007.getCode());
            }
            sysMenu.setParentCode(bo.getParentCode());
        }
        if (MenuType.CATALOG.getValue().equals(bo.getType())) {//目录
            sysMenu.setIcon(bo.getIcon() == null ? "" : bo.getIcon());
        }
        if (MenuType.MENU.getValue().equals(bo.getType())) {//菜单
            sysMenu.setPath(bo.getPath() == null ? "" : bo.getPath());
        }
        if (MenuType.BUTTON.getValue().equals(bo.getType())) {//按钮
            //权限标识
            List<Long> authorityIdList = bo.getAuthorityIdList();
            int count = addMenuAuthrotysByauIds(authorityIdList, id);
        }
        sysMenu.setOrderNum(bo.getOrderNum() == null ? new Integer(0) : bo.getOrderNum());
        sysMenu.setIsEnabled(bo.getIsEnabled());
        //机构path
        formMenuPath(sysMenu);
        sysMenu.setCreatedBy(SecurityContextUtil.getUsername());
        sysMenu.setCreationDate(new DateTime());
        sysMenu.setLastUpdatedBy(SecurityContextUtil.getUsername());
        sysMenu.setLastUpdateDate(new DateTime());
        int row = this.baseMapper.insert(sysMenu);
        if (row > 0) {
            //删除缓存
            sysMenuRedisService.delMenuCaches();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMenuByBo(SysMenuBo bo) {
        if (null == bo.getId()) {
            throw new CheckedException(RespI18nConstants.COM2002.getCode());
        }
        checkMenuBo(bo);//参数不为空判断
        checkNameUnique(bo);//名称校验
        SysMenuEntity oldSysMenu = this.baseMapper.selectById(bo.getId());
        if (oldSysMenu != null) {
            if (!MenuType.SYSTEM.getValue().equals(oldSysMenu.getType())) {//原先非系统
                if (StringUtils.isBlank(bo.getParentCode())) {
                    throw new CheckedException(RespI18nConstants.MENU1007.getCode());
                }
                if (!bo.getParentCode().equals(oldSysMenu.getParentCode())) {
                    oldSysMenu.setParentCode(bo.getParentCode());
                    //结构path
                    formMenuPath(oldSysMenu);
                }
            }
            if (MenuType.BUTTON.getValue().equals(oldSysMenu.getType())) {//原先是按钮
                oldSysMenu.setType(bo.getType());
                //删除旧的权限标识
                sysMenuAuthorityService.deleteAuthorityByMenuId(oldSysMenu.getId());
                if (MenuType.BUTTON.getValue().equals(bo.getType())) {//修改还是按钮
                    //添加新的权限标识
                    List<Long> authorityIdList = bo.getAuthorityIdList();
                    int count = addMenuAuthrotysByauIds(authorityIdList, bo.getId());
                }
            }
            oldSysMenu.setAppType(bo.getAppType());
            oldSysMenu.setName(bo.getName());
            if (StringUtils.isNotBlank(bo.getHost())) {
                oldSysMenu.setHost(bo.getHost());
            }
            if (StringUtils.isNotBlank(bo.getPort())) {
                oldSysMenu.setPort(bo.getPort());
            }
                oldSysMenu.setI18nCode(bo.getI18nCode()==null?"":bo.getI18nCode());
            if (StringUtils.isNotBlank(bo.getPath())) {
                oldSysMenu.setPath(bo.getPath());
            }
                oldSysMenu.setOrderNum(bo.getOrderNum()==null ? new Integer(0) : bo.getOrderNum());
            if(StringUtils.isNotBlank(bo.getIcon())){
                oldSysMenu.setIcon(bo.getIcon());
            }
            checkMenuData(bo);//编辑菜单规则校验
            oldSysMenu.setLastUpdatedBy(SecurityContextUtil.getUsername());
            oldSysMenu.setLastUpdateDate(new DateTime());
            int count = this.baseMapper.updateById(oldSysMenu);
            if (count > 0) {//删除缓存
                sysMenuRedisService.delMenuCaches();
                return true;
            }
        }
        return false;
    }
    /**
     * 除本身外，同一菜单下，按钮名称唯一
     * 除本身外，同一应用类型下，系统、目录、菜单名称唯一
     * */
    public void checkNameUnique(SysMenuBo bo){
      if(MenuType.BUTTON.getValue().equals(bo.getType())){//按钮
          QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
          queryWrapper.lambda().eq(SysMenuEntity::getParentCode,bo.getParentCode())
                  .eq(SysMenuEntity::getName,bo.getName());
          if(null != bo.getId()){
              queryWrapper.lambda().ne(SysMenuEntity::getId,bo.getId());
          }
          Long row = this.count(queryWrapper);
          Assert.state(row>0,RespI18nConstants.MENU1021.getCode());
      }else{
          QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
          wrapper.lambda().eq(SysMenuEntity::getAppType,bo.getAppType())
                  .eq(SysMenuEntity::getName,bo.getName());
          if(null != bo.getId()){
              wrapper.lambda().ne(SysMenuEntity::getId,bo.getId());
          }
          Long row = this.count(wrapper);
          Assert.state(row>0,RespI18nConstants.MENU1022.getCode());
      }
    }

    public void checkMenuBo(SysMenuBo bo) {
        if (StringUtils.isBlank(bo.getMenuCode())) {
            throw new CheckedException(RespI18nConstants.MENU1008.getCode());
        }
        if (StringUtils.isBlank(bo.getAppType())) {
            throw new CheckedException(RespI18nConstants.COM2004.getCode());
        }
        if (StringUtils.isBlank(bo.getType())) {
            throw new CheckedException(RespI18nConstants.MENU1009.getCode());
        }
        if (StringUtils.isBlank(bo.getName())) {
            throw new CheckedException(RespI18nConstants.MENU1010.getCode());
        }
    }

    /**
     * 根据MENU_CODE查询菜单数量
     */
    public Long getCountByMenuCode(String menuCode) {
        QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuEntity::getMenuCode, menuCode);
        return this.count(wrapper);
    }

    /**
     * 根据PARENT_CODE查询父级菜单
     */
    @Override
    public SysMenuEntity getParentMenuByMenuCode(String parentCode) {
        QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenuEntity::getMenuCode, parentCode);
        return this.baseMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addMenuAuthrotysByauIds(List<Long> authorityIdList, Long id) {
        int count = 0;
        if (authorityIdList != null && authorityIdList.size() > 0) {
            for (Long authId : authorityIdList) {
                SysMenuAuthorityEntity sysMenuAuth = new SysMenuAuthorityEntity();
                sysMenuAuth.setId(UUIDUtils.idNumber());
                sysMenuAuth.setAuthorityId(authId);
                sysMenuAuth.setMenuId(id);
                sysMenuAuth.setIsEnabled(Operate.ENABLE.code());
                sysMenuAuth.setCreatedBy(SecurityContextUtil.getUsername());
                sysMenuAuth.setCreationDate(new DateTime());
                sysMenuAuth.setLastUpdatedBy(SecurityContextUtil.getUsername());
                sysMenuAuth.setLastUpdateDate(new DateTime());
                int res = sysMenuAuthorityService.getBaseMapper().insert(sysMenuAuth);
                if (res > 0) {
                    count++;
                }
            }

        }
        return count;
    }

    /**
     * 根据传入菜单获取菜单树
     */
    public static List<SysMenuTreeVo> buildMenuTree(List<SysMenuTreeVo> treeNodes) {
        List<SysMenuTreeVo> result = new ArrayList<>();
        Map<String, SysMenuTreeVo> mapAll = new LinkedHashMap<>();
        for (SysMenuTreeVo node : treeNodes) {
            mapAll.put(node.getMenuCode(), node);
        }
        Set<Map.Entry<String, SysMenuTreeVo>> entrySet = mapAll.entrySet();
        for (Map.Entry<String, SysMenuTreeVo> entry : entrySet) {
            String pid = entry.getValue().getParentCode();
            SysMenuTreeVo parentMenu = mapAll.get(pid);
            if (parentMenu == null) {
                result.add(entry.getValue());
            } else {
                List<SysMenuTreeVo> children = parentMenu.getChildrenList() == null ? new ArrayList<>() : parentMenu.getChildrenList();
                children.add(entry.getValue());
                parentMenu.setChildrenList(children);
            }
        }
        return result;
    }

    /**
     * 菜单名称唯一性校验和关联逻辑性校验
     *
     */
    private void checkMenuData(SysMenuBo bo) {
        /**
         * 菜单规则：
         * 现在系统的type是-1，目录的type是0，菜单的type是1，按钮的type是2
         * 	1. 根目录只能为系统。系统下可以有0，1，2
         * 	2. 目录下可以有0，1
         * 	3. 菜单下可以有2
         *  4. 按钮下不能有其他
         */
        // 非系统
        if (!MenuType.SYSTEM.getValue().equals(bo.getType())) {
            SysMenuEntity parentMenu = getParentMenuByMenuCode(bo.getParentCode());
            if (parentMenu == null) {
                throw new CheckedException(RespI18nConstants.MENU1011.getCode());
            }
            //上级菜单类型
            String parentType = parentMenu.getType();

            //系统
            if (MenuType.SYSTEM.getValue().equals(parentType)) {
                if (!MenuType.CATALOG.getValue().equals(bo.getType())) {
                    throw new CheckedException(RespI18nConstants.MENU1012.getCode());
                } else if (countByMenuName(bo)) {
                    throw new CheckedException(RespI18nConstants.MENU1013.getCode());
                }
            }
            //父节点是目录
            if (MenuType.CATALOG.getValue().equals(parentType)) {
                if (MenuType.BUTTON.getValue().equals(bo.getType())) {
                    throw new CheckedException(RespI18nConstants.MENU1014.getCode());
                } else if (countByMenuName(bo)) {
                    throw new CheckedException(RespI18nConstants.MENU1015.getCode());
                }
            }
            //父节点是菜单
            if (MenuType.MENU.getValue().equals(parentType)) {
                if (!MenuType.BUTTON.getValue().equals(bo.getType())) {
                    throw new CheckedException(RespI18nConstants.MENU1016.getCode());
                } else if (countByMenuName(bo)) {
                    throw new CheckedException(RespI18nConstants.MENU1017.getCode());
                }
            }
            //按钮
            if (MenuType.BUTTON.getValue().equals(parentType)) {
                throw new CheckedException(RespI18nConstants.MENU1018.getCode());
            }

            Long menuId = bo.getId();
            // menuId为空时，新增
            if (null != menuId) {
                // 通过menuId获取DB中存在的数据
                SysMenuEntity existMenuFromDB = this.baseMapper.selectById(menuId);
                // 当前端数据与DB数据的父节点id或者菜单类型不一样时，需要判断当前菜单是否有子菜单。
                if (!bo.getParentCode().equals(existMenuFromDB.getParentCode())
                        || !bo.getType().equals(existMenuFromDB.getType())) {
                    QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
                    wrapper.lambda().eq(SysMenuEntity::getParentCode, existMenuFromDB.getMenuCode());
                    List<SysMenuEntity> childMenuList = baseMapper.selectList(wrapper);
                    // 当前菜单中有子菜单，则不允许其修改父节点。
                    if (childMenuList != null && childMenuList.size() > 0) {
                        throw new CheckedException(RespI18nConstants.MENU1019.getCode());
                    }
                }
            }
            //系统
        } else {
            if (bo.getId() != null) {//修改才判断
                String parentCode = bo.getParentCode();
                if (!MenuType.SYSTEM_PARENT.getValue().equals(parentCode) && bo.getType().equals(MenuType.SYSTEM.getValue())) {
                    throw new CheckedException(RespI18nConstants.MENU1020.getCode());
                }
            }

        }
    }

    /**
     * 设置菜单的结构path
     */
    public void formMenuPath(SysMenuEntity entity) {
        if (MenuType.SYSTEM_PARENT.getValue().equals(entity.getParentCode())) {
            entity.setMenuPath(entity.getMenuCode());
        } else {
            //查询它的父级path
            SysMenuEntity sysMenuEntity = getParentMenuByMenuCode(entity.getParentCode());
            String s = makeMenuPath(sysMenuEntity.getMenuPath());
            entity.setMenuPath(s);
        }
    }

    /**
     * 根据父节点rPath计算子节点Path
     *
     * @param parentPath
     * @return
     */
    public String makeMenuPath(String parentPath) {
        String parentChild = parentPath + "_____";
        int value = 1;
        try {
            String maxId = baseMapper.getMenuPathsByParent(parentChild);
            if (maxId != null) {
                int lastFlagIndex = maxId.lastIndexOf("_") + 1;
                String valueS = maxId.substring(lastFlagIndex);
                value = Integer.valueOf(valueS) + 1;
            } else {
                return parentPath + "_1001";
            }
        } catch (Exception e) {
            log.error("RegionDaoImpl createRegionId error !", e);
        }
        String tmp = new DecimalFormat("0000").format(value);
        String result = parentPath + "_" + tmp;
        return result;
    }

    public boolean countByMenuName(SysMenuBo bo) {
        QueryWrapper<SysMenuEntity> qw = new QueryWrapper<>();
        // 新增
        qw.lambda().eq(SysMenuEntity::getAppType, bo.getAppType())
                .eq(SysMenuEntity::getParentCode, bo.getParentCode())
                .eq(SysMenuEntity::getName, bo.getName());
        // 编辑
        if (null != bo.getId()) {
            qw.lambda().ne(SysMenuEntity::getId, bo.getId());
        }

        return this.count(qw) > 0;
    }
}
