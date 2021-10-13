package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgBusiOptEntity;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import com.grgbanking.counter.csr.vo.BusiOptNumVo;
import io.swagger.annotations.Api;
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
@Api(tags = "座席操作记录")
@RestController
@RequestMapping("/grgbusiopt")
public class GrgBusiOptController {
    @Autowired
    private GrgBusiOptService grgBusiOptService;

    /**
     * 列表
     */
    @ApiOperation(value = "查看所有操作记录")
    @GetMapping("/list")
//    @RequiresPermissions("csr:grgbusiopt:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgBusiOptService.queryPage(params);

        return Resp.success(page," page");
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("csr:grgbusiopt:info")
    public Resp info(@PathVariable("id") String id){
		GrgBusiOptEntity grgBusiOpt = grgBusiOptService.getById(id);

        return Resp.success(grgBusiOpt, "grgBusiOpt");
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增账户交易流水信息")
    @PostMapping("/save")
//    @RequiresPermissions("csr:grgbusiopt:save")
    public Resp<GrgBusiOptEntity> save(@RequestBody GrgBusiOptEntity grgBusiOpt){
		grgBusiOptService.saveBusiOpt(grgBusiOpt);

        return Resp.success();
    }

    /**
     * 更改交易操作记录
     */
    @PostMapping("/update")
    @ApiOperation(value = "更改交易操作记录")
//    @RequiresPermissions("csr:grgbusiopt:update")
    public Resp update(@RequestBody GrgBusiOptEntity grgBusiOpt){
		grgBusiOptService.updateByBusiOptNo(grgBusiOpt);

        return Resp.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除业务操作记录")
    @DeleteMapping("/delete")
//    @RequiresPermissions("csr:grgbusiopt:delete")
    public Resp delete(@RequestBody String[] ids){
		grgBusiOptService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

    /**
     * 列表
     */
    //TODO 当前排队量
    @ApiOperation(value = "查看座席办理的业务量")
    @PostMapping("/getBuisOptNum")
//    @RequiresPermissions("csr:grgbusiopt:list")
    public Resp getBuisOptNum(@RequestBody GrgEmployeeServiceEntity entity){

        BusiOptNumVo busiOpt = grgBusiOptService.getBuisOptNum(entity);

        return Resp.success(busiOpt,"busiOpt");
    }

}
