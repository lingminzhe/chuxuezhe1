package com.grgbanking.counter.iam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysAuthorityEntity;
import com.grgbanking.counter.iam.service.SysAuthorityService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.iam.api.bo.SysAuthorityBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 权限标识
 *
 */
@RestController
@Api(value = "权限管理接口", tags = {"权限管理接口"})
@RequestMapping("/sys/authority")
public class SysAuthorityController {

    @Autowired
    private SysAuthorityService sysAuthorityService;

    /**
     * 根据所属服务名、所属模块名、授权标识、所属类名、所属方法名、启用状态查询权限标识列表
     */
    @SysLog("根据所属服务名、所属模块名、授权标识、所属类名、所属方法名、启用状态查询权限标识列表")
    @ApiOperation(value = "根据所属服务名、所属模块名、授权标识、所属类名、所属方法名、启用状态查询权限标识列表")
    @GetMapping("/query/list")
    public Resp<IPage<SysAuthorityEntity>> queryList(Page page, SysAuthorityBo sysAuthorityBo) {
        IPage<SysAuthorityEntity> result = sysAuthorityService.queryPageList(page, sysAuthorityBo);
        return Resp.ok(result);
    }

    /**
     * 根据所属服务名、所属模块名、授权标识、所属类名、所属方法名查询权限标识列表
     */
    @SysLog("根据所属服务名、所属模块名、授权标识、所属类名、所属方法名查询权限标识列表")
    @ApiOperation(value = "根据所属服务名、所属模块名、授权标识、所属类名、所属方法名查询权限标识列表")
    @GetMapping("/enabled/list")
    public Resp<IPage<SysAuthorityEntity>> queryEnabledList(Page page, SysAuthorityBo sysAuthorityBo) {
        IPage<SysAuthorityEntity> result = sysAuthorityService.queryEnabledPageList(page, sysAuthorityBo);
        return Resp.ok(result);
    }


    /**
     * 根据权限标识id查询权限标识信息
     */
    @SysLog("根据权限标识id查询权限标识信息")
    @ApiOperation(value = "根据权限标识id查询权限标识信息")
    @GetMapping("/query/{id}")
    public Resp<SysAuthorityEntity> info(@PathVariable("id") Long id) {
        SysAuthorityEntity sysAuthority = sysAuthorityService.getById(id);
        return Resp.ok(sysAuthority);
    }

    /**
     * 新增权限标识信息
     */
    @SysLog("新增权限标识信息")
    @ApiOperation(value = "新增权限标识信息")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys:authority:add')")
    public Resp save(@RequestBody SysAuthorityBo sysAuthorityBo) {
        sysAuthorityService.saveAuthorityByBo(sysAuthorityBo);
        return Resp.ok();
    }

    /**
     * 修改权限标识信息
     */
    @SysLog("修改权限标识信息")
    @ApiOperation(value = "修改权限标识信息")
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys:authority:edit')")
    public Resp update(@RequestBody SysAuthorityBo sysAuthorityBo) {
        sysAuthorityService.updateAuthByBo(sysAuthorityBo);
        return Resp.ok();
    }

    /**
     * 根据权限标识id修改权限标识状态（停用、启用）
     */
    @SysLog("根据权限标识id修改权限标识状态（停用、启用）")
    @ApiOperation(value = "根据权限标识id修改权限标识状态（停用、启用）")
    @PostMapping("/update/status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "isEnabled", value = "是否启用", required = true, paramType = "query", dataType = "String")
    })
    @PreAuthorize("@pms.hasPermission('sys:authority:switch')")
    public Resp updateStatus(@RequestParam("id") Long id, @RequestParam("isEnabled") String isEnabled) {
        sysAuthorityService.updateToStatus(id, isEnabled);
        return Resp.ok();
    }

    /**
     * 删除权限标识
     */
    @SysLog("删除权限标识")
    @ApiOperation(value = "删除权限标识")
    @PostMapping("/delete")
    @ApiImplicitParam(name = "idList", value = "id列表", required = true, paramType = "body", dataType = "List")
    @PreAuthorize("@pms.hasPermission('sys:authority:delete')")
    public Resp delete(@RequestBody List<Long> idList) {
        sysAuthorityService.deleteByIds(idList);
        return Resp.ok();
    }

}
