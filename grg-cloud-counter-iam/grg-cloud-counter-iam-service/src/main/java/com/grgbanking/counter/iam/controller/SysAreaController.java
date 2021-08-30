package com.grgbanking.counter.iam.controller;

import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.entity.SysAreaEntity;
import com.grgbanking.counter.iam.service.SysAreaService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.validator.ValidatorUtils;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.api.bo.SysAreaBo;
import com.grgbanking.counter.iam.api.vo.SysAreaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 系统区域表
 *
 * @author zzwei6
 * @email
 * @date 2021-01-05 18:44:06
 */
@RestController
@Api(value = "区域管理接口", tags = "区域管理接口")
@RequestMapping("sys/area")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 列表树
     */
    @SysLog("区域列表树")
    @GetMapping("/query/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "区域名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaCode", value = "区域编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isEnabled", value = "状态；1 启用；0 禁用", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "i18nCode", value = "国际码", paramType = "query", dataType = "String")
    })
    @ApiOperation(value = "区域列表树", notes = "列表", httpMethod = "GET")
    public Resp<List<SysAreaVo>> queryTreeList(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "areaCode", required = false) String areaCode, @RequestParam(value = "isEnabled", required = false) String isEnabled, @RequestParam(value = "i18nCode", required = false) String i18nCode) {
        //处理超级管理员
        Long userId = null;
        if (SecurityContextUtil.notAdminRole()) {
            userId = SecurityContextUtil.getUserId();
            List<SysAreaVo> sysAreaEntitys = sysAreaService.queryTreeListNotAdminRole(name, areaCode, isEnabled, i18nCode, userId);
            return Resp.ok(sysAreaEntitys);
        } else {
            List<SysAreaVo> sysAreaEntitys = sysAreaService.queryAllTree(name, areaCode, isEnabled, i18nCode);
            return Resp.ok(sysAreaEntitys);
        }
    }

    /**
     * 根据用户名查询管理区域树
     */
    @SysLog("根据用户名查询管理区域树")
    @GetMapping("/tree")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String")
    })
    @ApiOperation(value = "区域列表树", notes = "列表", httpMethod = "GET")
    public Resp<List<SysAreaVo>> queryTreeByUsername(@RequestParam(value = "username", required = false) String username) {
        //超级管理员
        if (SecurityContextUtil.hasAdminRole()) {
            List<SysAreaVo> sysAreaVos = sysAreaService.queryAreaForAdminEnabled();
            return Resp.ok(sysAreaVos);
        }
        List<SysAreaVo> sysAreaEntitys = sysAreaService.queryTreeByUsername(username);
        return Resp.ok(sysAreaEntitys);
    }


    /**
     * 根据区域id查询区域信息
     */
    @SysLog("根据区域id查询区域信息")
    @GetMapping("/query/{id}")
    @ApiOperation(value = "区域详情", notes = "详情", httpMethod = "GET")
    public Resp<SysAreaEntity> info(@PathVariable("id") Long id) {
        SysAreaEntity sysArea = sysAreaService.getById(id);
        return Resp.ok(sysArea);
    }

    /**
     * 保存
     */
    @SysLog("区域新增")
    @PreAuthorize("@pms.hasPermission('sys:area:add')")
    @PostMapping("/save")
    @ApiOperation(value = "区域新增", notes = "新增", httpMethod = "POST")
    public Resp save(@RequestBody SysAreaBo sysAreaBo) {
        ValidatorUtils.validateEntity(sysAreaBo);
        sysAreaService.saveAndUpdate(sysAreaBo);
        return Resp.ok();

    }

    /**
     * 修改
     */
    @SysLog("区域修改")
    @PreAuthorize("@pms.hasPermission('sys:area:edit')")
    @PostMapping("/update")
    @ApiOperation(value = "区域修改", notes = "修改", httpMethod = "POST")
    public Resp update(@RequestBody SysAreaBo sysAreabo) {
        ValidatorUtils.validateEntity(sysAreabo);
        sysAreaService.saveAndUpdate(sysAreabo);
        return Resp.ok();
    }

    /**
     * 删除
     */
    @SysLog("区域删除")
    @PreAuthorize("@pms.hasPermission('sys:area:delete')")
    @PostMapping("/delete")
    @ApiOperation(value = "区域删除", notes = "删除", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList", value = "区域id", paramType = "body", dataType = "Long[]")
    })
    public Resp delete(@RequestBody List<Long> idList) {
        boolean result = sysAreaService.removeByListIds(idList);
        if (result) {
            return Resp.ok();
        } else {
            return Resp.error(RespI18nConstants.AREA1010.getCode());
        }
    }

    /**
     * 根据区域id修改区域状态（停用、启用）
     */
    @SysLog("根据区域id修改区域状态（停用、启用）")
    @PreAuthorize("@pms.hasPermission('sys:area:switch')")
    @PostMapping("/update/status")
    @ApiOperation(value = "区域状态改变", notes = "启停", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "区域id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isEnabled", value = "是否启用", paramType = "query", dataType = "String")
    })
    public Resp updateStatus(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "isEnabled", required = false) String isEnabled) {
        boolean result = false;
        if (Operate.ENABLE.code().equals(isEnabled)) {//启用
            result = sysAreaService.enable(id);
        } else {//停用
            result = sysAreaService.disable(id);
        }
        if (result) {
            return Resp.ok();
        } else {
            return Resp.error(RespI18nConstants.AREA1011.getCode());
        }
    }

    /**
     * 启用
     */
    @SysLog("区域启用")
    @PreAuthorize("@pms.hasPermission('sys:area:switch')")
    @PostMapping("/enable")
    @ApiOperation(value = "区域启用", notes = "启用", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "区域id", paramType = "body", dataType = "String")
    })
    public Resp enable(@RequestBody Long[] ids) {
        boolean result = sysAreaService.enableByListIds(Arrays.asList(ids));
        if (result) {
            return Resp.ok();
        } else {
            return Resp.error(RespI18nConstants.AREA1002.getCode());
        }
    }

    /**
     * 停用
     */
    @SysLog("区域停用")
    @PreAuthorize("@pms.hasPermission('sys:area:switch')")
    @PostMapping("/disable")
    @ApiOperation(value = "区域停用", notes = "停用", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "区域id", paramType = "body", dataType = "String")
    })
    public Resp disable(@RequestBody Long[] ids) {
        boolean result = sysAreaService.disableByListIds(Arrays.asList(ids));
        if (result) {
            return Resp.ok();
        } else {
            return Resp.error(RespI18nConstants.AREA1003.getCode());
        }
    }

}
