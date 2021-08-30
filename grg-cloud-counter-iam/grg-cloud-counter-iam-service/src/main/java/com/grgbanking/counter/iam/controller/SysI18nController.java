package com.grgbanking.counter.iam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.entity.SysI18nEntity;
import com.grgbanking.counter.iam.service.SysI18nService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.iam.api.bo.SysI18nBo;
import com.grgbanking.counter.iam.api.vo.SysI18nDataTypeVo;
import com.grgbanking.counter.iam.service.redis.SysI18nRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 国际化管理接口
 *
 */
@RestController
@Api(value = "国际化管理接口", tags = {"国际化管理接口"})
@RequestMapping("/sys/i18n")
public class SysI18nController {

    @Autowired
    private SysI18nService sysI18nService;

    @Autowired
    private SysI18nRedisService sysI18nRedisService;

    /**
     * 根据应用类别和语言类型获取国际化信息列表
     */
    @ApiOperation(value = "根据应用类别和语言类型获取国际化信息列表")
    @GetMapping("/list")
    public Resp<List<SysI18nDataTypeVo>> list(@RequestParam(value = "appType", required = true) String appType, @RequestParam(value = "i18nLanguageType", required = true) String i18nLanguageType) {
        return Resp.ok(sysI18nService.i18nList(appType, i18nLanguageType));
    }

    /**
     * 根据应用类别、数据类型、国际化编码、国际化值、语言类型查国际化列表
     */
    @ApiOperation(value = "根据应用类别、数据类型、国际化编码、国际化值、语言类型查国际化列表")
    @GetMapping("/query/list")
    public Resp<IPage<SysI18nEntity>> listI18n(Page page, SysI18nBo sysI18nBo) {
        IPage<SysI18nEntity> result = sysI18nService.queryPageList(page, sysI18nBo);
        return Resp.ok(result);
    }

    /**
     * 根据应用类型、数据类型、国际化编码查询国际化信息
     */
    @ApiOperation(value = "根据应用类型、数据类型、国际化编码查询国际化信息")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appType", value = "应用类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dataType", value = "数据类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "i18nCode", value = "国际化编码", required = true, paramType = "query", dataType = "String"),
    })
    public Resp<List<SysI18nEntity>> queryByCode(@RequestParam(value = "appType") String appType, @RequestParam(value = "dataType") String dataType, @RequestParam(value = "i18nCode") String i18nCode) {
        List<SysI18nEntity> result = sysI18nService.queryInfoByCode(appType, dataType, i18nCode);
        return Resp.ok(result);
    }


    /**
     * 查询国际化详情
     */
    @ApiOperation(value = "查询国际化详情")
    @GetMapping("/info/{id}")
    public Resp<SysI18nEntity> info(@PathVariable("id") Long id) {
        SysI18nEntity sysI18n = sysI18nService.getById(id);

        return Resp.ok(sysI18n);
    }

    /**
     * 新增国际化详情
     */
    @SysLog("新增国际化详情")
    @ApiOperation(value = "新增国际化详情")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys:i18n:add')")
    public Resp save(@RequestBody List<SysI18nBo> sysI18nBoList) {
        sysI18nService.saveBatchData(sysI18nBoList);
        return Resp.ok();
    }

    /**
     * 更新国际化接口
     */
    @SysLog("更新国际化接口")
    @ApiOperation(value = "更新国际化接口")
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys:i18n:edit')")
    public Resp update(@RequestBody List<SysI18nBo> sysI18nBoList) {
        Long count = sysI18nService.updateSysI18nBatch(sysI18nBoList);
        if (count == sysI18nBoList.size()) {//更新成功
            sysI18nRedisService.delI18nCaches();
        }
        return Resp.ok();
    }

    /**
     * 删除国际化接口
     */
    @SysLog("删除国际化接口")
    @ApiOperation(value = "删除国际化接口")
    @PostMapping("/delete")
    @PreAuthorize("@pms.hasPermission('sys:i18n:delete')")
    public Resp delete(@RequestBody List<Long> idList) {
        List<SysI18nEntity> sysI18nEntityList = sysI18nService.getI18nListByIds(idList);
        Boolean flag = sysI18nService.removeByIds(idList);
        if (flag) {
            sysI18nRedisService.delI18nCaches();
        }
        return Resp.ok();
    }

}
