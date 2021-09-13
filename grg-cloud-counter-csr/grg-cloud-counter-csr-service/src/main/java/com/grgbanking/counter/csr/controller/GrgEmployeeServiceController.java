package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;




/**
 * 坐席客服表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13 10:55:49
 */
@RestController
@RequestMapping("csr/grgemployeeservice")
public class GrgEmployeeServiceController {
    @Autowired
    private GrgEmployeeServiceService grgEmployeeServiceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("csr:grgemployeeservice:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgEmployeeServiceService.queryPage(params);

        return Resp.success(page, "page");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp info(@PathVariable("id") String id){
		GrgEmployeeServiceEntity grgEmployeeService = grgEmployeeServiceService.getById(id);

        return Resp.success(grgEmployeeService, "grgEmployeeService");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("csr:grgemployeeservice:save")
    public Resp save(@RequestBody GrgEmployeeServiceEntity grgEmployeeService){
		grgEmployeeServiceService.save(grgEmployeeService);

        return Resp.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("csr:grgemployeeservice:update")
    public Resp update(@RequestBody GrgEmployeeServiceEntity grgEmployeeService){
		grgEmployeeServiceService.updateById(grgEmployeeService);

        return Resp.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("csr:grgemployeeservice:delete")
    public Resp delete(@RequestBody String[] ids){
		grgEmployeeServiceService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
