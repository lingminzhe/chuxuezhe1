package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 业务操作流水表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@RestController
@RequestMapping("csr/grgbusiopt")
public class GrgBusiOptController {
    @Autowired
    private GrgBusiOptService grgBusiOptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("csr:grgbusiopt:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgBusiOptService.queryPage(params);

        return Resp.success(page," page");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("csr:grgbusiopt:info")
    public Resp info(@PathVariable("id") String id){
		GrgBusiOptEntity grgBusiOpt = grgBusiOptService.getById(id);

        return Resp.success(grgBusiOpt, "grgBusiOpt");
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增账户交易流水信息")
    @RequestMapping("/save")
//    @RequiresPermissions("csr:grgbusiopt:save")
    public Resp save(@RequestBody GrgBusiOptEntity grgBusiOpt){

        //
		grgBusiOptService.saveBusiOpt(grgBusiOpt);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("csr:grgbusiopt:update")
    public Resp update(@RequestBody GrgBusiOptEntity grgBusiOpt){
		grgBusiOptService.updateById(grgBusiOpt);

        return Resp.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("csr:grgbusiopt:delete")
    public Resp delete(@RequestBody String[] ids){
		grgBusiOptService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
