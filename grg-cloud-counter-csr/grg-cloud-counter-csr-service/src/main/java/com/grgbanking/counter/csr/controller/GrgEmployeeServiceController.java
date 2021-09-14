package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;




/**
 * 坐席客服表
 *
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13
 */
@Api(tags = "座席端服务信息")
@RestController
@RequestMapping("csr/grgemployeeservice")
public class GrgEmployeeServiceController {
    @Autowired
    private GrgEmployeeService grgEmployeeService;

    /**
     * 列表
     */
    @ApiOperation(value = "查看座席信息信息")
    @GetMapping("/list")
    //@RequiresPermissions("csr:grgemployeeservice:list")
    public Resp list(@RequestParam Map<String, Object> params){
        PageUtils page = grgEmployeeService.queryPage(params);

        return Resp.success(page, "page");
    }


    /**
     * 信息
     */
    @ApiOperation(value = "根据employee_id查询账户信息")
    @GetMapping("/info/{id}")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp info(@PathVariable("id") String id){
		GrgEmployeeServiceEntity grgEmployeeService = this.grgEmployeeService.getByEmployeeId(id);

        return Resp.success(grgEmployeeService, "grgEmployeeService");
    }

    /**
     * 获取所有空闲座席
     */
    //TODO 分配座席问题
    @ApiOperation(value = "获取所有空闲座席")
    @GetMapping("/freeEmployee")
    //@RequiresPermissions("csr:grgemployeeservice:info")
    public Resp getFreeEmployee(){
         List<GrgEmployeeServiceEntity> list = grgEmployeeService.getFreeEmployee();

        return Resp.success(list, "空闲座席数量为："+list.size());
    }

    /**
     * 保存
     */
    @Transactional
    @ApiOperation(value = "新增座席")
    @PostMapping("/save")
    //@RequiresPermissions("csr:grgemployeeservice:save")
    public Resp save(@RequestBody GrgEmployeeServiceEntity grgEmployeeService){
		this.grgEmployeeService.save(grgEmployeeService);

        return Resp.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "更改座席状态")
    @Transactional
    @PostMapping("/update")
    //@RequiresPermissions("csr:grgemployeeservice:update")
    public Resp update(@RequestBody GrgEmployeeServiceEntity grgEmployeeService){
		this.grgEmployeeService.updateById(grgEmployeeService);

        return Resp.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("csr:grgemployeeservice:delete")
    public Resp delete(@RequestBody String[] ids){
		grgEmployeeService.removeByIds(Arrays.asList(ids));

        return Resp.success();
    }

}
