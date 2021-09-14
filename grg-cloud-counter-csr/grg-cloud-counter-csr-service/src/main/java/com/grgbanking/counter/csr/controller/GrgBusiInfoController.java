package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@Api(tags = "业务接口")
@RestController
@RequestMapping("csr/grgbusiinfo")
public class GrgBusiInfoController {
    @Autowired
    private GrgBusiInfoService grgBusiInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "查看所有业务信息")
    @GetMapping("/list")
//    @RequiresPermissions("csr:grgbusiinfo:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgBusiInfoService.queryPage(params);

        return Resp.success(page,"page");
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("csr:grgbusiinfo:info")
    public Resp info(@PathVariable("id") String id){
		GrgBusiInfoEntity grgBusiInfo = grgBusiInfoService.getById(id);

        return Resp.success(grgBusiInfo, "grgBusiInfo");
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("csr:grgbusiinfo:save")
    public Resp save(@RequestBody GrgBusiInfoEntity grgBusiInfo){
		grgBusiInfoService.save(grgBusiInfo);

        return Resp.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("csr:grgbusiinfo:update")
    public Resp update(@RequestBody GrgBusiInfoEntity grgBusiInfo){
		grgBusiInfoService.updateById(grgBusiInfo);

        return Resp.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
//    @RequiresPermissions("csr:grgbusiinfo:delete")
    public Resp delete(@RequestBody String[] ids){
		grgBusiInfoService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
