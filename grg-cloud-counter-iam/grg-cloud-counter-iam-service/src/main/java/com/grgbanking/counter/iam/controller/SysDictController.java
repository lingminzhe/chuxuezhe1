package com.grgbanking.counter.iam.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grgbanking.counter.iam.api.bo.SysDictBo;
import com.grgbanking.counter.iam.api.vo.SysDictVo;
import com.grgbanking.counter.iam.constans.Operate;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.entity.SysDictEntity;
import com.grgbanking.counter.iam.service.SysDictService;
import com.grgbanking.counter.iam.service.redis.SysDictRedisService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.StringUtil;
import com.grgbanking.counter.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 数据字典表
 *
 * @author zzwei6
 * @email
 * @date 2021年1月14日16:59:01
 */
@Slf4j
@RestController
@RequestMapping("/sys/dict")
@Api(value = "字典管理接口", tags = "字典管理接口")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysDictRedisService sysDictRedisService;

    @Value("${server.port}")
    private int port;

    @GetMapping("test")
    public int test(){
        log.info("端口：{}",port);
        return port;
    }

    /**
     * 根据应用类别、代码类型、代码值、代码值名称、国际化编码、启用状态查数据字典列表
     */
    @GetMapping("/query/list")
    @ApiOperation(value = "根据应用类别、代码类型、代码值、代码值名称、国际化编码、启用状态查数据字典列表", notes = "根据应用类别、代码类型、代码值、代码值名称、国际化编码、启用状态查数据字典列表")
    public Resp<IPage<SysDictVo>> queryList(Page page, SysDictBo sysDictBo) {
        IPage<SysDictVo> sysDictPage = sysDictService.querySysDictList(page, sysDictBo);
        return Resp.ok(sysDictPage);
    }


    /**
     * 根据数据字典id查询数据字典信息
     */
    @GetMapping("/query/{id}")
    @ApiOperation(value = "根据数据字典id查询数据字典信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据字典id", required = true, paramType = "path", dataType = "String")
    })
    public Resp<SysDictVo> info(@PathVariable("id") Long id) {
        SysDictEntity sysDict = sysDictService.getById(id);
        SysDictVo sysDictVo = new SysDictVo();
        BeanUtils.copyProperties(sysDict, sysDictVo);
        return Resp.ok(sysDictVo);
    }

    /**
     * 新增数据字典
     */
    @SysLog("新增数据字典")
    @PostMapping("/save")
    @ApiOperation(value = "新增数据字典")
    @PreAuthorize("@pms.hasPermission('sys:dict:add')")
    public Resp<String> save(@RequestBody SysDictBo sysDictBo) {
        sysDictRedisService.delDictCaches();
        sysDictService.save(sysDictBo);
        return Resp.ok();
    }

    /**
     * 更新数据字典
     */
    @SysLog("更新数据字典")
    @PostMapping("/update")
    @ApiOperation(value = "更新数据字典")
    @PreAuthorize("@pms.hasPermission('sys:dict:edit')")
    public Resp<String> update(@RequestBody SysDictBo sysDictBo) {
        sysDictRedisService.delDictCaches();
        sysDictService.update(sysDictBo);
        return Resp.ok();
    }

    /**
     * 根据数据字典id修改数据字典状态（停用、启用）
     */
    @SysLog("根据数据字典id修改数据字典状态（停用、启用）")
    @PostMapping("/update/status")
    @ApiOperation(value = "根据数据字典id修改数据字典状态（停用、启用）", notes = "根据数据字典id修改数据字典状态（停用、启用）", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isEnabled", value = "状态(0停用，1启用)", paramType = "query", dataType = "String")
    })
    @PreAuthorize("@pms.hasPermission('sys:dict:switch')")
    public Resp<String> updateStatus(@RequestParam("id") Long id, @RequestParam("isEnabled") String isEnabled) {
        boolean result = false;
        if (Operate.ENABLE.code().equals(isEnabled)) {//启用
            result = sysDictService.enable(id);
        } else {//停用
            result = sysDictService.disable(id);
        }
        sysDictRedisService.delDictCaches();
        if (result) {
            return Resp.ok();
        } else {
            return Resp.error(RespI18nConstants.COM1006.getCode());
        }
    }


    /**
     * 根据数据字典id列表删除数据字典信息
     */
    @SysLog("根据数据字典id列表删除数据字典信息")
    @PostMapping("/delete")
    @ApiOperation(value = "根据数据字典id列表删除数据字典信息")
    @ApiImplicitParam(name = "idList", value = "要删除的数据字典id集合", paramType = "body", dataType = "List<Long>")
    @PreAuthorize("@pms.hasPermission('sys:dict:delete')")
    public Resp delete(@RequestBody List<Long> idList) {
        sysDictRedisService.delDictCaches();
        sysDictService.removeByIds(idList);
        return Resp.ok();
    }

    /**
     * 根据应用类别获取数据字典信息列表
     */
    @GetMapping("/list/{appType}")
    @ApiOperation(value = "根据应用类别(appType)获取数据字典信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appType", value = "应用类别", required = true, paramType = "path", dataType = "String")
    })
    public Resp<List<SysDictVo>> listByAppType(@PathVariable("appType") String appType) {
        List<SysDictVo> voList = sysDictRedisService.getCacheList(appType);
        if (CollectionUtil.isNotEmpty(voList)) {
            return Resp.ok(voList);
        }
        List<SysDictVo> list = sysDictService.getListByAppType(appType);
        return Resp.ok(list);
    }

    /**
     * 根据代码类型获取数据字典信息列表（默认获取所有语言类型）
     */
    @GetMapping("/code/list/{codeType}")
    @ApiOperation(value = "根据代码类型获取数据字典信息列表（默认获取所有语言类型）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeType", value = "代码类型，默认值languageType", required = true, paramType = "path", dataType = "String")
    })
    public Resp<List<SysDictVo>> listByCodeType(@PathVariable("codeType") String codeType) {
        if (StringUtils.isBlank(codeType)) {
            codeType = "languageType";
        }
        List<SysDictVo> list = sysDictService.getListByCodeType(codeType);
        return Resp.ok(list);
    }


}
