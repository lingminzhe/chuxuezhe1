package com.grgbanking.counter.iam.controller;

import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysMenuService;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysMenuBo;
import com.grgbanking.counter.iam.api.vo.SysMenuTreeVo;
import com.grgbanking.counter.iam.api.vo.SysMenuVo;
import com.grgbanking.counter.iam.service.redis.SysMenuRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理接口
 */
@RestController
@Api(value = "菜单管理接口", tags = {"菜单管理接口"})
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuRedisService sysMenuRedisService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据应用类型、名称、资源类型、启用状态查询菜单列表
     */
    @ApiOperation(value = "根据应用类型、名称、资源类型、启用状态查询菜单列表")
    @GetMapping("/query/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appType", value = "应用类型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "资源类型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isEnabled", value = "启用状态", required = false, paramType = "query", dataType = "String")
    })
    public Resp<List<SysMenuVo>> queryList(String appType, String name, String type, String isEnabled) {
        List<SysMenuVo> result = sysMenuService.queryList(appType, name, type, isEnabled);
        return Resp.ok(result);
    }


    /**
     * 根据菜单id查询菜单信息
     */
    @ApiOperation(value = "根据菜单id查询菜单信息")
    @GetMapping("/query/{id}")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "path", dataType = "Long")
    public Resp<SysMenuVo> info(@PathVariable("id") Long id) {
        SysMenuVo result = sysMenuService.queryMenuVoById(id);
        return Resp.ok(result);
    }

    /**
     * 根据用户名查询启用状态中的可分配菜单的权限标识列表
     */
    @ApiOperation(value = "根据用户名查询启用状态中的可分配菜单的权限标识列表")
    @GetMapping("/authority/alloc/{username}")
    public Resp<List<SysAuthorityEntity>> allocAuthorityList(@PathVariable("username") String username) {
        List<SysAuthorityEntity> result = sysMenuService.getAuthorityListByUsername(username);
        return Resp.ok(result);
    }

    /**
     * 根据用户名查询启用状态中的可分配菜单树
     */
    @ApiOperation(value = "根据用户名查询启用状态中的可分配菜单树")
    @GetMapping("/tree/alloc/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String")
    public Resp<List<SysMenuTreeVo>> getMenuTree(@PathVariable("username") String username) {
        Long userId = null;
        if (StringUtils.isBlank(username)) {
            if (SecurityContextUtil.hasAdminRole()) {//超级管理员
                return Resp.ok(sysMenuService.getEnabledMenuAllTree());
            }
            userId = SecurityContextUtil.getUserId();
        } else {
            SysUserEntity user = sysUserService.getUserByUsername(username);
            if (user == null) {
                throw new CheckedException(RespI18nConstants.COM1007.getCode());
            }
            userId = user.getId();
            Boolean boss = sysUserService.isSuperUser(userId);
            if (boss) {//超级管理员
                return Resp.ok(sysMenuService.getEnabledMenuAllTree());
            }
        }
        List<SysMenuTreeVo> cacheResult = sysMenuRedisService.getAllocCacheTree(username);
        if (cacheResult != null && cacheResult.size() > 0) {
            return Resp.ok(cacheResult);
        }
        List<SysMenuTreeVo> result = sysMenuService.getMenuLeaderTree(userId);
        //缓存保存
        if (result != null && result.size() > 0) {
            sysMenuRedisService.saveAllocCacheTree(username, result);
        }
        return Resp.ok(result);
    }

    /**
     * 根据用户名查询启用状态中的可使用菜单树
     */
    @ApiOperation(value = "根据用户名查询启用状态中的可使用菜单树")
    @GetMapping("/tree/use/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "path", dataType = "String")
    public Resp<List<SysMenuTreeVo>> getMenuUseTree(@PathVariable("username") String username) {
        Long userId = null;
        if (StringUtils.isBlank(username)) {
            if (SecurityContextUtil.hasAdminRole()) {//超级管理员
                return Resp.ok(sysMenuService.getEnabledMenuAllTree());
            }
            userId = SecurityContextUtil.getUserId();
        } else {
            SysUserEntity user = sysUserService.getUserByUsername(username);
            if (user == null) {
                throw new CheckedException(RespI18nConstants.COM1007.getCode());
            }
            userId = user.getId();
            Boolean boss = sysUserService.isSuperUser(userId);
            if (boss) {//超级管理员
                return Resp.ok(sysMenuService.getEnabledMenuAllTree());
            }
        }
        List<SysMenuTreeVo> cacheResult = sysMenuRedisService.getUseCacheTree(username);
        if (cacheResult != null && cacheResult.size() > 0) {
            return Resp.ok(cacheResult);
        }
        List<SysMenuTreeVo> result = sysMenuService.getMenuUseTree(userId);
        //缓存保存
        if (result != null && result.size() > 0) {
            sysMenuRedisService.saveUseCacheTree(username, result);
        }
        return Resp.ok(result);
    }

    /**
     * 保存菜单
     */
    @SysLog("保存菜单")
    @ApiOperation(value = "保存菜单")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys:menu:add')")
    public Resp save(@RequestBody SysMenuBo sysMenuBo) {
       Boolean flag = sysMenuService.saveMenuByBo(sysMenuBo);
       if(flag){
           return Resp.ok();
       }else{
         return Resp.error(RespI18nConstants.COM1006.getCode());
       }
    }

    /**
     * 修改菜单
     */
    @SysLog("修改菜单")
    @ApiOperation(value = "修改菜单")
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys:menu:edit')")
    public Resp update(@RequestBody SysMenuBo sysMenuBo) {
       Boolean flag = sysMenuService.updateMenuByBo(sysMenuBo);
        if(flag){
            return Resp.ok();
        }else{
            return Resp.error(RespI18nConstants.COM1006.getCode());
        }
    }

    /**
     * 根据菜单id修改菜单状态（停用、启用）
     */
    @SysLog("根据菜单id修改菜单状态（停用、启用）")
    @ApiOperation(value = "根据菜单id修改菜单状态（停用、启用）")
    @PostMapping("/update/status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "isEnabled", value = "是否启用", required = true, paramType = "query", dataType = "String")
    })
    @PreAuthorize("@pms.hasPermission('sys:menu:switch')")
    public Resp updateStatus(@RequestParam("id") Long id, @RequestParam("isEnabled") String isEnabled) {
        sysMenuService.updateStatusByMenuId(id, isEnabled);
        return Resp.ok();
    }

    /**
     * 根据菜单id列表删除菜单信息
     */
    @SysLog("根据菜单id列表删除菜单信息")
    @ApiOperation(value = "根据菜单id列表删除菜单信息")
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('sys:menu:delete')")
    public Resp delete(@RequestBody List<Long> idList) {
        sysMenuService.deleteIds(idList);
        return Resp.ok();
    }

}
