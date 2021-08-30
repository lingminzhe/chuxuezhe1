package com.grgbanking.counter.iam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysBizConfEntity;
import com.grgbanking.counter.iam.service.SysBizConfService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.iam.api.bo.SysBizConfBo;
import com.grgbanking.counter.iam.api.vo.SysBizConfVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统业务配置表
 *
 */
@RestController
@Api(value = "业务配置管理接口", tags = {"业务配置管理接口"})
@RequestMapping("/biz/conf")
public class SysBizConfController {

    @Autowired
    private SysBizConfService sysBizConfService;

    /**
     * 根据应用类型、启用状态查询业务配置列表
     */
    @SysLog("根据应用类型、启用状态查询业务配置列表")
    @ApiOperation(value = "根据应用类型、启用状态查询业务配置列表")
    @GetMapping("/query/list")
    public Resp<IPage<SysBizConfEntity>> list(Page page, String appType, String isEnabled) {
        IPage result = sysBizConfService.queryPage(page, appType,isEnabled);
        return Resp.ok(result);
    }


    /**
     * 根据id查询业务配置信息
     */
    @SysLog("根据id查询业务配置信息")
    @ApiOperation(value = "根据id查询业务配置信息")
    @GetMapping("/query/{id}")
    public Resp<SysBizConfVo> info(@PathVariable("id") Long id) {
        SysBizConfVo result = sysBizConfService.getBizConfVoById(id);
        return Resp.ok(result);
    }

    /**
     * 根据应用类型与业务编号模糊查询业务配置信息
     */
    @SysLog("根据应用类型与业务编号模糊查询业务配置信息")
    @ApiOperation(value = "根据应用类型与业务编号模糊查询业务配置信息")
    @GetMapping("/query/bizNo")
    public Resp<List<SysBizConfVo>> queryListByLikeBizNo(String appType,String bizNo) {
        List<SysBizConfVo> result =sysBizConfService.getBizListByImportBizNo(appType, bizNo);
          return Resp.ok(result);
    }

    /**
     * 新增业务配置信息
     */
    @SysLog("根据id查询业务配置信息")
    @ApiOperation(value = "新增业务配置信息")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys:biz:conf:add')")
    public Resp save(@RequestBody SysBizConfBo sysBizConfBo) {
        sysBizConfService.saveDataByBo(sysBizConfBo);
        return Resp.ok();
    }


    /**
     * 根据id修改业务配置状态（停用、启用）
     */
    @SysLog("根据id修改业务配置状态（停用、启用）")
    @ApiOperation(value = "根据id修改业务配置状态（停用、启用）")
    @PostMapping("/update/status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "isEnabled", value = "启用状态", required = true, paramType = "query", dataType = "String")
    })
    @PreAuthorize("@pms.hasPermission('sys:biz:conf:switch')")
    public Resp updateStatus(@RequestParam(value = "id")Long id,@RequestParam(value = "isEnabled")String isEnabled) {
         sysBizConfService.changeStatus(id,isEnabled);
        return Resp.ok();
    }

    /**
     * 更新业务配置信息
     */
    @SysLog("更新业务配置信息")
    @ApiOperation(value = "更新业务配置信息")
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys:biz:conf:edit')")
    public Resp update(@RequestBody SysBizConfBo sysBizConfBo) {
        sysBizConfService.updateByBo(sysBizConfBo);
        return Resp.ok();
    }

    /**
     * 删除业务配置信息
     */
    @SysLog("删除业务配置信息")
    @ApiOperation(value = "删除业务配置信息")
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('sys:biz:conf:delete')")
    public Resp delete(@RequestBody List<Long> ids) {
        sysBizConfService.removeByIds(ids);
        return Resp.ok();
    }

}
