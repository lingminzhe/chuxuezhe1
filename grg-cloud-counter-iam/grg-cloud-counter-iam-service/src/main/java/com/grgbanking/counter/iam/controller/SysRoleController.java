package com.grgbanking.counter.iam.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysRoleEntity;
import com.grgbanking.counter.iam.service.SysRoleService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysRoleBo;
import com.grgbanking.counter.iam.api.bo.SysRoleListBo;
import com.grgbanking.counter.iam.api.bo.SysRoleSaveBo;
import com.grgbanking.counter.iam.api.vo.SysRoleVo;
import com.grgbanking.counter.iam.service.redis.SysMenuRedisService;
import com.grgbanking.counter.iam.service.redis.SysOrgRedisService;
import com.grgbanking.counter.iam.service.redis.SysRoleRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理接口
 *
 * @author MARK xx@grgbanking.com
 */
@RestController
@Api(value = "角色管理接口", tags = "角色管理接口")
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleRedisService sysRoleRedisService;

    @Autowired
    private SysOrgRedisService sysOrgRedisService;

    @Autowired
    private SysMenuRedisService sysMenuRedisService;

    /**
     * 角色列表
     */
    @SysLog("角色列表查询")
    @GetMapping("/query/list")
    @ApiOperation(value = "角色列表", notes = "列表")
    public Resp<IPage<SysRoleVo>> list(Page page, SysRoleListBo sysRoleListBo) {
        IPage<SysRoleVo> iPage = sysRoleService.queryPage(page, sysRoleListBo);
        return Resp.ok(iPage);
    }

    /**
     * 保存角色
     */
    @SysLog("角色保存")
    @PreAuthorize("@pms.hasPermission('sys:role:add')")
    @ApiOperation(value = "角色新增", notes = "新增")
    @PostMapping("/save")
    public Resp save(@RequestBody SysRoleBo sysRoleBo) {
        SysRoleSaveBo bo = new SysRoleSaveBo();
        BeanUtils.copyProperties(sysRoleBo, bo);
        sysRoleService.saveOrUpdate(bo);
        sysRoleRedisService.delCache();
        sysOrgRedisService.delCacheListTree();
        //删除菜单的缓存
        sysMenuRedisService.delMenuCaches();
        return Resp.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("角色修改")
    @PreAuthorize("@pms.hasPermission('sys:role:edit')")
    @ApiOperation(value = "角色修改", notes = "修改角色")
    @PostMapping("/update")
    public Resp update(@RequestBody SysRoleSaveBo sysRoleSaveBo) {
        sysRoleService.saveOrUpdate(sysRoleSaveBo);
        sysRoleRedisService.delCache();
        sysOrgRedisService.delCacheListTree();
        //删除菜单的缓存
        sysMenuRedisService.delMenuCaches();
        return Resp.ok();
    }

    /**
     * 停用启用
     */
    @SysLog("角色停用启用")
    @PreAuthorize("@pms.hasPermission('sys:role:switch')")
    @ApiOperation(value = "停用启用", notes = "有用户使用的不能停用")
    @PostMapping("/update/status")
    public Resp updateStatus(@RequestParam("id") Long id, @RequestParam("isEnabled") String isEnabled) {
        Integer num = sysRoleService.updateStatus(id, isEnabled);
        if (num > 0) {
            sysRoleRedisService.delCache();
            sysOrgRedisService.delCacheListTree();
            //删除菜单的缓存
            sysMenuRedisService.delMenuCaches();
            return Resp.ok();
        }
        return Resp.error();
    }

    /**
     * 删除
     */
    @SysLog("角色删除")
    @PreAuthorize("@pms.hasPermission('sys:role:delete')")
    @ApiOperation(value = "删除", notes = "只能删除自己创建的")
    @PostMapping("/delete")
    public Resp del(@RequestBody List<Long> idList) {
        Boolean ok = sysRoleService.deleteBatch(idList);
        if (ok) {
            sysRoleRedisService.delCache();
            sysOrgRedisService.delCacheListTree();
            //删除菜单的缓存
            sysMenuRedisService.delMenuCaches();
            return Resp.ok();
        }
        return Resp.error();
    }

    @SysLog("根据角色ID查询角色信息")
    @GetMapping("/query/{id}")
    @ApiOperation(value = "根据角色id查询角色信息", notes = "根据角色id查询角色信息", httpMethod = "GET")
    public Resp queryOne(@PathVariable("id") Long id) {
        SysRoleVo sysRoleVo = sysRoleService.queryOne(id);
        return Resp.ok(sysRoleVo);
    }

    @SysLog("根据用户账户查询角色")
    @GetMapping("/list/{username}")
    @ApiOperation(value = "用户帐号查询用户角色", notes = "根据用户账号获取当前用户拥有的角色列", httpMethod = "GET")
    public Resp queryRoleList(@PathVariable("username") String username) {
        //超级管理员处理
        if (SecurityContextUtil.hasAdminRole()) {
            List<SysRoleEntity> roleList = sysRoleService.getUserRoleListByAdmin();
            return Resp.ok(roleList);
        }

        //缓存获取
        List<SysRoleEntity> cacheList = sysRoleRedisService.getCacheList(username);
        if (CollectionUtil.isNotEmpty(cacheList)) {
            return Resp.ok(cacheList);
        }

        List<SysRoleEntity> userRoleListByUserName = sysRoleService.getUserRoleListByUsername(username);
        //缓存保存
        sysRoleRedisService.saveCacheList(username, userRoleListByUserName);
        return Resp.ok(userRoleListByUserName);
    }


}
