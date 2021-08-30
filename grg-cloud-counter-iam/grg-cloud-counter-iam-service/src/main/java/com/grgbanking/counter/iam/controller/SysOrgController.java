package com.grgbanking.counter.iam.controller;

import com.grgbanking.counter.iam.api.bo.SysOrgBo;
import com.grgbanking.counter.iam.api.bo.SysOrgQueryListBo;
import com.grgbanking.counter.iam.api.vo.SysOrgOneVo;
import com.grgbanking.counter.iam.api.vo.SysOrgVo;
import com.grgbanking.counter.iam.constans.RespI18nConstants;
import com.grgbanking.counter.iam.entity.SysOrgEntity;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysOrgService;
import com.grgbanking.counter.iam.service.SysUserService;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.log.annotation.SysLog;
import com.grgbanking.counter.common.security.utils.SecurityContextUtil;
import com.grgbanking.counter.iam.service.redis.SysOrgRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author lggui1
 * @Date 2021/1/6
 * @Description 机构管理接口
 **/
@Slf4j
@RestController
@RequestMapping("/sys/org")
@Api(value = "机构管理接口", tags = "机构管理接口")
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysOrgRedisService sysOrgRedisService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 保存机构
     */
    @SysLog("保存机构")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys:org:add')")
    @ApiOperation(value = "保存机构", notes = "保存", httpMethod = "POST")
    public Resp save(@RequestBody SysOrgBo sysOrgBo) {
        Long userId = SecurityContextUtil.getUserId();
        SysOrgEntity entity = new SysOrgEntity();
        BeanUtils.copyProperties(sysOrgBo, entity);

        Boolean flag = sysOrgService.save(entity, userId);
        if (flag) {
            sysOrgRedisService.delCacheListTree();
            return Resp.ok();
        }
        return Resp.error();
    }

    /**
     * 更新机构
     */
    @SysLog("更新机构")
    @PostMapping("/update")
    @PreAuthorize("@pms.hasPermission('sys:org:edit')")
    @ApiOperation(value = "更新机构", notes = "更新", httpMethod = "POST")
    public Resp update(@RequestBody SysOrgBo sysOrgBo) {
        Long userId = SecurityContextUtil.getUserId();
        SysOrgEntity entity = new SysOrgEntity();
        BeanUtils.copyProperties(sysOrgBo, entity);
        Boolean flag = sysOrgService.update(entity, userId);
        if (flag) {
            sysOrgRedisService.delCacheListTree();
            return Resp.ok();
        }
        return Resp.error();
    }

    @SysLog("停用启用机构")
    @PreAuthorize("@pms.hasPermission('sys:org:switch')")
    @ApiOperation(value = "停用启用机构", notes = "停用启用机构", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "主键", name = "id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "状态(0停用，1启用)", name = "isEnabled", paramType = "query", dataType = "String")
    })
    @PostMapping("/update/status")
    public Resp updateStatus(@RequestParam("id") Long id, @RequestParam("isEnabled") String isEnabled) {
        Integer num = sysOrgService.updateStatus(id, isEnabled);
        if (num > 0) {
            sysOrgRedisService.delCacheListTree();
            return Resp.ok();
        }
        return Resp.error();
    }

    @SysLog("删除机构")
    @PreAuthorize("@pms.hasPermission('sys:org:delete')")
    @PostMapping("/delete")
    @ApiOperation(value = "删除机构", notes = "删除机构", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "机构id列表", name = "idList", paramType = "body", dataType = "String", allowMultiple = true)
    })
    public Resp delete(@RequestBody List<Long> idList) {
        sysOrgService.delete(idList);
        sysOrgRedisService.delCacheListTree();
        return Resp.ok();
    }

    /**
     * 首页查询机构列表
     */
    @GetMapping("/query/list")
    @ApiOperation(value = "机构管理首页查询", notes = "查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "名称", name = "name", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "是否启用1启用，0停用", name = "isEnabled", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "机构编码", name = "orgCode", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "金融机构编码", name = "finCode", paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "多语言编码", name = "i18nCode", paramType = "query", dataType = "String")
    })
    public Resp<List<SysOrgVo>> queryList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "isEnabled", required = false) String isEnabled,
            @RequestParam(value = "orgCode", required = false) String orgCode,
            @RequestParam(value = "finCode", required = false) String finCode,
            @RequestParam(value = "i18nCode", required = false) String i18nCode) {
        SysOrgBo bo = new SysOrgBo();
        bo.setName(name);
        bo.setIsEnabled(isEnabled);
        bo.setOrgCode(orgCode);
        bo.setFinCode(finCode);
        bo.setI18nCode(i18nCode);
        //处理超级管理员
        Long userId = null;
        if (SecurityContextUtil.notAdminRole()) {
            userId = SecurityContextUtil.getUserId();
        }
        //缓存获取  有查询条件的情况下程序查找数据库
        List<SysOrgVo> cacheListTree = null;
        if (sysOrgRedisService.isSaveCach(bo)) {
            cacheListTree = sysOrgRedisService.getCacheList(SecurityContextUtil.getUsername());
        }
        if (cacheListTree != null && cacheListTree.size() > 0) {
            return Resp.ok(cacheListTree);
        }
        SysOrgQueryListBo entity = new SysOrgQueryListBo();
        BeanUtils.copyProperties(bo, entity);
        List<SysOrgVo> sysOrgVos = sysOrgService.listTree(entity, userId);
        //缓存保存
        if (sysOrgRedisService.isSaveCach(bo)) {
            sysOrgRedisService.saveCacheList(SecurityContextUtil.getUsername(), sysOrgVos);
        }
        return Resp.ok(sysOrgVos);
    }

    /**
     * 查询用户管理机构列表（启用状态的）
     */
    @GetMapping("/tree/{username}")
    @ApiOperation(value = "查询用户管理机构列表（启用状态的）", notes = "查询用户管理机构列表（启用状态的）", httpMethod = "GET")
    public Resp<List<SysOrgVo>> queryListTree(@PathVariable(value = "username", required = false) String username) {

        String existUser = SecurityContextUtil.isExistUser(username);

        SysUserEntity user = sysUserService.getUserByUsername(existUser);
        if (user == null) {
            return Resp.error(RespI18nConstants.COM1007.getCode());
        }
        Long userId = null;
        boolean superUser = sysUserService.isSuperUser(user.getId());
        if (!superUser) {
            userId = user.getId();
        }

        //缓存获取
        List<SysOrgVo> cacheListTree = sysOrgRedisService.getCacheListTree(user.getId());
        if (cacheListTree != null && cacheListTree.size() > 0) {
            return Resp.ok(cacheListTree);
        }
        List<SysOrgVo> vo = sysOrgService.manageOrgTreeEnable(userId);
        //缓存保存
        sysOrgRedisService.saveCacheListTree(user.getId(), vo);
        return Resp.ok(vo);
    }

    /**
     * 查询用户管理机构列表（启用状态的）
     */
    @PostMapping("/tree/area")
    @ApiOperation(value = "查询用户管理机构列表（启用状态的）", notes = "查询", httpMethod = "POST")
    public Resp<List<SysOrgVo>> queryListTreeByArea(@RequestBody(required =false) List<Long> areaIdList,
                                                    @RequestParam(value = "username", required = false) String username) {

        String existUser = SecurityContextUtil.isExistUser(username);
        SysUserEntity user = sysUserService.getUserByUsername(existUser);
        if (user == null) {
            return Resp.error(RespI18nConstants.COM1007.getCode());
        }
        Long userId = null;
        boolean superUser = sysUserService.isSuperUser(user.getId());
        if (!superUser) {
            userId = user.getId();
        }
        List<SysOrgVo> vo = sysOrgService.manageOrgTreeEnable(userId,areaIdList);
        return Resp.ok(vo);
    }

    /**
     * 查询单个信息
     */
    @GetMapping("/query/{id}")
    @ApiOperation(value = "查询单个信息", notes = "查询", httpMethod = "GET")
    public Resp<SysOrgOneVo> queryById(@PathVariable("id") Long id) {
        SysOrgOneVo sysOrgEntity = sysOrgService.queryForUpdateById(id);
        return Resp.ok(sysOrgEntity);
    }
}
