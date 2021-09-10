package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgBusiInfoEntity;
import com.grgbanking.counter.csr.service.GrgBusiInfoService;
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
@RestController
@RequestMapping("csr/grgbusiinfo")
public class GrgBusiInfoController {
    @Autowired
    private GrgBusiInfoService grgBusiInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("csr:grgbusiinfo:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgBusiInfoService.queryPage(params);

        return Resp.success(page,"page");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("csr:grgbusiinfo:info")
    public Resp info(@PathVariable("id") String id){
		GrgBusiInfoEntity grgBusiInfo = grgBusiInfoService.getById(id);

        return Resp.success(grgBusiInfo, "grgBusiInfo");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("csr:grgbusiinfo:save")
    public Resp save(@RequestBody GrgBusiInfoEntity grgBusiInfo){
		grgBusiInfoService.save(grgBusiInfo);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("csr:grgbusiinfo:update")
    public Resp update(@RequestBody GrgBusiInfoEntity grgBusiInfo){
		grgBusiInfoService.updateById(grgBusiInfo);

        return Resp.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("csr:grgbusiinfo:delete")
    public Resp delete(@RequestBody String[] ids){
		grgBusiInfoService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
