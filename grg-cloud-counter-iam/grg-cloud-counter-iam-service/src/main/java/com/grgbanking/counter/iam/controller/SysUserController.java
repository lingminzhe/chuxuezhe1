package com.grgbanking.counter.iam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.common.core.exception.CheckedException;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysUserBo;
import com.grgbanking.counter.iam.api.bo.SysUserPasswordBo;
import com.grgbanking.counter.iam.api.bo.SysUserQueryBo;
import com.grgbanking.counter.iam.api.bo.SysUserUpdateBo;
import com.grgbanking.counter.iam.api.vo.SysNavVo;
import com.grgbanking.counter.iam.api.vo.SysUserInfoVo;
import com.grgbanking.counter.iam.api.vo.SysUserQueryVo;
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
 * @Author lggui1
 * @Date 2021/1/12
 * @Description 用户管理接口
 **/
@RestController
@Api(value = "用户管理接口", tags = "用户管理接口")
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuRedisService sysMenuRedisService;

    @SysLog("用户停用启用")
    @PreAuthorize("@pms.hasPermission('sys:user:switch')")
    @PostMapping("/update/status")
    @ApiOperation(value = "用户停用启用", notes = "用户停用启用", httpMethod = "POST")
    public Resp status(@RequestParam("username") String username, @RequestParam(value = "isEnabled", required = false) String isEnabled) {
        sysUserService.status(username, isEnabled);
        return Resp.ok();
    }

    @SysLog("解除用户锁定")
    @PreAuthorize("@pms.hasPermission('sys:user:resetlock')")
    @PostMapping("/update/lock/{username}")
    @ApiOperation(value = "解除用户锁定", notes = "解除锁定", httpMethod = "POST")
    public Resp unlock(@PathVariable("username") String username) {
        sysUserService.unlockCache(username);
        return Resp.ok();
    }
    @SysLog("用户保存")
    @PreAuthorize("@pms.hasPermission('sys:user:add')")
    @PostMapping("/save")
    @ApiOperation(value = "新增单个用户", notes = "新增", httpMethod = "POST")
    public Resp save(@RequestBody SysUserBo vo) {
        boolean mark = sysUserService.save(vo);
        if (mark) {
            //删除菜单的缓存
            sysMenuRedisService.delMenuCaches();
            return Resp.ok();
        }
        return Resp.error(RespI18nConstants.COM1006.getCode());
    }

    @SysLog("用户更新")
    @PreAuthorize("@pms.hasPermission('sys:user:edit')")
    @PostMapping("/update")
    @ApiOperation(value = "用户更新", notes = "更新用户信息", httpMethod = "POST")
    public Resp update(@RequestBody SysUserUpdateBo vo) {
        boolean mark = sysUserService.update(vo);
        if (mark) {
            //删除菜单的缓存
            sysMenuRedisService.delMenuCaches();
            return Resp.ok();
        }
        return Resp.error(RespI18nConstants.COM1006.getCode());
    }

    /**
     * 重置密码
     */
    @SysLog("用户重置密码")
    @PreAuthorize("@pms.hasPermission('sys:user:resetpwd')")
    @ApiOperation(value = "重置密码", notes = "修改")
    @PostMapping("/update/pwd/{username}")
    @ApiImplicitParam(name = "username", value = "用户账号", dataType = "String")
    public Resp resetPassword(@PathVariable("username") String username) {
        if (StringUtils.isBlank(username)) {
            throw new CheckedException(RespI18nConstants.USER1002.getCode());
        }
        sysUserService.resetPassword(username);
        return Resp.ok();
    }

    @SysLog("用户修改密码")
    @ApiOperation(value = "修改密码", notes = "根据用户账号修改用户登录密码")
    @PostMapping("/update/password")
    public Resp updatePassword(@RequestBody SysUserPasswordBo user) {
        boolean flag = sysUserService.updatePassword(user);
        if (flag) {
            return Resp.ok();
        }
        return Resp.error();
    }

    /**
     * 查询
     */
    @SysLog("用户列表查询")
    @ApiOperation(value = "用户列表", notes = "根据机构编码、用户账号、启用状态、姓名、锁定状态查询当前登录用户管理机构下的用户列表")
    @GetMapping("/query/list")
    public Resp list(Page page, SysUserQueryBo bo) {
        //处理超级管理员
        Long userId = null;
        if (SecurityContextUtil.notAdminRole()) {
            userId = SecurityContextUtil.getUserId();
        }
        IPage<SysUserQueryVo> iPage = sysUserService.queryPage(page, bo, userId);
        return Resp.ok(iPage);
    }

    @SysLog("用户删除")
    @PreAuthorize("@pms.hasPermission('sys:user:delete')")
    @PostMapping("/delete")
    @ApiOperation(value = "用户删除", notes = "删除用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id列表", name = "idList", paramType = "body", dataType = "String", allowMultiple = true)
    })
    public Resp delete(@RequestBody List<Long> idList) {
        boolean b = sysUserService.delUser(idList);
        if (b) {
            return Resp.ok();
        }
        return Resp.error();
    }

    /**
     * 根据用户帐户查询用户信息
     */
    @SysLog("根据用户帐户查询用户信息")
    @GetMapping("/query/{username}")
    @ApiOperation(value = "根据用户账号查询用户信息", notes = "用户详情")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户账号", dataType = "String")
    public Resp<SysUserInfoVo> info(@PathVariable("username") String username) {
        SysUserInfoVo userInfo = sysUserService.getUserInfoByUsername(SecurityContextUtil.isExistUser(username));
        return Resp.ok(userInfo);
    }

    @SysLog("根据用户账号获取导航菜单和可使用权限标识")
    @GetMapping("/nav/{username}")
    @ApiOperation(value = "根据用户账号获取导航菜单和可使用权限标识", notes = "根据用户账号获取导航菜单和可使用权限标识")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户账号", dataType = "String")
    public Resp<SysNavVo> nav(@PathVariable("username") String username) {
        SysNavVo nav = sysUserService.nav(SecurityContextUtil.isExistUser(username));
        return Resp.ok(nav);
    }

}
